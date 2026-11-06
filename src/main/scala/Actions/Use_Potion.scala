package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer



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



  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
