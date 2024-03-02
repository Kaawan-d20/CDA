package modele;

public class Joueur implements Comparable<Joueur> {
    private String nom;
    private int nbVictoires;

    /**
     * Permet de créer un nouvel objet Joueur qui permet de stocker le nom et le nombre de victoire d'un joueur.
     *
     * @param nom : nom du joueur*/
    public Joueur(String nom) {
        this.nom = nom;
        this.nbVictoires = 0;
    }

    /**
     * Getter du nom.
     *
     * @return le nom du joueur*/
    public String getNom() {
        return nom;
    }

    /**
     * Getter du nombre de victoire.
     *
     * @return le nombre de victoire du joueur*/
    public int getNbVictoires() {
        return nbVictoires;
    }

    /**
     * Incrémente le nombre de victoires de 1.
     * */
    public void incrementVictoires() {
        this.nbVictoires ++;
    }

    /**
     * Implémentation de l'interface Comparable<Joueur> qui permet
     * d'efficacement comparer le nombre de victoires des joueurs
     *
     * @return x < 0 si this < o, x = 0 si this = o, x > 0 si this > o */
    @Override
    public int compareTo(Joueur o) {
        return getNbVictoires() - o.getNbVictoires();
    }
}
