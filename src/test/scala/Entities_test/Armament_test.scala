package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.*




class Armament_test extends FunSuite:
  var sword: Sword = Sword("Royal Sword", 50, 10, "Steve")
  var wand: Wand = Wand("Varita de Fuego", 30, 2, "Sin dueño", 80)
  var cane: Cane = Cane("Báculo Anciano", 20, 5, "Mago", 60)
  var dagger: Dagger = Dagger("Daga Sombría", 25, 1, "Asesino")
  var bow: Bow = Bow("Arco Largo", 40, 4, "Cazador")

  override def beforeEach(context: BeforeEach): scala.Unit =
    sword = Sword("Royal Sword", 50, 10, "Steve")

    wand = Wand("Varita de Fuego", 30, 2, "Sin dueño", 80)
    cane = Cane("Báculo Anciano", 20, 5, "Mago", 60)
    dagger = Dagger("Daga Sombría", 25, 1, "Asesino")
    bow = Bow("Arco Largo", 40, 4, "Cazador")


  test("The non-magic armament are created correctly"):
    assertEquals(sword.name, "Royal Sword")
    assertEquals(sword.get_attack_points, 50)
    assertEquals(sword.weight, 10)
    assertEquals(sword.get_owner, "Steve")



