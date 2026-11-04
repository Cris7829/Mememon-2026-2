package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer

/** A class representing the action of Heal.
 *
 *
 * @constructor Creates a new Heal action with its name.
 */
class Heal extends Non_Utility_Action:
  override val name: String = "Heal"
