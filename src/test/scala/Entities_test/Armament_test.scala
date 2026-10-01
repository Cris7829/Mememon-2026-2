package cl.uchile.dcc
package Entities_test

import cl.uchile.dcc.Entities.*

import munit.FunSuite

class Armament_test extends FunSuite:
  var sword: Sword
  var wand: Wand 
  var cane: Cane 
  var dagger: Dagger 
  var bow: Bow

  override def beforeEach(context: BeforeEach): scala.Unit =
    sword = Sword("Espada Real", 50, 10, "Sin dueño")
    wand = Wand("Varita de Fuego", 30, 2, "Sin dueño", 80)
    cane = Cane("Báculo Anciano", 20, 5, "Mago", 60)
    dagger = Dagger("Daga Sombría", 25, 1, "Asesino")
    bow = Bow("Arco Largo", 40, 4, "Cazador")





