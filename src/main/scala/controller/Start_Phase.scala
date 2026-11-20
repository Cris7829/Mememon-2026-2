package cl.uchile.dcc
package controller

import Entities.*
import Panel.Panel

/** State of the GameController that represents everything before the game itself.*/
class Start_Phase extends Template_Phase:

  /** Called by the GameController, which delegates to its current phase: it calls the method design
   * to add a Player in the GameController.
   *
   * @param x The Player that will be added.
   * @param z the GameController that his method will be called.
   *
   * */
 override def add_players(x: Players, z: GameController): scala.Unit =
   z.add_players_to_game(x)


  /** Called by the GameController, which delegates to its current phase: it calls the method design
   * to add an Enemy in the GameController.
   *
   * @param x The Enemy that will be added.
   * @param z the GameController that his method will be called.
   * */
 override def add_enemies(x: Enemy, z: GameController): scala.Unit =
    z.add_enemy_to_game(x)

  /** Called by the GameController, which delegates to its current phase:
   * it calls the method design to add the Character in the GameController.
   *
   * @param x The Player.
   * @param y The Character that will be added.
   * @param z the GameController that his method will be called.
   * */
 override def add_units_to_player(x: Players, y: Character, z: GameController): scala.Unit =
   z.add_unit_to_player_in_game(x,y)

  /** Called by the GameController, which delegates to its current phase: it calls the method design
   * to add a Panel in the GameController.
   *
   * @param p The Panel that will be added.
   * @param z the GameController that his method will be called.
   * */
 override def add_panel(p: Panel, z: GameController): scala.Unit =
   z.add_panel_to_game(p)


  /** Called by the GameController, which delegates to its current phase: it calls the method design
   * to start the game in the GameController.
   *
   * @param z the GameController that his method will be called.
   *
   * */
 override def Start_Game(z: GameController): scala.Unit =
    z.begin_game()

