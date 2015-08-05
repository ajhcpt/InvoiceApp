import org.scalatest._

class LoadItemsTest extends FeatureSpec with GivenWhenThen {
  val titleString = "productId, unitprice, description"
  val itemString1 = "1, 10, item one"
  val item1 = new Product(1, "item one", 10)
  val itemString2 = "2, 10, item two"
  val item2 = new Product(2, "item two", 10)
  val itemString3 = "3, 10, item three"
  val item3 = new Product(3, "item three", 10)
  val itemString4 = "12, 10, item twelve"
  val item4 = new Product(12, "item twelve", 10)
  val itemString5 = "7, 10, item seven"
  val item5 = new Product(7, "item seven", 10)

  feature("File Load") {
    scenario("Title line should be removed") {
      Given("A sequence of lines loaded from a file")
      val fileLines: Seq[String] = Seq(titleString, itemString1, itemString2, itemString3)

      When("Remove Title is called")
      val linesWithoutTitle = LoadItemsFromFile.RemoveTitleRow(fileLines)

      Then("the number of lines remaining should be file lines - 1")
      assert((fileLines.size - linesWithoutTitle.size) == 1)

      And("the title line should not be there")
      assert(linesWithoutTitle.count(line => line.contains(titleString)) == 0)
    }

    scenario("strings should be converted to Items") {
      Given("A sequence of lines loaded from a file")
      val fileLines: Seq[String] = Seq(itemString1, itemString2, itemString2, itemString3, itemString4, itemString5)

      When("The strings are converted to Items")
      val items = LoadItemsFromFile.ConvertStringToItem(fileLines)

      Then("there should be 6 items")
      assertResult(6)(items.size)

      And("item two should appear twice")
      assertResult(2)(items.count(item => item.equals(item2)))

      And("each other item should appear once")
      assertResult(1)(items.count(item => item.equals(item1)))
      assertResult(1)(items.count(item => item.equals(item3)))
      assertResult(1)(items.count(item => item.equals(item4)))
      assertResult(1)(items.count(item => item.equals(item5)))
    }
  }
}
