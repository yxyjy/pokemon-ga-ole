import java.util.List;
import java.util.Scanner;

import model.Battle;
import model.Player;
import model.Pokeball;
import model.Pokemons.Pokemon;
import util.Utility;

import java.util.ArrayList;

public class MainGame {
    private String gameID;
    private Player player;
    private Utility utility;

    public static Scanner scanner = new Scanner(System.in);

    public MainGame(){
    }

    public MainGame(Player player){
        this.player = player;
    }

    //initiate player
    public static Player initializePlayer(){
        System.out.println("Enter your name: ");
        String playerName = scanner.nextLine();
        return new Player(playerName);
    }

    //display menu
    public static void displayMenu(){
        System.out.println("Select Game Mode:");
        System.out.println("1. Catch and Battle");
        System.out.println("2. Shop");
        System.out.println("3. Exit");
    }

    //battle prep
    public void randomEncounter(){
        ArrayList<Pokemon> currentPokemons = player.getPokemons();
        if (currentPokemons == null) currentPokemons = new ArrayList<>();

        ArrayList<Pokemon> encounteredPokemons = new ArrayList<>(utility.getRandomEncounteredPokemons());
        
        while (currentPokemons == null || currentPokemons.size() < 2) {
            System.out.println("You've encountered the following Pokemons:");
            for (int i = 0; i < encounteredPokemons.size(); i++) {
                System.out.println("\n\t"+(i + 1) + ". " + encounteredPokemons.get(i).simpleString());
            }

            Pokeball pokeball = utility.getRandomPokeball();
            System.out.println("You have a " + pokeball.getType() + "!");
            System.out.println("Which Pokemon do you want to catch? (Enter number)");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > encounteredPokemons.size()) {
            System.out.println("Invalid choice!");
            continue; 
            }

            Pokemon selectedPokemon = encounteredPokemons.get(choice - 1);

            try {
                if (pokeball.getCatchRate() >= selectedPokemon.getLevel()) {
                    System.out.println("You caught " + selectedPokemon.getName() + "!");
                    currentPokemons.add(selectedPokemon);
                    encounteredPokemons.remove(selectedPokemon);

                    System.out.printf("\nYour currently have (%d/2) Pokemons:\n", currentPokemons.size());
                    for (Pokemon p : currentPokemons) {
                        System.out.println("- " + p.simpleString());
                    }
                } else {
                    System.out.println(selectedPokemon.getName() + " escaped!");
                    System.out.printf("\nYour currently have (%d/2) Pokemons:\n", currentPokemons.size());
                    for (Pokemon p : currentPokemons) {
                        System.out.println("- " + p.simpleString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void prepareBattle(){
        System.out.println("Preparing for Battle...");
        System.out.println("Time to catch your Pokemons!");
        randomEncounter();
        System.out.println("You have caught 2 Pokemons! Let's start the battle!");
    }

    public static void main (String[] args) {
        int menuChoice = -1;

        //1. New Game
        MainGame game = new MainGame();
        game.utility = new Utility();
        System.out.println("Welcome to the Pokemon Game!");

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
                    System.out.println("Entering Battle Mode...");
                    game.prepareBattle();
                    //start battle
                    Battle battle = new Battle(game.player, game.utility);
                    battle.startBattle();
                    break;
                case 2:
                    System.out.println("Entering Shop Mode...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        System.out.println("Thank you for playing the Pokemon Game! Goodbye!");
    }   

}
