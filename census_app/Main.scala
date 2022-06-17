package census_app

import scala.Console.println
import org.apache.spark.sql.SparkSession
import java.util.InputMismatchException._
import scala.collection.mutable._
import scala.collection.JavaConversions._
import java.util.{InputMismatchException, Scanner}

object Main {
  val databaseManager = new DatabaseManager

  def Init(): Unit = {
    databaseManager.createDatabase()
  }

  def main(args: Array[String]): Unit = {

    var condition = true

    while (condition != false) {

      MenuChoices.menu()
      try {

        val input = scala.io.StdIn.readLine()

        input match {

          case "1" => { //login to main
            println("Successful login")
            MenuChoices.login_menu()

            val input = scala.io.StdIn.readLine()
            input match {

              case "1" => { //census menu
                MenuChoices.census_menu()

                val input = scala.io.StdIn.readLine()
                input match {

                  case "1" => { //scenario 1
                    MenuChoices.scenario_one()
                    val input = scala.io.StdIn.readLine()
                    input match { //scenario 1 menu
                      case "1" => {
                        databaseManager.scenarios("one")
                      }
                      case "0" => {
                        MenuChoices.census_menu()
                      }
                    }
                  }
                  case "2" => { //scenario 2 menu
                    MenuChoices.scenario_two()
                    val input = scala.io.StdIn.readLine()
                    input match {
                      case "1" => {
                        databaseManager.scenarios("two")
                      }
                      case "2" => {
                        databaseManager.scenarios("three")
                      }
                      case "3" => {
                        databaseManager.scenarios("four")
                      }
                      case "0" => {
                        MenuChoices.census_menu()
                      }
                    }
                  }
                  case "3" => { //scenario 3 menu
                    MenuChoices.scenario_three()
                    val input = scala.io.StdIn.readLine()
                    input match {
                      case "1" => {
                        databaseManager.scenarios("five")
                      }
                      case "2" => {
                        databaseManager.scenarios("six")
                      }
                      case "3" => {
                        databaseManager.scenarios("seven")
                      }
                      case "0" => {
                        MenuChoices.census_menu()
                      }
                    }
                  }
                  case "4" => { //scenario 4 menu
                    MenuChoices.scenario_four()
                    val input = scala.io.StdIn.readLine()
                    input match {
                      case "1" => {
                        databaseManager.scenarios("eight")
                      }
                      case "2" => {
                        databaseManager.scenarios("nine")
                      }
                      case "3" => {
                        databaseManager.scenarios("ten")
                      }
                      case "0" => {
                        MenuChoices.census_menu()
                      }
                    }
                  }
                  case "5" => { //scenario 5 menu
                    MenuChoices.scenario_five
                    val input = scala.io.StdIn.readLine()
                    input match {
                      case "1" => {
                        databaseManager.scenarios("eleven")
                      }
                      case "2" => {
                        databaseManager.scenarios("twelve")
                      }
                      case "0" => {
                        MenuChoices.census_menu()
                      }
                    }
                  }
                  case "0" => {
                    MenuChoices.menu()
                  }
                }
              }
            }
          }
          case "0" => {
            println("Thank you for using the application. Hope to see you again!")
            condition = false;
            databaseManager.closeSpark()
          }
          case other => {
            println("Invalid option. Please select the correct numerical key and try again.")
          }
        }
      } catch {
        case e: IllegalArgumentException =>
          println("Error")
      }
    }
  }
}
