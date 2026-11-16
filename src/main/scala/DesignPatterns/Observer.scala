package cl.uchile.dcc
package DesignPatterns

import Entities.*


/** Following the Observer design pattern, it a trait representing the observers that will receive the
 * information when a Character or Enemy is death after an Action. The observer receives the class, and
 * does the methods needed to actualize the observer.
 *
 *
 * Main use is for the GameProgram, when Character or Enemy has died, its notified and he
 * removes the Unit from his lists.
 * */
trait Observer:

  /** Following the observer design pattern. This method receives an Enemy that his health is 0.
   *
   * The method, removes the Enemy form the enemies list, the panel that it on
   * and from the turn's program unit list.
   *
   *
   * After all that, using verify_end() method, if the players have won it set the who_won variable to
   * Some(true), if the enemies have won it set the variable to Some(false). If nobody has won this part, does nothing.
   *
   * @param x The Enemy.
   */
  def actualize_death(x: Enemy): scala.Unit

  /** Following the observer design pattern. This method receives an Enemy that his health is 0.
   *
   * The method, removes the Character form the player's unit list, the panel that it on
   * and from the turn's program unit list.
   *
   * After all that, using verify_end() method, if the players have won it set the who_won variable to
   * Some(true), if the enemies have won it set the variable to Some(false). If nobody has won this part, does nothing.
   *
   * @param x The Character.
   */
  def actualize_death(x: Character): scala.Unit
