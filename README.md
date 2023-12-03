# dai-lab04-SMTP
- [Description du projet](#description)
- [Instruction](#instruction)
- [Utilisation avec MailDev](#utilisation-avec-maildev)
- [Description de l'implémentation](#description-de-limplémentation)

## Description du projet
Le but de ce projet est d'implémenter partiellement un client SMTP afin d'automatiser l'envoie de blague par mail. Ceci est à but formatif et non de réellement envoyer des spams et devrait être utilisé uniquement dans un environement de test.

## Fonctionnement de l'application
SmtpPranker créé des groupes d'adresses email en leurs assignant un objet et un contenu. L'application va ensuite envoyer un email par groupe sur un serveur SMTP.
L'application utilise deux fichiers de configuration nommés victimes.txt et jokes.txt qui contiennent une liste d'adresses email et une liste d'objets et de contenus de mail.
Chaque groupe a un objet et un contenu différents des autres et une liste d'adresses email, la première étant celle du faux émetteur.

## Instruction

## Utilisation avec MailDev
Pour tester notre application, nous avons utilisé [MailDev](https://maildev.github.io/maildev/), qui est un serveur SMTP qui peut recevoir des mails sans les envoyer à des personnes réelles. Pour l'utiliser, il faut avoir Docker installé puis télécharger et lancer l'image avec la commande suivante:
```
docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev
```

Le serveur est atteignable sur l'adresse localhost et utilise le port 1025 pour la réception des mail et le port 1080 pour l'interface web permettant de consulter les mails envoyé.

## Utilisation de l'application
Pour utiliser l'application il faut commencer par compiler le code source pour avoir un package java .jar.  Il faut placer les deux fichiers de configuration dans un dossier "config" qui doit se trouver dans le même répertoire que le fichier .jar. Le fichier jokes.txt doit contenir un objet par ligne suivi d'un contenu de mail par ligne comme par exemple :
```
Object1
Content1
Object2
Content2
.
.
.
```
Pour exécuter l'application, lancer un terminal dans le répertoire où se trouve SmtpPranker-1.0.jar et faire la commande suivante en indiquant le nombre de groupe à créer :
```
java -jar ./SmtpPranker-1.0.jar <nb groupes>
```


## Description de l'implémentation
