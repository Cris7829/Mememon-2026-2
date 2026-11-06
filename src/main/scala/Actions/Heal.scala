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
  override val name: String = "Heal"

  val cost: Int = 15

  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
