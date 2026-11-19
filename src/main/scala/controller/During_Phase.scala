package cl.uchile.dcc
package controller

import Entities.*
import Action_Sistem.*
import Actions.*

/** State of the GameController that represents the actions phase.*/
class During_Phase extends  Template_Phase:
  /** Second step for the double dispatch of the State pattern design for doing an action: it calls the method design
   * to do an action in the GameController.
   *
   * @param u      The Unity trying to do an action.
   * @param action The action the Unity is trying to do.
   * @param target The target of the Action.
   * @param z      the GameController that his method will be called.
   * @return true if the action was done successfully, otherwise false.
   * */
  override def do_action(u: Unity, action: Action, target: Target,z:GameController): Boolean = 
    z.execute_action(u: Unity, action: Action, target: Target)
