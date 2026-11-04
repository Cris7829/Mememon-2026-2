package cl.uchile.dcc
package Actions

import Entities.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer

/** A class representing the action of moving.
 *
 *
 * @constructor Creates a new moving action with its name.
 */
class Move extends Non_Utility_Action :
  override val name: String = "Move"
