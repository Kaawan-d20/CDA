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
4. Le système demande à l'utilisateur s'il est seul ou à deux
5. L'utilisateur répond à la question
6. UC: Nim

### Extensions

4\. b L'utilisateur à répondu autre chose

1. Le système affiche un message d'erreur
2. Retour au point 2 du scénario nominal

6\. a L'utilisateur à répondu puissance 4

1. UC : Puissance 4






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

4\. a Le joueur répond autre chose ou un nombre inférieur à 1
1. Le système affiche un message d'erreur "le nombre de tas doit être un entier supérieur à 1"
2. Retour au point 2 du scénario nominal

7\. a L'utilisateur répond à la question avec une chaîne vide
1. Le système affiche un message d'erreur "Le nom ne peut pas être vide"
2. Retour au point 5 du scénario nominal

8\. a Le joueur joue seul
1. Retour au point 11 du scénario nominal

10\. a L'utilisateur répond à la question avec une chaîne vide
1. Le système affiche un message d'erreur "Le nom ne peut pas être vide"
2. Retour au point 8 du scénario nominal

13\. a Le joueur répond autre chose ou un nombre inférieur à 0
1. Le système affiche un message d'erreur "la limite doit etre un entier superieur ou égal à 0"
2. Retour au point 11 du scénario nominal

16\. a Le joueur n'a pas entré une réponse au format $m\ n$
1. Le système affiche un message d'erreur "Le format de la réponse doit être $n\ m$"
2. Retour au point 14 du scénario nominal avec le même joueur

16\. b Le joueur a demandé un tas qui n'existe pas
1. Le système affiche un message d'erreur "Tas inexistant"
2. Retour au point 14 du scénario nominal avec le même joueur

16\. c Le joueur veut prendre un nombre d'allumette supérieur au nombre contenue dans le tas
1. Le système affiche un message d'erreur "Pas assez d'allumette dans ce tas"
2. Retour au point 14 du scénario nominal avec le même joueur

16\. d Le joueur veut prendre un nombre d'allumette supérieur à la limite
1. Le système affiche un message d'erreur "Le nombre max est ..."
2. Retour au point 14 du scénario nominal avec le même joueur

16\. e La partie n'est pas fini 
1. Retour au point 14 du scénario nominal avec l'autre joueur

19\. a L'utilisateur à répondu autre chose que y ou n
1. Le système affiche un message d'erreur "Répondre avec "y" ou "n""
2. Retour au point 17 du scénario nominal

19\. b L'utilisateur à choisi d’arrêter de jouer
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

4\. a L'utilisateur répond à la question avec une chaîne vide
1. Le système affiche un message d'erreur "Le nom ne peut pas être vide"
2. Retour au point 2 du scénario nominal

5\. a Le joueur joue seul
1. Retour au point 8 du scénario nominal

7\. a L'utilisateur répond à la question avec une chaîne vide
1. Le système affiche un message d'erreur "Le nom ne peut pas être vide"
2. Retour au point 5 du scénario nominal


10\. a l'utilisateur répond autre chose que 'y' ou 'n'
1. le système affiche un message d'erreur
2. retour au point 8 du scénario nominal

13\. a L'utilisateur choisit un numéro de colonne entre 1 et 7
1. Le système affiche un message d'erreur
2. retour au point 11 du scénario nominal

13\. b L'utilisateur choisit un numéro de colonne valide, mais la colonne est pleine
1. Le système affiche un message d'erreur
2. retour au point 11 du scénario nominal

13\. c L'utilisateur choisit de faire tourner le plateauNim
1. Le système vérifie que l'option est activée
    1. Le système affiche un message d'erreur
    2. retour au point 11 du scénario nominal
2. Le système fait tourner la grille
3. retour au point 11 du scénario nominal

13\. a La partie n'est pas finie
1. Le système change le joueur qui doit jouer
2. retour au point 11 du scénario nominal

16\. a l'utilisateur répond autre chose que 'y' ou 'n'
1. le système affiche un message d'erreur
2. retour au point 14 du scénario nominal

16\.b L'utilisateur répond 'n'
1. le système affiche le gagnant et le nombre de victoire du gagnant
    - Il n'y a pas de gagnant
        1. le système affiche ex æquo et le nombre de victoire des joueurs (il est identique pour les deux)
        2. Retour au point 2 de l'extension 16.b
2. fin du programme



# Modèle de Conception


