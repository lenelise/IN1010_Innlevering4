class Hvit extends Resept{

    Hvit(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
        super(legemiddel, utskrivendeLege, p, reit);
    }

    @Override
    public String farge(){
        return "Hvit";
    } 

    @Override
    public int prisAaBetale(){
        return legemiddel.pris;
    }

    @Override 
    public String hentType(){
        return "hvit";
    }

    @Override
    public String toString(){
        return (reseptId + ": " + this.farge() + " resept: " + legemiddel.toString() + ". Reit: " + super.reit + ". Utskrivende lege: " + utskrivendeLege.hentNavn());
    }
 }