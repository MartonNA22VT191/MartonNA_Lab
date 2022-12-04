package bank.service;

import bank.entity.Bank;
import bank.entity.User;
import java.time.LocalDate;

public interface UserService {
    void create(Integer id, String name, String surname, LocalDate birthDay, String work);
    void update(User user);
    void delete();
    User getUser();

    void addBank(Bank bank);
    void delBank(Bank bank);
    Boolean addCreditAcc(CreditAccountService creditAcc);
    Boolean delCreditAcc(CreditAccountService creditAcc);
    Boolean addPayAcc(PaymentAccountService payAcc);
    Boolean delPayAcc(PaymentAccountService payAcc);

    String getInfo();

    void changeWork(String newWork, Double newMonthSalary);
}