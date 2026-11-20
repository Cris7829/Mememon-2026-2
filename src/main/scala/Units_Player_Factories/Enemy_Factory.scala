package cl.uchile.dcc
package Units_Player_Factories
import Entities.*

class Enemy_Factory extends Abstract_Unit_Factory:

  protected var name: String = "Enemy"

  protected var health: Int = 100

  protected var defense: Int = 8

  protected var weight: Int = 15

  protected var attack: Int = 25

 
  def set_attack(x: Int): scala.Unit =
    if x >= 0 then
      attack = x

  override def Create(): Enemy = new Enemy(name, health, defense, weight, attack)