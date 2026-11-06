package cl.uchile.dcc
package Action_Sistem
import Actions.*

import Entities.*

trait Target:

        def receive(a: Attack, attacker: Character): Boolean

        def receive(a: Attack, attacker: Enemy): Boolean

        def receive(a: Move, u: Unity): Boolean

        def receive(a: Equip_Weapon, c: Character): Boolean

        def receive(a: Use_Potion, c: Character): Boolean

        def receive(a: Thunder, power: Int): Boolean

        def receive(a: Meteor, power: Int): Boolean

        def receive(a: Heal, power: Int): Boolean

        def receive(a: Purification, power: Int): Boolean