package by.solbegsoft.streamtask.service;

import by.solbegsoft.streamtask.dao.InMemoryUserDAO;
import by.solbegsoft.streamtask.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserService {
   /* Implement:
            1. Print name of each user
            2. Print name only if user age >= 18
            3. Print unique names
            4. Print unique emails of users
            5. Print total age (sum) of all users
            6. Print all unique usernames in one line (should be one String) */

    List<User> userList = InMemoryUserDAO.buildUsersCollection();


    public void printAllName() {
        InMemoryUserDAO.buildUsersCollection().stream().forEach(u -> System.out.println(u.getName()));
    }

    public void printAllAdultUserName() {
        InMemoryUserDAO.buildUsersCollection().stream().filter(u -> u.getAge() > 17).forEach(u -> System.out.println(u.getName()));
    }

    public void printUniqueNames() {
        InMemoryUserDAO.buildUsersCollection().stream().map(User::getName).distinct().forEach(System.out::println);
    }

    public void printUniqueEmails() {
        InMemoryUserDAO.buildUsersCollection().stream().map(User::getEmails).flatMap(Collection::stream).toList().stream().distinct().forEach(System.out::println);
    }

    public void printAgeSum() {
        Optional<Integer> ageSum = InMemoryUserDAO.buildUsersCollection().stream().map(User::getAge).reduce(Integer::sum);
        if (ageSum.isPresent()) {
            System.out.println(ageSum.get());
        } else {
            System.out.println(0);
        }

    }

    public void printUniqueUsernameLine() {
        Optional<String> lineU = InMemoryUserDAO.buildUsersCollection().stream().map(User::getName).distinct().reduce(String::concat);
        if (lineU.isPresent()) {
            System.out.println(lineU.get());
        } else System.out.println(0);


    }


}
