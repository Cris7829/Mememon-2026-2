package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}
import Action_Sistem.*


/** A trait representing any action that can do a Unit.*/
trait Action:

     /** The name of the action.*/
     val name: String


     /** Starts the double dispatch for any action: calls the 
      * Do method of the Source, so only a Source that can use the potion, does the action.
      *
      * @param s The source that does the action.
      * @param t The target of the action.
      * @return true if the action was done successfully.
      */
     def Causes(s: Source, t: Target): Boolean
  




