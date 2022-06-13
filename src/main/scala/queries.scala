import org.apache.spark.sql.SparkSession

class queries(spark:SparkSession) {
  def Q1(): Unit = {
    val menu = new menu(spark)
    println("Please enter\n" +
      "[1] to display total population of country\n" +
      "[2] to display region with highest population\n" +
      "[3] to display population of different race/ethnicities\n" +
      "[4] to go back to main menu\n")
    print("Please choose you entry: ")
    val choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
      //query for total population of country
      case "2" =>
      //query for region with highest population
      case "3" =>
      //query for population of different race/ethnicities
      case "4" =>
        menu.mainmenu()
      case default =>
        println("Invalid entry, please try again!\n")
        Q1()
    }
  }

  def Q2(): Unit = {
    val menu = new menu(spark)
    println("Please enter\n" +
      "[1] to see population changes trend line for country\n" +
      "[2] to see population changes trend line per state of choice\n" +
      "[3] to go back to main menu")
    print("Please choose you entry: ")
    val choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
      //query for trend line of population changes for country
      case "2" =>
        print("Please enter state of choice: ")
        val state_in = scala.io.StdIn.readLine()
      //query for trend line of population changes for state of choice
      case "3" =>
        menu.mainmenu()
      case default =>
        println("Invalid entry, please try again!\n")
        Q2()
    }
  }

  def Q3(): Unit = {
    val menu = new menu(spark)
    //query for any future analysis based on above queries
    menu.mainmenu()
  }

  def Q4(): Unit = {
    val menu = new menu(spark)
    println("Please enter\n" +
      "[1] to display fastest growing regions\n" +
      "[2] to display fastest growing states\n" +
      "[3] to display areas of decreasing population\n" +
      "[4] to go back to main menu\n")
    print("Please choose you entry: ")
    val choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
      //query for fastest growing regions(top three)
      case "2" =>
      //query for fastest growing states(top 10)
      case "3" =>
      //query for areas of decreasing population either state wise or region wise(top 10 or 3 respectively)
      case "4" =>
        menu.mainmenu()
      case default =>
        println("Invalid entry, please try again!\n")
        Q4()
    }
  }
}
