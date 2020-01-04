package views.helpers

import java.time.format.DateTimeFormatter

object DateHelpers {

	def getCurrentYear() : String = {
		return DateTimeFormatter.ofPattern("YYYY").format(java.time.LocalDate.now)
	}
}
