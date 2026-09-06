package cl.uchile.dcc
package Entities

import scala.collection.mutable.ListBuffer

class Player(val name: String):
 protected val List_Units: ListBuffer[Unity] = ListBuffer()
 protected var isDefeated: Boolean = false