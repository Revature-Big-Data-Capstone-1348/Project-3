package census_app

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.collection.JavaConversions._
import org.apache.spark.sql.SparkSession


object Main {
  //turn off log messages
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val databaseManager = new DatabaseManager()

  def Init(): Unit ={
    databaseManager.LoadTables()
  }

  def main(args: Array[String]): Unit = {
    Init()
    MenuChoices.login_menu(                                                )
  }
}
