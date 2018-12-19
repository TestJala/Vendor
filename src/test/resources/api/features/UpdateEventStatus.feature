@Incomplete
Feature:Update Event Status

  Scenario: Update Event Status - /api/vmEvent/updateStatus
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
    Given A POST request to "/api/vmEvent/updateStatus" endpoint with the following values
      | eventNo | &eventNo             |
      | userId  | 1                    |
      | geoCode | 32.910565,-97.259996 |
    Then The response status code should be 200
    And response includes the following in any order
      | result        | true |
      | statusCode    | 0    |
      | errorMessage  |      |
      | currentStatus |      |
      | nextStatus    |      |
