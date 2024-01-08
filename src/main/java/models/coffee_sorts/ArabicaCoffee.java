package models.coffee_sorts;
import models.Coffee;
import enums.CoffeeSorts;
import enums.CoffeeState;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class ArabicaCoffee extends Coffee {
    public ArabicaCoffee(String name, double price, double volume, CoffeeState state) {
        super(name, price, volume, CoffeeSorts.Arabica, state);
    }

    @Override
    public String getSortName() {
        return "арабика";
    }

    public static ArabicaCoffee loadFromJson(String filePath) {
        try {
            Gson gson = new Gson();
            String json = Files.readString(Paths.get(filePath));
            return gson.fromJson(json, ArabicaCoffee.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}