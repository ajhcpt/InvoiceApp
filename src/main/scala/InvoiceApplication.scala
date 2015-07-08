import scala.io.Source
import scala.math.BigDecimal

class InvoiceApplication {

  def LoadItems(fileName: String): Seq[Item] = {
    Source.fromFile(fileName).getLines.toSeq.tail.map(line => {
      val test = line.split(",")
      new Item(test(0).toInt, test(2).toString.trim, BigDecimal(test(1).toString.trim))
    }).toSeq
  }

  def ItemToInvoiceItem(items: Seq[Item]): Seq[InvoiceItem] = {
    Seq()
  }

  def PrintInvoice(invoice: Invoice) = {

  }
}
