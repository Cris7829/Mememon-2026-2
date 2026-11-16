package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer


/** An abstract base class representing a base for a general action that uses a Utilizable.
 *
 * @constructor Creates a new general Utility action with a name.
 */
abstract class Utility_Action extends Action:
  /** A non-mutable variable with the name of the Utility_action. */
  val name: String

  /** Starts the double dispatch for any action that uses a utility: calls the 
   * Do method of the Source, so only a Source that can use the potion, does the action.
   *
   * @param s The source that does the action.
   * @param t The target of the action.
   * @return true if the action was done successfully.
   */
  def Causes(s: Source, t: Target): Boolean
