import java.util.NoSuchElementException


trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

singleton[Int](1)
singleton[Boolean](true)
singleton(1)
singleton(true)

def nth[T](n: Int, l: List[T]): T =
  if (l.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) l.head
  else nth(n - 1, l.tail)
