package cl.uchile.dcc
package Actions



trait Action




abstract class Utility_Action extends Action:
   val name: String




class Use_Potion extends Utility_Action :
  val name: String = "Use Potion"

class Equip_weapon extends Utility_Action :
  val name: String = "Equip weapon"







abstract class Non_Utility_Action extends Action:
   val name: String




class Move extends Non_Utility_Action :
   override val name: String = "Move"


class Attack extends Non_Utility_Action :
  override  val name: String = "Attack"


class Thunder extends Non_Utility_Action:
  override val name: String = "Thunder"


class Meteor extends Non_Utility_Action:
  override val name: String = "Meteor"


class Heal extends Non_Utility_Action:
  override val name: String = "Heal"


class Purification extends Non_Utility_Action:
  override val name: String = "Purification"
