package cl.uchile.dcc
package Panel
import Exceptions.*
import Entities.{Black_Mage, Character, Enemy, Unity, White_Mage}
import Actions.*

import Action_Sistem.Target
import Entities.*
import scala.collection.mutable.ListBuffer
import Exceptions.*


/** A class representing each coordinate of the map.
 *
 * Each panel has its own x-axis and y-axis, a list with the Units that are in that panel, and a list
 * with the adjacent panels.
 *
 * @constructor Creates a new Panel with hte coordinates given and empty list of units and empty list of adjacent panels
 */
class Panel(a:Int , b:Int) extends Target:
  /** Variable that represents the x-axis coordinate of the Panel */
  val x :Int=a

  /** Variable that represents the y-axis coordinate of the Panel */
  val y :Int=b

  /** Variable that has a list of Unities, representing the ones that are in the Panel */
  private val units: ListBuffer[Unity] = ListBuffer()

  /** Variable that has a list of Panels, representing the ones that are adjacent to the Panel */
   val adjacent: ListBuffer[Panel] = ListBuffer()


  /** Retrieves the list of Units in the Panel.
   *
   * @return The current list of Units.
   */
  def get_units: ListBuffer[Unity] = units

  /** It adds a Unit to the list of the Panel, if its already there, the method does nothing.
   *
   * @param x The Unity that will be added
   * */
  def add_units(x:Unity): scala.Unit =
    if !units.contains(x) then
     units += x

  /** It removes a Unit from the list of the Panel, if the Unit isn't in the list, method dos nothing.
   *
   * @param x The Unity that will be removed.
   * */
  def remove_units(x:Unity): scala.Unit=
    if units.contains(x) then
      units -= x



  /** Retrieves the list of Adjacent Panels.
   *
   * @return The current list of adjacent.
   */
  def get_adjacent: ListBuffer[Panel] = adjacent


  /** It adds a Panel to the list of the Adjacent Panels, if its already in the list, the method does nothing.
   *
   * @param x The Unity that will be added
   * */
  def add_adjacent(x: Panel): scala.Unit =
    if !adjacent.contains(x) then
      adjacent += x


  /** It removes a Panel from the list of Adjacent Panels, if the Panel isn't in the list, method dos nothing.
   *
   * @param x The Panel that will be removed.
   * */
  def remove_adjacent(x: Panel): scala.Unit =
      if adjacent.contains(x) then
        adjacent -= x


  def receive(a:Meteor,m:Black_Mage): Boolean =
    if !get_units.exists(_.get_health > 0) then
      throw InvalidTargetException(s"Panel ($x, $y) has no alive units to receive Meteor")
    else
      m.set_mana(m.get_mana - 50)
      for x <- get_units if x.get_health > 0 do 
        x.set_health(x.get_health - (m.get_weapon.get.get_magic_attack_points*m.get_magic_multiplier).toInt)

      m.set_magic_multiplier(1.0)
      true


  def receive(a: Attack, attacker: Character): Boolean = false

  def receive(a: Attack, attacker: Enemy): Boolean =false



  def receive(a: Move, u: Unity): Boolean =
    get_adjacent.find(_.get_units.contains(u)) match
      case Some(p) =>
        p.remove_units(u)
        add_units(u)
        true
      case None =>
        throw InvalidMovementException(s"${u.name} cannot move to panel ($x, $y) because it is not adjacent to its current panel")






  def receive(a: Equip_Weapon, c: Character): Boolean =false

  def receive(a: Use_Potion, c: Character): Boolean =false


  def receive(a: Thunder, m: Black_Mage): Boolean = false


  def receive(a: Heal, m: White_Mage): Boolean =
    throw InvalidTargetException(s"($x, $y) cannot receive Heal, only characters can")


  def receive(a: Purification, m: White_Mage): Boolean =

    throw InvalidTargetException(s"($x, $y) cannot receive Purification, only enemies can")


  def receive(a: Equip_Weapon, c: Knight): Boolean = false

  def receive(a: Equip_Weapon, c: Archer): Boolean = false

  def receive(a: Equip_Weapon, c: Thieve): Boolean = false

  def receive(a: Equip_Weapon, c: Black_Mage): Boolean = false

  def receive(a: Equip_Weapon, c: White_Mage): Boolean = false


  def receive(a: Use_Potion, m: Magic_Character): Boolean = false








