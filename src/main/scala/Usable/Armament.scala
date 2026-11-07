package cl.uchile.dcc
package Usable
import Exceptions.*
import Entities.*

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}

/** An abstract base class representing a general physical armament in the game.
 *
 * This class defines the core attributes shared by all armaments, such as
 * damage, weight, name and owner
 *
 * @param n The name of the armament.
 * @param a The initial attack points of the armament.
 * @param w The weight of the armament.
 * @param o The initial owner that has armament in its inventory.
 *
 * @constructor Creates a new general armament with the specified basic attributes.
 */
abstract class Armament(n:String, a:Int, w:Int, o:Option[Character]) extends Usable_Armament:
  def is_magic: Boolean = false
  /** The immutable name of the armament. */
  val name: String = n

  /** The attack points of the armament */
  private var attack_points: Int = a

  /** The immutable weight of the armament. */
  val weight : Int = w

  /** The name of the owner of the armament. */
  private var owner : Option[Character] = o

  /** Retrieves the current attack points of the armament.
   *
   * @return The current attack points.
   */
  def get_attack_points: Int = attack_points

  /** Retrieves the current name of the owner's armament.
   *
   * @return The name of the owner.
   */
  def get_owner: String = owner.get.name

  /** Updates the attack points of the armament.
   *
   * This method ensures that the attack points cannot drop below zero. If a negative
   * value is passed, the method does nothing.
   *
   * @param x The new attack.
   */
  def set_attack_points(x: Int): scala.Unit =
    if x >= 0 then
      attack_points = x

  def get_magic_attack_points: Int = 0




  def can_be_equipped_by(c: Knight): Boolean = false

  def can_be_equipped_by(c: Archer): Boolean = false

  def can_be_equipped_by(c: Thieve): Boolean = false

  def can_be_equipped_by(c: Black_Mage): Boolean = false

  def can_be_equipped_by(c: White_Mage): Boolean = false




  def set_owner(c: Character): scala.Unit = owner = Some(c)




  def receive(a: Attack, attacker: Character): Boolean = false

  def receive(a: Attack, attacker: Enemy): Boolean = false

  def receive(a: Move, u: Unity): Boolean = false
  

  def receive(a: Use_Potion, c: Character): Boolean = false


  def receive(a: Thunder, m: Black_Mage): Boolean =false

  def receive(a: Meteor, m: Black_Mage): Boolean=false

  def receive(a: Heal, m: White_Mage): Boolean=
    throw InvalidTargetException(s"$name cannot receive Heal, only characters can")

  def receive(a: Purification, m: White_Mage): Boolean=

    throw InvalidTargetException(s"$name cannot receive Purification, only enemies can")

  
  
  
  
  
  def receive(a: Equip_Weapon, c: Character): Boolean = false

  def receive(a: Equip_Weapon, c: Knight): Boolean = equip_on(c, can_be_equipped_by(c))

  def receive(a: Equip_Weapon, c: Archer): Boolean = equip_on(c, can_be_equipped_by(c))

  def receive(a: Equip_Weapon, c: Thieve): Boolean = equip_on(c, can_be_equipped_by(c))

  def receive(a: Equip_Weapon, c: Black_Mage): Boolean = equip_on(c, can_be_equipped_by(c))

  def receive(a: Equip_Weapon, c: White_Mage): Boolean = equip_on(c, can_be_equipped_by(c))

  
  private def equip_on(c: Character, allowed: Boolean): Boolean =
    if !allowed then throw InvalidWeaponException(s"${c.name} cannot equip $name")
    else
      c.get_weapon.foreach(w => c.get_inventory += w)
      c.get_inventory -= this
      c.set_weapon(Some(this))
      set_owner(c)
      true

  def receive(a: Use_Potion, m: Magic_Character): Boolean = false    


