# Introduction #

Le projet consiste à utiliser les informations d’un profil MySpace afin de représenter graphiquement le réseau d’amis d’un profil ainsi que certaines de ses informations. Le but de cette application est de récupérer à partir d'un profil MySpace donné, l'ensemble des profils qui lui sont connectés (amis, amis d'amis, ...) jusqu'à une profondeur N donnée.

Les informations à récupérer seront par exemple :

> -	les amis
> -	tout ce qui concerne le profil (nom, prénom, date de naissance, …)
> -	les commentaires
> - Les centres d'intérêts

Les étapes :

> -	Aspirer un profil MySpace et les profils qui lui sont connectés
> -	Configurer l'aspiration en ajoutant des listes d'exclusion et un nombre maximum d'amis à partir duquel un profil n'est plus aspiré
> -	Stocker les données au format XML
> -	Ecrire une interface de navigation dans les données



# Fonctionnement #

L’interface Web proposera la saisie d’un ID MySpace (un membre), et une profondeur jusqu’à laquelle notre application parcourra les amis de l’ID saisi. Il sera vérifié que l'ID saisi correspond bien à un profil MySpace.

Une fois les informations récupérées, nous créons un fichier XML/JSON qu’on enregistre localement afin de pouvoir les retraiter. C’est l’objectif final de notre application qui est d’utiliser les données XML ainsi enregistrées pour représenter graphiquement le réseau d’amis d’un profil.

Nous utiliserons la librairie Java : WebExtractor (http://www-sop.inria.fr/edelweiss/wiki/wakka.php?wiki=Software) afin d’extraire les informations de la page MySpace une fois l’ID du profil envoyé.

Ensuite, à l’aide de cette librairie, nous extrairons les informations que nous souhaitons (profil, amis, commentaire, nombre de commentaire par amis, ...) et les enregistrerons dans un fichier XML/JSON. C’est à partir de ce fichier que nous construirons le graphe représentant le réseau d’amis du profil demandé.

Afin de représenter le graphe, nous utiliserons le projet "Javascript Information Visualization" (http://blog.thejit.org/javascript-information-visualization-toolkit-jit/) en Javascript. En effet, celui-ci étant simple à mettre en oeuvre, et proposant un design épuré, il conviendrait tout à fait à ce que l'on recherche (création de réseau d'amis)

Sur notre interface WEB, la page sera dynamique grâce à l'AJAX. Sans changer de page, la saisie de l'ID (après traitement des informations via MySpace), le graphe sera affiché à droite. Ce graphe représentera l'ensemble des amis du profil demandé et permettra de naviguer dans les amis en actualisant les amis de cet ami et ainsi de suite. C'est là qu'intervient le champ profondeur sur notre page WEB. C'est celui-ci qui déterminera le maximum d'amis à récupérer lors du "post" du formulaire.

Concernant la technologie AJAX utilisé, nous travaillerons principalement avec le framework jQuery de part sa légéreté, et son implémentation rapide pour les taches simples dont nous avons besoin (création de noeud, requête ajax à proprement parlé, ...)