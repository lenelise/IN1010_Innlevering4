import java.util.Scanner;
import java.io.FileNotFoundException;

class Legesystem{
        static IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
        static IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>();
        static Prioritetskoe<Lege> legeListe = new Prioritetskoe<>();
        static IndeksertListe<Resept> reseptListe = new IndeksertListe<>();

    public static void main(String[] args) throws UgyldigHvitResept,FileNotFoundException,UgyldigUtskrift{
        
        //Leser inn fil og populerer listene med data:
        SystemValg.LesInnFil();
        Scanner s = new Scanner(System.in);

        //Hovedmeny for bruker av programmet:
        boolean cont = true;
        while (cont){
            System.out.println("");
            System.out.println("HOVEDMENY:");
            System.out.println("1: Skriv ut fullstendig oversikt over pasienter, leger, legemidler og resepter");
            System.out.println("2: Opprette nye objekter i systemet");
            System.out.println("3: Bruke resept paa pasient");
            System.out.println("4: Se statistikk");
            System.out.println("5: Skriv naavaerende informasjon til fil");
            System.out.println("-1: Avslutt");
            String input = s.nextLine();
            
            if (input.equals("-1")){
                cont=false; 
            } 
            else if(input.equals("1")){
                SystemValg.SkrivInformasjon();
            } 
            else if (input.equals("2")){
                System.out.print("lege/pasient/legemiddel/resept: ");
                String elementType = s.nextLine();
                SystemValg.OpprettElement(elementType);
            } 
            else if(input.equals("3")){
                SystemValg.BrukResept();
            } 
            else if (input.equals("4")){
                String typeStatistikk;
                System.out.println("Hvilket legemiddel oensker du statistikk om? (narkotisk/vanedannende)");
                typeStatistikk = s.nextLine();
                SystemValg.hentStatistikk(typeStatistikk);
            }
            else if (input.equals("5")){
                SystemValg.SkrivTilFil();
            }
            else{
                System.out.println("Ugyldig input, proev paa nytt.");
            }
        }
        s.close();
    }//avslutter main
    
}//avslutter klasse