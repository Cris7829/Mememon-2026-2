package cl.uchile.dcc
package Entities
import scala.collection.mutable.ListBuffer
import Actions.*
import Action_Sistem.*

import Exceptions.*

/** An abstract base class representing a general Unit.
 *
 * This class defines the core attributes shared by all units such as character being used by a player
 * and the characters that used the enemies. It has the name, health, defense and weight.
 * .
 *
 * @param n The name of the Unit.
 * @param h The health points of the Unit.
 * @param d The defense points of the Unit
 * @param w The weight of the Unit .
 *
 * @constructor Creates a new general Unit with the specified basic attributes.
 */
abstract class Unit (n:String, h:Int, d:Int, w:Int) extends Unity:
  /** A non-mutable variable for the name of the Unit. */
  val name: String = n

  /** A variable for the health of the Unit. */
  protected var health: Int = h

  /** A variable for the defense points of the Unit. */
  protected var defense: Int = d

  /** A variable for the defense points of the Unit. For the moment this variable may change during the combat*/
  protected var weight: Int = w


  /** Retrieves the health points of the Unit.*/
  def get_health: Int = health



  def set_health(x:Int): scala.Unit=
    if x<0 then
     health=0
    else health = x



  /** Retrieves the defense points of the Unit.*/
  def get_defense: Int = defense

  /** Retrieves the weight points of the Unit.*/
  def get_weight: Int = weight



  val actions_possible: ListBuffer[Action] = ListBuffer(Attack(), Move())

  def find_action(id: String): Option[Action] = actions_possible.find(_.name == id)



  def doAction(action: Action, target: Target): Boolean =
    if get_health <= 0 then
      throw DefeatedUnitException(s"$name is defeated and cannot do any action")
    else if find_action(action.name).isEmpty then
      throw InvalidTargetException(s"$name cannot do the action ${action.name}")
    else
      action.Causes(this, target)



  def Do(a: Attack, t: Target): Boolean = false




  def Do(a: Move, t: Target): Boolean = t.receive(a, this)




  def Do(a: Equip_Weapon, t: Target): Boolean = false

  def Do(a: Use_Potion, t: Target): Boolean = false

  def Do(a: Thunder, t: Target): Boolean = false

  def Do(a: Meteor, t: Target): Boolean = false

  def Do(a: Heal, t: Target): Boolean = false

  def Do(a: Purification, t: Target): Boolean = false





  def receive(a: Attack, attacker: Character): Boolean = false

  def receive(a: Attack, attacker: Enemy): Boolean = false

  def receive(a: Move, u: Unity):Boolean = false

  def receive(a: Equip_Weapon, c: Character): Boolean = false

  def receive(a: Use_Potion, c: Character): Boolean = false

  def receive(a: Use_Potion, m: Magic_Character): Boolean = false


  def receive(a: Thunder, m: Black_Mage): Boolean = false

  def receive(a: Meteor, m: Black_Mage): Boolean = false

  def receive(a: Heal, m: White_Mage): Boolean =
    throw InvalidTargetException(s"$name cannot receive Heal, only characters can")

  def receive(a: Purification, m: White_Mage): Boolean = 

    throw InvalidTargetException(s"$name cannot receive Purification, only enemies can")

  def receive(a: Equip_Weapon, c: Knight): Boolean = false

  def receive(a: Equip_Weapon, c: Archer): Boolean = false

  def receive(a: Equip_Weapon, c: Thieve): Boolean = false

  def receive(a: Equip_Weapon, c: Black_Mage): Boolean = false

  def receive(a: Equip_Weapon, c: White_Mage): Boolean = false












