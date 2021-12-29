package by.solbegsoft.streamtask.util;

import by.solbegsoft.streamtask.dao.UserDAO;
import by.solbegsoft.streamtask.model.User;
import org.apache.commons.lang3.StringUtils;
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

    String allName = "IvanJohnMariaKatePhilKateJohn";
    String adultUsers = "JohnMariaKateKateJohn";
    String uniqueNames = "Ivan John Maria Kate Phil";
    String uniqueEmail = "idontliketorepeattwice@mail.rumsin@fds.comlemon@john.comj@weak.iodb@maria.ru" +
            "xray@gmail.comhh@tut.rupasswordisthesameaslogin@gmail.comreallycomplexmail222@hh.ru" +
            "reallycomplexmail333@hh.rureallycomplexmail444@hh.ru";
    String uniqueUsernameLine = "IvanJohnMariaKatePhil";
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
        Assertions.assertEquals(allName, StringUtils.deleteWhitespace(output.toString()));
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
        Assertions.assertEquals(adultUsers, StringUtils.deleteWhitespace(output.toString()));
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
        userDAO.printUniqueUserNames();
        Assertions.assertEquals(uniqueNames, StringUtils.deleteWhitespace(output.toString()));
    }

    @Test
    void printUniqueNames_emptyList_true() {
        userDAO.setUserList(emptyList);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals(noRegisteredUsersMessage, output.toString().trim());
    }

    @Test
    void printUniqueNames_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueNames_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUniqueNames_nullInUsersList_shouldPrintMessage() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueEmails_correctWork_true() {
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals(uniqueEmail, StringUtils.deleteWhitespace(output.toString()));
    }

    @Test
    void printUniqueEmails_emptyList_true() {
        userDAO.setUserList(emptyList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals(noRegisteredUsersMessage, output.toString().trim());
    }

    @Test
    void printUniqueEmails_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals("db@maria.ruxray@gmail.com", StringUtils.deleteWhitespace(output.toString()));
    }

    @Test
    void printUniqueEmails_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUniqueEmails_nullInUsersList_shouldPrintMessage() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals("db@maria.ruxray@gmail.com", StringUtils.deleteWhitespace(output.toString()));
    }

    @Test
    void printAgeSum_correctWork_true() {
        userDAO.printTotalUserAge();
        Assertions.assertEquals("140", output.toString().trim());
    }

    @Test
    void printAgeSum_emptyList_true() {
        userDAO.setUserList(emptyList);
        userDAO.printTotalUserAge();
        Assertions.assertEquals(noRegisteredUsersMessage, output.toString().trim());
    }

    @Test
    void printAgeSum_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        userDAO.printTotalUserAge();
        Assertions.assertEquals("23", output.toString().trim());
    }

    @Test
    void printAgeSum_usersListIsNull_shouldPrintMessage() {
        userDAO.setUserList(null);
        userDAO.printTotalUserAge();
        Assertions.assertEquals("0", output.toString().trim());
    }

    @Test
    void printAgeSum_nullInUsersList_shouldPrintEmptyMessage() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printTotalUserAge();
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
        userDAO.printTotalUserAge();
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