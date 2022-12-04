package bank.service;

import bank.entity.Bank;

public interface BankService {
    void create(Integer id, String name);
    void update(Bank bank);
    void delete();
    Bank getBank();
    void addMoney(Bank bank, Double sumMoney);
    Boolean subtractMoney(Bank bank, Double sumMoney);
}