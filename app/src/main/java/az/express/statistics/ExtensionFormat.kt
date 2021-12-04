package az.express.statistics

import java.text.DecimalFormat

fun Float.formatForSpace() = DecimalFormat("###,###").format(this).replace(",", " ")