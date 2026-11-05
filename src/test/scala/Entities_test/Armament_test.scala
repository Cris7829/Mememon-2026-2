package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.*
import Usable.*

class Armament_test extends FunSuite:
  var steve: Character = new Character("Steve", 100, 10, 20, None)
  var cris: Character = new Character("Cris", 100, 10, 20, None)

  var sword: Sword = Sword("Royal Sword", 50, 10, Some(steve))
  var wand: Wand = Wand("Fire Wand", 30, 2, None, 80)
  var cane: Cane = Cane("Cas", 20, 5, Some(cris), 60)
  var dagger: Dagger = Dagger("Dark Dagger", 25, 1, None)
  var bow: Bow = Bow("Arco Largo", 40, 4, Some(cris))

  override def beforeEach(context: BeforeEach): scala.Unit =
    steve = new Character("Steve", 100, 10, 20, None)
    cris = new Character("Cris", 100, 10, 20, None)
    sword = Sword("Royal Sword", 50, 10, Some(steve))
    wand = Wand("Fire Wand", 30, 2, None, 80)

  test("The non-magic armament are created correctly"):
    assertEquals(sword.name, "Royal Sword")
    assertEquals(sword.get_attack_points, 50)
    assertEquals(sword.weight, 10)
    assertEquals(sword.get_owner, "Steve")

  test("The magic armament are created correctly"):
    assertEquals(wand.name, "Fire Wand")
    assertEquals(wand.get_attack_points, 30)
    assertEquals(wand.weight, 2)
    assertEquals(wand.get_magic_attack_points, 80)

  test("get_owner returns the name of the owner"):
    assertEquals(cane.get_owner, "Cris")
    assertEquals(bow.get_owner, "Cris")

  test("get_owner fails if the armament has no owner"):
    intercept[NoSuchElementException]:
      wand.get_owner

  test("set_attack_points ignore below 0"):
    sword.set_attack_points(-10)
    assertEquals(sword.get_attack_points, 50)

  test("set_magic_attack_points changes correctly and ignores below 0"):
    wand.set_magic_attack_points(100)
    assertEquals(wand.get_magic_attack_points, 100)

    wand.set_magic_attack_points(-20)
    assertEquals(wand.get_magic_attack_points, 100)


