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


// Referential Transparency principle


// Example: structural induction

// Let's show that
// (xs ++ ys) ++ zs = xs ++ (ys ++ zs)

/*

def concat[T](xs: List[T], ys: List[T]) = xs match {
  case List() => ys
  case x :: xs1 => x :: concat(xs1, ys)
}

distill two defining clauses of ++:

Nil ++ ys = ys                      // 1st clause
(x :: xs1) ++ ys = x :: (xs1 ++ ys) // 2nd clause

*/

// Use structural induction on xs

// Base Case: Nil

// left hand side
// (Nil ++ ys) + zs
// = ys ++ zs           // by 1st clause of ++

// right hand side
// Nil ++ (ys ++ zs)
// = ys ++ zs           // by 1st clause of ++

// Induction Step x :: xs

// left hand
// ((x :: xs) ++ ys) ++ zs
// = (x :: (xs ++ ys)) ++ zs        // by 2nd clause of ++
// = x :: ((xs ++ ys) ++ zs)        // by 2nd clause of ++
// = x :: (xs ++ (ys ++ zs))        // by induction hypothesis

// right hand
// (x :: xs) ++ (ys ++ zs)
// = x :: (xs ++ (ys ++ zs))        // by 2nd clause of ++

// left = right


// Exercise
// Show by induction on xs that xs ++ Nil = xs

// Base Case: Nil

// Nil ++ Nil         // by 1st clause
// = Nil

// Induction Step x :: xs

// left hand
// (x :: xs) ++ Nil
// = x :: (xs ++ Nil)     // by 2nd
// = x :: xs              // by induction hypothesis

// right hand
// x :: xs

// left = right


