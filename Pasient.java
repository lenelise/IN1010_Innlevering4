import java.util.ArrayList;

/**
 * Pasientklasse
 * @param SSN String, foedselsnummer. Final.
 * @param navn String. 
 * @param pasientId int, final, automatisk generert. 
 */

class Pasient{
    public final String SSN; //foedselsnr (Social security number)
    public String navn;
    public final int pasientId;
    public static int idTeller;
    ArrayList<Resept> resepter;

    Pasient(String navn, String SSN){
        this.navn = navn; 
        pasientId = idTeller; 
        idTeller++;
        this.SSN = SSN;
        resepter = new ArrayList<>();

    }

    public int hentId(){
        return pasientId;
    }

    public String hentSSN(){
        return SSN;
    }

    public String hentNavn(){
        return navn;
    }

    public void LeggTilResept(Resept resept){
        resepter.add(resept);
    }

    public void skrivResepter(){
        if (resepter.size()==0){
            System.out.println(navn+" har ingen aktive resepter.");
        } else{
            for (Resept resept:resepter){
                System.out.println(resept.toString());
            }
        }
    }

    @Override
    public String toString(){
        return pasientId+". Pasient: "+navn;
    }
}