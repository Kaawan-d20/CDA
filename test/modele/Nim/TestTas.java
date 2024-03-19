package modele.Nim;

import exception.NombreBatonnetsInvalide;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTas {
    @Test
    public void testGetNombre(){
        int nombreBatonnet = 3;
        Tas tas = new Tas(nombreBatonnet);
        assertEquals(nombreBatonnet, tas.getNombre());
    }
    @Test
    public void testRetirerBatonnet(){
        int nombreBatonnet = 3;
        Tas tas = new Tas(nombreBatonnet);
        try{
            tas.retirerBatonnet(2);
        } catch (NombreBatonnetsInvalide ignored){
            fail("pas normal");
        }

        assertEquals(1, tas.getNombre());

        try{
            tas.retirerBatonnet(2);
            fail("pas normal");
        } catch (NombreBatonnetsInvalide ignored){}
        assertEquals(1, tas.getNombre());
    }
    @Test
    public void testEstVide(){
        int nombreBatonnet = 3;
        Tas tas = new Tas(nombreBatonnet);
        try{
            tas.retirerBatonnet(3);
        } catch (NombreBatonnetsInvalide ignored){
            fail("pas normal");
        }
        assertEquals(0,tas.getNombre());
    }
    @Test
    public void testToString(){
        int nombreBatonnet = 3;
        Tas tas = new Tas(nombreBatonnet);
        assertEquals("|||", tas.toString());
    }

}
