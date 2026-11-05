package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.*

class Player_test extends FunSuite:
  var player: Players = Players("Ash")

  var knight: Knight = Knight("Arthur", 150, 20, 40)
  var archer: Archer = Archer("Robin", 90, 8, 20)
  var blackMage: Black_Mage = Black_Mage("Dark", 70, 3, 14, 100)

  var deadKnight: Knight = Knight("Knight", 0, 20, 40)
  var deadThieve: Thieve = Thieve("Thieve", 0, 5, 12)
  var deadMage: Black_Mage = Black_Mage("Mage", 0, 3, 14, 100)

  override def beforeEach(context: BeforeEach): scala.Unit =
    player = Players("Ash")

    knight = Knight("Arthur", 150, 20, 40)
    archer = Archer("Robin", 90, 8, 20)
    blackMage = Black_Mage("Dark", 70, 3, 14, 100)

    deadKnight = Knight("Knight", 0, 20, 40)
    deadThieve = Thieve("Thieve", 0, 5, 12)
    deadMage = Black_Mage("Mage", 0, 3, 14, 100)


  test("Player initialization and name getter"):
    assertEquals(player.name, "Ash")

  test("Hierarchy checks"):
    assert(player.isInstanceOf[Player])
    assert(player.isInstanceOf[Players])

  test("A new player has an empty list of units"):
    assert(player.get_List_Units.isEmpty)

  test("A new player is not defeated"):
    assert(!player.get_is_Defeated)

  test("Two players have independent unit lists"):
    val other = Players("Gary")
    player.get_List_Units += knight
    assertEquals(player.get_List_Units.size, 1)
    assert(other.get_List_Units.isEmpty)
    assertEquals(other.name, "Gary")


  test("Units added to the list can be retrieved"):
    player.get_List_Units += knight
    player.get_List_Units += archer
    assertEquals(player.get_List_Units.size, 2)
    assert(player.get_List_Units.contains(knight))
    assert(player.get_List_Units.contains(archer))


  test("The list accepts different kinds of characters"):
    player.get_List_Units += knight
    player.get_List_Units += archer
    player.get_List_Units += blackMage
    assertEquals(player.get_List_Units.size, 3)


  test("was_Defeated does nothing if every unit is alive"):
    player.get_List_Units += knight
    player.get_List_Units += archer
    player.was_Defeated()
    assert(!player.get_is_Defeated)


  test("was_Defeated does nothing if at least one unit is still alive"):
    player.get_List_Units += deadKnight
    player.get_List_Units += archer
    player.get_List_Units += deadThieve
    player.was_Defeated()
    assert(!player.get_is_Defeated)


  test("was_Defeated sets isDefeated to true if the only unit has 0 health"):
    player.get_List_Units += deadKnight
    player.was_Defeated()
    assert(player.get_is_Defeated)

  test("was_Defeated sets isDefeated to true if all units have 0 health"):
    player.get_List_Units += deadKnight
    player.get_List_Units += deadThieve
    player.get_List_Units += deadMage
    player.was_Defeated()
    assert(player.get_is_Defeated)

  test("was_Defeated can be called several times without changing the result"):
    player.get_List_Units += knight
    player.was_Defeated()
    player.was_Defeated()
    assert(!player.get_is_Defeated)

    val other = Players("Gary")
    other.get_List_Units += deadKnight
    other.was_Defeated()
    other.was_Defeated()
    assert(other.get_is_Defeated)

  test("isDefeated is not updated until was_Defeated is called"):
    player.get_List_Units += deadKnight
    assert(!player.get_is_Defeated)
    player.was_Defeated()
    assert(player.get_is_Defeated)

  test("was_Defeated only affects the player it is called on"):
    val other = Players("Gary")
    player.get_List_Units += deadKnight
    other.get_List_Units += knight
    player.was_Defeated()
    other.was_Defeated()
    assert(player.get_is_Defeated)
    assert(!other.get_is_Defeated)



  test("A player with no alive units is defeated after was_Defeated"):
    player.was_Defeated()
    assert(player.get_is_Defeated)

  test("Once defeated, a player stays defeated even if a living unit is added"):
    player.get_List_Units += deadKnight
    player.was_Defeated()
    player.get_List_Units += archer
    player.was_Defeated()
    assert(player.get_is_Defeated)


  test("add_units adds a character to the list"):
    player.add_units(knight)
    assertEquals(player.get_List_Units.size, 1)
    assert(player.get_List_Units.contains(knight))

  test("add_units adds several different characters"):
    player.add_units(knight)
    player.add_units(archer)
    player.add_units(blackMage)
    assertEquals(player.get_List_Units.size, 3)
    assert(player.get_List_Units.contains(knight))
    assert(player.get_List_Units.contains(archer))
    assert(player.get_List_Units.contains(blackMage))

  test("add_units does nothing if the character is already in the list"):
    player.add_units(knight)
    player.add_units(knight)
    assertEquals(player.get_List_Units.size, 1)

  test("add_units doesn't accepts characters with 0 health"):
    player.add_units(deadKnight)
    assertEquals(player.get_List_Units.size, 0)
    assert(!player.get_List_Units.contains(deadKnight))

  test("Two different characters with the same stats are both added"):
    val knight2 = Knight("Arthur", 150, 20, 40)
    player.add_units(knight)
    player.add_units(knight2)
    assertEquals(player.get_List_Units.size, 2)


  test("A player with units added through add_units is defeated only when all of them have 0 health"):
    player.add_units(deadKnight)
    player.add_units(archer)
    player.was_Defeated()
    assert(!player.get_is_Defeated)

    val other = Players("Gary")
    other.add_units(deadKnight)
    other.add_units(deadThieve)
    other.was_Defeated()
    assert(other.get_is_Defeated)


