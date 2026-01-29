package model;

public class Pokeball {
    private String type;
    private double catchRate;
    private double availabilityRate;

    public Pokeball(String type, double catchRate, double availabilityRate){
        this.type = type;
        this.catchRate = catchRate;
        this.availabilityRate = availabilityRate;   
    }

    public String getType(){
        return type;
    }

    public double getCatchRate(){
        return catchRate;
    }

    public double getAvailabilityRate(){
        return availabilityRate;
    }
}