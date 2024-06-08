import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

//en kommentar her

/**
 * Klasse  med metoder for hvert valg i menyen til Legesystem.java, i tillegg til en metode for å lese inn fil
 */
class SystemValg{

    /**
     * Metode for å lese inn fil, opprette objekter og legge disse til resepektive lister.
     */
    public static void LesInnFil() throws UgyldigHvitResept,FileNotFoundException, UgyldigUtskrift{
        //Les inn fil: 
        File fil = new File("legedata.txt");
        Scanner filscanner = new Scanner(fil);

        //Leser fila og fyller de fire listene initialisert over
        int teller = 0;
        while(filscanner.hasNextLine()){
            String line = filscanner.nextLine();
            char[] lineCharacters = line.toCharArray();

            if (lineCharacters[0]=='#'){
                teller++;
            }
            else if(teller == 1){
                //PASIENTLISTE
                String[] lineArray = line.split(",");
                String navn = lineArray[0].trim();
                String SSN = lineArray[1].trim();
                Pasient nyPasient = new Pasient(navn,SSN);
                Legesystem.pasientListe.leggTil(nyPasient);
            }
            else if (teller == 2){
                //LEGEMIDDELLISTE
                String[] lineArray = line.split(",");
                String navn = lineArray[0].trim();
                String legemiddelType = lineArray[1].trim();
                int pris = Integer.parseInt(lineArray[2].trim());
                double virkestoff = Double.parseDouble(lineArray[3].trim());
                int styrke=0;
                if (lineArray.length==5){
                    styrke = Integer.parseInt(lineArray[4].trim());
                }
                Legemiddel nyttLegemiddel;

                if (legemiddelType.equals("narkotisk")){
                    nyttLegemiddel = new Narkotisk(navn,pris,virkestoff,styrke); 
                } else if (legemiddelType.equals("vanedannende")){
                    nyttLegemiddel = new Vanedannende(navn,pris,virkestoff,styrke);
                } else if (legemiddelType.equals("vanlig")){
                    nyttLegemiddel = new Vanlig(navn,pris,virkestoff);
                } else {
                    System.out.println("Ugyldig legemiddeltype, hoppes over..");
                    break;
                }

                Legesystem.legemiddelListe.leggTil(nyttLegemiddel);
                
                } 
            else if (teller == 3){
                //LEGELISTE OK
                String[] lineArray = line.split(",");
                String navn = lineArray[0];
                String kontrollkode = lineArray[1];
                Lege nyLege;
                if(kontrollkode.equals("0")){
                     nyLege = new Lege(navn); //vanlig lege
                } else{
                    nyLege = new Spesialist(navn, kontrollkode); //Spesialist
                }
                Legesystem.legeListe.leggTil(nyLege);

            } else if (teller == 4){
                //RESEPTLISTE, KUN HVIT PER NÅ
                String[] lineArray = line.split(",");
                int legemiddelNummer = Integer.parseInt(lineArray[0].trim());
                String utskrivendeLegeNavn = lineArray[1];
                int pasientId = Integer.parseInt(lineArray[2].trim());
                String typeResept = lineArray[3];
                int reit=-1;
                if (lineArray.length==5){
                    reit =  Integer.parseInt(lineArray[4].trim());
                }

                if (typeResept.equals("hvit")){
                    //finn riktig Lege-objekt i legelista
                    Lege utskrivendeLege = null;
                    for(Lege lege:Legesystem.legeListe){
                        if (lege.hentNavn().equals(utskrivendeLegeNavn)){
                            utskrivendeLege = lege;
                        }
                    }
                    Legemiddel legemiddel = Legesystem.legemiddelListe.hent(legemiddelNummer);
                    Pasient pasient = Legesystem.pasientListe.hent(pasientId);
                    
                    //Skriv ut resepten og legg til reseptlista:
                    Resept nyResept = utskrivendeLege.skrivHvitResept(legemiddel,pasient,reit);
                    Legesystem.reseptListe.leggTil(nyResept);
                }
            } 
            else{
                System.out.println("Noe har gaatt alvorlig galt under lesing av fil..");
                System.exit(1);
            }
            // filscanner.close();
        }//avslutter while
    }


