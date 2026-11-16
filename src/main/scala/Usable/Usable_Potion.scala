package cl.uchile.dcc
package Usable

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}
import Entities.*


/** A  trait representing any potion that can be used by a Unit */
trait Usable_Potion extends Utilizable:


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


  /** Receives the action of being consumed as a potion by a Magic_Character.
   *
   * @param a The Use_Potion action.
   * @param m The Magic_Character that tries to consume the Usable_Potion
   * @return true if the target was consumed and applied its effect.
   */
  def receive(a: Use_Potion, m: Magic_Character): Boolean


  /** Applies the effect of the Usable_Potion to a magic character.
   *
   * @param c The magic character that consumes the potion.
   * @return true, because the potion is always applied.
   */
  def apply_to(c: Character): Boolean


  /** Applies the effect of the Usable_Potion to a magic character.
   *
   * @param m The magic character that consumes the potion.
   * @return true, because the potion is always applied to a magic character.
   */
  def apply_to(m: Magic_Character): Boolean

