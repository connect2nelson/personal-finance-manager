package fun.nelson.abm.personalfinancemanager.accountService.service;

import fun.nelson.abm.personalfinancemanager.accountService.domain.Account;
import fun.nelson.abm.personalfinancemanager.accountService.domain.User;
import fun.nelson.abm.personalfinancemanager.accountService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements  AccountService{

    @Autowired
    private AccountRepository repository;


    public Account findByName(String accountName) {
        return  repository.findByName(accountName);
    }

    @Override
    public Account create(User user) {
        return null;
    }

    @Override
    public void saveChanges(String name, Account update) {

    }
}
