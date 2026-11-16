package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer

/** A class representing the action of Purification.
 *
 *
 * @constructor Creates a new Purification action with its name.
 */
class Purification extends Spells:
  /** Variable with the name.*/
  override val name: String = "Purification"
  /** Variable with the cost of the spell.*/
  val cost: Int = 25

  /** Starts the double dispatch for Purification: calls the Do method of the source for Purification,
   * so only a source that can cast it applies the spell.
   *
   * @param s The source that casts Purification.
   * @param t The target that receives Purification.
   * @return true if the spell was cast successfully.
   */
  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)