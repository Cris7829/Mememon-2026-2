package cl.uchile.dcc
package Entities
import Exceptions.*
import Usable.*

import Action_Sistem.Target
import Actions.Equip_Weapon

/** A class representing a Thieve, a player's playable character.
 *
 * @param n The name of the Thieve.
 * @param h The health points of the Thieve.
 * @param d The defense points of the Thieve.
 * @param w The weight of the Thieve.
 * @param we The slot for the equipped armament of the Thieve.
 *
 * @constructor Creates a new general Thieve with the specified basic attributes and its equipped armament.
 */
class Thieve(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
  /** Auxiliary constructor to create a Thieve without an initial equipped armament
   *
   * @param n The name of the Thieve.
   * @param h The health points of the Thieve.
   * @param d The defense points of the Thieve.
   * @param w The weight of the Thieve.
   *
   */
  def this(n:String, h:Int, d:Int, w:Int) =
    this(n, h, d, w ,Option.empty[Usable_Armament])

  /** First step of the double dispatch to equip a weapon: the Thieve calls receive
   * on the weapon with itself, so the weapon knows it is a Thieve and checks the weapons table.
   *
   * @param a The Equip_Weapon action.
   * @param t The weapon to equip.
   * @return true if the weapon was equipped.
   * @throws InvalidTargetException if the weapon is not in the inventory of the Thieve.
   */
  override def Do(a: Equip_Weapon, t: Target): Boolean =
      if get_inventory.contains(t) then t.receive(a, this)
      else
        throw InvalidTargetException(s"$name does not have that weapon in its inventory")