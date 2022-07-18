package com.hunglp.springaop.dao;


import com.hunglp.springaop.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    private String name;

    private String serviceCode;

    public void addAccount() {
        System.out.println(getClass() + "VOID" + " : Adding an empty account");
    }


    public boolean addAccount(String name) {
        System.out.println(getClass() + "BOOLEAN" + " : Adding an account");
        return true;
    }

    public void addAccount(Account account){
        System.out.println(getClass() + " : " + "Adding an account object");
    }

    public String getName() {
        System.out.println("In getName() ");
        return name;
    }

    public void setName(String name) {
        System.out.println("Int setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("In getServiceCode() ");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("Int setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
