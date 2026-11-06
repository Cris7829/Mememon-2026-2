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
  
  val max_mana: Int = m


  private var magic_multiplier: Double = 1.0

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
  

  
  def get_magic_multiplier: Double = magic_multiplier

 
  def set_magic_multiplier(x: Double): scala.Unit =
    if x >= 1.0 then magic_multiplier = x

  
  override def Do(a: Use_Potion, t: Target): Boolean =
    if get_inventory.contains(t) then t.receive(a, this)
    else false    
      
      
