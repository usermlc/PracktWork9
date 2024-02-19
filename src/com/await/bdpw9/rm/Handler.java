package com.await.bdpw9.rm;

import com.await.bdpw9.da.entity.User;

public interface Handler {
    public boolean validate(User user);
    public void setNextCHandler(Handler handler);
}
