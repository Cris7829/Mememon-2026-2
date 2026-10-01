package cl.uchile.dcc
package Entities

/** A  trait representing any potion that can be used by a Unit */
trait Usable_Potion

/** An abstract base class representing a general potion in the game.
 *
 * This class defines the core attributes shared by all potion, such as the name.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general potion with its name.
 */
abstract class Potion (n:String) extends Usable_Potion:

  /** The immutable name of the potion. */
  val name: String=n



/** A class representing a Healing type potion.
 *
 *  It follows the Potion abstract class with a name, usable to regain health points
 *  of a Unit.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general Healing potion.
 */
class Healing (n:String) extends Potion(n)


/** A class representing a Mana type potion.
 *
 *  It follows the Potion abstract class with a name, usable to regain mana
 *  points for a Unit.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general Mana potion.
 */
class Mana (n:String) extends Potion(n)


/** A class representing a Magic_Strength type potion.
 *
 *  It follows the Potion abstract class with a name, usable to add more attack_points
 *  of an attack.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general Magic_Strength potion.
 */
class Magic_Strength (n:String) extends Potion(n)


/** A class representing a Magic potion type potion.
 *
 *  It follows the Potion abstract class with a name, usable to add more magic_attack_points
 *  of an attack.
 *
 * @param n The name of the potion.
 * @constructor Creates a new general Healing potion.
 */
class Strength_Player (n:String) extends Potion(n)