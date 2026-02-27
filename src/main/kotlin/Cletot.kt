import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent
import java.awt.Color
import java.util.*d


data class Joueur(var id: String, var score: Int = 0){
    override fun equals(other: Any?): Boolean {
        return if(other is Joueur)
            other.id == this.id
        else
            false
    }
}
var Question = 0
var Numero = 0
var Aleatoire = Random()
var Double = -1
var Channel = 0
var Verification = mutableListOf<Int>()
val listeDeJoueur = mutableListOf<Joueur>()
val BugReport = mutableListOf<String>()
var Serveur = "Erreur Serveur/ Reporte le bug avec le !s"
var PhraseSecret = Aleatoire.nextInt(10000)
fun main() {
    val api = DiscordApiBuilder().setToken("TOKEN").login().join()

    api.addMessageCreateListener { event ->

        if(event.messageContent.equals("!invite")){
            repond("${api.createBotInvite()}",event)
        }

        if(event.messageContent.equals("!aide",true)){
            val aide = EmbedBuilder()
                .setTitle("Aide")
                .setDescription("*Version 1.0*")
                .addInlineField("!regle", "Voir les règles du mini-jeux")
                .addInlineField("!s", "!s [Message] pour si vous avez une suggestion ou un report de bug ATTENTION voir si le bug est report dans !bug")
                .addInlineField("!quizz", "lancer le mini jeux")
                .addField("**------------------------------------------------------------------------------------------------**","°°°")
                .addField("!bug","Permet de voir la liste des bugs reporté")
                .setColor(Color.BLUE)
            event.message.channel.sendMessage(aide)
        }
        if(event.messageContent.equals("!regle",true)){
            val regle = EmbedBuilder()
                .addField("Cletot :","Ce mini-jeux dispos de 100 questions.")
                .addInlineField("Question :","Vous ne pouvez pas choisir le nombre de questions dans 1 partie (10 questions par partie)")
                .addInlineField("Points :", "Pour gagner des points il suffit de répondre le plus rapidement possible")
                .addInlineField("Scores :", "Un classement sera donné à la fin de la partie")
                .addField("—————————","Bon jeux")
                .setColor(Color.YELLOW)
            event.message.channel.sendMessage(regle)
        }

        if(event.messageContent.startsWith("!s")){
            api.getUserById(453874339721248781).get().openPrivateChannel().get().sendMessage("${event.messageAuthor.discriminatedName} : ${event.messageContent}")
            event.message.delete();
            event.message.userAuthor.get().openPrivateChannel().get().sendMessage("Merci d'avoir envoyé votre suggestion/report de bug \n Bonne journée ❤ ")
        }

        if(event.messageContent.startsWith(".bug") && event.messageAuthor.id == 453874339721248781){
            BugReport.add(event.messageContent)
            api.getUserById(453874339721248781).get().openPrivateChannel().get().sendMessage("Bug report !")
        }

        if(event.messageContent.equals("!bug",true)){
            var Bug = " "
            repond("**Liste De bug :**",event)
            for(element in BugReport) {
                Bug += "$element \n"
            }
            repond("$Bug",event)
        }
        if (event.messageContent.equals("!quizz", true)) {
            if (Question == 0) {
                Serveur = event.message.server.get().name
                Verification.clear()
                listeDeJoueur.clear()
                Channel = event.message.channel.id.toInt()
                Aleatoire(event)
            }else {
                repond("Le mini-jeux est déjà en cours sur le serveur: **$Serveur**", event)
            }
        }
        if (event.message.channel.id.toInt() == Channel) {
            when (Numero) {
                0 -> if(event.messageContent.equals("Chloe", true) || event.messageContent.startsWith("Chloé", true) || event.messageContent.startsWith("Chloé Bourgeois", true) || event.messageContent.startsWith("Chloe Bourgeois", true) ){
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Pollen transforme moi !  \uD83C\uDF89",event)}
                    Double = 0
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                1 -> if(event.messageContent.equals("Ladybug", true)) {
                    Double = 1
                    bonneReponse(event)
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 LuckyCharme ! \uD83C\uDF89",event)}
                }else mauvaiseReponse(event)
                2 -> if(event.messageContent.equals("Adrien", true) || event.messageContent.startsWith("Adrien Agreste", true)) {
                    Double = 2
                    bonneReponse(event)
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Plagg je suis amoureux de Ladybug !*\uD83C\uDF89",event)}
                }else mauvaiseReponse(event)
                3 -> if(event.messageContent.equals("Jalil", true) || event.messageContent.startsWith("Jalil Kubdel")) {
                    Double = 3
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Suis-je le seul à aimer la mythologie égyptienne ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                4 -> if(event.messageContent.equals("Alya", true) || event.messageContent.startsWith("Alya Cesaire", true) || event.messageContent.startsWith("Alya Césaire", true)) {
                    Double = 4
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Avez-vous consulté mon ladyblog ?! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                5 -> if(event.messageContent.equals("glaciator", true) || event.messageContent.startsWith("Glaciator 2.0", true)) {
                    Double = 5
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Qui veut couter à mes bonnes glaces ?? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                6 -> if(event.messageContent.equals("Thomas", true) || event.messageContent.startsWith("Thomas Astruc", true)) {
                    Double = 6
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le réalisateur ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                7 -> if(event.messageContent.equals("Manon", true) || event.messageContent.startsWith("Manon Chamack", true)) {
                    Double = 7
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis la Petite Licorne ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                8 -> if(event.messageContent.equals("Fluff", true)) {
                    Double = 8
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le miraculous du lapin ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                9 -> if(event.messageContent.equals("Animan", true)) {
                    Double = 9
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le gardien d'un zoo ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                10 -> if(event.messageContent.equals("Marinette", true) || event.messageContent.startsWith("Marinette Dupain Cheng", true) || event.messageContent.startsWith("Marinette Dupain-Cheng", true)) {
                    Double = 10
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Adrien ? je ... suis en retard pour l'école \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                11 -> if(event.messageContent.equals("Marc", true) || event.messageContent.startsWith("Marck Anciel", true)) {
                    Double = 11
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'adore écrire des histoires sur ladybug et chat noir \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                12 -> if(event.messageContent.equals("Sapotis", true)) {
                    Double = 12
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 On veut regarder la télévision ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                13 -> if(event.messageContent.equals("Sass", true)) {
                    Double = 13
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'ai le pouvoir d'une seconde chance ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                14 -> if(event.messageContent.equals("Maitre Fu", true)) {
                    Double = 14
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Qui vous a dit que j’étais le gardien des kwamis ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                15 -> if(event.messageContent.equals("Nooroo", true)) {
                    Double = 15
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Papillon est mon maitre ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                16 -> if(event.messageContent.equals("Numeric", true) || event.messageContent.equals("Numéric")) {
                    Double = 16
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Jagged Stone ? ou ça ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                17 -> if(event.messageContent.equals("Pire Noel", true) || event.messageContent.startsWith("Pire Noël", true)) {
                    Double = 17
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 je suis le Pire Noël....  \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                18 -> if(event.messageContent.equals("Penny", true) || event.messageContent.startsWith("Penny Rolling", true)) {
                    Double = 18
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Vous avez un rendez-vous avec Jagged Stone ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                19 -> if(event.messageContent.equals("Plagg", true)) {
                    Double = 19
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Miam du camembert ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                20 -> if(event.messageContent.equals("Aquatikki", true)) {
                    Double = 20
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Plagg ne m'appelle pas Sucrette! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                21 -> if(event.messageContent.equals("Gabriel", true) || event.messageContent.startsWith("Gabriel Agreste", true)) {
                    Double = 21
                    if (PhraseSecret == 1) {
                        repond(" \uD83C\uDF89 Rapporte-moi les miraculous ! \uD83C\uDF89", event)
                    }
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                22 -> if(event.messageContent.equals("Wayzz", true)) {
                    Double = 22
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'habite dans le bracelet de mon porteur ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                23 -> if(event.messageContent.equals("Pollen",true)) {
                    Double = 23
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le kwami de la domination ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                24 -> if(event.messageContent.equals("Duusu", true)) {
                    Double = 24
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 je suis le Miraculous du paon ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                25 -> if(event.messageContent.equals("Mullo", true)) {
                    Double = 25
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 je suis le Miraculous de la Souris ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                26 -> if(event.messageContent.equals("stompp", true)) {
                    Double = 26
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 j'habite dans un bijou et toi ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                27 -> if(event.messageContent.equals("Roarr", true)) {
                    Double = 27
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le kwami de l'exaltation ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                28 -> if(event.messageContent.equals("Longg", true)) {
                    Double = 28
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le kwami de la perfection et toi ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                29 -> if(event.messageContent.equals("Kaalki", true)) {
                    Double = 29
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 j'ai le pouvoir de me téléporter ,tu le sais le faire toi ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                30 -> if(event.messageContent.equals("Xuppu", true)) {
                    Double = 30
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis un singe ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                31 -> if(event.messageContent.equals("Aurore", true) || event.messageContent.equals("Aurore Beaureal", true) || event.messageContent.equals("Aurore Beauréal", true)) {
                    Double = 31
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 On me surnomme aussi la reine des glaces ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                32 -> if(event.messageContent.equals("Alec", true) || event.messageContent.equals("Alec Cataldi", true)) {
                    Double = 32
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis presenteur sur la chaine TVI et KIDZ+ ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                33 -> if(event.messageContent.equals("Alya", true) || event.messageContent.equals("Alya Cesaire", true) || event.messageContent.equals("Alya Césaire", true)) {
                    Double = 33
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu veux voir mon ladyblog ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                34 -> if(event.messageContent.equals("Armand", true) || event.messageContent.equals("Armand D'Argencourt", true) || event.messageContent.equals("Armand D Argencourt", true) || event.messageContent.equals("Armand Argencourt", true)) {
                    Double = 34
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis professeur d'escrime tu veux en faire partie ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                35 -> if(event.messageContent.equals("Clara", true) || event.messageContent.equals("Clara Rossignol", true)) {
                    Double = 35
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu as dû entendre parle de moi, je suis une célèbre chanteuse  \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                36 -> if(event.messageContent.equals("Roger", true) ||event.messageContent.equals("Roger Raincomprix", true)) {
                    Double = 36
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je te connais toi tu n’as pas respecté la loi numéros 19 lignes 10 ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                37 -> if(event.messageContent.equals("Dislocœur", true) || event.messageContent.equals("Dislocoeur", true) || event.messageContent.equals("le Dislocoeur",true) || event.messageContent.equals("Le Dislocœur",true)) {
                    Double = 37
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu veux venir nager avec moi ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                38 -> if(event.messageContent.equals("Silence", true) || event.messageContent.equals("Vérité", true) || event.messageContent.equals("Verite", true)) {
                    Double = 38
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu sais jouer de la guitare ? moi oui ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                39 -> if(event.messageContent.equals("Horrificator", true)) {
                    Double = 39
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Toi aussi tu adores la nature ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                40 -> if(event.messageContent.equals("Chronogirl", true)) {
                    Double = 40
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu veux faire une course de roller ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                41 -> if(event.messageContent.equals("Couffaine", true)) {
                    Double = 41
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu savais que j'etais Viperion ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                42 -> if(event.messageContent.equals("Césaire", true) || event.messageContent.equals("Cesaire",true)) {
                    Double = 42
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu as déjà gouté à ma cuisine ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                43 -> if(event.messageContent.equals("Roth", true)) {
                    Double = 43
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le plus grand producteur de musique ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                44 -> if(event.messageContent.equals("Césaire", true) || event.messageContent.equals("Cesaire",true)) {
                    Double = 44
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu connais ma soeur jumelle Etta ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                45 -> if(event.messageContent.equals("Manon", true) || event.messageContent.equals("Nadja",true)) {
                    Double = 45
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89On habite à Paris. \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                46 -> if(event.messageContent.equals("Alec", true)) {
                    Double = 46
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu veux faire partie de ma prochaine émission Miss météo ? sur KIDZ+ ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                47 -> if(event.messageContent.equals("Caline", true)) {
                    Double = 47
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis professeur au collège François Dupont ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                48 -> if(event.messageContent.equals("Roger", true) || event.messageContent.equals("Sabrina",true)) {
                    Double = 48
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Tu as RainComprix ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                49 -> if(event.messageContent.equals("Lila", true)) {
                    Double = 49
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis la meilleur ami de Ladybug ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                50 -> if(event.messageContent.equals("Clara", true)) {
                    Double = 50
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis journaliste ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                51 -> if(event.messageContent.equals("Ziggy", true)) {
                    Double = 51
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis sur le thème de la chèvre ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                52 -> if(event.messageContent.equals("Tikki", true)) {
                    Double = 52
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'ai le pouvoir de la création ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                53 -> if(event.messageContent.equals("Nooroo", true)) {
                    Double = 53
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Papillon est mon maitre ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                54 -> if(event.messageContent.equals("Trixx", true)) {
                    Double = 54
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'ai le pouvoir de l'illusion ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                55 -> if(event.messageContent.equals("Fluff", true)) {
                    Double = 55
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le Miraculous du Lapin ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                56 -> if(event.messageContent.equals("Orriko", true)) {
                    Double = 56
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis sur le theme du coq ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                57 -> if(event.messageContent.equals("Daizzi", true)) {
                    Double = 57
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le Miraculous du Cochon ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                58 -> if(event.messageContent.equals("Liiri", true)) {
                    Double = 58
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le kwami de la liberation ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                59 -> if(event.messageContent.equals("Auguste", true)) {
                    Double = 59
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'ai été akumatise en Gigantitan ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                60 -> if(event.messageContent.equals("Edgar", true)) {
                    Double = 60
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Cui-Cui ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                61 -> if(event.messageContent.equals("M. Damoclès", true) || event.messageContent.equals("M. Damocles") || event.messageContent.equals("M Damocles",true) || event.messageContent.equals("M Damoclès",true) || event.messageContent.equals("Damocles",true) || event.messageContent.equals("Damoclès",true)  || event.messageContent.equals("Monsieur Damocles",true)  || event.messageContent.equals("Monsieur Damoclès",true)) {
                    Double = 61
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le Hibou \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                62 -> if(event.messageContent.equals("M. Damoclès", true) || event.messageContent.equals("M. Damocles") || event.messageContent.equals("M Damocles",true) || event.messageContent.equals("M Damoclès",true) || event.messageContent.equals("Damcles",true) || event.messageContent.equals("Damoclès",true) || event.messageContent.equals("Monsieur Damocles",true)  || event.messageContent.equals("Monsieur Damoclès",true)) {
                    Double = 62
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Vous avez besoin de moi pour sauver le monde ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                63 -> if(event.messageContent.equals("markov", true)) {
                    Double = 63
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Max est mon meilleur ami ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                64 -> if(event.messageContent.equals("Anarka", true) || event.messageContent.equals("Anarka Couffaine")) {
                    Double = 64
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Mon bateau eet le Liberte ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                65 -> if(event.messageContent.equals("Mylène Haprèle", true) ||event.messageContent.equals("Mylène Haprele", true) ||event.messageContent.equals("Mylene Haprèle", true)  ||event.messageContent.equals("Mylene Haprele", true)  ||event.messageContent.equals("Mylène", true) ||event.messageContent.equals("Mylene", true)) {
                    Double = 65
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'ai etais transformé en Horrificator! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                66 -> if(event.messageContent.equals("Nathalie Sancœur", true) || event.messageContent.equals("Nathalie Sancoeur",true) || event.messageContent.equals("Nathalie",true)) {
                    Double = 66
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis Mayura ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                67 -> if(event.messageContent.equals("Bob Roth", true) || event.messageContent.equals("Bob",true)) {
                    Double = 67
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Mon fils est XY ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                68 -> if(event.messageContent.equals("André", true) || event.messageContent.equals("Andre")) {
                    Double = 68
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis vendeur de glaces ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                69 -> if(event.messageContent.equals("André Bourgeois", true) || event.messageContent.equals("Andre Bourgeois",true) || event.messageContent.equals("André",true) || event.messageContent.equals("Andre")) {
                    Double = 69
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis le maire de cette ville ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                70 -> if(event.messageContent.equals("Kagami Tsurugi", true) || event.messageContent.equals("Kagami",true)) {
                    Double = 70
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Marinette est ma meilleure amie ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                71 -> if(event.messageContent.equals("Rogercop", true)) {
                    Double = 71
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je vous donne l'ordre de gagner ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                72 -> if(event.messageContent.equals("Kung Food", true)) {
                    Double = 72
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Chloe à piège mon plat ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                73 -> if(event.messageContent.equals("Le Collectionneur", true) || event.messageContent.equals("Collectionneur",true)) {
                    Double = 73
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je me suis moi-même akumatise! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                74 -> if(event.messageContent.equals("Rossignoble", true)) {
                    Double = 74
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'adore la musique ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                75 -> if(event.messageContent.equals("Style Queen", true)) {
                    Double = 75
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Gabriel Agreste tu m'as humilié ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                76 -> if(event.messageContent.equals("Le Patineur", true) || event.messageContent.equals("Patineur",true)) {
                    Double = 76
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis Philipe entraineur en patinage tu veux rejoindre mon club ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                77 -> if(event.messageContent.equals("Gamer 2.0", true)) {
                    Double = 77
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Faisons une partie tu veux ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                78 -> if(event.messageContent.equals("Desperada", true)) {
                    Double = 78
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis Vivica ! la guitariste de Jagged Stone \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                79 -> if(event.messageContent.equals("Oblivio", true)) {
                    Double = 79
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 je vais vous faire perdre la mémoire ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                80 -> if(event.messageContent.equals("Volpina", true)) {
                    Double = 80
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis la sauveuse de Paris ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                81 -> if(event.messageContent.equals("Jean", true)) {
                    Double = 81
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je travail dans Le Grand Paris ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                82 -> if(event.messageContent.equals("Noël Lahiffe", true) || event.messageContent.equals("Noel Lahiffe",true) || event.messageContent.equals("Noel",true) || event.messageContent.equals("Noël",true)) {
                    Double = 82
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Vivement Noël ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                83 -> if(event.messageContent.equals("Tom", true)) {
                    Double = 83
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis boulanger, le meilleur de France ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                84 -> if(event.messageContent.equals("Sabine", true)) {
                    Double = 84
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis marchande !\uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                85 -> if(event.messageContent.equals("Emilie", true)) {
                    Double = 85
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Gabriel essayé de me réanime grace au miraculous de ladybug et chat noir ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                86 -> if(event.messageContent.equals("Otis", true)) {
                    Double = 86
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'ai 4 filles ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                87 -> if(event.messageContent.equals("Marlena", true)) {
                    Double = 87
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je suis la mere de Nora, Alya, Ella et Etta! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                88 -> if(event.messageContent.equals("Bleus", true) || event.messageContent.equals("Bleu",true) || event.messageContent.equals("Brun",true)) {
                    Double = 88
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 J'ai toujours la même coiffure aussi ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                89 -> if(event.messageContent.equals("Vert", true) || event.messageContent.equals("Verts",true)) {
                    Double = 89
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Comme sur la glace de marinette ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                90 -> if(event.messageContent.equals("cyan", true) || event.messageContent.equals("bleu",true) || event.messageContent.equals("bleus",true)) {
                    Double = 90
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Comme mes pointes de cheveux ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                91 -> if(event.messageContent.equals("Blond", true) || event.messageContent.equals("Blonds") || event.messageContent.equals("Blonde")) {
                    Double = 91
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Et mon parapluie est jaune ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                92 -> if(event.messageContent.equals("marron", true)) {
                    Double = 92
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Comme mes cheveux ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                93 -> if(event.messageContent.equals("rouge", true)) {
                    Double = 93
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Alors vous aimez mon foulard ? \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                94 -> if(event.messageContent.equals("Fluff", true)) {
                    Double = 94
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Je mesure 10cm ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                95 -> if(event.messageContent.equals("rose", true)) {
                    Double = 95
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Pauvre Emilie toujours endormis ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                96 -> if(event.messageContent.equals("rose", true)) {
                    Double = 96
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Ma couleur préférée est mon prénom ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                97 -> if(event.messageContent.equals("Bleu clair", true) || event.messageContent.equals("bleu",true) || event.messageContent.equals("bleus",true)) {
                    Double = 97
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Et mon pantalon est blanc ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                98 -> if(event.messageContent.equals("rouge", true)) {
                    Double = 98
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 etz mes yeux sont marron ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                99 -> if(event.messageContent.equals("Blanc", true)) {
                    Double = 99
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 \" Bye Bye petit akuma \" ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
                100 -> if(event.messageContent.equals("bleu",true) || event.messageContent.equals("bleus")) {
                    Double = 100
                    if(PhraseSecret == 1){repond(" \uD83C\uDF89 Et quand je suis Robostus elle est rouge ! \uD83C\uDF89",event)}
                    bonneReponse(event)
                }else mauvaiseReponse(event)
            }}}
}
fun repond(message: String,event: MessageCreateEvent){
    event.message.channel.sendMessage(message)}

fun bonneReponse(event: MessageCreateEvent) {
    event.message.reply("Bravo ${event.message.author.displayName} vous avez trouvé la bonne réponse")
    AjoutPoint(event)
    Secret()
    val random = Random()
    val React = random.nextInt(5)
    when (React) {
        0 -> event.message.addReaction("\uD83D\uDC4D")
        1 -> event.message.addReaction("\uD83E\uDD73")
        2 -> event.message.addReaction("\uD83D\uDE03")
        3 -> event.message.addReaction("\uD83D\uDCAF")
        4 -> event.message.addReaction("\uD83E\uDD70")
    }
    if(Question < 10){
        repond("Prochaine question dans 5 secondes",event)
    }else repond("Résultat des scores dans 5 secondes",event)
    Thread.sleep(5000L)
    Aleatoire(event)
}

fun mauvaiseReponse(event: MessageCreateEvent){
    if(event.message.author.isYourself || event.messageContent.equals("!quizz",true)) {}else if(Channel == event.message.channel.id.toInt()){event.message.addReaction("❌")      }

}

fun Aleatoire(event: MessageCreateEvent) {
    if(Question < 10){
        Numero = Aleatoire.nextInt(101)
        if(Verification.contains(Numero)){
            Aleatoire(event)
        }else ChoixQuestion(event)
    }else {
        repond("**Score :**", event)
        Score(event)
        Question = 0
        Numero = -1
    }
}
fun ChoixQuestion(event: MessageCreateEvent) {
    Verification.add(Numero)
    Question++
    repond("**Question numero $Question/10**",event)
    when(Numero) {
        0 -> repond("*Qui est Queen Bee ?*", event)
        1 -> repond("*Qui est Marinette ?*", event)
        2 -> repond("Qui est Chat Noir ?", event)
        3 -> repond("*Qui se transforme en Le Pharaon?*", event)
        4 -> repond("*A qui partient le kwami Trixx*", event)
        5 -> repond("*En quoi André le glacier est akumatise ?*", event)
        6 -> repond("*Qui est Animaestro*", event)
        7 -> repond("*Qui est la fille de Nadja Chamack*", event)
        8 -> repond("*Quel est le nom du Miraculous du Lapin*", event)
        9 -> repond("*Otis akumatisé en qui ?*", event)
        10 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/c/c2/Marinette_Dupain-Cheng_Square.png/revision/latest/top-crop/width/200/height/150?cb=20201230013607",
                event
            )
        }
        11 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/4/4c/Marc_Anciel_Square_2.png/revision/latest/scale-to-width-down/350?cb=20211016170658",
                event
            )
        }
        12 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/8/8c/Sapotis_Square.png/revision/latest/scale-to-width-down/1000?cb=20180207163306",
                event
            )
        }
        13 -> {
            repond("*Qui est ce*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/6/69/Sass_Square.png/revision/latest?cb=20210914093458",
                event
            )
        }
        14 -> repond("*Qui a cree le Festin ?*", event)
        15 -> repond("*Quel est le kwami du Papillon ?*", event)
        16 -> {
            repond("*Qui est ce*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/2/29/Pixelator_Square.png/revision/latest/scale-to-width-down/115?cb=20190808183241",
                event
            )
        }
        17 -> {
            repond("*Qui est ce*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/c/c9/Santa_Claws.png/revision/latest/scale-to-width-down/150?cb=20171029095705&path-prefix=fr",
                event
            )
        }
        18 -> {
            repond("*Qui est ce*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/3/3b/Penny_Rolling_Square.png/revision/latest/scale-to-width-down/115?cb=20190808180915",
                event
            )
        }
        19 -> {
            repond("*Qui est ce*", event)
            repond(
                "https://www.animationsource.org/sites_content/miraculous_ladybug/upload/fanchars/pic_detail588228761806e.png",
                event
            )
        }
        20 -> {
            repond("*Qui est ce*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/d/d7/Aqua_tlki.png/revision/latest?cb=20180712160049&path-prefix=fr",
                event
            )
        }
        21 -> repond("*Qui est le Papillon ?*", event)
        22 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/4/4b/Wayzz_Square.png/revision/latest?cb=20190105001917",
                event
            )
        }
        23 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/9/9a/Pollen_Carr%C3%A9.webp/revision/latest?cb=20210427230428&path-prefix=fr",
                event
            )
        }
        24 -> {
            repond("*Qui est ce* ?", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/e/ed/Duusu_Square_2.png/revision/latest?cb=20210601090330",
                event
            )
        }
        25 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/8/8d/Mullo_Square.png/revision/latest?cb=20210914090327",
                event
            )
        }
        26 -> {
            repond("*Qui est ce* ?", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/6/60/Stompp_Carr%C3%A9.png/revision/latest?cb=20210421173526&path-prefix=fr",
                event
            )
        }
        27 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/4/4d/Roaar_Square.png/revision/latest?cb=20210827190759",
                event
            )
        }
        28 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/4/45/Longg_Square.png/revision/latest?cb=20191207110824",
                event
            )
        }
        29 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/4/42/Kaalki_Square.png/revision/latest?cb=20210914091916",
                event
            )
        }
        30 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/d/d6/Xuppu_Square.png/revision/latest?cb=20210830024605",
                event
            )
        }
        31 -> repond("*Qui est akumatisé en Climatika ?*", event)
        32 -> repond("*Qui est l'Exauceur ?*", event)
        33 -> repond("*Qui est Lady Wifi ?*", event)
        34 -> repond("*Qui est akumatisé en Chevalier Noir ?*", event)
        35 -> repond("*Qui est Rossignoble*", event)
        36 -> repond("*Qui est akumatisé en Rogercop ?*", event)
        37 -> repond("*En qui Kim Chien Le est akumatisé ?*", event)
        38 -> repond("*En qui Luka Couffaine est akumatisé ?*", event)
        39 -> repond("*En qui Mylène Haprèle est akumatisé ?*", event)
        40 -> repond("*En qui Alix Kubdel est  akumatisé ?*", event)
        41 -> repond("*Quel est le nom famille de Luka ?*", event)
        42 -> repond("*Quel est le nom famille de Marlena ?*", event)
        43 -> repond("*Quel est le nom famille de Bob ?*", event)
        44 -> repond("*Quel est le nom famille de Ella?*", event)
        45 -> repond("*Quel est le prénom pour Chamack ?*", event)
        46 -> repond("*Quel est le prénom pour Cataldi ?*", event)
        47 -> repond("*Quel est le prénom pour Bustier?*", event)
        48 -> repond("*Quel est le prénom pour Raincomprix ?*", event)
        49 -> repond("*Quel est le prénom pour Rossi ?*", event)
        50 -> repond("*Quel est le prénom pour Contard ?*", event)
        51 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/a/a3/Ziggy_Square.png/revision/latest?cb=20210914091530",
                event
            )
        }
        52 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/d/d1/Tikki_Carr%C3%A9.webp/revision/latest?cb=20210427024556&path-prefix=fr",
                event
            )
        }
        53 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/b/be/Nooroo.png/revision/latest/top-crop/width/360/height/450?cb=20160413224335&path-prefix=fr",
                event
            )
        }
        54 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/6/6a/Trixx_Square.png/revision/latest?cb=20181230195613",
                event
            )
        }
        55 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/d/d6/Fluff_Square.png/revision/latest?cb=20191207122932",
                event
            )
        }
        56 -> {
            repond("*Qui est ce ?*", event)
            repond("https://i.pinimg.com/236x/c2/d1/53/c2d153e9e6cc95b1068773a1e6ed1bc5.jpg", event)
        }
        57 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/5/5e/Daizzi_Carr%C3%A9_2.webp/revision/latest?cb=20210502220522&path-prefix=fr",
                event
            )
        }
        58 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/5/58/Liiri.jpg/revision/latest?cb=20201002213356&path-prefix=fr",
                event
            )
        }
        59 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/e/eb/August_Square.png/revision/latest/scale-to-width-down/350?cb=20210422105013&path-prefix=fr  ",
                event
            )
        }
        60 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/e/eb/Pi.png/revision/latest/scale-to-width-down/310?cb=20210523213105&path-prefix=fr",
                event
            )
        }
        61 -> repond("*Qui est le principal du college Francois Dupont ?*", event)
        62 -> repond(
            "*Qui dit :  \"Excuse moi, jeune homme. Personne ne t'a appris à toquer la porte ?\"  ?*",
            event
        ) //M. Damoclès
        63 -> repond("*Comment se nomme le robot de Max Kante*", event) //markov
        64 -> repond("*Qui dit \" Le désordre c'est la vie \" ?*", event)  //Anarka Couffaine
        65 -> repond(
            "*Qui dit \"Je suis désolée. Je crois que ce serait mieux pour tout le monde que je quitte le film... \"?*",
            event
        )//Mylène Haprèle
        66 -> repond(
            "*Qui dit : \"Adrien, non, s'il vous plaît ! Vous savez bien que votre père s'y oppose !\"*",
            event
        )//Nathalie Sancœur
        67 -> repond(
            "*Qui dit : \"Alors écoute ! Ma maison de disque a pour objectif de rester numéro 1 des ventes ! \"*",
            event
        )//Bob Roth
        68 -> repond(
            "*Qui dit : \"Salut ! Qu'est-ce que je vous sert ? Ça vous dirait, une glace, pour le dessert ?\"*",
            event
        )//André
        69 -> repond(
            "*Qui dit : \"Dois-je vous rappeler qu'en tant que maire de cette ville, je suis votre supérieur ? \"*",
            event
        )//André Bourgeois
        70 -> repond(
            "*Qui dit : \" Adrien ? Pas d'inquiétude, mère. Avec lui j'ai tout ce qu'il faut pour réussir.\"*",
            event
        )//Kagami Tsurugi
        71 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/2/2d/Rogercop_Carr%C3%A9.png/revision/latest/scale-to-width-down/880?cb=20190713105027&path-prefix=fr",
                event
            )
        }//Rogercop
        72 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/5/54/Kung_Food_Carr%C3%A9.png/revision/latest/scale-to-width-down/115?cb=20190716064412&path-prefix=fr",
                event
            )
        }//Kung Food
        73 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/a/a2/The_Collector_Square.png/revision/latest/scale-to-width-down/150?cb=20210421170944",
                event
            )
        }//Le Collectionneur
        74 -> {
            repond("*Qui est ce ?*", event)
            repond("https://static.wikia.nocookie.net/lady-bug/images/0/0f/Frightningale_Square.png/revision/latest/scale-to-width-down/115?cb=20180927182259", event)
        }//Rossignoble
        75 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/miraculousladybug/images/5/59/Style_Queen_Square.png/revision/latest/scale-to-width-down/115?cb=20190108170542&path-prefix=fr",
                event
            )
        }//Style Queen
        76 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/0/06/Frozer_Square.png/revision/latest/scale-to-width-down/115?cb=20200501230109",
                event
            )
        }//Le Patineur
        77 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/f/fd/Gamer_Square.png/revision/latest/scale-to-width-down/115?cb=20190801202256",
                event
            )
        }//Gamer 2.0
        78 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/b/b9/Desperada_Square.png/revision/latest/scale-to-width-down/115?cb=20210827201437",
                event
            )
        }//Desperada
        79 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/e/e5/Oblivio_Square_3.png/revision/latest/scale-to-width-down/115?cb=20190811155601",
                event
            )
        }//Oblivio
        80 -> {
            repond("*Qui est ce ?*", event)
            repond(
                "https://static.wikia.nocookie.net/lady-bug/images/8/87/VolpinaWep.png/revision/latest/scale-to-width-down/115?cb=20160319161937",
                event
            )
        }//Volpina
        81 -> repond("*Quel est le prenom du majordome de Chloe ?*",event)//jean
        82 -> repond("*Qui est le frere de Nino Lahiffe ?*",event)//Noël Lahiffe
        83 -> repond("*Comment se nomme le pere de Marinette Dupain-Cheng ?*",event)//Tom
        84 -> repond("*Comment se nomme la mere de Marinette Dupain-Cheng ?*", event)//Sabine
        85 -> repond("*Comment se nomme la mere de Adrien Agreste ?*",event)//Emilie
        86 -> repond("*Comment se nomme le pere de Alya ?*",event)//Otis
        87 -> repond("*Comment se nomme la mere de Nora ?*",event)//Marlena
        88 -> repond("*De quelle couleurs sont les yeux de Marinette Dupain-Cheng?*",event)//Bleus
        89 -> repond("*De quelle couleurs sont les yeux de Adrien ?*",event)//Vert
        90 -> repond("*De quelle couleurs sont les yeux de Luka ?*",event)//cyan - bleu
        91 -> repond("*De quelle couleurs sont les cheveux de Aurore Beaureal*",event)//Blond
        92 -> repond("*De quelle couleurs est la moustache de Armand D'Argencourt ?*",event)//marron
        93 -> repond("*De quelle couleurs est le foulard d'Andre ?*",event)//rouge
        94 -> repond("*Quelle est le miraculous de Alix Kubdel*", event)//Fluff
        95 -> repond("*Quelle fleur est sur l'epaule droit de Emilie Agreste ?*",event)//rose
        96 -> repond("*Quelle est la couleur prefere de Rose ?*",event)//Rose
        97 -> repond("*De quel couleur est le t-shirt de Alec Cataldi est-il ?*",event)//Bleu clair
        98 -> repond("*De quelle couleurs est la casquette de Nino Lahiffe?*",event)//Rouge
        99 -> repond("*De quelle couleurs est le papillon avant d'etre transforme en akuma ?*",event)//Blanc
        100 -> repond("*De quelle couleurs est l'helice de markov ?*",event)//bleu
    }
}

fun AjoutPoint(msg: MessageCreateEvent){
    val newJoueur = listeDeJoueur.find { it.id == msg.messageAuthor.idAsString }

    if (newJoueur == null) {
        listeDeJoueur.add(Joueur(msg.messageAuthor.idAsString))
        AjoutPoint(msg)
    }else
        newJoueur.score++
}

fun Score(msg: MessageCreateEvent){
    var stats = ""
    listeDeJoueur.sortedByDescending { it.score }.forEach {
        stats += "<@${it.id}> : ${it.score} \n"
    }
    msg.channel.sendMessage(stats)
}

fun Secret(){
    PhraseSecret = Aleatoire.nextInt(10000)
}
