package cl.uchile.dcc
package Game_Program_test

import munit.FunSuite
import controller.GameController
import Entities.*
import Usable.*
import Actions.*
import Panel.*


class Game_Program_test extends FunSuite:
  var controller: GameController = GameController()
  var player: Players = Players("Ash")

  var sword: Sword = Sword("Royal Sword", 50, 10, None)
  var knight: Knight = Knight("Arthur", 100, 5, 10, Some(sword))
  var archer: Archer = Archer("Robin", 90, 8, 20)
  var black_mage: Black_Mage = Black_Mage("Merlin", 70, 3, 14, 100)
  var enemy: Enemy = Enemy("Goblin", 100, 5, 20, 15)

  var p1: Panel = Panel(1, 1)
  var p2: Panel = Panel(1, 2)


  override def beforeEach(context: BeforeEach): scala.Unit =
    controller = GameController()
    player = Players("Ash")

    sword = Sword("Royal Sword", 50, 10, None)
    knight = Knight("Arthur", 100, 5, 10, Some(sword))
    archer = Archer("Robin", 90, 8, 20)
    black_mage = Black_Mage("Merlin", 70, 3, 14, 100)
    enemy = Enemy("Goblin", 100, 5, 20, 15)

    p1 = Panel(1, 1)
    p2 = Panel(1, 2)


  def start_basic_game(): scala.Unit =
    player.add_units(knight)
    controller.add_players(player)
    controller.add_enemies(enemy)
    controller.get_turns_program().start_next_turn(1.0)



  test("A new controller is empty"):
    assert(controller.get_players_list().isEmpty)
    assert(controller.get_enemies_list().isEmpty)
    assert(controller.get_owners.isEmpty)
    assert(controller.get_panels.isEmpty)
    assert(controller.get_turns_program().get_t_units_list.isEmpty)
    assertEquals(controller.get_last_error, None)

  test("A new controller has no turn and no next turn"):
    assertEquals(controller.get_who_turn, None)
    assertEquals(controller.get_who_next_turn, None)



  test("add_players adds the player and its units to the owners and the turn program"):
    player.add_units(knight)
    player.add_units(archer)
    controller.add_players(player)
    assertEquals(controller.get_players_list().size, 1)
    assertEquals(controller.get_owners(knight), player)
    assertEquals(controller.get_owners(archer), player)
    assert(controller.get_turns_program().get_t_units_list.contains(knight))
    assert(controller.get_turns_program().get_t_units_list.contains(archer))

  test("add_players does nothing if the player is already in the game"):
    controller.add_players(player)
    controller.add_players(player)
    assertEquals(controller.get_players_list().size, 1)

  test("add_units_to_player adds a character to a player that is in the game"):
    controller.add_players(player)
    controller.add_units_to_player(player, knight)
    assertEquals(controller.get_owners(knight), player)
    assert(player.get_List_Units.contains(knight))
    assert(controller.get_turns_program().get_t_units_list.contains(knight))

  test("add_units_to_player does nothing if the player is not in the game"):
    controller.add_units_to_player(player, knight)
    assert(!controller.get_owners.contains(knight))
    assert(!controller.get_turns_program().get_t_units_list.contains(knight))

  test("add_units_to_player does nothing if the character already has an owner"):
    val other = Players("Gary")
    controller.add_players(player)
    controller.add_players(other)
    controller.add_units_to_player(player, knight)
    controller.add_units_to_player(other, knight)
    assertEquals(controller.get_owners(knight), player)
    assert(!other.get_List_Units.contains(knight))

  test("add_enemies adds the enemy to the list and the turn program"):
    controller.add_enemies(enemy)
    assertEquals(controller.get_enemies_list().size, 1)
    assert(controller.get_turns_program().get_t_units_list.contains(enemy))

  test("add_enemies does nothing if the enemy is already in the game"):
    controller.add_enemies(enemy)
    controller.add_enemies(enemy)
    assertEquals(controller.get_enemies_list().size, 1)






  test("add_panel adds a panel only once"):
    controller.add_panel(p1)
    controller.add_panel(p1)
    controller.add_panel(p2)
    assertEquals(controller.get_panels.size, 2)

  test("get_panel_at returns the panel with those coordinates"):
    controller.add_panel(p1)
    controller.add_panel(p2)
    assertEquals(controller.get_panel_at(1, 2), Some(p2))

  test("get_panel_at returns None if there is no panel with those coordinates"):
    controller.add_panel(p1)
    assertEquals(controller.get_panel_at(9, 9), None)


  test("reset_controller_to_base empties players, enemies, owners and turn program"):
    start_basic_game()
    controller.reset_controller_to_base()
    assert(controller.get_players_list().isEmpty)
    assert(controller.get_enemies_list().isEmpty)
    assert(controller.get_owners.isEmpty)
    assert(controller.get_turns_program().get_t_units_list.isEmpty)
    assertEquals(controller.get_who_turn, None)



  test("get_actions returns the possible actions of a unit"):
    assertEquals(controller.get_actions(enemy).map(_.name), List("Attack", "Move"))
    assertEquals(controller.get_actions(knight).size, 4)
    assertEquals(controller.get_actions(black_mage).size, 6)

  test("The unit with the smallest action bar gets the first turn"):
    start_basic_game()
    assertEquals(controller.get_who_turn, Some(knight))


  test("A unit cannot act if it is not its turn"):
    start_basic_game()
    assert(!controller.do_action(enemy, Attack(), knight))
    assert(controller.get_last_error.get.contains("not the turn"))
    assertEquals(knight.get_health, 100)

  test("No unit can act before the game starts"):
    player.add_units(knight)
    controller.add_players(player)
    controller.add_enemies(enemy)
    assert(!controller.do_action(knight, Attack(), enemy))
    assert(controller.get_last_error.isDefined)

  test("If an action throws an exception, the controller catches it and the turn does not change"):
    start_basic_game()
    assert(!controller.do_action(knight, Move(), p2))
    assert(controller.get_last_error.get.contains("cannot move"))
    assertEquals(controller.get_who_turn, Some(knight))

  test("If an action returns false, the turn does not change"):
    start_basic_game()
    assert(!controller.do_action(knight, Attack(), archer))
    assertEquals(controller.get_who_turn, Some(knight))

  test("After a failed action, a successful one clears the last error"):
    start_basic_game()
    controller.do_action(knight, Move(), p2)
    assert(controller.get_last_error.isDefined)
    controller.do_action(knight, Attack(), enemy)
    assertEquals(controller.get_last_error, None)

  test("A defeated unit cannot act through the controller"):
    start_basic_game()
    knight.set_health(0)
    assert(!controller.do_action(knight, Attack(), enemy))
    assert(controller.get_last_error.get.contains("defeated"))

  test("A unit defeated by an action is removed from the turn program"):
    start_basic_game()
    enemy.set_health(40)
    assert(controller.do_action(knight, Attack(), enemy))
    assertEquals(enemy.get_health, 0)
    assert(!controller.get_turns_program().get_t_units_list.contains(enemy))
    assertEquals(controller.get_who_turn, Some(knight))

  test("remove_defeated_units only removes units with 0 health"):
    player.add_units(knight)
    controller.add_players(player)
    controller.add_enemies(enemy)
    enemy.set_health(0)
    controller.remove_defeated_units()
    assert(!controller.get_turns_program().get_t_units_list.contains(enemy))
    assert(controller.get_turns_program().get_t_units_list.contains(knight))




