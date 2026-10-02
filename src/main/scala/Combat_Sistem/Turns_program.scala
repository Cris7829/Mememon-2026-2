package cl.uchile.dcc
package Combat_Sistem

import Entities.*
import scala.collection.mutable.ListBuffer

/** A class responsible to calculate the order of the actions of the game.
 *
 * It calculates the maximum action bar for each unit in the game. When the maximum capacity of the
 * bar has been reached, the turn the unit gets the turn.
 *
 * @constructor Creates a new empty Turn_program.
 */
class Turns_program:

  /** A list representing the units that take part in the calculation.*/
   private val t_units: ListBuffer[Unity] = ListBuffer()

  /** The current bar for each unit. */
   private var action_bars: Map[Unity, Int] = Map()

  /** Retrieves the lists of the units that are in the Turn_program.
   *
   * @return The list of units.
   */
   def get_t_units: ListBuffer[Unity] = t_units

   /** Add a unit to the Turn_program unit's list_
    *
    * This method ensures that the unit isn't already in the Turn_program. Otherwise, does nothing.
    *
    * It also gives the unit an action bar.
    *
    * @param x The new unit
    */
   def add_t_units(x: Unity): scala.Unit =
     if !t_units.contains(x) then
       t_units += x
       action_bars = action_bars + (x -> 0)

  /** Remove a unit to the Turn_program unit's list_
   *
   * This method ensures that the unit is in the list, if isn't, the method does nothing.
   *
   * It also removes the unit's action bar.
   *
   * @param x The unit that is being removed.
   */
   def remove_t_units(x: Unity): scala.Unit =
     if t_units.contains(x) then
       t_units -= x
       action_bars = action_bars - x


   private def total_weight(x: Unity): Int =
       if x.isInstanceOf[Character] then
         val c = x.asInstanceOf[Character]
         if c.get_weapon.isDefined then
            c.get_weight + c.get_weapon.get.asInstanceOf[Armament].weight
         else
            c.get_weight
       else
          x.asInstanceOf[Unit].get_weight

  /** Calculates the max amount of action bar for a Unit
   *
   * It uses the tota_weight of the unit.
   */
   def calculate_max_action_bar: Int =
       if t_units.isEmpty then
         0
       else t_units.map(total_weight).max

  /** Retrieves the action bar amount.
   *
   * @param x The unit that it's bar it's being retriever
   */
   def get_action_bar(x: Unity): Int =
       action_bars.getOrElse(x, 0)

  /** Reset the action bar of a unit to 0
   *
   * @param x The unit
   */
   def reset_action_bars(x:Unity):scala.Unit=
     if action_bars.contains(x) then
       action_bars = action_bars.updated(x, 0)

   /** Increase the amount
    *
    * The method ensures that the increase amount its above 0. Otherwise, the method does nothing.
    *
    * @param x The amount increased
    */
   def increase_action_bars(x: Int): scala.Unit =
     if x > 0 then
       action_bars = action_bars.map((u, bar) => (u, bar + x))

  /** It gives false if the action bar of a Unit hasn't been completed.
   *
   *
   * @param x The unit
   */
   def is_bar_completed(x: Unity): Boolean =
     val max_bar = calculate_max_action_bar
     max_bar > 0 && get_action_bar(x) >= max_bar


   private def get_surplus(x: Unity): Int =
       get_action_bar(x) - calculate_max_action_bar

  /** Checks and retrieves the unit that has a surplus*/
   def get_ready_units: List[Unity] =
        t_units.filter(is_bar_completed).toList.sortBy(u => -get_surplus(u))

  /** Retrieves the unit that has the turn */
   def get_current_turn_unit: Option[Unity] =
       get_ready_units.headOption
