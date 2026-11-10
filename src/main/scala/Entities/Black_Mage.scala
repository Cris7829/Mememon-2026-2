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

  /** First step of the double dispatch to cast Thunder.
   *
   * The Black_Mage can only cast it with a magic weapon equipped and at least 20 mana.
   *
   * The target must be an Enemy.
   *
   * @param a The Thunder action.
   * @param t The target that receives Thunder.
   * @return true if the target damaged by Thunder.
   * @throws InvalidWeaponExceptionif the White Mage does not have a magic weapon equipped.
   * @throws InsufficientManaException if the White Mage has less than 20 mana.
   */
  override def Do(a: Thunder, t:Target): Boolean =
    if !get_weapon.exists(_.is_magic) then
      throw InvalidWeaponException(s"$name needs a magic weapon to cast ${a.name}")
    else if get_mana < 20 then
      throw InsufficientManaException(s"$name needs 20 mana to cast ${a.name}, but has $get_mana")
    else
      t.receive(a,this)

  /** First step of the double dispatch to cast Meteor.
   *
   * The Black_Mage can only cast it with a magic weapon equipped and at least 20 mana.
   *
   * The cast it's done to a Panel. All the units that are in it are damaged by the spell.
   *
   * The target must be a Panel.
   *
   * @param a The Meteor action.
   * @param t The target that receives Meteor.
   * @return true if the Units in the Panel where damaged by Meteor.
   * @throws InvalidWeaponExceptionif  the White Mage does not have a magic weapon equipped.
   * @throws InsufficientManaException if the White Mage has less than 50 mana.
   */
  override def Do(a: Meteor,t:Target): Boolean =
    if !get_weapon.exists(_.is_magic) then
      throw InvalidWeaponException(s"$name needs a magic weapon to cast ${a.name}")
    else if get_mana < 50 then
      throw InsufficientManaException(s"$name needs 50 mana to cast ${a.name}, but has $get_mana")
    else
      t.receive(a,this)
      
      
  actions_possible ++= List(Thunder(), Meteor())

  /** First step of the double dispatch to equip a weapon: the Black_Mage calls receive
   * on the weapon with itself, so the weapon knows it is a White Mage and checks the weapons table.
   *
   * @param a The Equip_Weapon action.
   * @param t The weapon to equip.
   * @return true if the weapon was equipped.
   * @throws InvalidTargetException if the weapon is not in the inventory of the Black_Mage.
   */
  override def Do(a: Equip_Weapon, t: Target): Boolean =
    if get_inventory.contains(t) then t.receive(a, this)
    else
      throw InvalidTargetException(s"$name does not have that weapon in its inventory")