package modele.joueur;

/**
 * Classe représentant un joueur
 */
public class Joueur implements Comparable<Joueur> {
    /** Le nom du Joueur*/
    protected String nom;
    /** Le nombre de victoires du Joueur*/
    protected int nbVictoires;

    protected boolean isHuman;


    /**
     * Permet de créer un nouvel objet Joueur qui permet de stocker le nom et le nombre de victoires d'un joueur.
     *
     * @param nom : nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.nbVictoires = 0;
        this.isHuman = true;
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
     * Implémentation de l'interface Comparable qui permet
     * d'efficacement comparer le nombre de victoires des joueurs
     *
     * @return un int représentant la comparaison
     */
    @Override
    public int compareTo(Joueur autreJoueur) {
        return getNbVictoires() - autreJoueur.getNbVictoires();
    }

    public boolean isHuman() {
        return isHuman;
    }
}
