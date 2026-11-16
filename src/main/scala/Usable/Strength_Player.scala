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


  /** Applies the effect of the Strength_Player potion to a magic character: multiplies the defense points
   * of the Character in 1.15.
   *
   * @param c The Character that consumes the potion.
   * @return true, because the potion is always applied to a Character.
   */
  override def apply_to(c: Character): Boolean =
    c.set_defense((c.get_defense * 1.15).toInt)
    true

  /** Applies the effect of the Strength_Player potion to a magic character as a Character.
   *
   * @param m The magic character that consumes the potion.
   * @return true, because the potion is always applied to a magic character.
   */
  override def apply_to(m: Magic_Character): Boolean = apply_to(m: Character)