package cl.uchile.dcc
package Exceptions

/** It follows the base GameException class and is used to be thrown any time that a Source doesn't have
 * a requirement armament to do the action that wanna do.
 *
 *
 * @param msg The message that will be shown once the exception has been thrown.
 * @constructor Creates the exception with a no real use other than the exceptions.
 */
class InvalidWeaponException(msg: String) extends GameException(msg)
