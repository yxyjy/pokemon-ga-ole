package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.Utility;
import model.Pokemons.Pokemon;

public class Battle {
     private Player player;
     private boolean playerAlive;
     private boolean opponentAlive;
     private Utility utility;
     private ArrayList<Pokemon> opponentPokemons;
     private ArrayList<Pokemon> playerPokemons;
     Scanner scanner = new Scanner(System.in);

    public Battle(Player player, Utility utility){
        this.player = player;
        this.utility = utility;
        this.opponentPokemons = new ArrayList<>();
        this.playerPokemons = player.getPokemons();
        playerAlive = true;
        opponentAlive = true;

        //generate opponent pokemons
        for (int i = 0; i < 2; i++){
            Pokemon opponentPokemon = utility.getPokemon(utility.getRandomPokemonName());
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
        
        System.out.println("Preparing for Attack!");

        //choose pokemon to use
        System.out.println("Which Pokemon will you use to attack?");
        displayPlayerPokemons();
        attackChoice = scanner.nextInt();

            while (playerPokemons.get(attackChoice - 1).isFainted()) {
                System.out.println("This Pokemon has fainted! Please choose another Pokemon.");
                displayPlayerPokemons();
                attackChoice = scanner.nextInt();
            } 
        usingPokemon = playerPokemons.get(attackChoice - 1);
        
        
        scanner.nextLine();

        //choose pokemon to attack
        System.out.println("Which Opponent Pokemon will you attack?");
        displayOpponentPokemons();
        opponentChoice = scanner.nextInt();

            while (opponentPokemons.get(opponentChoice - 1).isFainted()) {
                System.out.println("This Pokemon has fainted! Please choose another Pokemon.");
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
        opponentPokemon = utility.getRandomPokemon(opponentPokemons);

            while (opponentPokemon.isFainted()) {
                opponentPokemon = utility.getRandomPokemon(opponentPokemons);
            }

        receivingPokemon = utility.getRandomPokemon(playerPokemons);

            while (receivingPokemon.isFainted()) {
                receivingPokemon = utility.getRandomPokemon(playerPokemons);
            }

        opponentPokemon.attack(opponentPokemon, receivingPokemon);
        isAlive();
    }

    public void startBattle(){
        int battleMenuChoice = -1;
        System.out.println("\n\n------------------------------------------------");
        System.out.println("----------------! Battle Time !----------------");
        System.out.println("------------------------------------------------\n");
        System.out.println("Your Opponent has arrived! They bring:");
        displayOpponentPokemons();

        System.out.println("\n\nYou have:");
        displayPlayerPokemons();

        //Players turn
        System.out.println("\n\tPlayer's turn:");
        System.out.println("\t[1] Attack");
        System.out.println("\t[2] Use Item");
        System.out.print("\t>>Choose an option: ");
        battleMenuChoice = scanner.nextInt();

        if (battleMenuChoice == 1) {
            while (playerAlive && opponentAlive) {
                selectAttack();
                isAlive();
                if (!opponentAlive) break;

                receiveAttack();
                isAlive();
            }
            if (playerAlive == false){
                System.out.println("You have no remaining Pokemons! You lost the battle!");
            } else {
                System.out.println("Opponent has no remaining Pokemons! You won the battle!");
            }
            
        }

    }

}
