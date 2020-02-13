Feature: Top 250 Movie Names
Scenario: Fetch top 250 movie names
     Given user navigates to IMDB website
     When user navigates to Top Rated movies from the menu list
     When user verifies that he has landed to the correct page
     Then user displays the top movie names in chronological sequence
    