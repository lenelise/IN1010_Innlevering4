/**
 * Nakrotisk: subclass of Legemiddel
 * @param navn String
 * @param pris int
 * @param virkestoff double 
 * @param styrke int
 */
class Narkotisk extends Legemiddel{
    public final int styrke; 

    Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn,pris,virkestoff);
        this.styrke=styrke;
    }

    public int hentStyrke(){
        return styrke;
    }

    @Override
    public String toString(){
        return (legemiddelId + ": Narkotisk legemiddel: " + super.navn + ". Pris: " + super.pris
        + ". Virkestoff mg: " + super.virkestoff + ". Styrke: " + styrke);
    }
}