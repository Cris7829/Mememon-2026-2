package cl.uchile.dcc
package Usable_Factories
import Usable.*


class Sword_Factory extends Abstract_Armament_Factory:

  protected var name: String = "Sword"
 
  protected var attack_points: Int = 25

  protected var weight: Int = 12
  
  override def Create(): Sword = new Sword(name, attack_points, weight, owner)
