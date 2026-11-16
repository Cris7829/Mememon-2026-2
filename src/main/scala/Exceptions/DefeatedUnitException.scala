package cl.uchile.dcc
package Exceptions

/** It follows the base GameException class and is used to be thrown any time that an action is has 
 * dead Target or Source.
 *
 *
 * @param msg The message that will be shown once the exception has been thrown.
 * @constructor Creates the exception with a no real use other than the exceptions.
 */
class DefeatedUnitException(msg: String) extends GameException(msg)
