package cl.uchile.dcc
package Usable_Factories
import Entities.Character

abstract class Abstract_Armament_Factory extends Armament_Factory:
  
  protected var name: String 
  protected var attack_points: Int 
  protected var weight: Int
  protected var owner: Option[Character] = None
  


  def set_name(x: String): scala.Unit =
    name = x

  def set_attack_points(x: Int): scala.Unit =
    if x > 0 then
      attack_points = x

  def set_weight(x: Int): scala.Unit =
    if x >= 0 then
      weight = x

  def set_owner(x: Option[Character]): scala.Unit =
    if x.isDefined then
      owner = x

      
      

