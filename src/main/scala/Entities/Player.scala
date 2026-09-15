package cl.uchile.dcc
package Entities

import scala.collection.mutable.ListBuffer

trait Player


class Players(val name: String) extends Player:
 protected val List_Units: ListBuffer[Unity] = ListBuffer()
 protected var isDefeated: Boolean = false