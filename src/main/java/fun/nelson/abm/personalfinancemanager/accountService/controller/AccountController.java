package fun.nelson.abm.personalfinancemanager.accountService.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/{name}")
    public Account getAccountByName(@PathVariable String name ){
        return new Account(name);
    }
}
