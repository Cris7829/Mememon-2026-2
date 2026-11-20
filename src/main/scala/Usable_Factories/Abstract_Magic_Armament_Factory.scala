package cl.uchile.dcc
package Usable_Factories

abstract class Abstract_Magic_Armament_Factory extends Abstract_Armament_Factory:
  
  protected var magic_attack_points:Int 
  
  def set_magic_attack(x: Int): scala.Unit =
    if x > 0 then
      magic_attack_points = x




