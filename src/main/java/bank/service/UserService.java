package bank.service;

import bank.entity.User;

import java.time.LocalDate;

public interface UserService {
    void create(Integer id, String name, String surname, LocalDate birthDay, String work);
    void update(User user);
    void delete();
    User getUser();

    void changeWork(String newWork, Double newMonthSalary);
}
