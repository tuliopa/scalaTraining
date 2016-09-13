import scala.util.Try
import scala.collection.mutable.ArraySeq
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer

case class Cell(key: String, value: Any)

class Row(c: Buffer[Cell]) {
  def cells = c

  def getValue(key: String): Any = {
    var selectedCell = cells.filter(f => f.key.equals(key)).asInstanceOf[Buffer[Row]]
    return selectedCell(0)
  }
}



object Validations {
  def main(args: Array[String]) {
    // Run Functions
    println("Validations!")
    var textToEval = "Color"
    println(textToEval + ": " +  isTextValid(textToEval))

    textToEval = "true"
    println(textToEval + ": " +  isBoolean(textToEval))

    var table = createTable();
    println("new Table: " + table)

    checkAndApplyRules(rules, table)
  }

  def createRow(color: String, size: String, price: Any, isNew: Any, serial: String, model: String) : Row = {
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

    table += createRow("Black", "5", 40.00, true, null, "Elantra")
    table += createRow("Blue", "1", null, true, "1234dfqwer", "Yaris")
    table += createRow("Gree", "7", 30.00, null, "ariupip", "Chevy")
    table += createRow("Red", "3", "20.00", true, "mjkojpia", "Sentra")
    table += createRow("Orange", "8", 100.00, false, "agweqwer", "fairly lady Z")//Add generic model
    table
  }

// Validations
  def isBoolean(text: String): Boolean = Try(text.toBoolean).getOrElse(false)

  def isTextValid(text: String): Boolean = {

    // Check First Ocurrance
    // val pattern = "^[a-zA-Z][a-zA-Z0-9]*".r
    // val matches = pattern.findFirstIn(text)
    // var result = !matches.isEmpty && matches.get.length == text.length
    // println(text + ": " + result + ", matches.: " + matches)
    // return result

    text.matches("^[a-zA-Z][a-zA-Z0-9]*")
  }

  def isEmail(text: String): Boolean = {
    return text.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
  }

  def evalPrice(row: Row): Unit = {
    val cell = row.getValue("price").asInstanceOf[Cell]
    println("Eval Price: " + cell)
  }

  def evalIsNew(row: Row): Unit = {
    val cell = row.getValue("isNew").asInstanceOf[Cell]
    println("Eval Price: " + cell)
  }

// Register rules
//  val rules: Array[(String) => Boolean] = Array(isBoolean, isTextValid, isEmail, evalPrice)
  val rules: Array[(Row) => Unit] = Array(evalPrice, evalIsNew)

// TODO Change Unit to Row.
  def checkAndApplyRules(rules: Array[(Row) => Unit], table: Buffer[Row]) : Unit = {
    println("Check rules")
    for(row <- table) {
      for(r <- rules) {
          r(row)
          //row.cells map r
      }

    }
  }
}
