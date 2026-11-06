package cl.uchile.dcc
package Entities

import Usable.Usable_Armament

import Actions.*
import Action_Sistem.*

/** A class representing a White Mage, a type of Magic Character.
 * .
 *It follows the base core attributes of the Magic_Character class.
 *
 * @param n The name of the White Mage.
 * @param h The health points of the White Mage.
 * @param d The defense points of the White Mage.
 * @param w The weight of the White Mage.
 * @param we The slot for the equipped armament of the White Mage.
 * @param m The mana bar of the White Mage.
 * @constructor Creates a new general White Mage with the specified basic attributes and its name.
 */
class White_Mage (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int):
  /** Auxiliary constructor to create a White Mage without an initial equipped armament
   *
   * @param n The name of the White Mage.
   * @param h The health points of the White Mage.
   * @param d The defense points of the White Mage.
   * @param w The weight of the White Mage.
   * @param m The mana bar of the White Mage.
   */
  def this(n: String, h: Int, d: Int, w: Int, m:Int) =
    this(n, h, d, w, Option.empty[Usable_Armament],m)


  override def Do(a: Heal, t: Target): Boolean =
    if get_mana >= 15 && get_weapon.exists(_.get_magic_attack_points > 0) then
      t.receive(a,this)
    else
      false

  override def Do(a: Purification, t: Target): Boolean =
    if get_mana >= 25 && get_weapon.exists(_.get_magic_attack_points > 0) then
      t.receive(a,this)
    else
      false

  actions_possible ++= List(Heal(), Purification())

  override def Do(a: Equip_Weapon, t: Target): Boolean =
    if get_inventory.contains(t) then t.receive(a, this)
    else false
