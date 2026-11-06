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
  override val name: String = "Thunder"

  val cost: Int = 20
  
  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
