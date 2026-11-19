package cl.uchile.dcc
package controller
import Combat_Sistem.*
import Entities.*

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import Panel.*
import Actions.Action
import Action_Sistem.Target
import Exceptions.*

import DesignPatterns.Observer

class Game_Controller_b extends Observer:

  
  private var players_list:ListBuffer[Players]= ListBuffer()


  private var enemies_list :ListBuffer[Enemy] = ListBuffer()
  
  private var turns_program: Turns_program = Turns_program()

  private var owners:mutable.Map[Character, Players] = mutable.Map()

  
  private var last_error: Option[String]= None

 
  private val panels:ListBuffer[Panel]= ListBuffer()


  def get_last_error: Option[String] = last_error

  
  def get_panels: List[Panel] = panels.toList

  
  def add_panel(p: Panel): scala.Unit =
    if who_won.isEmpty then
      if !panels.contains(p) then panels += p


  def get_panel_at(x: Int, y: Int): Option[Panel] =
    panels.find(p => p.x == x && p.y == y)






  
  def get_players_list(): ListBuffer[Players] = players_list

  
  def get_enemies_list(): ListBuffer[Enemy] = enemies_list

 
  def get_turns_program(): Turns_program = turns_program

  
  def get_owners: Map[Character, Players] = owners.toMap




  
  def reset_controller_to_base() : scala.Unit =
    players_list = ListBuffer()
    enemies_list = ListBuffer()
    turns_program = Turns_program()
    owners = mutable.Map()
    panels.clear()
    who_won = None
    last_error = None


  
  def get_who_turn: Option[Unity] =
    if turns_program.get_turn.isDefined then
      Some(turns_program.get_turn.get)
    else None


  
  def get_who_next_turn: Option[Unity] =
    if turns_program.get_sorted_Seq.nonEmpty then
      Some(turns_program.get_sorted_Seq.head._1)
    else None

  
  def add_players(x: Players): scala.Unit =
    if who_won.isEmpty then
      if !players_list.contains(x) then
        players_list += x
        for c <- x.get_List_Units do
          add_units_to_player(x, c)

  
  def add_units_to_player(x: Players, y: Character): scala.Unit =
    if who_won.isEmpty then
      if players_list.contains(x) && !owners.contains(y) then
        owners(y) = x
        x.add_units(y)
        turns_program.add_unit_to_program(y)
        y.set_observer(Some(this))

  
  def add_enemies(x: Enemy): scala.Unit =
    if who_won.isEmpty then
      if !enemies_list.contains(x) then
        enemies_list += x
        turns_program.add_unit_to_program(x)
        x.set_observer(Some(this))

  
  
  def do_action(u: Unity, action: Action, target: Target): Boolean =
    verify_end()
    if who_won.isDefined then false
    else
      val momentary_k: Double = 1.0
      try
        if !turns_program.get_turn.contains(u) then
          throw NotYourTurnException(s"It is not the turn of ${u.name}")
        val ok = u.doAction(action, target)
        if ok then
          turns_program.end_turn()
          turns_program.start_next_turn(momentary_k)
        last_error = None
        ok
      catch
        case e: GameException =>
          last_error = Some(e.getMessage)
          println(s"Action failed: ${e.getMessage}")
          false

  
  def get_actions(u: Unity): List[Action] = u.actions_possible.toList


  
  private def remove_enemy(x:Enemy): scala.Unit =

    if who_won.isEmpty then
      if enemies_list.contains(x) then
        enemies_list-=x

  
  private def remove_players_Character(x: Character): scala.Unit =
    if who_won.isEmpty then
      if owners.contains(x) then
        owners(x).remove_unit(x)
        owners.remove(x)

  private def remove_Unit_panel(x:Unit):scala.Unit =
    if who_won.isEmpty then
      for c<-get_panels do
        if c.get_units.contains(x) then
          c.remove_units(x)


  
  def actualize_death(x: Character): scala.Unit =
    turns_program.remove_unit_of_program(x)
    remove_Unit_panel(x)
    remove_players_Character(x)
    verify_end()

  
  def actualize_death(x: Enemy): scala.Unit =
    turns_program.remove_unit_of_program(x)
    remove_Unit_panel(x)
    remove_enemy(x)
    verify_end()


  
  private var who_won:Option[Boolean] = None

  
  def get_who_won:Option[Boolean] = who_won

 
  private def verify_end(): scala.Unit =
    if who_won.isEmpty then
      if enemies_list.isEmpty then
        who_won = Some(true)
      else if players_list.forall(_.get_List_Units.isEmpty) then
        who_won = Some(false)