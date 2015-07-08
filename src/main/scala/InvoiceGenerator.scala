import scala.math.BigDecimal.RoundingMode

object InvoiceGenerator {
  def GenerateInvoice(items: Seq[Item]): Invoice = {
    val invoiceItems = items.groupBy(item => item.productId).map(x => {
      val productId = x._2.head.productId
      val description = x._2.head.description
      val count = x._2.size
      val unitPrice = x._2.head.unitPrice
      val vatRate = 0.14
      val vat: BigDecimal = (count * unitPrice * vatRate).setScale(2, RoundingMode.HALF_UP)
      val lineTotal = (count * unitPrice) + vat
      new InvoiceItem(productId, description, count, unitPrice, vat, lineTotal)
    }).toSeq

    new Invoice(invoiceItems)
  }
}
