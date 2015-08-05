
object InvoiceApp extends App {
  val items = LoadItemsFromFile.LoadFromFile("E:\\Projects\\Personal\\InvoiceApp\\src\\main\\scala\\invoiceItems.txt")
  val invoiceItems = InvoiceItemGenerator.GenerateInvoiceItems(items)
  val invoice = new Invoice(invoiceItems)
  InvoicePrinter.PrintInvoice(invoice)
}
