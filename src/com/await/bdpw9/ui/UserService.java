package com.await.bdpw9.ui;

import com.await.bdpw9.da.UserDao;
import com.await.bdpw9.da.entity.User;
import com.await.bdpw9.rm.EmailValidation;
import com.await.bdpw9.rm.Handler;
import com.await.bdpw9.rm.NameValidation;
import com.await.bdpw9.rm.PasswordValidation;
import com.rickotb.bdpw7.da.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private UserDao userDao;

    boolean isValid = false;

    Handler nameHandler = new NameValidation();
    Handler emailHandler = new EmailValidation();
    Handler passwordHandler = new PasswordValidation();


    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void listUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();
        if (users.size() > 0) {
            for (User user : users) {
                System.out.println("ID: " + user.getId() + ", Ім'я: "
                        + user.getName() + ", Емайл: " + user.getEmail());
            }
        } else {
            System.out.println("Користувачів не знайдено!");
            Menu.menu();
        }
    }

    public void addUser() throws SQLException {
        List<User> users = userDao.getAllUsers();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть ім'я користувача");
            String name = scanner.nextLine();

            for(User userEquals : users){
                if(userEquals.getName().equals(name)){
                    System.out.println("Користувач вже інсує");
                    Menu.menu();
                }else {
                    System.out.println("Ведіть електронну пошту");
                    String email = scanner.nextLine();
                    System.out.println("Введіть пароль");
                    String password = scanner.nextLine();

                    nameHandler.setNextCHandler(nameHandler);
                    emailHandler.setNextCHandler(emailHandler);
                    passwordHandler.setNextCHandler(passwordHandler);

                    User user = new User(name, email, password);

                    isValid = nameHandler.validate(user);
                    isValid = emailHandler.validate(user);
                    isValid = passwordHandler.validate(user);

                    if (isValid) {
                        userDao.addUser(user);
                        System.out.println("Користувача додано!");
                        Menu.menu();
                    } else {
                        Menu.menu();

                        for (String validMsg : user.getValidationMessage()) {
                            System.out.println(validMsg);
                        }
                    }
                }
            }
        }catch (Exception ignored){}
    }

    public void updateUser() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        List<User> users = userDao.getAllUsers();
        listUsers();

        try {
            System.out.print("Введіть ID користувача: ");
            int id = scanner.nextInt();

            scanner.nextLine();
            System.out.println("Введіть нове ім'я користувача");
            String name = scanner.nextLine();

            for (User userEquals: users){
                if (userEquals.getName().equals(name)) {
                    System.out.println("Користувач вже інсує");
                } else {
                    System.out.println("Ведіть нову електронну пошту");
                    String email = scanner.nextLine();

                    System.out.println("Введіть новий пароль");
                    String password = scanner.nextLine();

                    nameHandler.setNextCHandler(nameHandler);
                    emailHandler.setNextCHandler(emailHandler);
                    passwordHandler.setNextCHandler(passwordHandler);

                    User user = new User(name, email, password);

                    isValid = nameHandler.validate(user);
                    isValid = emailHandler.validate(user);
                    isValid = passwordHandler.validate(user);

                    if (isValid) {
                        userDao.updateUser(user);
                        System.out.println("Користувача оновлено!");
                        Menu.menu();
                    } else {
                        Menu.menu();
                        for (String validMsg : user.getValidationMessage()) {
                            System.out.println(validMsg);
                        }
                    }
                }
            }
        }catch (Exception ignored){}
    }

    public void deleteUser() throws SQLException {
        listUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть ID користувача: ");
        int id = scanner.nextInt();
        userDao.deleteUser(id);
        System.out.println("Користувача видалено!");
    }

    public void findUserId() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введіть ID користувача: ");
            int id = scanner.nextInt();

            userDao.getUserById(id);
            System.out.println("Користувача знайдено Ім'я: " + userDao.getUserById(id).getName()
                    + " Емайл: " + userDao.getUserById(id).getEmail());
            Menu.menu();

        }catch (Exception e){
            System.out.println("Користувача не існує!");
            Menu.menu();
        }
    }
}
