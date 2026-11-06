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
  override val name: String = "Purification"

  val cost: Int = 25
  
  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)