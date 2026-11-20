package cl.uchile.dcc
package Units_Player_Factories
import Entities.*

class Black_Mage_Factory extends Abstract_Magic_Factory:

  protected var name: String = "Black Mage"

  protected var health: Int = 70

  protected var defense: Int = 6
 
  protected var weight: Int = 10
  
  protected var mana: Int = 100

 
  override def Create(): Black_Mage = new Black_Mage(name, health, defense, weight, mana)
