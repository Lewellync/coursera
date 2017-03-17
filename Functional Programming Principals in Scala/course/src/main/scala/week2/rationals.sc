

val x = new Rational(12, 16)
x.numer
x.denom

class Rational(x: Int, y: Int) {
  def g: Int = gcd(x, y)
  def numer: Int = x / g
  def denom: Int = y / g

  def add(that: Rational) = new Rational(
    numer * that.denom + that.numer * denom,
    denom * that.denom
  )

  def sub(that: Rational): Rational = add(that.neg)

  def neg = new Rational(-numer, denom)

  private def gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)
  override def toString: String = numer + "/" + denom
}