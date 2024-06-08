class milResept extends Hvit{

    milResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p){
        super(legemiddel, utskrivendeLege, p, 3); 
        legemiddel.pris=0; 
    }

    @Override 
    public int prisAaBetale(){
        return legemiddel.pris;
    }

    @Override
    public String hentType(){
        return "militaer";
    }

    @Override
    public String toString(){
        return ("Millitaer resept: " + legemiddel.toString() + ". Reit: " + super.reit + ". Utskrivende lege: " + utskrivendeLege.hentNavn());
    }
}