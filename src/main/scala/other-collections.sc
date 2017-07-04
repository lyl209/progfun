// Vector implementations is shallow tree

// 32 elements
// 32 pointers to each 32 elements
// continue ...

// 32 = 2 ^ 5
// 32 * 32 = 2 ^ 10
// 2 ^ 15
// 2 ^ 20
// 2 ^ 25
// 2 ^ 30 this is the limit

// log32(N)

// bulk operation (i.e. map) is good because can do cache in chunk
// locality is better than list
// but list is still best if you only need head/tail stuff


// Cons
//
// Instead of x :: xs, there is
//   x +: xs Create a new vector with leading element x, followed
// by all elements of xs.
//   xs :+ x Create a new vector with trailing element x, preceded
// by all elements of xs.
//
// (Note that the : always points to the sequence.)

// Append an element to a vector
// log32(N) for object creations
// Get 2 copies of vectors


// Collection Hierarchy
//   Iterable
//  /    \   \
// Seq, Set, Map






