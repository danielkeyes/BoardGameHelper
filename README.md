# Game Playing app

Simple app to help when playing boardgames

Some of the issues I have while gaming is the wife and I forgetting how many actions we have left when playing Dreamwell, or need a life counter, or need to calculate score without paper, etc...

## Implementation Consideration

I want to use 
- Compose 
- Flow
- Material 3

## Design Decisions

For MVP I want to create this mainly with two player in mind since it would just be used between my wife and I. Later down the road I would add support for players > 2 in the future.

Also I am still looking into sharing ViewModels in compose navigation(rather not scope to myNavHost) and NavType String array argument since the documentation for both are not straight forward.

## Notes

Issue where current Material 3 is missing Dropdown composable

## TODO

- Create reusable scaffold to take in an options and title for cleanliness