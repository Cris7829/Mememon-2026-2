package cl.uchile.dcc
package Usable

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}
import Entities.{Black_Mage, Character, Enemy, Unity, White_Mage}

/** An abstract base class representing a general potion in the game.
 *
 * This class defines the core attributes shared by all potion, such as the name.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general potion with its name.
 */
abstract class Potion (n:String) extends Usable_Potion:

  /** The immutable name of the potion. */
  val name: String=n


  def receive(a: Attack, attacker: Character): Boolean = false

  def receive(a: Attack, attacker: Enemy): Boolean = false

  def receive(a: Move, u: Unity): Boolean = false

  def receive(a: Equip_Weapon, c: Character): Boolean = false
  

  def receive(a: Use_Potion, c: Character): Boolean = false


  def receive(a: Thunder, m: Black_Mage): Boolean =false

  def receive(a: Meteor, m: Black_Mage): Boolean =false

  def receive(a: Heal, m: White_Mage): Boolean =false

  def receive(a: Purification, m: White_Mage): Boolean =false
