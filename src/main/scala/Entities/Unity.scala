package cl.uchile.dcc
package Entities

import scala.collection.mutable.ListBuffer


/** A  trait representing any Unity that is in the game such as characters and enemies */
trait Unity


/** An abstract base class representing a general Unit.
 *
 * This class defines the core attributes shared by all units such as character being used by a player
 * and the characters that used the enemies. It has the name, health, defense and weight.
 * .
 *
 * @param n The name of the Unit.
 * @param h The health points of the Unit.
 * @param d The defense points of the Unit
 * @param w The weight of the Unit .
 *
 * @constructor Creates a new general Unit with the specified basic attributes.
 */
abstract class Unit (n:String, h:Int, d:Int, w:Int) extends Unity:
  /** A non-mutable variable for the name of the Unit. */
  val name: String = n

  /** A variable for the health of the Unit. */
  protected var health: Int = h

  /** A variable for the defense points of the Unit. */
  protected var defense: Int = d

  /** A variable for the defense points of the Unit. For the moment this variable may change during the combat*/
  protected var weight: Int = w


  /** Retrieves the health points of the Unit.*/
  def get_health: Int = health

  /** Retrieves the defense points of the Unit.*/
  def get_defense: Int = defense

  /** Retrieves the weight points of the Unit.*/
  def get_weight: Int = weight


/** A class representing a general Unit that can do damage to the player's Unit.
 * .
 *It follows the base core attributes of the Unit abstract class an adds an attack points, because they don't
 * use armament of potions, they just do damage.
 *
 * @param n The name of the Enemies.
 * @param h The health points of the Enemy.
 * @param d The defense points of the Enemy.
 * @param w The weight of the Enemies .
 * @param a The attack points of the Enemy .
 *
 * @constructor Creates a new general Enemy Unit with the specified basic attributes.
 */
class Enemy(n:String, h:Int, d:Int, w:Int, a:Int) extends Unit(n:String, h:Int, d:Int, w:Int):
  /** A private variable with the attack points of the Enemy.*/
  private var base_attack:Int = a

  /** Retrieves the attack points of the Enemy.*/
  def get_attack: Int = base_attack



/** A class representing a general Unit that can be used by a player.
 * .
 *It follows the base core attributes of the Unit abstract class an adds a slot to equip an armament and
 * inventory for armaments and potions
 *
 * @param n The name of the Character.
 * @param h The health points of the Character.
 * @param d The defense points of the Character.
 * @param w The weight of the Character.
 * @param we The slot for the equipped armament of the Character.
 *
 * @constructor Creates a new general Character with the specified basic attributes.
 */
class Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Unit(n:String, h:Int, d:Int, w:Int):
 /** A variable that can be scala.Unit or and actual armament if the Character has one equipped*/
 protected var weapon : Option[Usable_Armament] = we
 /** A variable that represents the inventory of utilizable.*/
 protected val inventory: ListBuffer[Utilizable] = ListBuffer()
 /** Retrieves the armament that its equipped.*/
 def get_weapon: Option[Usable_Armament] = weapon
 /** Retrieves the inventory.*/
 def get_inventory: ListBuffer[Utilizable] = inventory



/** A class representing a Thieve, a player's playable character.
 *
 * @param n The name of the Thieve.
 * @param h The health points of the Thieve.
 * @param d The defense points of the Thieve.
 * @param w The weight of the Thieve.
 * @param we The slot for the equipped armament of the Thieve.
 *
 * @constructor Creates a new general Thieve with the specified basic attributes and its equipped armament.
 */
