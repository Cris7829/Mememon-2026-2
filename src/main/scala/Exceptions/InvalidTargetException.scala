package cl.uchile.dcc
package Exceptions

/** It follows the base GameException class and is used to be thrown any time that Target cannot receive
 * an action.
 *
 * Following the game, rules, not any Target can receive any actions.
 *
 *
 * @param msg The message that will be shown once the exception has been thrown.
 * @constructor Creates the exception with a no real use other than the exceptions.
 */
class InvalidTargetException(msg: String) extends GameException(msg) 
