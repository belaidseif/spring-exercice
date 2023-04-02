Feature: We want to retrieve all commands of a specific client

  Scenario Outline: Call backend with the client id
    When the client calls endpoint "/rampup/api/<clientId>/commands"
    Then response status code is 200
    Then response list size is <size>
    Examples:
      | clientId | size |
      | 2        | 3    |
      | 1        | 0    |
      | 3        | 1    |



  Scenario: Getting command by id
    When the client calls endpoint "/rampup/api/2/commands/1"
    Then response status code is 200
    Then response command must be like this:
      | id        | 1    |
      | details_size        | 3    |
      | createdAt        | 2018-10-10T00:00:00+01:00    |

