// src/main/scala/progscala2/implicits/scala-database-api.scala

// A Scala wrapper for the Java-like Database API.
package progscala2.implicits {
    package scaladb {
    object implicits {
      import javadb.JRow

      implicit class SRow(jrow: JRow) {
        val savedjrow = jrow

        def get[T](colName: String)(implicit toT: (JRow,String) => T): T = {
          println(this.getClass.getCanonicalName)
          toT(jrow, colName)
        }

        def visitRow(other: SRow, col: String) = {
          println(other.savedjrow.getText(col))
        }

      }

      implicit val jrowToInt: (JRow,String) => Int =
        (jrow: JRow, colName: String) => jrow.getInt(colName)
      implicit val jrowToDouble: (JRow,String) => Double =
        (jrow: JRow, colName: String) => jrow.getDouble(colName)
      implicit val jrowToString: (JRow,String) => String =
        (jrow: JRow, colName: String) => jrow.getText(colName)

      /*
      implicit def jrowToInt(jrow: JRow, colName: String) = {
        jrow.getInt(colName)
      }

      implicit def jrowToDouble(jrow: JRow, colName: String) = {
        jrow.getDouble(colName)
      }

      implicit def jrowToString(jrow: JRow, colName: String) = {
        jrow.getText(colName)
      }

       */
    }

    object DB {
      import implicits._

      def main(args: Array[String]): Unit = {
        val row = javadb.JRow("one" -> 1, "two" -> 2.2, "three" -> "THREE!")
        //val otherrow = javadb.JRow("other" -> "másmilyen")

        val oneValue1: Int      = row.get("one")
        val twoValue1: Double   = row.get("two")
        val threeValue1: String = row.get("three")
        val oneValue_e = row.getInt("one")
        // val fourValue1: Byte    = row.get("four")  // won't compile

        println(s"one1   -> $oneValue1")
        println(s"two1   -> $twoValue1")
        println(s"three1 -> $threeValue1")
        println(s"oneValue_e -> $oneValue_e")

        val oneValue2   = row.get[Int]("one")
        val twoValue2   = row.get[Double]("two")
        val threeValue2 = row.get[String]("three")
        // val fourValue2    = row.get[Byte]("four")  // won't compile

        println(s"one2   -> $oneValue2")
        println(s"two2   -> $twoValue2")
        println(s"three2 -> $threeValue2")

        //println(row.visitRow(otherrow, "other"))
      }
    }
  }
}
