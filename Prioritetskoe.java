class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E>{

    Prioritetskoe(){super();}

    @Override
    public void leggTil(E x){
        Node nyNode = new Node(x);

        //hvis tom liste: 
        if (start == null){
            start = nyNode;
            return;
        }

        //hvis ikke-tom liste:
        Node peker = start;
        Node forrige = start;

        //elemenetet skal inn forst i en ikke-tom liste
        if(peker.element.compareTo(x) >=0){
            start = nyNode;
            nyNode.neste = peker;
            return;
        }
        
        //elementet skal ikke inn forst i lista:
        while(peker.element.compareTo(x)<0 && peker.neste !=null) {
            forrige = peker; 
            peker = peker.neste;
        }

        if (peker.element.compareTo(x)>0){
            forrige.neste = nyNode;
            nyNode.neste = peker;
            return;
        } else if (peker.element.compareTo(x)<=0){
            if(peker.neste==null){
                super.leggTil(x);
                return;
            } else {
                forrige = peker; 
                peker = peker.neste;
                forrige.neste = nyNode;
                nyNode.neste = peker;
                return;
            }
        }
    } 
}