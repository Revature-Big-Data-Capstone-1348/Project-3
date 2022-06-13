import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object project3 {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val spark = SparkSession
      .builder
      .appName("Project2")
      .config("spark.master", "local[4]")
      .enableHiveSupport()
      .getOrCreate()

    val menu = new menu(spark)
    menu.loginmenu()
  }
}
