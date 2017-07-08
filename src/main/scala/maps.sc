// Map[Key, Value]

val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)
val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")


// Maps are Iterables
val countryOfCapital = capitalOfCountry map {
  case(x, y) => (y, x)
} // Map("Washington"" -> "US", "Bern" -> "Switzerland")


// Maps are Functions
// Class Map[Key, Value] also extend the function type Key => Value,
// so maps can be used everywhere functions can.

"I".->(1)

// This map lookup looks like a function call
capitalOfCountry("US")

// Querying Map
// capitalOfCountry("Andorra")
// java.util.NoSuchElementException: key not found: Andorra

// Use get
capitalOfCountry get "US" // Some("Washington")
capitalOfCountry get "Andorra" // None

// The result of a get operation is an Option value

// The Option Type
/*

trait Option[+A]
case class Some[+A](value: A) extends Option[A]
object None extends Option[Nothing]

*/

// The expression map get key returns
// > None
// > Some(x)

// Decomposing Option

def showCapital(country: String) = capitalOfCountry.get(country) match {
  case Some(capital) => capital
  case None => "missing data"
}

showCapital("US")
showCapital("AAA")


// Sorted and GroupBy

val fruit = List("apple", "pear", "orange", "pineapple")
fruit sortWith (_.length < _.length) // List("pear", "apple", "orange", "pineapple")
fruit.sorted // List("apple", "orange", "pear", "pineapple")

// head is the first character
fruit groupBy (_.head)
//> Map(p -> List(pear, pineapple),
//| a -> List(apple),
//| o -> List(orange))

// Example
// x^3 − 2x + 5 can be represented with the map
// Map(0 -> 5, 1 -> 2, 3 -> 1)
//

class Poly(val terms0: Map[Int, Double]) {
  val terms = terms0 withDefaultValue 0.0

  def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))

  def adjust(term: (Int, Double)): (Int, Double) = {
    val (exp, coeff) = term
    exp -> (coeff + terms(exp))

    /*
    // without default value
    terms get exp match {
      case Some(coeff1) => exp -> (coeff + coeff1)
      case None => exp -> coeff
    }
    */
  }

  override def toString =
    (for ((exp, coeff) <- terms.toList.sorted.reverse)
      yield coeff + "x^" + exp) mkString " + "
}

val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
// 6.2x^5 + 4.0x^3 + 2.0x^1

val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))
// 7.0x^3 + 3.0x^0


// def + (other: Poly) = new Poly(terms ++ other.terms)
p1 + p2
// 6.2x^5 + 7.0x^3 + 2.0x^1 + 3.0x^0
// is wrong, should be 11.0x^3
// need to sum coefficients

// Correct after adding adjust !
// Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0

// Default Values
// So far, maps were partial functions: Applying a map to a key value
// in map(key) could lead to an exception

// withDefaultValue turns a map into a total function
val cap1 = capitalOfCountry withDefaultValue "<unknown>"
cap1("Andorra") // "<unknown>"

// val terms = terms0 withDefaultValue 0.0
p1.terms(7)


// Variable Length Argument Lists

// It's inconvenient to write
new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))

// Repeated parameters *
def Polynom(bindings: (Int, Double)*) =
  new Polynom(bindings.toMap withDefaultValue 0)


class Polynom(val terms0: Map[Int, Double]) {

  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue 0.0

  def + (other: Polynom) =
    new Polynom((other.terms foldLeft terms)(addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
    val (exp, coeff) = term
    terms + (exp -> (coeff + terms(exp)))
  }

  override def toString =
    (for ((exp, coeff) <- terms.toList.sorted.reverse)
      yield coeff + "x^" + exp) mkString " + "
}

// Need a way to pass variable length
val p = new Polynom(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)

val p3 = new Polynom(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)

val p4 = new Polynom(0 -> 3.0, 3 -> 7.0)

p3 + p4

// Which version is more efficient?
//
// The version using ++
// (v) The version using foldLeft

/*
Each of these bindings will be immediately added to our terms Maps so, we build up
the result directly, whereas before, we would create another list of terms
that contain the adjusted terms and then we would concatenate this list to
the original one.

new Poly(terms ++ (other.terms map adjust))
new Polynom((other.terms foldLeft terms)(addTerm))

So the version with foldLeft avoids this creation of
the intermediate list data structure.
 */