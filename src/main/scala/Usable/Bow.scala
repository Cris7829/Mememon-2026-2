package cl.uchile.dcc
package Usable
import Entities.*

import Actions.*
/** A ranged, non-magical bow.
 *
 * @param n The name of the bow.
 * @param a The attack points of the bow.
 * @param w The weight of the bow.
 * @param o The owner of the bow.
 *
 * @constructor Creates a new Bow instance.
 */
class Bow(n:String, a:Int, w:Int, o:Option[Character]) extends Armament(n ,a ,w ,o):


  override def can_be_equipped_by(c: Knight): Boolean = true
  override def can_be_equipped_by(c: Archer): Boolean = true
  override def can_be_equipped_by(c: White_Mage): Boolean = true