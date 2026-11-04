package cl.uchile.dcc
package Usable

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
