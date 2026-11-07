package cl.uchile.dcc
package Entities
import Exceptions.*
import Usable.Usable_Armament
import Actions.*
import Action_Sistem.*

import Panel.Panel

/** A class representing a Black Mage, a type of Magic Character.
 * .
 *It follows the base core attributes of the Magic_Character class.
 *
 * @param n The name of the Black Mage.
 * @param h The health points of the Black Mage.
 * @param d The defense points of the Black Mage.
 * @param w The weight of the Black Mage.
 * @param we The slot for the equipped armament of the Black Mage.
 * @param m The mana bar of the Black Mage.
 * @constructor Creates a new general Black Mage with the specified basic attributes and its name.
 */
class Black_Mage (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int):
  /** Auxiliary constructor to create a Black Mage without an initial equipped armament
   *
   * @param n The name of the Black Mage.
   * @param h The health points of the Black Mage.
   * @param d The defense points of the Black Mage.
   * @param w The weight of the Black Mage.
   * @param m The mana bar of the Black Mage.
   */
  def this(n: String, h: Int, d: Int, w: Int, m:Int) =
    this(n, h, d, w, Option.empty[Usable_Armament],m)

  override def Do(a: Thunder, t:Target): Boolean =
    if !get_weapon.exists(_.is_magic) then
      throw InvalidWeaponException(s"$name needs a magic weapon to cast ${a.name}")
    else if get_mana < 20 then
      throw InsufficientManaException(s"$name needs 20 mana to cast ${a.name}, but has $get_mana")
    else
      t.receive(a,this)

  override def Do(a: Meteor,t:Target): Boolean =
    if !get_weapon.exists(_.is_magic) then
      throw InvalidWeaponException(s"$name needs a magic weapon to cast ${a.name}")
    else if get_mana < 50 then
      throw InsufficientManaException(s"$name needs 50 mana to cast ${a.name}, but has $get_mana")
    else
      t.receive(a,this)
      
      
  actions_possible ++= List(Thunder(), Meteor())

  override def Do(a: Equip_Weapon, t: Target): Boolean =
    if get_inventory.contains(t) then t.receive(a, this)
    else
      throw InvalidTargetException(s"$name does not have that weapon in its inventory")