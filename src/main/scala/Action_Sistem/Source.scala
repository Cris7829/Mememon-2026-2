package cl.uchile.dcc
package Action_Sistem
import Actions.*

import scala.collection.mutable.ListBuffer

trait Source:
  /** The name of the source. */
  val name:String

  /** The actions that this source is able to do. */
  val actions_possible: ListBuffer[Action]


  /** Searches one of the possible actions of the source by its name.
   *
   * @param id The name of the action.
   * @return Some(action) if the source has it, None otherwise.
   */
  def find_action(id: String): Option[Action]

  /** Executes an action of this source on a target, starting the double dispatch.
   *
   * @param action The action to execute.
   * @param target The target that receives the action.
   * @return true if the action was done successfully.
   */
  def doAction(action: Action, target: Target):Boolean


  /** First step of the double dispatch for Attack: the source attacks the target.
   *
   * @param a The Attack action.
   * @param t The target of the attack.
   * @return true if the target received the attack.
   */
  def Do(a: Attack, t: Target): Boolean

  /** First step of the double dispatch for Move: the source moves into the target.
   *
   * @param a The Move action.
   * @param t The target.
   * @return true if the target moved into the attack successfully.
   */
  def Do(a: Move, t: Target): Boolean

  /** First step of the double dispatch for Attack: the source equips the target.
   *
   * @param a The Equip_Weapon action.
   * @param t The target.
   * @return true if the target was equipped.
   */
  def Do(a: Equip_Weapon, t: Target): Boolean

  /** First step of the double dispatch for Use_Potion: the source attacks the target.
   *
   * @param a The Use_Potion action.
   * @param t The target that will be used as a potion
   * @return true if the target was consumed successfully.
   */
  def Do(a: Use_Potion, t: Target): Boolean

  /** First step of the double dispatch for Thunder: the source cast the spell on the target.
   *
   * @param a The Thunder action.
   * @param t The target of the spell.
   * @return true if the target received the spell.
   */
  def Do(a: Thunder, t: Target): Boolean

  /** First step of the double dispatch for Meteor: the source cast the spell on the target.
   *
   * @param a The Meteor action.
   * @param t The target of the spell.
   * @return true if the target received the spell.
   */
  def Do(a: Meteor, t: Target): Boolean

  /** First step of the double dispatch for Heal: the source cast the spell on the target.
   *
   * @param a The Heal action.
   * @param t The target of the spell.
   * @return true if the target received the spell.
   */
  def Do(a: Heal, t: Target): Boolean

  /** First step of the double dispatch for Purification: the source cast the spell on the target.
   *
   * @param a The Purification action.
   * @param t The target of the spell.
   * @return true if the target received the spell.
   */
  def Do(a: Purification, t: Target): Boolean 
 
  