import org.apache.spark.sql.SparkSession
import java.util.{InputMismatchException, Scanner}

class loginmain(spark:SparkSession) {
  def createTable() {
    //spark.sql("drop table logins")
    //spark.sql("CREATE TABLE IF NOT EXISTS logins(username varchar(255) ,password varchar(255) row format delimited fields terminated by ','")

    val logindb = spark.read.options(Map("inferSchema" -> "true", "header" -> "true")).csv("input/logintable")
    logindb.createOrReplaceGlobalTempView("logins")

    //spark.sql("create table if not exists logins as select * from logintemp")
  }

  def login(): Unit = {
    val menu = new menu(spark)
    print("Enter username: ")
    var user_in = scala.io.StdIn.readLine()
    val logindb = spark.read.options(Map("inferSchema" -> "true", "header" -> "true")).csv("input/logintable").toDF()
    logindb.createOrReplaceTempView("logins")
    var first1 = spark.sql("select username from logins where username = '" + user_in + "'")
    if (first1.count() == 1) {
      print("Enter password: ")
      var password_in = scala.io.StdIn.readLine()
      var first2 = spark.sql("select password from logins where password = '" + password_in + "'")
      if (first2.count() == 1) {
        println("Login successful!\n")
        menu.mainmenu()
      }
      else {
        println("Incorrect password, please try again.")
        print("Enter password again: ")
        var password_in2 = scala.io.StdIn.readLine()
        var first3 = spark.sql("select password from logins where password = '" + password_in2 + "'")
        if (first3.count() == 1) {
          println("Login successful!\n")
          menu.mainmenu()
        }
        else {
          println("Incorrect password, going back to login page.\n")
          menu.loginmenu()
        }
      }
    }
    else {
      println("Incorrect username, please try again!\n")
      menu.loginmenu()
    }
  }

  def insert(): Unit = {
    print("Create new username: ")
    var user_in = scala.io.StdIn.readLine()
    val logindb = spark.read.options(Map("inferSchema" -> "true", "header" -> "true")).csv("input/logintable").toDF()
    logindb.createOrReplaceTempView("logins")
    spark.sql("create table login2 as select * from logins")
    var step1 = spark.sql("select username from login2 where username = '" + user_in + "'")
    if (step1.count() > 0) {
      println(user_in + " already exist.")
    }
    else {
      print("Create password: ")
      var password_in = scala.io.StdIn.readLine()
      print("Confirm password: ")
      var password_confirm_in = scala.io.StdIn.readLine()
      if (password_in == password_confirm_in) {
        spark.sql("insert into login2(username, password) values('" + user_in + "','" + password_in + "')")
        println("Login created successfully.")
      }
      else {
        println("Sorry, password didn't match.")
      }
    }
  }
}