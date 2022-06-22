package census_app

import org.apache.spark.sql.{DataFrame, SparkSession}

class DatabaseManager {
  //utilize winutils.exe for running Hadoop on Windows
  System.setProperty("%HADOOP_HOME%", "C://Hadoop//bin")

  //DataManager will utilize a new instance of the census_app DataAnalysis class to map each query function to a specific case
  val dataAnalysis = new DataAnalysis()

  //create SparkSession while also enabling Hive support
  val spark = SparkSession
    .builder ()
    .appName ("census_app")
    .config ("spark.master", "local[4]")
    .getOrCreate()

  //create a function that will create DataFrames from the data contained within the 2000, 2010, and 2020 census data CSVs
  def LoadTables(): Unit = {
    val df2020: DataFrame = spark.read.format("csv")
      .option("header","false")
      .option("inferSchema", "true")
      .load("output20.csv")
      .toDF("State_no","Region","State_Name","State_pop","WO","BO","AIO","AO","NHO","SOR","HL")
    df2020.createOrReplaceTempView("2020_data")

    val df2010: DataFrame = spark.read.format("csv")
      .option("header","false")
      .option("inferSchema", "true")
      .load("output10.csv")
      .toDF("State_no","Region","State_Name","State_pop","WO","BO","AIO","AO","NHO","SOR","HL")
    df2010.createOrReplaceTempView("2010_data")

    val df2000: DataFrame = spark.read.format("csv")
      .option("header","false")
      .option("inferSchema", "true")
      .load("output00.csv")
      .toDF("State_no","Region","State_Name","State_pop","WO","BO","AIO","AO","NHO","SOR","HL")
    df2000.createOrReplaceTempView("2000_data")
  }

  //function to maintain a SparkSession
  def returnSparkSession(): SparkSession = {
    spark
  }

  //function to end a SparkSession
  def closeSpark(): Unit = {
    println("Spark Session closed")
    spark.close
  }

  //queries for the data analysis questions are each mapped to a case that will run each query dependent on user input within the running app
  def queries(queryNumber: String): Unit = queryNumber match {
    case "Q1_1" => dataAnalysis.Total_Pops(spark:SparkSession)
    case "Q1_2" => dataAnalysis.Region_Pops(spark:SparkSession)
    case "Q1_3" => dataAnalysis.Race_Pops(spark:SparkSession)
    case "Q2_1" => dataAnalysis.total_predict_2020(spark:SparkSession)
    case "Q2_2" => dataAnalysis.state_predict_2020(spark:SparkSession)
    case "Q3_1" => dataAnalysis.total_future(spark:SparkSession)
    case "Q3_2" => dataAnalysis.state_future(spark:SparkSession)
    case "Q4_1" => dataAnalysis.Region_Deff(spark:SparkSession)
    case "Q4_2" => dataAnalysis.State_Deff(spark:SparkSession)
    case "Q4_3" => dataAnalysis.decr_pops(spark:SparkSession)
  }

  //function to create a temporary delay between menu screens
  def delay(milliseconds: Int): Unit ={
    Thread.sleep(milliseconds)
  }

  //function to create a temporary pause for output in the CLI
  def pause(milliseconds: Int): Unit = {
    Thread.sleep(milliseconds)
    println("**************************************************\n")
    println("[1] to continue: ")
    readChar()
    println("**************************************************\n")
  }

}