    //VALG 1:
    /**
     * Metode for å skrive ut all informasjon i lege-, pasient-, legemiddel- og reseptliste.
     */
    public static void SkrivInformasjon(){
        System.out.println("");
        System.out.println("PASIENTLISTA:");
        System.out.println(Legesystem.pasientListe.toString());
        System.out.println();
        
        System.out.println("LEGELISTA:");
        System.out.println(Legesystem.legeListe.toString());
        System.out.println();
        
        System.out.println("LEGEMIDDELLISTA:");
        System.out.println(Legesystem.legemiddelListe.toString());
        System.out.println();
        
        System.out.println("RESEPTLISTA:");
        System.out.println(Legesystem.reseptListe.toString());
        System.out.println();
        
    }

    //VALG 2: 
    /**
     * Metode for aa opprette nye elementer, og legge de til passende lister.
     * Parameteren elementType lar bruker avgjoere hvilken type objekt hen vil opprette.
     * @param elementType String, (lege, pasient, legemiddel, resept)
     */
    public static void OpprettElement(String elementType) throws UgyldigUtskrift,UgyldigHvitResept{
        System.out.println("");
        Scanner s = new Scanner(System.in);

        //OPPRETT LEGE:
        if (elementType.equals("lege")){
            System.out.println("Legens navn: ");
            String navn = s.nextLine();
            System.out.println("Legens kontrollkode (tast 0 hvis legen ikke har kontrollkode): ");
            String kontrollkode = s.nextLine();
            Lege lege;
            if (kontrollkode.equals("0")){
                lege = new Lege(navn);
            } else {
                lege = new Spesialist(navn, kontrollkode);
            }
            Legesystem.legeListe.leggTil(lege);
        } 
        
        //OPPRETT PASIENT
        else if (elementType.equals("pasient")){ 
            System.out.println("Pasientens navn: ");
            String navn = s.nextLine();
            System.out.println("Pasientens SSN: ");
            String SSN = s.nextLine();
            Pasient pasient = new Pasient(navn, SSN);
            Legesystem.pasientListe.leggTil(pasient);
            
        } 
        
        //OPPRETT RESEPT (KUN HVIT MULIG PR NAA)
        else if (elementType.equals("resept")){
            System.out.println("Type resept (hvit/blaa/mil/p) - kun hvit mulig per naa: ");
            String typeResept = s.nextLine();

            //sjekker at hvit er valgt:
            if (typeResept.equals("blaa") || typeResept.equals("mil")||typeResept.equals("p")){
                System.out.println("Det finnes ikke digital loesning for andre resepter enn hvite.");
                return;
            } else if (!typeResept.equals("hvit")){
                System.out.println("Ugyldig resepttype: " + typeResept);
                return;
            }
            
            //oppretter hvit resept:
            if (typeResept.equals("hvit")){
                System.out.println("Velg utskrivende lege (navn): ");
                System.out.println(Legesystem.legeListe.toString());
                String legeNavn = s.nextLine();
                Lege utskrivendeLege = null;
                for (Lege dr:Legesystem.legeListe){
                    if (dr.hentNavn().equals(legeNavn)){
                        utskrivendeLege = dr;
                        break;
                    }
                }

                //Sjekker at input er gyldig legenavn: 
                if (utskrivendeLege==null){
                    System.out.println("Ugyldig lege: " + legeNavn);
                    return;
                }

                //Typecaster hvis legeobjektet er spesialist:
                if (!utskrivendeLege.hentKontrollkode().equals("0")){
                    utskrivendeLege = (Spesialist) utskrivendeLege;
                }

                //velg pasient fra lista
                System.out.println("Velg pasient (nr):");
                System.out.println(Legesystem.pasientListe.toString());
                int pasientNr = Integer.parseInt(s.nextLine());
                Pasient pasient = Legesystem.pasientListe.hent(pasientNr);

                //velg legemiddel fra lista
                System.out.println("Velg legemiddel (nr):");
                System.out.println(Legesystem.legemiddelListe.toString());
                int legemiddelNr = Integer.parseInt(s.nextLine());
                Legemiddel legemiddel = Legesystem.legemiddelListe.hent(legemiddelNr);

                //opprett resept:
                Resept resept = utskrivendeLege.skrivHvitResept(legemiddel,pasient,3);
                Legesystem.reseptListe.leggTil(resept);               
            }

        } 
        
        //OPPRETT LEGEMIDDEL
        else if(elementType.equals("legemiddel")){
            System.out.println("Hvilken type (vanlig/vanedannende/narkotisk)?");
            String legemiddelType = s.nextLine();
            if(!(legemiddelType.equals("vanlig")||legemiddelType.equals("vanedannende")||legemiddelType.equals("narkotisk"))){
                System.out.println("Ugyldig legemiddeltype: " + legemiddelType);
                return;
            }
            System.out.println("navn paa legemiddel: ");
            String navn = s.nextLine();
            System.out.println("Pris (heltall): ");
            int pris = Integer.parseInt(s.nextLine());
            System.out.println("Virkestoff i mg (flyttall): ");
            double virkestoff = Double.parseDouble(s.nextLine());

            Legemiddel nyttLegemiddel=null;
            
            if (legemiddelType.equals("vanlig")){
                nyttLegemiddel = new Vanlig(navn, pris, virkestoff);
            } else{
                System.out.println("Styrke (1-10): ");
                int styrke = Integer.parseInt(s.nextLine());
                if (legemiddelType.equals("vanedannende")){
                    nyttLegemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
                } else if (legemiddelType.equals("narkotisk")){
                    nyttLegemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
                    }
                }
                Legesystem.legemiddelListe.leggTil(nyttLegemiddel);
        } 
        
        //UGYLDIG INPUT:
        else{
            System.out.println("Ugyldig type objekt: " + elementType);
        }
    }

