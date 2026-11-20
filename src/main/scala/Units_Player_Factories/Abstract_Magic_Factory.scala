package cl.uchile.dcc
package Units_Player_Factories
import Entities.*

abstract class Abstract_Magic_Factory extends Abstract_Character_Factory:

  protected var mana: Int
  
  def set_mana(x: Int): scala.Unit =
    if x >= 0 then
      mana = x


  override def Create(): Magic_Character




