@Incomplete
Feature:Create Work Item

  Scenario: Create Work Item - /api/vmUnit/createWorkItem
    Given A POST request to "/api/vmUnit/createWorkItem" endpoint with the following values
      | unitNo        | 123456789         |
      | VIN           | 1HGCM82633A004352 |
      | workItemType  | 1                 |
      | userId        | 1                 |
      | parentEventId |                   |
    #parentEventId: Yard Check Event Id
    #XMJQH6J9WK6XMR6789
    Then The response status code should be 200
    And response includes the following in any order
      | result       | true |
      | errorMessage |      |

