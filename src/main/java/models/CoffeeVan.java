package models;
import java.util.List;
import enums.CoffeeSorts;
import enums.CoffeeState;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CoffeeVan {
    private final double volume;
    private final List<Coffee> cargo;

    public CoffeeVan(double volume) {
        this.volume = volume;
        this.cargo = new ArrayList<>();
    }

    public void loadCoffee(Coffee coffee) {
        if (getRemainingCapacity() >= coffee.getVolume()) {
            cargo.add(coffee);
        } else {
            System.out.println("Фургон перегружен. Невозможно загрузить кофе.");
        }
    }

    public void sortByPriceToVolumeRatio() {
        cargo.sort((c1, c2) -> {
            double ratio1 = c1.getPrice() / c1.getVolume();
            double ratio2 = c2.getPrice() / c2.getVolume();
            return Double.compare(ratio1, ratio2);
        });
    }

    public List<Coffee> findCoffeeByFilters(double minPrice, double maxPrice, double minVolume, double maxVolume,
                                            CoffeeSorts sort, CoffeeState state) {
        List<Coffee> result = new ArrayList<>();
        for (Coffee coffee : cargo) {
            if ((coffee.getPrice() >= minPrice) && (coffee.getPrice() <= maxPrice)
                    && (coffee.getVolume() >= minVolume) && (coffee.getVolume() <= maxVolume)) {
                if (sort == null) {
                    if (state == null) {
                        result.add(coffee);
                    } else {
                        if (state == coffee.getState()) {
                            result.add(coffee);
                        }
                    }
                } else {
                    if (state == null) {
                        if (sort == coffee.getSort()) {
                            result.add(coffee);
                        }
                    } else {
                        if ((sort == coffee.getSort()) && (state == coffee.getState())) {
                            result.add(coffee);
                        }
                    }
                }
            }
        }
        return result;
    }

    private double getRemainingCapacity() {
        double usedCapacity = cargo.stream().mapToDouble(Coffee::getVolume).sum();
        return volume - usedCapacity;
    }

    public void saveToJson(String filePath) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);
            Files.writeString(Paths.get(filePath), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CoffeeVan loadFromJson(String filePath) {
        try {
            Gson gson = new Gson();
            String json = Files.readString(Paths.get(filePath));
            return gson.fromJson(json, CoffeeVan.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clearVan() {
        cargo.clear();
    }
}