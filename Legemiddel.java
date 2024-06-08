/**
 * Legemiddelklasse: Vanlig, vanedannende, narkotisk.
 * @param navn String
 * @param pris int
 * @param virkestoff double 
 * @param styrke int, not required for all subclasses
 * 
 */
abstract public class Legemiddel {
    public final int legemiddelId;
    public static int teller = 0; 
    public final String navn; 
    public int pris;
    public final double virkestoff; //enhet mg

    Legemiddel(String navn, int pris, double virkestoff){
        legemiddelId = teller; 
        teller++;
        this.navn = navn; 
        this.pris = pris; 
        this.virkestoff = virkestoff;
    }

    public int hentPris(){
        return pris;
    }

    public String hentNavn(){
        return navn;
    }

    public int hentStyrke(){
        return 0;
    }

    public int hentId(){
        return legemiddelId;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(int nyPris){
        this.pris = nyPris;
    }

    @Override
    public String toString(){
        return navn;
    }

}
