package bank.service;

import bank.entity.Bank;

public interface BankService {
    void create(Integer id, String name);
    void update(Bank bank);
    void delete();
    Bank getBank();

    Boolean addBankOffice(BankOfficeService bankOffice);
    Boolean delBankOffice(BankOfficeService bankOffice);
    Boolean addBankATM(AtmService bankATM);
    Boolean delBankATM(AtmService bankATM);
    Boolean addEmployee(EmployeeService employee);
    Boolean delEmployee(EmployeeService employee);
    Boolean addUser(UserService user);
    Boolean delUser(UserService user);

    String getInfo();

    void addMoney(Bank bank, Double sumMoney);
    Boolean subtractMoney(Bank bank, Double sumMoney);
}