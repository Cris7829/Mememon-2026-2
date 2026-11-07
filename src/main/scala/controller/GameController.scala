package cl.uchile.dcc
package controller
import Combat_Sistem.*
import Entities.*

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import Panel.*
import Actions.Action
import Action_Sistem.Target
import Exceptions.*

/** The GameController is a class that it keeps track of the state of the game.
 *
 * It works directly with Players, Units , Actions and Turn_program. Uses this last one
 * to calculate and to use actions.
 */
class GameController :

  /** A  variable that will keep the player as a list, that are in the game.*/
  private var players_list:ListBuffer[Players]= ListBuffer()

  /** A  variable that will keep the enemies as a list, that are in the game.*/
  private var enemies_list :ListBuffer[Enemy] = ListBuffer()

  /** A  variable that will keep the turn program.*/
  private var turns_program: Turns_program = Turns_program()

  /** A  variable that will, given a Character, its player.*/
  private var owners:mutable.Map[Character, Players] = mutable.Map()
  
  private var last_error: Option[String]= None
  
  private val panels:ListBuffer[Panel]= ListBuffer()

  /** Getter for the last error variable.*/
  def get_last_error: Option[String] = last_error

  /** Getter for the panels variables.*/
  def get_panels: List[Panel] = panels.toList

  /** It adds a panel to the panels_list, if it is already there, the method does nothing
   *
   * @param p The Panel
   *
   * */
  def add_panel(p: Panel): scala.Unit =
    if !panels.contains(p) then panels += p

  /** It fines the panel that has the given coordinates.
   *
   * @param x The x coordinate of the Panel
   * @param y The y coordinate of the Panel
   *
   * */
  def get_panel_at(x: Int, y: Int): Option[Panel] =
    panels.find(p => p.x == x && p.y == y)

  
  
  
  
  
  /** Getter for the players list that are in the GameController.*/
  def get_players_list(): ListBuffer[Players] = players_list
  /** Getter for the enemies list that are in the GameController.*/
  def get_enemies_list(): ListBuffer[Enemy] = enemies_list
  /** Getter for the turn programs that are in the GameController.*/
  def get_turns_program(): Turns_program = turns_program
  /** Getter for the owners map.*/
  def get_owners: Map[Character, Players] = owners.toMap




  /** It reset, every variable such as players, enemies, turns and their base forms into empty
   * lists and a new turn program.
   *
   */
  def reset_controller_to_base() : scala.Unit =
    players_list = ListBuffer()
    enemies_list = ListBuffer()
    turns_program = Turns_program()
    owners = mutable.Map()


  /** Get Some(Unity), if there is a Unity that needs to play his turn, otherwise returns None.
   *
   * @return Some(Unity) if there is, none if there is not.
   */
  def get_who_turn: Option[Unity] =
    if turns_program.get_turn.isDefined then
      Some(turns_program.get_turn.get)
    else None


  /** Get Some(Unity), if there is a Unity that has the next turn, otherwise returns None.
   *
   * @return Some(Unity) if there is, none if there is not.
   */
  def get_who_next_turn: Option[Unity] =
    if turns_program.get_sorted_Seq.nonEmpty then
      Some(turns_program.get_sorted_Seq.head._1)
    else None

  /** It adds a player to the game and its characters to the owners map and the turn program.
   *
   * @param x the player
   */
  def add_players(x: Players): scala.Unit =
      if !players_list.contains(x) then
        players_list += x
        for c <- x.get_List_Units do
          add_units_to_player(x, c)

  /** It adds a character to a player that is in the game, and to the turn program.
   *
   * @param x the player , y the character
   */
  def add_units_to_player(x: Players, y: Character): scala.Unit =
      if players_list.contains(x) && !owners.contains(y) then
        owners(y) = x
        x.add_units(y)
        turns_program.add_unit_to_program(y)

  /** It adds an Enemy to a player to the turn program and the enemies_list , if it's already in the Game Controller, does nothing.
   *
   * @param x The enemy
   */
  def add_enemies(x: Enemy): scala.Unit =
      if !enemies_list.contains(x) then
        enemies_list += x
        turns_program.add_unit_to_program(x)

  /** Following the game logic, it make a unity do an action to a Target.
   *
   *
   * It throws the NotYourTurnException it's not the given's Unity's turn.
   *
   * If the action wasn't done succsesfully, prints the cause.
   *
   *
   * @param u The Unity
   * @param action The Action
   * @param target The Target
   *
   * @return false if the unity could not do the action succesfully, otherwise true.
   */
  def do_action(u: Unity, action: Action, target: Target): Boolean = 
   
    val momentary_k: Double =1.0
    try
      if !turns_program.get_turn.contains(u) then
        throw NotYourTurnException(s"It is not the turn of ${u.name}")
      val ok = u.doAction(action, target)
      if ok then
        remove_defeated_units()
        turns_program.end_turn()
        turns_program.start_next_turn(momentary_k)
      last_error = None
      ok
    catch
      case e: GameException =>
        last_error = Some(e.getMessage)
        println(s"Action failed: ${e.getMessage}")
        false

  /** Getter for the actions than a Unity can do.
   *
   * @param u The Unity
   * @return The list of Actions
   */
  def get_actions(u: Unity): List[Action] = u.actions_possible.toList

  /** It removes every Unity that has 0 point of health from the turn and game program.*/
  def remove_defeated_units(): scala.Unit =
      for x <- turns_program.get_t_units_list.toList if x.get_health <= 0 do
        turns_program.remove_unit_of_program(x)
