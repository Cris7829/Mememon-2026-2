package cl.uchile.dcc
package Entities
import Exceptions.*
import Usable.{Usable_Armament, Utilizable}


import scala.collection.mutable.ListBuffer

import Action_Sistem.*
import Actions.*
import Panel.*
/** A class representing a general Unit that can be used by a player.
 * .
 *It follows the base core attributes of the Unit abstract class an adds a slot to equip an armament and
 * inventory for armaments and potions
 *
 * @param n The name of the Character.
 * @param h The health points of the Character.
 * @param d The defense points of the Character.
 * @param w The weight of the Character.
 * @param we The slot for the equipped armament of the Character.
 * @constructor Creates a new general Character with the specified basic attributes.
 */
class Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Unit(n:String, h:Int, d:Int, w:Int):
  /** A variable that can be scala.Unit or and actual armament if the Character has one equipped*/
  protected var weapon : Option[Usable_Armament] = we
  /** A variable that represents the inventory of utilizable.*/
  protected val inventory: ListBuffer[Utilizable] = ListBuffer()
  /** Retrieves the armament that its equipped.*/
  def get_weapon: Option[Usable_Armament] = weapon
  /** Retrieves the inventory.*/
  def get_inventory: ListBuffer[Utilizable] = inventory

  actions_possible ++= List(Equip_Weapon(), Use_Potion())

  /** Value that represents the max health of a Character, associated with the initial health points.*/
  val max_health: Int = h

  /** Setter for the defense of the Character, if d, it's below or equal to cero, the method does nothing,
   * otherwise it changes the defense points of a Character.
   *
   * @param d
   * .*/
  def set_defense(d:Int):scala.Unit =
    if d>0 then defense=d

  /** Setter for the equip weapon of a Character.
   *
   * It only works if its given an option that is defined with a Usable Armament in it.
   *
   * @param x The Usable Armament into an Option format
    * . */
  def set_weapon(x:Option[Usable_Armament]):scala.Unit=
    if x.isDefined then  weapon = x

  override def Do(a: Attack, t: Target): Boolean =
    if weapon.isDefined then t.receive(a, this)
    else
      throw InvalidWeaponException(s"$name does not have a weapon equipped")


  override def Do(a: Use_Potion, t: Target): Boolean =
      if get_inventory.contains(t) then t.receive(a, this)
      else false






  override def receive(a: Attack, attacker: Character): Boolean = false


  
  override def receive(a: Attack, attacker: Enemy): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
      val attack: Int = attacker.get_attack - get_defense
      if attack >= 0 then
        set_health(get_health - attack)
      true


  override def receive(a: Move, u:Unity): Boolean = false

  override def receive(a: Equip_Weapon, c: Character): Boolean = false

  override def receive(a: Use_Potion, c: Character): Boolean = false






  override def receive(a: Thunder, m: Black_Mage): Boolean = false

 


  override def receive(a: Meteor, m: Black_Mage): Boolean = false


  override def receive(a: Heal, m: White_Mage): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
      m.set_mana(m.get_mana - 15)
      set_health(get_health + max_health * 2/10 + (m.get_weapon.get.get_magic_attack_points * m.get_magic_multiplier).toInt / 4)
      m.set_magic_multiplier(1.0)
      true
    


