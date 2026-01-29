package model.Pokemons;


    public class WaterPokemon extends Pokemon {
    
    //constructor
    public WaterPokemon(String index, String name, int level, double hp, double attack, double defense, int speed, String ability){
        super(index, name, level, "Water", hp, attack, defense, speed, ability);
    }

    @Override
    public void attack (Pokemon playerPokemon, Pokemon opponentPokemon){
        if (playerPokemon instanceof WaterPokemon && opponentPokemon.getType().equals("Fire")) {
            playerPokemon.setAttack(1.5 * playerPokemon.getAttack());
        }
        super.attack(playerPokemon, opponentPokemon);
    }
    
}


