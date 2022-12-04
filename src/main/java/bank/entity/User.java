package bank.entity;

import bank.entity.parentClasses.Human;

import java.time.LocalDate;

public class User extends Human {
    private String work;
    private Double monthSalary;
    private Integer creditRating;

    public User(Integer id, String name, String surname, LocalDate birthDay, String work) {
        super(id, name, surname, birthDay);
        this.work = work;
        this.monthSalary = null;
        this.creditRating = null;
    }

    public User(Integer id, String name, String surname, String middleName, LocalDate birthDay, String work) {
        super(id, name, surname, middleName, birthDay);
        this.work = work;
        this.monthSalary = null;
        this.creditRating = null;
    }

    @Override
    public String toString() {
        return "ФИО: " + super.getFullName() + "\nДата рождения: " + super.getBirthDay() + "\nРабота: " +
                work + "\nЗарплата: " + monthSalary + "\nКредитный рейтинг: " + creditRating;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(Double monthSalary) {
        this.monthSalary = monthSalary;
    }

    public Integer getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(Integer creditRating) {
        this.creditRating = creditRating;
    }
}
