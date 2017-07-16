// For-Expressions and Higher-Order Functions
/*
  The syntax of for is closely related to the higher-order functions
  map, flatMap and filter.
  First of all, these functions can all be defined in terms of for:

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  for (x <- xs) yield f(x)

def flatMap[T, U](xs: List[T], f: T => Iterable[U]): List[U] =
  for (x <- xs; y <- f(x)) yield y

def filter[T](xs: List[T], p: T => Boolean): List[T] =
  for (x <- xs if p(x)) yield x
*/

// In reality, the Scala compiler expresses for-expressions in terms of
// map, flatMap and a lazy variant of filter.


// Exercise
case class Book(title: String, authors: List[String])

val books: Set[Book] = Set(
  Book(title = "Structure and Interpretation of Computer Programs",
    authors = List("Abelson, Harald", "Sussman, Gerald J.")),
  Book(title = "Introduction to Functional Programming",
    authors = List("Bird, Richard", "Wadler, Phil")),
  Book(title = "Effective Java",
    authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers",
    authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Programming in Scala",
    authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")))


// Translate
for (b <- books; a <- b.authors if a startsWith "Bird")
yield b.title
// into higher-order functions

// Answer:
// Step 1
books flatMap (b =>
  for (a <- b.authors if a startsWith "bird") yield b.title)

// Step 2
books flatMap (b =>
  for (a <- b.authors.withFilter(a => a startsWith "bird")) yield b.title)

// Step 3
books flatMap (b =>
  b.authors.withFilter(a => a startsWith "bird") map (y => b.title))


// Generalization of for
/*
Interestingly, the translation of for is not limited to lists or
  sequences, or even collections;
It is based solely on the presence of the methods map, flatMap and
  withFilter.
    This lets you use the for syntax for your own types as well – you
must only define map, flatMap and withFilter for these types.
There are many types for which this is useful: arrays, iterators,
databases, XML data, optional values, parsers, etc.
*/

// For and Databases
/*
For example, books might not be a list, but a database stored on
some server.
As long as the client interface to the database defines the methods
  map, flatMap and withFilter, we can use the for syntax for querying
the database.
This is the basis of the Scala data base connection frameworks
ScalaQuery and Slick.
  Similar ideas underly Microsoft’s LINQ.
*/