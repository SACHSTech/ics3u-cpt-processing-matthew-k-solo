[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=6695810&assignment_repo_type=AssignmentRepo)
# Mini Golf

## Objective of the game (what's the point of playing)
- Aim to get the ball in the hold as least times as possible
- It’s fun ! It's a fun minigolf game that lasts however long you want.

## Gameplay mechanics and/or user interaction
- You drag the ball, and try to aim it for the hole as little strokes as possible. Don’t hit the bunker or sand.
- If you land in the sand, your power will be subtracted by a random number.
- If you land in the water, your score will be given 2 points, and you will spawn back at the start.

## Scoring (if any)
- The score will be kept for every stroke
- Will be reset after a completed game.
- The pars will be added together, and the score you get on a hole will be compared to that. So if par is 4 and you get a 5, your score will have 1 added to it. Same if par is 3 and you get 1, 2 will be subtracted from it.
- If you land in the water, +2 is added

## Limitations (any important things your program doesn't do that is worth noting)
- There are no hills, unless i find a ton of free time that will allow me to program them in
- The levels are random so there's no sense of level design or specified difficulty.

## Description

The objective of my game is to score the golf ball into the hole, in the least amount of strokes possible. The game will randomly generate a course to play on consisting of two box like obstacles, and a bunker of sand or a water trap. The player will then drag a line in the opposite direction of where they want to send the ball, which when released will shoot the ball forward. Each stroke will be recorded, and saved to a total score for the game. When the ball is made into the hole, the ball will slowly shrink like it's falling in. I will randomly generate what “par” is (between 2-4) which will be used to create your final score.


The score will remain present in the corner. When you start the game, you will be presented with a menu screen that asks if you wanna play 3, 9, or 18 holes. When you’re done with the game you’ll be shown a screen that shows your score, then at a click of a button returns you to the menu.


