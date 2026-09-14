package cl.uchile.dcc
package Entities

trait Usable_Armament
 def 

//Base mold for all the Armaments
abstract class Armament(n:String, a:Int, w:Int, o:String) extends Usable_Armament:
 protected val name: String = n
 protected var attack_points: Int = a
 protected var weight : Int = w
 protected var owner : String = o

  
//getter 
 def get_attack_points: Int = attack_points
 def get_weight: Int = weight
 def get_owner: String = owner
 def get_name: String = name

//Base mold for magic weapons such as Wand and Cane
class Magic_Weapon(n:String, a:Int, w:Int, o:String, m_a:Int ) extends Armament(n, a, w, o):
  protected var magic_attack_points: Int = m_a

//getter
  def get_magic_attack_points: Int = magic_attack_points



//Magic weapons with Magic-Weapons mold
class Cane (n:String, a:Int, w:Int, o:String, m_a:Int ) extends Magic_Weapon(n, a, w, o ,m_a)

class Wand (n:String, a:Int, w:Int, o:String, m_a:Int ) extends Magic_Weapon(n, a, w, o ,m_a)


//Non-Magic Weapons, they don't need mold other than Armaments
class Sword(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)

class Dagger(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)

class Bow(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)



