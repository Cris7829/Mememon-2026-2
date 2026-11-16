package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer

/** A class representing the action of Heal.
 *
 *
 * @constructor Creates a new Heal action with its name.
 */
class Heal extends Spells:
  /** Variable with the name.*/
  override val name: String = "Heal"
  /** Variable with the cost of the spell.*/
  val cost: Int = 15

  /** Starts the double dispatch for Heal: calls the Do method of the source for Heal,
   * so only a source that can cast it applies the spell.
   *
   * @param s The source that casts Heal.
   * @param t The target that receives Heal.
   * @return true if the spell was cast successfully.
   */
  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
