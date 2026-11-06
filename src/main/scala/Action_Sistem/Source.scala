package cl.uchile.dcc
package Action_Sistem
import Entities.*
import Actions.*

import scala.collection.mutable.ListBuffer

trait Source:
  
  val name:String
  
  
  val actions_possible: ListBuffer[Action]

  
  
  def find_action(id: String): Option[Action]
  
  
  def doAction(action: Action, target: Target):Boolean

  
  
  def Do(a: Attack, t: Target): Boolean 

  def Do(a: Move, t: Target): Boolean
  
  def Do(a: Equip_Weapon, t: Target): Boolean 

  def Do(a: Use_Potion, t: Target): Boolean 

  def Do(a: Thunder, t: Target): Boolean 

  def Do(a: Meteor, t: Target): Boolean

  def Do(a: Heal, t: Target): Boolean 

  def Do(a: Purification, t: Target): Boolean 
 
  