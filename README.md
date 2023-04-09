# TestTask22

Задание сделал на MySQL, а не на PostgreSQL, т.к. компьютер отказывается с ним работать! Пытался убедить его разными способами, не удалось.

GET| http://localhost:8080/teams/all - список всех команд 
GET| http://localhost:8080/teams/sport - список команд по виду спорта 
GET| http://localhost:8080/teams/period - список команд за определенный период 
GET| http://localhost:8080/members/team - список участников команды по индексу команды 
GET| http://localhost:8080/members/role - список участников в команде определенной роли 

POST| http://localhost:8080/teams/new - создание команды 
POST| http://localhost:8080/members/new - создание участника 

PUT| http://localhost:8080/members/transfer - перевод участника в другую команду 
PUT| http://localhost:8080/members/update - изменение данных участника 
PUT| http://localhost:8080/teams/update - изменение данных команды

DELETE| http://localhost:8080/members/delete - удаление участника по индексу 
DELETE| http://localhost:8080/teams/delete - удаление команды по индексу
