package census_app

import scala.Console.println
import scala.collection.mutable.Map
import org.apache.spark.sql.SparkSession

class Login() {

  val databaseManager = new DatabaseManager()


/*  What do these methods do...
 def createLoginTable(): Unit = {
 def login(): Unit = {
 def insert(): Unit = {

 It seems like we're using everything starting from val globallogindata.
 But someone from the app team will probably be able to speak more to that.*/

  def createLoginTable(): Unit = {
    val loginDB = databaseManager.spark.read.options(Map("inferSchema" -> "true", "header" -> "true"))
      .csv("src/input/logintable")
    loginDB.createOrReplaceTempView("logins")
  }

  def login(): Unit = {
    println("Please enter your username: ")
    val username = scala.io.StdIn.readLine()
    val loginDB = databaseManager.spark.read.options(Map("inferSchema" -> "true", "header" -> "true"))
      .csv("src/input/logintable").toDF()
    loginDB.createOrReplaceTempView("logins")
    val first1 = databaseManager.spark.sql("SELECT username FROM logins WHERE username = '" + username + "'")
    if (first1.count() == 1) {
      println("Please enter your password: ")
      val userpassword = scala.io.StdIn.readLine()
      val first2 = databaseManager.spark.sql("SELECT password FROM logins WHERE password = '" + userpassword + "'")
      if (first2.count() == 1) {
        println("Login successful!\n")
        MenuChoices.main_menu()
      }
      else {
        println("Incorrect password! Please try again.")
        print("Enter password again: ")
        val userpassword = scala.io.StdIn.readLine()
        val first3 = databaseManager.spark.sql("SELECT password FROM logins WHERE password = '" + userpassword + "'")
        if (first3.count() == 1) {
          println("Login successful!\n")
          MenuChoices.main_menu()
        }
        else {
          println("You have entered the incorrect password too many times.\n")
          MenuChoices.login_menu()
        }
      }
    }
    else {
      println("Incorrect username, please try again!\n")
      MenuChoices.login_menu()
    }
  }

  def insert(): Unit = {
    print("Create new username: ")
    val username = scala.io.StdIn.readLine()
    val loginDB = databaseManager.spark.read.options(Map("inferSchema" -> "true", "header" -> "true"))
      .csv("src/input/logintable").toDF()
    loginDB.createOrReplaceTempView("logins")
    databaseManager.spark.sql("CREATE table login2 AS SELECT * FROM logins")
    val step1 = databaseManager.spark.sql("SELECT username FROM login2 WHERE username = '" + username + "'")
    if (step1.count() > 0) {
      println(username + " Username already exists! Please try another username")
    }
    else {
      print("Create password: ")
      val userpassword = scala.io.StdIn.readLine()
      print("Confirm password: ")
      val password_confirm = scala.io.StdIn.readLine()
      if (userpassword == password_confirm) {
        databaseManager.spark.sql("INSERT into login2(username, password) VALUES('" + username + "','" + userpassword + "')")
        println("Login created successfully.")
      }
      else {
        println("Sorry, password didn't match.")
      }
    }
  }

  val globallogindata = scala.collection.mutable.Map("tek" -> "1234",
    "admin" -> "root")

  def login_2(): Unit = {
    //val logindata = scala.collection.mutable.Map("tek" -> "1234")
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

  def create(): Unit = {
    print("Create a temporary username: ")
    val username_new = scala.io.StdIn.readLine()
    if (globallogindata.contains(username_new)) {
      println("Username already exists, please try creating different username!\n")
      create()
    }
    else {
      print("Create a new temporary password: ")
      val password_new = scala.io.StdIn.readLine()
      print("Confirm password: ")
      val password_confirm = scala.io.StdIn.readLine()
      if (password_confirm == password_new) {
        globallogindata += (username_new -> password_new)
        println("Logging in as guest with temporary credentials!")
        MenuChoices.main_menu()
      }
      else {
        println("Sorry, password didn't match!")
        MenuChoices.login_menu()
      }
    }
  }
}
