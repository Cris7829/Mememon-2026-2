package cl.uchile.dcc
package Units_Player_Factories
import Entities.*

class Thieve_Factory extends Abstract_Character_Factory:

  protected var name: String = "Thieve"

  protected var health: Int = 80

  protected var defense: Int = 8

  protected var weight: Int = 8


  override def Create(): Thieve = new Thieve(name, health, defense, weight)
