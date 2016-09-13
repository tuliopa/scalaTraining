import scala.util.Try
import scala.collection.mutable.ArraySeq
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer

case class Cell(key: String, var value: String)

class Row(c: Buffer[Cell]) {
  def cells = c

  def getValue(key: String): Any = {
    var selectedCell = cells.filter(f => f.key.equals(key)).asInstanceOf[Buffer[Row]]
    return selectedCell(0)
  }

  override def toString(): String = {
    var message: String = "{"
    for(c <- cells) {
      message ++= "\n" + c.key + ": " + c.value
    }
    message ++= "\n}"
    return message
  }
}

object Validations {
  def main(args: Array[String]) {
    // Run Function
    println("\nStandarize table\n")

    var table = createTable();
    println("Before standarization:")
    printTable(table)
    checkAndApplyRules(rules, table)
    println("After standarization:")
    printTable(table)
  }

  def createRow(color: String, size: String, price: String, isNew: String, serial: String, model: String) : Row = {
    // Create a row of cells
    var row = new Row(new ArrayBuffer[Cell](10))
    row.cells += new Cell("color", color)
    row.cells += new Cell("size", size)
    row.cells += new Cell("price", price)
    row.cells += new Cell("isNew", isNew)
    row.cells += new Cell("serial", serial)
    row.cells += new Cell("model", model)
    row
  }

  def createTable() : Buffer[Row] = {
    var table = new ArrayBuffer[Row](5)

    table += createRow("Black", "5", "40.00", "true", null, "Elantra")
    table += createRow("Blue", "1", null, "true", "1234dfqwer", "Yaris")
    table += createRow("Green", "7", "30.00", null, "ariupip", "Chevy")
    table += createRow("Red", "3", "20.00", "true", "mjkojpia", "Sentra")
    table += createRow("Orange", "8", "100.as0", "false", "agweqwer", "fairly lady Z")
    table
  }

  def printTable(table: Buffer[Row]): Unit = for(row <- table) println(row.toString())

// Validations
  def evalPrice(row: Row): Unit = {
    val cell = row.getValue("price").asInstanceOf[Cell]
    try {
      cell.value.toDouble
    } catch {
      case e: Exception => cell.value = "0.00"
    }
  }

  def evalIsNew(row: Row): Unit = {
    val cell = row.getValue("isNew").asInstanceOf[Cell]
    try {
        cell.value.toBoolean
    } catch {
        case e: Exception => cell.value = "false"
    }
  }

  // Register rules
  val rules: Array[(Row) => Unit] = Array(evalPrice, evalIsNew)

  def checkAndApplyRules(rules: Array[(Row) => Unit], table: Buffer[Row]) : Unit = {
    println("Checking rules and fixing it.")
    for(row <- table) {
      for(r <- rules) {
          r(row)
      }
    }
  }
}
