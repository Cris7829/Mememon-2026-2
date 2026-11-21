package cl.uchile.dcc
package Units_Player_Factories
import Entities.*
import Actions.*


class Thieve_Factory extends Abstract_Character_Factory:

  protected var name: String = "Thieve"

  protected var health: Int = 80

  protected var defense: Int = 8

  protected var weight: Int = 8



  override def Create(): Thieve =
    val x: Thieve = new Thieve(name, health, defense, weight)

    for y <- inventory do
      x.get_inventory += y.Create()

    if armament.isDefined then
      x.Do(Equip_Weapon(), armament.get.Create())

    x
