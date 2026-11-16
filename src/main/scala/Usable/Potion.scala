package cl.uchile.dcc
package Usable

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}
import Entities.*
import Exceptions.*

/** An abstract base class representing a general potion in the game.
 *
 * This class defines the core attributes shared by all potion, such as the name.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general potion with its name.
 */
abstract class Potion (n:String) extends Usable_Potion:

  /** The immutable name of the potion. */
  val name: String=n

  /** By default, a Potion can't receive an attack made by a Character.
   *
   * No subclass of Potion overrides this method, so none of them can receive this action either.
   *
   * @param a Attack action.
   * @param attacker The Character source of the action.
   *
   * @return false.
   * */
  def receive(a: Attack, attacker: Character): Boolean = false

  /** By default, a Potion can't receive an attack made by an Enemy.
   *
   * No subclass of Potion overrides this method, so none of them can receive this action either.
   *
   * @param a Attack action.
   * @param attacker The Enemy source of the action.
   * @return false.
   * */
  def receive(a: Attack, attacker: Enemy): Boolean = false

  /** By default, a Unity can't move to a Potion.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Move action.
   * @param u The Unity source of the action.
   * @return false.
   * */
  def receive(a: Move, u: Unity): Boolean = false

  /** By default, a Potion can't be equipped by a Character.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Equip_Weapon action.
   * @param c The Character source of the action.
   * @return false.
   * */
  def receive(a: Equip_Weapon, c: Character): Boolean = false


  /** By default, a Potion can't be the target of a Thunder cast made by a Black_Mage.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Thunder action.
   * @param m The Black_Mage source of the action.
   * @return false.
   * */
  def receive(a: Thunder, m: Black_Mage): Boolean =false


  /** By default, a Potion can't be the target of a Meteor cast made by a Black_Mage.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Meteor action.
   * @param m The Black_Mage source of the action.
   * @return false.
   * */
  def receive(a: Meteor, m: Black_Mage): Boolean =false


  /** By default, a Potion can't be the target of a Heal cast made by a White_Mage.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Heal action.
   * @param m The White_Mage source of the action.
   * @throws InvalidTargetException because only characters can receive and complete de action successfully.
   *
   * */
  def receive(a: Heal, m: White_Mage): Boolean =
    throw InvalidTargetException(s"$name cannot receive Heal, only characters can")


  /** By default, a Potion can't be the target of a Purification cast made by a White_Mage.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Purification action.
   * @param m The White_Mage source of the action.
   * @throws InvalidTargetException because only enemies can receive and complete de action successfully.
   *
   * */
  def receive(a: Purification, m: White_Mage): Boolean =
    throw InvalidTargetException(s"$name cannot receive Purification, only enemies can")

  /** By default, a Potion can't be equipped by a Knight.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Equip_Weapon action.
   * @param c The Knight source of the action.
   * @return false.
   *
   * */
  def receive(a: Equip_Weapon, c: Knight): Boolean = false

  /** By default, a Potion can't be equipped by an Archer.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Equip_Weapon action.
   * @param c The Archer source of the action.
   * @return false.
   *
   * */
  def receive(a: Equip_Weapon, c: Archer): Boolean = false

  /** By default, a Potion can't be equipped by a Thieve.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Equip_Weapon action.
   * @param c The Thieve source of the action.
   * @return false.
   *
   * */
  def receive(a: Equip_Weapon, c: Thieve): Boolean = false

  /** By default, a Potion can't be equipped by a Black_Mage.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Equip_Weapon action.
   * @param c The Black_Mage source of the action.
   * @return false.
   *
   * */
  def receive(a: Equip_Weapon, c: Black_Mage): Boolean = false

  /** By default, a Potion can't be equipped by a White_Mage.
   *
   * No subclass of Potion overrides this method, because none of them can't complete the action successfully.
   *
   * @param a Equip_Weapon action.
   * @param c The White_Mage source of the action.
   * @return false.
   *
   * */
  def receive(a: Equip_Weapon, c: White_Mage): Boolean = false


  /** By default, a Potion can't be consumed by a Character
   *
   * Each subclass of Potion overrides this method if it can be consumed by any character.
   *
   * @param c The Character that consumes the potion.
   * @return false.
   */
  def apply_to(c: Character): Boolean = false

  /** By default, a Potion can't be consumed by a Magic_Character
   *
   * Each subclass of Potion overrides this method if it can be consumed by a Magic_Character.
   *
   * @param m The Magic_Character that consumes the potion.
   * @return false.
   */
  def apply_to(m: Magic_Character): Boolean = false


  /** Second step of the double dispatch for Use_Potion: a Character consumes this potion.
   *
   * The potion applies its effect and, if it worked, it is removed from the inventory of the Character.
   *
   * @param a The Use_Potion action.
   * @param c The Character that consumes the Potion.
   * @return true if the potion was applied and consumed, false if it has no effect on the character.
   */
  def receive(a: Use_Potion, c: Character): Boolean = consume_on(c, apply_to(c))

  /** Second step of the double dispatch for Use_Potion: a Magic_Character consumes this potion.
   *
   * The potion applies its effect and, if it worked, it is removed from the inventory of the Magic_Character.
   *
   * @param a The Use_Potion action.
   * @param m The Magic_Character that consumes the Potion.
   * @return true if the potion was applied and consumed, false if it has no effect on the character.
   */
  def receive(a: Use_Potion, m: Magic_Character): Boolean = consume_on(m, apply_to(m))


  /** Removes the potion from the inventory of the character if its effect was applied.
   *
   * @param c The Character that consumed the Potion.
   * @param applied true if the effect was applied.
   * @return true if the Potion was consumed, false otherwise.
   */
  private def consume_on(c: Character, applied: Boolean): Boolean =
    if !applied then false
    else
      c.get_inventory -= this
      true
