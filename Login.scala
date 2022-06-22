package census_app

import scala.Console.println
import scala.collection.mutable.Map
import org.apache.spark.sql.SparkSession

class Login() {
  //the instance of databaseManager will be used in the create function to delay output to the CLI when a new username/password is created
  val databaseManager = new DatabaseManager()
  //global login data has an admin key -> value, but the map is also mutable for new username/password credentials
  val globallogindata = scala.collection.mutable.Map("admin" -> "root")

  //a simple login function that checks whether username/password is contained within the globallogin map
  def login(): Unit = {
    print("Please enter username: ")
    val username_in = scala.io.StdIn.readLine()
    if (globallogindata.contains(username_in)) {
      print("Please enter password: ")
      val password = if (globallogindata.contains(username_in)) globallogindata(username_in) else 0
      val password_in = scala.io.StdIn.readLine()
      if (password_in == password) {
        println("Login successful!\n")
        MenuChoices.main_menu()
      }
      else {
        println("Incorrect password, please try again!\n")
        MenuChoices.login_menu()
      }
    }
    else {
      println("Incorrect username: please try again!")
      MenuChoices.login_menu()
    }
  }

  //the create function gives the user the option to create new username/password credentials to login
  def create(): Unit = {
    print("Create a new username: ")
    val username_new = scala.io.StdIn.readLine()
    if (globallogindata.contains(username_new)) {
      println("Username already exists, please try creating different username!\n")
      create()
    }
    else {
      print("Create a new password: ")
      val password_new = scala.io.StdIn.readLine()
      print("Confirm password: ")
      val password_confirm = scala.io.StdIn.readLine()
      if (password_confirm == password_new) {
        globallogindata += (username_new -> password_new)
        println("Login created!")
        databaseManager.delay(1000)
        MenuChoices.login_menu()
      }
      else {
        println("Sorry, password didn't match!")
        MenuChoices.login_menu()
      }
    }
  }
}
