package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.{Archer, *}


class Unity_test extends FunSuite:

  var enemy: Enemy = Enemy("Goblin", 50, 5, 8, 15)
  var thieve: Thieve = Thieve("Dark Ares", 80, 10, 12)
  var knight: Knight = Knight("Arturo", 120, 25, 20)
  var archer: Archer = Archer("Robin", 90, 12, 14)
  var black_mage: Black_Mage = Black_Mage("Cris", 60, 5, 7, 100)
  var white_mage: White_Mage = White_Mage("Sky", 70, 7, 9, 110)

  override def beforeEach(context: BeforeEach): scala.Unit =
    enemy = Enemy("Goblin", 50, 5, 8, 15)
    thieve = Thieve("Dark Ares", 80, 10, 12)
    knight = Knight("Arturo", 120, 25, 20)
    archer = Archer("Robin", 90, 12, 14)
    black_mage = Black_Mage("Cris", 60, 5, 7, 100)
    white_mage = White_Mage("Sky", 70, 7, 9, 110)

  test("Enemy initialization and getters"):
    assertEquals(enemy.name, "Goblin")
    assertEquals(enemy.get_health, 50)
    assertEquals(enemy.get_defense, 5)
    assertEquals(enemy.get_weight, 8)
    assertEquals(enemy.get_attack, 15)

  test("Playable characters weapon and inventory creator"):
    assertEquals(thieve.get_weapon, None)
    assert(thieve.get_inventory.isEmpty)
    assertEquals(knight.get_weapon, None)
    assertEquals(archer.get_weapon, None)

  test("Magic characters mana and setters"):
    assertEquals(black_mage.get_mana, 100)
    assertEquals(white_mage.get_mana, 110)
    black_mage.set_mana(150)
    assertEquals(black_mage.get_mana, 150)
    black_mage.set_mana(-20)
    assertEquals(black_mage.get_mana, 150)

  test("Hierarchy checks"):
    assert(enemy.isInstanceOf[Unit])
    assert(thieve.isInstanceOf[Character])
    assert(black_mage.isInstanceOf[Magic_Character])
    assert(!thieve.isInstanceOf[Magic_Character])


