package model;

import java.util.ArrayList;
import java.util.Scanner;

import util.Utility;
import util.Coins;
import util.ItemManagement;
import model.Pokemons.Pokemon;

public class Battle {
     private Player player;
     private boolean playerAlive;
     private boolean opponentAlive;
     private ArrayList<Pokemon> opponentPokemons;
     private ArrayList<Pokemon> playerPokemons;
     Scanner scanner = new Scanner(System.in);

    public Battle(Player player){
        this.player = player;
        this.opponentPokemons = new ArrayList<>();
        this.playerPokemons = player.getPokemons();
        playerAlive = true;
        opponentAlive = true;

        //generate opponent pokemons
        for (int i = 0; i < 2; i++){
            Pokemon opponentPokemon = Utility.getPokemon(Utility.getRandomPokemonName());
            opponentPokemons.add(opponentPokemon);
        }
    }

    //retrive and display opponent/player pokemons
    public ArrayList<Pokemon> getOpponentPokemons() {
        return opponentPokemons;
    }

    public Pokemon getOppPokemon1(){
        return opponentPokemons.get(0);
    }

    public Pokemon getOppPokemon2(){
        return opponentPokemons.get(1);
    }

    public void displayOpponentPokemons(){
        for (int i = 0; i < opponentPokemons.size(); i++){
            System.out.println("\n\t"+(i + 1) + ". " + opponentPokemons.get(i).simpleString());
        }
    }

    public void displayPlayerPokemons(){
        for (int i = 0; i < playerPokemons.size(); i++){
            System.out.println("\n\t"+(i + 1) + ". " + playerPokemons.get(i).simpleString());
        }
    }   


    //attack operations
    public void isAlive() {
        boolean playerHasHealthy = false;
        for (Pokemon p : playerPokemons) {
            if (!p.isFainted()) playerHasHealthy = true;
        }
        playerAlive = playerHasHealthy;

        boolean opponentHasHealthy = false;
        for (Pokemon p : opponentPokemons) {
            if (!p.isFainted()) opponentHasHealthy = true;
        }
        opponentAlive = opponentHasHealthy;
    }

    public void selectAttack(){
        int attackChoice = -1;
        int opponentChoice = -1;
        Pokemon usingPokemon;
        Pokemon attackedPokemon; 
        
        Utility.typeWriterPrint("\n\tPreparing for Attack!", 40);

        //choose pokemon to use
        System.out.println("\n\tWhich Pokemon will you use to attack?");
        System.out.println("\t>");
        displayPlayerPokemons();
        attackChoice = scanner.nextInt();
        scanner.nextLine();

            while (playerPokemons.get(attackChoice - 1).isFainted()) {
                System.out.println("\n\t[x] This Pokemon has fainted! Please choose another Pokemon.");
                displayPlayerPokemons();
                attackChoice = scanner.nextInt();
                scanner.nextLine();
            } 
        usingPokemon = playerPokemons.get(attackChoice - 1);
        
        
        scanner.nextLine();

        //choose pokemon to attack
        System.out.println("\n\tWhich Opponent Pokemon will you attack?");
        System.out.println("\t>");
        displayOpponentPokemons();
        opponentChoice = scanner.nextInt();
        scanner.nextLine();

            while (opponentPokemons.get(opponentChoice - 1).isFainted()) {
                System.out.println("\n\t[x] This Pokemon has fainted! Please choose another Pokemon.");
                displayOpponentPokemons();
                opponentChoice = scanner.nextInt();
            }
        attackedPokemon = opponentPokemons.get(opponentChoice - 1);

        usingPokemon.attack(usingPokemon, attackedPokemon);
        isAlive();
    }

    public void receiveAttack(){
        System.out.println("\n\tOpponent's turn:");
        Pokemon opponentPokemon;
        Pokemon receivingPokemon; 
        opponentPokemon = Utility.getRandomPokemon(opponentPokemons);

            while (opponentPokemon.isFainted()) {
                opponentPokemon = Utility.getRandomPokemon(opponentPokemons);
            }

        receivingPokemon = Utility.getRandomPokemon(playerPokemons);

            while (receivingPokemon.isFainted()) {
                receivingPokemon = Utility.getRandomPokemon(playerPokemons);
            }

        opponentPokemon.attack(opponentPokemon, receivingPokemon);
        isAlive();
    }

    public void showPlayerMenu() {
        System.out.println();
        System.out.println("\t═══════════════ YOUR TURN ═══════════════");
        System.out.println("\t  [1] Attack");
        System.out.println("\t  [2] Use Item");
        System.out.print("\t  >> Choose your action: ");
    }


    public void startBattle(){
        int battleMenuChoice = -1;
        System.out.println("\n\n\n\n\n");
        System.out.println("\t================================================");
        // System.out.println("               B A T T L E   T I M E");
        Utility.typeWriterPrint("\tB A T T L E   T I M E\n", 40);
        System.out.println("\t================================================");
        System.out.println();

        System.out.println("\tYour opponent steps forward with:");
        System.out.println("\t────────────────────────────────────────");
        displayOpponentPokemons();

        System.out.println("\n\tYou send out:");
        System.out.println("\t────────────────────────────────────────");
        displayPlayerPokemons();

        //Players turn
        showPlayerMenu();
        battleMenuChoice = scanner.nextInt();

        while (playerAlive && opponentAlive){
            if (battleMenuChoice < 1 || battleMenuChoice > 2) {
                System.out.println("\n\t[x] Invalid choice. Please choose a valid option.");
                showPlayerMenu();
                battleMenuChoice = scanner.nextInt();

            } else if (battleMenuChoice == 1) {
                selectAttack();
                isAlive();
                if (!opponentAlive) break;

                receiveAttack();
                isAlive();

                showPlayerMenu();
                battleMenuChoice = scanner.nextInt();

            } else if (battleMenuChoice == 2) {
                int useItem = -1;

                ItemManagement.displayPlayerInventory(player);

                //select item to use
                System.out.println("\n\t[Please select the item you want to use, or enter 0 to cancel:]");
                System.out.print("\t> ");
                useItem = scanner.nextInt() - 1;
                scanner.nextLine();

                if (useItem == -1) {
                    //cancel use item
                    System.out.println("\n\t[Item use cancelled.]");
                    // continue;
                } else if (useItem >= 0 && useItem < player.getInventory().size()) {
                    //set use item
                    Item selectedItem = player.getInventory().get(useItem);

                    //select pokemon to use item on
                    System.out.println("\n\t[Select the Pokemon to use the item on:]");
                    System.out.print("\t> ");
                    displayPlayerPokemons();
                    int pokemonChoice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    Pokemon usingOnPokemon = playerPokemons.get(pokemonChoice);

                    //use the item
                    ItemManagement.useItem(usingOnPokemon, selectedItem);
                    
                    // After using the item, remove it from inventory
                    player.getInventory().remove(useItem);

                    receiveAttack();
                    isAlive();
                } else {
                    System.out.println("\n\t[x] Invalid item selection.");
                }
                // isAlive();

                showPlayerMenu();
                battleMenuChoice = scanner.nextInt();
            }
        }
        if (playerAlive) {
            System.out.println("\n\t═══════════════  VICTORY! ═══════════════");
            Coins.calculateWinningCoins(player, Coins.getWinnningRewardCoins());
            System.out.println("\n\t[You received " + Coins.getWinnningRewardCoins() + " coins as a reward!]");
        } else {
            System.out.println("\n\t═══════════════  DEFEAT! ═══════════════");
        }
    }

}
