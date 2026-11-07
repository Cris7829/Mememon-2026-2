package cl.uchile.dcc
package Entities
import Action_Sistem.*
import Actions.*
import Usable.Usable_Armament
import Exceptions.*
/** A class representing an Archer, a player's playable character.
 *
 * @param n The name of the Archer.
 * @param h The health points of the Archer.
 * @param d The defense points of the Archer.
 * @param w The weight of the Archer.
 * @param we The slot for the equipped armament of the Archer.
 * @constructor Creates a new general Thieve with the specified basic attributes and its equipped armament.
 */
class Archer (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
  /** Auxiliary constructor to create an Archer without an initial equipped armament
   *
   * @param n The name of the Archer.
   * @param h The health points of the Archer.
   * @param d The defense points of the Archer.
   * @param w The weight of the Archer.
   *
   */
  def this(n: String, h: Int, d: Int, w: Int) =
    this(n, h, d, w, Option.empty[Usable_Armament])

  override def Do(a: Equip_Weapon, t: Target): Boolean =
    if get_inventory.contains(t) then t.receive(a, this)
    else
      throw InvalidTargetException(s"$name does not have that weapon in its inventory")