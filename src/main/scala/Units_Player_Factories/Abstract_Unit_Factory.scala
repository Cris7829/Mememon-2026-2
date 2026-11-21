package cl.uchile.dcc
package Units_Player_Factories

import Usable_Factories.*

import scala.collection.mutable.ListBuffer


abstract class Abstract_Unit_Factory extends Unit_Factory:
  
  protected var name: String

  protected var health: Int
 
  protected var defense: Int
  
  protected var weight: Int
  
  
  protected var armament: Option[Armament_Factory] = None
  
  protected var inventory: ListBuffer[Usable_Factory] = ListBuffer()
  
  
  def set_name(x: String): scala.Unit =
    name = x


  def set_health(x: Int): scala.Unit =
    if x > 0 then
      health = x


  def set_defense(x: Int): scala.Unit =
    if x >= 0 then
      defense = x

  
  def set_weight(x: Int): scala.Unit =
    if x > 0 then
      weight = x