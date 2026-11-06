package cl.uchile.dcc
package Usable

import Action_Sistem.Target

import Actions.{Attack, Equip_Weapon, Heal, Meteor, Move, Purification, Thunder, Use_Potion}
import Entities.{Black_Mage, Character, Enemy, Unity, White_Mage}


/** A trait that represents al the usable objects that a player's Unit can use, such as potions and armaments*/
trait Utilizable extends Target:

  def receive(a: Attack, attacker: Character): Boolean

  def receive(a: Attack, attacker: Enemy): Boolean

  def receive(a: Move, u: Unity): Boolean

  def receive(a: Equip_Weapon, c: Character): Boolean

  def receive(a: Use_Potion, c: Character): Boolean


  def receive(a: Thunder, m: Black_Mage): Boolean

  def receive(a: Meteor, m: Black_Mage): Boolean

  def receive(a: Heal, m: White_Mage): Boolean

  def receive(a: Purification, m: White_Mage): Boolean