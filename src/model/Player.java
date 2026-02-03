package model;

import java.util.ArrayList;
import model.Pokemons.Pokemon;

public class Player {
    private String name;
    private ArrayList<Pokemon> pokemons;
    private int coins;
    private ArrayList<Item> inventory;

    public Player(String name){
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.coins = 100;
        this.inventory = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Pokemon> getPokemons(){
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons){
        this.pokemons = pokemons;
    }

    public int getCoins(){
        return coins;
    }

    public void setCoins(int coins){
        this.coins = coins;
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }
    
    public void setInventory(ArrayList<Item> inventory){
        this.inventory = inventory;
    }
}
