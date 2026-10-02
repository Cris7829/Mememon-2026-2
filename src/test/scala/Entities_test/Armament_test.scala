package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.*




class Armament_test extends FunSuite:
  var sword: Sword = Sword("Royal Sword", 50, 10, "Steve")
  var wand: Wand = Wand("Fire Wand", 30, 2, "Without Owner", 80)
  var cane: Cane = Cane("Cas", 20, 5, "Mage", 60)
  var dagger: Dagger = Dagger("Dark Dagger", 25, 1, "The Killer")
  var bow: Bow = Bow("Arco Largo", 40, 4, "Cris")

  override def beforeEach(context: BeforeEach): scala.Unit =
    sword = Sword("Royal Sword", 50, 10, "Steve")
    wand = Wand("Fire Wand", 30, 2, "Without Owner", 80)


  test("The non-magic armament are created correctly"):
    assertEquals(sword.name, "Royal Sword")
    assertEquals(sword.get_attack_points, 50)
    assertEquals(sword.weight, 10)
    assertEquals(sword.get_owner, "Steve")

  test("The magic armament are created correctly"):
    assertEquals(wand.name, "Fire Wand")
    assertEquals(wand.get_attack_points, 30)
    assertEquals(wand.weight, 2)
    assertEquals(wand.get_owner, "Without Owner")
    assertEquals(wand.get_magic_attack_points, 80)

  test("set_attack_points ignore below 0"):
    sword.set_attack_points(-10)
    assertEquals(sword.get_attack_points, 50)

  test("set_owner changes correctly"):
    sword.set_owner("Cris")
    assertEquals(sword.get_owner, "Cris")

  test("set_magic_attack_points changes correctly and ignores below 0"):
    wand.set_magic_attack_points(100)
    assertEquals(wand.get_magic_attack_points, 100)

    wand.set_magic_attack_points(-20)
    assertEquals(wand.get_magic_attack_points, 100)


