package cl.uchile.dcc
package controller

import Entities.*
import Action_Sistem.*
import Actions.*

import Panel.Panel

abstract class Template_Phase extends Game_Program_States:


 def add_players(x: Players,z:GameController): scala.Unit = ()
   

 def add_enemies(x: Enemy,z:GameController): scala.Unit = ()


 def do_action(u: Unity, action: Action, target: Target,z:GameController): Boolean = false
 

 def add_units_to_player(x: Players, y: Character,z:GameController): scala.Unit = ()


 def add_panel(p: Panel,z:GameController): scala.Unit = ()


 def Start_Game(z:GameController): scala.Unit = ()



