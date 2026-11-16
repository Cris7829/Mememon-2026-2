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
   
   /** Tells, through single dispatch, that a Dagger can be equipped by a Knight.
    *
    * @param c The Knight that tries to equip the Dagger.
    * @return true, because a Knight can equip a Dagger.
    */
   override def can_be_equipped_by(c: Knight): Boolean = true

   /** Tells, through single dispatch, that a Dagger can be equipped by a Thieve.
    *
    * @param c The Thieve that tries to equip the Dagger.
    * @return true, because a Thieve can equip a Dagger.
    */
   override def can_be_equipped_by(c: Thieve): Boolean = true


   /** Tells, through single dispatch, that a Dagger can be equipped by a Black_Mage.
    *
    * @param c The Black_Mage that tries to equip the Dagger.
    * @return true, because a Black_Mage can equip a Dagger.
    */
   override def can_be_equipped_by(c: Black_Mage): Boolean = true

