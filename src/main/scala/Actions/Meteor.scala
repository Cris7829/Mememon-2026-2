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
  /** Variable with the name.*/
  override val name: String = "Meteor"

  /** Variable with the cost of the spell.*/
  val cost: Int = 50


  /** Starts the double dispatch for Meteor: calls the Do method of the source for Meteor,
   * so only a source that can cast it applies the spell.
   *
   * @param s The source that casts Meteor.
   * @param t The target that receives Meteor.
   * @return true if the spell was cast successfully.
   */
  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
