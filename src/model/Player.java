package model;

import java.util.ArrayList;
import model.Pokemons.Pokemon;

public class Player {
    private String name;
    private ArrayList<Pokemon> pokemons;
    private int medals;

    public Player(String name){
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.medals = 0;
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

    public int getMedals(){
        return medals;
    }

    public void setMedals(int medals){
        this.medals = medals;
    }
}
