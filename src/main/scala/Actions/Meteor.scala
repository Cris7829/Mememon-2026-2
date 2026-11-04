package cl.uchile.dcc
package Actions

import Entities.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer




/** A class representing the action of Meteor.
 *
 *
 * @constructor Creates a new Meteor action with its name.
 */
class Meteor extends Non_Utility_Action:
  override val name: String = "Meteor"
