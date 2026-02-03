//import java.util.List;
import java.util.Scanner;

import model.Battle;
import model.Item;
import model.Player;
import model.Pokeball;
import model.Pokemons.Pokemon;
import util.Utility;
import util.ItemManagement;

import java.util.ArrayList;

public class MainGame {
    private String gameID;
    private Player player;
    //private Utility utility;

    public static Scanner scanner = new Scanner(System.in);

    public MainGame(){
    }

    public MainGame(Player player){
        this.player = player;
    }

    public static Player initializePlayer() {
        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.println("|  Welcome, Trainer!                   |");
        System.out.println("|                                      |");
        System.out.println("|  What is your name?                  |");
        System.out.println("+--------------------------------------+");
        System.out.print("> ");

        String playerName = scanner.nextLine().trim();
        return new Player(playerName);
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.println("|        WHAT WILL YOU DO?             |");
        System.out.println("|                                      |");
        System.out.println("|  1. CATCH AND BATTLE                 |");
        System.out.println("|  2. SHOP                             |");
        System.out.println("|  3. EXIT                             |");
        System.out.println("+--------------------------------------+");
        System.out.print("> ");
    }

    //battle prep
    public void randomEncounter(){
        ArrayList<Pokemon> currentPokemons = player.getPokemons();
        if (currentPokemons == null) currentPokemons = new ArrayList<>();

        Utility.typeWriterPrint("\n\t[!] The tall grass begins to rustle...\n", 50);
        ArrayList<Pokemon> encounteredPokemons = new ArrayList<>(Utility.getRandomEncounteredPokemons());
        
        while (currentPokemons == null || currentPokemons.size() < 2) {
            System.out.println("\n\t+--------------------------------------+");
            System.out.println("\t|        POKEMONS SPOTTED!             |");
            System.out.println("\t+--------------------------------------+");
            for (int i = 0; i < encounteredPokemons.size(); i++) {
                System.out.println("\n\t"+(i + 1) + ". " + encounteredPokemons.get(i).simpleString());
            }

            Pokeball pokeball = Utility.getRandomPokeball();
            System.out.println("\n\t[!] You have a " + pokeball.getType() + "!");
            System.out.println("\n\tWhich Pokemon do you want to catch? (Enter number)");
            System.out.print("\t> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > encounteredPokemons.size()) {
            System.out.println("\n\t [x] Invalid choice!");
            continue; 
            }

            Pokemon selectedPokemon = encounteredPokemons.get(choice - 1);

            try {
                if (pokeball.getCatchRate() >= selectedPokemon.getLevel()) {
                    System.out.println("\n\t[!] You caught " + selectedPokemon.getName() + "!");
                    currentPokemons.add(selectedPokemon);
                    encounteredPokemons.remove(selectedPokemon);

                    System.out.printf("\n\tYour currently have (%d/2) Pokemons:\n", currentPokemons.size());
                    for (Pokemon p : currentPokemons) {
                        System.out.println("\t- " + p.simpleString());
                    }
                } else {
                    System.out.println("\n\t[~] " + selectedPokemon.getName() + " escaped!");
                    System.out.printf("\n\tYour currently have (%d/2) Pokemons:\n", currentPokemons.size());
                    for (Pokemon p : currentPokemons) {
                        System.out.println("\t- " + p.simpleString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void prepareBattle(){
        if (player.getPokemons().size() != 0){
            for (Pokemon p : player.getPokemons()){
                player.getPokemons().remove(p);
            }
        }
        Utility.typeWriterPrint("Preparing for Battle...", 40);

        System.out.println("\n\n\t=========== Time to catch your Pokemons! =============");
        randomEncounter();
        System.out.println("\n\tYou have caught 2 Pokemons! Let's start the battle!");
    }

    //store prep
    public void buyItem(){
        int selectedIndex = -1;
        Item selectedItem = null;

        System.out.println("\n\tEnter the index of the item you want to buy: ");
        System.out.print("\t> ");
        selectedIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if(selectedIndex > ItemManagement.allItems.size()){
            System.out.println("\n\t[x] This item is not in the store! Please select a valid item.");
        } else{
            selectedItem = ItemManagement.getItemByIndex(selectedIndex);
            ItemManagement.purchaseItem(player, selectedItem);
            ItemManagement.displayPlayerInventory(player);
        }
    }

    public void enterShop(){
        System.out.println();
        System.out.println("================================================");
        System.out.println("I T E M   S H O P");
        System.out.println("================================================");
        System.out.println();
        System.out.printf("\tYou have %d coins.%n%n", player.getCoins());
        ItemManagement.displayAllItems();

        int shopChoice = -1;
        System.out.println();
        System.out.println("\t+--------------------------------------+");
        System.out.println("\t|      DO YOU WANT TO BUY AN ITEM?     |");
        System.out.println("\t|                                      |");
        System.out.println("\t|  1. YES                              |");
        System.out.println("\t|  2. NO                               |");
        System.out.println("\t+--------------------------------------+");
        System.out.print("\t> ");
        shopChoice = scanner.nextInt();
        scanner.nextLine();

        while (shopChoice != 2){
            if (shopChoice != 1){
                System.out.println("\n\t[x] Invalid choice. Please select 1 for Yes / 2 for No.");
                shopChoice = scanner.nextInt();
                scanner.nextLine();
                continue;
            }
            buyItem();
            System.out.println();
            System.out.println("\t+--------------------------------------+");
            System.out.println("\t|   DO YOU WANT TO BUY ANOTHER ITEM?   |");
            System.out.println("\t|                                      |");
            System.out.println("\t|  1. YES                              |");
            System.out.println("\t|  2. NO                               |");
            System.out.println("\t+--------------------------------------+");
            System.out.print("\t> ");
            shopChoice = scanner.nextInt();
            scanner.nextLine();
        }

        Utility.typeWriterPrint("\nExiting the Item Shop...\n", 40);

    }

    public static void main (String[] args) {
        int menuChoice = -1;

        //1. New Game
        MainGame game = new MainGame();

        System.out.println("\n==================================================================");
        Utility.typeWriterPrint("           --- WELCOME TO THE POKEMON-GA-OLE REGION ---           ", 40);
        System.out.println("\n==================================================================\n");


        //2. Initialize Player
        game.player = initializePlayer();

        //3. Select Mode:
        displayMenu();
        menuChoice = scanner.nextInt();
        scanner.nextLine();

        //4. Process Mode
        while (menuChoice != 3){
            switch(menuChoice){
                case 1:
                    Utility.typeWriterPrint("\nEntering Battle Mode...\n", 40);
                    game.prepareBattle();
                    //start battle
                    Battle battle = new Battle(game.player);
                    battle.startBattle();

                    displayMenu();
                    menuChoice = scanner.nextInt();
                    scanner.nextLine();

                    break;

                case 2:
                    Utility.typeWriterPrint("\nEntering the Item Shop...\n", 40);
                    //start shop
                    game.enterShop();

                    displayMenu();
                    menuChoice = scanner.nextInt();
                    scanner.nextLine();

                    break;

                default:
                    System.out.println("\n[x]Invalid choice. Please select a valid option.");
            }
        }
        System.out.println("Thank you for playing the Pokemon Game! Goodbye!");
    }   

}
