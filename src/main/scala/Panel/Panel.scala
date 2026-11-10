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

  /** Receives Meteor cast by a Black Mage: an area attack that damages every alive unit in the panel.
   *
   * The magic damage is the magic attack points of the Black Mage's weapon multiplied by its
   * magic multiplier. Every alive unit in the panel, including allies, loses that amount of health.
   * The spell costs 50 mana and the magic multiplier goes back to 1.0 after casting it.
   *
   * @param a The Meteor action.
   * @param m The Black Mage that casts Meteor.
   * @return true if the spell was cast.
   * @throws InvalidTargetException if there are no alive units in the panel.
   */
  def receive(a:Meteor,m:Black_Mage): Boolean =
    if !get_units.exists(_.get_health > 0) then
      throw InvalidTargetException(s"Panel ($x, $y) has no alive units to receive Meteor")
    else
      m.set_mana(m.get_mana - 50)
      for x <- get_units if x.get_health > 0 do 
        x.set_health(x.get_health - (m.get_weapon.get.get_magic_attack_points*m.get_magic_multiplier).toInt)

      m.set_magic_multiplier(1.0)
      true

  /** By default, a Panel can't be attacked by a Character.
   *
   * @param a The Attack action.
   * @param attacker The Character that tries to attack the Panel.
   * @return false, because a Panel can't be attacked.
   */
  def receive(a: Attack, attacker: Character): Boolean = false

  /** By default, a Panel can't be attacked by a Enemy.
   *
   * @param a The Attack action.
   * @param attacker The Enemy that tries to attack the Panel.
   * @return false, because a Panel can't be attacked.
   */
  def receive(a: Attack, attacker: Enemy): Boolean =false


  /** Receives a unit that wants to move to this panel.
   *
   * The unit must be in one of the adjacent panels. If it is, the unit is removed
   * from its current panel and added to this one.
   *
   * @param a The Move action.
   * @param u The unit that wants to move.
   * @return true if the unit was moved.
   * @throws InvalidMovementException if the unit is not in a panel adjacent to this one.
   */
  def receive(a: Move, u: Unity): Boolean =
    get_adjacent.find(_.get_units.contains(u)) match
      case Some(p) =>
        p.remove_units(u)
        add_units(u)
        true
      case None =>
        throw InvalidMovementException(s"${u.name} cannot move to panel ($x, $y) because it is not adjacent to its current panel")


  /** By default, a Panel can't be equipped by a Character.
   *
   * @param a The Equip_Weapon action.
   * @param c The Character that tries to equip the Panel.
   * @return false, because a Panel can't be equipped.
   */
  def receive(a: Equip_Weapon, c: Character): Boolean =false

  /** By default, a Panel can't be used as a potion by a Character.
   *
   * @param a The Use_Potion action.
   * @param c The Character that tries to equip the Panel.
   * @return false, because a Panel can't be used as a potion.
   */
  def receive(a: Use_Potion, c: Character): Boolean = false

  /** By default, a Panel can't be the target of a Thunder spell made by a Black_Mage.
   *
   * @param a The Thunder action.
   * @param m The Black_Mage that cast .
   * @return false, because a Panel can't be the target.
   */
  def receive(a: Thunder, m: Black_Mage): Boolean = false

  /** By default, a Panel can't be the target of a Heal spell made by a White_Mage.
   *
   * @param a The Heal action.
   * @param m The White_Mage that cast.
   * @throws InvalidTargetException with the coordinates of the panel.
   */
  def receive(a: Heal, m: White_Mage): Boolean =
    throw InvalidTargetException(s"($x, $y) cannot receive Heal, only characters can")

  /** By default, a Panel can't be the target of a Purification spell made by a White_Mage.
   *
   * @param a The Purification action.
   * @param m The White_Mage that cast.
   * @throws InvalidTargetException with the coordinates of the panel.
   */
  def receive(a: Purification, m: White_Mage): Boolean =

    throw InvalidTargetException(s"($x, $y) cannot receive Purification, only enemies can")

  /** By default, a Panel can't be equipped by a Knight.
   *
   * @param a The Equip_Weapon action.
   * @param c The Knight trying to equip the Panel.
   * @return false, because Panel can't be equipped.
   */
  def receive(a: Equip_Weapon, c: Knight): Boolean = false

  /** By default, a Panel can't be equipped by an Archer.
   *
   * @param a The Equip_Weapon action.
   * @param c The Archer trying to equip the Panel.
   * @return false, because Panel can't be equipped.
   */
  def receive(a: Equip_Weapon, c: Archer): Boolean = false

  /** By default, a Panel can't be equipped by a Thieve.
   *
   * @param a The Equip_Weapon action.
   * @param c The Thieve trying to equip the Panel.
   * @return false, because Panel can't be equipped.
   */
  def receive(a: Equip_Weapon, c: Thieve): Boolean = false

  /** By default, a Panel can't be equipped by a Black_Mage.
   *
   * @param a The Equip_Weapon action.
   * @param c The Black_Mage trying to equip the Panel.
   * @return false, because Panel can't be equipped.
   */
  def receive(a: Equip_Weapon, c: Black_Mage): Boolean = false

  /** By default, a Panel can't be equipped by a White_Mage.
   *
   * @param a The Equip_Weapon action.
   * @param c The White_Mage trying to equip the Panel.
   * @return false, because Panel can't be equipped.
   */
  def receive(a: Equip_Weapon, c: White_Mage): Boolean = false

  /** By default, a Panel can't be used as a potion by a Magic_Character.
   *
   * @param a The Use_Potion action.
   * @param m The Magic_Character trying to use the Panel.
   * @return false, because Panel can't be used a potion.
   */
  def receive(a: Use_Potion, m: Magic_Character): Boolean = false








