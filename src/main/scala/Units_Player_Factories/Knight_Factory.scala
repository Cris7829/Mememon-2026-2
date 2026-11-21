package cl.uchile.dcc
package Units_Player_Factories
import Entities.*
import Actions.*

class Knight_Factory extends Abstract_Unit_Factory:

  protected var name: String = "Knight"

  protected var health: Int = 120

  protected var defense: Int = 15

  protected var weight: Int = 20

  
  override def Create(): Knight =
    val x: Knight = new Knight(name, health, defense, weight)

    for y <- inventory do
      x.get_inventory += y.Create()

    if armament.isDefined then
      x.Do(Equip_Weapon(), armament.get.Create())

    x
