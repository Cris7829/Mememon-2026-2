package cl.uchile.dcc
package Panel_test

import Panel.*
import munit.FunSuite
import Entities.{Archer, *}
import Actions.*
import Exceptions.*
import Usable.*

class Panel_test extends FunSuite:
  var p1 = Panel(0, 0)
  var p2 = Panel(0, 1)
  var enemy = Enemy("Goblin", 50, 5, 8, 15)

  def bmage = Black_Mage("BM", 100, 5, 10, Some(Wand("W", 10, 2, None, 30)), 100)



  override def beforeEach(context: BeforeEach): scala.Unit =
    p1 = Panel(0, 0)
    p2 = Panel(0, 1)
    enemy = Enemy("Goblin", 50, 5, 8, 15)




  test("Panel coordinates created correctly"):
    assertEquals(p1.x, 0)
    assertEquals(p1.y, 0)

  test("Management of units list"):
    assert(p1.get_units.isEmpty)
    p1.add_units(enemy)
    p1.add_units(enemy)
    assertEquals(p1.get_units.length, 1)
    p1.remove_units(enemy)
    assert(p1.get_units.isEmpty)

  test("Management adjacent list"):
    assert(p1.get_adjacent.isEmpty)
    p1.add_adjacent(p2)
    p1.add_adjacent(p2)
    assertEquals(p1.get_adjacent.length, 1)
    p1.remove_adjacent(p2)
    assert(p1.get_units.isEmpty)


  test("Removing a unit or panel that is not in the list does nothing"):
    p1.add_units(enemy)
    p1.remove_units(Enemy("Orc", 50, 5, 8, 15))
    assertEquals(p1.get_units.length, 1)
    p1.add_adjacent(p2)
    p1.remove_adjacent(Panel(5, 5))
    assertEquals(p1.get_adjacent.length, 1)


  test("A unit moves to an adjacent panel"):
    p1.add_units(enemy)
    p2.add_adjacent(p1)
    assert(enemy.doAction(Move(), p2))
    assert(p2.get_units.contains(enemy))
    assert(!p1.get_units.contains(enemy))

  test("A unit cannot move to a panel that is not adjacent"):
    val far = Panel(5, 5)
    p1.add_units(enemy)
    intercept[InvalidMovementException](enemy.doAction(Move(), far))
    assert(p1.get_units.contains(enemy))
    assert(!far.get_units.contains(enemy))

  test("A unit that is not in any panel cannot move"):
    p2.add_adjacent(p1)
    intercept[InvalidMovementException](enemy.doAction(Move(), p2))

  test("A unit cannot move to the panel it is already in"):
    p1.add_units(enemy)
    intercept[InvalidMovementException](enemy.doAction(Move(), p1))



  test("Meteor damages every alive unit in the panel and costs 50 mana"):
    val m = bmage
    val e2 = Enemy("Orc", 50, 5, 8, 15)
    val k = Knight("K", 100, 5, 10)
    p1.add_units(enemy)
    p1.add_units(e2)
    p1.add_units(k)
    assert(m.doAction(Meteor(), p1))
    assertEquals(enemy.get_health, 20)
    assertEquals(e2.get_health, 20)
    assertEquals(k.get_health, 70)
    assertEquals(m.get_mana, 50)

  test("Meteor does not affect defeated units in the panel"):
    val m = bmage
    val dead = Enemy("Dead", 50, 5, 8, 15)
    dead.set_health(0)
    p1.add_units(enemy)
    p1.add_units(dead)
    m.doAction(Meteor(), p1)
    assertEquals(dead.get_health, 0)
    assertEquals(enemy.get_health, 20)

  test("Meteor with Magic Strength applies the bonus to every unit and then resets it"):
    val m = bmage
    val e2 = Enemy("Orc", 50, 5, 8, 15)
    p1.add_units(enemy)
    p1.add_units(e2)
    m.set_magic_multiplier(1.5)
    m.doAction(Meteor(), p1)
    assertEquals(enemy.get_health, 5)
    assertEquals(e2.get_health, 5)
    assertEquals(m.get_magic_multiplier, 1.0)

  test("Meteor on an empty panel throws and does not spend mana"):
    val m = bmage
    intercept[InvalidTargetException](m.doAction(Meteor(), p1))
    assertEquals(m.get_mana, 100)

  test("Meteor on a panel with only defeated units throws"):
    val m = bmage
    enemy.set_health(0)
    p1.add_units(enemy)
    intercept[InvalidTargetException](m.doAction(Meteor(), p1))

    assert(!p1.receive(Equip_Weapon(), White_Mage("WM", 100, 5, 10, 100)))
