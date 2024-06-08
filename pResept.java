class pResept extends Hvit{
    private int rabatt = 108; 

    pResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
        super(legemiddel, utskrivendeLege, p, reit); 
        if (legemiddel.pris < rabatt){
            legemiddel.pris = 0; 
        } else {
            legemiddel.pris = legemiddel.pris - rabatt;
        }
    }

    @Override 
    public int prisAaBetale(){
        return legemiddel.pris;
    }

    @Override 
    public String hentType(){
        return "p";
    }

    @Override
    public String toString(){
        return ("P-resept: " + legemiddel.toString() + ". Reit: " + super.reit + ". Utskrivende lege: " + utskrivendeLege.hentNavn());
    }
}