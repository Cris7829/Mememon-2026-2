package cl.uchile.dcc
package Actions

import Entities.{Usable_Armament, Usable_Potion}


import scala.collection.mutable.ListBuffer

trait Action




abstract class Utility_Action extends Action:
   val name: String




class Use_Potion extends Utility_Action :
  val name: String = "Use Potion"
  var usable_list :  ListBuffer[Usable_Potion] = ListBuffer()


class Equip_weapon extends Utility_Action :
  val name: String = "Equip weapon"
  var usable_list :  ListBuffer[Usable_Armament] = ListBuffer()







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
