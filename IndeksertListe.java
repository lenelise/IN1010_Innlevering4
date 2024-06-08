class IndeksertListe<E> extends Lenkeliste<E> {

    IndeksertListe(){super();}

    public void leggTil(int pos, E x){
        if (pos < 0) {throw new UgyldigListeindeks(pos);} 

        Node nyNode = new Node(x);

        if (super.erTom()){
            nyNode.indeks = 0;
            start = nyNode;
            antElementer++;   
            return;
        } 

        Node peker = start; 
        Node forrige = start;
        
        while(peker.indeks != pos && peker.indeks<pos && peker.neste != null){
            forrige = peker;
            peker = peker.neste;
        }

        if (pos == 0){
            //legges til forst i lista
            nyNode.indeks = 0;
            start = nyNode;
            nyNode.neste = peker;
        } else if (peker.neste == null && peker.indeks<pos){
            //legges til bak i lista, med ny indeks
            nyNode.indeks = pos;
            peker.neste = nyNode;
            antElementer++;
            return;
        } else if (peker.neste == null && peker.indeks>=pos){
            //legges til bak i lista, med lik indeks
            nyNode.indeks = pos;
            forrige.neste = nyNode;
            nyNode.neste = peker;
            peker.indeks = peker.indeks + 1;
            antElementer++;
            return;
        } else {
            // legges et sted midt i lista:
            forrige = peker;
            peker = peker.neste;
            forrige.neste = nyNode;
            nyNode.neste = peker;
            nyNode.indeks = pos;
        }

        //justere indeksene for elementene bak:
        while(peker.neste != null){
            peker.indeks++;
            peker = peker.neste;
        }

        peker.indeks++;//to account for the last Node in the list
        antElementer++;
        }

    public void sett (int pos, E x){
        if (pos < 0 || pos >= antElementer) {throw new UgyldigListeindeks(pos);} 

        Node forrige = start;
        Node nyNode = new Node(x);
        Node peker = start;
        
        while(peker.indeks != pos){
            forrige = peker;
            peker = peker.neste;
        }   

        nyNode.indeks = pos;
        forrige.neste = nyNode;
        nyNode.neste = peker.neste;
    } 

    public E hent(int pos){
        if (pos < 0) {throw new UgyldigListeindeks(pos);} 
        if (pos >= antElementer) {throw new UgyldigListeindeks(pos);} 
        Node peker = start; 
        while(peker.indeks != pos){
            peker = peker.neste;
        }
        return peker.element;
    }

    public E fjern (int pos){

        if (pos < 0) {throw new UgyldigListeindeks(pos);} 
        if (pos >= antElementer) {throw new UgyldigListeindeks(pos);} 
        
        Node peker = start;
        Node forrige = start;
        
        while(peker.indeks != pos){
            forrige = peker;
            peker = peker.neste;
        }
        
        if (peker.neste == null){
            //fjerner siste element
            forrige.neste = null;
        } else{ 
            forrige.neste = peker.neste;
        }
        E toReturn = peker.element;
        
        //maa justere indekser
        while (peker.neste != null){
            peker = peker.neste;
            peker.indeks = peker.indeks-1;
        }
        peker.indeks = peker.indeks-1;
        
        return toReturn;
    }
    
}