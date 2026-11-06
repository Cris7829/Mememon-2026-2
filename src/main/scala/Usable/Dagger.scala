package cl.uchile.dcc
package Usable
import Entities.*
/** A lightweight, non-magical dagger.
 *
 * @param n The name of the dagger.
 * @param a The attack points of the dagger.
 * @param w The weight of the dagger.
 * @param o The owner of the dagger.
 *
 * @constructor Creates a new Dagger instance.
 */
class Dagger(n:String, a:Int, w:Int, o:Option[Character]) extends Armament(n ,a ,w ,o):

   override def can_be_equipped_by(c: Knight): Boolean = true
   override def can_be_equipped_by(c: Thieve): Boolean = true
   override def can_be_equipped_by(c: Black_Mage): Boolean = true

