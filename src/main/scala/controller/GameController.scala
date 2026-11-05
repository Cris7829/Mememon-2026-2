package cl.uchile.dcc
package controller
import Combat_Sistem.*
import Entities.*

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 *
 *
 *
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