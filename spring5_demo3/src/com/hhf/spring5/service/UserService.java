package com.hhf.spring5.service;

import com.hhf.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;

/**
 * @author HP
 * @Transactional 可添加到类或者方法上
 */
@Service
//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,timeout = -1,readOnly = false)
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    public void accountMoney(){
/*        try {
            userDao.addMoney();
            userDao.reduceMoney();
        }catch (Exception e){
            //出现异常，事务回滚
        }*/

        userDao.addMoney();
        //int i = 10/0;
        userDao.reduceMoney();
    }
}