    //VALG 3:
    /** Metode for aa bruke resept, gitt pasient og resept allerede utskrevet for pasienten 
    */
    public static void BrukResept(){
        System.out.println("");
        Scanner s = new Scanner(System.in);
        System.out.println("Hvilken pasient vil du se resepter for? ");
        for (Pasient pas:Legesystem.pasientListe){
            System.out.println(pas.toString());
        }
        try{
            int pasientNr = Integer.parseInt(s.nextLine());  //tar brukerinput
            Pasient pasient = Legesystem.pasientListe.hent(pasientNr);
            System.out.println("Valgt pasient: " + pasient.toString());

            //Avslutter med beskjed om pasienten ikke har aktive resepter:
            if (pasient.resepter.size()==0){
                pasient.skrivResepter();
                return;
            }
            //Fortsetter dersom pasienten har aktive resepter:
            System.out.println("Hvilken resept vil du bruke? (nr)");
            pasient.skrivResepter();
            
            int reseptNr = Integer.parseInt(s.nextLine()); //Velger resept
            Resept resept = Legesystem.reseptListe.hent(reseptNr);

            //Sjekker om det er reit igjen, og bruker resept hvis det er det:
            if(resept.reit<=0){
                System.out.println("Ingen flere reit igjen paa denne resepten");
            } else {
                resept.bruk();
                System.out.println("Brukte resept paa " + resept.legemiddel.hentNavn());
                System.out.println("Gjenvaerende reit: " + resept.hentReit());

            }
        } catch (UgyldigListeindeks ue){
            System.out.println(ue.getMessage());
        }
    }//Avslutter BrukResept()

