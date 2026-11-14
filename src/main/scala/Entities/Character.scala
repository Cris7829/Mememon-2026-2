package cl.uchile.dcc
package Entities
import Exceptions.*
import Usable.{Usable_Armament, Utilizable}


import scala.collection.mutable.ListBuffer

import Action_Sistem.*
import Actions.*

/** A class representing a general Unit that can be used by a player.
 * .
 *It follows the base core attributes of the Unit abstract class an adds a slot to equip an armament and
 * inventory for armaments and potions
 *
 * @param n The name of the Character.
 * @param h The health points of the Character.
 * @param d The defense points of the Character.
 * @param w The weight of the Character.
 * @param we The slot for the equipped armament of the Character.
 * @constructor Creates a new general Character with the specified basic attributes.
 */
class Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Unit(n:String, h:Int, d:Int, w:Int):
  /** A variable that can be scala.Unit or and actual armament if the Character has one equipped*/
  protected var weapon : Option[Usable_Armament] = we
  /** A variable that represents the inventory of utilizable.*/
  protected val inventory: ListBuffer[Utilizable] = ListBuffer()
  /** Retrieves the armament that its equipped.*/
  def get_weapon: Option[Usable_Armament] = weapon
  /** Retrieves the inventory.*/
  def get_inventory: ListBuffer[Utilizable] = inventory
  
  actions_possible ++= List(Equip_Weapon(), Use_Potion())

  /** Value that represents the max health of a Character, associated with the initial health points.*/
  val max_health: Int = h

  /** Setter for the defense of the Character, if d, it's below or equal to cero, the method does nothing,
   * otherwise it changes the defense points of a Character.
   *
   * @param d the defense.
   * .*/
  def set_defense(d:Int):scala.Unit =
    if d>0 then defense=d

  /** Setter for the equip weapon of a Character.
   *
   * It only works if its given an option that is defined with a Usable Armament in it.
   *
   * @param x The Usable Armament into an Option format
    * */
  def set_weapon(x:Option[Usable_Armament]):scala.Unit=
    if x.isDefined then  weapon = x

  /** First step of the double dispatch for an Attack: the Character attacks the target
   * with its equipped weapon.
   *
   * @param a The Attack action.
   * @param t The target of the attack.
   * @return true if the target received the attack.
   * @throws InvalidWeaponException if the Character does not have a weapon equipped.
   */
  override def Do(a: Attack, t: Target): Boolean =
    if weapon.isDefined then t.receive(a, this)
    else
      throw InvalidWeaponException(s"$name does not have a weapon equipped")

  /** First step of the double dispatch for Use_Potion: the Character consumes a potion
   * from its inventory.
   *
   * @param a The Use_Potion action.
   * @param t The potion to consume.
   * @return true if the potion was consumed, false if it is not in the inventory
   *         or it has no effect on this Character.
   */
  override def Do(a: Use_Potion, t: Target): Boolean =
      if get_inventory.contains(t) then t.receive(a, this)
      else false


  /** By default, any Character can't be attacked by a Character.
   *
   *
   * @param a Attack action.
   * @param attacker Character that tries do the action.
   * @return false, because a Character can't be attacked by a Character.
   * */
  override def receive(a: Attack, attacker: Character): Boolean = false


  /** Receives an attack from an enemy.
   *
   * The damage is the attack points of the enemy minus the defense of the Character.
   *
   * @param a The Attack action.
   * @param attacker The Enemy that attacks.
   * @return true if the Character received the attack.
   * @throws DefeatedUnitException if the Character is already defeated.
   */
  override def receive(a: Attack, attacker: Enemy): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
      val attack: Int = attacker.get_attack - get_defense
      if attack >= 0 then
        set_health(get_health - attack)
      true

  /** By default, any Character can't be the target of a Move action.
   *
   * No subclass overrides the method, because each of them cannot be the target of the action either.
   *
   * @param a The Move action.
   * @param u Unity that tries to move to the Character.
   *
   * @return false, because a Character can't be the target.
   * */
  override def receive(a: Move, u:Unity): Boolean = false

  /** By default, any Character can't be equipped.
   *
   * No subclass overrides the method, because each of them cannot be the target of the action either.
   *
   * @param a The Equip_Weapon action.
   * @param c Character that tries to equip the Character.
   * @return false, because a Character can't be equipped.
   * */
  override def receive(a: Equip_Weapon, c: Character): Boolean = false

  /** By default, any Character can't be used as a Potion.
   *
   * No subclass overrides the method, because each of them cannot be the target of the action either.
   *
   * @param a The Use_Potion action.
   * @param c Character that tries do the action.
   * @return false, because a Character can't be used as a Potion.
   * */
  override def receive(a: Use_Potion, c: Character): Boolean = false


  /** By default, any Character can't be the target of a Thunder spell made from a Black_Mage.
   *
   * No subclass overrides the method, because each of them cannot be the target of the action either.
   *
   * @param a The Thunder action.
   * @param m Black_Mage that tries do the action.
   * @return false, because a Character can't be the target.
   * */
  override def receive(a: Thunder, m: Black_Mage): Boolean = false


  /** By default, any Character can't be the target of a Meteor spell made from a Black_Mage.
   *
   * No subclass overrides the method, because each of them cannot be the target of the action either.
   *
   * @param a The Meteor action.
   * @param m Black_Mage that tries do the action.
   * @return false, because a Character can't be the target.
   * */
  override def receive(a: Meteor, m: Black_Mage): Boolean = false


  /** Receives Heal cast by a White Mage.
   *
   * The Character recovers 20% of its max health plus a quarter of the magic damage of the
   * White Mage, that is, the magic attack points of its weapon multiplied by its magic multiplier.
   *
   * The spell costs 15 mana and the magic multiplier goes back to 1.0 after casting it.
   *
   * @param a The Heal action.
   * @param m The White Mage that casts Heal.
   * @return true if the Character was healed.
   * @throws DefeatedUnitException if the Character is already defeated.
   */
  override def receive(a: Heal, m: White_Mage): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
      m.set_mana(m.get_mana - 15)
      set_health(get_health + max_health * 2/10 + (m.get_weapon.get.get_magic_attack_points * m.get_magic_multiplier).toInt / 4)
      m.set_magic_multiplier(1.0)
      true
    


