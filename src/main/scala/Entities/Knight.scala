package cl.uchile.dcc
package Entities
import Exceptions.*
import Usable.*
import Action_Sistem.*
import Actions.*

/** A class representing a Knight, a player's playable character.
 *
 * @param n The name of the Knight.
 * @param h The health points of the Knight.
 * @param d The defense points of the Knight.
 * @param w The weight of the Knight.
 * @param we The slot for the equipped armament of the Knight.
 * @constructor Creates a new general Thieve with the specified basic attributes and its equipped armament.
 */
class Knight (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
  /** Auxiliary constructor to create a Knight without an initial equipped armament
   *
   * @param n The name of the Knight.
   * @param h The health points of the Knight.
   * @param d The defense points of the Knight.
   * @param w The weight of the Knight.
   *
   */
  def this(n:String, h:Int, d:Int, w:Int) =
    this(n, h, d, w ,Option.empty[Usable_Armament])

  /** First step of the double dispatch to equip a weapon: the Knight calls receive
   * on the weapon with itself, so the weapon knows it is a Knight and checks the weapons table.
   *
   * @param a The Equip_Weapon action.
   * @param t The weapon to equip.
   * @return true if the weapon was equipped.
   * @throws InvalidTargetException if the weapon is not in the inventory of the Knight.
   */
  override def Do(a: Equip_Weapon, t: Target): Boolean =
    if get_inventory.contains(t) then t.receive(a, this)
    else
      throw InvalidTargetException(s"$name does not have that weapon in its inventory")  
