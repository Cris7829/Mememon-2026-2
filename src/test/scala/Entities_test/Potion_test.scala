package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Usable.*
import Entities.*
import Actions.*
import Exceptions.*



class Potion_test extends FunSuite:
  var healing_potion: Healing=Healing("Healing Potion")
  var mana_potion: Mana=Mana("Maná Potion")
  var magic_strength_potion: Magic_Strength = Magic_Strength("Ares Potion")
  var strength_potion: Strength_Player = Strength_Player("GOD potion")

  def knight = Knight("K", 100, 20, 20)

  def bmage = Black_Mage("BM", 100, 20, 20, 100)

  override def beforeEach(context: BeforeEach): scala.Unit =
    healing_potion = Healing("Healing Potion")
    mana_potion = Mana("Maná Potion")
    magic_strength_potion = Magic_Strength("Ares Potion")
    strength_potion = Strength_Player("GOD potion")

  test("The creator works for every type of potion"):
    assertEquals(healing_potion.name, "Healing Potion")

    assertEquals(mana_potion.name, "Maná Potion")

    assertEquals(magic_strength_potion.name, "Ares Potion")

    assertEquals(strength_potion.name, "GOD potion")



  test("Hyperarchy of all the potions types"):
    assert(healing_potion.isInstanceOf[Potion])
    assert(healing_potion.isInstanceOf[Usable_Potion])

    assert(mana_potion.isInstanceOf[Potion])
    assert(mana_potion.isInstanceOf[Usable_Potion])

    assert(magic_strength_potion.isInstanceOf[Potion])
    assert(magic_strength_potion.isInstanceOf[Usable_Potion])

    assert(strength_potion.isInstanceOf[Potion])
    assert(strength_potion.isInstanceOf[Usable_Potion])

  test("Healing restores 20% of the max health and is removed from the inventory"):
    val k = knight
    k.get_inventory += healing_potion
    k.set_health(50)
    assert(healing_potion.receive(Use_Potion(), k))
    assertEquals(k.get_health, 70)
    assert(!k.get_inventory.contains(healing_potion))

  test("Healing does not exceed the max health"):
    val k = knight
    k.set_health(95)
    healing_potion.receive(Use_Potion(), k)
    assertEquals(k.get_health, 100)

  test("Healing also works on a magic character"):
    val m = bmage
    m.set_health(50)
    assert(healing_potion.receive(Use_Potion(), m))
    assertEquals(m.get_health, 70)

  test("Strength increases the defense by 15%"):
    val k = knight
    assert(strength_potion.receive(Use_Potion(), k))
    assertEquals(k.get_defense, 23)

  test("Strength also works on a magic character"):
    val m = bmage
    assert(strength_potion.receive(Use_Potion(), m))
    assertEquals(m.get_defense, 23)

  test("Mana restores 30% of the max mana"):
    val m = bmage
    m.set_mana(50)
    m.get_inventory += mana_potion
    assert(mana_potion.receive(Use_Potion(), m))
    assertEquals(m.get_mana, 80)
    assert(!m.get_inventory.contains(mana_potion))

  test("Mana does not exceed the max mana"):
    val m = bmage
    m.set_mana(90)
    mana_potion.receive(Use_Potion(), m)
    assertEquals(m.get_mana, 100)

  test("Mana has no effect on a common character and is not consumed"):
    val k = knight
    k.get_inventory += mana_potion
    assert(!mana_potion.receive(Use_Potion(), k))
    assert(k.get_inventory.contains(mana_potion))

  test("Magic Strength sets the magic multiplier to 1.5"):
    val m = bmage
    assert(magic_strength_potion.receive(Use_Potion(), m))
    assertEquals(m.get_magic_multiplier, 1.5)

  test("Magic Strength has no effect on a common character"):
    val k = knight
    k.get_inventory += magic_strength_potion
    assert(!magic_strength_potion.receive(Use_Potion(), k))
    assert(k.get_inventory.contains(magic_strength_potion))

  test("A potion cannot receive Heal nor Purification"):
    intercept[InvalidTargetException](healing_potion.receive(Heal(), White_Mage("WM", 100, 5, 10, 100)))
    intercept[InvalidTargetException](healing_potion.receive(Purification(), White_Mage("WM", 100, 5, 10, 100)))
