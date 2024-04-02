# Diagramme de cas d'utilisation


```Mermaid
graph LR
    Joueur[🧍‍♂️ Joueur] --- Sélectionner
    Sélectionner -. include .- Nim([Nim])
    Sélectionner -. include .- Puissance([Puissance 4])
```

# Scénario

## **UC : Sélectionner**
**Périmètre** : Le jeu <br>
**Niveau** : But utilisateur <br>
**Acteur principal** : Joueur<br>
**Pré-condition** : Aucune<br>
**Post-conditions** : Aucune

### Scénario nominal

1. L'utilisateur lance le programme
2. Le système demande à l'utilisateur à quel jeu il veux jouer
3. L'utilisateur répond à la question
4. UC: Nim

### Extensions

4. a L'utilisateur à répondu puissance 4
    1. UC : Puissance 4

4. b L'utilisateur à répondu autre chose
    1. Le système affiche un message d'erreur
    2. Retour au point 2 du scénario nominal







## **UC : Nim**
**Périmètre** : Le jeu <br>
**Niveau** : But utilisateur <br>
**Acteur principal** : Joueur<br>
**Pré-condition** : Aucune<br>
**Post-conditions** : Aucune

### Scénario nominal

1. L'utilisateur lance le programme
2. Le système demande à l'utilisateur le nombre de tas qu'il souhaite
3. L'utilisateur répond à la question
4. Le système enregistre le nombre de tas
5. Le système demande le nom du joueur 1
6. Le joueur 1 répond
7. Le système enregistre le nom du joueur 1
8. Le système demande au joueur 2
9. Le joueur 2 répond
10. Le système enregistre le nom du joueur 2
11. Le système demande au joueur le nombre maximal de bâtonnet retirer par coup
12. Le joueur répond
13. Le système enregistre la réponse du joueur
14. Le système affiche les tas ainsi que le nom du joueur qui doit jouer
15. Le joueur choisi dans quel tas il souhaite prendre un certain nombre d'allumette 
16. Le système vérifie si la partie est gagné
17. Le système affiche le vainqueur et demande si l'utilisateur souhaite refaire une partie ("y" or "n")
18. L'utilisateur répond "y" ou "n"
19. Le système retourne au point 11 du scénario nominal et incrément le classement entre les deux joueur

### Extensions

4. a Le joueur répond autre chose ou un nombre inférieur à 1
    1. Le système affiche un message d'erreur "le nombre de tas doit être un entier supérieur à 1"
    2. Retour au point 2 du scénario nominal

7. a L'utilisateur répond à la question avec une chaîne vide
    1. Le système affiche un message d'erreur "Le nom ne peut pas être vide"
    2. Retour au point 5 du scénario nominal

10. a L'utilisateur répond à la question avec une chaîne vide
    1. Le système affiche un message d'erreur "Le nom ne peut pas être vide"
    2. Retour au point 8 du scénario nominal

13. a Le joueur répond autre chose ou un nombre inférieur à 0 (peut être supérieur à quelque chose à définir)
    1. Le système affiche un message d'erreur "le nombre de tas doit être un entier supérieur à 0"
    2. Retour au point 11 du scénario nominal

16. a Le joueur n'a pas entré une réponse au format $m\ n$
    1. Le système affiche un message d'erreur "Le format de la réponse doit être $n\ m$"
    2. Retour au point 14 du scénario nominal avec le même joueur

16. b Le joueur a demandé un tas qui n'existe pas
    1. Le système affiche un message d'erreur "Tas inexistant"
    2. Retour au point 14 du scénario nominal avec le même joueur

16. c Le joueur veut prendre un nombre d'allumette supérieur au nombre contenue dans le tas
    1. Le système affiche un message d'erreur "Pas assez d'allumette dans ce tas"
    2. Retour au point 14 du scénario nominal avec le même joueur

16. d Le joueur veut prendre un nombre d'allumette supérieur à la limite
    1. Le système affiche un message d'erreur "Le nombre max est ..."
    2. Retour au point 14 du scénario nominal avec le même joueur

16. e La partie n'est pas fini 
    1. Retour au point 14 du scénario nominal avec l'autre joueur

19. a L'utilisateur à répondu autre chose que y ou n
    1. Le système affiche un message d'erreur "Répondre avec "y" ou "n""
    2. Retour au point 17 du scénario nominal

19. b L'utilisateur à choisi d’arrêter de jouer
    1. Le système affiche le classement entre les deux joueur
    2. Le système attend une action de l'utilisateur pour arrêter le programme









