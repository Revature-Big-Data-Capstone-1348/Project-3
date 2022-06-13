import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

class menu(spark:SparkSession) {
    def loginmenu(): Unit = {
      //val loginmain = new loginmain(spark)
      val loginmain2 = new loginmain2(spark)
      print("Welcome to the Census Bureau Analysis!\n" +
        "Please enter\n" +
        "[1] to login as admin\n" +
        "[2] to login as guest\n" +
        "[3] to exit\n" +
        "Please choose your entry: ")
      var choice = scala.io.StdIn.readLine()
      choice match {
        case "1" =>
          loginmain2.login()
        case "2" =>
          loginmain2.create()
        case "3" =>
          sys.exit()
        case deafult =>
          println("Invalid input, please try again.\n")
          loginmenu()
      }
    }

    def mainmenu(): Unit = {
      val queries = new queries(spark)
      println("Welcome to the Census Bureau Analysis Dashboard!")
      println("Please enter\n" +
        " [1] to see total population\n" +
        " [2] to see trend line prediction\n" +
        " [3] to see future analysis\n" +
        " [4] to see population comparison\n" +
        " [5] to log out")
      print("Please choose you entry: ")
      var choice = scala.io.StdIn.readLine()
      choice match {
        case "1" =>
          queries.Q1()
        case "2" =>
          queries.Q2()
        case "3" =>
          queries.Q3()
        case "4" =>
          queries.Q4()
        case "5" =>
          println("Logging out!\n")
          loginmenu()
        case default =>
          println("Invalid entry, please try again!\n")
          mainmenu()

      }
    }
  }
