package cl.uchile.dcc
package Entities

import Usable.*

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer
import Actions.*

/** A  trait representing any Unity that is in the game such as characters and enemies */
trait Unity extends Source with Target:

  val name: String


  val actions_possible: ListBuffer[Action]

  def find_action(id: String): Option[Action]


  def doAction(action: Action, target: Target): Boolean


  def Do(a: Attack, t: Target): Boolean

  def Do(a: Move, t: Target): Boolean

  def Do(a: Equip_Weapon, t: Target): Boolean

  def Do(a: Use_Potion, t: Target): Boolean

  def Do(a: Thunder, t: Target): Boolean

  def Do(a: Meteor, t: Target): Boolean

  def Do(a: Heal, t: Target): Boolean

  def Do(a: Purification, t: Target): Boolean


  
  def receive(a: Attack, damage: Int): Boolean 

  def receive(a: Move, u: Unit): Boolean 

  def receive(a: Equip_Weapon, c: Character): Boolean 

  def receive(a: Use_Potion, c: Character): Boolean 

  def receive(a: Thunder, power: Int): Boolean 

  def receive(a: Meteor, power: Int): Boolean 

  def receive(a: Heal): Boolean 

  def receive(a: Purification): Boolean 

  

    

