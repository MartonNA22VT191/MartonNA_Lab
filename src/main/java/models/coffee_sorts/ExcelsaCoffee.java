package models.coffee_sorts;
import models.Coffee;
import enums.CoffeeSorts;
import enums.CoffeeState;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class ExcelsaCoffee extends Coffee {
    public ExcelsaCoffee(String name, double price, double volume, CoffeeState state) {
        super(name, price, volume, CoffeeSorts.Excelsa, state);
    }

    @Override
    public String getSortName() {
        return "эксцельса";
    }

    public static ExcelsaCoffee loadFromJson(String filePath) {
        try {
            Gson gson = new Gson();
            String json = Files.readString(Paths.get(filePath));
            return gson.fromJson(json, ExcelsaCoffee.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}