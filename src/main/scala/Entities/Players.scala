package cl.uchile.dcc
package Entities

import scala.collection.mutable.ListBuffer



/** A class representing any player in the game.
 *
 * This class defines the core attributes shared by all players, each has a name, a list of
 * characters and a boolean that tells if it has been defeated or not.
 *
 * @param n The name of the player.
 * @constructor Creates a new player, with an empty list of characters and boolean telling that
 *  hasn't been defeated.
 */

class Players(n :String) extends Player:
  /** A non-mutable variable with the name of the player*/
  val name: String = n

  /** A variable with the list of characters*/
  private val List_Units: ListBuffer[Character] = ListBuffer()

  /** A boolean with false if the player hasn't been defeated or true if it has.*/
  private var isDefeated: Boolean = false


  /** A getter for the units that the player has.
   *
   * @return List with Unity.
   * */
  def get_List_Units: ListBuffer[Character] = List_Units



  /** A getter for the isDefeated variable.
   *
   * @return boolean, false if the player hasn't been defeated yet, otherwise true.
   * */
  def get_is_Defeated: Boolean = isDefeated


  /** Check if all the units of a player have 0 points of health, and chances the isDefeated variable to true.
   *
   * If there is at least one Unity of the list that has health points, the method does nothing.
   *
   * */
  def was_Defeated(): scala.Unit =
    for x <- List_Units do
      if x.get_health != 0 then
         return 
    isDefeated = true

  /** It adds a Characte to the list, if its already there, does nothing.*/
  def add_units(x:Character):scala.Unit =
    if !List_Units.contains(x) && x.get_health>0 then
      List_Units += x 
    



