# Microservices :
Ce projet consiste a implémenter 3 micros services. 
### 1- geteway :
Ce micro-service est responsable du routage des micro-service sur notre api. Les éléments qui interviennent dans ce service sont dans la figure ci-dessous.

### 2- Service-auth :
Ce micro-service est responsable de l’authentification afin d’utiliser le service opérations. Dans la configuration il contient les méthode de génération des clé pour ensuite générer un token d’authentification. Il a comme model un utilisateur seulement.
Les classes de ce service sont affichées dans la figure ci-dessous.



### 3 -service-opérations :

Ce micro-service est responsable des opération C.R.U.D sur des opérations de change. De plus, nous avons implémenter dans la configuration Springs Security afin de gérer les droits et les rôles sur les méthode de ce service.
Ces droit et rôles sont définis dans le service auth ou les utilisateurs sont créé. Les opérations sont enregistrées dans une base de données H2.
Les méthode du contrôleur sont :
    • GetOpérationById : qui renvoie un Json contenant les données de l’opération
    • getAllOperations : qui renvoi un Json représentant toute les opération enregistrée dans la base de données H2
    • deleteOperation() : la méthode responsable de la suppression des opération
    • UpdateOperation : la méthode responsable de mettre ajour une opération .
    • Create() la méthode responsable de crée une opération a partir d’un fichier Json
donnée en entrée.
Dans la figure ci-dessous, le schéma représente les class intervenant dans le service de gestion des opération.



## Le client pour les tests :
Nous avons utilisé pour les tests un client http fournis par Intellij.
### A. L’authentification :
Cette image représente les requêtes que nous avons testé en local afin de gérer les authentification et les générations des clés.



## Les Opérations :
Dans cette image nous indiquons quelques requêtes que nous avons tester sur notre micro- service en local afin de tester les méthodes CRUD

## Docker :
Pour lancer les contenaires docker : 
- lancer le script du docker consul 
- packager les microservices dans Maven avec "mvn pckage"
- Docker Run.

## Exemple d'utilisation en local : 

## pour se connecter a l'api et générer le token 
POST http://localhost:8088/login
Content-Type: application/json

{"username": "user", "password": "user"}

> {% client.global.set("auth_token", response.headers.valueOf("Authorization")); %}
###
###Réponse : 
HTTP/1.1 200 OK
Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTYyNTI1NTc4MX0.Xtl7e8a7fg_kOBmha-MB28KGdSKXqxUJTVPuKLpKM-BvgCuHXXT1Kuvf2pyWz9TquPZzR1PWvucPiJT9tEdpyXSeOSe_DHIp2J7Fh3dQdzKlG-Vw09lqrzmV6cq1JEsODMPoZs6A9qYvlGf5qyaD-_LqhbZQ3vBSytchZECbznghTKHfSheyWaNm24djt1jOHoG9zHf0xbW5euG-GX4oAo_-h3t60FqvjDt02Qz5auLTnsFxLBfG4sxlWs9I5QLvF8DhkKeJ6zvPcoLLo4iJcdrIdCpxGcm1QDB1TzxpjfUd98BEfRK2LDvWd5BRZ9KI9Ujkio22hlu5M4NS2ZzuDw
content-length: 0

<Response body is empty>

Response code: 200 (OK); Time: 622ms; Content length: 0 bytes


## pour inserer ou mettre a jour une opération : 
requete : 

PUT http://localhost:8085/operations
Authorization: {{auth_token}}
Content-Type: application/json
Accept: application/json

{"id_transaction" : 1,
  "monnaieSource": "USD",
  "monnaieDestination" : "EUR",
  "taux": "1.2",
  "montant" : "6.00"
}
###
###result : 
PUT http://localhost:8085/operations

HTTP/1.1 201 Created
Location: http://localhost:8085/api/operations/1
Content-Type: application/json
Content-Length: 94
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1 ; mode=block
Referrer-Policy: no-referrer

{
  "taux": 1.2,
  "montant": 6.0,
  "id_transaction": 1,
  "monnaieSource": "USD",
  "monnaieDestination": "EUR"
}


## pour lister toute les opérations possibles: 
GET http://localhost:8085/operations
### resultats
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 191
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1 ; mode=block
Referrer-Policy: no-referrer

[
  {
    "taux": 1.2,
    "montant": 6.0,
    "id_transaction": 1,
    "monnaieSource": "USD",
    "monnaieDestination": "EUR"
  },
  {
    "taux": 1.2,
    "montant": 6.0,
    "id_transaction": 2,
    "monnaieSource": "USD",
    "monnaieDestination": "EUR"
  }
]


## pour recuperer une opération by id

request:
 GET http://localhost:8085/operations/1
## resultats
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 94
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1 ; mode=block
Referrer-Policy: no-referrer

{
  "taux": 1.2,
  "montant": 6.0,
  "id_transaction": 1,
  "monnaieSource": "USD",
  "monnaieDestination": "EUR"
}

Response code: 200 (OK); Time: 70ms; Content length: 94 bytes


## pour SUPPRIMER une opération by id

request:
 DELETE http://localhost:8085/operations/1
## resultats
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 94
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1 ; mode=block
Referrer-Policy: no-referrer

{
  "taux": 1.2,
  "montant": 6.0,
  "id_transaction": 1,
  "monnaieSource": "USD",
  "monnaieDestination": "EUR"
}

Response code: 200 (OK); Time: 70ms; Content length: 94 bytes
