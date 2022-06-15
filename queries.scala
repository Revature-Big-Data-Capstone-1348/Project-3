import org.apache.spark.sql.{DataFrame, SparkSession}

class queries(spark:SparkSession) {
  def Q1(): Unit = {
    LoadTables()
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
        Total_Pops()
      case "2" =>
      //query for region with highest population
      Region_Pops()
      case "3" =>
      //query for population of different race/ethnicities
      Race_Pops()
      case "4" =>
        menu.mainmenu()
      case default =>
        println("Invalid entry, please try again!\n")
        Q1()
    }
    Q1()
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
  def LoadTables(): Unit = {
    val df2020: DataFrame = spark.read.format("csv")
      .option("header","false")
      .option("inferSchema", "true")
      .load("output20.csv")
      .toDF("State_no","Region","State_pop","WO","BO","AIO","AO","NHO","SOR","HL")
    df2020.createOrReplaceTempView("2020_data")

    val df2010: DataFrame = spark.read.format("csv")
      .option("header","false")
      .option("inferSchema", "true")
      .load("output10.csv")
      .toDF("State_no","Region","State_pop","WO","BO","AIO","AO","NHO","SOR","HL")
    df2010.createOrReplaceTempView("2010_data")

    val df2000: DataFrame = spark.read.format("csv")
      .option("header","false")
      .option("inferSchema", "true")
      .load("output00.csv")
      .toDF("State_no","Region","State_pop","WO","BO","AIO","AO","NHO","SOR","HL")
    df2000.createOrReplaceTempView("2000_data")
  }

  def Total_Pops(): Unit = {
    val Max20 = spark.sql("select sum(State_pop) as max_population_2020 from 2020_data")
    Max20.show()

    val Max10 = spark.sql("select sum(State_pop) as max_population_2010 from 2010_data")
    Max10.show()

    val Max00 = spark.sql("select sum(State_pop) as max_population_2000 from 2000_data")
    Max00.show()

  }

  def Region_Pops(): Unit = {
    var year = 2000

    while(year < 2029) {
      val R100 = spark.sql("select sum(state_pop) as Region_1 from "+year+"_Data where Region = '1'")
      var temp = R100.select("Region_1").collect().toList
      val R100i = temp(0).toString().substring(1, temp(0).toString().length - 1).toInt

      val R200 = spark.sql("select sum(state_pop) as Region_2 from "+year+"_Data where Region = '2'")
      temp = R200.select("Region_2").collect().toList
      val R200i = temp(0).toString().substring(1, temp(0).toString().length - 1).toInt

      val R300 = spark.sql("select sum(state_pop) as Region_3 from "+year+"_Data where Region = '3'")
      temp = R300.select("Region_3").collect().toList
      val R300i = temp(0).toString().substring(1, temp(0).toString().length - 1).toInt

      val R400 = spark.sql("select sum(state_pop) as Region_4 from "+year+"_Data where Region = '4'")
      temp = R400.select("Region_4").collect().toList
      val R400i = temp(0).toString().substring(1, temp(0).toString().length - 1).toInt

      println(year)
      if (R100i > R200i) {
        if (R300i > R400i) {
          if (R100i > R300i) {
            R100.show()
          } else {
            R300.show()
          }
        } else if (R100i > R400i) {
          R100.show()
        } else {
          R400.show()
        }
      } else if (R300i > R400i) {
        if (R200i > R300i) {
          R200.show()
        } else {
          R300.show()
        }
      } else if (R200i > R400i) {
        R200.show()
      } else {
        R400.show()
      }
      year = year + 10
    }
  }

  def Race_Pops(): Unit = {
    var year = 2000
    while(year < 2029) {
      val Race00 = spark.sql("Select sum(WO),sum(BO),sum(AIO),sum(AO),sum(NHO),sum(SOR),sum(HL) from " + year + "_data")
      println(year)
      Race00.show()
      year = year + 10
    }
  }
}
