$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/DVLA.feature");
formatter.feature({
  "name": "DVLA Feature",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@ui"
    },
    {
      "name": "@dvla"
    }
  ]
});
formatter.scenario({
  "name": "First Test",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@ui"
    },
    {
      "name": "@dvla"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Am on DVLA Site",
  "keyword": "Given "
});
formatter.match({
  "location": "DVLAVechicleSteps.amOnDVLASite()"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService$SupportedFiles.setListOfSupportedFiles(FileService.java:31)\r\n\tat gov.uk.digital.dvla.vechicleenquiry.servicelayer.CSVReader.getFileInfo(CSVReader.java:36)\r\n\tat gov.uk.digital.dvla.vechicleenquiry.servicelayer.CSVReader.getVechicleDetails(CSVReader.java:16)\r\n\tat steps.DVLAVechicleSteps.amOnDVLASite(DVLAVechicleSteps.java:34)\r\n\tat ✽.Am on DVLA Site(features/DVLA.feature:5)\r\n",
  "status": "failed"
});
formatter.after({
  "status": "passed"
});
});