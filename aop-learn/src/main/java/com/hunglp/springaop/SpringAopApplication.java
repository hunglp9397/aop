package com.hunglp.springaop;

import com.hunglp.springaop.dao.AccountDAO;
import com.hunglp.springaop.dao.MemberDAO;
import com.hunglp.springaop.model.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.AnnotatedArrayType;

@SpringBootApplication
public class SpringAopApplication {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringAopApplication.class, args);


//         Test add*() execution "beforeAddAccount"
//        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
//        accountDAO.addAccount();

//        MemberDAO memberDAO = context.getBean(("memberDAO"), MemberDAO.class);
//        memberDAO.addAccount();

//        // Test PointCut
//        AccountDAO accountDAO = new AccountDAO();
//
//        accountDAO.setName("foobar");
//        accountDAO.setServiceCode("silver");
//        String name = accountDAO.getName();
//        String serviceCode = accountDAO.getServiceCode();

        // Test add*() execution JoinPoint
          AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
          accountDAO.addAccount(new Account("HungLePhi", "1"));



    }
}
