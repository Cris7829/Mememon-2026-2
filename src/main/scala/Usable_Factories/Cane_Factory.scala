package cl.uchile.dcc
package Usable_Factories
import Usable.*

class Cane_Factory extends Abstract_Magic_Armament_Factory:
  
  protected var name: String = "Cane"

  protected var attack_points: Int = 12

  protected var weight: Int = 8

  protected var magic_attack_points: Int = 30


  override def Create(): Cane = new Cane(name, attack_points, weight, owner, magic_attack_points)
  