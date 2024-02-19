package com.await.bdpw9.rm;

import com.await.bdpw9.da.entity.User;

public class NameValidation implements Handler{
    private Handler nextHandler;

    @Override
    public boolean validate(User user){
        if (user.getName().length() < 2 || user.getName().length() > 50) {
            System.out.println("Ім'я не може містити менше 2 символів, або більше 50-ти");
            return false;
        }
        return true;
    }
    @Override
    public void setNextCHandler(Handler handler){
        nextHandler = handler;
    }
}

