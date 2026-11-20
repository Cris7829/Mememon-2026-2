package cl.uchile.dcc
package Entities_Factories
import scala.util.Random


class Random_Class(val min: Int, val max: Int):


  def roll(x: Random): Int = min + x.nextInt(max - min + 1)


  def contains(x: Int): Boolean = min <= x && x <= max
