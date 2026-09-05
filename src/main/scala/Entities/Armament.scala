package cl.uchile.dcc
package Entities

//Base mold for all the Armaments
abstract class Armament(n:String, a:Int, w:Int):
 protected val name: String = n
 protected var attack_points: Int = a
 protected var weight : Int = w

//Base mold for magic weapons such as Wand and Cane
class Magic_Weapon(n:String, a:Int, w:Int, m_a:Int ) extends Armament(n, a, w):
  protected var magic_attack_points: Int = m_a



//Magic weapons with Magic-Weapons mold
class Cane (n:String, a:Int, w:Int, m_a:Int ) extends Magic_Weapon(n, a, w, m_a)

class Wand (n:String, a:Int, w:Int, m_a:Int ) extends Magic_Weapon(n, a, w, m_a)


//Non-Magic Weapons, they don't need mold other than Armaments
class Sword(n:String, a:Int, w:Int) extends Armament(n ,a ,w)

class Dagger(n:String, a:Int, w:Int) extends Armament(n ,a ,w)

class Bow(n:String, a:Int, w:Int) extends Armament(n ,a ,w)