    //VALG 4:
     /**
     * Metode for aa hente ut statistikk, basert på de ulike listene
     */
    public static void hentStatistikk(String type){
        System.out.println("");

        if (type.equals("vanedannende")){

            //TOTALT ANTALL RESEPTER PAA VANEDANNENDE LEGEMIDLER:
            int antall_vanedannende_resepter = 0;
            for (Resept resept : Legesystem.reseptListe){
                Legemiddel current_legemiddel = resept.hentLegemiddel();
                if (current_legemiddel instanceof Vanedannende) {
                    antall_vanedannende_resepter++;
                }
            }
            System.out.println("Antall utskrevne resepter av vanedannende legemidler: " + antall_vanedannende_resepter);

             //LEGER SOM HAR SKREVET UT MINST ÉN RESEPT PAA VANEDANNENDE LEGEMIDDEL
             for (Lege lege : Legesystem.legeListe){
                int teller = 0; 
                for (Resept resept:lege.hentUtskrevneResepter()){
                    if (resept.hentLegemiddel() instanceof Vanedannende){
                        teller++;
                    }
                }
                if (teller>0){
                    System.out.println(lege.hentNavn() + " har skrevet ut " + teller + " resept(er) paa vanedannende legemidler.");
                    }
            }

            //PASIETER MED MINST ÉN GYLDIG RESEPT PAA VANEDANNENDE LEGEMIDDEL
            for (Pasient pasient : Legesystem.pasientListe){
                int teller = 0;
                for (Resept resept : pasient.resepter){
                    if (resept.hentLegemiddel() instanceof Vanedannende && resept.hentReit()>0){
                        teller++;
                    }
                }
                if (teller>0){
                    System.out.println("Pasient " + pasient.hentNavn() + " har " + teller + " resept(er) pa vanedannende legemidler.");
                }
            }    

        } else if(type.equals("narkotisk")){
            //TOTALT ANTALL RESEPTER PAA VANEDANNENDE LEGEMIDLER:
            int antall_narkotiske_resepter = 0;
            for (Resept resept : Legesystem.reseptListe){

                //Hvis narkotisk: oek teller:
                Legemiddel current_legemiddel = resept.hentLegemiddel();
                if (current_legemiddel instanceof Narkotisk) {
                    antall_narkotiske_resepter++;
                }                
            }
            System.out.println("Antall utskrevne resepter av narkotiske legemidler: " + antall_narkotiske_resepter);
            
            //LEGER SOM HAR SKREVET UT MINST ÉN RESEPT PAA NARKOTISK LEGEMIDDEL
            for (Lege lege : Legesystem.legeListe){
                int teller = 0; 
                for (Resept resept:lege.hentUtskrevneResepter()){
                    if (resept.hentLegemiddel() instanceof Narkotisk){
                        teller++;
                    }
                }
                if (teller>0){
                    System.out.println(lege.hentNavn() + " har skrevet ut " + teller + " resept(er) paa narkotiske legemidler.");
                    }
                }
        }
    }//avslutter hentStatistikk()

    //VALG 5: 
    /**
     * Metode for aa skrive informasjon i legesystem til fil.
     */
    public static void SkrivTilFil() throws FileNotFoundException{
        File fil = new File("systemdata.txt");
        PrintWriter pw = new PrintWriter(fil);
        
        //SKRIVER UT PASIENTINFO:
        pw.println("# Pasienter (navn, fnr)");
        for (Pasient pasient : Legesystem.pasientListe){
            String streng = "";
            streng = streng + pasient.hentNavn() + "," + pasient.hentSSN();
            pw.println(streng);
        }

        //SKRIVER UT LEGEMIDDELINFO:
        pw.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
        for (Legemiddel legemiddel : Legesystem.legemiddelListe){
            String streng = "";
            String type = "";
            int styrke = 0;
            streng = streng + legemiddel.hentNavn()+",";

            if (legemiddel instanceof Vanlig){
                streng = streng + "vanlig" + "," + legemiddel.hentPris() + "," + legemiddel.hentVirkestoff();
            }
            else if (legemiddel instanceof Vanedannende){
                streng = streng + "vanedannende" + "," + legemiddel.hentPris() + "," + legemiddel.hentVirkestoff() + "," +legemiddel.hentStyrke();
            } else {
                streng = streng + "narkotisk" + "," + legemiddel.hentPris() + "," + legemiddel.hentVirkestoff() + "," +legemiddel.hentStyrke();
            }

            pw.println(streng);
        }

        //SKRIVER UT LEGEINFO:
        pw.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
        for (Lege lege : Legesystem.legeListe){
            String streng = "";
            streng = streng + lege.hentNavn() + "," + lege.hentKontrollkode();
            pw.println(streng);
        }

        //SKRIVER UT RESEPTINFO:
        pw.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
        for (Resept resept:Legesystem.reseptListe){
            String streng;
            Legemiddel legemiddel = resept.hentLegemiddel();
            Lege utskrivendeLege = resept.hentUtskrivendeLege();
            Pasient pasient = resept.hentPasient();
            
            streng = legemiddel.hentId() + "," + utskrivendeLege.hentNavn()+","+pasient.hentId();
            streng = streng + ","+resept.farge()+","+resept.hentType()+","+resept.hentReit();
            pw.println(streng);
        }
        
        pw.close();
    }
}