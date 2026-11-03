package cl.uchile.dcc
package Combat_Sistem

import Entities.*
import scala.collection.mutable
import scala.collection.mutable.ListBuffer



class Turns_program:


   private val t_units: ListBuffer[Unity] = ListBuffer()

   private var turn:  Option[Unity] = None

   private var during_program_map: mutable.Map[Unity, Double] = mutable.Map()


   private val action_bars:  mutable.Map[Unity, Double] = mutable.Map()


   private var sorted_Seq: Seq[(Unity, Double)] = Seq()


   def get_turn: Option[Unity] = turn

   def get_t_units_list: ListBuffer[Unity] = t_units

   def get_sorted_Seq: Seq[(Unity, Double)] = sorted_Seq

   def get_during_program_map: mutable.Map[Unity, Double] = during_program_map

   def get_action_bars:  mutable.Map[Unity, Double] = action_bars


   def add_unit_to_program(x: Character): scala.Unit =
     if !t_units.contains(x) then
       t_units += x
       during_program_map(x) = 0
       action_bars(x) = max_action_bar(x)

   def add_unit_to_program(x: Enemy): scala.Unit =
     if !t_units.contains(x) then
      t_units += x
      during_program_map(x)=  0
      action_bars(x) = max_action_bar(x)


   def remove_unit_of_program(x: Unity): scala.Unit =
     if t_units.contains(x) then
       t_units -= x
       during_program_map.remove(x)
       action_bars.remove(x)



   private def max_action_bar(x: Character): Double =
    if x.get_weapon.isEmpty then
      x.get_weight
    else
      x.get_weight + 0.5 * x.get_weapon.get.weight


    
   private def max_action_bar(x: Enemy) : Double =
     x.get_weight.toDouble


   def add_action_bar(k:Double): scala.Unit=
     if sorted_Seq!=Seq() then
       for x <- t_units do
         during_program_map(x)= during_program_map(x) + k

     if check_action_bar then
       create_seq_turns()


   def check_action_bar : Boolean=
     for x <- t_units do
       if during_program_map(x)>=action_bars(x) then
         return true
     false



   private def surplus(x:Unity):Double= action_bars(x)-during_program_map(x)


   private def create_seq_turns(): scala.Unit =
      for x <- t_units do
       if surplus(x) >= 0 then
         sorted_Seq = sorted_Seq :+ (x, surplus(x))
         during_program_map(x)=0

      sorted_Seq = sorted_Seq.sortBy(-_._2)


   private def a_turn(): scala.Unit =
     if sorted_Seq.nonEmpty then
       turn = Some(sorted_Seq.head._1)
       sorted_Seq = sorted_Seq.tail













     



