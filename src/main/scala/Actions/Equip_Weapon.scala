package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer



class Equip_Weapon extends Utility_Action :
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

  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)    