package cl.uchile.dcc
package Combat_Sistem

import Entities.*
import scala.collection.mutable.ListBuffer

class Turns_program:
   private val t_units: ListBuffer[Unity] = ListBuffer()
   private var action_bars: Map[Unity, Int] = Map()


   def get_t_units: ListBuffer[Unity] = t_units

   def add_t_units(x: Unity): scala.Unit =
     if !t_units.contains(x) then
       t_units += x
       action_bars = action_bars + (x -> 0)

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


   def calculate_max_action_bar: Int =
       if t_units.isEmpty then 0
         else t_units.map(total_weight).max

   def get_action_bar(x: Unity): Int =
       action_bars.getOrElse(x, 0)

   def reset_action_bars(x:Unity):scala.Unit=
     if action_bars.contains(x) then
       action_bars = action_bars.updated(x, 0)

   def increase_action_bars(amount: Int): scala.Unit =
     if amount > 0 then
       action_bars = action_bars.map((u, bar) => (u, bar + amount))

   def is_bar_completed(x: Unity): Boolean =
     val max_bar = calculate_max_action_bar
     max_bar > 0 && get_action_bar(x) >= max_bar    

   def get_surplus(x: Unity): Int =
       get_action_bar(x) - calculate_max_action_bar

   def get_ready_units: List[Unity] =
       t_units.filter(is_bar_completed).toList.sortBy(u => -get_surplus(u))

   def get_current_turn_unit: Option[Unity] =
       get_ready_units.headOption
