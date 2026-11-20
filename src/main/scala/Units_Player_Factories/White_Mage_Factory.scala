package cl.uchile.dcc
package Units_Player_Factories
import Entities.*

class White_Mage_Factory extends Abstract_Magic_Factory:

  protected var name: String = "White Mage"

  protected var health: Int = 75

  protected var defense: Int = 7

  protected var weight: Int = 10

  protected var mana: Int = 110 


  override def Create(): White_Mage = new White_Mage(name, health, defense, weight, mana)
