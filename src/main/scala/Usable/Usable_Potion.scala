package cl.uchile.dcc
package Usable

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}
import Entities.*


/** A  trait representing any potion that can be used by a Unit */
trait Usable_Potion extends Utilizable:

  def receive(a: Attack, attacker: Character): Boolean

  def receive(a: Attack, attacker: Enemy): Boolean

  def receive(a: Move, u: Unity): Boolean

  def receive(a: Equip_Weapon, c: Character): Boolean

  def receive(a: Use_Potion, c: Character): Boolean


  def receive(a: Thunder, m: Black_Mage): Boolean

  def receive(a: Meteor, m: Black_Mage): Boolean

  def receive(a: Heal, m: White_Mage): Boolean

  def receive(a: Purification, m: White_Mage): Boolean


  
  
  def receive(a: Use_Potion, m: Magic_Character): Boolean

 
  def apply_to(c: Character): Boolean
  def apply_to(m: Magic_Character): Boolean

