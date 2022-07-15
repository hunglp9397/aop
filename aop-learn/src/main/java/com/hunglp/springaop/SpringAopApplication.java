package com.hunglp.springaop;

import com.hunglp.springaop.dao.AccountDAO;
import com.hunglp.springaop.dao.MemberDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringAopApplication {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringAopApplication.class, args);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
        accountDAO.addAccount();

        MemberDAO memberDAO = context.getBean(("memberDAO"), MemberDAO.class);
        memberDAO.addAccount();

    }
}
