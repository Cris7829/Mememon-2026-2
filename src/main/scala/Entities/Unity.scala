package cl.uchile.dcc
package Entities

import scala.collection.mutable.ListBuffer

trait Unity


abstract class Unit (n:String, h:Int, d:Int, w:Int):
  protected val name: String = n
  protected var health: Int = h
  protected var defense: Int = d
  protected var weight: Int = w


  def get_name: String = name
  def get_health: Int = health
  def get_defense: Int = defense
  def get_weight: Int = weight



class Enemies(n:String, h:Int, d:Int, w:Int, a:Int) extends unit(n:String, h:Int, d:Int, w:Int):
 protected var attack:Int=a


  
 def get_attack: Int = attack


class Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Unit(n:String, h:Int, d:Int, w:Int):
 protected var weapon : Option[Usable_Armament] = we
 protected val inventory: ListBuffer[Usable_Potion] = ListBuffer()


 def get_weapon: Option[Usable_Armament] = weapon
 def get_inventory: ListBuffer[Usable_Potion] = inventory

 def this(n:String, h:Int, d:Int, w:Int) =
  this(n, h, d, w ,Option.empty[Usable_Armament])



class Non_Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament])


class Thieve(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Non_Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament])
class Knight (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Non_Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament])
class Archer (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Non_Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament])






class Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
 protected var mana: Int = m
 

 def get_mana: Int = mana


class Black_Mage (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int)
class White_Mage (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int)







