package cl.uchile.dcc
package Panel

import Entities.Unity
import scala.collection.mutable.ListBuffer

/** A class representing each coordinate of the map.
 *
 * Each panel has its own x-axis and y-axis, a list with the Units that are in that panel, and a list
 * with the adjacent panels.
 *
 * @constructor Creates a new Panel with hte coordinates given and empty list of units and empty list of adjacent panels
 */
class Panel(a:Int , b:Int) :
  /** Variable that represents the x-axis coordinate of the Panel */
  val x :Int=a

  /** Variable that represents the y-axis coordinate of the Panel */
  val y :Int=b

  /** Variable that has a list of Unities, representing the ones that are in the Panel */
  private val units: ListBuffer[Unity] = ListBuffer()

  /** Variable that has a list of Panels, representing the ones that are adjacent to the Panel */
  private val adjacent: ListBuffer[Panel] = ListBuffer()


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
