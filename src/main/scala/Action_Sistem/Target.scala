package cl.uchile.dcc
package Action_Sistem
import Actions.*

import Entities.*

trait Target:



        def receive(a: Attack, attacker: Character): Boolean

        def receive(a: Attack, attacker: Enemy): Boolean

        def receive(a: Move, u:Unity): Boolean


        def receive(a: Use_Potion, c: Character): Boolean



        def receive(a: Thunder, m: Black_Mage): Boolean

        def receive(a: Meteor, m: Black_Mage): Boolean

        def receive(a: Heal, m: White_Mage): Boolean

        def receive(a: Purification, m: White_Mage): Boolean


        def receive(a: Equip_Weapon, c: Knight): Boolean

        def receive(a: Equip_Weapon, c: Archer): Boolean

        def receive(a: Equip_Weapon, c: Thieve): Boolean

        def receive(a: Equip_Weapon, c: Black_Mage): Boolean

        def receive(a: Equip_Weapon, c: White_Mage): Boolean


        def receive(a: Use_Potion, m: Magic_Character): Boolean