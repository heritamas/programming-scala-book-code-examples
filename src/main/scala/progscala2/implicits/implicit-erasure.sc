// src/main/scala/progscala2/implicits/implicit-erasure.sc
object M {
  implicit object IntMarker                                          // <1>
  implicit object StringMarker
  implicit val i : Integer = 1

  def m(seq: Seq[Int])(implicit i: IntMarker.type): Unit =           // <2>
    println(s"Seq[Int]: $seq")

  def m(seq: Seq[String])(implicit s: StringMarker.type): Unit =     // <3>
    println(s"Seq[String]: $seq")

  def m(seq: Seq[Double])(implicit i: Integer): Unit =           // <2>
    println(s"Seq[Double]: $seq")
}

import M._                                                           // <4>
m(List(1,2,3))
m(List("one", "two", "three"))
m(List(1D,2D,3D))

println(IntMarker.getClass.toString)

