package cl.uchile.dcc
package Usable
import Exceptions.*
import Entities.*

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}

/** An abstract base class representing a general physical armament in the game.
 *
 * This class defines the core attributes shared by all armaments, such as
 * damage, weight, name and owner
 *
 * @param n The name of the armament.
 * @param a The initial attack points of the armament.
 * @param w The weight of the armament.
 * @param o The initial owner that has armament in its inventory.
 *
 * @constructor Creates a new general armament with the specified basic attributes.
 */
abstract class Armament(n:String, a:Int, w:Int, o:Option[Character]) extends Usable_Armament:

  /** Any armament is defined as a non_magical weapon.
   *
   * @return false.
   */
  def is_magic: Boolean = false


  /** The immutable name of the armament. */
  val name: String = n

  /** The attack points of the armament */
  private var attack_points: Int = a

  /** The immutable weight of the armament. */
  val weight : Int = w

  /** The name of the owner of the armament. */
  private var owner : Option[Character] = o

  /** Retrieves the current attack points of the armament.
   *
   * @return The current attack points.
   */
  def get_attack_points: Int = attack_points

  /** Retrieves the current name of the owner's armament.
   *
   * @return The name of the owner.
   */
  def get_owner: String = owner.get.name

  /** Updates the attack points of the armament.
   *
   * This method ensures that the attack points cannot drop below zero. If a negative
   * value is passed, the method does nothing.
   *
   * @param x The new attack.
   */
  def set_attack_points(x:Int):scala.Unit =
    if x >= 0 then
      attack_points = x

  /** By default, an Armament doesn't have magic points.
   *
   * Usable to the actions methods, so the armament can't do any actions that requires use of magical points.
   *
   * @return 0
   * */
  def get_magic_attack_points: Int = 0


  /** By default, any armament can't be quipped by a Knight.
   *
   * Each weapon that extends Armament, overrides this method if that weapon can be equipped by a Knight.
   *
   * @param c A Knight.
   * @return false, because a Knight can't equip an Armament.
   * */
  def can_be_equipped_by(c: Knight): Boolean = false

  /** By default, any armament can't be quipped by an Archer.
   *
   * Each weapon that extends Armament, overrides this method if that weapon can be equipped by an Archer.
   *
   * @param c An Archer.
   * @return false, because an Archer can't equip an Armament.
   * */
  def can_be_equipped_by(c: Archer): Boolean = false

  /** By default, any armament can't be quipped by a Thieve.
   *
   * Each weapon that extends Armament, overrides this method if that weapon can be equipped by a Thieve.
   *
   * @param c A Thieve.
   * @return false, because a Thieve can't equip an Armament.
   * */
  def can_be_equipped_by(c: Thieve): Boolean = false

  /** By default, any armament can't be quipped by a Black_Mage.
   *
   * Each weapon that extends Armament, overrides this method if that weapon can be equipped by a Black_Mage.
   *
   * @param c A Black_Mage.
   * @return false, because a Black_Mage can't equip an Armament.
   * */
  def can_be_equipped_by(c: Black_Mage): Boolean = false

  /** By default, any armament can't be quipped by a White_Mage.
   *
   * Each weapon that extends Armament, overrides this method if that weapon can be equipped by a White_Mage.
   *
   * @param c A White_Mage.
   * @return false, because a White_Mage can't equip an Armament.
   * */
  def can_be_equipped_by(c: White_Mage): Boolean = false


  /** Setter for the owner of the Armament.
   *
   * @param c A Character
   * */
  def set_owner(c: Character): scala.Unit =
    owner = Some(c)


  /** By default, an Armament can't receive an attack made by a Character.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   * @param a Attack action.
   * @param attacker The Character source of the action.
   *
   * @return false.
   * */
  def receive(a: Attack, attacker: Character): Boolean = false

  /** By default, an Armament can't receive an attack made by an Enemy.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   *
   * @param a Attack action.
   * @param attacker The Enemy source of the action.
   *
   * @return false.
   * */
  def receive(a: Attack, attacker: Enemy): Boolean = false

  /** By default, an Armament can't be the Target of a Move action.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   * @param a The Move action.
   * @param u the Unity source of the action.
   *
   * @return false.
   * */
  def receive(a: Move, u: Unity): Boolean = false

  /** By default, an Armament can't be used as a potion by a Character.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   * @param a The Use_Potion action.
   * @param c the Character source of the action.
   *
   * @return false.
   * */
  def receive(a: Use_Potion, c: Character): Boolean = false

  /** By default, an Armament can't be used by a potion by a Magic Character.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   * @param a The Use_Potion action.
   * @param m The Magic_Character source of the action.
   *
   * @return false.
   * */
  def receive(a: Use_Potion, m: Magic_Character): Boolean = false



  /** By default, an Armament can't receive a Thunder spell thrown by a Black_Mage.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   * @param a The Thunder spell action.
   * @param m The Black_Mage source of the action.
   * @return false.
   * */
  def receive(a: Thunder, m: Black_Mage): Boolean = false



  /** By default, an Armament can't receive a Meteor spell thrown by a Black_Mage.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   * @param a The Meteor spell action.
   * @param m The Black_Mage source of the action.
   *
   * @return false.
   * */
  def receive(a: Meteor, m: Black_Mage): Boolean= false




  /** By default, an Armament can't receive a Heal spell thrown by a White_Mage.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   *
   * @param a The Meteor spell action.
   * @param m The White_Mage source of the action.
   *
   * @throws InvalidTargetException with the name of the Armament.
   * */
  def receive(a: Heal, m: White_Mage): Boolean=
    throw InvalidTargetException(s"$name cannot receive Heal, only Character can")





  /** By default, an Armament can't receive a Purification spell thrown by a White_Mage.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   * @param a The Purification spell action.
   * @param m The White_Mage source of the action.
   *
   *
   * @throws InvalidTargetException with the name of the Armament.
   * */
  def receive(a: Purification, m: White_Mage): Boolean=

    throw InvalidTargetException(s"$name cannot receive Purification, only enemies can")


  /** By default, an Armament can't be the target of the Equip_Weapon action by a Character.
   *
   * No subclass of Armament overrides this method, so none of them can receive this action either.
   *
   *
   * @param a The Equip_Weapon action.
   * @param c The Character source of the action.
   *
   * @return false
   * */
  def receive(a: Equip_Weapon, c: Character): Boolean = false

  /** Second step of the double dispatch to equip an armament.
   *
   *
   * Was initially called by the Knight itself to the action and now the Armament.
   *
   * If the Armament can be equipped by the Knight, it changes the owner of the armament it's equipped
   * by the Knight retiring it form the inventory and the old equipped weapon goes into the inventory.
   *
   * If the Armament can't be equipped, the InvalidWeaponException is thrown.
   *
   *
   * @param a The Equip_Weapon action.
   * @param c The Knight that tries to equip the armament.
   * @return true if the armament was equipped.
   * @throws InvalidWeaponException if a Knight cannot equip this armament.
   */
  def receive(a: Equip_Weapon, c: Knight): Boolean =
    equip_on(c, can_be_equipped_by(c))

  /** Second step of the double dispatch to equip an armament.
   *
   *
   * Was initially called by the Archer itself to the action and now the Armament.
   *
   * If the Armament can be equipped by the Archer, it changes the owner of the armament it's equipped
   * by the Archer retiring it form the inventory and the old equipped weapon goes into the inventory.
   *
   * If the Armament can't be equipped, the InvalidWeaponException is thrown.
   *
   * @param a The Equip_Weapon action.
   * @param c The Archer that tries to equip the armament.
   * @return true if the armament was equipped.
   * @throws InvalidWeaponException if an Archer cannot equip this armament.
   */
  def receive(a: Equip_Weapon, c: Archer): Boolean =
    equip_on(c, can_be_equipped_by(c))



  /** Second step of the double dispatch to equip an armament.
   *
   *
   * Was initially called by the Thieve itself to the action and now the Armament.
   *
   * If the Armament can be equipped by the Thieve, it changes the owner of the armament it's equipped
   * by the Thieve retiring it form the inventory and the old equipped weapon goes into the inventory.
   *
   * If the Armament can't be equipped, the InvalidWeaponException is thrown.
   *
   * @param a The Equip_Weapon action.
   * @param c The Thieve that tries to equip the armament.
   * @return true if the armament was equipped.
   * @throws InvalidWeaponException if a Thieve cannot equip this armament.
   */
  def receive(a: Equip_Weapon, c: Thieve): Boolean =
    equip_on(c, can_be_equipped_by(c))

  /** Second step of the double dispatch to equip an armament.
   *
   *
   * Was initially called by the Black_Mage itself to the action and now the Armament.
   *
   * If the Armament can be equipped by the Black_Mage, it changes the owner of the armament it's equipped
   * by the Black_Mage retiring it form the inventory and the old equipped weapon goes into the inventory.
   *
   * If the Armament can't be equipped, the InvalidWeaponException is thrown.
   *
   * @param a The Equip_Weapon action.
   * @param c The Black_Mage that tries to equip the armament.
   * @return true if the armament was equipped.
   * @throws InvalidWeaponException if a Black_Mage cannot equip this armament.
   */
  def receive(a: Equip_Weapon, c: Black_Mage): Boolean =
    equip_on(c, can_be_equipped_by(c))

  /** Second step of the double dispatch to equip an armament.
   *
   *
   * Was initially called by the White_Mage itself to the action and now the Armament.
   *
   * If the Armament can be equipped by the White_Mage, it changes the owner of the armament it's equipped
   * by the White_Mage retiring it form the inventory and the old equipped weapon goes into the inventory.
   *
   * If the Armament can't be equipped, the InvalidWeaponException is thrown.
   *
   * @param a The Equip_Weapon action.
   * @param c The White_Mage that tries to equip the armament.
   * @return true if the armament was equipped.
   * @throws InvalidWeaponException if a White_Mage cannot equip this armament.
   */
  def receive(a: Equip_Weapon, c: White_Mage): Boolean =
    equip_on(c, can_be_equipped_by(c))

  /** It makes the receive, equip weapon dispatch to work.
   *
   * It verifies that the Armament that called, can be equipped byh the given Character, if it does the method continue
   * normally, otherwise throws the InvalidWeaponException.
   *
   *
   * If the Armament can be equipped by the Character, it changes the owner of the armament it's equipped
   * by the Character retiring it form the inventory and the old equipped weapon goes into the inventory.
   *
   *
   * @param c The Equip_Weapon action.
   * @param allowed A boolean, that represents that the Armament can be equipped by the given Character.
   * @return true if the armament was equipped.
   * @throws InvalidWeaponException if a Character cannot equip this armament.
   */
  private def equip_on(c: Character, allowed: Boolean): Boolean =
    if !allowed then throw InvalidWeaponException(s"${c.name} cannot equip $name")
    else
      c.get_weapon.foreach(w => c.get_inventory += w)
      c.get_inventory -= this
      c.set_weapon(Some(this))
      set_owner(c)
      true




