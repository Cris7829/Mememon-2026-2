package cl.uchile.dcc
package controller

import Entities.*
import Action_Sistem.*
import Actions.*

class During_Phase extends  Template_Phase:

  override def do_action(u: Unity, action: Action, target: Target,z:GameController): Boolean = 
    z.execute_action(u: Unity, action: Action, target: Target)
