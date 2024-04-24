package modele.abstrait;

import exception.*;

/**
 * Classe abstraite du plateau, n'est pas une interface pour que dans
 * le contrôleur puisse avoir un attribut commun de type Plateau
 * et donc peu prend soit un PlateauNim ou un PlateauP4
 * @see controleur.Controleur
 */
public abstract class Plateau {
    public abstract void reset();
    public abstract boolean verifierFin();

}
