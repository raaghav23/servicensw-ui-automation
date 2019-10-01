@serviceNSW
Feature:  Validate Service NSW UI

  Scenario Outline: Service NSW UI Validation
    Given User navigates to Service NSW homepage
    When Search "Apply for a number plate"
    Then  Validate the navigation to Apply for a number plate
    And  Verify Find locations
    And  Enter suburb "<SuburbName>"
    Then "<ServiceCentreName>" is displayed in search results Items
    Examples:
      | SuburbName  | ServiceCentreName           |
      | Sydney 2000 | Marrickville Service Centre |

  Scenario Outline: Service NSW UI Validation
    Given User navigates to Service NSW homepage
    When Search "Apply for a number plate"
    Then  Validate the navigation to Apply for a number plate
    And  Verify Find locations
    And  Enter suburb "<SuburbName>"
    Then "<ServiceCentre>" displayed in search results Items
    Examples:
      | SuburbName  | ServiceCentre  |
      | Sydney Domestic Airport 2020 | Rockdale Service Centre |

