package bank;

import java.time.LocalDate;
import bank.service.impl.*;
import bank.entity.enums.StatusATM;
import bank.entity.enums.StatusOffice;

public class Main {
    public static void main(String[] args) {
        //Bank
        System.out.println("Bank:");
        BankServiceImpl bankService = new BankServiceImpl();
        bankService.create(1, "bank_name");
        System.out.println(bankService.getBank());

        //Bank Office
        System.out.println("\n\nOffice:");
        BankOfficeServiceImpl bankOfficeService = new BankOfficeServiceImpl();
        bankOfficeService.create(1, "office_name", bankService.getBank(), "address_1",
                StatusOffice.Work, 15000.0);
        System.out.println(bankOfficeService.getBankOffice());

        //Employee
        System.out.println("\n\nEmployee:");
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        employeeService.create(1, "Ivan", "Ivanov", LocalDate.of(2000, 10, 11),
                bankService.getBank(), bankOfficeService.getBankOffice(), "job_1", 100.0);
        System.out.println(employeeService.getEmployee());

        //Bank ATM
        System.out.println("\n\nATM:");
        AtmServiceImpl atmService = new AtmServiceImpl();
        atmService.create(1, "ATM_1", StatusATM.Work, Boolean.TRUE, Boolean.TRUE,
                100.0, bankService.getBank(), bankOfficeService.getBankOffice(),
                employeeService.getEmployee());
        System.out.println(atmService.getBankATM());

        //User
        System.out.println("\n\nUser:");
        UserServiceImpl userService = new UserServiceImpl();
        userService.create(1, "Maxim", "Maximovich", LocalDate.of(2000, 10, 11),
                "work_1");
        System.out.println(userService.getUser());

        //Payment Account
        System.out.println("\n\nPayment Account:");
        PaymentAccountServiceImpl paymentAccountService = new PaymentAccountServiceImpl();
        paymentAccountService.create(1, userService.getUser(), bankService.getBank());
        System.out.println(paymentAccountService.getPayAcc());

        //Credit Account
        System.out.println("\n\nCredit Account:");
        CreditAccountServiceImpl creditAccountService = new CreditAccountServiceImpl();
        creditAccountService.create(1, userService.getUser(), bankService.getBank(), employeeService.getEmployee(),
                paymentAccountService.getPayAcc(), LocalDate.of(2022, 11, 11), 12,
                150.0);
        System.out.println(creditAccountService.getCreditAcc());
    }
}