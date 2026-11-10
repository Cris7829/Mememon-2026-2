package cl.uchile.dcc
package Actions
import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer

/** A class representing the action of Thunder.
 *
 *
 * @constructor Creates a new Thunder action with its name.
 */
class Thunder extends Spells:

  /** Variable with the name.*/
  override val name: String = "Thunder"
  
  /** Variable with the cost of the spell.*/
  val cost: Int = 20

  /** Starts the double dispatch for Thunder: calls the Do method of the source for Thunder,
   * so only a source that can cast it applies the spell.
   *
   * @param s The source that casts Thunder.
   * @param t The target that receives Thunder.
   * @return true if the spell was cast successfully.
   */
  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
