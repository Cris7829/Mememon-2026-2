package cl.uchile.dcc
package Usable
import Entities.*
/** A class representing a Magic potion type potion.
 *
 *  It follows the Potion abstract class with a name, usable to add more magic_attack_points
 *  of an attack.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general Healing potion.
 */

class Strength_Player (n:String) extends Potion(n):
  override def apply_to(c: Character): Boolean =
    c.set_defense((c.get_defense * 1.15).toInt)
    true
    
  override def apply_to(m: Magic_Character): Boolean = apply_to(m: Character)