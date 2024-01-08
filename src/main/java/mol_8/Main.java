package mol_8;
import enums.CoffeeSorts;
import enums.CoffeeState;
import models.Coffee;
import models.CoffeeVan;

import java.util.List;
import java.util.Scanner;
import models.coffee_sorts.ArabicaCoffee;
import models.coffee_sorts.ExcelsaCoffee;
import models.coffee_sorts.RobustaCoffee;
import models.coffee_sorts.LibericaCoffee;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        CountDownLatch initializationLatch = new CountDownLatch(1);

        AtomicReference<CoffeeVan> coffeeVanAtomic = new AtomicReference<>(null);
        AtomicReference<ArabicaCoffee> arabica_1Atomic = new AtomicReference<>(null);
        AtomicReference<ArabicaCoffee> arabica_2Atomic = new AtomicReference<>(null);
        AtomicReference<ArabicaCoffee> arabica_3Atomic = new AtomicReference<>(null);
        AtomicReference<RobustaCoffee> robusta_1Atomic = new AtomicReference<>(null);
        AtomicReference<RobustaCoffee> robusta_2Atomic = new AtomicReference<>(null);
        AtomicReference<RobustaCoffee> robusta_3Atomic = new AtomicReference<>(null);
        AtomicReference<ExcelsaCoffee> excelsa_1Atomic = new AtomicReference<>(null);
        AtomicReference<ExcelsaCoffee> excelsa_2Atomic = new AtomicReference<>(null);
        AtomicReference<ExcelsaCoffee> excelsa_3Atomic = new AtomicReference<>(null);
        AtomicReference<LibericaCoffee> liberica_1Atomic = new AtomicReference<>(null);
        AtomicReference<LibericaCoffee> liberica_2Atomic = new AtomicReference<>(null);
        AtomicReference<LibericaCoffee> liberica_3Atomic = new AtomicReference<>(null);

        Thread initializationThread = new Thread(() -> {
            CoffeeVan coffeeVan_ = CoffeeVan.loadFromJson("src/files/van.json");
            coffeeVanAtomic.set(coffeeVan_);

            ArabicaCoffee arabica_1_ = ArabicaCoffee.loadFromJson("src/files/arabica1.json");
            arabica_1Atomic.set(arabica_1_);
            ArabicaCoffee arabica_2_ = ArabicaCoffee.loadFromJson("src/files/arabica2.json");
            arabica_2Atomic.set(arabica_2_);
            ArabicaCoffee arabica_3_ = ArabicaCoffee.loadFromJson("src/files/arabica3.json");
            arabica_3Atomic.set(arabica_3_);

            RobustaCoffee robusta_1_ = RobustaCoffee.loadFromJson("src/files/robusta1.json");
            robusta_1Atomic.set(robusta_1_);
            RobustaCoffee robusta_2_ = RobustaCoffee.loadFromJson("src/files/robusta2.json");
            robusta_2Atomic.set(robusta_2_);
            RobustaCoffee robusta_3_ = RobustaCoffee.loadFromJson("src/files/robusta3.json");
            robusta_3Atomic.set(robusta_3_);

            ExcelsaCoffee excelsa_1_ = ExcelsaCoffee.loadFromJson("src/files/excelsa1.json");
            excelsa_1Atomic.set(excelsa_1_);
            ExcelsaCoffee excelsa_2_ = ExcelsaCoffee.loadFromJson("src/files/excelsa2.json");
            excelsa_2Atomic.set(excelsa_2_);
            ExcelsaCoffee excelsa_3_ = ExcelsaCoffee.loadFromJson("src/files/excelsa3.json");
            excelsa_3Atomic.set(excelsa_3_);

            LibericaCoffee liberica_1_ = LibericaCoffee.loadFromJson("src/files/liberica1.json");
            liberica_1Atomic.set(liberica_1_);
            LibericaCoffee liberica_2_ = LibericaCoffee.loadFromJson("src/files/liberica2.json");
            liberica_2Atomic.set(liberica_2_);
            LibericaCoffee liberica_3_ = LibericaCoffee.loadFromJson("src/files/liberica3.json");
            liberica_3Atomic.set(liberica_3_);

            initializationLatch.countDown();
        });

        initializationThread.start();

        AtomicReference<Double> minPrice = new AtomicReference<>(0.0);
        AtomicReference<Double> maxPrice = new AtomicReference<>(99999.0);
        AtomicReference<Double> minVolume = new AtomicReference<>(0.0);
        AtomicReference<Double> maxVolume = new AtomicReference<>(99999.0);
        AtomicReference<CoffeeSorts> sort = new AtomicReference<>(null);
        AtomicReference<CoffeeState> state = new AtomicReference<>(null);

        Thread menuThread = new Thread(() -> {
            try {
                initializationLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CoffeeVan coffeeVan = coffeeVanAtomic.get();
            ArabicaCoffee arabica_1 = arabica_1Atomic.get();
            ArabicaCoffee arabica_2 = arabica_2Atomic.get();
            ArabicaCoffee arabica_3 = arabica_3Atomic.get();
            RobustaCoffee robusta_1 = robusta_1Atomic.get();
            RobustaCoffee robusta_2 = robusta_2Atomic.get();
            RobustaCoffee robusta_3 = robusta_3Atomic.get();
            ExcelsaCoffee excelsa_1 = excelsa_1Atomic.get();
            ExcelsaCoffee excelsa_2 = excelsa_2Atomic.get();
            ExcelsaCoffee excelsa_3 = excelsa_3Atomic.get();
            LibericaCoffee liberica_1 = liberica_1Atomic.get();
            LibericaCoffee liberica_2 = liberica_2Atomic.get();
            LibericaCoffee liberica_3 = liberica_3Atomic.get();

            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            while (running) {
                System.out.println("Меню:");
                System.out.println("1. Посмотреть содержимое фургона");
                System.out.println("2. Посмотреть содержимое склада");
                System.out.println("3. Загрузить всё что можно со склада в фургон");
                System.out.println("4. Опустошить фургон");
                System.out.println("5. Отсортировать товары в фургоне на основе соотношения цены и объёма.");
                System.out.println("6. Ввести фильтры поиска содержимого фургона");
                System.out.println("7. Очистить фильтры содержимого фургона");
                System.out.println("8. Выход");
                System.out.print("Выберите опцию: ");
                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.println("Содержимое фургона:");
                        List<Coffee> list = coffeeVan.findCoffeeByFilters(minPrice.get(), maxPrice.get(),
                                minVolume.get(), maxVolume.get(), sort.get(), state.get());
                        System.out.println(list.toString() + "\n");
                    }
                    case 2 -> {
                        System.out.println("Содержимое склада:");
                        System.out.println("[" + arabica_1.toString() + ",");
                        System.out.println(arabica_2.toString() + ",");
                        System.out.println(arabica_3.toString() + ",");
                        System.out.println(robusta_1.toString() + ",");
                        System.out.println(robusta_2.toString() + ",");
                        System.out.println(robusta_3.toString() + ",");
                        System.out.println(excelsa_1.toString() + ",");
                        System.out.println(excelsa_2.toString() + ",");
                        System.out.println(excelsa_3.toString() + ",");
                        System.out.println(liberica_1.toString() + ",");
                        System.out.println(liberica_2.toString() + ",");
                        System.out.println(liberica_3.toString() + "]\n");
                    }
                    case 3 -> {
                        coffeeVan.loadCoffee(arabica_1);
                        coffeeVan.loadCoffee(arabica_2);
                        coffeeVan.loadCoffee(arabica_3);
                        coffeeVan.loadCoffee(robusta_1);
                        coffeeVan.loadCoffee(robusta_2);
                        coffeeVan.loadCoffee(robusta_3);
                        coffeeVan.loadCoffee(excelsa_1);
                        coffeeVan.loadCoffee(excelsa_2);
                        coffeeVan.loadCoffee(excelsa_3);
                        coffeeVan.loadCoffee(liberica_1);
                        coffeeVan.loadCoffee(liberica_2);
                        coffeeVan.loadCoffee(liberica_3);
                    }
                    case 4 -> {
                        coffeeVan.clearVan();
                    }
                    case 5 -> {
                        coffeeVan.sortByPriceToVolumeRatio();
                    }
                    case 6 -> {
                        boolean running2 = true;
                        while (running2) {
                            System.out.println("Выберите фильтр:");
                            System.out.println("1. Минимальная цена");
                            System.out.println("2. Максимальная цена");
                            System.out.println("3. Минимальный объём");
                            System.out.println("4. Максимальный объём");
                            System.out.println("5. Сорт");
                            System.out.println("6. Физическое состояние");
                            System.out.println("7. Выход из установки фильтров");
                            System.out.print("Выберите опцию: ");
                            int filterOption = scanner.nextInt();
                            switch (filterOption) {
                                case 1 -> {
                                    double newMin = scanner.nextDouble();
                                    minPrice.set(newMin);
                                }
                                case 2 -> {
                                    double newMax = scanner.nextDouble();
                                    maxPrice.set(newMax);
                                }
                                case 3 -> {
                                    double newMinV = scanner.nextDouble();
                                    minVolume.set(newMinV);
                                }
                                case 4 -> {
                                    double newMaxV = scanner.nextDouble();
                                    maxVolume.set(newMaxV);
                                }
                                case 5 -> {
                                    System.out.println("1. Арабика");
                                    System.out.println("2. Робуста");
                                    System.out.println("3. Либерика");
                                    System.out.println("4. Эксцельса");
                                    System.out.print("Выберите сорт: ");
                                    int filterSortOption = scanner.nextInt();
                                    switch (filterSortOption) {
                                        case 1 -> sort.set(CoffeeSorts.Arabica);
                                        case 2 -> sort.set(CoffeeSorts.Robusta);
                                        case 3 -> sort.set(CoffeeSorts.Liberica);
                                        default -> sort.set(CoffeeSorts.Excelsa);
                                    }
                                }
                                case 6 -> {
                                    System.out.println("1. Зерно");
                                    System.out.println("2. Молотый");
                                    System.out.println("3. Растворимый в банке");
                                    System.out.println("4. Растворимый в пакетике");
                                    System.out.print("Выберите сорт: ");
                                    int filterStateOption = scanner.nextInt();
                                    switch (filterStateOption) {
                                        case 1 -> state.set(CoffeeState.Grain);
                                        case 2 -> state.set(CoffeeState.Ground);
                                        case 3 -> state.set(CoffeeState.InstantInCans);
                                        default -> state.set(CoffeeState.SolubleInSachets);
                                    }
                                }
                                case 7 -> {
                                    System.out.println("Выход");
                                    running2 = false;
                                }
                                default -> System.out.println("Неверный выбор.");
                            }
                        }
                    }
                    case 7 -> {
                        minPrice.set(0.0);
                        maxPrice.set(99999.0);
                        minVolume.set(0.0);
                        maxVolume.set(99999.0);
                        sort.set(null);
                        state.set(null);
                    }
                    case 8 -> {
                        System.out.println("Выход из программы.");
                        running = false;
                    }
                    default -> System.out.println("Неверный выбор.");
                }
            }
        });
        menuThread.start();

        try {
            initializationThread.join();
            menuThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}