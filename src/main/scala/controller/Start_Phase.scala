package cl.uchile.dcc
package controller

import Entities.*
import Action_Sistem.*
import Actions.*

import Panel.Panel

class Start_Phase extends Template_Phase:


 override def add_players(x: Players, z: GameController): scala.Unit =
   z.add_players_to_game(x)



 override def add_enemies(x: Enemy, z: GameController): scala.Unit =
    z.add_enemy_to_game(x)


 override def add_units_to_player(x: Players, y: Character, z: GameController): scala.Unit =
   z.add_unit_to_player_in_game(x,y)


 override def add_panel(p: Panel, z: GameController): scala.Unit =
   z.add_panel_to_game(p)



 override def Start_Game(z: GameController): scala.Unit =
    z.begin_game()

