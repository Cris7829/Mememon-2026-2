package cl.uchile.dcc
package Entities

trait Usable_Potion

//Base mold
abstract class Potion (n:String) extends Usable_Potion:
  protected val name: String=n



//Types of potions using Potion mold
class Healing (n:String) extends Potion(n)

class Mana (n:String) extends Potion(n)

class Magic_Strength (n:String) extends Potion(n)

class Strength_Player (n:String) extends Potion(n)