## **UC : Puissance 4**
**Périmètre** : Le jeu <br>
**Niveau** : But utilisateur <br>
**Acteur principal** : Joueur<br>
**Pré-condition** : Aucune<br>
**Post-conditions** : Aucune

### Scénario nominal

1.  L'utilisateur lance le programme
2.  Le système demande le nom du joueur 1
3.  Le joueur 1 répond
4.  Le système enregistre le nom du joueur 1
5.  Le système demande le nom du joueur 2
6.  Le joueur 2 répond
7.  Le système enregistre le nom du joueur 2
8.  Le système demande aux joueurs s'ils veulent activer la rotation 
9.  Le joueur répond
10.  Le système enregistre la réponse du joueur
11.  Le système affiche le plateau ainsi que le nom du joueur qui doit jouer
12.  Le joueur choisi dans quelle colonne il souhaite mettre un jeton 
13. Le système vérifie si la partie est gagné
14. Le système affiche le vainqueur et demande si l'utilisateur souhaite refaire une partie ("y" or "n")
15. L'utilisateur répond "y" ou "n"
16. Le système retourne au point 8 du scénario nominal et incrémente le nombre de partie


### Extensions

- 4.a L'utilisateur répond à la question avec une chaîne vide
    1. Retour au point 2 du scénario nominal

- 7.a L'utilisateur répond à la question avec une chaîne vide
    1. Retour au point 5 du scénario nominal


- 10.a l'utilisateur répond autre chose que 'y' ou 'n'
    1. le système affiche un message d'erreur
    2. retour au point 8 du scénario nominal

- 13.a L'utilisateur choisit un numéro de colonne $n \notin \lbrace x \in \N, \text{ tq } 1 \le x \le 7 \rbrace$
    1. Le système affiche un message d'erreur
    2. retour au point 11 du scénario nominal

- 13.b L'utilisateur choisit un numéro de colonne valide, mais la colonne est pleine
    1. Le système affiche un message d'erreur
    2. retour au point 11 du scénario nominal

- 13.c L'utilisateur choisit de faire tourner le plateau
    1. Le système vérifie que l'option est activée
        1. Le système affiche un message d'erreur
        2. retour au point 11 du scénario nominal
    2. Le système fait tourner la grille
    2. retour au point 13 du scénario nominal

- 13.a La partie n'est pas finie
    1. Le système change le joueur qui doit jouer
    2. retour au point 11 du scénario nominal

- 16.a l'utilisateur répond autre chose que 'y' ou 'n'
    1. le système affiche un message d'erreur
    2. retour au point 14 du scénario nominal

- 16.b L'utilisateur répond 'n'
    1. le système affiche le gagnant et le nombre de victoire du gagnant
        - Il n'y a pas de gagnant
            1. le système affiche ex æquo et le nombre de victoire des joueurs (il est identique pour les deux)
            2. Retour au point 2 de l'extension 16.b
    2. fin du programme



# Modèle de Conception


