package cl.uchile.dcc
package Entities

trait Usable_Armament


abstract class Armament(n:String, a:Int, w:Int, o:String) extends Usable_Armament:
 val name: String = n
 protected var attack_points: Int = a
 val weight : Int = w
 protected var owner : String = o


 def get_attack_points: Int = attack_points
 def get_owner: String = owner


 def set_attack_points(x: Int): scala.Unit =
   if x >= 0 then
     attack_points = x


 def set_owner(x: String): scala.Unit=
   owner = x




abstract class Magic_Weapon(n: String, a: Int, w: Int, o: String, m_a: Int) extends Armament(n, a, w, o):
  protected var magic_attack_points: Int = m_a


  def get_magic_attack_points: Int = magic_attack_points

  def set_magic_attack_points(x: Int): scala.Unit =
    if x >= 0 then
      magic_attack_points = x



//Magic weapons with Magic-Weapons mold
class Cane (n:String, a:Int, w:Int, o:String, m_a:Int ) extends Magic_Weapon(n, a, w, o ,m_a)

class Wand (n:String, a:Int, w:Int, o:String, m_a:Int ) extends Magic_Weapon(n, a, w, o ,m_a)


//Non-Magic Weapons, they don't need mold other than Armaments
class Sword(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)

class Dagger(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)

class Bow(n:String, a:Int, w:Int, o:String) extends Armament(n ,a ,w ,o)



