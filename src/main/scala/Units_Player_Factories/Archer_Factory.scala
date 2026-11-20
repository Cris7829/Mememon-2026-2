package cl.uchile.dcc
package Units_Player_Factories
import Entities.*

class Archer_Factory extends Abstract_Character_Factory:

  protected var name: String = "Archer"

  protected var health: Int = 90

  protected var defense: Int = 10

  protected var weight: Int = 12

 
  override def Create(): Archer = new Archer(name, health, defense, weight)