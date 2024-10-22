package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    public static void main(String[] args) {
        // Konstanty pro definovani jednotlivych operaci (pouze pro cisty kod)
        final int KONEC_PROGRAMU = 0;
        final int VYPIS_CHATEK = 1;
        final int VYPIS_KONKRETNI_CHATKU = 2;
        final int PRIDANI_NAVSTEVNIKU = 3;
        final int ODEBRANI_NAVSTEVNIKU = 4;
        final int CELKOVA_OBSAZENOST = 5;
        final int VYPIS_PRAZDNE_CHATKY = 6;

        final int VELIKOST_KEMPU = 5;
        final int MAX_VELIKOST_CHATKY = 10;

        Scanner scanner = new Scanner(System.in);

        // Definovani pole podle velikosti kempu (poctu chatek)
        int[] chatky = new int[VELIKOST_KEMPU];
        int operace;

        do {
            System.out.println("""
                    ****************************
                    MENU:
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    ****************************""");

            // Ziskani operace od uzivatele
            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> {

                    // Projdi cele pole od <0, VELIKOST) a vypis kazdy index
                    for (int i = 0; i < VELIKOST_KEMPU; i++) {
                        System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
                    }
                }

                case VYPIS_KONKRETNI_CHATKU -> {

                    // Ziskani cisla chatky od uzivatele
                    System.out.print("Zadej cislo chatky: ");
                    int cisloChatky = scanner.nextInt();

                    // Zjisti, jestli je chatka mimo velikost kempu
                    if (cisloChatky < 1 || cisloChatky > VELIKOST_KEMPU) {
                        System.err.println("Tato chatka neexistuje");
                        continue; // Zacni novou iteraci cyklu
                    }

                    System.out.println("Chatka [" + (cisloChatky) + "] = " + chatky[cisloChatky-1]);
                }

                case PRIDANI_NAVSTEVNIKU -> {

                    // Ziskani cisla chatky od uzivatele
                    System.out.print("Zadej cislo chatky: ");
                    int cisloChatky = scanner.nextInt();

                    // Zjisti, jestli je chatka mimo velikost kempu
                    if (cisloChatky < 1 || cisloChatky > VELIKOST_KEMPU) {
                        System.err.println("Tato chatka neexistuje");
                        continue; // Zacni novou iteraci cyklu
                    }

                    // Ziskani poctu navstevniku, kteri se chteji v chatce ubytovat
                    System.out.print("Zadej pocet navstevniku: ");
                    int pocetNavstevniku = scanner.nextInt();

                    // Zaporne cislo nebo prilis velky nevalidni vstup
                    if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
                        System.err.println("Neplatna hodnota pro pocet navstevniku");
                        continue; // Zacni novou iteraci cyklu
                    }

                    // Pokud je pocet uz ubytovanych plus ty co se chteji ubytovat vetsi nez kapacita chatky je to nevalidni vstup
                    if ((chatky[cisloChatky-1] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
                        System.err.println("Prekrocen maximalni pocet navstevniku chatky");
                        continue; // Zacni novou iteraci cyklu
                    }

                    // Pridej nove ubytovane do chatky k tem co uz tam jsou
                    chatky[cisloChatky-1] += pocetNavstevniku;
                }

                case ODEBRANI_NAVSTEVNIKU -> {
                    
                    // Ziskani cisla chatky od uzivatele
                    System.out.print("Zadej cislo chatky: ");
                    int cisloChatky = scanner.nextInt();

                    // Zjisti, jestli je chatka mimo velikost kempu
                    if (cisloChatky < 1 || cisloChatky > VELIKOST_KEMPU) {
                        System.err.println("Tato chatka neexistuje");
                        continue; // Zacni novou iteraci cyklu
                    }

                    // Ziskani poctu navstevniku k odebrani
                    System.out.print("Zadej pocet navstevniku: ");
                    int pocetNavstevniku = scanner.nextInt();

                    // Zaporne cislo nebo prilis velky nevalidni vstup
                    if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
                        System.err.println("Neplatna hodnota pro pocet navstevniku");
                        continue; // Zacni novou iteraci cyklu
                    }
                    
                    // Pokud chceme odebirat vice lidi, nez je v chatce ubytovano, je to nevalidni vstup
                    if ((chatky[cisloChatky-1] - pocetNavstevniku) < 0) {
                        System.err.println("Odebirate prilis navstevniku");
                        continue; // Zacni novou iteraci cyklu
                    }
                    
                    // Odeber navstevniky z chatky
                    chatky[cisloChatky-1] -= pocetNavstevniku;
                }

                case CELKOVA_OBSAZENOST -> {
                    // Projdi vsechny chatky a secti obsazenosti
                    int obsazenost = 0;
                    for (int obsazenostChatky : chatky ) {
                        obsazenost += obsazenostChatky;
                    }
                    System.out.println("Celkova obsazenost je " + obsazenost + " navstevniku.");
                }

                case VYPIS_PRAZDNE_CHATKY -> {
                    System.out.println("Prazdne chatky:");
                    for (int i = 0; i < VELIKOST_KEMPU; i++) {
                        if (chatky[i] == 0) {
                            System.out.println("Chatka [" + (i + 1) + "]");
                        }
                    }
                }

                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu. Na shledanou.");
                }

                default -> {
                    System.err.println("Neplatna volba");
                }
            }
        } while (operace != 0);
    }
}
