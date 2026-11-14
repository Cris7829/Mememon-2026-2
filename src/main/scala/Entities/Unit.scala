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


  /** Retrieves the health points of the Unit.
   *
   * @return The health points.
   * */
  def get_health: Int = health


  /** Setter of the health points of the Unit.
   *
   * @param x The new amount, if it's below 0, the method does nothing.
   *
   */
  def set_health(x:Int): scala.Unit =
    if x<0 then
     health=0
    else health = x



  /** Retrieves the defense points of the Unit
   *
   * @return The defense points.
   * */
  def get_defense: Int = defense

  /** Retrieves the weight points of the Unit.
   *
   * @return The weight points.
   * */
  def get_weight: Int = weight


  /** A list with the possible actions that a Unit can do. By default, it can Move and Attack.
   *
   * Each Class of Unit adds to the list the actions that can do by default.
   *
   * @return the list.
   * */
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


  
  
  
  /** By default, a Unit cannot be attacked by a Character.
   * 
   * Only Enemy overrides this method because, can receive and complete the action.
   * 
   * @param a The Attack action.
   * @param attacker The Character that is trying to attack the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Attack, attacker: Character): Boolean = false

  /** By default, a Unit cannot be attacked by an Enemy.
   * 
   * Only Character overrides this method, because his and it's classes can receive and complete the action.
   * 
   * @param a The Attack action.
   * @param attacker The Enemy that is trying to attack the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Attack, attacker: Enemy): Boolean = false

  /** By default, a Unity cannot move to a Unit.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   * 
   * @param a The Move action.
   * @param u The Unity that is trying to move to Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Move, u: Unity):Boolean = false



  /** By default, a Unit cannot be equipped by a Character.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   * 
   * @param a The Equip_Weapon action.
   * @param c The Character that is trying to equip the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Equip_Weapon, c: Character): Boolean = false


  /** By default, a Unit cannot be used as a potion by a Character.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   * 
   * @param a The Use_Potion action.
   * @param c The Character that is trying to use the Character as a potion.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Use_Potion, c: Character): Boolean = false


  /** By default, a Unit cannot be used as a potion by a Magic_Character.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   * 
   * @param a The Use_Potion action.
   * @param m The Magic_Character that is trying to use the Character as a potion.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Use_Potion, m: Magic_Character): Boolean = false


  /** By default, a Unit cannot be the target of a Thunder cast made by a Black_Mage.
   *
   * Only Enemy overrides this method, because it can receive and complete de action.
   *
   * @param a The Thunder action.
   * @param m The Black_Mage that is trying to cast Thunder on the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Thunder, m: Black_Mage): Boolean = false


  /** By default, a Unit cannot be the target of a Meteor cast made by a Black_Mage.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   *
   * @param a The Meteor action.
   * @param m The Black_Mage that is trying to cast Meteor on the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Meteor, m: Black_Mage): Boolean = false

  /** By default, a Unit cannot be the target of a Heal cast made by a White_Mage.
   *
   * Only Character overrides this method, because his and it's classes can receive and complete the action.
   *
   * @param a The Heal action.
   * @param m The White_Mage that is trying to cast Heal on the Unit.
   * @throws InvalidTargetException because only characters can receive the cast.
   */
  def receive(a: Heal, m: White_Mage): Boolean =
    throw InvalidTargetException(s"$name cannot receive Heal, only characters can")


  /** By default, a Unit cannot be the target of a Purification cast made by a White_Mage.
   *
   * Only Enemy overrides this method, because it can receive and complete de action.
   *
   * @param a The Purification action.
   * @param m The White_Mage that is trying to cast Purification on the Unit.
   * @throws InvalidTargetException because only enemies can receive the cast.
   */
  def receive(a: Purification, m: White_Mage): Boolean = 

    throw InvalidTargetException(s"$name cannot receive Purification, only enemies can")



  /** By default, a Unit cannot be equipped by a Knight.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   *
   * @param a The Equip_Weapon action.
   * @param c The Knight that is trying to equip the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Equip_Weapon, c: Knight): Boolean = false

  /** By default, a Unit cannot be equipped by an Archer.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   *
   * @param a The Equip_Weapon action.
   * @param c The Archer that is trying to equip the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Equip_Weapon, c: Archer): Boolean = false

  /** By default, a Unit cannot be equipped by a Thieve.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   *
   * @param a The Equip_Weapon action.
   * @param c The Thieve that is trying to equip the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Equip_Weapon, c: Thieve): Boolean = false

  /** By default, a Unit cannot be equipped by a Black_Mage.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   *
   * @param a The Equip_Weapon action.
   * @param c The Black_Mage that is trying to equip the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Equip_Weapon, c: Black_Mage): Boolean = false

  /** By default, a Unit cannot be equipped by a White_Mage.
   *
   * No classes from Unit overrides the method, because they can't complete the action neither.
   *
   * @param a The Equip_Weapon action.
   * @param c The White_Mage that is trying to equip the Unit.
   * @return false, because a Unit can't complete the action successfully.
   */
  def receive(a: Equip_Weapon, c: White_Mage): Boolean = false












