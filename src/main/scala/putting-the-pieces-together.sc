// Phone keys have mnemonics assigned to them.

val mnemonics = Map(
'2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
'6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

/*
Design a method translate such that

  translate(phoneNumber)

produces all phrases of words that can serve as mnemonics for the
phone number.

Example: The phone number “7225247386” should have the
mnemonic 'Scala is fun' as one element of the set of solution
phrases.
*/


/*
Background

This example was taken from:
Lutz Prechelt: An Empirical Comparison of Seven
Programming Languages. IEEE Computer 33(10): 23-29
(2000)

Tested with Tcl, Python, Perl, Rexx, Java, C++, C.
Code size medians:
  ▶ 100 lines of code (loc) for scripting languages
  ▶ 200-300 loc for the others

*/

import scala.io.Source
var in = Source.fromURL("https://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")

var words = in.getLines.toList filter (word => word forall (chr => chr.isLetter))

/*
val mnemonics = Map(
'2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
'6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")
*/

//charCode: Map[Char,Char] = Map(E -> 3, X -> 9, N -> 6, T -> 8, Y -> 9, J -> 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G -> 4, V -> 8, Q -> 7, L -> 5, B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 5, R -> 7, O -> 6, D -> 3, Z -> 9, S -> 7)

// Invert the mnem map to give a map from chars 'A' ... 'Z' to '2' ... '9'
val charCode: Map[Char, Char] =
  for ((digit, str) <- mnemonics; ltr <- str) yield ltr -> digit


//test: Map[Char,Char] = Map(T -> 8, J -> 5, A -> 2, M -> 6, G -> 4, P -> 7, W -> 9, D -> 3)
val test: Map[Char, Char] =
  for ((d, s) <- mnemonics) yield s.head -> d

// Maps a word to the digit string it can represent, e.g. "Java" -> "5282"
def wordCode(word: String): String =
  word.toUpperCase map charCode

wordCode("Java")

"Java".toUpperCase() map charCode


/**
  * A map from digit strings to the words that represent them,
  * e.g. "5282" -> List("Java", "Kata", "Lava", ...)
  * Note: A missing number should map to th empty set,
  * e.g. "1111" -> List()
  */
val wordsForNum: Map[String, Seq[String]] =
  words groupBy wordCode withDefaultValue Seq()


// Return all ways to encode a number as a list of words
def encode(number: String): Set[List[String]] =
  if (number.isEmpty) Set(List())
  else {
    for {
      split <- 1 to number.length
      word <- wordsForNum(number take split)
      rest <- encode(number drop split)
    } yield word :: rest
  }.toSet

encode("7225247386")


def translate(number: String): Set[String] =
  encode(number) map (_ mkString " ")

translate("7225247386")



/*
Scala’s immutable collections are:

  ▶ easy to use: few steps to do the job.
  ▶ concise: one word replaces a whole loop.
  ▶ safe: type checker is really good at catching errors.
  ▶ fast: collection ops are tuned, can be parallelized.
  ▶ universal: one vocabulary to work on all kinds of collections.

This makes them a very attractive tool for software development

*/