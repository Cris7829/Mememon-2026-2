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
  override val name: String = "Move"


  def Causes(s: Source, t: Target): Boolean = s.Do(this, t)
