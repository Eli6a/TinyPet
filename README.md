# Rapport

Nous avons repris le code de [webandcloud](https://github.com/momo54/webandcloud) que nous avons modifié. 
Au niveau du backend, le code a été majoritairement ajouté dans la classe ScoreEndpoint, nous avons ajouté une classe Petition ainsi que UserP que nous n'utilisons pas encore.
Pour le frontend, les pages [new_petition.html](https://donneesmassives-402312.ew.r.appspot.com/new_petition.html) et [success_petition.html](https://donneesmassives-402312.ew.r.appspot.com/success_petition.html) ont été ajoutées, et la page [index.html](https://donneesmassives-402312.ew.r.appspot.com/index.html) a été renommé en [index1.html](https://donneesmassives-402312.ew.r.appspot.com/index1.html) afin de mettre la page qui aurait dû afficher les pétitions de notre base de données.
Cependant, nous avons oublié d'ajouter le lien vers la page [new_petition.html](https://donneesmassives-402312.ew.r.appspot.com/new_petition.html).

## Ce qui a été implémenté
Nous avons réussi à faire en sorte de pouvoir se connecter avec son compte google et de pouvoir ajouter des pétitions avec son nom de google. Lorsque la personne n'est pas connectée, elle est redirigée vers l'interface de connexion, puis sa pétition est enregistrée.

## Ce qui n'a pas été implémenté
Nous avons écrit le code pour signer une pétition, ajouter un utilisateur et vérifier si on l'a dans la base de donnée, mais comme nous n'avons pas réussi à faire fonctionner ce code, ce qui nous a contraint à commenter tout cela pour ne pas impacter ce qui fonctionne.

## Ce qui a été pensé
Les clés des pétitions (et des utilisateurs) ont été salé par un random de taille 8 pour éviter d'écrire au même endroit, et donc un hotspot. 

Pour pouvoir lister les pétitions signées par l'utilisateur, nous voulions stocker les identifiants des pétitions dans une liste signedPetition dans l'entité utilisateur et de seulement incrémenter le nombre de signature dans l'entité pétition. Nous voulions aussi incrémenter le nombre au niveau du frontend sans que la valeur ne soit forcément mise à jour immédiatement, en mettant un Thread.sleep() pour éviter un hotspot tout en donnant l'illusion que l'incrémentation se fasse instantanément. 

![image](https://github.com/Eli6a/TinyPet/assets/48087333/4a6e2a5d-2be2-4eb5-9312-92df5c2fdb00)
![image](https://github.com/Eli6a/TinyPet/assets/48087333/4fa48f90-0b00-4ba3-b533-41678d2a15e9)

