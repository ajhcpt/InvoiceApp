import scala.io.Source
import scala.math.BigDecimal

object LoadItemsFromFile {
  def LoadFromFile(fileName: String): Seq[Item] = {
    ConvertStringToItem(RemoveTitleRow(Source.fromFile(fileName).getLines.toSeq))
  }

  def RemoveTitleRow(fileLines: Seq[String]): Seq[String] = {
    fileLines.tail
  }

  def ConvertStringToItem(fileLines: Seq[String]): Seq[Item] = {
    fileLines.map(line => {
      val test = line.split(",")
      new Item(test(0).toInt, test(2).toString.trim, BigDecimal(test(1).toString.trim))
    }).toSeq
  }
}