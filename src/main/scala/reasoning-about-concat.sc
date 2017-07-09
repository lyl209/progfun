// Law of Concat

// Structural induction

// Example

// Given:
def factorial(n: Int): Int =
  if (n == 0) 1 // 1st clause
  else n * factorial(n-1) // 2nd clause

// Show that, for all n >= 4
// factorial(n) >= power(2, n)