```mermaid


classDiagram
    direction BT

    class Controleur {
        <<abstract>>
    + Controleur() 
    # Plateau plateau
    # Ihm ihm
    # Joueur[] lesJoueurs
    # int nombrePartie
    # int numeroJoueurCourant
    # getIA() IA
    # getCoup() void
    # setOption() void
    # getJoueurCourant() Joueur
    # finPartie() void
    # tourSuivant() void
    # getNomJoueurCourant() String
    + jouer() void
    # toursDeJeu() void
    # victoire() void
    # initJoueur() void
    }
    class ControleurNim {
    + ControleurNim(Ihm) 
    + jouer() void
    # victoire() void
    # getIA() IA
    # setOption() void
    # getCoup() void
    }
    class ControleurP4 {
    + ControleurP4(Ihm) 
    # victoire() void
    # setOption() void
    # getIA() IA
    + jouer() void
    # getCoup() void
    }
    class Coup {
        <<abstract>>
    + Coup() 
    }
    class CoupNim {
    + CoupNim(int, int) 
    + CoupNim(int[]) 
    - int tas
    - int nbBatonnets
    + getNbBatonnets() int
    + getTas() int
    + toString() String
    + equals(Object) boolean
    + getCoup() int[]
    }
    class CoupP4 {
        <<abstract>>
    + CoupP4() 
    # byte joueur
    + getJoueur() byte
    + setJoueur(int) void
    + isRotation() boolean
    }
    class CoupP4Coup {
    + CoupP4Coup(byte, int) 
    + CoupP4Coup(byte, byte) 
    + CoupP4Coup(byte) 
    - byte colonne
    - byte ligne
    + setLigne(byte) void
    + getColonne() byte
    # copie() CoupP4Coup
    + toString() String
    + isRotation() boolean
    + getLigne() byte
    + equals(Object) boolean
    }
    class CoupP4Rotation {
    + CoupP4Rotation(boolean) 
    + CoupP4Rotation(boolean, byte) 
    - boolean sens
    + toString() String
    + equals(Object) boolean
    + getSens() boolean
    + isRotation() boolean
    }

    class IA {
        <<abstract>>
    # IA() 
    }
    class IANim {
    + IANim() 
    - demanderCoupAleatoire(PlateauNim) CoupNim
    - demanderCoupGagnant(PlateauNim) CoupNim?
    + demanderCoup(PlateauNim) CoupNim
    }
    class IAP4 {
    + IAP4() 
    - CoupP4Rotation[] lesRotationsAdverses
    - int[] scoreTailleIa
    - CoupP4Rotation[] lesRotations
    - CoupP4Coup[] lesPlacements
    - CoupP4Coup[] lesPlacementsAdverses
    - int[] scoreTailleJoueur
    + demanderCoup(PlateauP4) CoupP4
    }
    class Ihm {
    + Ihm() 
    - Scanner scanner
    + afficherVictoire(String, int, int, boolean) void
    + demanderNomJoueur(int) String
    + demanderJeu() boolean
    + demanderJouerEncore() boolean
    + demanderCoupNim(String) CoupNim
    + demanderRotation(String) CoupP4Rotation
    + demanderActivationRotation() boolean
    + afficherCoup(Coup) void
    + afficherErreur(String) void
    + demanderSeulOuMulti() boolean
    + demanderCoupP4(String) CoupP4Coup
    + demanderNbTas() int
    + demanderCoupOuRotation(String) boolean
    + setOptionNim() int
    + afficherPlateau(String) void
    }

    class Joueur {
    + Joueur(String) 
    # String nom
    # boolean isHuman
    # int nbVictoires
    + isHuman() boolean
    + compareTo(Joueur) int
    + incrementVictoires() void
    + getNom() String
    + getNbVictoires() int
    }
    class Main {
    + Main() 
    + main(String[]) void
    }
    class Plateau {
        <<abstract>>
    + Plateau() 
    + verifierFin() boolean
    + reset() void
    }
    class PlateauNim {
    + PlateauNim(int) 
    + PlateauNim(int[]) 
    - int maxBatonnets
    - boolean isLimite
    - int nombreTas
    - Tas[] lesTas
    + toString() String
    + reset() void
    + isLimite() boolean
    + retirerBatonnets(CoupNim) void
    + setOption(int) void
    + verifierFin() boolean
    + getPlateau() int[]
    + getMaxBatonnets() int
    }
    class PlateauP4 {
    + PlateauP4(byte[][]) 
    + PlateauP4(byte[][], CoupP4Coup, boolean, boolean, byte[]) 
    + PlateauP4() 
    - boolean rotations
    - byte[] nbRotations
    - byte[][] plateau
    - boolean wasRotation
    - CoupP4Coup dernierCoup
    + estPlein() boolean
    - rotationHoraire() void
    + compterMaxJetonsAlignes(CoupP4Coup) int
    + toString() String
    + setRotations(boolean) void
    + isRotations(int) boolean
    + verifierVictoireCase(CoupP4Coup) byte
    - rotationAntiHoraire() void
    + copie() PlateauP4
    + placerJeton(CoupP4Coup) void
    + verifierFin() boolean
    - getLignePosition(CoupP4Coup) byte[][]
    + reset() void
    + verifierVictoire() byte
    + rotation(CoupP4Rotation) void
    + getPlateau() byte[][]
    }

    class Tas {
    + Tas(int) 
    - int nombreBatonnet
    + getNombre() int
    + retirerBatonnet(int) void
    + toString() String
    + estVide() boolean
    }


    ControleurNim  --|>  Controleur 
    ControleurP4  --|>  Controleur 
    CoupP4  --|>  Coup 
    CoupP4Coup  --|>  CoupP4 
    CoupP4Rotation  --|>  CoupP4 
    CoupNim  --|>  Coup 
    IA  --|>  Joueur 
    IANim  --|>  IA 
    IAP4  --|>  IA 
    PlateauNim  --|>  Plateau 
    PlateauP4  --|>  Plateau




    Controleur --> "1" Plateau 
    Controleur --> "1..*" Joueur : lesJoueurs
    Controleur --> "1" Ihm 
    Ihm  ..>  Coup : «create»
    IA  ..>  Coup : «create»
    PlateauNim *--> "1..*" Tas : lesTas
``` 



# Répartition des tâches
## Itération 4

### 24/03/2024 (Fin des exams)

Dany : Refactor (des problèmes de l'itération 3), IA Nim (Aléatoire)

Nathan : IA Puissance 4

Agathe : IA Nim (Gagnant)

# Utilisation de l'IA
## Itération 4

- Nathan : Aucun Usage
- Agathe : Aucun Usage
- Dany : Aucun Usage
