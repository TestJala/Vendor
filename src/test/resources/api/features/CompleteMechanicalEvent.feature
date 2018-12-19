@Incomplete
Feature:Complete Mechanical Event

  Scenario: Complete Mechanical Event - /api/vmEvent/completeMechanicalEvent
    Given A created event with the following values
      | address               | 100 Main St.                        |
      | city                  | Cherryville                         |
      | state                 | NC                                  |
      | zip                   | 28021                               |
      | pointOfContact        | Jim Allen                           |
      | pointOfContactPhoneNo | 704-111-2222                        |
      | assignment            | Customer has requested a yard check |
      | additionalInformation | Gate Passcode: 1234                 |
      | unitNo                |                                     |
      | vin                   |                                     |
      | customerId            | 1                                   |
      | eventType             | 4                                   |
    And A POST request to "/api/vmEvent/completeMechanicalEvent" endpoint with the following values
      | eventNo | &eventNo             |
      | userId  | 1                    |
      | geoCode | 32.910565,-97.259996 |
    Then The response status code should be 200
    And response includes the following in any order
      | result       | true |
      | errorMessage |      |
      | statusCode   | 0    |
