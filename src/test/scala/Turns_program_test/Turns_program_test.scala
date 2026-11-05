package cl.uchile.dcc
package Turns_program_test

import munit.FunSuite
import Combat_Sistem.*
import Entities.*
import Usable.*

class Turns_program_test extends FunSuite:
  var program: Turns_program = Turns_program()

  var hero: Character = new Character("Hero", 100, 10, 20, None)
  var armedHero: Character = new Character("Armed", 100, 10, 20, Some(Sword("Royal Sword", 50, 10, None)))
  var goblin: Enemy = Enemy("Goblin", 50, 5, 30, 10)
  var fastEnemy: Enemy = Enemy("Bat", 20, 1, 10, 5)


  var knight: Knight = Knight("Arthur", 150, 20, 40)
  var thieve: Thieve = Thieve("Rogue", 80, 5, 12)
  var archer: Archer = Archer("Robin", 90, 8, 20)
  var blackMage: Black_Mage = Black_Mage("Dark", 70, 3, 14, 100)
  var whiteMage: White_Mage = White_Mage("Light", 75, 4, 16, 90)

  var armedKnight: Knight = Knight("Lancelot", 150, 20, 40, Some(Sword("Excalibur", 60, 10, None)))
  var armedThieve: Thieve = Thieve("Shadow", 80, 5, 12, Some(Dagger("Dark Dagger", 25, 1, None)))
  var armedArcher: Archer = Archer("Legolas", 90, 8, 20, Some(Bow("Arco Largo", 40, 4, None)))
  var armedBlackMage: Black_Mage = Black_Mage("Merlin", 70, 3, 14, Some(Wand("Fire Wand", 30, 2, None, 80)), 100)
  var armedWhiteMage: White_Mage = White_Mage("Gandalf", 75, 4, 16, Some(Cane("Cas", 20, 5, None, 60)), 90)

  override def beforeEach(context: BeforeEach): scala.Unit =
    program = Turns_program()

    hero = new Character("Hero", 100, 10, 20, None)
    armedHero = new Character("Armed", 100, 10, 20, Some(Sword("Royal Sword", 50, 10, None)))
    goblin = Enemy("Goblin", 50, 5, 30, 10)
    fastEnemy = Enemy("Bat", 20, 1, 10, 5)

    knight = Knight("Arthur", 150, 20, 40)
    thieve = Thieve("Rogue", 80, 5, 12)
    archer = Archer("Robin", 90, 8, 20)

    blackMage = Black_Mage("Dark", 70, 3, 14, 100)
    whiteMage = White_Mage("Light", 75, 4, 16, 90)

    armedKnight = Knight("Lancelot", 150, 20, 40, Some(Sword("Excalibur", 60, 10, None)))
    armedThieve = Thieve("Shadow", 80, 5, 12, Some(Dagger("Dark Dagger", 25, 1, None)))
    armedArcher = Archer("Legolas", 90, 8, 20, Some(Bow("Arco Largo", 40, 4, None)))
    armedBlackMage = Black_Mage("Merlin", 70, 3, 14, Some(Wand("Fire Wand", 30, 2, None, 80)), 100)
    armedWhiteMage = White_Mage("Gandalf", 75, 4, 16, Some(Cane("Cas", 20, 5, None, 60)), 90)



  test("A new turn program is empty"):
    assertEquals(program.get_turn, None)
    assert(program.get_t_units_list.isEmpty)
    assert(program.get_during_program_map.isEmpty)
    assert(program.get_action_bars.isEmpty)
    assert(program.get_sorted_Seq.isEmpty)

  test("check_action_bar is false on an empty program"):
    assert(!program.check_action_bar)



  test("A Character without weapon is added with max action bar equal to its weight"):
    program.add_unit_to_program(hero)
    assert(program.get_t_units_list.contains(hero))
    assertEquals(program.get_during_program_map(hero), 0.0)
    assertEquals(program.get_action_bars(hero), 20.0)

  test("A Character with weapon is added with max action bar = weight + 0.5 * weapon weight"):
    program.add_unit_to_program(armedHero)
    assert(program.get_t_units_list.contains(armedHero))
    assertEquals(program.get_during_program_map(armedHero), 0.0)
    assertEquals(program.get_action_bars(armedHero), 25.0)

  test("An Enemy is added with max action bar equal to its weight"):
    program.add_unit_to_program(goblin)
    assert(program.get_t_units_list.contains(goblin))
    assertEquals(program.get_during_program_map(goblin), 0.0)
    assertEquals(program.get_action_bars(goblin), 30.0)

  test("Adding the same Character twice does not duplicate it"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(hero)
    assertEquals(program.get_t_units_list.size, 1)
    assertEquals(program.get_action_bars.size, 1)
    assertEquals(program.get_during_program_map.size, 1)

  test("Adding the same Enemy twice does not duplicate it"):
    program.add_unit_to_program(goblin)
    program.add_unit_to_program(goblin)
    assertEquals(program.get_t_units_list.size, 1)
    assertEquals(program.get_action_bars.size, 1)
    assertEquals(program.get_during_program_map.size, 1)

  test("Characters and Enemies can coexist in the program"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    assertEquals(program.get_t_units_list.size, 2)
    assertEquals(program.get_action_bars.size, 2)
    assertEquals(program.get_during_program_map.size, 2)



  test("A Knight without weapon has max action bar equal to its weight"):
    program.add_unit_to_program(knight)
    assert(program.get_t_units_list.contains(knight))
    assertEquals(program.get_during_program_map(knight), 0.0)
    assertEquals(program.get_action_bars(knight), 40.0)

  test("A Knight with weapon has max action bar = weight + 0.5 * weapon weight"):
    program.add_unit_to_program(armedKnight)
    assertEquals(program.get_action_bars(armedKnight), 45.0)

  test("A Thieve without weapon has max action bar equal to its weight"):
    program.add_unit_to_program(thieve)
    assert(program.get_t_units_list.contains(thieve))
    assertEquals(program.get_action_bars(thieve), 12.0)

  test("A Thieve with weapon has max action bar = weight + 0.5 * weapon weight"):
    program.add_unit_to_program(armedThieve)
    assertEquals(program.get_action_bars(armedThieve), 12.5)

  test("An Archer without weapon has max action bar equal to its weight"):
    program.add_unit_to_program(archer)
    assert(program.get_t_units_list.contains(archer))
    assertEquals(program.get_action_bars(archer), 20.0)

  test("An Archer with weapon has max action bar = weight + 0.5 * weapon weight"):
    program.add_unit_to_program(armedArcher)
    assertEquals(program.get_action_bars(armedArcher), 22.0)



  test("A Black_Mage without weapon has max action bar equal to its weight"):
    program.add_unit_to_program(blackMage)
    assert(program.get_t_units_list.contains(blackMage))
    assertEquals(program.get_during_program_map(blackMage), 0.0)
    assertEquals(program.get_action_bars(blackMage), 14.0)

  test("A Black_Mage with weapon has max action bar = weight + 0.5 * weapon weight"):
    program.add_unit_to_program(armedBlackMage)
    assertEquals(program.get_action_bars(armedBlackMage), 15.0)

  test("A White_Mage without weapon has max action bar equal to its weight"):
    program.add_unit_to_program(whiteMage)
    assert(program.get_t_units_list.contains(whiteMage))
    assertEquals(program.get_during_program_map(whiteMage), 0.0)
    assertEquals(program.get_action_bars(whiteMage), 16.0)

  test("A White_Mage with weapon has max action bar = weight + 0.5 * weapon weight"):
    program.add_unit_to_program(armedWhiteMage)
    assertEquals(program.get_action_bars(armedWhiteMage), 18.5)

  test("The mana of a magic character does not affect its action bar"):
    program.add_unit_to_program(blackMage)
    assertEquals(program.get_action_bars(blackMage), blackMage.get_weight.toDouble)

  test("All kinds of units can coexist in the program"):
    program.add_unit_to_program(knight)
    program.add_unit_to_program(thieve)
    program.add_unit_to_program(archer)
    program.add_unit_to_program(blackMage)
    program.add_unit_to_program(whiteMage)
    program.add_unit_to_program(goblin)
    assertEquals(program.get_t_units_list.size, 6)
    assertEquals(program.get_action_bars.size, 6)
    assertEquals(program.get_during_program_map.size, 6)


  test("remove_unit_of_program removes the unit from the list and both maps"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    program.remove_unit_of_program(hero)
    assert(!program.get_t_units_list.contains(hero))
    assert(!program.get_during_program_map.contains(hero))
    assert(!program.get_action_bars.contains(hero))
    assert(program.get_t_units_list.contains(goblin))

  test("remove_unit_of_program does nothing if the unit is not in the program"):
    program.add_unit_to_program(hero)
    program.remove_unit_of_program(goblin)
    assertEquals(program.get_t_units_list.size, 1)
    assert(program.get_t_units_list.contains(hero))

  test("remove_unit_of_program works with specific characters"):
    program.add_unit_to_program(knight)
    program.add_unit_to_program(blackMage)
    program.remove_unit_of_program(knight)
    assert(!program.get_t_units_list.contains(knight))
    assert(!program.get_action_bars.contains(knight))
    assert(program.get_t_units_list.contains(blackMage))

  test("A removed unit can be added again"):
    program.add_unit_to_program(hero)
    program.remove_unit_of_program(hero)
    program.add_unit_to_program(hero)
    assert(program.get_t_units_list.contains(hero))
    assertEquals(program.get_action_bars(hero), 20.0)
    assertEquals(program.get_during_program_map(hero), 0.0)



  test("add_action_bar ignores negative values"):
    program.add_unit_to_program(hero)
    program.add_action_bar(-5)
    assertEquals(program.get_during_program_map(hero), 0.0)
    assert(program.get_sorted_Seq.isEmpty)

  test("add_action_bar with 0 does not change anything"):
    program.add_unit_to_program(hero)
    program.add_action_bar(0)
    assertEquals(program.get_during_program_map(hero), 0.0)
    assert(program.get_sorted_Seq.isEmpty)

  test("add_action_bar on an empty program does nothing"):
    program.add_action_bar(10)
    assert(program.get_sorted_Seq.isEmpty)
    assert(program.get_during_program_map.isEmpty)

  test("check_action_bar is false right after adding units"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    assert(!program.check_action_bar)

  test("add_action_bar increases the action bar of every unit"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    program.add_action_bar(10)
    assertEquals(program.get_during_program_map(hero), 10.0)
    assertEquals(program.get_during_program_map(goblin), 10.0)
    assert(program.get_sorted_Seq.isEmpty)

  test("add_action_bar accumulates over several calls"):
    program.add_unit_to_program(goblin)
    program.add_action_bar(10)
    program.add_action_bar(10)
    assertEquals(program.get_during_program_map(goblin), 20.0)
    assert(program.get_sorted_Seq.isEmpty)

  test("A unit that reaches its max action bar enters sorted_Seq"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    program.add_action_bar(10)
    assert(program.get_sorted_Seq.isEmpty)
    program.add_action_bar(10)
    assertEquals(program.get_sorted_Seq.map(_._1), Seq[Unity](hero))
    assertEquals(program.get_sorted_Seq.map(_._2), Seq(0.0))

  test("A unit that completes its bar enters sorted_Seq with its surplus"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    program.add_action_bar(25)
    assertEquals(program.get_sorted_Seq.map(_._1), Seq[Unity](hero))
    assertEquals(program.get_sorted_Seq.map(_._2), Seq(5.0))

  test("A unit that completes its bar has its action bar reset to 0"):
    program.add_unit_to_program(hero)
    program.add_action_bar(25)
    assertEquals(program.get_during_program_map(hero), 0.0)

  test("Units that did not complete their bar keep their progress"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    program.add_action_bar(25)
    assertEquals(program.get_during_program_map(goblin), 25.0)

  test("sorted_Seq only contains units that reached their max action bar"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    program.add_unit_to_program(fastEnemy)
    program.add_action_bar(20)
    val inSeq = program.get_sorted_Seq.map(_._1)
    assert(inSeq.contains(hero))
    assert(inSeq.contains(fastEnemy))
    assert(!inSeq.contains(goblin))
    assert(program.get_sorted_Seq.forall(_._2 >= 0))

  test("sorted_Seq is ordered by decreasing surplus"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(fastEnemy)
    program.add_unit_to_program(goblin)
    program.add_action_bar(40)
    assertEquals(program.get_sorted_Seq.map(_._1), Seq[Unity](fastEnemy, hero, goblin))
    assertEquals(program.get_sorted_Seq.map(_._2), Seq(30.0, 20.0, 10.0))

  test("add_action_bar does nothing while there are units waiting for their turn"):
    program.add_unit_to_program(hero)
    program.add_unit_to_program(goblin)
    program.add_action_bar(25)
    val seqBefore = program.get_sorted_Seq
    program.add_action_bar(10)
    assertEquals(program.get_during_program_map(goblin), 25.0)
    assertEquals(program.get_during_program_map(hero), 0.0)
    assertEquals(program.get_sorted_Seq, seqBefore)



  test("Different character classes are ordered by surplus in sorted_Seq"):
    program.add_unit_to_program(knight)
    program.add_unit_to_program(thieve)
    program.add_unit_to_program(archer)
    program.add_unit_to_program(blackMage)
    program.add_action_bar(20)
    assertEquals(program.get_sorted_Seq.map(_._1), Seq[Unity](thieve, blackMage, archer))
    assertEquals(program.get_sorted_Seq.map(_._2), Seq(8.0, 6.0, 0.0))
    assertEquals(program.get_during_program_map(knight), 20.0)

  test("A weapon makes a character take longer to complete its action bar"):
    program.add_unit_to_program(thieve)
    program.add_unit_to_program(armedThieve)
    program.add_action_bar(12)
    assertEquals(program.get_sorted_Seq.map(_._1), Seq[Unity](thieve))
    assertEquals(program.get_during_program_map(armedThieve), 12.0)

  test("Magic characters complete their action bar like any other unit"):
    program.add_unit_to_program(whiteMage)
    program.add_unit_to_program(blackMage)
    program.add_action_bar(16)
    assertEquals(program.get_sorted_Seq.map(_._1), Seq[Unity](blackMage, whiteMage))
    assertEquals(program.get_sorted_Seq.map(_._2), Seq(2.0, 0.0))

  test("Characters and Enemies are mixed correctly in sorted_Seq"):
    program.add_unit_to_program(armedKnight)
    program.add_unit_to_program(goblin)
    program.add_unit_to_program(fastEnemy)
    program.add_unit_to_program(whiteMage)
    program.add_action_bar(35)
    assertEquals(program.get_sorted_Seq.map(_._1), Seq[Unity](fastEnemy, whiteMage, goblin))
    assertEquals(program.get_sorted_Seq.map(_._2), Seq(25.0, 19.0, 5.0))
    assertEquals(program.get_during_program_map(armedKnight), 35.0)