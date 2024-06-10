class IndeksertListe<E> extends Lenkeliste<E> {

    IndeksertListe(){super();}

    public void leggTil(int pos, E x){
        if (pos < 0) {throw new UgyldigListeindeks(pos);} 

        Node nyNode = new Node(x);

        if (super.erTom()){
            start = nyNode;
            antElementer++;   
            return;
        } 

        Node peker = start; 
        Node forrige = start;
        int indeks = 0;
        
        while(indeks != pos && indeks<pos && peker.neste != null){
            forrige = peker;
            peker = peker.neste;
            indeks++;
        }

        if (pos == 0){
            //legges til forst i lista
            start = nyNode;
            nyNode.neste = peker;
        } else if (peker.neste == null && indeks<pos){
            //legges til bak i lista, med ny indeks
            peker.neste = nyNode;
            antElementer++;
            return;
        } else if (peker.neste == null && indeks>=pos){
            //legges til bak i lista, med lik indeks
            forrige.neste = nyNode;
            nyNode.neste = peker;
            antElementer++;
            return;
        } else {
            // legges et sted midt i lista:
            forrige = peker;
            peker = peker.neste;
            forrige.neste = nyNode;
            nyNode.neste = peker;
        }

        antElementer++;
        }


    public void sett (int pos, E x){
        if (pos < 0 || pos >= antElementer) {throw new UgyldigListeindeks(pos);} 

        Node forrige = start;
        Node nyNode = new Node(x);
        Node peker = start;
        int indeks = 0;
        
        while(indeks != pos){
            forrige = peker;
            peker = peker.neste;
            indeks++;
        }   

        forrige.neste = nyNode;
        nyNode.neste = peker.neste;
    } 

    public E hent(int pos){
        if (pos < 0) {throw new UgyldigListeindeks(pos);} 
        if (pos >= antElementer) {throw new UgyldigListeindeks(pos);} 
        Node peker = start; 
        int indeks = 0; 
        while(indeks != pos){
            peker = peker.neste;
            indeks++;
        }
        return peker.element;
    }
    public E fjern (int pos){

        if (pos < 0) {throw new UgyldigListeindeks(pos);} 
        if (pos >= antElementer) {throw new UgyldigListeindeks(pos);} 
        
        Node peker = start;
        Node forrige = start;
        int indeks = 0; 
        
        while(indeks != pos && peker.neste != null){
            forrige = peker;
            peker = peker.neste;
            indeks++;
        }
        
        if (indeks == antElementer-1){
            //fjerner siste element
            forrige.neste = null;
        } else if (indeks==0){
            //fjerner forste element
            start = peker.neste;
        } else{ 
            forrige.neste = peker.neste;
        }

        return peker.element;
    }
    
}