package cl.uchile.dcc
package Actions

import Entities.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer


/** An abstract base class representing a base for a general action that uses a Utilizable.
 *
 * @constructor Creates a new general Utility action with a name.
 */
abstract class Utility_Action extends Action:
  /** A non-mutable variable with the name of the Utility_action. */
  val name: String
