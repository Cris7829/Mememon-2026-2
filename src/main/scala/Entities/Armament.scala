package cl.uchile.dcc
package Entities

/** A  trait representing any armament that can be equipped and utilized by a Unit. */
trait Usable_Armament

/** An abstract base class representing a general physical armament in the game.
 *
 * This class defines the core attributes shared by all armaments, such as
 * damage, weight, name and owner
 *
 * @param n The name of the armament.
 * @param a The initial attack points of the armament.
 * @param w The weight of the armament.
 * @param o The initial owner that has armament in its inventory.
 *
 * @constructor Creates a new general armament with the specified basic attributes.
 */
abstract class Armament(n:String, a:Int, w:Int, o:String) extends Usable_Armament:

 /** The immutable name of the armament. */
 val name: String = n

 /** The attack points of the armament */
 private var attack_points: Int = a

 /** The immutable weight of the armament. */
 val weight : Int = w

 /** The name of the owner of the armament. */
 private var owner : String = o


 /** Retrieves the current attack points of the armament.
  *
  * @return The current attack points.
  */
 def get_attack_points: Int = attack_points

 /** Retrieves the current owner of the armament.
  *
  * @return The name of the owner.
  */
 def get_owner: String = owner

 /** Updates the attack points of the armament.
  *
  * This method ensures that the attack points cannot drop below zero. If a negative
  * value is passed, the method does nothing.
  *
  * @param x The new attack.
  */
 def set_attack_points(x: Int): scala.Unit =
   if x >= 0 then
     attack_points = x

 /** Assigns a new owner to the armament.
  *
  * @param x The name of the new owner.
  */
 def set_owner(x: String): scala.Unit=
   owner = x



/** An abstract base class representing a general magic weapons.
 *
 * This class defines the core attributes shared by all magical armament, it's use the base armament abstract class
 * and adds the magical points attribute.
 *
 * @param n The name of the magic weapon.
 * @param a The initial attack points of the magic weapon.
 * @param w The weight of the magic weapon.
 * @param o The initial owner that has magic weapon in its inventory.
 * @param m_a The magic attack points of the magic weapon.
 *
 * @constructor Creates a new general armament with the specified basic attributes.
 */
abstract class Magic_Weapon(n: String, a: Int, w: Int, o: String, m_a: Int) extends Armament(n, a, w, o):

  /** The magical attack point of the magic weapon. */
  private var magic_attack_points: Int = m_a

  /** Retrieves the magic points of the weapon. */
  def get_magic_attack_points: Int = magic_attack_points

  /** Updates the magic attack points of the magic weapon.
   *
   * This method ensures that the magic attack points cannot drop below zero. If a negative
   * value is passed, the method does nothing.
   *
   * @param x The new magic attack points.
   */
  def set_magic_attack_points(x: Int): scala.Unit =
    if x >= 0 then
      magic_attack_points = x



/** A magical Cane designed for a magical use.
 *
 * @param n The name of the Cane.
 * @param a The attack points of the Cane.
 * @param w The weight of the Cane.
 * @param o The owner of the Cane.
 * @param m_a The magic attack points of the Cane.
 *
 * @constructor Creates a new Bow instance.
 */
class Cane (n:String, a:Int, w:Int, o:String, m_a:Int ) extends Magic_Weapon(n, a, w, o ,m_a)



/** A magical wand designed for mages or magical Units.
 *
 * @param n The name of the wand.
 * @param a The attack points of the wand.
 * @param w The weight of the wand.
 * @param o The owner of the wand.
 * @param m_a The magic attack points of the wand.
 *
 * @constructor Creates a new Bow instance.
 */
class Wand (n:String, a:Int, w:Int, o:String, m_a:Int ) extends Magic_Weapon(n, a, w, o ,m_a)


/** A non-magical sword designed for close combat.
 *
 * @param n The name of the sword.
 * @param a The attack points of the sword.
 * @param w The weight of the sword.
 * @param o The owner of the sword.
 *
 * @constructor Creates a new sword instance.
 */
class Sword(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)


/** A lightweight, non-magical dagger.
 *
 * @param n The name of the dagger.
 * @param a The attack points of the dagger.
 * @param w The weight of the dagger.
 * @param o The owner of the dagger.
 *
 * @constructor Creates a new Dagger instance.
 */
class Dagger(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)




/** A ranged, non-magical bow.
 *
 * @param n The name of the bow.
 * @param a The attack points of the bow.
 * @param w The weight of the bow.
 * @param o The owner of the bow.
 *
 * @constructor Creates a new Bow instance.
 */
class Bow(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)



