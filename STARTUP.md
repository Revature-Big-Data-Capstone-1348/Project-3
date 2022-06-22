# Welcome to Start-up
Here you will find everything you need to get started with this application.

## Git Clone
- Information on how to utilize Git Clone commands and functionality can be found here:
  - https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository

- Links corresponding to this application's Git Clone utiliztion found here:
  - Http: https://github.com/Revature-Big-Data-Capstone-1348/Project-3.git
  - CLI : gh repo clone Revature-Big-Data-Capstone-1348/Project-3

## Application Requirements (versions and links)
- Scala (vesion: 2.13.8, download: https://scala-lang.org/download/2.13.8.html)

- Spark & Spark Core & Hadoop (version 3.3.0, download: https://spark.apache.org/downloads.html)

- Sbt tools (version 0.13.8, download: https://www.scala-sbt.org/download.html)

- Java JDK (version 1.8.1 A.K.A version 8, download: https://www.oracle.com/java/technologies/downloads/)

- IntelliJ (version Community 2021.3, download: https://www.jetbrains.com/idea/download/#section=windows)
 
- MySQL or other similar Database (MySQL version: 8.0.29, download: https://dev.mysql.com/downloads/workbench/)

- Azure Cloud Services or other similar cloud services
  - Within Azure:
    - Azure Online SQL Server
    - Azure Blob Storage
    - Azure Pipeline

#### If using Web-Scraping Techniques (work with Justin specifically) 
- Ubuntu (version 20.04 LTS, download: https://ubuntu.com/download/desktop)

#### If using Census.gov recommended cleaning steps
- Microsoft Excel
- Microsoft Access
- Access Shell (provided at https://www.census.gov/programs-surveys/decennial-census/about/rdo/summary-files.2020.html)

## Setup: Application Envrionment 
#### Within IntteliJ ensure
  - Scala plugins are installed
#### After plugins:
- Begin a new project with Scala 
  - Ensure sbt build
  - Ensure JDK
  - Ensure Maven repositories are accurate
- Build src folder
- Create new directories, classes, and objects as necessary
- Ensure imports as necessary, imports we used can be found in: 
  - https://github.com/Revature-Big-Data-Capstone-1348/Project-3/tree/main/src/main/scala/census_app 

- Additional help for these steps can be found at: 
  - https://docs.scala-lang.org/getting-started/intellij-track/getting-started-with-scala-in-intellij.html 
  - https://spark.apache.org/docs/latest/index.html
  - https://mvnrepository.com/open-source/collections

## Setup: Data Sourcing
Steps to complete Data Sourcing can be found in the Wiki tab here:
https://github.com/Revature-Big-Data-Capstone-1348/Project-3/wiki

## Setup: Cloud Services (if needed)
Steps to complete Cloud Service connections can also be found in the Wiki tab here:
https://github.com/Revature-Big-Data-Capstone-1348/Project-3/wiki/Azure-Tutorial

## General Steps for Execution
- Begin by sourcing your data from the census.gov page and cleaning it to meet your requirements
- Migrate your cleaned data to either the cloud, MySQL, or directly into the application (depending on cleaning still required, and number of users who require access)
  - If from the cloud: 
    - Pipe your data to the online SQL server
    - Proceed to MySQL steps
    - Share access as necessary to other users
  - If from MySQL
    - Perform queries as necessary
    - Output queried files as csv
    - Proceed to application
  - If directly into application
    - Import file by utilizing df
    - Perform queries or additional queries to derive information from dataset
   
## That's it, you should be ready to begin! 
 
