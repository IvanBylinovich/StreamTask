package by.solbegsoft.streamtask.service;


import by.solbegsoft.streamtask.dao.InMemoryUserDAO;
import by.solbegsoft.streamtask.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class UserServiceTest {

    UserService userService = new UserService();
    List<User> userList = InMemoryUserDAO.buildUsersCollection();
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    String AllName = "Ivan\r\nJohn\r\nMaria\r\nKate\r\nPhil\r\nKate\r\nJohn\r\n";
    String adultUsers = "John\r\nMaria\r\nKate\r\nKate\r\nJohn\r\n";
    String UniqueNames = "Ivan\r\nJohn\r\nMaria\r\nKate\r\nPhil\r\n";
    String UniqueEmail = "idontliketorepeattwice@mail.ru\r\nmsin@fds.com\r\nlemon@john.com\r\nj@weak.io\r\ndb@maria.ru\r\nxray@gmail.com\r\nhh@tut.ru\r\npasswordisthesameaslogin@gmail.com\r\nreallycomplexmail222@hh.ru\r\nreallycomplexmail333@hh.ru\r\nreallycomplexmail444@hh.ru\r\n";

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @org.junit.jupiter.api.Test
    void printAllName() {
        userService.printAllName();
        Assertions.assertEquals(AllName, output.toString());
    }

    @org.junit.jupiter.api.Test
    void printAllAdultUserName() {
        userService.printAllAdultUserName();
        Assertions.assertEquals(adultUsers, output.toString());
    }


    @org.junit.jupiter.api.Test
    void printUniqueNames() {
        System.setOut(new PrintStream(output));
        userService.printUniqueNames();
        Assertions.assertEquals(UniqueNames, output.toString());
    }

    @org.junit.jupiter.api.Test
    void printUniqueEmails() {
        userService.printUniqueEmails();
        Assertions.assertEquals(UniqueEmail, output.toString());
    }

    @org.junit.jupiter.api.Test
    void printAgeSum() {
        userService.printAgeSum();
        Assertions.assertEquals("140\r\n", output.toString());
    }

    @org.junit.jupiter.api.Test
    void printUniqueUsernameLine() {
        userService.printUniqueUsernameLine();
        Assertions.assertEquals("IvanJohnMariaKatePhil\r\n", output.toString());
    }
}