package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}

import Action_Sistem.{Source, Target}

import scala.collection.mutable.ListBuffer

/** A class representing the action of moving.
 *
 *
 * @constructor Creates a new moving action with its name.
 */
class Move extends Non_Utility_Action :

  /** Variable with the name.*/
  override val name: String = "Move"

  /** Starts the double dispatch for Move: calls the Do method of the source for Move,
   * so only a source that can equip the weapon.
   *
   * @param s The source that moves.
   * @param t The target that receives Move.
   * @return true if the Unit moved successfully.
   */
  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
