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
  override val name: String = "Attack"

  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)