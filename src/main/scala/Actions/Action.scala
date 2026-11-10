package cl.uchile.dcc
package Actions

import Usable.{Usable_Armament, Usable_Potion}
import Action_Sistem.*


/** A trait representing any action that can do a Unit.*/
trait Action:

     /** The name of the action.*/
     val name: String


     /** First step of the double dispatch: defines the interaction between the source and the target.
      *
      * Each concrete action calls the Do method of the source that matches its own type,
      * so the source knows which action has to do without checking types.
      *
      * The method itself, does not verify that follows the game's rules. But then the Source and target do.
      *
      * @param s The source that does the action.
      * @param t The target that receives the action.
      * @return true if the action was successful.
      */
     def Causes(s: Source, t: Target): Boolean
  




