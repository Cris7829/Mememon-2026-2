package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer


/** An abstract class representing the action that don't use any utilities.
 *
 *
 * @constructor Creates a new non_utility_action.
 */
abstract class Non_Utility_Action extends Action:
  val name: String