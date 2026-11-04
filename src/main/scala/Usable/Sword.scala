package cl.uchile.dcc
package Usable

/** A non-magical sword designed for close combat.
 *
 * @param n The name of the sword.
 * @param a The attack points of the sword.
 * @param w The weight of the sword.
 * @param o The owner of the sword.
 *
 * @constructor Creates a new sword instance.
 */
class Sword(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)
