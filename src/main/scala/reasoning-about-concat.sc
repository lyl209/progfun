// Law of Concat

// Structural induction

// Example: natural induction

// Given:
def factorial(n: Int): Int =
  if (n == 0) 1 // 1st clause
  else n * factorial(n-1) // 2nd clause

// Show that, for all n >= 4
// factorial(n) >= power(2, n)

// Base Case n = 4
// factorial(4) = 24 >= 16 = power(2, 4)

// Induction Step: n + 1
// We have for n >= 4

// Assume factorial(n) >= power (2, n)
//
// factorial(n + 1)
// = (n + 1) x factorial(n)
// > 2 x factorial(n)
// >= 2 x power(2, n)
// = power(2, n + 1)


// Referential Transparency
//



