package cl.uchile.dcc
package controller

import Action_Sistem.Target
import Actions.Action
import Entities.{Character, Enemy, Players, Unity}
import Panel.Panel

trait Game_Program_States:

  def add_players(x: Players): scala.Unit


  def add_enemies(x: Enemy): scala.Unit


  def do_action(u: Unity, action: Action, target: Target): Boolean


  def add_units_to_player(x: Players, y: Character): scala.Unit


  def add_panel(p: Panel): scala.Unit


  def Start_Game(): scala.Unit
