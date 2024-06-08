/**
 * Vanedannende: subclass of Legemiddel
 * @param navn String
 * @param pris int
 * @param virkestoff double 
 * @param styrke int
 */

public class Vanedannende extends Legemiddel{
    public final int styrke; 
    public static int antallVandedannendeResepter=0; //teller antall resepter av vanedannende legemidler

    Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn,pris,virkestoff);
        this.styrke=styrke;

    }

    public int hentStyrke(){
        return styrke;
    }

    @Override
    public String toString(){
        return (legemiddelId + ": Vanedannende legemiddel: " + super.navn + ". Pris: " + super.pris
        + ". Virkestoff mg: " + super.virkestoff + ". Styrke: " + styrke);
    }
}