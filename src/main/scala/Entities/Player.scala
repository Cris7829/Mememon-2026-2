package cl.uchile.dcc
package Entities

import scala.collection.mutable.ListBuffer

/** A  trait representing any player that has its character*/
trait Player

/** A class representing any player in the game.
 *
 * This class defines the core attributes shared by all players, each has a name, a list of
 * characters and a boolean that tells if it has been defeated or not.
 *
 * @param n The name of the player.
 *
 * @constructor Creates a new player, with an empty list of characters and boolean telling that
 *  hasn't been defeated.
 */

class Players(n :String) extends Player:
 /** A non-mutable variable with the name of the player*/
 val name: String = n

 /** A variable with the list of characters*/
 protected val List_Units: ListBuffer[Unity] = ListBuffer()

 /** A boolean with false if the player hasn't been defeated or true if it has.*/
 protected var isDefeated: Boolean = false