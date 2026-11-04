package cl.uchile.dcc
package Usable

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
