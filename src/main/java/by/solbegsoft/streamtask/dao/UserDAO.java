package by.solbegsoft.streamtask.dao;

import by.solbegsoft.streamtask.model.User;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class UserDAO {

    private List<User> userList;

    {
        userList = Arrays.asList(
                new User("Ivan", 14, Arrays.asList("idontliketorepeattwice@mail.ru", "idontliketorepeattwice@mail.ru", "msin@fds.com")),
                new User("John", 18, Arrays.asList("lemon@john.com", "j@weak.io", "j@weak.io", "j@weak.io")),
                new User("Maria", 23, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com")),
                new User("Kate", 32, Arrays.asList("hh@tut.ru", "passwordisthesameaslogin@gmail.com")),
                new User("Phil", 12, Arrays.asList("reallycomplexmail222@hh.ru")),
                new User("Kate", 19, Arrays.asList("reallycomplexmail333@hh.ru")),
                new User("John", 22, Arrays.asList("reallycomplexmail444@hh.ru", "idontliketorepeattwice@mail.ru"))
        );
    }

    public void printUserNames() {
        Optional.ofNullable(userList).ifPresentOrElse(v -> v.stream()
                        .filter(Objects::nonNull)
                        .map(User::getName)
                        .filter(Objects::nonNull)
                        .forEach(System.out::println)
                , () -> System.out.println("")
        );
    }

    public void printUserNamesWhereAgeMoreThanNumber(int age) {
        Optional.ofNullable(userList).ifPresentOrElse(v -> v.stream()
                        .filter(Objects::nonNull)
                        .filter(u -> u.getName() != null && u.getAge() >= age)
                        .forEach(u -> System.out.println(u.getName()))
                , () -> System.out.println("")
        );
    }

    public void printUserNamesIfUnique() {
        Optional.ofNullable(userList).ifPresentOrElse(v -> v.stream()
                        .filter(Objects::nonNull)
                        .map(User::getName)
                        .filter(Objects::nonNull)
                        .distinct()
                        .forEach(System.out::println)
                , () -> System.out.println("")
        );

    }

    public void printUserEmailsIfUnique() {
        Optional.ofNullable(userList).ifPresentOrElse(v -> v.stream()
                        .filter(Objects::nonNull)
                        .map(User::getEmails)
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .toList()
                        .stream()
                        .distinct()
                        .forEach(System.out::println)
                , () -> System.out.println("")
        );

    }

    public void printAgeSum() {
        Optional.ofNullable(userList).ifPresentOrElse(v -> v.stream()
                        .filter(Objects::nonNull)
                        .map(User::getAge)
                        .filter(Objects::nonNull)
                        .reduce(Integer::sum)
                        .ifPresent(System.out::println)
                , () -> System.out.println(0)
        );

    }

    public void printUniqueUsernameLine() {
        Optional.ofNullable(userList).ifPresentOrElse(v -> v.stream()
                        .filter(Objects::nonNull)
                        .map(User::getName)
                        .filter(Objects::nonNull)
                .distinct().reduce((x, y) -> x.concat(", " + y))
                        //.collect(Collectors.joining())
                , () -> System.out.println("")
        );

    }
}


