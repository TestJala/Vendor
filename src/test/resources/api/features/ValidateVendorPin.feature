@Incomplete
Feature:Validate Vendor Pin

  Scenario: Validate Vendor PIN - /api/vmRegistration/validatePin
    Given A POST request to "/api/vmRegistration/validatePin" endpoint with the following values
      | pin | 946755 |
    Then The response status code should be 200
    And response includes the following in any order
      | result              | true |
      | errorMessage        |      |
      | serviceProviderName |      |
      | serviceProviderCode |      |
