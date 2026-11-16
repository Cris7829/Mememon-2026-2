package cl.uchile.dcc
package Usable

import Entities.*
import Actions.Equip_Weapon

import Entities.*

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
class Cane (n:String, a:Int, w:Int, o:Option[Character], m_a:Int ) extends Magic_Weapon(n, a, w, o ,m_a):

   /** Tells, through single dispatch, that a Cane can be equipped by a Black_Mage.
    *
    * @param c The Black_Mage that tries to equip the Cane.
    * @return true, because a Black_Mage can equip a Cane.
    */
   override def can_be_equipped_by(c: Black_Mage): Boolean = true


   /** Tells, through single dispatch, that a Cane can be equipped by a White_Mage.
    *
    * @param c The White_Mage that tries to equip the Cane.
    * @return true, because a White_Mage can equip a Cane.
    */
   override def can_be_equipped_by(c: White_Mage): Boolean = true

