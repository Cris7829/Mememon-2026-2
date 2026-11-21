package cl.uchile.dcc
package Units_Player_Factories
import Entities.*

import scala.collection.mutable.ListBuffer


class Player_Factory:

  protected var name: String = "Player"

  protected val character_factories: ListBuffer[Abstract_Character_Factory] = ListBuffer()


  def set_name(x: String): scala.Unit =
    name = x


  def add_character_factory(x: Abstract_Character_Factory): scala.Unit =
    character_factories += x


  def remove_character_factory(x: Abstract_Character_Factory): scala.Unit =
    if character_factories.contains(x) then
      character_factories -= x


  def Create(): Players =
    val player = new Players(name)
    for f <- character_factories do
      player.add_units(f.Create())
    player
