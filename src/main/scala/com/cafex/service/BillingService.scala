package com.cafex.service

import com.cafex.model.MenuItem

class BillingService {

  def generateBill(basketItems: List[String]): BigDecimal = (
    confirmStock andThen
      getPurchasedItemsCost
    ) (basketItems)

  private val confirmStock = (basketItems: List[String]) => for (
    basketItem <- basketItems;
    availableItem <- getAvailableItems if availableItem.name == basketItem
  ) yield availableItem

  private val getPurchasedItemsCost = (purchasedItems: List[MenuItem]) => purchasedItems.map(_.price).sum

  private def getAvailableItems =
    List(
      MenuItem(name = "Cola", price = 0.50),
      MenuItem(name = "Coffee", price = 1.00),
      MenuItem(name = "Cheese Sandwich", price = 2.00),
      MenuItem(name = "Steak Sandwich", price = 4.50)
    )
}