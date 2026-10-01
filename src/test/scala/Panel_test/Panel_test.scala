package cl.uchile.dcc
package Panel_test

import Entities.*
import munit.FunSuite
import Map.*


class Panel_test extends FunSuite:
  var p1 = Panel(0, 0)
  var p2 = Panel(0, 1)
  var enemy = Enemy("Goblin", 50, 5, 8, 15)

  override def beforeEach(context: BeforeEach): scala.Unit =
    p1 = Panel(0, 0)
    p2 = Panel(0, 1)
    enemy = Enemy("Goblin", 50, 5, 8, 15)


  test("Panel coordinates created correctly"):
    assertEquals(p1.x, 0)
    assertEquals(p1.y, 0)
