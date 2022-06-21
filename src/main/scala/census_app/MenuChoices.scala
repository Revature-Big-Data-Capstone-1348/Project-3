package census_app

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types._
import org.apache.spark.sql.SparkSession

import scala.collection.JavaConversions._

object MenuChoices {

  val databaseManager = new DatabaseManager()
  val login = new Login()

  def login_menu(): Unit = {
    //val loginmain = new loginmain(spark)
    println("\n----Welcome to the Census Bureau Analysis----\n" +
      "[1] Login with an Existing Account\n" +
      "[2] Create a New User Account\n" +
      "[3] Temporary Bypass to Main Menu\n" +
      "[0] Exit\n" +
      "---------------------------------------------\n")
    println("Please select from one of the following options: ")
    var choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
        login.login
      case "2" =>
        login.create
      case "3" =>
        main_menu()
      case "0" =>
        println("Thank you for using the application!")
        sys.exit()
      case default =>
        println("Invalid input, please try again.\n")
        login_menu()
    }
  }

  def main_menu(): Unit = {
    println("\n----------------Main Menu-------------------\n" +
      "[1] Scenario One: Total Population\n" +
      "[2] Scenario Two: Trend Line Predictions\n" +
      "[3] Scenario Three: Future Analysis\n" +
      "[4] Scenario Four: Population Comparison\n" +
      "[5] Log out\n" +
      "[0] Exit\n" +
      "---------------------------------------------")
    println("\nPlease select from one of the following options: ")
    var choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
        Q1()
      case "2" =>
        Q2()
      case "3" =>
        Q3()
      case "4" =>
        Q4()
      case "5" =>
        println("\nLogging Out!")
        login_menu()
      case "0" =>
        println("Thank you for using the application!")
        sys.exit()
      case default =>
        println("Invalid entry, please try again!\n")
        main_menu()
    }
  }
  def Q1(): Unit = {
    println("\n-------------Scenario One Menu---------------\n" +
      "[1] Display total population of the country\n" +
      "[2] Display region with the highest population\n" +
      "[3] Display population of different race/ethnicity\n" +
      "[4] Return to Main Menu\n" +
      "---------------------------------------------\n")
    println("\nPlease select from one of the following options: ")
    val choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
        println("\n**************************************************")
        //query for total population of country
        databaseManager.queries(queryNumber = "Q1_1")
        databaseManager.pause(1000)
        Q1()
      case "2" =>
        println("\n**************************************************")
        //query for region with highest population
        databaseManager.queries(queryNumber = "Q1_2")
        databaseManager.pause(1000)
        Q1()
      case "3" =>
        println("\n**************************************************")
        //query for population of different race/ethnicities
        databaseManager.queries(queryNumber = "Q1_3")
        databaseManager.pause(1000)
        Q1()
      case "4" =>
        main_menu()
      case default =>
        println("Invalid entry, please try again!\n")
        Q1()
    }
  }

  def Q2(): Unit = {
    println("\n-------------Scenario Two Menu---------------\n" +
      "[1] Display population trend line for country\n" +
      "[2] Display population trend line per state\n" +
      "[3] Return to Main Menu\n" +
      "---------------------------------------------\n")
    println("\nPlease select from one of the following options: ")
    val choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
        println("\n**************************************************")
        //query for trend line of population changes for country
        databaseManager.queries(queryNumber = "Q2_1")
        databaseManager.pause(1000)
        Q2()
      case "2" =>
        println("\n**************************************************")
        //query for trend line of population changes for state of choice
        databaseManager.queries(queryNumber = "Q2_2")
        databaseManager.pause(1000)
        Q2()
      case "3" =>
        main_menu()
      case default =>
        println("Invalid entry, please try again!\n")
        Q2()
    }
  }

  def Q3(): Unit = {
    println("\n------------Future Analysis Menu--------------\n" +
      "[1] Display population trend line for country\n" +
      "[2] Display population trend line per state\n" +
      "[3] Return to Main Menu\n" +
      "---------------------------------------------\n")
    println("\nPlease select from one of the following options: ")
    val choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
        println("\n**************************************************")
        //query for trend line of population changes for country
        databaseManager.queries(queryNumber = "Q3_1")
        databaseManager.pause(1000)
        Q3()
      case "2" =>
        println("\n**************************************************")
//        print("Please enter state of choice: ")
//        val state_in = scala.io.StdIn.readLine()
        //query for trend line of population changes for state of choice
        databaseManager.queries(queryNumber = "Q3_2")
        databaseManager.pause(1000)
        Q3()
      case "3" =>
        main_menu()
      case default =>
        println("Invalid entry, please try again!\n")
        Q3()
    }
  }

  def Q4(): Unit = {
    println("\n-------Population Comparison Menu-----------\n" +
      "[1] Display the fastest growing regions\n" +
      "[2] Display the fastest growing states\n" +
      "[3] Display the areas of decreasing population\n" +
      "[4] Return to Main Menu\n" +
      "---------------------------------------------\n")
    println("\nPlease select from one of the following options: ")
    val choice = scala.io.StdIn.readLine()
    choice match {
      case "1" =>
        println("\n**************************************************")
        //query for fastest growing regions(top three)
        databaseManager.queries(queryNumber="Q4_1")
        databaseManager.pause(1000)
        Q4()
      case "2" =>
        println("\n**************************************************")
        //query for fastest growing states(top 10)
        databaseManager.queries(queryNumber="Q4_2")
        databaseManager.pause(1000)
        Q4()
      case "3" =>
        println("\n**************************************************")
        //query for areas of decreasing population either state wise or region wise(top 10 or 3 respectively)
        databaseManager.queries(queryNumber="Q4_3")
        databaseManager.pause(1000)
        Q4()
      case "4" =>
        main_menu()
      case default =>
        println("Invalid entry, please try again!\n")
        Q4()
    }
  }
}
