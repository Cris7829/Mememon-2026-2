package cl.uchile.dcc
package Entities

import Usable.{Usable_Armament, Utilizable}

import scala.collection.mutable.ListBuffer


/** A class representing a general Unit that can be used by a player.
 * .
 *It follows the base core attributes of the Unit abstract class an adds a slot to equip an armament and
 * inventory for armaments and potions
 *
 * @param n The name of the Character.
 * @param h The health points of the Character.
 * @param d The defense points of the Character.
 * @param w The weight of the Character.
 * @param we The slot for the equipped armament of the Character.
 * @constructor Creates a new general Character with the specified basic attributes.
 */
class Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Unit(n:String, h:Int, d:Int, w:Int):
  /** A variable that can be scala.Unit or and actual armament if the Character has one equipped*/
  protected var weapon : Option[Usable_Armament] = we
  /** A variable that represents the inventory of utilizable.*/
  protected val inventory: ListBuffer[Utilizable] = ListBuffer()
  /** Retrieves the armament that its equipped.*/
  def get_weapon: Option[Usable_Armament] = weapon
  /** Retrieves the inventory.*/
  def get_inventory: ListBuffer[Utilizable] = inventory
