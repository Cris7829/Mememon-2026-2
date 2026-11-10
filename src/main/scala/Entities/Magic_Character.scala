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
  /** Value for the mana amount of the magic character's mana points that was created with.*/   
  val max_mana: Int = m

  /** Variable of the magic multiplier that affect the attack of the magic character, by default 1.0.*/
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


  /** Getter for the magic multiplier.*/
  def get_magic_multiplier: Double = magic_multiplier

  /** Setter of the magic multiplier.
   * 
   * If the amount given is below 1.0, the method does nothing.
   * 
   * @param x the amount set.
   * */
  def set_magic_multiplier(x: Double): scala.Unit =
    if x >= 1.0 then magic_multiplier = x

  /** First step of the double dispatch for Use_Potion on a magic character.
   *
   * It overrides the Character version so the potion receives this character as a
   * Magic_Character. That way, magic potions such as Mana and Magic_Strength can
   * apply their effect.
   *
   * @param a The Use_Potion action.
   * @param t The potion to consume.
   * @return true if the potion was consumed, false if it is not in the inventory
   *         or it has no effect on this character.
   */
  override def Do(a: Use_Potion, t: Target): Boolean =
    if get_inventory.contains(t) then t.receive(a, this)
    else false    
      
      
