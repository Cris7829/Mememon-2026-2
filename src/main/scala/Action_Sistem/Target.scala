package cl.uchile.dcc
package Action_Sistem
import Actions.*

import Entities.*

trait Target:
        
        

        def receive(a: Attack, attacker: Character): Boolean

        def receive(a: Attack, attacker: Enemy): Boolean

        def receive(a: Move, u:Unity): Boolean

        def receive(a: Equip_Weapon, c: Character): Boolean

        def receive(a: Use_Potion, c: Character): Boolean

        
        
        def receive(a: Thunder, m: Black_Mage): Boolean

        def receive(a: Meteor, m: Black_Mage): Boolean

        def receive(a: Heal, m: White_Mage): Boolean

        def receive(a: Purification, m: White_Mage): Boolean