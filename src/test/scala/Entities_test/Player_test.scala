package cl.uchile.dcc
package Entities_test

import munit.FunSuite
import Entities.*

class Player_test extends FunSuite:
  var player: Players = Players("Ash")

  override def beforeEach(context: BeforeEach): scala.Unit =
   player = Players("Ash")


  test("Player initialization and name getter"):
    assertEquals(player.name, "Ash")


  test("Hierarchy checks"):
    assert(player.isInstanceOf[Player])
    assert(player.isInstanceOf[Players])


