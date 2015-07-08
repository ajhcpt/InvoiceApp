
import org.scalatest.{GivenWhenThen, FeatureSpec}

class InvoiceGeneratorTests extends FeatureSpec with GivenWhenThen {
  val item1 = new Item(1, "item1", 25)
  val item2 = new Item(2, "item1", 13.33)
  val item3 = new Item(3, "item1", 5.33)
  val item4 = new Item(4, "item1", 83.95)
  val item5 = new Item(5, "item1", 12)

  feature("Generating Invoice") {
    scenario("Check grouping of like items") {
      Given("A sequence of items")
      val items = Seq(item1, item2, item3, item4, item2, item1, item5)

      When("the list of items is converted to an invoice")
      val invoice = InvoiceGenerator.GenerateInvoice(items)

      Then("there should be 5 invoice items")
      assertResult(5)(invoice.invoiceItems.size)

      And("item 1 should appear correctly on the invoice")
      assert(invoice.invoiceItems.filter(item => item.productId.equals(1)).head.lineTotal.equals(BigDecimal(57)))

      And("item 2 should appear correctly on the invoice")
      assert(invoice.invoiceItems.filter(item => item.productId.equals(2)).head.lineTotal == BigDecimal(30.39))
    }


    scenario("Check rounding of vat amount") {
      Given("A sequence of items")
      val items = Seq(item2, item2, item2)

      When("the list of items is converted to an invoice")
      val invoice = InvoiceGenerator.GenerateInvoice(items)

      Then("there should be 1 invoice items")
      assertResult(1)(invoice.invoiceItems.size)

      And("the line total for item 2 should be rounded correctly")
      assert(invoice.invoiceItems.filter(item => item.productId.equals(2)).head.lineTotal == BigDecimal(45.59))

    }
  }
}
