## This program works in conjunction with datasets provided at:

- 2020 Census
https://www.census.gov/programs-surveys/decennial-census/about/rdo/summary-files.2020.html

- 2010 Census
https://www.census.gov/data/datasets/2010/dec/redistricting-file-pl-94-171.html

- 2000 Census
https://www.census.gov/data/datasets/2000/dec/redistricting.html

## In order to utilize the datasets you will need:
- Microsoft Excel
- Microsoft Access
- Access Shell (also provided at https://www.census.gov/programs-surveys/decennial-census/about/rdo/summary-files.2020.html)
- Your favorite database (SQL, MySQL, MONGODB, etc. - optional host it online through AWS, Microsoft Azure, etc.)


## General Instructions for 2020 Census
- Download each zip folder for each state

- Unzip each file

- Open the Access Shell
  - Import the geo file and append it to the geo header within the shell
  - Import the segment 1 file and append it to the segment 1 header within the shell

- Ensure a relationship exists between the two tables on the LOGRECNO column

- Create a query to select only the necessary columns from both tables

- Export the queried file to a csv format

- Import the csv file to a database

- The data should be ready for the program once imported to the database

## General Instructions for 2010 and 2000
- Download either the zip folder or the zip file for segment 1

- Unzip each file

- Open the Access Shell
  - Import the segment 1 file and append it to the segment 1 header within the shell

- Create a query to select only the necessary columns from the table

- Export the queried file to a csv format

- Import the csv file to a database

- The data should be ready for the program once imported to the databse

## Additional Information
- Guides for the download process, Access and Excel utilization can be found at:
  - https://www2.census.gov/programs-surveys/decennial/rdo/about/2020-census-program/Phase3/SupportMaterials/HowToUse_2020Census_PL94-171_MSAccessShells.pdf 
