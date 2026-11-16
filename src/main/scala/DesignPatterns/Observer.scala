package cl.uchile.dcc
package DesignPatterns

import Entities.*



trait Observer:

  def actualize_death(x: Enemy): scala.Unit


  def actualize_death(x: Character): scala.Unit
