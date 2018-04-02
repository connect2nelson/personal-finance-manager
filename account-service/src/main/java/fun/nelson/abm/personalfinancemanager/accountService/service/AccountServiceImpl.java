package fun.nelson.abm.personalfinancemanager.accountService.service;

import fun.nelson.abm.personalfinancemanager.accountService.domain.Account;
import fun.nelson.abm.personalfinancemanager.accountService.domain.Currency;
import fun.nelson.abm.personalfinancemanager.accountService.domain.Saving;
import fun.nelson.abm.personalfinancemanager.accountService.domain.User;
import fun.nelson.abm.personalfinancemanager.accountService.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;


@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository repository;

    public Account findByName(String accountName) {
        return repository.findByName(accountName);
    }

    @Override
    public Account create(User user) {

        Account existing = repository.findByName(user.getUsername());

        if (existing != null) {
            logger.error("The account already exists");
        }

        Saving saving = new Saving();
        saving.setAmount(new BigDecimal(0));
        saving.setCurrency(Currency.getDefault());
        saving.setInterest(new BigDecimal(0));
        saving.setDeposit(false);
        saving.setCapitalization(false);

        Account account = new Account();
        account.setName(user.getUsername());
        account.setLastSeen(new Date());
        account.setSaving(saving);

        repository.save(account);

        logger.info("new account has been created: " + account.getName());
        return account;

    }

    @Override
    public void saveChanges(String name, Account update) {

        Account account = repository.findByName(name);

        Assert.notNull(account, "can't find account with name " + name);

        account.setIncomes(update.getIncomes());
        account.setExpenses(update.getExpenses());
        account.setSaving(update.getSaving());
        account.setNote(update.getNote());
        account.setLastSeen(new Date());
        repository.save(account);

        logger.debug("account {} changes has been saved", name);

//        statisticsClient.updateStatistics(name, account);
    }
}
