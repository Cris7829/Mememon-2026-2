package cl.uchile.dcc
package controller

import Entities.*
import Action_Sistem.*
import Actions.*

import Panel.Panel
import Exceptions.*

abstract class Template_Phase extends Game_Program_States:


 def add_players(x: Players,z:GameController): scala.Unit =
  throw NotMethodOfPhase("Players cannot be added in this phase of the game")


 def add_enemies(x: Enemy,z:GameController): scala.Unit =
  throw NotMethodOfPhase("Enemies cannot be added in this phase of the game")


 def do_action(u: Unity, action: Action, target: Target,z:GameController): Boolean=
  throw NotMethodOfPhase("Actions cannot be done in this phase of the game")


 def add_units_to_player(x: Players, y: Character,z:GameController): scala.Unit =
  throw NotMethodOfPhase("Characters cannot be added to players in this phase of the game")


 def add_panel(p: Panel,z:GameController): scala.Unit =
  throw NotMethodOfPhase("Panels cannot be added in this phase of the game")


 def Start_Game(z:GameController): scala.Unit =
  throw NotMethodOfPhase("The game cannot be started in this phase")



