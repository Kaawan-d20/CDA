package modele.p4;

import modele.abstrait.CoupP4;

public class CoupP4Rotation extends CoupP4 {
    private boolean sens;

    public CoupP4Rotation(boolean sens) {
        this.sens = sens;
    }

    public boolean getSens() {
        return sens;
    }
}
