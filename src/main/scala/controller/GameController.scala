package cl.uchile.dcc
package controller
import Combat_Sistem.*
import Entities.*

import scala.collection.mutable.ListBuffer

/**
 *
 *
 *
 */
class GameController :

  /** A  variable that will keep the player as a list, that are in the game.*/
  private var players_list:ListBuffer[Player]= ListBuffer()

  /** A  variable that will keep the enemies as a list, that are in the game.*/
  private var enemies_list :ListBuffer[Enemy] = ListBuffer()

  /** A  variable that will keep the turn program.*/
  private var turns_program: Turns_program = Turns_program()



  /** A  variable that will keep the player as a list, that are in the game.
   *
   * It will be for a game reset. It only has the initial players and the ones that are added
   * along the game but, it doesn't keep track of any change with the use of actions.
   */
  private var base_players_list: ListBuffer[Player] = ListBuffer()

  /** A  variable that will keep the enemies as a list, that are in the game.
   *
   * It will be for a game reset. It only has the initial enemies and the ones that are added
   * along the game but, it doesn't keep track of any change with the use of actions.
   */
  private var base_enemies_list :ListBuffer[Enemy] = ListBuffer()

  /** A  variable that will keep the turn_program.
   *
   * It will be for a game reset. It has the units that were added during the game,
   * but in their base state.
   */
  private var base_turns_program: Turns_program = Turns_program()

  /** Getter for the players list that are in the GameController.*/
  def get_players_list(): ListBuffer[Player] = players_list
  /** Getter for the enemies list that are in the GameController.*/
  def get_enemies_list(): ListBuffer[Enemy] = enemies_list
  /** Getter for the turn programs that are in the GameController.*/
  def get_turns_program(): Turns_program = turns_program
  /** Getter for the players list made for the game reset, that is in the GameController.*/
  def get_base_players_list(): ListBuffer[Player] = base_players_list
  /** Getter for the enemies list made for the game reset, that is in the GameController.*/
  def get_base_enemies_list(): ListBuffer[Enemy] = base_enemies_list
  /** Getter for the turn program made for the game reset, that is in the GameController.*/
  def get_base_turns_program(): Turns_program = base_turns_program






  /** It reset the game into a state where, all the units are in their base form
   * every unit that were added before the reset are after the reset.
   *.
   */
  def reset_controller() : scala.Unit =
    players_list = base_players_list
    enemies_list = base_enemies_list
    turns_program = base_turns_program


  /** It reset, every variable such as players, enemies, turns and their base forms into empty
   * lists and a new turn program.
   *
   */
  def reset_controller_to_base() : scala.Unit =
    players_list = ListBuffer()
    enemies_list = ListBuffer()
    turns_program = Turns_program()

    base_players_list = ListBuffer()
    base_enemies_list = ListBuffer()
    base_turns_program = Turns_program()




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


