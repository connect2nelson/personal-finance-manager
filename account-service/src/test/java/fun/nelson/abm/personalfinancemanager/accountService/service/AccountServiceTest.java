package fun.nelson.abm.personalfinancemanager.accountService.service;


import fun.nelson.abm.personalfinancemanager.accountService.domain.Account;
import fun.nelson.abm.personalfinancemanager.accountService.domain.Currency;
import fun.nelson.abm.personalfinancemanager.accountService.domain.User;
import fun.nelson.abm.personalfinancemanager.accountService.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository repository;

    @Test
    public void shouldFindByName() {
        final Account account = new Account();
        account.setName("test");

        when(repository.findByName(account.getName())).thenReturn(account);

        Account found = accountService.findByName(account.getName());

        assertEquals(account, found);
    }

    @Test
    public void shouldCreateAUserIfUserDoesNotExist() {
        final Account account = new Account();
        account.setName("test");

        when(repository.findByName(account.getName())).thenReturn(null);

        User user = new User();
        user.setUsername("test_user");
        user.setPassword("test_pwd");
        Account newlyCreatedAccount = accountService.create(user);

        //check user related data
        assertEquals(newlyCreatedAccount.getName(), user.getUsername());
        assertTrue(newlyCreatedAccount.getLastSeen() !=null);

        //check savings related data
        assertEquals(newlyCreatedAccount.getSaving().getAmount(), new BigDecimal(0));
        assertEquals(newlyCreatedAccount.getSaving().getCurrency(), Currency.getDefault());
        assertEquals(newlyCreatedAccount.getSaving().getInterest(), new BigDecimal(0));
        assertEquals(newlyCreatedAccount.getSaving().getDeposit(), false);
        assertEquals(newlyCreatedAccount.getSaving().getCapitalization(), false);
    }

}