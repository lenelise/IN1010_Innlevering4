class Spesialist extends Lege implements Godkjenningsfritak{

    String kontrollkode; 

    Spesialist(String n, String k){
        super(n);
        kontrollkode = k; 
    }

    @Override 
    public String hentKontrollkode(){
        return kontrollkode; 
    }


    @Override
    public String toString(){
        return ("Lege: " + super.navn + ". Kontrollkode: " + kontrollkode);
    }
    
}