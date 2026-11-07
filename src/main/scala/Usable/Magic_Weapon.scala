package cl.uchile.dcc
package Usable

import Entities.*

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
abstract class Magic_Weapon(n: String, a: Int, w: Int, o:Option[Character], m_a: Int) extends Armament(n, a, w, o):

  /** The magical attack point of the magic weapon. */
  private var magic_attack_points: Int = m_a

  /** Retrieves the magic points of the weapon. */
  override def get_magic_attack_points: Int = magic_attack_points

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

  override def is_magic: Boolean = true
