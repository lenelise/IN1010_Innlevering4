class Stabel <E> extends Lenkeliste<E> {

    Stabel(){
        super();
    }

    @Override
    //nye elementer skal legges inn foerst i listen 
    public void leggTil (E x){
        Node nyNode = new Node(x);
        if (start == null){
            start = nyNode;
        } else {
            Node peker = nyNode;
            peker.neste = start; 
            start = nyNode;
        }
    }
}