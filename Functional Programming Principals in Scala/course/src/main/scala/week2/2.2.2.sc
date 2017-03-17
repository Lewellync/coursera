
// We can also make the sum function even more efficient with currying
def sum(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + sum(f)(a + 1, b)

def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)

val ten = sum(fact)(_, _)
sum(x => x)(1, 10)

ten(3, 4)