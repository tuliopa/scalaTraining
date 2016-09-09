import scala.collection.mutable.ArraySeq
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer

case class Cell(key: String, value: Any)

class Row(c: Buffer[Cell]) {
  def cells = c
}


object Validations {
  def main(args: Array[String]) {
    // Run Functions
    println("Validations!")

  var table = createTable();
  println("new Table: " + table)
  }

  def createRow(color: String, size: String, price: Number, isNew: Boolean, serial: String, model: String) : Row = {
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

    table += createRow("Black", "5", 40.00, true, "asdaskr", "Elantra")
    table += createRow("Blue", "1", 50.00, true, "1234dfqwer", "Yaris")
    table += createRow("Gree", "7", 30.00, false, "ariupip", "Chevy")
    table += createRow("Red", "3", 20.00, true, "mjkojpia", "Sentra")
    table += createRow("Orange", "8", 100.00, false, "agweqwer", "fairly lady Z")
    table
  }

}
