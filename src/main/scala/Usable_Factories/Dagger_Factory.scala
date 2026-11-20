package cl.uchile.dcc
package Usable_Factories
import Usable.*

class Dagger_Factory extends Abstract_Armament_Factory:

  protected var name: String = "Dagger"

  protected var attack_points: Int = 15

  protected var weight: Int = 4

  
  override def Create(): Dagger = new Dagger(name, attack_points, weight, owner)

