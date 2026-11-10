package cl.uchile.dcc
package Usable

import Entities.*

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}

/** A  trait representing any armament that can be equipped and utilized by a Unit. */
trait Usable_Armament extends Utilizable:

  /** By default, defines if the Usable_Armament it's or not magic.*/
  def is_magic: Boolean

  /** Value for the name of the Usable_Armament.*/
  val name: String

  /** Value for the name of the Usable_Armament.*/
  val weight: Int

  /** Getter for the owner of the Usable_Armament.*/
  def get_owner: String

  /** Retrieves the current attack points of the armament.
   *
   * @return The current attack points.
   */
  def get_attack_points: Int
  def get_magic_attack_points: Int
  def set_attack_points(x:Int):scala.Unit


  def receive(a: Attack, attacker: Character): Boolean

  def receive(a: Attack, attacker: Enemy): Boolean

  def receive(a: Move, u: Unity): Boolean

  def receive(a: Equip_Weapon, c: Character): Boolean

  def receive(a: Use_Potion, c: Character): Boolean


  def receive(a: Thunder, m: Black_Mage): Boolean

  def receive(a: Meteor, m: Black_Mage): Boolean

  def receive(a: Heal, m: White_Mage): Boolean

  def receive(a: Purification, m: White_Mage): Boolean
  
  
  def can_be_equipped_by(c: Knight): Boolean

  def can_be_equipped_by(c: Archer): Boolean

  def can_be_equipped_by(c: Thieve): Boolean

  def can_be_equipped_by(c: Black_Mage): Boolean

  def can_be_equipped_by(c: White_Mage): Boolean

  def set_owner(c: Character): scala.Unit

