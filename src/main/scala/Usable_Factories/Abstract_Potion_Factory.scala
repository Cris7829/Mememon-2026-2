package cl.uchile.dcc
package Usable_Factories

abstract class Abstract_Potion_Factory extends Potion_Factory:

  protected var name: String
  
  def set_name(x: String): scala.Unit =
    name = x
