package cl.uchile.dcc
package Entities

import Usable.*
import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer
import Actions.*

import Panel.Panel

/** A  trait representing any Unity that is in the game such as characters and enemies */
trait Unity extends Source with Target:

  /** A non-mutable variable for the name of the Unity. */
  val name: String

  /** A list with the possible actions that a Unity can do.*/
  val actions_possible: ListBuffer[Action]


  /** Searches one of the possible actions of the Unity by its name.
   *
   * @param id The name of the action.
   * @return Some(action) if the Unit has it, None otherwise.
   */
  def find_action(id: String): Option[Action]


  /** Executes an action of this Unity on a target, starting the double dispatch.
   *
   * @param action The action to execute.
   * @param target The target that receives the action.
   * @return true if the action was done successfully.
   */
  def doAction(action: Action, target: Target): Boolean

  /** Setter of the health points of the Unity.
   * 
   * @param x The new amount, if it's below 0, the method does nothing.
   *
   */
  def set_health(x:Int): scala.Unit

  /** Retrieves the health points of the Unity.
   * 
   * @return The health points.
   * */
  def get_health: Int


  /** First step of the double dispatch for Attack: the Unity attacks the target.
   *
   * @param a The Attack action.
   * @param t The target of the attack.
   * @return true if the target received the attack.
   */
  def Do(a: Attack, t: Target): Boolean

  /** First step of the double dispatch for Move: the Unity moves into the target.
   *
   * @param a The Move action.
   * @param t The target.
   * @return true if the target moved into the attack successfully.
   */
  def Do(a: Move, t:Target): Boolean

  /** First step of the double dispatch for Attack: the Unity equips the target.
   *
   * @param a The Equip_Weapon action.
   * @param t The target.
   * @return true if the target was equipped.
   */
  def Do(a: Equip_Weapon, t: Target): Boolean

  /** First step of the double dispatch for Use_Potion: the Unity attacks the target.
   *
   * @param a The Use_Potion action.
   * @param t The target that will be used as a potion
   * @return true if the target was consumed successfully.
   */
  def Do(a: Use_Potion, t: Target): Boolean

  /** First step of the double dispatch for Thunder: the Unity cast the spell on the target.
   *
   * @param a The Thunder action.
   * @param t The target of the spell.
   * @return true if the target received the spell.
   */
  def Do(a: Thunder, t: Target): Boolean

  /** First step of the double dispatch for Meteor: the Unity cast the spell on the target.
   *
   * @param a The Meteor action.
   * @param t The target of the spell.
   * @return true if the target received the spell.
   */
  def Do(a: Meteor, t: Target): Boolean

  /** First step of the double dispatch for Heal: the Unity cast the spell on the target.
   *
   * @param a The Heal action.
   * @param t The target of the spell.
   * @return true if the target received the spell.
   */
  def Do(a: Heal, t: Target): Boolean

  /** First step of the double dispatch for Purification: the Unity cast the spell on the target.
   *
   * @param a The Purification action.
   * @param t The target of the spell.
   * @return true if the target received the spell.
   */
  def Do(a: Purification, t: Target): Boolean

  
  
  
  
  
  /** Receives an attack from a Character.
   *
   * @param a The Attack action.
   * @param attacker The Character that attacks.
   * @return true if the target received the attack.
   */
  def receive(a: Attack, attacker: Character): Boolean

  /** Receives an attack from an Enemy.
   *
   * @param a The Attack action.
   * @param attacker The Enemy that attacks.
   * @return true if the target received the attack.
   */
  def receive(a: Attack, attacker: Enemy): Boolean

  /** Receives a Unity that wants to move to this target.
   *
   * @param a The Move action.
   * @param u The Unity that wants to move.
   * @return true if the Unity moved.
   */
  def receive(a: Move, u:Unity): Boolean

  /** Receives the action of being consumed as a potion by a Character.
   *
   * @param a The Use_Potion action.
   * @param c The Character that consumes the target.
   * @return true if the potion was consumed.
   */
  def receive(a: Use_Potion, c: Character): Boolean

  /** Receives Thunder cast by a Black Mage.
   *
   * @param a The Thunder action.
   * @param m The Black Mage that casts Thunder.
   * @return true if the target received the spell.
   */
  def receive(a: Thunder, m: Black_Mage): Boolean

  /** Receives Meteor cast by a Black Mage.
   *
   * @param a The Meteor action.
   * @param m The Black Mage that casts Meteor.
   * @return true if the target received the spell.
   */
  def receive(a: Meteor, m: Black_Mage): Boolean

  /** Receives Heal cast by a White Mage.
   *
   * @param a The Heal action.
   * @param m The White Mage that casts Heal.
   * @return true if the Target was healed.
   */
  def receive(a: Heal, m: White_Mage): Boolean

  /** Receives Purification cast by a White Mage.
   *
   * @param a The Purification action.
   * @param m The White Mage that casts Purification.
   * @return true if the target received the spell.
   */
  def receive(a: Purification, m: White_Mage): Boolean

  /** Receives the action of being equipped by a Knight.
   *
   * @param a The Equip_Weapon action.
   * @param c The Knight that tries to equip the target.
   * @return true if the target was equipped.
   */
  def receive(a: Equip_Weapon, c: Knight): Boolean

  /** Receives the action of being equipped by a Archer.
   *
   * @param a The Equip_Weapon action.
   * @param c The Archer that tries to equip the target.
   * @return true if the target was equipped.
   */
  def receive(a: Equip_Weapon, c: Archer): Boolean

  /** Receives the action of being equipped by a Thieve.
   *
   * @param a The Equip_Weapon action.
   * @param c The Thieve that tries to equip the target.
   * @return true if the target was equipped.
   */
  def receive(a: Equip_Weapon, c: Thieve): Boolean

  /** Receives the action of being equipped by a Black_Mage.
   *
   * @param a The Equip_Weapon action.
   * @param c The Black_Mage that tries to equip the target.
   * @return true if the target was equipped.
   */
  def receive(a: Equip_Weapon, c: Black_Mage): Boolean

  /** Receives the action of being equipped by a White_Mage.
   *
   * @param a The Equip_Weapon action.
   * @param c The White_Mage that tries to equip the target.
   * @return true if the target was equipped.
   */
  def receive(a: Equip_Weapon, c: White_Mage): Boolean

  /** Receives the action of being consumed as a potion by a magic character.
   *
   * @param a The Use_Potion action.
   * @param m The magic character that tries to consume the target.
   * @return true if the target was consumed and applied its effect.
   */ 
  def receive(a: Use_Potion, m: Magic_Character): Boolean
  

    

