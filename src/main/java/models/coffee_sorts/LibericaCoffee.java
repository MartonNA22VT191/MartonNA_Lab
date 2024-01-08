package models.coffee_sorts;
import models.Coffee;
import enums.CoffeeSorts;
import enums.CoffeeState;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class LibericaCoffee extends Coffee {
    public LibericaCoffee(String name, double price, double volume, CoffeeState state) {
        super(name, price, volume, CoffeeSorts.Liberica, state);
    }

    @Override
    public String getSortName() {
        return "либерика";
    }

    public static LibericaCoffee loadFromJson(String filePath) {
        try {
            Gson gson = new Gson();
            String json = Files.readString(Paths.get(filePath));
            return gson.fromJson(json, LibericaCoffee.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}