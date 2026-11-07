package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.{Archer, *}
import Actions.*
import Exceptions.*
import Usable.*

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


  test("set_health never goes below 0"):
    knight.set_health(-30)
    assertEquals(knight.get_health, 0)

  test("set_defense ignores values below or equal to 0"):
    knight.set_defense(0)
    assertEquals(knight.get_defense, 25)
    knight.set_defense(30)
    assertEquals(knight.get_defense, 30)

  test("set_weapon ignores None"):
    val sword = Sword("S", 50, 10, None)
    knight.set_weapon(Some(sword))
    knight.set_weapon(None)
    assertEquals(knight.get_weapon, Some(sword))

  test("Magic multiplier starts at 1.0 and ignores values below 1.0"):
    assertEquals(black_mage.get_magic_multiplier, 1.0)
    black_mage.set_magic_multiplier(0.5)
    assertEquals(black_mage.get_magic_multiplier, 1.0)
    black_mage.set_magic_multiplier(1.5)
    assertEquals(black_mage.get_magic_multiplier, 1.5)

  test("max_health and max_mana keep the initial values"):
    knight.set_health(10)
    assertEquals(knight.max_health, 120)
    black_mage.set_mana(10)
    assertEquals(black_mage.max_mana, 100)


  test("Each unit has its own possible actions"):
    assert(enemy.find_action("Attack").isDefined)
    assert(enemy.find_action("Move").isDefined)
    assert(enemy.find_action("Equip weapon").isEmpty)
    assert(knight.find_action("Equip weapon").isDefined)
    assert(knight.find_action("Use Potion").isDefined)
    assert(knight.find_action("Thunder").isEmpty)
    assert(black_mage.find_action("Thunder").isDefined)
    assert(black_mage.find_action("Meteor").isDefined)
    assert(white_mage.find_action("Heal").isDefined)
    assert(white_mage.find_action("Purification").isDefined)


  test("A defeated unit cannot do any action"):
    knight.set_health(0)
    intercept[DefeatedUnitException](knight.doAction(Attack(), enemy))

  test("A unit cannot do an action it does not have"):
    intercept[InvalidTargetException](enemy.doAction(Thunder(), knight))
    intercept[InvalidTargetException](knight.doAction(Heal(), archer))


  test("A character with a weapon attacks an enemy"):
    knight.set_weapon(Some(Sword("S", 50, 10, None)))
    assert(knight.doAction(Attack(), enemy))
    assertEquals(enemy.get_health, 5)

  test("An enemy attacks a character"):
    assert(enemy.doAction(Attack(), thieve))
    assertEquals(thieve.get_health, 75)

  test("If the defense is higher than the attack, no damage is done"):
    assert(enemy.doAction(Attack(), knight))
    assertEquals(knight.get_health, 120)

  test("A character cannot attack without a weapon"):
    intercept[InvalidWeaponException](knight.doAction(Attack(), enemy))

  test("A character cannot attack another character and an enemy cannot attack another enemy"):
    knight.set_weapon(Some(Sword("S", 50, 10, None)))
    assert(!knight.doAction(Attack(), archer))
    assert(!enemy.doAction(Attack(), Enemy("Orc", 50, 5, 8, 15)))


  test("A defeated enemy cannot receive an attack"):
    knight.set_weapon(Some(Sword("S", 50, 10, None)))
    enemy.set_health(0)
    intercept[DefeatedUnitException](knight.doAction(Attack(), enemy))

  test("A defeated character cannot receive an attack"):
    thieve.set_health(0)
    intercept[DefeatedUnitException](enemy.doAction(Attack(), thieve))

  test("A defeated enemy cannot receive Thunder nor Purification"):
    black_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    white_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    enemy.set_health(0)
    intercept[DefeatedUnitException](black_mage.doAction(Thunder(), enemy))
    intercept[DefeatedUnitException](white_mage.doAction(Purification(), enemy))

  test("A defeated character cannot receive Heal"):
    white_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    knight.set_health(0)
    intercept[DefeatedUnitException](white_mage.doAction(Heal(), knight))

  test("Thunder deals magic damage and costs 20 mana"):
    black_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    assert(black_mage.doAction(Thunder(), enemy))
    assertEquals(enemy.get_health, 20)
    assertEquals(black_mage.get_mana, 80)

  test("Thunder can defeat an enemy"):
    black_mage.set_weapon(Some(Wand("W", 10, 2, None, 80)))
    black_mage.doAction(Thunder(), enemy)
    assertEquals(enemy.get_health, 0)

  test("Magic Strength increases Thunder damage by 50% and is lost after casting"):
    black_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    black_mage.set_magic_multiplier(1.5)
    black_mage.doAction(Thunder(), enemy)
    assertEquals(enemy.get_health, 5)
    assertEquals(black_mage.get_magic_multiplier, 1.0)

  test("Purification reduces 15% of the current health plus magicDamage/2"):
    white_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    assert(white_mage.doAction(Purification(), enemy))
    assertEquals(enemy.get_health, 27)
    assertEquals(white_mage.get_mana, 85)

  test("Purification can defeat an enemy"):
    white_mage.set_weapon(Some(Wand("W", 10, 2, None, 200)))
    white_mage.doAction(Purification(), enemy)
    assertEquals(enemy.get_health, 0)

  test("Heal restores 20% of max health plus magicDamage/4"):
    white_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    knight.set_health(50)
    assert(white_mage.doAction(Heal(), knight))
    assertEquals(knight.get_health, 81)
    assertEquals(white_mage.get_mana, 95)

  test("A Black Mage without enough mana cannot cast Thunder nor Meteor"):
    black_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    black_mage.set_mana(10)
    intercept[InsufficientManaException](black_mage.doAction(Thunder(), enemy))
    black_mage.set_mana(40)
    intercept[InsufficientManaException](black_mage.doAction(Meteor(), Panel.Panel(1, 1)))

  test("A White Mage without enough mana cannot cast Heal nor Purification"):
    white_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    white_mage.set_mana(10)
    intercept[InsufficientManaException](white_mage.doAction(Heal(), knight))
    white_mage.set_mana(20)
    intercept[InsufficientManaException](white_mage.doAction(Purification(), enemy))

  test("Failing a spell for mana does not change the mana nor the target"):
    black_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    black_mage.set_mana(10)
    intercept[InsufficientManaException](black_mage.doAction(Thunder(), enemy))
    assertEquals(black_mage.get_mana, 10)
    assertEquals(enemy.get_health, 50)


  test("A mage without weapon cannot cast spells"):
    intercept[InvalidWeaponException](black_mage.doAction(Thunder(), enemy))
    intercept[InvalidWeaponException](black_mage.doAction(Meteor(), Panel.Panel(1, 1)))
    intercept[InvalidWeaponException](white_mage.doAction(Heal(), knight))
    intercept[InvalidWeaponException](white_mage.doAction(Purification(), enemy))

  test("A mage with a non-magic weapon cannot cast spells"):
    black_mage.set_weapon(Some(Dagger("D", 20, 1, None)))
    white_mage.set_weapon(Some(Bow("B", 20, 4, None)))
    intercept[InvalidWeaponException](black_mage.doAction(Thunder(), enemy))
    intercept[InvalidWeaponException](white_mage.doAction(Heal(), knight))


  test("Purification can only be received by enemies"):
    white_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    intercept[InvalidTargetException](white_mage.doAction(Purification(), knight))
    intercept[InvalidTargetException](white_mage.doAction(Purification(), black_mage))

  test("Heal can only be received by characters"):
    white_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    intercept[InvalidTargetException](white_mage.doAction(Heal(), enemy))

  test("Thunder and Meteor cannot be received by characters"):
    black_mage.set_weapon(Some(Wand("W", 10, 2, None, 30)))
    assert(!black_mage.doAction(Thunder(), knight))
    assert(!knight.receive(Meteor(), black_mage))
    assert(!enemy.receive(Meteor(), black_mage))


  test("Each character class can equip an allowed weapon from its inventory"):
    val sword = Sword("S", 50, 10, None)
    val bow = Bow("B", 40, 4, None)
    val dagger = Dagger("D", 20, 1, None)
    val cane = Cane("C", 10, 5, None, 40)
    val wand = Wand("W", 10, 2, None, 30)
    knight.get_inventory += sword
    archer.get_inventory += bow
    thieve.get_inventory += dagger
    black_mage.get_inventory += cane
    white_mage.get_inventory += wand
    assert(knight.doAction(Equip_Weapon(), sword))
    assert(archer.doAction(Equip_Weapon(), bow))
    assert(thieve.doAction(Equip_Weapon(), dagger))
    assert(black_mage.doAction(Equip_Weapon(), cane))
    assert(white_mage.doAction(Equip_Weapon(), wand))
    assertEquals(knight.get_weapon, Some(sword))
    assertEquals(white_mage.get_weapon, Some(wand))

  test("A character cannot equip a weapon that is not in its inventory"):
    val sword = Sword("S", 50, 10, None)
    intercept[InvalidTargetException](knight.doAction(Equip_Weapon(), sword))
    intercept[InvalidTargetException](archer.doAction(Equip_Weapon(), sword))
    intercept[InvalidTargetException](thieve.doAction(Equip_Weapon(), sword))
    intercept[InvalidTargetException](black_mage.doAction(Equip_Weapon(), sword))
    intercept[InvalidTargetException](white_mage.doAction(Equip_Weapon(), sword))

  test("A character cannot equip a weapon not allowed by the table"):
    val wand = Wand("W", 10, 2, None, 30)
    knight.get_inventory += wand
    intercept[InvalidWeaponException](knight.doAction(Equip_Weapon(), wand))
    assertEquals(knight.get_weapon, None)






