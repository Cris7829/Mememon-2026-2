package cl.uchile.dcc
package Usable

import Entities.*

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
class Wand (n:String, a:Int, w:Int, o:Option[Character], m_a:Int ) extends Magic_Weapon(n, a, w, o ,m_a):

  /** Tells, through single dispatch, that a Wand can be equipped by an Archer.
   *
   * @param c The Archer that tries to equip the Wand.
   * @return true, because an Archer can equip a Wand.
   */
  override def can_be_equipped_by(c: Archer): Boolean = true

  /** Tells, through single dispatch, that a Wand can be equipped by a Black_Mage.
   *
   * @param c The Black_Mage that tries to equip the Wand.
   * @return true, because a Black_Mage can equip a Wand.
   */
  override def can_be_equipped_by(c: Black_Mage): Boolean = true


  /** Tells, through single dispatch, that a Wand can be equipped by a White_Mage.
   *
   * @param c The White_Mage that tries to equip the Wand.
   * @return true, because a White_Mage can equip a Wand.
   */
  override def can_be_equipped_by(c: White_Mage): Boolean = true