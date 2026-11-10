package cl.uchile.dcc
package Actions

import Action_Sistem.{Source, Target}


abstract class Spells extends Non_Utility_Action:
  /** Variable with the name.*/
  val name: String
  /** Variable with the cost of the spell.*/
  val cost: Int

  /** Starts the double dispatch for any spell action: calls the Do method of the Source, so only a Source 
   * that can cast the spell does the action.
   *
   * @param s The source that casts the spell.
   * @param t The target that receives the spell.
   * @return true if the action was done successfully.
   */
  def Causes(s: Source, t: Target): Boolean 

