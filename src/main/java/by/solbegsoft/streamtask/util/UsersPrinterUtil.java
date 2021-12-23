package by.solbegsoft.streamtask.util;

import by.solbegsoft.streamtask.dao.InMemoryUserDAO;
import by.solbegsoft.streamtask.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UsersPrinterUtil {

    private List<User> userList;

    public UsersPrinterUtil(InMemoryUserDAO userDAO) {
        this.userList = userDAO.getUsers();
    }

    public void printAllName() {
        if (userList == null || userList.stream().anyMatch(Objects::isNull)) {
            System.out.println("error message");
        } else if (userList.isEmpty()) {
            System.out.println("There are no registered users in the system");
        } else {
            userList.stream().map(User::getName).filter(Objects::nonNull).forEach(System.out::println);
        }
    }

    public void printAllAdultUsername() {
        if (userList == null || userList.stream().anyMatch(Objects::isNull)) {
            System.out.println("error message");
        } else if (userList.isEmpty()) {
            System.out.println("There are no registered users in the system");
        } else {
            userList.stream().filter(u -> u.getName() != null && u.getAge() > 17).forEach(u -> System.out.println(u.getName()));
        }
    }

    public void printUniqueNames() {
        if (userList == null || userList.stream().anyMatch(Objects::isNull)) {
            System.out.println("error message");
        } else if (userList.isEmpty()) {
            System.out.println("There are no registered users in the system");
        } else {
            userList.stream().map(User::getName).filter(Objects::nonNull).distinct().forEach(System.out::println);
        }
    }

    public void printUniqueEmails() {
        if (userList == null || userList.stream().anyMatch(Objects::isNull)) {
            System.out.println("error message");
        } else if (userList.isEmpty()) {
            System.out.println("There are no registered users in the system");
        } else {
            userList.stream()
                    .map(User::getEmails).filter(Objects::nonNull)
                    .flatMap(Collection::stream).toList()
                    .stream().distinct().forEach(System.out::println);
        }
    }

    public void printAgeSum() {
        if (userList == null || userList.stream().anyMatch(Objects::isNull)) {
            System.out.println("error message");
        } else if (userList.isEmpty()) {
            System.out.println("There are no registered users in the system");
        } else {
            Optional<Integer> ageSum = userList.stream().map(User::getAge).filter(Objects::nonNull).reduce(Integer::sum);
            ageSum.ifPresentOrElse(System.out::println, () -> System.out.println(0));
        }
    }

    public void printUniqueUsernameLine() {
        if (userList == null || userList.stream().anyMatch(Objects::isNull)) {
            System.out.println("error message");
        } else if (userList.isEmpty()) {
            System.out.println("There are no registered users in the system");
        } else {
            Optional<String> lineU = userList.stream().map(User::getName).filter(Objects::nonNull).distinct().reduce((x, y) -> x.concat(", " + y));
            lineU.ifPresentOrElse(System.out::println, () -> System.out.println("There are no registered users in the system"));
        }
    }

}
