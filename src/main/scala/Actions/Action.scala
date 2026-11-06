package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}
import Action_Sistem.*


/** A trait representing any action that can do a Unit.*/
trait Action:
     val name: String
  
     def Causes(s: Source, t: Target): Boolean
  




