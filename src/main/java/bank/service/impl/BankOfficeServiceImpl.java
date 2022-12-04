package bank.service.impl;

import bank.entity.Bank;
import bank.entity.BankATM;
import bank.entity.BankOffice;
import bank.entity.enums.StatusOffice;
import bank.service.BankOfficeService;

public class BankOfficeServiceImpl implements BankOfficeService {
    private BankOffice bankOffice = null;

    /*Создание экземпляра офиса на основе входных данных, в виде id, имени, банка, адреса, статуса и стоимости
    аренды офиса*/
    @Override
    public void create(Integer id, String name, Bank bank, String address, StatusOffice status, Double rentCost) {
        bank.setCountOffice(bank.getCountOffice() + 1);
        this.bankOffice = new BankOffice(id, name, bank, address, status, rentCost);
    }

    /*Обновление экземпляра офиса на основе входных данных, в виде другого экземпляра офиса*/
    @Override
    public void update(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    /*Обнуление экземпляра офиса*/
    @Override
    public void delete() {
        this.bankOffice = null;
    }

    /*Возврат экземпляра офиса*/
    @Override
    public BankOffice getBankOffice() {
        return this.bankOffice;
    }

    /*Добавление суммы денег в офис, и, соответственно, добавление суммы денег в банк, которому
    принадлежит данный офис*/
    @Override
    public void addMoney(Double sumMoney) {
        Double sumBank = this.bankOffice.getBank().getMoney();
        Double sumOffice = this.bankOffice.getMoney();
        this.bankOffice.getBank().setMoney(sumBank + sumMoney);
        this.bankOffice.setMoney(sumOffice + sumMoney);
    }

    /*Вычитание суммы денег из офиса, и, соответственно, вычитание суммы денег из банка, которому принадлежит
     * данный офис, с проверкой того, достаточно ли денег в офисе, чтобы их вычесть. Если не достаточно, то
     * возвращается false, иначе true*/
    @Override
    public Boolean subtractMoney(Double sumMoney) {
        Double sumBank = this.bankOffice.getBank().getMoney();
        Double sumOffice = this.bankOffice.getMoney();
        if (sumOffice < sumMoney)
            return Boolean.FALSE;
        this.bankOffice.getBank().setMoney(sumBank - sumMoney);
        this.bankOffice.setMoney(sumOffice - sumMoney);
        return Boolean.TRUE;
    }

    /*Добавление нового банкомата в офис, и, соответственно, добавление нового банкомата в банк, которому
     * принадлежит данный офис, с проверкой того, можно ли добавить в этот офис новый банкомат.
     * Если нельзя достаточно, то возвращается false, иначе true*/
    @Override
    public Boolean addATM() {
        if (!this.bankOffice.getMaySetATM())
            return Boolean.FALSE;
        this.bankOffice.setCountATM(this.bankOffice.getCountATM() + 1);
        this.bankOffice.getBank().setCountATM(this.bankOffice.getBank().getCountATM() + 1);
        return Boolean.TRUE;
    }

    /*Вычитание банкомата из офиса, и, соответственно, вычитание банкомата из банка, которому
     * принадлежит данный офис*/
    @Override
    public void subtractATM(BankATM bankATM) {
        this.bankOffice.setCountATM(this.bankOffice.getCountATM() - 1);
        this.bankOffice.getBank().setCountATM(this.bankOffice.getCountATM() - 1);
        bankATM.setBankOffice(null);
    }
}