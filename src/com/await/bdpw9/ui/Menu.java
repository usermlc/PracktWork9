package com.await.bdpw9.ui;

import com.await.bdpw9.da.ConnectToDB;
import com.await.bdpw9.da.UserDao;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void menu() throws SQLException {
        UserService userService = new UserService(new UserDao(ConnectToDB.connect()));

        int mainMenu;
        Scanner mainInput = new Scanner(System.in);
        do{
            System.out.println("Вітаємо в центрі управління базою даних");
            System.out.println("[1] Додати користувача");
            System.out.println("[2] Редагувати користувача");
            System.out.println("[3] Переглянути користувачів");
            System.out.println("[4] Видалити користувача");
            System.out.println("[5] Знайти користувача по ID");
            System.out.println("[6] Вийти з програми");

            mainMenu = mainInput.nextInt();
            switch (mainMenu){
                case 1:
                    userService.addUser();
                    break;
                case 2:
                    userService.updateUser();
                    break;
                case 3:
                    userService.listUsers();
                    break;
                case 4:
                    userService.deleteUser();
                    break;
                case 5:
                    userService.findUserId();
                    break;
                case 6:
                    System.out.println("Дякуємо що скористалися нашими послугами");
                    System.exit(5);
                    break;
            }
            if(mainMenu > 5){
                System.out.println();
                System.out.println("Введено хибні дані");
            }
        }while (mainMenu != 5);
    }

}
