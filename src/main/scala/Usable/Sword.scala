package cl.uchile.dcc
package Usable



import Entities.*

/** A non-magical sword designed for close combat.
 *
 * @param n The name of the sword.
 * @param a The attack points of the sword.
 * @param w The weight of the sword.
 * @param o The owner of the sword.
 *
 * @constructor Creates a new sword instance.
 */
class Sword(n:String, a:Int, w:Int, o:Option[Character]) extends Armament(n ,a ,w ,o):

   override def can_be_equipped_by(c: Knight): Boolean = true
   override def can_be_equipped_by(c: Archer): Boolean = true
   override def can_be_equipped_by(c: Thieve): Boolean = true