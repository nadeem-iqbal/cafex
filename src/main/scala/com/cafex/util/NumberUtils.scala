package com.cafex.util

import scala.math.BigDecimal.RoundingMode

object NumberUtils {

  private final val hundred = 100

  def calculatePercentage(amount: BigDecimal, percentage: BigDecimal): BigDecimal = amount * percentage / hundred

  def roundTwoDecimalPlaces(amount: BigDecimal): BigDecimal = amount.setScale(2, RoundingMode.HALF_UP)

}
