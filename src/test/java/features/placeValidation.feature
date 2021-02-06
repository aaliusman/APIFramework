Feature: Validate Place API's

  @Regression
  Scenario Outline: Verify Place is added successfully
    Given Add Place payload with "<name>" "<language>" "<address>" "<typesOne>" "<typesTwo>" <lat>
    When user calls "addPlaceAPI" with "Post" http request
    Then the API call gets success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address   | typesOne | typesTwo   | lat       |
      | BBHouse | English  | Bensalem  | shoeShop | appleStore | -12.23455 |
      | Usman   | Urdu     | Langhorne | pharmacy | hpStore    | -23.64747 |

  @DeletePlace
  Scenario: Verify if Delete Place API call is working
    Given Delete Place payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call gets success with status code 200
    And "status" in response body is "OK"
