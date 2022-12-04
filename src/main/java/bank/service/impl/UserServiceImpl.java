package bank.service.impl;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import bank.entity.*;

import java.time.LocalDate;

import bank.service.CreditAccountService;
import bank.service.PaymentAccountService;
import bank.service.UserService;

public class UserServiceImpl implements UserService {
    private User user = null;

    /*Создание экземпляра пользователя на основе входных данных, в виде id, имени, фамилии, даты рождения и работы
    пользователя*/
    @Override
    public void create(Integer id, String name, String surname, LocalDate birthDay, String work) {
        this.user = new User(id, name, surname, birthDay, work);
        calcSalary();
        calcCreditRating();
    }

    /*Обновление экземпляра пользователя на основе входных данных, в виде другого экземпляра пользователя*/
    @Override
    public void update(User user) {
        this.user = user;
    }

    /*Обнуление экземпляра пользователя*/
    @Override
    public void delete() {
        this.user = null;
    }

    /*Возврат экземпляра пользователя*/
    @Override
    public User getUser() {
        return this.user;
    }

    /*Расчёт зарплаты пользователя рандомным способом*/
    private void calcSalary() {
        Random random = new Random();
        user.setMonthSalary(random.nextDouble(1, 10000));
    }

    /*Расчёт кредитного рейтинга пользователя на основе его зарплаты*/
    private void calcCreditRating() {
        int creditRating = 0;
        Integer startRat = 0;
        Integer endRat = 1000;
        while ((startRat != 10000) && (creditRating == 0)) {
            if ((user.getMonthSalary() <= endRat) && (user.getMonthSalary() >= startRat))
                creditRating = endRat / 10;
            else {
                startRat += 1000;
                endRat += 1000;
            }
        }
        user.setCreditRating(creditRating);
    }

    /*Добавление банка в список банков клиента*/
    @Override
    public void addBank(Bank bank) {
        ArrayList<Bank> banks = this.user.getBanks();
        banks.add(bank);
        this.user.setBanks(banks);
    }

    /*Удаление банка из списка банков клиента*/
    @Override
    public void delBank(Bank bank) {
        ArrayList<Bank> banks = this.user.getBanks();
        banks.remove(bank);
        this.user.setBanks(banks);
    }

    /*Добавление кредитного счёта в список кредитных счетов клиента*/
    @Override
    public Boolean addCreditAcc(CreditAccountService creditAcc) {
        if (!Objects.equals(creditAcc.getCreditAcc().getUser(), this.user))
            return false;
        ArrayList<CreditAccount> creditAccounts = this.user.getCreditAccounts();
        creditAccounts.add(creditAcc.getCreditAcc());
        this.user.setCreditAccounts(creditAccounts);
        creditAcc.getCreditAcc().setUser(this.user);
        return true;
    }

    /*Удаление кредитного счёта из списка кредитных счетов клиента*/
    @Override
    public Boolean delCreditAcc(CreditAccountService creditAcc) {
        if (!Objects.equals(creditAcc.getCreditAcc().getUser(), this.user))
            return false;
        ArrayList<CreditAccount> creditAccounts = this.user.getCreditAccounts();
        creditAccounts.remove(creditAcc.getCreditAcc());
        this.user.setCreditAccounts(creditAccounts);
        creditAcc.getCreditAcc().setUser(this.user);
        return true;
    }

    /*Добавление платёжного счёта в список платёжных счетов клиента*/
    @Override
    public Boolean addPayAcc(PaymentAccountService payAcc) {
        if (!Objects.equals(payAcc.getPayAcc().getUser(), this.user))
            return false;
        ArrayList<PaymentAccount> paymentAccounts = this.user.getPaymentAccounts();
        paymentAccounts.add(payAcc.getPayAcc());
        this.user.setPaymentAccounts(paymentAccounts);
        payAcc.getPayAcc().setUser(this.user);
        return true;
    }

    /*Удаление платёжного счёта из списка платёжных счетов клиента*/
    @Override
    public Boolean delPayAcc(PaymentAccountService payAcc) {
        if (!Objects.equals(payAcc.getPayAcc().getUser(), this.user))
            return false;
        ArrayList<PaymentAccount> paymentAccounts = this.user.getPaymentAccounts();
        paymentAccounts.remove(payAcc.getPayAcc());
        this.user.setPaymentAccounts(paymentAccounts);
        payAcc.getPayAcc().setUser(this.user);
        return true;
    }

    @Override
    public String getInfo() {
        StringBuilder returnStr = new StringBuilder(this.user.toString());
        returnStr.append("\n\nПлатёжные счета клиента:");
        for (int i_1 = 0; i_1 < this.user.getPaymentAccounts().size(); i_1++) {
            returnStr.append(String.format("\n\n#№%d\n", i_1));
            returnStr.append(user.getPaymentAccounts().get(i_1).toString());
        }
        returnStr.append("\n\nКредитные счета клиента:");
        for (int i_1 = 0; i_1 < this.user.getCreditAccounts().size(); i_1++) {
            returnStr.append(String.format("\n\n#№%d\n", i_1));
            returnStr.append(user.getCreditAccounts().get(i_1).toString());
        }
        return returnStr.toString();
    }

    /*Смена пользователем работы, а соответственно, и заработной платы, и пересчёт его кредитного рейтинга*/
    @Override
    public void changeWork(String newWork, Double newMonthSalary) {
        user.setWork(newWork);
        user.setMonthSalary(newMonthSalary);
        int creditRating = 0;
        int startRat = 0;
        int endRat = 1000;
        while ((startRat != 10000) && (creditRating == 0)) {
            if ((newMonthSalary <= endRat) && (newMonthSalary >= startRat))
                creditRating = endRat / 10;
            else {
                startRat += 1000;
                endRat += 1000;
            }
        }
        user.setCreditRating(creditRating);
    }
}