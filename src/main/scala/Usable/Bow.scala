package cl.uchile.dcc
package Usable
import Entities.*


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

  /** Tells, through single dispatch, that a Bow can be equipped by a Knight.
   *
   * @param c The Knight that tries to equip the Bow.
   * @return true, because a Knight can equip a Bow.
   */
  override def can_be_equipped_by(c: Knight): Boolean = true

  /** Tells, through single dispatch, that a Bow can be equipped by an Archer.
   *
   * @param c The Archer that tries to equip the Bow.
   * @return true, because an Archer can equip a Bow.
   */
  override def can_be_equipped_by(c: Archer): Boolean = true

  /** Tells, through single dispatch, that a Bow can be equipped by a White_Mage.
   *
   * @param c The White_Mage that tries to equip the Bow.
   * @return true, because a White_Mage can equip a Bow.
   */
  override def can_be_equipped_by(c: White_Mage): Boolean = true