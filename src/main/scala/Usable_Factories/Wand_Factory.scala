package cl.uchile.dcc
package Usable_Factories
import Usable.*


class Wand_Factory extends Abstract_Magic_Armament_Factory:
  
  protected var name: String = "Wand"

  protected var attack_points: Int = 10

  protected var weight: Int = 4
 
  protected var magic_attack_points: Int = 25

 
  override def Create(): Wand = new Wand(name, attack_points, weight, owner, magic_attack_points)
