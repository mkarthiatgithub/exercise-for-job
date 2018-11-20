@ui @dvla
Feature: DVLA Feature for CSV and XLS support

  Scenario: First Test for CSV file
    Given Am on DVLA Site
    When I process all CSV files in the folder src/main/resources/testData and get their details
    Then I assert the car Make,color for a given regNumber

  Scenario: First Test for XLSX file
    Given Am on DVLA Site
    When I process all XLSX files in the folder src/main/resources/testData and get their details
    Then I assert the car Make,color for a given regNumber
