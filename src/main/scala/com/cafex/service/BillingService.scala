package com.cafex.service

import com.cafex.model.StockItem
import com.cafex.util.NumberUtils.{calculatePercentage, roundTwoDecimalPlaces}

class BillingService {
  private final val twenty: BigDecimal = 20
  private final val ten: BigDecimal = 10
  private final val zero: BigDecimal = 0

  def generateBill(basketItems: List[String]): BigDecimal = {

    val applyServiceChargeOnItemsCost = (
      confirmStock andThen
        getPurchasedItemsCost andThen
          applyServiceCharge
      ) (basketItems)(_)

    (confirmStock andThen
      getServiceChargePercentage andThen
        applyServiceChargeOnItemsCost
    ) (basketItems)
  }

  private val confirmStock = (basketItems: List[String]) => for (
    basketItem <- basketItems;
    availableItem <- getAvailableItems if availableItem.name == basketItem
  ) yield availableItem

  private val getPurchasedItemsCost = (purchasedItems: List[StockItem]) => purchasedItems.map(_.price).sum

  private val getServiceChargePercentage = (purchasedItems: List[StockItem]) =>
    if (purchasedItems.exists(_.isHotFood)) twenty else if (purchasedItems.exists(_.isFood)) ten else zero

  private val applyServiceCharge = (itemsCost: BigDecimal) => (percentage: BigDecimal) =>
    roundTwoDecimalPlaces(
      calculatePercentage(itemsCost, percentage) match {
        case amount if amount < twenty => amount
        case _ => twenty
      }
    ) + itemsCost

  private def getAvailableItems =
    List(
      StockItem(name = "Cola", price = 0.50, isFood = false, isHotFood = false),
      StockItem(name = "Coffee", price = 1.00, isFood = false, isHotFood = false),
      StockItem(name = "Egg Sandwich", price = 1.49, isFood = true, isHotFood = false),
      StockItem(name = "Cheese Sandwich", price = 2.00, isFood = true, isHotFood = false),
      StockItem(name = "Steak Sandwich", price = 4.50, isFood = true, isHotFood = true),
      StockItem(name = "Three course meal", price = 130.00, isFood = true, isHotFood = true)
    )
}