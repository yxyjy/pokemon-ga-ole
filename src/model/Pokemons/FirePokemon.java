package model.Pokemons;


public class FirePokemon extends Pokemon {

    //constructor
    public FirePokemon(String index, String name, int level, double hp, double attack, double defense, int speed, String ability) {
        super(index, name, level, "Fire", hp, attack, defense, speed, ability);
        
    }

    @Override
    public void attack (Pokemon playerPokemon, Pokemon opponentPokemon){
        if (playerPokemon instanceof FirePokemon && opponentPokemon.getType().equals("Grass")) {
            playerPokemon.setAttack(1.5 * playerPokemon.getAttack());
        }
        super.attack(playerPokemon, opponentPokemon);
    }
    
}
