package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args){
        UserService service = new UserServiceImpl();

        service.createUsersTable();

        User user = new User("Игорь", "Васичкин", (byte) 22);
        service.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем – " + user.getName() + " добавлен в базу данных" );

        user = new User("Антон", "Беляев", (byte) 32);
        service.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем – " + user.getName() + " добавлен в базу данных" );

        user = new User("Настя", "Глушкова", (byte) 25);
        service.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем – " + user.getName() + " добавлен в базу данных" );

        user = new User("Анна", "Каренина", (byte) 22);
        service.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем – " + user.getName() + " добавлен в базу данных" );

        user = new User("Лев", "Толстой", (byte) 11);
        service.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем – " + user.getName() + " добавлен в базу данных" );

        List<User> users = service.getAllUsers();
        users.forEach(System.out::println);

        service.cleanUsersTable();

        service.dropUsersTable();
    }
}
