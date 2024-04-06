package modele;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import exception.NombreBatonnetsInvalide;
import exception.NumeroTasInvalide;

/**
 * Classe abstraite du plateau, n'est pas une interface pour que dans le controleur
 */
public abstract class Plateau {
    public abstract void reset();
    public abstract boolean verifierFin();
    public abstract void placerJeton(byte colonne, byte joueur) throws ColonnePleine, FormatReponseInvalide;
    public abstract boolean verifierVictoire();
    public abstract void retirerBatonnets(int m, int n) throws NombreBatonnetsInvalide, NumeroTasInvalide;
}
