package cl.uchile.dcc
package Entities

import Usable.Usable_Armament

/** A class representing a Black Mage, a type of Magic Character.
 * .
 *It follows the base core attributes of the Magic_Character class.
 *
 * @param n The name of the Black Mage.
 * @param h The health points of the Black Mage.
 * @param d The defense points of the Black Mage.
 * @param w The weight of the Black Mage.
 * @param we The slot for the equipped armament of the Black Mage.
 * @param m The mana bar of the Black Mage.
 * @constructor Creates a new general Black Mage with the specified basic attributes and its name.
 */
class Black_Mage (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int):
  /** Auxiliary constructor to create a Black Mage without an initial equipped armament
   *
   * @param n The name of the Black Mage.
   * @param h The health points of the Black Mage.
   * @param d The defense points of the Black Mage.
   * @param w The weight of the Black Mage.
   * @param m The mana bar of the Black Mage.
   */
  def this(n: String, h: Int, d: Int, w: Int, m:Int) =
    this(n, h, d, w, Option.empty[Usable_Armament],m)
