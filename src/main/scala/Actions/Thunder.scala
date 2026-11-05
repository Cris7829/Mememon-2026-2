package cl.uchile.dcc
package Actions
import Usable.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer

/** A class representing the action of Thunder.
 *
 *
 * @constructor Creates a new Thunder action with its name.
 */
class Thunder extends Spells:
  override val name: String = "Thunder"
