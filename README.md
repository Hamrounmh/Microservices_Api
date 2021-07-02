# microservices
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
