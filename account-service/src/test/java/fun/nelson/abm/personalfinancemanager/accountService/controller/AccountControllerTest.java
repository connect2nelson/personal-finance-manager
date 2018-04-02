package fun.nelson.abm.personalfinancemanager.accountService.controller;

import fun.nelson.abm.personalfinancemanager.accountService.domain.Account;
import fun.nelson.abm.personalfinancemanager.accountService.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WithMockUser(username = "ram", roles={"ADMIN"})
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;


    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void shouldGetAccountByName() throws Exception {
        final Account account = new Account();
        account.setName("Nelson");

        given(accountService.findByName(account.getName())).willReturn(account);

        mockMvc.perform(get("/v1/account/" + account.getName()))
                .andExpect(jsonPath("$.name").value(account.getName()))
                .andExpect(status().isOk());

    }


}