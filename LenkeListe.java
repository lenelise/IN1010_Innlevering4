import java.util.Iterator;

abstract class Lenkeliste<E> implements Liste<E>{

    protected Node start; 
    protected int antElementer;

    Lenkeliste(){
        start = null;
        antElementer = 0;
    }

    public boolean erTom(){
        return start==null;
    }

    @Override
    public Iterator<E> iterator(){
        return new LenkelisteIterator();
    }

    private class LenkelisteIterator implements Iterator<E>{
        Node currentNode = start;

        @Override
        public boolean hasNext(){
            return currentNode!=null;
        }

        @Override
        public E next(){
            Node returnNode = currentNode;
            currentNode = currentNode.neste;
            return returnNode.element;
        }
    }

    @Override 
    public int stoerrelse(){
        int teller = 0;
        
        if (start == null){
            return teller;
        } else{
            Node peker = start;
            while (peker.neste != null){
                teller++;
                peker = peker.neste;
            }
            teller = teller +1; //to account for the last node
            return teller;
        }
    }

    //Adding elements in the list, at the back
    @Override
    public void leggTil(E x){
        Node nyNode = new Node(x);

        if (start == null){
            start = nyNode;
            antElementer++;
        } else {
            Node peker = start;
            while(peker.neste != null){
                peker = peker.neste;
            }
            peker.neste = nyNode;
            antElementer++;
        }
    }

    //Get the first element in the list
    @Override
    public E hent(){
        return start.element;
    }

    //Remove and return the first element in the list
    @Override
    public E fjern(){
        if (start != null){
            Node peker = start;
            start = peker.neste;
            return peker.element;
        }
        throw new UgyldigListeindeks(0);
    }
    
    @Override
    public String toString(){
        String streng = "";
        if (start != null) {
            Node peker = start; 
            while (peker.neste != null){
                streng = streng + peker.element.toString() + "\n";//antar metoden finnes
                peker = peker.neste;
            }
            streng = streng + peker.element.toString();
            return streng;
        }
        throw new UgyldigListeindeks(0);
    }

    //Inner class
    protected class Node{
        Node neste;
        E element;

        Node (E x){
            neste = null;
            element = x;
        }
    }
}//avslutter klassen