package util;

import java.util.ArrayList;
import model.Pokemons.Pokemon;
import model.Pokemons.Entei;
import model.Pokemons.Darmaniltan;
import model.Pokemons.Suicune;
import model.Pokemons.Kyogre;
import model.Pokemons.Shaymin;
import model.Pokemons.Meganium;
import model.Pokeball;

public final class Utility{
    public Utility(){};

    public static final ArrayList<String> allPokemons = new ArrayList<>(){
        {
            add("Entei");
            add("Darmaniltan");
            add("Suicune");
            add("Kyogre");
            add("Shaymin");
            add("Meganium");
        }
    };

    public Pokemon getPokemon(String pokemonName) {
        switch (pokemonName) {
            case "Entei":
                return new Entei();
            case "Darmaniltan":
                return new Darmaniltan();
            case "Suicune":
                return new Suicune();
            case "Kyogre":
                return new Kyogre();
            case "Shaymin":
                return new Shaymin();
            case "Meganium":
                return new Meganium();
            default:
                return null;
        }
    }

  //   public static final ArrayList<Pokemon> allPokemons = new ArrayList<Pokemon>() {{
  //   //fire type
  //   add(new Entei());
	// 	add(new Darmaniltan());

  //   //water type
  //   add(new Suicune());
	// 	add(new Kyogre());
		
  //   //grass type
  //   add(new Shaymin());
	// 	add(new Meganium());  
  // }};

  public static final ArrayList<Pokeball> allPokeballs = new ArrayList<Pokeball>() {{
    add(new Pokeball("Pokeball", 1.0, 5.0));
    add(new Pokeball("Greatball", 3.0, 3.0));
    add(new Pokeball("Ultraball", 4.0, 2.0));
    add(new Pokeball("Masterball", 5.0, 1.0));
  }};

  public Pokemon getRandomPokemon(ArrayList<Pokemon> pokemonList) {
    double r = Math.random() * pokemonList.size();
    return pokemonList.get((int) r);
  }

  public String getRandomPokemonName() {
    int index = (int) (Math.random() * allPokemons.size());
    return allPokemons.get(index);
  }

  public ArrayList<Pokemon> getRandomEncounteredPokemons() {
    ArrayList<Pokemon> encountered = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
        String name = getRandomPokemonName();
        encountered.add(getPokemon(name));
    }
    return encountered;
}

  public Pokeball getRandomPokeball() {
    double r = Math.random() * allPokeballs.size();
    return allPokeballs.get((int) r);
  }
}