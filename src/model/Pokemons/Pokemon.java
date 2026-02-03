package model.Pokemons;

public class Pokemon{
    //attributes
    private String index;
    private String name;
    private int level;
    private String type;
    private double hp;
    private int maxHp = 300;
    private double attack;
    private double defense;
    private int speed;
    private String ability;
    private boolean fainted;

    //constructor
    public Pokemon(String index, String name, int level, String type, double hp, double attack, double defense, int speed, String ability){
        this.index = index;
        this.name = name;
        this.level = level;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.ability = ability;
        this.fainted = false;
    }

    //getters setters
    public String getIndex(){
        return index;
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    public double getHp(){
        return hp;
    }

    public void setHp(double hp){
        this.hp = hp;
    }

    public double getMaxHp(){
        return maxHp;
    }

    public boolean isFainted(){
        return fainted;
    }

    public void setFainted(boolean fainted){
        this.fainted = fainted;
    }

    public String getType(){
        return type;
    }   

    public double getAttack(){
        return attack;
    }   

    public void setAttack(double attack){
        this.attack = attack;
    }

    public double getDefense(){
        return defense;
    }

    public void setDefense(double defense){
        this.defense = defense;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public String getAbility(){
        return ability;
    }

    public double calculateDamage (double dmg){
        double damage = dmg/2 - defense/4;
        this.hp -= damage;
        if (hp <= 0) {
            hp = 0;
            setFainted(true);
        }
        return damage;
    }

    public void heal (double healHP){
        this.hp += healHP;
        if (this.hp > maxHp){
            this.hp = maxHp;
        }
    }

    // public void takeDamage(double dmg){
    //    hp -= (dmg/10);
    //     if (hp <= 0) {
    //         hp = 0;
    //         fainted = true;}
    // }

    public void attack (Pokemon playerPokemon, Pokemon opponentPokemon){
        double damage = opponentPokemon.calculateDamage(playerPokemon.getAttack());
        System.out.printf ("\n\t\t%s attacked on %s, causing %.2f damage!\n", playerPokemon.getName(), opponentPokemon.getName(), damage);
    }

    public String toString(){
        return String.format("%s (Level %d) \nType: %s\nHP: %.2f/%d\nAttack: %.2f\nDefense: %.2f\nSpeed: %d\nAbility: %s", 
                             name, level, type, hp, maxHp, attack, defense, speed, ability);
    }

    public String simpleString(){
        return String.format("%s (Level %d) -- HP: %.2f \n\tATK: %.2f \n\tDEF: %.2f", name, level, hp, attack, defense);
    }
}