package cl.uchile.dcc
package Exceptions

/** It follows the base GameException class and is thrown any time a method of the GameController
 * is called in a phase of the game that does not allow it.
 *
 * @param msg The message that will be shown once the exception has been thrown.
 * @constructor Creates the exception with the given message.
 */
class NotMethodOfPhase(msg:String) extends GameException(msg)
