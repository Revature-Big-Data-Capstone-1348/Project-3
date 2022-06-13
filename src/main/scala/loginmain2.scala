import org.apache.spark.sql.SparkSession

class loginmain2(spark:SparkSession) {
  val globallogindata = scala.collection.mutable.Map("tek" -> "1234")
  def login(): Unit = {
    val menu = new menu(spark)
    //val logindata = scala.collection.mutable.Map("tek" -> "1234")
    print("Please enter username: ")
    val username_in = scala.io.StdIn.readLine()
    if (globallogindata.contains(username_in)) {
      print("Please enter password: ")
      val password = if (globallogindata.contains(username_in)) globallogindata(username_in) else 0
      val password_in = scala.io.StdIn.readLine()
      if (password_in == password) {
        println("Login successful!\n")
        menu.mainmenu()
      }
      else {
        println("Incorrect password, please try again!\n")
        menu.loginmenu()
      }
    }
    else {
      println("Incorrect username: please try again!")
      menu.loginmenu()
    }
  }

  def create(): Unit = {
    val menu = new menu(spark)
    print("Create temporary username: ")
    val username_new = scala.io.StdIn.readLine()
    if (globallogindata.contains(username_new)) {
      println("Username already exists, please try creating different username!\n")
      create()
    }
    else {
      print("Create new temporary password: ")
      val password_new = scala.io.StdIn.readLine()
      print("Confirm new password: ")
      val password_confirm = scala.io.StdIn.readLine()
      if(password_confirm == password_new) {
        globallogindata += (username_new -> password_new)
        println("Logging in as guest with temporary user!")
        menu.mainmenu()
      }
      else {
        println("Sorry, password didn't match!")
        menu.loginmenu()
      }
    }
  }
}

