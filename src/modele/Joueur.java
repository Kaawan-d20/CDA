package modele;

public class Joueur implements Comparable<Joueur> {
    /** Le nom du Joueur*/
    private String nom;
    /** Le nombre de victoires du Joueur*/
    private int nbVictoires;

    /**
     * Permet de créer un nouvel objet Joueur qui permet de stocker le nom et le nombre de victoires d'un joueur.
     *
     * @param nom : nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.nbVictoires = 0;
    }

    /**
     * Getter du nom.
     *
     * @return Le nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter du nombre de victoires.
     *
     * @return Le nombre de victoires du joueur
     */
    public int getNbVictoires() {
        return nbVictoires;
    }

    /**
     * Incrémente le nombre de victoires de 1.
     */
    public void incrementVictoires() {
        this.nbVictoires ++;
    }

    /**
     * Implémentation de l'interface Comparable<Joueur> qui permet
     * d'efficacement comparer le nombre de victoires des joueurs
     *
     * @return x < 0 si this < autreJoueur, x = 0 si this = autreJoueur, x > 0 si this > autreJoueur
     */
    @Override
    public int compareTo(Joueur autreJoueur) {
        return getNbVictoires() - autreJoueur.getNbVictoires();
    }
}
