/**
 * Vanlig: subclass of Legemiddel
 * @param navn String
 * @param pris int
 * @param virkestoff double 
 */

public class Vanlig extends Legemiddel{
    Vanlig(String navn, int pris, double virkestoff){
        super(navn,pris,virkestoff);
    }

    @Override
    public String toString(){
        return (legemiddelId + ": Vanlig legemiddel: " + super.navn + ". Pris: " + super.pris
        + ". Virkestoff mg: " + super.virkestoff );
    }
}