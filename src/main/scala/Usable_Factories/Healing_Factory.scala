package cl.uchile.dcc
package Usable_Factories
import Usable.*

class Healing_Factory extends Abstract_Potion_Factory:

  protected var name: String = "Healing"

 
  override def Create(): Healing = new Healing(name)
