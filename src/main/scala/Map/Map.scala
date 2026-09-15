package cl.uchile.dcc
package Map

import Entities.Unity
import scala.collection.mutable.ListBuffer


trait Map


class Panel(a:Int , b:Int) extends Map:

  private val x :Int=a
  private val y :Int=b

  private val units: ListBuffer[Unity] = ListBuffer()
  private val neighbors: ListBuffer[Panel] = ListBuffer()

  def get_x: Int = x
  def get_y: Int = y
  def get_units: ListBuffer[Unity] = units
  def get_neighbors: ListBuffer[Panel] = neighbors