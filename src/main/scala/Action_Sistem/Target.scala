package cl.uchile.dcc
package Action_Sistem
import Actions.*
import Entities.*


trait Target:


        /** Receives an attack from a Character.
         *
         * @param a The Attack action.
         * @param attacker The Character that attacks.
         * @return true if the target received the attack.
         */
        def receive(a: Attack, attacker: Character): Boolean

        /** Receives an attack from an Enemy.
         *
         * @param a The Attack action.
         * @param attacker The Enemy that attacks.
         * @return true if the target received the attack.
         */
        def receive(a: Attack, attacker: Enemy): Boolean

        /** Receives a Unity that wants to move to this target.
         *
         * @param a The Move action.
         * @param u The Unity that wants to move.
         * @return true if the Unity moved.
         */
        def receive(a: Move, u:Unity): Boolean

        /** Receives the action of being consumed as a potion by a Character.
         *
         * @param a The Use_Potion action.
         * @param c The Character that consumes the target.
         * @return true if the potion was consumed.
         */
        def receive(a: Use_Potion, c: Character): Boolean


        /** Receives Thunder cast by a Black Mage.
         *
         * @param a The Thunder action.
         * @param m The Black Mage that casts Thunder.
         * @return true if the target received the spell.
         */
        def receive(a: Thunder, m: Black_Mage): Boolean

        /** Receives Meteor cast by a Black Mage.
         *
         * @param a The Meteor action.
         * @param m The Black Mage that casts Meteor.
         * @return true if the target received the spell.
         */
        def receive(a: Meteor, m: Black_Mage): Boolean

        /** Receives Heal cast by a White Mage.
         *
         * @param a The Heal action.
         * @param m The White Mage that casts Heal.
         * @return true if the Target was healed.
         */
        def receive(a: Heal, m: White_Mage): Boolean

        /** Receives Purification cast by a White Mage.
         *
         * @param a The Purification action.
         * @param m The White Mage that casts Purification.
         * @return true if the target received the spell.
         */
        def receive(a: Purification, m: White_Mage): Boolean

        /** Receives the action of being equipped by a Knight.
         *
         * @param a The Equip_Weapon action.
         * @param c The Knight that tries to equip the target.
         * @return true if the target was equipped.
         */
        def receive(a: Equip_Weapon, c: Knight): Boolean

        /** Receives the action of being equipped by a Archer.
         *
         * @param a The Equip_Weapon action.
         * @param c The Archer that tries to equip the target.
         * @return true if the target was equipped.
         */
        def receive(a: Equip_Weapon, c: Archer): Boolean

        /** Receives the action of being equipped by a Thieve.
         *
         * @param a The Equip_Weapon action.
         * @param c The Thieve that tries to equip the target.
         * @return true if the target was equipped.
         */
        def receive(a: Equip_Weapon, c: Thieve): Boolean

        /** Receives the action of being equipped by a Black_Mage.
         *
         * @param a The Equip_Weapon action.
         * @param c The Black_Mage that tries to equip the target.
         * @return true if the target was equipped.
         */
        def receive(a: Equip_Weapon, c: Black_Mage): Boolean

        /** Receives the action of being equipped by a White_Mage.
         *
         * @param a The Equip_Weapon action.
         * @param c The White_Mage that tries to equip the target.
         * @return true if the target was equipped.
         */
        def receive(a: Equip_Weapon, c: White_Mage): Boolean

        /** Receives the action of being consumed as a potion by a magic character.
         *
         * @param a The Use_Potion action.
         * @param m The magic character that consumes the target.
         * @return true if the potion was consumed and applied its effect.
         */
        def receive(a: Use_Potion, m: Magic_Character): Boolean