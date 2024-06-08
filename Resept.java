/**
 * Resept. 
 * @param id int, unik, final, automatisk generert. 
 * 
 */

abstract class Resept{
    public final int reseptId; 
    public static int teller = 0; 

    Legemiddel legemiddel; 
    Lege utskrivendeLege; 
    // int pasientId;
    Pasient pasient;
    int reit;

    Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
        this.legemiddel = legemiddel; 
        this.utskrivendeLege = utskrivendeLege; 
        pasient = p;
        this.reit = reit;
        if (reit <=0){
            System.out.println("Resepten er ugyldig");
            System.exit(1);
        } else{
            this.reit = reit;
        }

        reseptId = teller; 
        teller++;

    }

    public int hentId(){
        return reseptId;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }
    
    public Lege hentUtskrivendeLege(){
        return utskrivendeLege;
    }

    public Pasient hentPasient(){
        return pasient;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        if (reit > 0){
            reit = reit -1;
            return true;
        } else {
            return false;
        }
    }

    abstract public String farge();

    abstract public String hentType();
    
    abstract public int prisAaBetale();

}
