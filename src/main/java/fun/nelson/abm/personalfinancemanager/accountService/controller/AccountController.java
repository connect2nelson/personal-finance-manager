package fun.nelson.abm.personalfinancemanager.accountService.controller;


import fun.nelson.abm.personalfinancemanager.accountService.domain.Account;
import fun.nelson.abm.personalfinancemanager.accountService.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AccountController {

    private AccountService accountService;

    @GetMapping("/{name}")
    public Account getAccountByName(@PathVariable String name ){
        return new Account(name);
    }

    @GetMapping("/current")
    public Account getCurrentAccount(Principal principal) {
        return accountService.findByName(principal.getName());
    }
}
