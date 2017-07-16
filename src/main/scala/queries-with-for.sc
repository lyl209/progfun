case class Book(title: String, authors: List[String])

val books: List[Book] = List(
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

// To find the titles of books whose author’s name is “Bird":
for (b <- books; a <- b.authors if a startsWith "Bird,")
  yield b.title

// To find all the books which have the word “Program" in the title:
for (b <- books if (b.title indexOf "Program") >= 0)
  yield b.title


// To find the names of all authors who have written at least two
// books present in the database.

for {
  b1 <- books
  b2 <- books
  if b1 != b2
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1


// Why do solutions show up twice? Two generators b1 and b2
// ("Effective Java", "Java Puzzles")
// ("Java Puzzles", "Effective Java")

// How can we avoid this?
for {
  b1 <- books
  b2 <- books
  if b1.title < b2.title // fix here
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1

// So we only have ("Effective Java", "Java Puzzles")

// What happens if an author has published three books?
// The author is printed three times!

{ for {
  b1 <- books
  b2 <- books
  if b1.title < b2.title
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1
}.distinct


// Better alternative: Compute with sets instead of sequences:
val bookSet = books.toSet
for {
  b1 <- bookSet
  b2 <- bookSet
  if b1 != b2
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1
