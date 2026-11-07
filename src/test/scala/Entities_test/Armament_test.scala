package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.*
import Usable.*
import Exceptions.*
import Panel.*

import Actions.*


class Armament_test extends FunSuite:
  var steve: Character = new Character("Steve", 100, 10, 20, None)
  var cris: Character = new Character("Cris", 100, 10, 20, None)

  def knight = Knight("K", 100, 10, 20)

  def archer = Archer("A", 100, 10, 20)

  def thief = Thieve("T", 100, 10, 20)

  def bmage = Black_Mage("BM", 100, 10, 20, 100)

  def wmage = White_Mage("WM", 100, 10, 20, 100)

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

  test("is_magic is false for common weapons and true for magic weapons"):
    assert(!sword.is_magic)
    assert(!dagger.is_magic)
    assert(!bow.is_magic)
    assert(wand.is_magic)
    assert(cane.is_magic)

  test("Common weapons have 0 magic attack points"):
    assertEquals(sword.get_magic_attack_points, 0)
    assertEquals(bow.get_magic_attack_points, 0)
    assertEquals(dagger.get_magic_attack_points, 0)

  test("set_attack_points changes the attack with a valid value"):
    sword.set_attack_points(70)
    assertEquals(sword.get_attack_points, 70)

  test("set_owner changes the owner"):
    wand.set_owner(steve)
    assertEquals(wand.get_owner, "Steve")

  test("Sword can be equipped by Knight, Archer and Thief only"):
    assert(sword.can_be_equipped_by(knight))
    assert(sword.can_be_equipped_by(archer))
    assert(sword.can_be_equipped_by(thief))
    assert(!sword.can_be_equipped_by(bmage))
    assert(!sword.can_be_equipped_by(wmage))

  test("Dagger can be equipped by Knight, Thief and Black Mage only"):
    assert(dagger.can_be_equipped_by(knight))
    assert(!dagger.can_be_equipped_by(archer))
    assert(dagger.can_be_equipped_by(thief))
    assert(dagger.can_be_equipped_by(bmage))
    assert(!dagger.can_be_equipped_by(wmage))

  test("Bow can be equipped by Knight, Archer and White Mage only"):
    assert(bow.can_be_equipped_by(knight))
    assert(bow.can_be_equipped_by(archer))
    assert(!bow.can_be_equipped_by(thief))
    assert(!bow.can_be_equipped_by(bmage))
    assert(bow.can_be_equipped_by(wmage))

  test("Wand can be equipped by Archer, Black Mage and White Mage only"):
    assert(!wand.can_be_equipped_by(knight))
    assert(wand.can_be_equipped_by(archer))
    assert(!wand.can_be_equipped_by(thief))
    assert(wand.can_be_equipped_by(bmage))
    assert(wand.can_be_equipped_by(wmage))

  test("Cane can be equipped by Black Mage and White Mage only"):
    assert(!cane.can_be_equipped_by(knight))
    assert(!cane.can_be_equipped_by(archer))
    assert(!cane.can_be_equipped_by(thief))
    assert(cane.can_be_equipped_by(bmage))
    assert(cane.can_be_equipped_by(wmage))

  test("An allowed weapon is equipped and its owner changes"):
    val k = knight
    k.get_inventory += sword
    assert(sword.receive(Equip_Weapon(), k))
    assertEquals(k.get_weapon, Some(sword))
    assertEquals(sword.get_owner, "K")
    assert(!k.get_inventory.contains(sword))

  test("Equipping a new weapon puts the old one back in the inventory"):
    val k = knight
    sword.receive(Equip_Weapon(), k)
    dagger.receive(Equip_Weapon(), k)
    assertEquals(k.get_weapon, Some(dagger))
    assert(k.get_inventory.contains(sword))

  test("Each character class throws when equipping a forbidden weapon"):
    intercept[InvalidWeaponException](wand.receive(Equip_Weapon(), knight))
    intercept[InvalidWeaponException](dagger.receive(Equip_Weapon(), archer))
    intercept[InvalidWeaponException](bow.receive(Equip_Weapon(), thief))
    intercept[InvalidWeaponException](sword.receive(Equip_Weapon(), bmage))
    intercept[InvalidWeaponException](dagger.receive(Equip_Weapon(), wmage))

  test("A forbidden weapon is not equipped after the exception"):
    val k = knight
    intercept[InvalidWeaponException](cane.receive(Equip_Weapon(), k))
    assertEquals(k.get_weapon, None)


  test("An armament cannot receive Heal nor Purification"):
    intercept[InvalidTargetException](sword.receive(Heal(), wmage))
    intercept[InvalidTargetException](sword.receive(Purification(), wmage))



