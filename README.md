# blackjack
blackjack game for assignment 1

Speed21!

1) main file(Speed21.java),which is inside the package "Application", creates only the view(AppFrame.java)
2) AppFrame.java,a JFrame file which is inside "view" package, gets the model(GameEngineImpl) from "Registry.java" ,
and  creates callback by passing its reference as argument constructor when creating callback.
3)GameEngineCallbackImpl.java, inside "model.implementation" package, takes view as parameter.
4)Registry.java, inside "model.implementation",a single instance class that provides model(GameEngineImpl)


GUI guide:
=============
Button Explanation:
================
1) Three Menu Button(Game,Edit,Help)
Game -> Clear, Exit Game
Edit -> Add Player, Remove Player
Help -> AboutGame


2) Two button in toolbar(AddPlayer, Play Game)

3) Two "Add Player" button , one in toolbar and other in Edit menu

Game Explanation:
=====================

1) User can add player by pressing "Add Player"(either in tool bar or  from Edit menu). User can pass name and total points
2) User can play game by pressing "Play Game", that takes bet points before starting game.
3) User should provide bet amount for every game, and cannot skip bet points.
4) User can add only one Player for this assignment1.
5) User can remove player from "Edit menu".
6) User can add player again if he removes the existing player
7) User cannot play game until the player and bet amount is provided.
8) User can clear the view by pressing "Clear" from "Game" menu





