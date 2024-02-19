package com.await.bdpw9.rm;

import com.await.bdpw9.da.entity.User;

public class PasswordValidation implements Handler{
    private Handler nextHandler;

    @Override
    public boolean validate(User user){
        if (user.getPassword().length() < 8) {
            System.out.println("Пароль не можу містити менше 8 символів");
            return false;
        } else if (!user.getPassword().matches(".*\\d.*")) {
            System.out.println("Пароль має містити хоча-б одну цифру.");
            return false;
        }
        return true;
    }

    @Override
    public void setNextCHandler(Handler handler){
        nextHandler = handler;
    }
}
