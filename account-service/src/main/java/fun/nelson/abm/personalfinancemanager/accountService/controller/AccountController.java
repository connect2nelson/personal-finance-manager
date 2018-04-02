package fun.nelson.abm.personalfinancemanager.accountService.controller;


import fun.nelson.abm.personalfinancemanager.accountService.domain.Account;
import fun.nelson.abm.personalfinancemanager.accountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/v1/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{name}")
    public Account getAccountByName(@PathVariable String name ){
        return accountService.findByName(name);
    }

    @GetMapping("/current")
    public Account getCurrentAccount(Principal principal) {

        String name = principal.getName();
        return accountService.findByName(name);
    }
}
