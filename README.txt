FileStatisticWebApp Java web application.
Main functionality:
  -  Upload txt file and split it by lines
  -  Calculate statistic for each line: longest word (symbols between 2 spaces), shortest word, line length, average word length.
  -  Aggregate these values for all lines from file (unit test).
  -  Store line and file statistic into DB (with Hibernate mapping).
  -  Return from server side list of handled files and statistic per file using RESTful services and Spring MVC controllers.
  -  Display list of files and statistic per line for selected file.

