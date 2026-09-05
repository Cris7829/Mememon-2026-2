package cl.uchile.dcc
package Entities

//Molde base de todos los armamentos
abstract class Armament(n:String, a:Int, w:Int):
 protected val name: String = n
 protected var attack_points: Int = a
 protected var weight : Int = w
 
//Molde de armas magicas
class Magic_Weapon(n:String, a:Int, w:Int, m_a:Int ) extends Armament(n, a, w):
  protected var magic_attack_points: Int = m_a



//Armas magicas
class Cane (n:String, a:Int, w:Int, m_a:Int ) extends Magic_Weapon(n, a, w, m_a)

class Wand (n:String, a:Int, w:Int, m_a:Int ) extends Magic_Weapon(n, a, w, m_a)


//Armas no magicas
class Sword(n:String, a:Int, w:Int) extends Armament(n ,a ,w)
  
class Dagger(n:String, a:Int, w:Int) extends Armament(n ,a ,w)
  
class Bow(n:String, a:Int, w:Int) extends Armament(n ,a ,w)
  
  
  
  