
# Game HangMan
It is the implementation of popular game HangMan, you can read more about the game and rule [here](https://en.wikipedia.org/wiki/Hangman_(game).
## Description
In this project you can play game hangman. For start game you need run project and authorize with default user(login:user,password:user). And after 
will appear buttons. One of this button is "Start game" to play, click this button. Game started, and it gives for you seven chance to guess word by selecting available letter on below.
After seizing every chance, game shows secret word and you will be allow to click button "Open Games", it show your all played games.

If you want to login as role Manager, you can authorize with default user(login:admin,password:user). In manager page shows all players with statistics of won games, failed games, lost games.
And you can able to add player by indicating username and password also delete.
On below show list of word of game, here also you able to add and delete.
##Instructions to run project
* You need to create new database and user(give all privileges to write, update, create, delete), if you do not have Postgres download! Here the example [video](https://www.youtube.com/watch?v=e1MwsT5FJRQ) how to use postgres.
* Download the IntelJ and open this project.
* Indicate your database url, name, user in file [application.properties](src/main/resources/application.properties)
* And run your project.
###### Attention your port 8080 must be free, if you want to run other in port, don't forget to change ports in frontend javascript [part](src/main/resources/static/front.js).
###Authorization
* ##### To authorize as Manager role login:admin password:user
* ##### To authorize as Player role login:user password:user
### Privileges of role Manager
* Can dd player indicating with username and password.
* Can dd word for game hangman.
* Can see statistics of players.
* Can delete word
* Can delete players
### Privileges of role Player
* Can play game.
* Can see all own games.
* Can see more details of games.
# Technologies Used
* ##### Spring boot
* ##### Hibernate
* ##### Postgres
* ##### Ajax 
* ##### Blowfish for encryption passwords




 
