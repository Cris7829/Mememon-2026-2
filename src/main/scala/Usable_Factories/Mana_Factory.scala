package cl.uchile.dcc
package Usable_Factories
import Usable.*

class Mana_Factory extends Abstract_Potion_Factory:

  protected var name: String = "Mana"
  
  override def Create(): Mana = new Mana(name)