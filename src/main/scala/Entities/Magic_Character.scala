package cl.uchile.dcc
package Entities
import Actions.*
import Action_Sistem.*
import Usable.Usable_Armament

/** A class representing a general magic type unit that can be used by a player.
 * .
 *It follows the base core attributes of the Character class an adds a variable for the mana bar, usable to cast
 * spells or use magic armaments
 *
 * @param n The name of the Magic Character.
 * @param h The health points of the Magic Character.
 * @param d The defense points of the Magic Character.
 * @param w The weight of the Magic Character.
 * @param we The slot for the equipped armament of the Magic Character.
 * @param m The mana bar of the Magic Character.
 * @constructor Creates a new general Magic Character with the specified basic attributes.
 */
class Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
  private var mana: Int = m

  /** Retrieves the current mana points of the Magic Character.
   *
   * @return The current mana points.
   */
  def get_mana: Int = mana

  /** Updates the mana points of the Magic Character.
   *
   * This method ensures that the mana points cannot drop below zero. If a negative
   * value is passed, the method does nothing.
   *
   * @param x The new mana points.
   */
  def set_mana(x:Int): scala.Unit =
    if x>=0 then
      mana=x

  override def Do(a: Thunder, t: Target): Boolean = false

  override def Do(a: Meteor, t: Target): Boolean = false

  override def Do(a: Heal, t: Target): Boolean = false

  override def Do(a: Purification, t: Target): Boolean = false