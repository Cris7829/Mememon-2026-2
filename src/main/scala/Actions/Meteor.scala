package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer




/** A class representing the action of Meteor.
 *
 *
 * @constructor Creates a new Meteor action with its name.
 */
class Meteor extends Spells:
  override val name: String = "Meteor"


  val cost: Int = 50 



  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
