package util;

import java.util.ArrayList;
import java.util.Scanner;

import model.Item;
import model.Player;
import model.Pokemons.Pokemon;


public class ItemManagement {
    static Scanner scanner = new Scanner(System.in);

    // ------ ITEM UTILITIES ------
    public static final ArrayList<Item> allItems = new ArrayList<>(){
        {
            add(new Item("Potion", "Restores a small amount of HP.", 30));
            add(new Item("Super Potion", "Restores a moderate amount of HP.", 70));
            add(new Item("Hyper Potion", "Restores a large amount of HP.", 120));
            add(new Item("Revive", "Revives a fainted Pokemon with half HP.", 150));
            add(new Item("Max Revive", "Revives a fainted Pokemon with full HP.", 300));
        }
    };

    public static String displayItem(Item item) {
        return String.format("%-15s | %-40s | $%7.2f%n", item.getName(), item.getDescription(), (double) item.getPrice());
    }

    public static void displayAllItems() {
        System.out.printf("\t%-6s | %-15s | %-40s | %-8s%n", "INDEX", "ITEM", "DESCRIPTION", "PRICE");
        System.out.println("\t--------------------------------------------------------------------------------");

        for (int i = 0; i < allItems.size(); i++) {
            Item item = allItems.get(i);
            
            System.out.printf("\t[%d]    | ", i + 1);
            
            System.out.printf("%-15s | %-40s | $%7.2f%n", 
                item.getName(), 
                item.getDescription(), 
                (double) item.getPrice()
            );
        }
        System.out.println("\t--------------------------------------------------------------------------------");
    }

    public static void displayPlayerInventory(Player player){
        ArrayList<Item> inventory = player.getInventory();
        if(inventory.isEmpty()){
            System.out.println("\n\t[0] Your inventory is empty!");
            return;
        }

        System.out.println("\n\t═══════════════ YOUR INVENTORY ═══════════════\n");
        System.out.printf("\t%-6s | %-15s | %-40s | %-8s%n", "INDEX", "ITEM", "DESCRIPTION", "PRICE");
        System.out.println("\t--------------------------------------------------------------------------------");

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            
            System.out.printf("\t[%d]    | ", i + 1);
            
            System.out.printf("%-15s | %-40s | $%7.2f%n", 
                item.getName(), 
                item.getDescription(), 
                (double) item.getPrice()
            );
        }
        System.out.println("\t--------------------------------------------------------------------------------");
    }

    public static Item getItemByIndex(int index) {
        if (index < 0 || index >= allItems.size()) {
            System.out.println("\n\t[x] This item is not in the store! Please select a valid item.");
            return null;
        }
        return allItems.get(index);
    }

    public static void purchaseItem(Player player, Item item){
        if(item.getPrice() > player.getCoins()){
            System.out.println("\n\t[x] You don't have enough coins to buy this item!");
            return;
        }else{
            player.setCoins(player.getCoins() - item.getPrice());
            player.getInventory().add(item);
            System.out.println("\n\t[✓] You have purchased " + item.getName() + "!");
            System.out.println("\n\tYou now have " + player.getCoins() + " coins left.");
        }
    }

    public static void useItem(Pokemon pokemon, Item item){
        switch(item.getName()){
            case "Potion":
                item.usePotion(pokemon);
                break;
            case "Super Potion":
                item.useSuperPotion(pokemon);
                break;
            case "Hyper Potion":
                item.useHyperPotion(pokemon);
                break;
            case "Revive":
                item.useRevive(pokemon);
                break;
            case "Max Revive":
                item.useMaxRevive(pokemon);
                break;
            default:
                System.out.println("Item not recognized!");
                break;
        }
    }

}
