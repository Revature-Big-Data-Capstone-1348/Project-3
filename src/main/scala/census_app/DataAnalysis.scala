package census_app

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types._

class DataAnalysis {

  def Total_Pops(spark:SparkSession): Unit = {
    val Max20 = spark.sql("select sum(State_pop) as max_population_2020 from 2020_data")
    Max20.show()

    val Max10 = spark.sql("select sum(State_pop) as max_population_2010 from 2010_data")
    Max10.show()

    val Max00 = spark.sql("select sum(State_pop) as max_population_2000 from 2000_data")
    Max00.show()

  }

  def Region_Pops(spark:SparkSession): Unit = {
    var year = 2000

    while(year < 2029) {
      val R100 = spark.sql("select sum(state_pop) as Region_1 from "+year+"_Data where Region = '1'")
      var temp = R100.select("Region_1").collect().toList
      val R100i = temp(0).toString().substring(1, temp(0).toString().length - 1).toInt

      val R200 = spark.sql("select sum(state_pop) as Region_2 from "+year+"_Data where Region = '2'")
      temp = R200.select("Region_2").collect().toList
      val R200i = temp(0).toString().substring(1, temp(0).toString().length - 1).toInt

      val R300 = spark.sql("select sum(state_pop) as Region_3 from "+year+"_Data where Region = '3'")
      temp = R300.select("Region_3").collect().toList
      val R300i = temp(0).toString().substring(1, temp(0).toString().length - 1).toInt

      val R400 = spark.sql("select sum(state_pop) as Region_4 from "+year+"_Data where Region = '4'")
      temp = R400.select("Region_4").collect().toList
      val R400i = temp(0).toString().substring(1, temp(0).toString().length - 1).toInt

      println(year)
      if (R100i > R200i) {
        if (R300i > R400i) {
          if (R100i > R300i) {
            R100.show()
          } else {
            R300.show()
          }
        } else if (R100i > R400i) {
          R100.show()
        } else {
          R400.show()
        }
      } else if (R300i > R400i) {
        if (R200i > R300i) {
          R200.show()
        } else {
          R300.show()
        }
      } else if (R200i > R400i) {
        R200.show()
      } else {
        R400.show()
      }
      year = year + 10
    }
  }

  def Race_Pops(spark:SparkSession): Unit = {
    var year = 2000
    while(year < 2029) {
      val Race00 = spark.sql("Select sum(WO),sum(BO),sum(AIO),sum(AO),sum(NHO),sum(SOR),sum(HL) from " + year + "_data")
      .toDF("white", "black_or_african_american","american_indian", "asian", "native_hawaiian","other","hispanic_or_latino")
      println(year)
      Race00.show()
      year = year + 10
    }
  }

  def Region_Deff(spark:SparkSession): Unit = {
    var year = 2000
    //var temp = ListBuffer[String]()
    val temp = scala.collection.mutable.Map[String, Int]()
    while (year < 2019) {
      val year10 = year + 10
      var i = 1
      while(i<5) {
        val Deff00_10_1 = spark.sql("Select(select sum(state_pop) from " + year10 + "_data where Region = "+i+")" +
          "-" +
          "(select sum(state_pop) from " + year + "_data where Region = "+i+") as 2000_to_2010")
        var temp1 = Deff00_10_1.select("2000_to_2010").collect().toList.toString()
        temp += s"${year}_to_${year10}_$i" -> temp1.substring(6, temp1.length - 2).toInt
        i=i+1
      }
      year = year10
    }
    var i = 0
    while(i<3) {
      val max = temp.valuesIterator.max
      var key = temp.filter(_._2 == max).toString()
      val temp1 = key.split(" ")
      key = temp1(0).substring(8)
      println(i+1+". Years and Region: "+key+" Difference: "+max)
      temp.remove(key)
      i=i+1
    }
  }

