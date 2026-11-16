package cl.uchile.dcc
package DesignPatterns
import Entities.*


/** An observer used only in the tests, it counts how many deaths it was notified of. */
class Counting_Observer extends Observer:

  /** A variable that has the count of enemies notified death. */
  var enemies_dead: Int = 0

  /** A variable that has the count of characters notified death. */
  var characters_dead: Int = 0

  /** Given an Enemy it adds 1 to the enemies_dead count. */
  def actualize_death(x: Enemy): scala.Unit = enemies_dead += 1

  /** Given an Character it adds 1 to the characters_dead count. */
  def actualize_death(x: Character): scala.Unit = characters_dead += 1
