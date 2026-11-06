package cl.uchile.dcc
package Action_Sistem
import Actions.*

import Entities.*

trait Target:
    
        def receive(a: Attack, damage: Int): Boolean

        def receive(a: Move, u: Unit): Boolean

        def receive(a: Equip_Weapon, c: Character): Boolean

        def receive(a: Use_Potion, c: Character): Boolean

        def receive(a: Thunder, power: Int): Boolean

        def receive(a: Meteor, power: Int): Boolean

        def receive(a: Heal): Boolean

        def receive(a: Purification): Boolean