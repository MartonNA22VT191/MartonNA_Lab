package bank.service.impl;

import bank.entity.User;
import bank.service.UserService;

import java.time.LocalDate;
import java.util.Random;

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