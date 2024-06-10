class UgyldigUtskrift extends Exception{
    //Exception om en vanlig lege forsoker aa skrive ut Narkotisk legemiddel
    UgyldigUtskrift(Lege l, Legemiddel lm){
        super("Legen "+l.navn+" har ikke lov aa skrive ut "+lm.hentNavn());
    }
}