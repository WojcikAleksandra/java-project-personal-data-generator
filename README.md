# Fictional Personal Data Generator

This application is used to generate a list of fictional personal data and export it to an Excel file (in XLS or XLSX format).  
The number of generated persons and the preferred file format are specified by the user in the graphical user interface of the application.

The project was developed as part of the Advanced Object and Functional Programming academic course as a group assignment.

## Generated elements
* *First and last name*  
  Combinations of first names and last names are generated randomly from lists of the most popular first names and surnames in Poland, taking into account both female and male forms.
* *Gender*  
  Gender is randomly assigned — female/male — and is consistent with the first and last name.
* *Residential address*  
  Includes the street name, building number, apartment number, and the name of a city or village. The data is generated based on a list of existing street names and takes into account the population sizes of cities in Poland.
* *Date of birth*  
  Dates of birth are randomly generated within realistic time ranges.
* *Phone number*  
  Phone numbers are generated in accordance with the format currently used in Poland and are unique for each person.
* *PESEL number*  
  PESEL numbers are generated in accordance with the valid format and are consistent with the remaining personal data (date of birth, gender). The remaining digits of the PESEL number are generated randomly, while maintaining the uniqueness constraint.
* *Kinship and marriage relationships*  
  Relationships between generated persons are randomly generated and assigned. Possible relationships include: father, mother, children, sisters, brothers, husband/wife.

## User interface
* A field for entering the number of generated persons in the range of 100–1,000,000.
* Selection of the XLS/XLSX file type, with information that exporting to the XLS format is only possible for up to 64,000 persons.
* “Run” button.
* If an invalid number of persons is entered, an appropriate message is displayed.

## Application performance report
The user interface contains a window in which the results of the application runtime measurements for individual task sizes are displayed, one below another.  
The compilation time in milliseconds is expressed by the formula:  
**0.1 * n * a**, where **5 > a > 0.2**, and **n** is the number of persons to be generated.  
The formula may work poorly for small values of **n**, but in such cases the execution time is of little interest.



