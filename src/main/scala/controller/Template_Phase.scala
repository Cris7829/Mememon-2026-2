package cl.uchile.dcc
package controller

import Entities.*
import Action_Sistem.*
import Actions.*

import Panel.Panel

abstract class Template_Phase extends Game_Program_States:


 def add_players(x: Players): scala.Unit = ()
   

 def add_enemies(x: Enemy): scala.Unit = ()


 def do_action(u: Unity, action: Action, target: Target): Boolean = false
 

 def add_units_to_player(x: Players, y: Character): scala.Unit = ()


 def add_panel(p: Panel): scala.Unit = ()


 def Start_Game(): scala.Unit = ()



