package week3

import java.util.NoSuchElementException

/**
  * Created by lewel_000 on 3/17/2017.
  */
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

object main extends App {
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
  singleton[Int](1)
  singleton[Boolean](true)
}