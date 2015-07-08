/**
 * Created by andrew on 2015-07-07.
 */
case class InvoiceItem(productId: Int, description: String, count: Int, unitPrice: BigDecimal,
                   vat: BigDecimal, lineTotal: BigDecimal) {

}