class Thieve(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
  /** Auxiliary constructor to create a Thieve without an initial equipped armament
   *
   * @param n The name of the Thieve.
   * @param h The health points of the Thieve.
   * @param d The defense points of the Thieve.
   * @param w The weight of the Thieve.
   *
  */
  def this(n:String, h:Int, d:Int, w:Int) =
   this(n, h, d, w ,Option.empty[Usable_Armament])


/** A class representing a Knight, a player's playable character.
 *
 * @param n The name of the Knight.
 * @param h The health points of the Knight.
 * @param d The defense points of the Knight.
 * @param w The weight of the Knight.
 * @param we The slot for the equipped armament of the Knight.
 *
 * @constructor Creates a new general Thieve with the specified basic attributes and its equipped armament.
 */
class Knight (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
  /** Auxiliary constructor to create a Knight without an initial equipped armament
   *
   * @param n The name of the Knight.
   * @param h The health points of the Knight.
   * @param d The defense points of the Knight.
   * @param w The weight of the Knight.
   *
   */
  def this(n:String, h:Int, d:Int, w:Int) =
   this(n, h, d, w ,Option.empty[Usable_Armament])




/** A class representing a Archer, a player's playable character.
 *
 * @param n The name of the Archer.
 * @param h The health points of the Archer.
 * @param d The defense points of the Archer.
 * @param w The weight of the Archer.
 * @param we The slot for the equipped armament of the Archer.
 *
 * @constructor Creates a new general Thieve with the specified basic attributes and its equipped armament.
 */
class Archer (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
   /** Auxiliary constructor to create an Archer without an initial equipped armament
    *
    * @param n The name of the Archer.
    * @param h The health points of the Archer.
    * @param d The defense points of the Archer.
    * @param w The weight of the Archer.
    *
    */
   def this(n: String, h: Int, d: Int, w: Int) =
     this(n, h, d, w, Option.empty[Usable_Armament])




/** A class representing a general magic type unit that can be used by a player.
 * .
 *It follows the base core attributes of the Character class an adds a varaible for the mana bar, usable to cast
 * spells or use magic armaments
 *
 * @param n The name of the Magic Character.
 * @param h The health points of the Magic Character.
 * @param d The defense points of the Magic Character.
 * @param w The weight of the Magic Character.
 * @param we The slot for the equipped armament of the Magic Character.
 * @param m The mana bar of the Magic Character.
 *
 * @constructor Creates a new general Magic Character with the specified basic attributes.
 */
class Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament]):
 private var mana: Int = m

  /** Retrieves the current mana points of the Magic Character.
   *
   * @return The current mana points.
   */
 def get_mana: Int = mana

  /** Updates the mana points of the Magic Character.
   *
   * This method ensures that the mana points cannot drop below zero. If a negative
   * value is passed, the method does nothing.
   *
   * @param x The new mana points.
   */
 def set_mana(x:Int): scala.Unit = 
   if x>=0 then
    mana=x



/** A class representing a Black Mage, a type of Magic Character.
 * .
 *It follows the base core attributes of the Magic_Character class.
 *
 * @param n The name of the Black Mage.
 * @param h The health points of the Black Mage.
 * @param d The defense points of the Black Mage.
 * @param w The weight of the Black Mage.
 * @param we The slot for the equipped armament of the Black Mage.
 * @param m The mana bar of the Black Mage.
 *
 * @constructor Creates a new general Black Mage with the specified basic attributes and its name.
 */
class Black_Mage (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int):
  /** Auxiliary constructor to create a Black Mage without an initial equipped armament
   *
   * @param n The name of the Black Mage.
   * @param h The health points of the Black Mage.
   * @param d The defense points of the Black Mage.
   * @param w The weight of the Black Mage.
   * @param m The mana bar of the Black Mage.
   */
  def this(n: String, h: Int, d: Int, w: Int, m:Int) =
    this(n, h, d, w, Option.empty[Usable_Armament],m)


/** A class representing a White Mage, a type of Magic Character.
 * .
 *It follows the base core attributes of the Magic_Character class.
 *
 * @param n The name of the White Mage.
 * @param h The health points of the White Mage.
 * @param d The defense points of the White Mage.
 * @param w The weight of the White Mage.
 * @param we The slot for the equipped armament of the White Mage.
 * @param m The mana bar of the White Mage.
 *
 * @constructor Creates a new general White Mage with the specified basic attributes and its name.
 */
class White_Mage (n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int) extends Magic_Character(n:String, h:Int, d:Int, w:Int, we:Option[Usable_Armament], m:Int):
  /** Auxiliary constructor to create a White Mage without an initial equipped armament
   *
   * @param n The name of the White Mage.
   * @param h The health points of the White Mage.
   * @param d The defense points of the White Mage.
   * @param w The weight of the White Mage.
   * @param m The mana bar of the White Mage.
   */
  def this(n: String, h: Int, d: Int, w: Int, m:Int) =
    this(n, h, d, w, Option.empty[Usable_Armament],m)






