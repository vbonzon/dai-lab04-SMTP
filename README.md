# dai-lab04-SMTP
- [Description du projet](#description)
- [Instruction](#instruction)
- [Utilisation avec MailDev](#utilisation-avec-maildev)
- [Description de l'implémentation](#description-de-limplémentation)

## Description
Le but de ce projet est d'implémenter partiellement un client SMTP afin d'automatiser l'envoie de blague par mail. Ceci est à but formatif et non de réellement envoyer des spams et devrait être utilisé uniquement dans un environement de test.

## Instruction

## Utilisation avec MailDev
Pour tester notre application, nous avons utilisé [MailDev](https://maildev.github.io/maildev/), qui est un serveur SMTP qui peut recevoir des mails sans les envoyer à des personnes réelles. Pour l'utiliser, il faut avoir Docker installé puis télécharger et lancer l'image avec la commande suivante:
```
docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev
```

Le serveur est atteignable sur l'adresse localhost et utilise le port 1025 pour la réception des mail et le port 1080 pour l'interface web permettant de consulter les mails envoyé.


## Description de l'implémentation