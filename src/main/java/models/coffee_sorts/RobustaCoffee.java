package models.coffee_sorts;
import models.Coffee;
import enums.CoffeeSorts;
import enums.CoffeeState;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class RobustaCoffee extends Coffee {
    public RobustaCoffee(String name, double price, double volume, CoffeeState state) {
        super(name, price, volume, CoffeeSorts.Robusta, state);
    }

    @Override
    public String getSortName() {
        return "робуста";
    }

    public static RobustaCoffee loadFromJson(String filePath) {
        try {
            Gson gson = new Gson();
            String json = Files.readString(Paths.get(filePath));
            return gson.fromJson(json, RobustaCoffee.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}