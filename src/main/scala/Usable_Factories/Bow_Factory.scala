package cl.uchile.dcc
package Usable_Factories
import Usable.*
import Entities.Character

class Bow_Factory extends Abstract_Armament_Factory:

  protected var name: String = "Bow"
  
  protected var attack_points: Int = 20
  
  protected var weight: Int = 7

  
  def Create(): Bow = new Bow(name,attack_points,weight,owner)