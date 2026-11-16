package cl.uchile.dcc
package Exceptions



/** It follows the base GameException class and is used to be thrown any time that the action's source
 * doesn't have the necessary amount of maná to cast a spell.
 *
 *
 * Because the Mage character are the only one that can cast spells, this class has only usage there.
 *
 *
 * @param msg The message that will be shown once the exception has been thrown.
 * @constructor Creates the exception with a no real use other than the exceptions.
 */
class InvalidMovementException(msg: String) extends GameException(msg)
