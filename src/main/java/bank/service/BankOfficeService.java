package bank.service;

import bank.entity.Bank;
import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.entity.enums.StatusOffice;

public interface BankOfficeService {
    void create(Integer id, String name, Bank bank, String address, StatusOffice status, Double rentCost);
    void update(BankOffice bankOffice);
    void delete();
    BankOffice getBankOffice();

    void addMoney(Double sumMoney);
    Boolean subtractMoney(Double sumMoney);
    Boolean addATM();
    void subtractATM(BankATM bankATM);
}