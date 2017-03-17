
def general(combine: (Int, Int) => Int, unit: Int)(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) unit else combine(f(a), general(combine, unit)(f)(a + 1, b))
}



def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
  if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
}

def product(f: Int => Int)(a: Int, b: Int) = mapReduce(f, (x, y) => x * y, 1)(a, b)

def partial(f: Int => Int, combine: (Int, Int) => Int, zero: Int, a: Int, b: Int): Int = {
  if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
}

// Are all the same sort of of
general((x, y) => x * y, 1)(z => z)(1, 5) // Triple curry?!
mapReduce(f => f, (x, y) => x * y, 1)(1, 5) // Two curry
partial(f => f, (x, y) => x * y, 1, 1, 5) // One large function