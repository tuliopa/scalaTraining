import java.util.{Date, Locale}
import java.text.DateFormat
import java.text.DateFormat._

object GermanDate {
	def main(args: Array[String]) {
		val now = new Date
		val df = getDateInstance(LONG, Locale.GERMANY)
		println(df format now)
	}
}
