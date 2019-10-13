package com.cafex

import com.cafex.service.BillingService
import org.scalatest.{FlatSpec, Matchers}

class BillingServiceSpec extends FlatSpec with Matchers {

  private val billingService = new BillingService

  "Billing service generate bill" should "return £0.50 for Cola" in {
    val bill = billingService.generateBill(List("Cola"))
    bill should be (0.50)
  }

  it should "return £1.00 for Coffee" in {
    val bill = billingService.generateBill(List("Coffee"))
    bill should be (1.00)
  }

  it should "return £2.00 for two Coffees" in {
    val bill = billingService.generateBill(List("Coffee", "Coffee"))
    bill should be (2.00)
  }

  it should "return £1.50 for Cola and Coffee" in {
    val bill = billingService.generateBill(List("Cola", "Coffee"))
    bill should be (1.50)
  }

  it should "return £2.00 for Cheese Sandwich" in {
    val bill = billingService.generateBill(List("Cheese Sandwich"))
    bill should be (2.00)
  }

  it should "return £3.00 for Cheese Sandwich and Coffee" in {
    val bill = billingService.generateBill(List("Cheese Sandwich", "Coffee"))
    bill should be (3.00)
  }

  it should "return £5.00 for Steak Sandwich and Cola" in {
    val bill = billingService.generateBill(List("Steak Sandwich", "Cola"))
    bill should be (5.00)
  }

}
