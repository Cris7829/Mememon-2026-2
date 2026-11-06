package cl.uchile.dcc
package Usable

import Entities.*

/** A  trait representing any armament that can be equipped and utilized by a Unit. */
trait Usable_Armament extends Utilizable:
  def weight: Int
  def get_attack_points: Int
  def get_magic_attack_points: Int


  def can_be_equipped_by(c: Knight): Boolean

  def can_be_equipped_by(c: Archer): Boolean

  def can_be_equipped_by(c: Thieve): Boolean

  def can_be_equipped_by(c: Black_Mage): Boolean

  def can_be_equipped_by(c: White_Mage): Boolean

  def set_owner(c: Character): scala.Unit

