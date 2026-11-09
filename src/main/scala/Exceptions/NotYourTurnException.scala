package cl.uchile.dcc
package Exceptions

/** It follows the base GameException class and is used to be thrown any time in the turn program that a Source, 
 * specifically a Unity that wants to do an actions but, it is not his turn
 *
 *
 * @param msg The message that will be shown once the exception has been thrown.
 * @constructor Creates the exception with a no real use other than the exceptions.
 */
class NotYourTurnException(msg:String) extends GameException(msg)
