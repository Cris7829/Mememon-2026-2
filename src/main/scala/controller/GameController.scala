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

import DesignPatterns.Observer


/** The GameController is a class that it keeps track of the state of the game.
 *
 * It works directly with Players, Units , Actions, Panels and Turn_program. Uses this last one
 * to calculate and to use actions.
 */
class GameController extends Observer:

  /** Represents "time", it is used into turn program, it's the value that it's added into each
   * Unit whenever no Unit of the game has a turn yet.*/
  private val k: Double = 1.0

  /** A variable that has the phase during the game that is in the GameController.*/
  private var state: Game_Program_States = new Start_Phase

  /** A  variable that will keep the player as a list, that are in the game. */
  private var players_list:ListBuffer[Players]= ListBuffer()

  /** A  variable that will keep the enemies as a list, that are in the game. */
  private var enemies_list :ListBuffer[Enemy] = ListBuffer()

  /** A  variable that will keep the turn program.*/
  private var turns_program: Turns_program = Turns_program()

  /** A  variable that will, given a Character, its player.*/
  private var owners:mutable.Map[Character, Players] = mutable.Map()

  /** A variable that saves the last error that was thrown during an action method. */
  private var last_error: Option[String]= None

  /** A variable that has the panels of the Game.*/
  private val panels:ListBuffer[Panel]= ListBuffer()

  /** Getter for the last error variable.
   *
   * @return the last error variable.
   * */
  def get_last_error: Option[String] = last_error

  /** Getter for the panels variables.
   *
   * @return list of panels.
   * */
  def get_panels: List[Panel] = panels.toList

  /** First step for the double dispatch of the State pattern design for starting the game: it sends the information
   * of the GameController into the current state that is on.
   *
   *
   * With the method safely(), the exception NotMethodOfPhase can be caught whenever is thrown by this method,
   * its message is saved in last_error and printed.
   *
   * */
  def Start_Game() : scala.Unit = safely(())(state.Start_Game(this))

  /** First step for the double dispatch of the State pattern design for adding panels: it sends the information
   * of the GameController into the current state that is on and the panel trying to be added.
   *
   *
   * With the method safely(), the exception NotMethodOfPhase can be caught whenever is thrown by this method,
   * its message is saved in last_error and printed.
   *
   * */
  def add_panel(p: Panel): scala.Unit = safely(())(state.add_panel(p, this))

  /** It fines the panel that has the given coordinates.
   *
   * @param x The x coordinate of the Panel
   * @param y The y coordinate of the Panel
   *
   * */
  def get_panel_at(x: Int, y: Int): Option[Panel] =
    panels.find(p => p.x == x && p.y == y)


  /** Getter for the players list that are in the GameController.
   *
   * @return the players list.
   * */
  def get_players_list(): ListBuffer[Players] = players_list

  /** Getter for the enemies list that are in the GameController.
   *
   * @return the enemies list.
   * */
  def get_enemies_list(): ListBuffer[Enemy] = enemies_list

  /** Getter for the turn programs that are in the GameController.
   *
   * @return the turn program class.
   * */
  def get_turns_program(): Turns_program = turns_program

  /** Getter for the owners map.
   *
   * @return owners list.
   * */
  def get_owners: Map[Character, Players] = owners.toMap


  /** It reset, every variable such as players, enemies, turns and their base forms into empty
   * lists a new turn program and a new state class of Start_Phase.
   *
   */
  def reset_controller_to_base() : scala.Unit =
    players_list = ListBuffer()
    enemies_list = ListBuffer()
    turns_program = Turns_program()
    owners = mutable.Map()
    panels.clear()
    who_won = None
    last_error = None
    state = new Start_Phase


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

  /** First step for the double dispatch of the State pattern design for adding a player: it sends the information
   * of the GameController into the current state that is on and the player that is trying to be added.
   *
   *
   * With the method safely(), the exception NotMethodOfPhase can be caught whenever is thrown by this method,
   * its message is saved in last_error and printed.
   *
   * */
  def add_players(x: Players): scala.Unit = safely(())(state.add_players(x, this))

  /** First step for the double dispatch of the State pattern design for adding units to a player:
   * it sends the information of the GameController into the current state that is on and the Character
   * trying to be added.
   *
   *
   * With the method safely(), the exception NotMethodOfPhase can be caught whenever is thrown by this method,
   * its message is saved in last_error and printed.
   *
   * */
  def add_units_to_player(x: Players, y: Character): scala.Unit =
    safely(())(state.add_units_to_player(x, y, this))

  /** First step for the double dispatch of the State pattern design for adding an Enemy: it sends the information
   * of the GameController into the current state that is on and the Enemy trying to be added.
   *
   *
   * With the method safely(), the exception NotMethodOfPhase can be caught whenever is thrown by this method,
   * its message is saved in last_error and printed.
   *
   * */
  def add_enemies(x:Enemy):scala.Unit = safely(())(state.add_enemies(x, this))

  /** First step for the double dispatch of the State pattern design for doing an action: it sends the information
   * of the GameController into the current state that is on and the source, the target and the action.
   *
   *
   * If the action fails because a game rule is not fulfilled (it is not the turn of the unit,
   * the phase does not allow actions, the unit is defeated, etc.), the exception is caught,
   * its message is saved in last_error and printed, and the turn does not change.
   *
   *
   * @param u The Unity trying to do an action.
   * @param action The concrete action that tries to do.
   * @param target The target of the action.
   * @return false if the action was done successfully, otherwise false.
   * */
  def do_action(u: Unity, action: Action, target: Target): Boolean =
    safely(false)(state.do_action(u, action, target, this))

  /** Getter for the actions than a Unity can do.
   *
   * @param u The Unity
   * @return The list of Actions
   */
  def get_actions(u: Unity): List[Action] = u.actions_possible.toList


  /** It removes an Enemy from the enemies list.
   *
   * If somebody has already won method does nothing.
   *
   * @param x The Enemy
   */
  private def remove_enemy(x:Enemy): scala.Unit =

    if who_won.isEmpty then
      if enemies_list.contains(x) then
        enemies_list-=x

  /** It removes a Character from a player's list.
   *
   * If somebody has already won, method does nothing.
   *
   * @param x The Character.
   */
  private def remove_players_Character(x: Character): scala.Unit =
    if who_won.isEmpty then
      if owners.contains(x) then
        owners(x).remove_unit(x)
        owners.remove(x)

  /** It removes a Unit from a panel's list.
   *
   * If somebody has already won method does nothing.
   *
   * @param x The Unit.
   */
  private def remove_Unit_panel(x:Unit):scala.Unit =
    if who_won.isEmpty then
      for c<-get_panels do
        if c.get_units.contains(x) then
          c.remove_units(x)


  /** Following the observer design pattern. This method receives an Enemy that his health is 0.
   *
   * The method, removes the Character form the player's unit list, the panel that it on
   * and from the turn's program unit list.
   *
   * After all that, using verify_end() method, if the players have won it set the who_won variable to
   * Some(true), if the enemies have won it set the variable to Some(false). If nobody has won this part, does nothing.
   *
   * @param x The Character.
   */
  def actualize_death(x: Character): scala.Unit =
    turns_program.remove_unit_of_program(x)
    remove_Unit_panel(x)
    remove_players_Character(x)
    verify_end()

  /** Following the observer design pattern. This method receives an Enemy that his health is 0.
   *
   * The method, removes the Enemy form the enemies list, the panel that it on
   * and from the turn's program unit list.
   *
   *
   * After all that, using verify_end() method, if the players have won it set the who_won variable to
   * Some(true), if the enemies have won it set the variable to Some(false). If nobody has won this part, does nothing.
   *
   * @param x The Enemy.
   */
  def actualize_death(x: Enemy): scala.Unit =
    turns_program.remove_unit_of_program(x)
    remove_Unit_panel(x)
    remove_enemy(x)
    verify_end()


  /** An Option variable, Some(true) means the players have won, Some(false) means the enemies have won, None if
   * the game still on.
   * */
  private var who_won:Option[Boolean] = None

  /** Getter for the who_won variable.
   *
   * Some(true) means the players have won, Some(false) means the enemies have won, None if
   * the game still on.
   *
   * @return the variable.
   * */
  def get_who_won:Option[Boolean] = who_won

  /** It checks if the enemies or the player's unit lists for every player are empty.
   *
   * If the enemies list is empty, set who_won to Some(true) meaning that the players have won.
   * If every player's unit list are empty, set who_won to Some(false) meaning that the enemies have won.
   *
   * If any of the last ones happens, it also changes the game state to Final_phase. Otherwise, the method does nothing.
   *
   * */
  private def verify_end(): scala.Unit =
    if who_won.isEmpty then
      if enemies_list.isEmpty then
        who_won = Some(true)
        set_state(new Final_Phase)
      else if players_list.forall(_.get_List_Units.isEmpty) then
        who_won = Some(false)
        set_state(new Final_Phase)


  /** It changes the actual state variable of the GameProgram for the given one.
   *
   * @param s The new state.
   * */
  private[controller] def set_state(s: Game_Program_States): scala.Unit = state = s

  /** It adds a player to the game and its characters to the owners map and the turn program.
   *
   * @param x the player
   */
  private[controller] def add_players_to_game(x: Players): scala.Unit =
    if !players_list.contains(x) then
      players_list += x
      for c <- x.get_List_Units do
        add_unit_to_player_in_game(x, c)

  /** It adds a character to a player that is in the game, and to the turn program.
   *
   * @param x the player , y the character
   */
  private[controller] def add_unit_to_player_in_game(x: Players, y: Character): scala.Unit =
    if players_list.contains(x) && !owners.contains(y) then
      owners(y) = x
      x.add_units(y)
      turns_program.add_unit_to_program(y)
      y.set_observer(Some(this))

  /** It adds an Enemy to a player to the turn program and the enemies_list , if it's already in the Game Controller, does nothing.
   *
   * @param x The enemy
   */
  private[controller] def add_enemy_to_game(x: Enemy): scala.Unit =
    if !enemies_list.contains(x) then
      enemies_list += x
      turns_program.add_unit_to_program(x)
      x.set_observer(Some(this))

  /** It adds a panel to the panels_list, if it is already there, the method does nothing
   *
   * @param p The Panel.
   *
   * */
  private[controller] def add_panel_to_game(p: Panel): scala.Unit =
    if !panels.contains(p) then panels += p

  /** It changes the state of the GameProgram to During_Phase and starts the next turn.*/
  private[controller] def begin_game(): scala.Unit =
    set_state(new During_Phase)
    turns_program.start_next_turn(k)
    verify_end()

  /** Following the game logic, it makes a unity do an action to a Target.
   *
   * If somebody has already won, it returns false and doesn't do the action.
   *
   * It throws the NotYourTurnException it's not the given's Unity's turn.
   * If the action wasn't done successfully, prints the cause.
   *
   * @param u      The Unity
   * @param action The Action
   * @param target The Target
   * @return false if the unity could not do the action successfully or somebody has won, otherwise true.
   *
   */
  private[controller] def execute_action(u: Unity, action: Action, target: Target): Boolean =
    if !turns_program.get_turn.contains(u) then
      throw NotYourTurnException(s"It is not the turn of ${u.name}")
    val ok = u.doAction(action, target)
    if ok then
      turns_program.end_turn()
      turns_program.start_next_turn(k)
    ok

  /** Executes a method of the current phase and catches any GameException it throws.
   *
   * If the method succeeds, last_error is cleared and its result is returned.
   * If it throws a GameException, the exception is caught, its message is saved in
   * last_error and printed, and the default value is returned instead.
   *
   * @param default The value to return if the method fails.
   * @param body The method to execute. 
   * @tparam T The type of the value returned by the method.
   * @return The result of the method, or the default value if it threw a GameException.
   */
  private def safely[T](default: T)(body: => T): T =
    try
      val result = body
      last_error = None
      result
    catch
      case e: GameException =>
        last_error = Some(e.getMessage)
        println(s"Action failed: ${e.getMessage}")
        default