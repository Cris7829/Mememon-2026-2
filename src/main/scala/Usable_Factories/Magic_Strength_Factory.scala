package cl.uchile.dcc
package Usable_Factories
import Usable.*

class Magic_Strength_Factory extends Abstract_Potion_Factory:

  protected var name: String = "Magic Strength"

  override def Create(): Magic_Strength = new Magic_Strength(name)
