class UgyldigListeindeks extends RuntimeException{
    UgyldigListeindeks(int indeks){
        super("Ugyldig indeks: " + indeks);
    }
}

class UgyldigUtskrift extends Exception{
    //Exception om en vanlig lege forsoker aa skrive ut Narkotisk legemiddel
    UgyldigUtskrift(Lege l, Legemiddel lm){
        super("Legen "+l.navn+" har ikke lov aa skrive ut "+lm.hentNavn());
    }
}

class UgyldigHvitResept extends Exception{
    //Exception om en vanlig lege forsoker aa skrive ut Narkotisk legemiddel
    UgyldigHvitResept(Legemiddel lm){
        super(lm.hentNavn()+ "kan ikke skrives ut på Hvit resept");
    }
}