  def State_Deff(spark:SparkSession): Unit = {
    var year = 2000
    //var temp = ListBuffer[String]()
    val temp = scala.collection.mutable.Map[String, Int]()
    while (year < 2019) {
      val year10 = year + 10
      var i = 1
      while(i<77) {
        val Deff00_10_1 = spark.sql("Select(select state_pop from " + year10 + "_data where State_no = "+i+")" +
          "-" +
          "(select state_pop from " + year + "_data where State_no = "+i+") as 2000_to_2010")
        var temp1 = Deff00_10_1.select("2000_to_2010").collect().toList.toString()
        val temp2 = temp1.substring(6,temp1.length-2)
        if(temp2.equals("null")){
          //     println("No state with that number")
        }else {
          var Name = spark.sql("Select State_Name from "+year+"_data where State_no = "+i)
          temp1 = Name.select("State_Name").collect().toList.toString()
          val SName = temp1.substring(6,temp1.length-2)
          temp += s"${year}_to_${year10}_$SName" -> temp2.toInt
        }
        i=i+1
      }
      year = year10
    }
    var i = 0
    while(i<10) {
      val max = temp.valuesIterator.max
      var key = temp.filter(_._2 == max).toString()
      val temp1 = key.split(" ")
      key = temp1(0).substring(8)
      println(i+1+".\tYears and State: "+key+"\tDifference: "+max)
      temp.remove(key)
      i=i+1
    }
  }

  def decr_pops(spark:SparkSession): Unit = {
    var year = 2000
    //var temp = ListBuffer[String]()
    val temp = scala.collection.mutable.Map[String, Int]()
    while (year < 2019) {
      val year10 = year + 10
      var i = 1
      while (i < 77) {
        val Deff00_10_1 = spark.sql("Select(select state_pop from " + year10 + "_data where State_no = " + i + ")" +
          "-" +
          "(select state_pop from " + year + "_data where State_no = " + i + ") as 2000_to_2010")
        var temp1 = Deff00_10_1.select("2000_to_2010").collect().toList.toString()
        val temp2 = temp1.substring(6, temp1.length - 2)
        if (temp2.equals("null")) {
          //     println("No state with that number")
        } else if (temp2.toInt < 0) {
          var Name = spark.sql("Select State_Name from " + year + "_data where State_no = " + i)
          temp1 = Name.select("State_Name").collect().toList.toString()
          val SName = temp1.substring(6, temp1.length - 2)
          temp += s"${year}_to_${year10}_$SName" -> temp2.toInt
        }
        i = i + 1
      }
      year = year10
    }
    var i = 0
    while (i < 6) {
      val max = temp.valuesIterator.min
      var key = temp.filter(_._2 == max).toString()
      val temp1 = key.split(" ")
      key = temp1(0).substring(8)
      println(i + 1 + ".\tYears and State: " + key + "\tDifference: " + max)
      temp.remove(key)
      i = i + 1
    }
  }

  def state_predict_2020(spark: SparkSession): Unit = {
    print("Please enter state abbreviation of choice: ")
    val state_in = scala.io.StdIn.readLine()
    val AlPop00 = spark.sql("Select State_pop FROM 2000_data WHERE State_Name = '" + state_in.toUpperCase()+"'")
    val AlPop10 = spark.sql("Select State_pop FROM 2010_data WHERE State_Name = '" + state_in.toUpperCase()+"'")

    println("2000")
    AlPop00.show()
    println("2010")
    AlPop10.show()
  }

  def total_predict_2020(spark: SparkSession): Unit = {
    val TotalPop00 = spark.sql("select sum(State_pop) as total_population_2000 from 2000_data")
    val TotalPop10 = spark.sql("select sum(State_pop) as total_population_2010 from 2010_data")

    println("2000")
    TotalPop00.show()
    println("2010")
    TotalPop10.show()
  }

  def state_future(spark:SparkSession): Unit = {
    print("Please enter state abbreviation of choice: ")
    val state_in = scala.io.StdIn.readLine()
    val alPop00 = spark.sql("Select State_pop FROM 2000_data WHERE State_Name = '" + state_in.toUpperCase()+"'")
    val alPop10 = spark.sql("Select State_pop FROM 2010_data WHERE State_Name = '" + state_in.toUpperCase()+"'")
    val alPop20 = spark.sql("Select State_pop FROM 2020_data WHERE State_Name = '" + state_in.toUpperCase()+"'")

    rintln("2000")
    alPop00.show()
    rintln("2010")
    alPop10.show()
    rintln("2020")
    alPop20.show()
  }

  def total_future(spark:SparkSession): Unit = {
    val TotalPop00 = spark.sql("select sum(State_pop) as total_population_2000 from 2000_data")
    val TotalPop10 = spark.sql("select sum(State_pop) as total_population_2010 from 2010_data")
    val TotalPop20 = spark.sql("select sum(State_pop) as total_population_2020 from 2020_data")

    rintln("2000")
    TotalPop00.show()
    rintln("2010")
    TotalPop10.show()
    rintln("2020")
    TotalPop20.show()
  }
}
