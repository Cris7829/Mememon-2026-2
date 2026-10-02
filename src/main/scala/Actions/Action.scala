package cl.uchile.dcc
package Actions

import Entities.{Usable_Armament, Usable_Potion}
import scala.collection.mutable.ListBuffer



/** A trait representing any action that can do a Unit.*/
trait Action



/** An abstract base class representing a base for a general action that uses a Utilizable.
 *
 * @constructor Creates a new general Utility action with a name.
 */
abstract class Utility_Action extends Action:
   /** A non-mutable variable with the name of the Utility_action. */
   val name: String



/** A class representing the action to use a potion.
 *
 * It used the base mold of the Utility action and adds the specific name and the list of potions that can
 * be used with the action.
 *
 * @constructor Creates a new use_potion action.
 */
class Use_Potion extends Utility_Action :
  /** A non-mutable variable with the name "Use a potion". */
  val name: String = "Use Potion"

  /** A variable with a list that represents the potions that can be used by the action. */
  private var usable_list :  ListBuffer[Usable_Potion] = ListBuffer()

  /** Retrieves the current list of potions that can be used.
   *
   * @return The current list of potions.
   */
  def get_usable_list: ListBuffer[Usable_Potion] = usable_list

  /** It adds a potion to the list usable_list*/
  def add_usable_list(x:Usable_Potion): scala.Unit =
    usable_list += x

  /** It removes the first encounter of the potion in the list, if there are no potions that can be used, the method does nothing*/
  def use_usable_list(x:Usable_Potion): scala.Unit =
    if usable_list.contains(x) then
     usable_list -= x



class Equip_weapon extends Utility_Action :
  /** A non-mutable variable with the name "Use a potion". */
  val name: String = "Equip weapon"

  /** A variable with a list that represents the armaments that can be equipped.*/
  private var usable_list :  ListBuffer[Usable_Armament] = ListBuffer()

  /** Retrieves the current list of armaments that can be used.
   *
   * @return The current list of armaments.
   */
  def get_usable_list: ListBuffer[Usable_Armament] = usable_list

  /** It adds an armament to the list usable_list*/
  def add_usable_list(x: Usable_Armament): scala.Unit =
    usable_list += x

  /** It removes the first encounter of the potion in the list, if there are no armament that can be equipped, the method does nothing*/
  def use_usable_list(x: Usable_Armament): scala.Unit =
    if usable_list.contains(x) then
      usable_list -= x




/** An abstract class representing the action that don't use any utilities.
 *
 *
 * @constructor Creates a new non_utility_action.
 */
abstract class Non_Utility_Action extends Action:
   val name: String



/** A class representing the action of moving.
 *
 *
 * @constructor Creates a new moving action with its name.
 */
class Move extends Non_Utility_Action :
   override val name: String = "Move"





/** A class representing the action of Attack.
 *
 *
 * @constructor Creates a new Attack action with its name.
 */
class Attack extends Non_Utility_Action :
  override val name: String = "Attack"





/** A class representing the action of Thunder.
 *
 *
 * @constructor Creates a new Thunder action with its name.
 */
class Thunder extends Non_Utility_Action:
  override val name: String = "Thunder"




/** A class representing the action of Meteor.
 *
 *
 * @constructor Creates a new Meteor action with its name.
 */
class Meteor extends Non_Utility_Action:
  override val name: String = "Meteor"




/** A class representing the action of Heal.
 *
 *
 * @constructor Creates a new Heal action with its name.
 */
class Heal extends Non_Utility_Action:
  override val name: String = "Heal"




/** A class representing the action of Purification.
 *
 *
 * @constructor Creates a new Purification action with its name.
 */
class Purification extends Non_Utility_Action:
  override val name: String = "Purification"
