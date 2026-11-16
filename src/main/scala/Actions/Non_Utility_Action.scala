package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer


/** An abstract class representing the action that don't use any utilities.
 *
 *
 * @constructor Creates a new non_utility_action.
 */
abstract class Non_Utility_Action extends Action:
  /** A non-mutable variable with the name of the Non_Utility_Action. */
  val name: String

  /** Starts the double dispatch for any action that not uses a utility: calls the 
   * Do method of the Source, so only a Source that can use the potion, does the action.
   *
   * @param s The source that does the action.
   * @param t The target of the action.
   * @return true if the action was done successfully.
   */
  def Causes(s: Source, t: Target): Boolean