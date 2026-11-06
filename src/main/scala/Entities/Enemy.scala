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

  
  override def Do(a: Attack, t: Target): Boolean =
     t.receive(a, this)

  override def receive(a: Attack, attacker: Character): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
      val attack: Int = attacker.get_weapon.get.get_attack_points - get_defense
      if attack >= 0 then
        set_health(get_health - attack)
      true
  
  override def receive(a: Thunder, m: Black_Mage): Boolean =
    if get_health <= 0 then throw DefeatedUnitException(s"$name is defeated and cannot receive actions")
    else
     m.set_mana(m.get_mana - 20)
     if m.get_weapon.get.get_magic_attack_points * m.get_magic_multiplier.toInt < get_health then
       set_health(get_health - (m.get_weapon.get.get_magic_attack_points * m.get_magic_multiplier).toInt)
     else set_health(0)
     m.set_magic_multiplier(1.0)
     true
  
  
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
     