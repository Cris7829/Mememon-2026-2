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

    override def apply_to(m: Magic_Character): Boolean =
      m.set_magic_multiplier(1.5)
      true
