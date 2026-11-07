package cl.uchile.dcc
package Actions_test
import Panel.*
import munit.FunSuite
import Actions.*
import Entities.*
import Usable.*

class Actions_test extends FunSuite:
  var poti: Usable_Potion = Mana("Ares")
  var poti2: Usable_Potion = Mana("Zeus")
  var sword: Sword = Sword("Royal Sword", 50, 10, None)
  var bow: Bow = Bow("Arco Largo", 40, 4, None)

  var mov: Move = Move()
  var att: Attack = Attack()
  var thunder: Thunder = Thunder()
  var meteor: Meteor = Meteor()
  var heal: Heal = Heal()
  var purif: Purification = Purification()
  var use_poti: Use_Potion = Use_Potion()
  var equip: Equip_Weapon = Equip_Weapon()

  override def beforeEach(context: BeforeEach): scala.Unit =
    poti = Mana("Ares")
    poti2 = Mana("Zeus")
    sword = Sword("Royal Sword", 50, 10, None)
    bow = Bow("Arco Largo", 40, 4, None)

    mov = Move()
    att = Attack()
    thunder = Thunder()
    meteor = Meteor()
    heal = Heal()
    purif = Purification()
    use_poti = Use_Potion()
    equip = Equip_Weapon()


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


  test("Every action has its own name"):
    assertEquals(att.name, "Attack")
    assertEquals(thunder.name, "Thunder")
    assertEquals(meteor.name, "Meteor")
    assertEquals(heal.name, "Heal")
    assertEquals(purif.name, "Purification")
    assertEquals(equip.name, "Equip weapon")



  test("Every action is an Action"):
    val all: List[Action] = List(mov, att, thunder, meteor, heal, purif, use_poti, equip)
    assert(all.forall(_.isInstanceOf[Action]))

  test("Move and Attack are Non_Utility_Action"):
    assert(mov.isInstanceOf[Non_Utility_Action])
    assert(att.isInstanceOf[Non_Utility_Action])

  test("Spells are Non_Utility_Action"):
    assert(thunder.isInstanceOf[Non_Utility_Action])
    assert(meteor.isInstanceOf[Non_Utility_Action])
    assert(heal.isInstanceOf[Non_Utility_Action])
    assert(purif.isInstanceOf[Non_Utility_Action])

  test("Thunder, Meteor, Heal and Purification are Spells"):
    assert(thunder.isInstanceOf[Spells])
    assert(meteor.isInstanceOf[Spells])
    assert(heal.isInstanceOf[Spells])
    assert(purif.isInstanceOf[Spells])

  test("Move and Attack are not Spells"):
    assert(!mov.isInstanceOf[Spells])
    assert(!att.isInstanceOf[Spells])

  test("Use_Potion and Equip_Weapon are Utility_Action"):
    assert(use_poti.isInstanceOf[Utility_Action])
    assert(equip.isInstanceOf[Utility_Action])

  test("Utility actions are not Non_Utility_Action and vice versa"):
    assert(!use_poti.isInstanceOf[Non_Utility_Action])
    assert(!equip.isInstanceOf[Non_Utility_Action])
    assert(!mov.isInstanceOf[Utility_Action])
    assert(!thunder.isInstanceOf[Utility_Action])

  test("Utility actions can be used through their base type"):
    val actions: List[Utility_Action] = List(use_poti, equip)
    assertEquals(actions.size, 2)
    assert(actions.forall(_.isInstanceOf[Action]))




  test("A new Use_Potion has an empty list"):
    assert(use_poti.get_usable_list.isEmpty)

  test("Use_Potion can hold several potions"):
    use_poti.add_usable_list(poti)
    use_poti.add_usable_list(poti2)
    assertEquals(use_poti.get_usable_list.length, 2)
    assert(use_poti.get_usable_list.contains(poti))
    assert(use_poti.get_usable_list.contains(poti2))

  test("use_usable_list removes only the used potion"):
    use_poti.add_usable_list(poti)
    use_poti.add_usable_list(poti2)
    use_poti.use_usable_list(poti)
    assertEquals(use_poti.get_usable_list.length, 1)
    assert(!use_poti.get_usable_list.contains(poti))
    assert(use_poti.get_usable_list.contains(poti2))

  test("use_usable_list removes only the first encounter of a repeated potion"):
    use_poti.add_usable_list(poti)
    use_poti.add_usable_list(poti)
    use_poti.use_usable_list(poti)
    assertEquals(use_poti.get_usable_list.length, 1)
    assert(use_poti.get_usable_list.contains(poti))

  test("use_usable_list does nothing if the potion is not in the list"):
    use_poti.add_usable_list(poti)
    use_poti.use_usable_list(poti2)
    assertEquals(use_poti.get_usable_list.length, 1)
    assert(use_poti.get_usable_list.contains(poti))

  test("use_usable_list on an empty list does nothing"):
    use_poti.use_usable_list(poti)
    assert(use_poti.get_usable_list.isEmpty)



  test("A new Equip_Weapon has an empty list"):
    assert(equip.get_usable_list.isEmpty)

  test("Equip_Weapon can hold weapons"):
    equip.add_usable_list(sword)
    equip.add_usable_list(bow)
    assertEquals(equip.get_usable_list.length, 2)
    assert(equip.get_usable_list.contains(sword))
    assert(equip.get_usable_list.contains(bow))

  test("Equip_Weapon removes only the used weapon"):
    equip.add_usable_list(sword)
    equip.add_usable_list(bow)
    equip.use_usable_list(sword)
    assertEquals(equip.get_usable_list.length, 1)
    assert(!equip.get_usable_list.contains(sword))
    assert(equip.get_usable_list.contains(bow))

  test("Equip_Weapon removes only the first encounter of a repeated weapon"):
    equip.add_usable_list(sword)
    equip.add_usable_list(sword)
    equip.use_usable_list(sword)
    assertEquals(equip.get_usable_list.length, 1)

  test("Equip_Weapon does nothing if the weapon is not in the list"):
    equip.add_usable_list(sword)
    equip.use_usable_list(bow)
    assertEquals(equip.get_usable_list.length, 1)
    assert(equip.get_usable_list.contains(sword))

  test("use_usable_list on an empty Equip_Weapon does nothing"):
    equip.use_usable_list(sword)
    assert(equip.get_usable_list.isEmpty)

  test("Equip_Weapon and Use_Potion lists are independent"):
    equip.add_usable_list(sword)
    assertEquals(equip.get_usable_list.length, 1)
    assert(use_poti.get_usable_list.isEmpty)

  test("Every spell has its own mana cost"):
    assertEquals(thunder.cost, 20)
    assertEquals(meteor.cost, 50)
    assertEquals(heal.cost, 15)
    assertEquals(purif.cost, 25)

  test("Attack.Causes makes the source attack the target"):
    val enemy = Enemy("Goblin", 100, 5, 10, 20)
    val knight = Knight("Arthur", 100, 5, 10)
    assert(att.Causes(enemy, knight))
    assertEquals(knight.get_health, 85)

  test("Move.Causes moves the unit to an adjacent panel"):
    val knight = Knight("Arthur", 100, 5, 10)
    val p1 = Panel(1, 1)
    val p2 = Panel(1, 2)
    p1.add_units(knight)
    p2.add_adjacent(p1)
    assert(mov.Causes(knight, p2))
    assert(p2.get_units.contains(knight))
    assert(!p1.get_units.contains(knight))

  test("Use_Potion.Causes makes the character consume the potion"):
    val knight = Knight("Arthur", 100, 5, 10)
    val potion = Healing("Cura")
    knight.get_inventory += potion
    knight.set_health(50)
    assert(use_poti.Causes(knight, potion))
    assertEquals(knight.get_health, 70)
    assert(!knight.get_inventory.contains(potion))

  test("Equip_Weapon.Causes makes the character equip the weapon"):
    val knight = Knight("Arthur", 100, 5, 10)
    knight.get_inventory += sword
    assert(equip.Causes(knight, sword))
    assertEquals(knight.get_weapon, Some(sword))

  test("Thunder.Causes damages the enemy with the magic damage"):
    val wand = Wand("Varita", 10, 3, None, 30)
    val bm = Black_Mage("Merlin", 100, 5, 10, Some(wand), 100)
    val enemy = Enemy("Goblin", 100, 5, 10, 20)
    assert(thunder.Causes(bm, enemy))
    assertEquals(enemy.get_health, 70)
    assertEquals(bm.get_mana, 80)

  test("Meteor.Causes damages every unit in the panel"):
    val wand = Wand("Varita", 10, 3, None, 30)
    val bm = Black_Mage("Merlin", 100, 5, 10, Some(wand), 100)
    val e1 = Enemy("Goblin", 100, 5, 10, 20)
    val e2 = Enemy("Orc", 100, 5, 10, 20)
    val panel = Panel(1, 1)
    panel.add_units(e1)
    panel.add_units(e2)
    assert(meteor.Causes(bm, panel))
    assertEquals(e1.get_health, 70)
    assertEquals(e2.get_health, 70)
    assertEquals(bm.get_mana, 50)

  test("Heal.Causes heals the character"):
    val wand = Wand("Varita", 10, 3, None, 30)
    val wm = White_Mage("Gandalf", 100, 5, 10, Some(wand), 100)
    val knight = Knight("Arthur", 100, 5, 10)
    knight.set_health(50)
    assert(heal.Causes(wm, knight))
    assertEquals(knight.get_health, 77)
    assertEquals(wm.get_mana, 85)

  test("Purification.Causes reduces the enemy health"):
    val wand = Wand("Varita", 10, 3, None, 30)
    val wm = White_Mage("Gandalf", 100, 5, 10, Some(wand), 100)
    val enemy = Enemy("Goblin", 100, 5, 10, 20)
    assert(purif.Causes(wm, enemy))
    assertEquals(enemy.get_health, 70)
    assertEquals(wm.get_mana, 75)
