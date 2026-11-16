package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer
import  Action_Sistem.*

/** A class representing the action of Attack.
 *
 *
 * @constructor Creates a new Attack action with its name.
 */
class Attack extends Non_Utility_Action :
  /** Variable with the name. */
  override val name: String = "Attack"

  /** Starts the double dispatch for Attack: calls the Do method of the source for Attack,
   * so only a source that attack, does the action.
   *
   * @param s The source that attacks.
   * @param t The target that receives Attack.
   * @return true if the action was done successfully.
   */
  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)