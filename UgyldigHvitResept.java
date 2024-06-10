class UgyldigHvitResept extends Exception{
    //Exception om en vanlig lege forsoker aa skrive ut Narkotisk legemiddel
    UgyldigHvitResept(Legemiddel lm){
        super(lm.hentNavn()+ "kan ikke skrives ut på Hvit resept");
    }
}