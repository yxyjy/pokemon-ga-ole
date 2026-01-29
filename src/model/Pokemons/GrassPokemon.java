package model.Pokemons;


public class GrassPokemon extends Pokemon {

    //constructor
    public GrassPokemon(String index, String name, int level, double hp, double attack, double defense, int speed, String ability){
        super(index, name, level, "Grass", hp, attack, defense, speed, ability);
    }

    @Override
    public void attack (Pokemon playerPokemon, Pokemon opponentPokemon){
        if (playerPokemon instanceof GrassPokemon && opponentPokemon.getType().equals("Water")) {
            playerPokemon.setAttack(1.5 * playerPokemon.getAttack());
        }
        super.attack(playerPokemon, opponentPokemon);
    }
    
}
