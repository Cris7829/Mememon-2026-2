package cl.uchile.dcc
package Actions_test

import munit.FunSuite
import Actions.*
import Entities.*

class Actions_test extends FunSuite:
  var poti : Usable_Potion = Mana("Ares")
  var mov : Move = Move()
  var use_poti: Use_Potion = Use_Potion()

  override def beforeEach(context: BeforeEach): scala.Unit =
    poti = Mana("Ares")
    mov = Move()
    use_poti = Use_Potion()


  test("Action names and initialization"):
    assertEquals(mov.name, "Move")
    assertEquals(use_poti.name, "Use Potion")

  test("Use_Potion list management"):
    assert(use_poti.get_usable_list.isEmpty)
    use_poti.add_usable_list(poti)
    assertEquals(use_poti.get_usable_list.length, 1)
    use_poti.use_usable_list(poti)
    assert(use_poti.get_usable_list.isEmpty)

  test("Hierarchy checks"):
    assert(mov.isInstanceOf[Action])
    assert(use_poti.isInstanceOf[Utility_Action])


