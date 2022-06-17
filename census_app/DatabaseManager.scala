package census_app

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types._
import java.io.{File, PrintWriter}
import scala.collection.mutable.ArrayBuffer

class DatabaseManager {
  //utilize winutils.exe for running Hadoop on Windows
  System.setProperty("%HADOOP_HOME%", "C:\\Hadoop\\bin")

  //turn off log messages
  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("akka").setLevel(Level.ERROR)

  //DataManager will utilize a new instance of the ecommerce_app.DataAnalysis class to map each query function to a specific case
  val dataAnalysis = new DataAnalysis

  //create SparkSession while also enabling Hive support
  val spark = SparkSession
    .builder ()
    .appName ("census_app")
    .config ("spark.master", "local")
    .enableHiveSupport()
    .getOrCreate()

  //create a function that reads the generated Datasets.csv during an initiated SparkSession
  def createDatabase(): Unit = {

    //val dfSchema = StructType(Array())

    val df = spark.read.option("header", "true")
      //schema(dfSchema)
      .option("inferSchema", "false")
      //.csv("src/input/name_of_data_file")

    //create a lazily evaluated table for queries
   // df.createOrReplaceTempView("first_of_many")

  }

  //function to end a SparkSession
  def closeSpark(): Unit = {
    println("Spark Session closed")
    spark.close
  }

  //queries for the data analysis questions
  def scenarios(scenarioNumber: String): Unit = scenarioNumber match {
    case "one" => dataAnalysis.query1(spark)
    case "two" => dataAnalysis.query2(spark)
    case "three" => dataAnalysis.query3(spark)
    case "four" => dataAnalysis.query4(spark)
    case "five" => dataAnalysis.query5(spark)
    case "six" => dataAnalysis.query6(spark)
    case "seven" => dataAnalysis.query7(spark)
    case "eight" => dataAnalysis.query8(spark)
    case "nine" => dataAnalysis.query9(spark)
    case "ten" => dataAnalysis.query10(spark)
    case "eleven" => dataAnalysis.query11(spark)
    case "twelve" => dataAnalysis.query12(spark)
  }

  // function to reestablish a SparkSession
  def returnSparkSession(): SparkSession = {
    spark
  }

}
