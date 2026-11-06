package cl.uchile.dcc
package controller
import Combat_Sistem.*
import Entities.*

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import Panel.*
import Actions.Action
import Action_Sistem.Target


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

  
  
  
  private val panels:ListBuffer[Panel]= ListBuffer()


  def get_panels: List[Panel] = panels.toList

  def add_panel(p: Panel): scala.Unit =
    if !panels.contains(p) then panels += p

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
  
  def add_enemies(x: Enemy): scala.Unit =
      if !enemies_list.contains(x) then
        enemies_list += x
        turns_program.add_unit_to_program(x)


  def do_action(u: Unity, action: Action, target: Target): Boolean =
    u.doAction(action,target)
  
  def get_actions(u: Unity): List[Action] = u.actions_possible.toList
  
