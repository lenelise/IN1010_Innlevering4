/**
 * Legeklasse
 * @param navn String, navnet på legen. Maa oppgis ved initialisering av klasseinstans.
 * @param utskrevneResepter IndeksertListe over reseptene denne legen har skrevet ut. 
 */

class Lege implements Comparable<Lege>{
    String navn; 
    IndeksertListe<Resept> utskrevneResepter;

    Lege(String navn){
        this.navn = navn;
        utskrevneResepter = new IndeksertListe<>();    
    }

    public String hentNavn(){
        return navn;
    }

    public IndeksertListe<Resept> hentUtskrevneResepter(){
        return utskrevneResepter;
    }

    /**
     * Metode for en Lege aa skrive hvit resept til spesifisert pasient
     */
    public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UgyldigHvitResept{
        
        //Sjekk at legemiddel kan skrives ut paa hvit resept, dvs at legemidlet ikke er narkotisk
        if (legemiddel instanceof Narkotisk){
            throw new UgyldigHvitResept(legemiddel);
        }

        //Opprett resepten, oppdater reseptlista til lege og pasient: 
        Hvit nyResept = new Hvit(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept);
        pasient.LeggTilResept(nyResept);

        return nyResept;
    }

    public String hentKontrollkode(){
        return "0"; 
    }

    @Override
    public String toString(){
        return ("Lege: " + navn + ". Kontrollkode: 0");
    }

    @Override
    public int compareTo(Lege annenLege){
        return this.hentNavn().compareTo(annenLege.hentNavn());
    }
}