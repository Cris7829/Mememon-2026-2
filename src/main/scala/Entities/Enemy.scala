package cl.uchile.dcc
package Entities
import Exceptions.*
import Action_Sistem.Target
import Actions.*

/** A class representing a general Unit that can do damage to the player's Unit.
 * .
 *It follows the base core attributes of the Unit abstract class an adds an attack points, because they don't
 * use armament of potions, they just do damage.
 *
 * @param n The name of the Enemies.
 * @param h The health points of the Enemy.
 * @param d The defense points of the Enemy.
 * @param w The weight of the Enemies .
 * @param a The attack points of the Enemy .
 * @constructor Creates a new general Enemy Unit with the specified basic attributes.
 */
class Enemy(n:String, h:Int, d:Int, w:Int, a:Int) extends Unit(n:String, h:Int, d:Int, w:Int):
  /** A private variable with the attack points of the Enemy.*/
  private var base_attack:Int = a

  /** Retrieves the attack points of the Enemy.*/
  def get_attack: Int = base_attack

  /** First step of the double dispatch for an Attack: the Enemy attacks the target
   * with its own attack points.
   *
   * @param a The Attack action.
   * @param t The target of the attack.
   * @return true if the target received the attack.
   */
  override def Do(a: Attack, t: Target): Boolean =
     t.receive(a, this)

  /** Receives an attack from a character.
   *
   * The damage is the attack points of the character's weapon minus the defense of the Enemy.
   *
   * @param a The Attack action.
   * @param attacker The Character that attacks.
   * @return true if the Enemy received the attack.
   * @throws DefeatedUnitException if the Enemy is already defeated.
   */
  override def receive(a: Attack, attacker: Character): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
      val attack: Int = attacker.get_weapon.get.get_attack_points - get_defense
      if attack >= 0 then
        set_health(get_health - attack)
      true

  /** Receives Thunder cast by a Black Mage.
   *
   * The Enemy loses health equal to the magic damage of the Black Mage, that is, the magic
   * attack points of its weapon multiplied by its magic multiplier. The health cannot go below 0.
   * 
   * 
   * The spell costs 20 mana and the magic multiplier goes back to 1.0 after casting it.
   *
   * @param a The Thunder action.
   * @param m The Black Mage that casts Thunder.
   * @return true if the Enemy received the spell.
   * @throws DefeatedUnitException if the Enemy is already defeated.
   */
  override def receive(a: Thunder, m: Black_Mage): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
     m.set_mana(m.get_mana - 20)
     if m.get_weapon.get.get_magic_attack_points * m.get_magic_multiplier.toInt < get_health then
       set_health(get_health - (m.get_weapon.get.get_magic_attack_points * m.get_magic_multiplier).toInt)
     else set_health(0)
     m.set_magic_multiplier(1.0)
     true

  
  /** Receives Purification cast by a White Mage.
   *
   * The Enemy loses 15% of its current health plus half of the magic damage of the White Mage,
   * that is, the magic attack points of its weapon multiplied by its magic multiplier.
   * 
   * 
   * 
   * The spell costs 25 mana and the magic multiplier goes back to 1.0 after casting it.
   *
   * @param a The Purification action.
   * @param m The White Mage that casts Purification.
   * @return true if the Enemy received the spell.
   * @throws DefeatedUnitException if the Enemy is already defeated.
   */
  override def receive(a: Purification, m: White_Mage): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
     m.set_mana(m.get_mana - 25)
     val during:Int = (get_health.toDouble * 0.85).toInt - (m.get_weapon.get.get_magic_attack_points* m.get_magic_multiplier).toInt/ 2
     if during > 0 then
      set_health(during)
     else set_health(0)
     m.set_magic_multiplier(1.0)
     true
     