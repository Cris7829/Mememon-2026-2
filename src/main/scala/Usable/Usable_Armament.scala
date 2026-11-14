package cl.uchile.dcc
package Usable

import Entities.*

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}

/** A  trait representing any armament that can be equipped and utilized by a Unit. */
trait Usable_Armament extends Utilizable:

  /** By default, defines if the Usable_Armament it's or not magic.*/
  def is_magic: Boolean

  /** Value for the name of the Usable_Armament.*/
  val name: String

  /** Value for the name of the Usable_Armament.*/
  val weight: Int

  /** Getter for the owner of the Usable_Armament.*/
  def get_owner: String

  /** Retrieves the current attack points of the armament.
   *
   * @return The current attack points.
   */
  def get_attack_points: Int
  def get_magic_attack_points: Int
  def set_attack_points(x:Int):scala.Unit


  /** Receives an attack from a Character.
   *
   * @param a        The Attack action.
   * @param attacker The Character that attacks.
   * @return true if the target received the attack.
   */
  def receive(a: Attack, attacker: Character): Boolean

  /** Receives an attack from an Enemy.
   *
   * @param a        The Attack action.
   * @param attacker The Enemy that attacks.
   * @return true if the target received the attack.
   */
  def receive(a: Attack, attacker: Enemy): Boolean

  /** Receives a Unity that wants to move to this target.
   *
   * @param a The Move action.
   * @param u The Unity that wants to move.
   * @return true if the Unity moved.
   */
  def receive(a: Move, u: Unity): Boolean

  /** Receives the action of being equipped by a Character.
   *
   * @param a The Equip_Weapon action.
   * @param c The Character that tries to equip the target.
   * @return true if the target was equipped.
   */
  def receive(a: Equip_Weapon, c: Character): Boolean

  /** Receives the action of being consumed as a potion by a Character.
   *
   * @param a The Use_Potion action.
   * @param c The Character that tries to consume the target.
   * @return true if the target was consumed and applied its effect.
   */
  def receive(a: Use_Potion, c: Character): Boolean

  /** Receives Thunder cast by a Black_Mage.
   *
   * @param a The Thunder action.
   * @param m The Black_Mage  that casts Thunder.
   * @return true if the target received the spell.
   */
  def receive(a: Thunder, m: Black_Mage): Boolean

  /** Receives Meteor cast by a Black_Mage .
   *
   * @param a The Meteor action
   * @param m The Black_Mage  that casts Meteor.
   * @return true if the target received the spell.
   */
  def receive(a: Meteor, m: Black_Mage): Boolean

  /** Receives Heal cast by a Black Mage.
   *
   * @param a The Heal action.
   * @param m The White_Mage  that casts Heal.
   * @return true if the target received the spell.
   */
  def receive(a: Heal, m: White_Mage): Boolean

  /** Receives Purification cast by a White_Mage .
   *
   * @param a The Meteor action.
   * @param m The White_Mage  that casts Meteor.
   * @return true if the target received the spell.
   */
  def receive(a: Purification, m: White_Mage): Boolean


  /** It shows if the Usable_Armament can or can't be equipped by a Knight.
   *
   * @param c A Knight.
   * @return false if Usable_Armament can't be equipped, otherwise true.
   * */
  def can_be_equipped_by(c: Knight): Boolean

  /** It shows if the Usable_Armament can or can't be equipped by a Archer.
   *
   * @param c An Archer.
   * @return false if Usable_Armament can't be equipped, otherwise true.
   * */
  def can_be_equipped_by(c: Archer): Boolean

  /** It shows if the Usable_Armament can or can't be equipped by a Thieve.
   *
   * @param c A Thieve.
   * @return false if Usable_Armament can't be equipped, otherwise true.
   * */
  def can_be_equipped_by(c: Thieve): Boolean

  /** It shows if the Usable_Armament can or can't be equipped by a Black_Mage.
   *
   * @param c A Black_Mage.
   * @return false if Usable_Armament can't be equipped, otherwise true.
   * */
  def can_be_equipped_by(c: Black_Mage): Boolean

  /** It shows if the Usable_Armament can or can't be equipped by a White_Mage.
   *
   * @param c A White_Mage.
   * @return false if Usable_Armament can't be equipped, otherwise true.
   * */
  def can_be_equipped_by(c: White_Mage): Boolean


  /** It changes the owner parameter of the Usable_Armament.
   *
   * @param c A Character that will be the new owner.
   * */
  def set_owner(c: Character): scala.Unit

