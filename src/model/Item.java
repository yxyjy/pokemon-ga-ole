package model;

import model.Pokemons.Pokemon;

public class Item {
    private String name;
    private String description;
    private int price;

    public Item(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getPrice(){
        return price;
    }

    //ITEM FUNCTIONS
    public void usePotion(Pokemon pokemon){
        if(pokemon.isFainted()){
            System.out.println("\n\t" + pokemon.getName() + " is fainted and cannot be healed!");
            return;
        }
        pokemon.heal(20);
        System.out.println("\n\t[~>] You used Potion!");
        System.out.println("\n\t" + pokemon.getName() + " healed by 20 HP!");
    }

    public void useSuperPotion(Pokemon pokemon){
        if(pokemon.isFainted()){
            System.out.println("\n\t" + pokemon.getName() + " is fainted and cannot be healed!");
            return;
        }
        pokemon.heal(50);
        System.out.println("\n\t[~>] You used Super Potion!");
        System.out.println("\n\t" + pokemon.getName() + " healed by 50 HP!");
    }

    public void useHyperPotion(Pokemon pokemon){
        if(pokemon.isFainted()){
            System.out.println("\n\t" + pokemon.getName() + " is fainted and cannot be healed!");
            return;
        }
        pokemon.heal(100);
        System.out.println("\n\t[~>] You used Hyper Potion!");
        System.out.println("\n\t" + pokemon.getName() + " healed by 100 HP!");
    }

    public void useRevive(Pokemon pokemon){
        if (pokemon.isFainted()){
            pokemon.setFainted(false);
            pokemon.setHp(pokemon.getMaxHp() / 2); //half HP
            System.out.println("\n\t[~>] You used Revive!");
            System.out.println("\n\t" + pokemon.getName() + " has been revived with half HP!");
        } else {
            System.out.println("\n\t" + pokemon.getName() + " is not fainted!");
        }
    }

    public void useMaxRevive(Pokemon pokemon){
        if (pokemon.isFainted()){
            pokemon.setFainted(false);
            pokemon.setHp(pokemon.getMaxHp()); //full HP
            System.out.println("\n\t[~>] You used Max Revive!");
            System.out.println("\n\t" + pokemon.getName() + " has been revived with full HP!");
        } else {
            System.out.println("\n\t" + pokemon.getName() + " is not fainted!");
        }
    }

    //toString
    public String toString(){
        return name + ": " + description + " (Price: " + price + " coins)";
    }
}
