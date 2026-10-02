package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.*



class Potion_test extends FunSuite:
  var healing_potion: Healing=Healing("Healing Potion")
  var mana_potion: Mana=Mana("Maná Potion")
  var magic_strength_potion: Magic_Strength = Magic_Strength("Ares Potion")
  var strength_potion: Strength_Player = Strength_Player("GOD potion")

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