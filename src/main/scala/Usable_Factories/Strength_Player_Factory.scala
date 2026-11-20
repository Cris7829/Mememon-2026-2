package cl.uchile.dcc
package Usable_Factories
import Usable.*

class Strength_Player_Factory extends Abstract_Potion_Factory:

  protected var name: String = "Strength"

  override def Create(): Strength_Player = new Strength_Player(name)
