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



  test("A player with no units is defeated after was_Defeated"):
    player.was_Defeated()
    assert(player.get_is_Defeated)

  test("Once defeated, a player stays defeated even if a living unit is added"):
    player.get_List_Units += deadKnight
    player.was_Defeated()
    player.get_List_Units += archer
    player.was_Defeated()
    assert(player.get_is_Defeated)


