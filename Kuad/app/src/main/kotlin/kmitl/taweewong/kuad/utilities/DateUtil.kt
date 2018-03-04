package kmitl.taweewong.kuad.utilities

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private const val dateStringPattern = "dd MMM YYYY"

    fun getTodayDateString(): String {
        return SimpleDateFormat(dateStringPattern, Locale.US).format(Date())
    }
}