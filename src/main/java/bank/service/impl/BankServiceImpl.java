package bank.service.impl;

import bank.entity.Bank;
import bank.service.BankService;

import java.util.Random;

public class BankServiceImpl implements BankService {
    private Bank bank = null;

    /*Создание экземпляра банка на основе входных данных, в виде id и имени банка*/
    @Override
    public void create(Integer id, String name) {
        this.bank = new Bank(id, name);
        calcRating();
        calcMoney();
        calcRate();
    }

    /*Обновление экземпляра банка на основе входных данных, в виде другого экземпляра банка*/
    @Override
    public void update(Bank bank) {
        this.bank = bank;
    }

    /*Обнуление экземпляра банка*/
    @Override
    public void delete() {
        this.bank = null;
    }

    /*Возврат экземпляра банка*/
    @Override
    public Bank getBank() {
        return this.bank;
    }

    /*Расчёт рейтинга банка рандомным способом*/
    private void calcRating() {
        Random random = new Random();
        this.bank.setRating(random.nextInt(0, 100));
    }

    /*Расчёт суммы денег банка рандомным способом*/
    private void calcMoney() {
        Random random = new Random();
        this.bank.setMoney(random.nextDouble(0, 1000000));
    }

    /*Расчёт процентной ставки банка, с учётом рейтинга банка*/
    private void calcRate() {
        this.bank.setInterestRate(20.0 - this.bank.getRating() / 5.0);
    }

    /*Добавление суммы денег в банк, путём взятия суммы из банка, добавления к ней суммы, которую хотим внести
    * и внесения в банк новой получившейся суммы*/
    @Override
    public void addMoney(Bank bank, Double sumMoney) {
        Double sum = bank.getMoney();
        bank.setMoney(sum + sumMoney);
    }

    /*Вычитание суммы денег из банка, путём взятия денег из банка, сравнения её с суммой списания, вычитание суммы
    * списания из суммы, взятой из банка, и запись в банк новой суммы*/
    @Override
    public Boolean subtractMoney(Bank bank, Double sumMoney) {
        Double sum = bank.getMoney();
        if (sumMoney > sum)
            return Boolean.FALSE;
        bank.setMoney(sum - sumMoney);
        return Boolean.TRUE;
    }
}
