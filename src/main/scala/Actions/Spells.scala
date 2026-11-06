package cl.uchile.dcc
package Actions

import Action_Sistem.{Source, Target}


abstract class Spells extends Non_Utility_Action:
  val name: String
  val cost: Int

  def Causes(s: Source, t: Target): Boolean 

