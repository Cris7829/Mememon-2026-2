package cl.uchile.dcc
package Usable

import Action_Sistem.Target

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}
import Entities.{Black_Mage, Character, Enemy, Unity, White_Mage}


/** A trait that represents al the usable objects that a player's Character can use, such as potions and armaments*/
trait Utilizable extends Target:
  
  /** Receives an attack from a Character.
   *
   * @param a The Attack action.
   * @param attacker The Character that attacks.
   * @return true if the target received the attack.
   */
  def receive(a: Attack, attacker: Character): Boolean

  /** Receives an attack from an Enemy.
   *
   * @param a The Attack action.
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