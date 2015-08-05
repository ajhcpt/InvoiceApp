import scala.io.Source
import scala.math.BigDecimal

object LoadItemsFromFile {
  def LoadFromFile(fileName: String): Seq[Product] = {
    ConvertStringToItem(RemoveTitleRow(Source.fromFile(fileName).getLines.toSeq))
  }

  def RemoveTitleRow(fileLines: Seq[String]): Seq[String] = {
    fileLines.tail
  }

  def ConvertStringToItem(fileLines: Seq[String]): Seq[Product] = {
    fileLines.map(line => {
      val test = line.split(",")
      new Product(test(0).toInt, test(2).toString.trim, BigDecimal(test(1).toString.trim))
    }).toSeq
  }
}