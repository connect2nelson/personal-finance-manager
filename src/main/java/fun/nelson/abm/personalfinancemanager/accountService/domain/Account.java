package fun.nelson.abm.personalfinancemanager.accountService.domain;

public class Account {
    private String name;

    Account(){

    }
    public Account(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
