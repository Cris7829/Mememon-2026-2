package cl.uchile.dcc
package Combat_Sistem

import Entities.*
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


/** A class representing the turn program, calculates which Unity has the turn.
 *
 * It can calculate each Unity action bar, and whenever one completes his, it gives the turn to that one.
 *
 * If there is more than one Unity that has completed hs action bar, the turn program give the turn to
 * one with the more surplus.
 *
 * @constructor Creates a new general turn program with basic attributes accordingly to the start of the game.
 */
class Turns_program:

   /** A variable that has all the Unity that are in the turn program*/
   private val t_units: ListBuffer[Unity] = ListBuffer()

   /** A variable that has None if there is no Unity that has the turn or a
    *  Unity if it there is one that has the turn.
    */
   private var turn:  Option[Unity] = None


   /** A variable of a map, that has all the Unity with the action bar at and exact point
    * during the game.
    */
   private var during_program_map: mutable.Map[Unity, Double] = mutable.Map()

   /** A variable of a map, that ahs the maximum amount of action bar for each Unity.*/
   private val action_bars:  mutable.Map[Unity, Double] = mutable.Map()

   /** A variable of a sequence in order, with the remaining Unities that have to do their turn.*/
   private var sorted_Seq: Seq[(Unity, Double)] = Seq()

  
  
  
  
  
   /** Getter for the Unity that has the current turn.*/
   def get_turn: Option[Unity] = turn

   /** Getter for the list of unities that are in the turn program.*/
   def get_t_units_list: ListBuffer[Unity] = t_units

   /** Getter of the sequence ordered of the remaining unities that will have the turn.*/
   def get_sorted_Seq: Seq[(Unity, Double)] = sorted_Seq

   /** Getter for the map with the unities and its current action bar.*/
   def get_during_program_map: mutable.Map[Unity, Double] = during_program_map

   /** Getter for the map with the max amount of action bar for each Unity.*/
   def get_action_bars:  mutable.Map[Unity, Double] = action_bars


   def update_action_bar(x: Character): scala.Unit =
    if action_bars.contains(x) then action_bars(x) = max_action_bar(x)


  /** It adds a Character Unity to the turn program adding it to the list, and both maps.
   *
   * To the Character to the action_bar map, it uses the max_action_bar method
   *
   * @param x The Character that will be added
   * */
   def add_unit_to_program(x: Character): scala.Unit =
     if !t_units.contains(x) then
       t_units += x
       during_program_map(x) = 0
       action_bars(x) = max_action_bar(x)


   /** It adds an Enemy Unity to the turn program adding it to the list, and both maps.
    *
    * To the Enemy to the action_bar map, it uses the max_action_bar method
    *
    * @param x The Enemy that will be added
    * */
   def add_unit_to_program(x: Enemy): scala.Unit =
     if !t_units.contains(x) then
      t_units += x
      during_program_map(x)=  0
      action_bars(x) = max_action_bar(x)


  /** It removes a Unity from the turn program, removing it from the list and both maps.
   *
   * @param x The Unity that will be added
   * */
   def remove_unit_of_program(x: Unity): scala.Unit =
     if t_units.contains(x) then
       t_units -= x
       during_program_map.remove(x)
       action_bars.remove(x)
       sorted_Seq = sorted_Seq.filterNot(_._1 == x)
       if turn.contains(x) then turn = None


  /** It calculates the max amount of the action bar for the given Character.
   *
   * @param x The Character max amount action bar that will be calculated.
   *
   * @return The max amount of the action bar for the Character.
   * */
   private def max_action_bar(x: Character): Double =
    if x.get_weapon.isEmpty then
      x.get_weight
    else
      x.get_weight + 0.5 * x.get_weapon.get.weight


  /** It calculates the max amount of the action bar for the given Enemy.
   *
   * @param x The Enemy max amount action bar that will be calculated.
   *
   * @return The max amount of the action bar for the Enemy.
   * */
   private def max_action_bar(x: Enemy) : Double =
     x.get_weight.toDouble


  /** It adds the given amount to the map with the current amount of the action bar to all Unity
   * in the turn program. And checks if one has completed the action bar.
   *
   *
   * For the check, uses check_action_bar method with it, if one or more unities have completed their
   * action bar and only there, it calls create_seq_turns to create the sequence with the order
   * of the turns.
   *
   *
   * @param k The amount added.
   * */
   def add_action_bar(k:Double): scala.Unit=
    if k>=0 && sorted_Seq.isEmpty then
       for x <- t_units do
         during_program_map(x)= during_program_map(x) + k

       if check_action_bar then
         create_seq_turns()

  /** It gives True if at least one Unity has reached his max action bar amount, otherwise returns false.
   *
   * @return The boolean.
   * */
   def check_action_bar: Boolean =
     t_units.exists(x => during_program_map(x) >= action_bars(x))


  /** It calculates the surplus to compare which Unity will have its turn.
   *
   * @param x The Unity that we want to calculate its surplus.
   *
   * @return The surplus amount in positive for the Unity given.
   * */
   private def surplus(x:Unity):Double= during_program_map(x)-action_bars(x)

  /** It creates te sequence in order of decreased surplus of the unities that have completed
   * their action bar and saves it in the sorted_Seq variable.
   *
   * */
   private def create_seq_turns(): scala.Unit =
      for x <- t_units do
       if surplus(x) >= 0 then
         sorted_Seq = sorted_Seq :+ (x, surplus(x))
         during_program_map(x)=0

      sorted_Seq = sorted_Seq.sortBy(-_._2)

  /** If the sorted_Seq variable is not empty, it removes the first Unity and adds it to the
   * turn variable, otherwise does nothing.
   * */
   private def a_turn(): scala.Unit =
     if sorted_Seq.nonEmpty then
       turn = Some(sorted_Seq.head._1)
       sorted_Seq = sorted_Seq.tail

  /** While there in not a Unity that has reached his max action bar, it adds units to every action bar of the turn
   * program.
   * 
   * When someone has reached his max action bar, gets puts the next's turn Unity into the variable using a_turn method. 
   * 
   * @param k The amount added each while cycle to each action bar.
   * @return The Unity that has the turn.
   * */
   def start_next_turn(k: Double): Option[Unity] =
    if turn.isEmpty && k > 0 && t_units.nonEmpty then
      while sorted_Seq.isEmpty do
        add_action_bar(k)
      a_turn()
    turn

  /** For the moment only changes the turn variable*/
   def end_turn(): scala.Unit =
     turn = None













     



