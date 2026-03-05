# 🤖 Bot de Rappels Discord (Kotlin)

> **Ne manquez plus jamais un événement important sur votre serveur Discord.**

Ce bot Discord, développé en **Kotlin**, propose un système de gestion de rappels simple, puissant et entièrement intégré via les **Slash Commands** de Discord.

## 🚀 Fonctionnalités

Le bot permet une gestion complète (CRUD) des rappels directement dans l'interface Discord :

* ` /rappel` : Configurez un nouveau rappel personnalisé.
* ` /liste` : Affichez l'intégralité des rappels programmés sur le serveur.
* ` /modifier` : Ajustez l'heure ou le contenu d'un rappel existant.
* ` /supprimer` : Retirez un rappel de la base de données (via son ID).
* ` /invite` : Générez un lien pour ajouter le bot sur d'autres serveurs.

## 🛠️ Stack Technique

* **Langage :** [Kotlin](https://kotlinlang.org/) (JVM)
* **Bibliothèque :** [JDA (Java Discord API)](https://github.com/DV8FromTheWorld/JDA)
* **Gestion de projet :** Gradle
* **Persistance :** Sauvegarde automatique des données pour prévenir toute perte en cas de redémarrage.

## 📦 Installation & Configuration

1.  **Cloner le projet :**
    ```bash
    git clone [https://github.com/nonov1012/discord-reminder-bot.git](https://github.com/nonov1012/discord-reminder-bot.git)
    ```
2.  **Configuration :** Créez un fichier de configuration (ou variable d'environnement) avec votre **Token Discord Bot**.
3.  **Build :**
    ```bash
    ./gradlew build
    ```
4.  **Lancement :** Exécutez le fichier JAR généré.

## 👤 Développement Solo

Ce projet m'a permis d'approfondir mes connaissances sur :
* Le cycle de vie des applications asynchrones en **Kotlin**.
* L'interaction avec des APIs complexes et la gestion des permissions.
* La mise en place d'une logique de persistance de données robuste.

---
© 2026 - Bot de Rappels Discord. Développé avec soin par Moi même.
