package cl.uchile.dcc
package controller

import Entities.*
import Panel.Panel

/** State of the GameController that represents everything before the game itself.*/
class Start_Phase extends Template_Phase:

  /** Second step for the double dispatch of the State pattern design for adding players: it calls the method design
   * to add a Player in the GameController.
   *
   * @param x The Player that will be added.
   * @param z the GameController that his method will be called.
   *
   * */ 
 override def add_players(x: Players, z: GameController): scala.Unit =
   z.add_players_to_game(x)


  /** Second step for the double dispatch of the State pattern design for adding enemies: it calls the method design
   * to add an Enemy in the GameController.
   *
   * @param x The Enemy that will be added.
   * @param z the GameController that his method will be called.
   * */
 override def add_enemies(x: Enemy, z: GameController): scala.Unit =
    z.add_enemy_to_game(x)

  /** Second step for the double dispatch of the State pattern design for adding a character 
   * into a player's Character list: it calls the method design to add the Character in the GameController.
   *
   * @param x The Player.
   * @param y The Character that will be added.
   * @param z the GameController that his method will be called.
   * */
 override def add_units_to_player(x: Players, y: Character, z: GameController): scala.Unit =
   z.add_unit_to_player_in_game(x,y)

  /** Second step for the double dispatch of the State pattern design for adding panels: it calls the method design
   * to add a Panel in the GameController.
   *
   * @param p The Panel that will be added.
   * @param z the GameController that his method will be called.
   * */
 override def add_panel(p: Panel, z: GameController): scala.Unit =
   z.add_panel_to_game(p)


  /** Second step for the double dispatch of the State pattern design for starting the game : it calls the method design
   * to start the game in the GameController.
   *
   * @param z the GameController that his method will be called.
   *
   * */
 override def Start_Game(z: GameController): scala.Unit =
    z.begin_game()

