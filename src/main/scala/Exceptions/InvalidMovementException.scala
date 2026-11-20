package cl.uchile.dcc
package Exceptions



/** It follows the base GameException class and is thrown any time a unit tries to move
 * to a panel that is not adjacent to its current panel.
 *
 * @param msg The message that will be shown once the exception has been thrown.
 * @constructor Creates the exception with the given message.
 */
class InvalidMovementException(msg: String) extends GameException(msg)
