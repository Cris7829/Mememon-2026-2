package cl.uchile.dcc
package Turns_program_test

import Combat_Sistem.Turns_program
import Entities.{Archer, Knight}


class Turns_program_test extends munit.FunSuite:

  var s: Turns_program = Turns_program()
  var knight: Knight = Knight("Knight", 100, 10, 10)
  var archer: Archer = Archer("Archer", 80, 5, 5)

  override def beforeEach(context: BeforeEach): scala.Unit =
    s = Turns_program()
    knight = Knight("Knight", 100, 10, 10)
    archer = Archer("Archer", 80, 5, 5)

  test("Add and remove units are correctly"):
    assert(s.get_t_units.isEmpty)
    s.add_t_units(knight)
    s.add_t_units(knight)
    assertEquals(s.get_t_units.length, 1)
    s.remove_t_units(knight)
    assert(s.get_t_units.isEmpty)

