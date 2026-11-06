package cl.uchile.dcc
package Entities

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



  override def Do(a: Attack, t: Target): Boolean =
    weapon.isDefined && t.receive(a,this) && t.get_health>0


  override def Do(a: Equip_Weapon, t: Target): Boolean = t.receive(a, this)

  override def Do(a: Use_Potion, t: Target): Boolean = t.receive(a, this)




  override def receive(a: Attack, attacker: Character): Boolean =
    val attack: Int = attacker.get_weapon.get.get_attack_points - get_defense
    if attack>=0 then
      set_health(get_health-attack)
    true




  override def receive(a: Attack, attacker: Enemy): Boolean =
    val attack: Int = attacker.get_attack-get_defense
    if attack >= 0 then
      set_health(get_health - attack)
    true


  override def receive(a: Move, u: Unity): Boolean = false

  override def receive(a: Equip_Weapon, c: Character): Boolean = false

  override def receive(a: Use_Potion, c: Character): Boolean = false


  override def receive(a: Thunder, m: Black_Mage): Boolean =

    m.set_mana(m.get_mana-20)
    if m.get_weapon.get.get_attack_points>get_health then
     set_health(get_health - m.get_weapon.get.get_attack_points)
    else set_health(0)
    true


  override def receive(a: Meteor, m: Black_Mage): Boolean = false


  override def receive(a: Heal, m: White_Mage): Boolean =
    m.set_mana(m.get_mana - 15)
    set_health((get_health.toDouble*1.2).toInt+m.get_weapon.get.get_attack_points/4)
    true

  override def receive(a: Purification, m: White_Mage): Boolean =

    m.set_mana(m.get_mana - 25)
    set_health((get_health.toDouble * 0.85).toInt + m.get_weapon.get.get_attack_points / 2)
    true