package models;
import enums.CoffeeSorts;
import enums.CoffeeState;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class Coffee {
    private final String name;
    private final double price;
    private final double volume;
    private final CoffeeSorts sort;
    private final CoffeeState state;

    public Coffee(String name, double price, double volume, CoffeeSorts sort, CoffeeState state) {
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.sort = sort;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    public CoffeeState getState() {
        return state;
    }

    public String getStateName() {
        switch (state) {
            case Grain -> {
                return "зерновой";
            }
            case Ground -> {
                return "молотый";
            }
            case InstantInCans -> {
                return "растворимый в банках";
            }
            case SolubleInSachets -> {
                return "растворимый в пакетиках";
            }
        }
        return null;
    }

    public CoffeeSorts getSort() {
        return sort;
    }

    public abstract String getSortName();

    public void saveToJson(String filePath) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);
            Files.writeString(Paths.get(filePath), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getName() + ": {\nЦена: " + getPrice() + "\nОбъём: " + getVolume() + "\nСорт: " + getSortName() +
                "\nФиз. состояние: " + getStateName() + "\n}";
    }
}