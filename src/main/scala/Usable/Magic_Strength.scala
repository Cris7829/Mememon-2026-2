package cl.uchile.dcc
package Usable
import Entities.*
/** A class representing a Magic_Strength type potion.
 *
 *  It follows the Potion abstract class with a name, usable to add more attack_points
 *  of an attack.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general Magic_Strength potion.
 */
class Magic_Strength (n:String) extends Potion(n):
  
  /** Applies the effect of the Magic_Strength potion to a magic character: set the magic multiplier to 1.5.
   *
   * @param m The magic character that consumes the potion.
   * @return true, because the potion is always applied to a magic character.
   */
    override def apply_to(m: Magic_Character): Boolean =
      m.set_magic_multiplier(1.5)
      true
