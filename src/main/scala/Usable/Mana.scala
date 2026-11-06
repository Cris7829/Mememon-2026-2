package cl.uchile.dcc
package Usable
import Entities.*
/** A class representing a Mana type potion.
 *
 *  It follows the Potion abstract class with a name, usable to regain mana
 *  points for a Unit.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general Mana potion.
 */


class Mana (n:String) extends Potion(n):
  override def apply_to(m: Magic_Character): Boolean =
    m.set_mana(math.min(m.get_mana + m.max_mana * 3/ 10, m.max_mana))
    true
