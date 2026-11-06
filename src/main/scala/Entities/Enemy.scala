package cl.uchile.dcc
package Entities

import Action_Sistem.Target
import Actions.Attack

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
     t.receive(a, this) && t.get_health>0