```mermaid
classDiagram
    class Main{ 
        + main() void
    }

    class Controleur {
        <<abstract>>
        # int numeroJoueurCourant = 0
        # int nombrePartie = 0

        # Joueur[]  lesjoueurs
        # Plateau plateau
        # Ihm ihm
        
        + Controleur(Ihm ihm)
        # initJoueur() void
        # tourDeJeu() void
        # tourSuivant() void
        # finPartie() void
        # getNumeroJoueurCourant() int
        # getJoueurCourant() Joueur
        # getNomJoueurCourant() String
    }

    class ControleurNim{
        + jouer() void
        # getCoup() void
        # victoire() void
    }

    class ControleurP4{
        + jouer() void
        # getCoup() void
        # victoire() void
    }

    Controleur <|-- ControleurNim
    Controleur <|-- ControleurP4

    class Plateau {<<abstract>>}
    class PlateauNim {
        - int nombreTas
        
        - Tas[] lesTas

        + PlateauNim(int nombreTas)
        + reset() void
        + setOption(String option) void
        + retirerBatonnets(int m, int n) void
        + verifierFin() bool
        + getPlateau() int[]
        + toString() String
    }

    class PlateauP4 {
        - byte[][] plateau
        - byte[] dernierCoup

        + PlateauP4()
        + reset() void
        + setOption(String option) void
        + rotation(bool sens) void
        + verifierFin() bool
        + verifierVictoire() bool
        + estPlein() bool
        + getPlateau() byte[][]
        + placerJeton(byte colonne, byte joueur) void
        + toString() String 

    }

    Plateau <|-- PlateauNim
    Plateau <|-- PlateauP4


    class Tas {
        - int nombreBatonnet

        + Tas (int nombreBatonnet)
        + retirerBatonnet(int n) void
        + getNombre() int
        + estVide() bool
        + toString() String
    }

    class Joueur {
        - String nom
        - int nbVictoires

        + Joueur(String nom)
        + getNom() String
        + getNbVictoires() int
        + incrementVictoires() void
        + compareTo(Joueur autreJoueur) int
    }





    class Ihm {
        <<abstract>>
        # Scanner scanner

        + Ihm()
        + demanderNomJoueur(int numJoueur) String
        + demanderJouerEncore() bool
        + afficherPlateau(String plateauString) void
        + afficherVictoire(String nomJoueur, int nbVictoires, int nbParties) void
        + afficherErreur(String message) void
    }  

    class IhmNim {
        + IhmNim()
        + demanderNbTas() int
        + demanderLimite() int
        + demanderCoupNim(String nomJoueur) int[]
    }

    class IhmP4 {
        + IhmP4()
        + demanderActivationRotation() bool
        + demanderCoupOuRotation(String nomJoueur) bool
        + demanderCoupP4(String nomJoueur) byte
        + demanderRotation(String nomJoueur) bool
    }

    Ihm <|-- IhmNim
    Ihm <|-- IhmP4


    Controleur --> "1" Plateau
    Controleur --> "1" Ihm
    Tas "1..n" --* PlateauNim : lesTas
    Controleur --> "2" Joueur : lesJoueurs
```


Et voici une idée de la structure (j'ai pas fait gaffe aux nom des méthodes, erreur)
```java

public class Controleur{
    public void initJoueur() {
        int nbJoueur = 2;
        lesJoueurs = new Joueur[nbJoueur];
        for (int i=0, i<nbJoueur, i++){
            lesJoueurs[i] = new Joueur(ihm.demanderNom());
        }
    }
    private void toursDeJeu() {
        nombrePartie += 1;
        numeroJoueurCourant = 1;
        plateau.reset();
        plateau.setOption(ihm.demanderOption());
        while (!plateau.verifierFin()) {
            tourSuivant();
            boolean estCoupCorrect = false;
            while (!estCoupCorrect) {
                try {
                    estCoupCorrect = true;
                    ihm.afficherPlateau(plateau.toString());
                    getCoup();
                } catch (CoupImpossible | FormatReponseInvalide exception) {
                    estCoupCorrect = false;
                    ihm.afficherErreur(exception.getMessage());
                }
            }
        }

        victoire();

        if (ihm.demanderJouerEncore()) {
            toursDeJeu();
        } else {
            finPartie();
        }
    }

}


public class ControleurNim{
    public void jouer() {
        initJoueur();
        plateau = new PlateauNim(ihm.demanderNbTas);
        tourDeJeu();
    }

    protected void getCoup(){
        int[] candidate = ihm.demanderCoupNim(getNomJoueurCourant());
        plateau.retirerBatonnets(candidate[0], candidate[1]);        
    }

    protected void victoire(){
        if (!plateau.verifierVictoire()){
            ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nbParties, true);
        } else {
            getJoueurCourant().incrementVictoires();
            ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nbParties, false);
        }
        ihm.afficherPlateau(plateau.toString());
    }
}



public class ControleurP4{
    public void jouer() {
        initJoueur();
        plateau = new PlateauP4();
        tourDeJeu();
    }

    protected void getCoup(){
        if (ihm.demanderCoupOuRotation()){
            byte candidate = ihm.demanderCoupP4(getNomJoueurCourant());
            plateau.placerJeton((byte) (candidate-1), (byte) (numeroJoueurCourant+1));       
        }
        else{
            boolean candidate = ihm.demanderRotation(getNomJoueurCourant()); //à définir que signifie true gauche ou droite
            plateau.rotation(candidate);
        }
    }
    protected void victoire(){
        getJoueurCourant().incrementVictoires();
        ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nbParties, false);

    }
}

```

# Répartition des tâches
## Itération 3

### 21/03/2024 (Publication du sujet)

Dany : Diagramme de classe, Cas d'utilisation

Nathan : Contrainte Nim et mise en commun

Agathe Contrainte Puissance 4

# Utilisation de l'IA
## Itération 3

- Nathan : Aucun Usage
- Dany : Aucun Usage
- Agathe : Aucun Usage