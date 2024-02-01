import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cereal {

    private String name;
    private char type;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private float fiber;
    private float carbohydrates;
    private int sugar;
    private int potassium;
    private int vitamins;
    private int shelf;
    private float weight;
    private float cups;
    private double rating;
    

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setType(char newType) {
        this.type = newType;
    }

    public char getType() {
        return type;
    }

    public void setCalories(int newCalories) {
        this.calories = newCalories;
    }

    public int getCalories() {
        return calories;
    }

    public void setProtein(int newProtein) {
        this.protein = newProtein;
    }

    public int getProtein() {
        return protein;
    }

    public void setFat(int newFat) {
        this.fat = newFat;
    }

    public int getFat() {
        return fat;
    }

    public void setSodium(int newSodium) {
        this.sodium = newSodium;
    }

    public int getSodium() {
        return sodium;
    }

    public void setFiber(float newFiber) {
        this.fiber = newFiber;
    }

    public float getFiber() {
        return fiber;
    }

    public void setCarbohydrates(float newCarbohydrates) {
        this.carbohydrates = newCarbohydrates;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setSugar(int newSugar) {
        this.sugar = newSugar;
    }

    public int getSugar() {
        return sugar;
    }

    public void setPotassium(int newPotassium) {
        this.potassium = newPotassium;
    }

    public int getPotassium() {
        return potassium;
    }

    public void setVitamins(int newVitamins) {
        this.vitamins = newVitamins;
    }

    public int getVitamins() {
        return vitamins;
    }

    public void setShelf(int newShelf) {
        this.shelf = newShelf;
    }

    public int getShelf() {
        return shelf;
    }

    public void setWeight(float newWeight) {
        this.weight = newWeight;
    }

    public float getWeight() {
        return weight;
    }

    public void setCups(float newCups) {
        this.cups = newCups;
    }

    public float getCups() {
        return cups;
    }

    public void setRating(double newRating) {
        this.rating = newRating;
    }

    public double getRating() {
        return rating;
    }

    public String toString(){
        return "Hello"; 
    }

    public double calculateHealthScore(){
        return (protein + fiber + vitamins + potassium) / (calories + fat + sodium + sugar);
    }

    public static List<Cereal> readCerealsFromCSV(String filename) {
        List<Cereal> cereals = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String name = fields[0].trim();
                    double healthScore = Double.parseDouble(fields[1].trim());
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return cereals;
    }

    public static Cereal findmostHealthy(List<Cereal> cereals){
        if(cereals.isEmpty()){
            return null;
        }

        Cereal mostHealthy = cereals.get(0);
        double maxHealthScore = mostHealthy.calculateHealthScore();

        for (Cereal cereal : cereals) {
            double healthScore = cereal.calculateHealthScore();
            if (healthScore > maxHealthScore) {
                maxHealthScore = healthScore;
                mostHealthy = cereal;
            }
        }

        return mostHealthy;
    }

    public static Cereal findLeastHealthy(List<Cereal> cereals){
        if(cereals.isEmpty()){
            return null;
        }

        Cereal leastHealthy = cereals.get(0);
        double minHealthScore = leastHealthy.calculateHealthScore();

        for (Cereal cereal : cereals) {
            double healthScore = cereal.calculateHealthScore();
            if (healthScore < minHealthScore) {
                minHealthScore = healthScore;
                leastHealthy = cereal;
            }
        }

        return leastHealthy;
    }
    public static void main(String[] args) {
        List<Cereal> cereals = readCerealsFromCSV("cereals.csv");
        Cereal mostHealthy = findmostHealthy(cereals);
        Cereal leastHealthy = findLeastHealthy(cereals);


        if (mostHealthy != null) {
            System.out.println("Most Healthy Cereal: " + mostHealthy.getName());
        } 
        else {
            System.out.println("No cereals in the list.");
        }

        if (leastHealthy != null) {
            System.out.println("Least Healthy Cereal: " + leastHealthy.getName());
        } 
        else {
            System.out.println("No cereals in the list.");
        }
    }
}
