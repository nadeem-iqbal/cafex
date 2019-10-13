package com.cafex

import com.cafex.service.BillingService
import org.scalatest.{FlatSpec, Matchers}

class BillingServiceSpec extends FlatSpec with Matchers {

  private val billingService = new BillingService

  "Billing service generate bill" should "return £0.50 for Cola with no service charge" in {
    val bill = billingService.generateBill(List("Cola"))
    bill should be (0.50)
  }

  it should "return £1.00 for Coffee with no service charge" in {
    val bill = billingService.generateBill(List("Coffee"))
    bill should be (1.00)
  }

  it should "return £2.00 for two Coffees with no service charge" in {
    val bill = billingService.generateBill(List("Coffee", "Coffee"))
    bill should be (2.00)
  }

  it should "return £1.50 for Cola and Coffee with no service charge" in {
    val bill = billingService.generateBill(List("Cola", "Coffee"))
    bill should be (1.50)
  }

  it should "return £2.20 for Cheese Sandwich including 10% service charge" in {
    val bill = billingService.generateBill(List("Cheese Sandwich"))
    bill should be (2.20)
  }

  it should "return £1.64 for Egg Sandwich including 10% service charge" in {
    val bill = billingService.generateBill(List("Egg Sandwich"))
    bill should be (1.64)
  }

  it should "return £5.40 for Steak Sandwich including 20% service charge" in {
    val bill = billingService.generateBill(List("Steak Sandwich"))
    bill should be (5.40)
  }

  it should "return £3.30 for Cheese Sandwich and Coffee including 10% service charge" in {
    val bill = billingService.generateBill(List("Cheese Sandwich", "Coffee"))
    bill should be (3.30)
  }

  it should "return £6.00 for Steak Sandwich and Cola including 20% service charge" in {
    val bill = billingService.generateBill(List("Steak Sandwich", "Cola"))
    bill should be (6.00)
  }

  it should "return £7.80 for Steak Sandwich and Cheese Sandwich including 20% service charge" in {
    val bill = billingService.generateBill(List("Steak Sandwich", "Cheese Sandwich"))
    bill should be (7.80)
  }

  it should "return £8.40 for Steak Sandwich, Cheese Sandwich and Cola including 20% service charge" in {
    val bill = billingService.generateBill(List("Steak Sandwich", "Cheese Sandwich", "Cola"))
    bill should be (8.40)
  }

  it should "return £150.00 for Three course meal including £20 maximum service charge" in {
    val bill = billingService.generateBill(List("Three course meal"))
    bill should be (150.00)
  }

}
