package cl.uchile.dcc
package Entities
import scala.collection.mutable.ListBuffer
import Actions.*
import Action_Sistem.*

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

  /** Retrieves the defense points of the Unit.*/
  def get_defense: Int = defense

  /** Retrieves the weight points of the Unit.*/
  def get_weight: Int = weight

  
  
  val actions_possible: ListBuffer[Action] = ListBuffer()

  def find_action(id: String): Option[Action] = None


  
  def doAction(action: Action, target: Target): Boolean = false

  
  

  def Do(a: Attack, t: Target): Boolean = false

  def Do(a: Move, t: Target): Boolean  = false

  def Do(a: Equip_Weapon, t: Target): Boolean = false

  def Do(a: Use_Potion, t: Target): Boolean = false

  def Do(a: Thunder, t: Target): Boolean = false

  def Do(a: Meteor, t: Target): Boolean = false

  def Do(a: Heal, t: Target): Boolean = false

  def Do(a: Purification, t: Target): Boolean = false
  
  
  
  
