package cl.uchile.dcc
package controller

import Action_Sistem.Target
import Actions.Action
import Entities.*
import Panel.Panel


/** A trait design for any state possible that could be the GameController on.*/
trait Game_Program_States:

  /** Second step for the double dispatch of the State pattern design for adding players: it calls the method design
   * to add a Player in the GameController.
   *
   * @param x The Player that will be added.
   * @param z the GameController that his method will be called.        
   * 
   * */
  def add_players(x: Players, z: GameController): scala.Unit

  /** Second step for the double dispatch of the State pattern design for adding enemies: it calls the method design
   * to add an Enemy in the GameController.
   *
   * @param x The Enemy that will be added.
   * @param z the GameController that his method will be called.
   * */
  def add_enemies(x: Enemy, z: GameController): scala.Unit

  /** Second step for the double dispatch of the State pattern design for doing an action: it calls the method design
   * to do an action in the GameController.
   * 
   * @param u The Unity trying to do an action.
   * @param action The action the Unity is trying to do.
   * @param target The target of the Action.
   * @param z the GameController that his method will be called.
   * @return true if the action was done successfully, otherwise false.         
   * */
  def do_action(u: Unity, action: Action, target: Target, z: GameController): Boolean

  /** Second step for the double dispatch of the State pattern design for adding a character 
   * into a player's Character list: it calls the method design to add the Character in the GameController.
   *
   * @param x The Player.
   * @param y The Character that will be added.
   * @param z the GameController that his method will be called. 
   * */
  def add_units_to_player(x: Players, y: Character, z: GameController): scala.Unit

  /** Second step for the double dispatch of the State pattern design for adding panels: it calls the method design
   * to add a Panel in the GameController.
   * 
   * 
   * @param p The Panel that will be added.
   * @param z the GameController that his method will be called.
   * */
  def add_panel(p: Panel, z: GameController): scala.Unit

  /** Second step for the double dispatch of the State pattern design for starting the game : it calls the method design
   * to start the game in the GameController.
   *
   * @param z the GameController that his method will be called. 
   * 
   * */
  def Start_Game(z: GameController): scala.Unit

