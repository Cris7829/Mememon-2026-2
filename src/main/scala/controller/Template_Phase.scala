package cl.uchile.dcc
package controller

import Entities.*
import Action_Sistem.*
import Actions.*

import Panel.Panel
import Exceptions.*

/** An abstract that it's the template for every state possible of the GameController.*/
abstract class Template_Phase extends Game_Program_States:

 /** By default, any Template_Phase state cannot add players.
  *
  *
  * Any subclass that could add player, overrides this method.
  *
  *
  * @param x The Player that is trying to be added.
  * @param z the GameController that his method will be called.
  * @throws NotMethodOfPhase because cannot add players.
  * */
 def add_players(x: Players,z:GameController): scala.Unit =
  throw NotMethodOfPhase("Players cannot be added in this phase of the game")

 /** By default, any Template_Phase state cannot add enemies.
  *
  *
  * Any subclass that could add enemies, overrides this method.
  *
  * @param x The Enemy trying to be added.
  * @param z the GameController that his method will be called.
  * @throws NotMethodOfPhase because cannot add enemies.
  * */
 def add_enemies(x: Enemy,z:GameController): scala.Unit =
  throw NotMethodOfPhase("Enemies cannot be added in this phase of the game")

 /** By default, any Template_Phase state cannot do actions.
  *
  *
  * Any subclass that could do actions, overrides this method.
  *
  * @param u The Unity trying to do an action.
  * @param action The action the Unity is trying to do.
  * @param target The target of the Action.
  * @param z the GameController that his method will be called.
  * @throws NotMethodOfPhase because cannot do actions.
  * */
 def do_action(u: Unity, action: Action, target: Target,z:GameController): Boolean=
  throw NotMethodOfPhase("Actions cannot be done in this phase of the game")

 /** By default, any Template_Phase state cannot add units to a Player.
  *
  *
  * Any subclass that could add units into a Player, overrides this method.
  *
  * @param x The Player.
  * @param y The Character that is trying to be added.
  * @param z the GameController that his method will be called.
  * @throws NotMethodOfPhase because cannot add units to a Player.
  * */
 def add_units_to_player(x: Players, y: Character,z:GameController): scala.Unit =
  throw NotMethodOfPhase("Characters cannot be added to players in this phase of the game")

 /** By default, any Template_Phase state cannot add panels.
  *
  *
  * Any subclass that could add panels, overrides this method.
  *
  * @param p The Panel that will be added.
  * @param z the GameController that his method will be called.
  * @throws NotMethodOfPhase because cannot add panels.
  * */
 def add_panel(p: Panel,z:GameController): scala.Unit =
  throw NotMethodOfPhase("Panels cannot be added in this phase of the game")

 /** By default, any Template_Phase state cannot start the game.
  *
  *
  * Any subclass that could start the game, overrides this method.
  *
  * @param z the GameController that his method will be called.
  * @throws NotMethodOfPhase because cannot start the game.
  *
  * */
 def Start_Game(z:GameController): scala.Unit =
  throw NotMethodOfPhase("The game cannot be started in this phase")



