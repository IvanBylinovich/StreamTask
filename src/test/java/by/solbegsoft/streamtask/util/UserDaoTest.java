package by.solbegsoft.streamtask.util;

import by.solbegsoft.streamtask.dao.UserDAO;
import by.solbegsoft.streamtask.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserDaoTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream standardOut = System.out;
    private UserDAO userDAO = new UserDAO();

    private List<User> blankUserInList = Arrays.asList(
            new User(),
            new User(),
            new User("Maria", 23, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com"))
    );

    private List<User> nullInUsersList = Arrays.asList(
            null,
            null,
            new User("Maria", 23, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com"))
    );

    private List<User> emptyList = new ArrayList<>();

    String allName = "Ivan John Maria Kate Phil Kate John";
    String adultUsers = "John Maria Kate Kate John";
    String uniqueNames = "Ivan John Maria Kate Phil";
    String uniqueEmail = "idontliketorepeattwice@mail.ru msin@fds.com lemon@john.com j@weak.io db@maria.ru" +
            " xray@gmail.com hh@tut.ru passwordisthesameaslogin@gmail.com reallycomplexmail222@hh.ru" +
            " reallycomplexmail333@hh.ru reallycomplexmail444@hh.ru";
    String uniqueUsernameLine = "IvanJohnMariaKatePhil";
    String errorMessage = "error message";
    String noRegisteredUsersMessage = "";

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(standardOut);
    }

    @Test
    void printAllName_correctWork_true() {
        userDAO.printUserNames();
        Assertions.assertEquals(allName, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printAllName_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        userDAO.printUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());

    }

    @Test
    void printAllName_emptyList_true() {
        userDAO.setUserList(emptyList);
        userDAO.printUserNames();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printAllName_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);

        userDAO.printUserNames();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printAllName_nullInUsersList_shouldPrintNonNullNames() {
        userDAO.setUserList(nullInUsersList);

        userDAO.printUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printAllAdultUsername_correctWork_true() {
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals(adultUsers, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printAllAdultUsername_emptyList_true() {
        userDAO.setUserList(emptyList);

        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals(noRegisteredUsersMessage, output.toString().trim());
    }

    @Test
    void printAllAdultUsername_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        

        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printAllAdultUsername_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printAllAdultUsername_nullInUsersList_shouldPrintMariaMessage() {
        userDAO.setUserList(nullInUsersList);
        
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueNames_correctWork_true() {
        userDAO.printUserNamesIfUnique();
        Assertions.assertEquals(uniqueNames, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUniqueNames_emptyList_true() {
        userDAO.setUserList(emptyList);
        

        userDAO.printUserNamesIfUnique();
        Assertions.assertEquals(noRegisteredUsersMessage, output.toString().trim());
    }

    @Test
    void printUniqueNames_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        

        userDAO.printUserNamesIfUnique();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueNames_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        

        userDAO.printUserNamesIfUnique();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUniqueNames_nullInUsersList_shouldPrintMessage() {
        userDAO.setUserList(nullInUsersList);
        

        userDAO.printUserNamesIfUnique();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueEmails_correctWork_true() {
        userDAO.printUserEmailsIfUnique();
        Assertions.assertEquals(uniqueEmail, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUniqueEmails_emptyList_true() {
        userDAO.setUserList(emptyList);
        

        userDAO.printUserEmailsIfUnique();
        Assertions.assertEquals(noRegisteredUsersMessage, output.toString().trim());
    }

    @Test
    void printUniqueEmails_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        

        userDAO.printUserEmailsIfUnique();
        Assertions.assertEquals("db@maria.ru\r\nxray@gmail.com\r\n", output.toString());
    }

    @Test
    void printUniqueEmails_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        

        userDAO.printUserEmailsIfUnique();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUniqueEmails_nullInUsersList_shouldPrintMessage() {
        userDAO.setUserList(nullInUsersList);
        
        userDAO.printUserEmailsIfUnique();
        Assertions.assertEquals("db@maria.ru xray@gmail.com", output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printAgeSum_correctWork_true() {
        userDAO.printAgeSum();
        Assertions.assertEquals("140", output.toString().trim());
    }

    @Test
    void printAgeSum_emptyList_true() {
        userDAO.setUserList(emptyList);
        

        userDAO.printAgeSum();
        Assertions.assertEquals(noRegisteredUsersMessage, output.toString().trim());
    }

    @Test
    void printAgeSum_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        

        userDAO.printAgeSum();
        Assertions.assertEquals("23", output.toString().trim());
    }

    @Test
    void printAgeSum_usersListIsNull_shouldPrintMessage() {
        userDAO.setUserList(null);
        

        userDAO.printAgeSum();
        Assertions.assertEquals("0", output.toString().trim());
    }

    @Test
    void printAgeSum_nullInUsersList_shouldPrintEmptyMessage() {
        userDAO.setUserList(nullInUsersList);
        

        userDAO.printAgeSum();
        Assertions.assertEquals("23", output.toString().trim());
    }

    @Test
    void printUniqueUsernameLine_correctWork_true() {
        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals(uniqueUsernameLine, output.toString().trim());
    }

    @Test
    void printUniqueUsernameLine_emptyList_true() {
        userDAO.setUserList(emptyList);
        

        userDAO.printAgeSum();
        Assertions.assertEquals(noRegisteredUsersMessage, output.toString().trim());
    }

    @Test
    void printUniqueUsernameLine_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        

        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueUsernameLine_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        

        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUniqueUsernameLine_nullInUsersList_shouldPrintEmptyMessage() {
        userDAO.setUserList(nullInUsersList);
        

        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals("", output.toString().trim());
    }

}