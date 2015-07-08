
object InvoiceApp extends App {
  val items = LoadItemsFromFile.LoadFromFile("E:\\Projects\\Personal\\InvoiceApp\\src\\main\\scala\\invoiceItems.txt")
  val invoice = InvoiceGenerator.GenerateInvoice(items)
  InvoicePrinter.PrintInvoice(invoice)
}
