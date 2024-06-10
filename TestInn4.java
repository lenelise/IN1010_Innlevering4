//Program for enhetstesting av Innlevering 4


class TestInn4{
    public static void main(String[] args) throws UgyldigHvitResept,UgyldigUtskrift{

        //TEST ITERERING
        Koe<String> koe1 = new Koe<>();
        koe1.leggTil("hei");
        koe1.leggTil("hallo");
        koe1.leggTil("hallaien");
        
        for (String thing:koe1){
            System.out.println(thing);
        }

        //TEST PASIENT:
        Pasient pasient1 = new Pasient("Lene-Lise", "1234567890");
        Pasient pasient2 = new Pasient("Agnes", "1234567890");
        System.out.println("Lene-Lise har pasiendID " + pasient1.hentId());
        System.out.println("Agnes har pasiendID " + pasient2.hentId());

        //TEST LEGE, RESEPTER
        Lege lege1 = new Lege("Elisabeth");
        Lege lege2 = new Lege("Arvid");
        Vanlig vanligLegemiddel1 = new Vanlig("Paracet", 50, 500);
        Vanlig vanligLegemiddel2 = new Vanlig("Ibux", 50, 500);
        Narkotisk narkotiskLegemiddel = new Narkotisk("Heroin", 500, 2,10);

        lege1.skrivHvitResept(vanligLegemiddel1,pasient1,3);
        lege1.skrivHvitResept(vanligLegemiddel2,pasient1,3);
        // lege1.skrivHvitResept(narkotiskLegemiddel,pasient1,3);
        for (Resept res:lege1.hentUtskrevneResepter()){
            System.out.println(res);
        }
    }
}