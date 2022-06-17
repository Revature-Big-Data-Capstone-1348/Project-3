package census_app
//create a command-line menu where the user can input
//choose to either create a new user profile or login with their
//current credentials

object MenuChoices {

  def menu() : Unit = {
    println("\n--------Welcome-----------")
    println("[1] Login to Main")
    println("[0] Exit")
    println("-----------------------------")
    println("\nPlease select from one of the following: ")
  }

//create a submenu to either access user profile or access the queries
  def login_menu(): Unit = {
    println("\n------Login Menu--------")
    println("[1] Access Census Data Menu")
    println("[0] Return to Main")
    println("-----------------------------")
    println("\nPlease select from one of the following: ")
  }

//create a submenu for a logged-in user to update and/or delete their profile
  def profile_menu(): Unit = {
    println("\n------User Profile Menu--------")
    println("[1] Update User Profile")
    println("[2] Delete User Profile")
    println("[0] Return to Previous Menu")
    println("-----------------------------")
  }
//create a submenu for a logged in user to access queries based on U.S. Census data
  def census_menu(): Unit = {
    println("\n------Census Data Menu--------")
    println("[1] Scenario One: Total Population")
    println("[2] Scenario Two: Region With Highest Population")
    println("[3] Scenario Three: Population of Different Categories")
    println("[4] Scenario Four: Trend Line Predictions")
    println("[5] Scenario Five: Future Analysis")
    println("[0] Return to Previous Menu")
    println("-----------------------------")
    println("\nPlease select from one of the following: ")
  }

  //create a submenu for a logged in user to access queries based on U.S. Census data
  def scenario_one(): Unit = {
    println("\n------Census Data Menu--------")
    println("[1] Query 1")
    println("[0] Return to Previous Menu")
    println("-----------------------------")
    println("\nPlease select from one of the following: ")
  }

  def scenario_two(): Unit = {
    println("\n------Census Data Menu--------")
    println("[1] Query 2")
    println("[2] Query 3")
    println("[3] Query 4")
    println("[0] Return to Previous Menu")
    println("-----------------------------")
    println("\nPlease select from one of the following: ")
  }

  def scenario_three(): Unit = {
    println("\n------Census Data Menu--------")
    println("[1] Query 5")
    println("[2] Query 6")
    println("[3] Query 7")
    println("[0] Return to Previous Menu")
    println("-----------------------------")
    println("\nPlease select from one of the following: ")
  }

  def scenario_four(): Unit = {
    println("\n------Census Data Menu--------")
    println("[1] Query 8")
    println("[2] Query 9")
    println("[3] Query 10")
    println("[0] Return to Previous Menu")
    println("-----------------------------")
    println("\nPlease select from one of the following: ")
  }

  def scenario_five(): Unit = {
    println("\n------Census Data Menu--------")
    println("[1] Query 11")
    println("[2] Query 12")
    println("[0] Return to Previous Menu")
    println("-----------------------------")
    println("\nPlease select from one of the following: ")
  }

}
