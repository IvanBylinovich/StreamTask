package by.solbegsoft.streamtask.dao;

import by.solbegsoft.streamtask.model.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional.ofNullable(userList).stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .map(User::getName)
                .filter(StringUtils::isNotBlank)
                .forEach(System.out::println);
    }

    public void printUserNamesWhereAgeMoreThanNumber(int age) {
        Optional.ofNullable(userList).stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(u -> StringUtils.isNotBlank(u.getName()) && u.getAge() >= age)
                .forEach(u -> System.out.println(u.getName()));
    }

    public void printUniqueUserNames() {
        Optional.ofNullable(userList).stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .map(User::getName)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .forEach(System.out::println);
    }

    public void printUniqueUserEmails() {
        Optional.ofNullable(userList).stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .map(User::getEmails)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .forEach(System.out::println);
    }

    public void printTotalUserAge() {
        Optional.ofNullable(userList).stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .map(User::getAge)
                .filter(Objects::nonNull)
                .reduce(Integer::sum)
                .ifPresentOrElse(System.out::println, () -> System.out.println(0));
    }

    public void printUniqueUsernameLine() {
        System.out.println(Optional.ofNullable(userList).stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .map(User::getName)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .collect(Collectors.joining(", ")));
    }
}



