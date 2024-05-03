package modele.joueur;

public abstract class IA extends Joueur{
    /**
     * Permet de créer un nouvel objet Joueur qui permet de stocker le nom et le nombre de victoires d'un joueur.
     *
     * @param nom : nom du joueur
     */
    public IA(String nom) {
        super(nom);
        isHuman = false;
    }
}
