import java.lang.Math;

class Blaa extends Resept{

    Blaa(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
        super(legemiddel, utskrivendeLege, p, reit);
        legemiddel.pris = (int) Math.round(legemiddel.pris/4);
    }

    @Override
    public String farge(){
        return "Blaa";
    }

    @Override
    public String hentType(){
        return "blaa";
    }

    @Override 
    public int prisAaBetale(){
        return legemiddel.pris;
    }

    @Override
    public String toString(){
        return (this.farge() + " resept: " + legemiddel.toString() + ". Reit: " + super.reit + ". Utskrivende lege: " + utskrivendeLege.hentNavn());
    }
}