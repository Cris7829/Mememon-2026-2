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

  test(" The Maximum action bar is the maximum weight"):
    s.add_t_units(knight)
    s.add_t_units(archer)
    assertEquals(s.calculate_max_action_bar, 10)

  test("Increase and reset action bar"):
    s.add_t_units(knight)
    s.increase_action_bars(4)
    assertEquals(s.get_action_bar(knight), 4)
    s.reset_action_bars(knight)
    assertEquals(s.get_action_bar(knight), 0)

  test()


