package cl.uchile.dcc
package Actions

import Entities.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer

/** A class representing the action of Thunder.
 *
 *
 * @constructor Creates a new Thunder action with its name.
 */
class Thunder extends Non_Utility_Action:
  override val name: String = "Thunder"
