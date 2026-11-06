package cl.uchile.dcc
package Entities

import Usable.*
import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer
import Actions.*

import Panel.Panel

/** A  trait representing any Unity that is in the game such as characters and enemies */
trait Unity extends Source with Target:

  val name: String


  val actions_possible: ListBuffer[Action]

  def find_action(id: String): Option[Action]


  def doAction(action: Action, target: Target): Boolean

  def set_health(x:Int): scala.Unit
  
  def get_health: Int
  
  def Do(a: Attack, t: Target): Boolean

  def Do(a: Move, t:Target): Boolean 

  def Do(a: Equip_Weapon, t: Target): Boolean

  def Do(a: Use_Potion, t: Target): Boolean

  def Do(a: Thunder, t: Target): Boolean

  def Do(a: Meteor, t: Target): Boolean

  def Do(a: Heal, t: Target): Boolean

  def Do(a: Purification, t: Target): Boolean


  def receive(a: Attack, attacker: Character): Boolean
  def receive(a: Attack, attacker: Enemy): Boolean

  def receive(a: Move, u:Unity): Boolean
  

  def receive(a: Use_Potion, c: Character): Boolean

  def receive(a: Thunder, m: Black_Mage): Boolean

  def receive(a: Meteor, m: Black_Mage): Boolean

  def receive(a: Heal, m: White_Mage): Boolean

  def receive(a: Purification, m: White_Mage): Boolean


  def receive(a: Equip_Weapon, c: Knight): Boolean

  def receive(a: Equip_Weapon, c: Archer): Boolean

  def receive(a: Equip_Weapon, c: Thieve): Boolean

  def receive(a: Equip_Weapon, c: Black_Mage): Boolean

  def receive(a: Equip_Weapon, c: White_Mage): Boolean

  def receive(a: Use_Potion, m: Magic_Character): Boolean
  

    

