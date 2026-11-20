package cl.uchile.dcc
package Units_Player_Factories
import Entities.*

class Knight_Factory extends Abstract_Unit_Factory:

  protected var name: String = "Knight"

  protected var health: Int = 120

  protected var defense: Int = 15

  protected var weight: Int = 20


  override def Create(): Knight = new Knight(name, health, defense, weight)
