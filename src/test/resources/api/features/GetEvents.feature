@Incomplete
Feature:Get Events

  Scenario: Get Events - /api/vmEvent/getOpenEvents
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
    Given A POST request to "/api/vmEvent/getOpenEvents" endpoint with the following values
      | vendorId | MSS8196              |
      | userId   | 1                    |
      | geoCode  | 32.910565,-97.259996 |
    Then The response status code should be 200
    And response includes the following in any order
      | result            | true |
      | errorMessage      |      |
      | statusCode        | 0    |
      | openEvents        |      |
      | scheduledMessages |      |
      | waitTimeEvent     |      |