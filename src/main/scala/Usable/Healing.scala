package cl.uchile.dcc
package Usable
import Entities.*
/** A class representing a Healing type potion.
 *
 *  It follows the Potion abstract class with a name, usable to regain health points
 *  of a Unit.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general Healing potion.
 */
class Healing (n:String) extends Potion(n):
  
  /** Applies the effect of the Magic_Strength potion to a magic character: restores 20% of the max health 
   * amount.
   *
   * @param c The Character that consumes the potion.
   * @return true, because the potion is always applied to a Character.
   */
    override def apply_to(c: Character): Boolean =
      c.set_health(math.min(c.get_health + c.max_health * 20/100, c.max_health))
      true

  /** Applies the effect of the Healing potion to a magic character as a Character.
   *
   * @param m The magic character that consumes the potion.
   * @return true, because the potion is always applied to a magic character.
   */
    override def apply_to(m: Magic_Character): Boolean = apply_to(m: Character)
