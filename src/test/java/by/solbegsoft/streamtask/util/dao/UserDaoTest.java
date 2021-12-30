package by.solbegsoft.streamtask.util.dao;

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

    private List<User> usersHaveFieldAgeNull = Arrays.asList(
            new User("Ivan", null, Arrays.asList("3db@maria.ru", "88xray@gmail.com", "xray99@gmail.com")),
            new User("Kate", 33, Arrays.asList("2000db@maria.ru", "1993xray@gmail.com")),
            new User("Maria", null, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com")),
            new User("John", 22, Arrays.asList("reallycomplexmail444@hh.ru", "idontliketorepeattwice@mail.ru"))
    );

    private List<User> usersHaveFieldAgeZero = Arrays.asList(
            new User("Ivan", 0, Arrays.asList("3db@maria.ru", "88xray@gmail.com", "xray99@gmail.com")),
            new User("Kate", 0, Arrays.asList("2000db@maria.ru", "1993xray@gmail.com")),
            new User("Maria", 0, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com"))
    );

    private List<User> usersHaveFieldAgeNegativeNumber = Arrays.asList(
            new User("Ivan", -9, Arrays.asList("3db@maria.ru", "88xray@gmail.com", "xray99@gmail.com")),
            new User("Kate", 33, Arrays.asList("2000db@maria.ru", "1993xray@gmail.com")),
            new User("Maria", 12, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com"))
    );

    private List<User> allUsersHaveBlankFieldNameInList = Arrays.asList(
            new User("", 3, Arrays.asList("3db@maria.ru", "88xray@gmail.com", "xray99@gmail.com")),
            new User("", 33, Arrays.asList("2000db@maria.ru", "1993xray@gmail.com")),
            new User("", 23, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com"))
    );

    private List<User> usersHaveBlankFieldNameInList = Arrays.asList(
            new User("", 3, Arrays.asList("3db@maria.ru", "88xray@gmail.com", "xray99@gmail.com")),
            new User("", 33, Arrays.asList("2000db@maria.ru", "1993xray@gmail.com")),
            new User("Maria", 23, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com"))
    );

    private List<User> allUsersHaveBlankFieldMailInList = Arrays.asList(
            new User("Ivan", 3, Arrays.asList("", "", "")),
            new User("Kate", 33, Arrays.asList("", "", "", "", "", "")),
            new User("Pit", 23, Arrays.asList("", ""))
    );

    private List<User> usersHaveBlankFieldMailInList = Arrays.asList(
            new User("Ivan", 3, Arrays.asList("3db@maria.ru", "88xray@gmail.com", "3db@maria.ru")),
            new User("Kate", 33, Arrays.asList("", "", "")),
            new User("Maria", 23, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com"))
    );

    private List<User> allUsersHaveNullMailInList = Arrays.asList(
            new User("Ivan", 3, null),
            new User("Kate", 33, null),
            new User("Maria", 23, null)
    );

    private List<User> usersHaveNullMailList = Arrays.asList(
            new User("Ivan", 3, null),
            new User("Kate", 33, Arrays.asList("3db@maria.ru", "88xray@gmail.com", "xray99@gmail.com", "88xray@gmail.com")),
            new User("Maria", 23, null)
    );

    private List<User> usersHaveNullInMailList = Arrays.asList(
            new User("Ivan", 3, Arrays.asList("3db@maria.ru", "88xray@gmail.com", "xray99@gmail.com")),
            new User("Kate", 33, Arrays.asList(null, "88xray@gmail.com", null)),
            new User("Maria", 23, Arrays.asList("db@maria.ru", null, "xray@gmail.com", "xray@gmail.com"))
    );

    private List<User> emptyUserList = new ArrayList<>();

    String allName = "Ivan John Maria Kate Phil Kate John";
    String adultUsers = "John Maria Kate Kate John";
    String uniqueNames = "Ivan John Maria Kate Phil";
    String uniqueEmail = "idontliketorepeattwice@mail.ru msin@fds.com lemon@john.com j@weak.io db@maria.ru" +
            " xray@gmail.com hh@tut.ru passwordisthesameaslogin@gmail.com reallycomplexmail222@hh.ru" +
            " reallycomplexmail333@hh.ru reallycomplexmail444@hh.ru";
    String uniqueEmailIfNullInList = "3db@maria.ru 88xray@gmail.com xray99@gmail.com db@maria.ru xray@gmail.com";
    String uniqueEmailIfUsersHaveNullMailInList = "3db@maria.ru 88xray@gmail.com xray99@gmail.com";
    String uniqueUsernameLine = "Ivan, John, Maria, Kate, Phil";
    String blankMessage = "";

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(standardOut);
    }

    @Test
    void printUserNames_correctWork_true() {
        userDAO.printUserNames();
        Assertions.assertEquals(allName, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUserNames_allUsersHaveBlankFieldNameInList_shouldPrintBlankMessage() {
        userDAO.setUserList(allUsersHaveBlankFieldNameInList);
        userDAO.printUserNames();
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUserNames_usersHaveBlankFieldNameInList_shouldPrintNonBlankNames() {
        userDAO.setUserList(usersHaveBlankFieldNameInList);
        userDAO.printUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUserNames_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        userDAO.printUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUserNames_emptyList_true() {
        userDAO.setUserList(emptyUserList);
        userDAO.printUserNames();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUserNames_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        userDAO.printUserNames();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUserNames_nullInUsersList_shouldPrintNonNullNames() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUserNamesWhereAgeMoreThanNumber_correctWork_true() {
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals(adultUsers, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUserNamesWhereAgeMoreThanNumber_allUsersHaveBlankFieldNameInList_shouldPrintBlankMessage() {
        userDAO.setUserList(allUsersHaveBlankFieldNameInList);
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUserNamesWhereAgeMoreThanNumber_usersHaveBlankFieldNameInList_shouldPrintNonBlankNames() {
        userDAO.setUserList(usersHaveBlankFieldNameInList);
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUserNamesWhereAgeMoreThanNumber_emptyList_true() {
        userDAO.setUserList(emptyUserList);
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUserNamesWhereAgeMoreThanNumber_blankedUsersInList_shouldPrintNonBlankNames() {
        userDAO.setUserList(blankUserInList);
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUserNamesWhereAgeMoreThanNumber_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUserNamesWhereAgeMoreThanNumber_nullInUsersList_shouldPrintMariaMessage() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printUserNamesWhereAgeMoreThanNumber(18);
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueUserNames_correctWork_true() {
        userDAO.printUniqueUserNames();
        Assertions.assertEquals(uniqueNames, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUniqueUserNames_allUsersHaveBlankFieldNameInList_shouldPrintBlankMessage() {
        userDAO.setUserList(allUsersHaveBlankFieldNameInList);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUniqueUserNames_usersHaveBlankFieldNameInList_shouldPrintNonBlankNames() {
        userDAO.setUserList(usersHaveBlankFieldNameInList);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueUserNames_emptyList_true() {
        userDAO.setUserList(emptyUserList);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUniqueUserNames_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueUserNames_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUniqueUserNames_nullInUsersList_shouldPrintMessage() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printUniqueUserNames();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

    @Test
    void printUniqueUserEmails_correctWork_true() {
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals(uniqueEmail, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUniqueUserEmails_allUsersHaveBlankFieldMailInList_shouldPrintEmptyMessage() {
        userDAO.setUserList(allUsersHaveBlankFieldMailInList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUniqueUserEmails_usersHaveBlankFieldMailInList_shouldPrintNonBlankedMails() {
        userDAO.setUserList(usersHaveBlankFieldMailInList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals("3db@maria.ru 88xray@gmail.com db@maria.ru xray@gmail.com"
                , output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUniqueUserEmails_allUsersHaveNullMailInList_shouldPrintBlankMessage() {
        userDAO.setUserList(allUsersHaveNullMailInList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals(blankMessage, output.toString());
    }

    @Test
    void printUniqueUserEmails_usersHaveNullMailInList_shouldPrintBlankMessage() {
        userDAO.setUserList(usersHaveNullMailList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals(uniqueEmailIfUsersHaveNullMailInList, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUniqueUserEmails_usersHaveNullInMailList_shouldPrintBlankMessage() {
        userDAO.setUserList(usersHaveNullInMailList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals(uniqueEmailIfNullInList, output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printUniqueUserEmails_emptyUserList_true() {
        userDAO.setUserList(emptyUserList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUniqueUserEmails_blankedUsersInList_shouldPrintNonNullNames() {
        userDAO.setUserList(blankUserInList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals("db@maria.ru\r\nxray@gmail.com\r\n", output.toString());
    }

    @Test
    void printUniqueUserEmails_usersListIsNull_shouldPrintEmptyMessage() {
        userDAO.setUserList(null);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals("", output.toString().trim());
    }

    @Test
    void printUniqueUserEmails_nullInUsersList_shouldPrintUniqueMailsOfNotNullUser() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printUniqueUserEmails();
        Assertions.assertEquals("db@maria.ru xray@gmail.com", output.toString().trim().replace("\r\n", " "));
    }

    @Test
    void printAgeSum_correctWork_true() {
        userDAO.printTotalUserAge();
        Assertions.assertEquals("140", output.toString().trim());
    }

    @Test
    void printAgeSum_emptyList_true() {
        userDAO.setUserList(emptyUserList);
        userDAO.printTotalUserAge();
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printAgeSum_blankedUsersInList_shouldPrintAgeSumNonNullUsers() {
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
    void printAgeSum_nullInUsersList_shouldPrintSumAgeNotNullUser() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printTotalUserAge();
        Assertions.assertEquals("23", output.toString().trim());
    }

    @Test
    void printAgeSum_usersHaveFieldAgeZero_shouldPrintSumAgeNotNullUser() {
        userDAO.setUserList(usersHaveFieldAgeZero);
        userDAO.printTotalUserAge();
        Assertions.assertEquals("0", output.toString().trim());
    }

    @Test
    void printAgeSum_usersHaveFieldAgeNegativeNumber_shouldPrintSumAgeNotNullUser() {
        userDAO.setUserList(usersHaveFieldAgeNegativeNumber);
        userDAO.printTotalUserAge();
        Assertions.assertEquals("45", output.toString().trim());
    }

    @Test
    void printAgeSum_usersHaveFieldAgeNull_shouldPrintNotNullAgeSum() {
        userDAO.setUserList(usersHaveFieldAgeNull);
        userDAO.printTotalUserAge();
        Assertions.assertEquals("55", output.toString().trim());
    }

    @Test
    void printUniqueUsernameLine_correctWork_true() {
        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals(uniqueUsernameLine, output.toString().trim());
    }

    @Test
    void printUniqueUsernameLine_allUsersHaveBlankFieldNameInList_shouldPrintBlankMessage() {
        userDAO.setUserList(allUsersHaveBlankFieldNameInList);
        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals(blankMessage, output.toString().trim());
    }

    @Test
    void printUniqueUsernameLine_usersHaveBlankFieldNameInList_shouldPrintNonNullNames() {
        userDAO.setUserList(usersHaveBlankFieldNameInList);
        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals("Maria", output.toString().trim());
    }


    @Test
    void printUniqueUsernameLine_emptyList_true() {
        userDAO.setUserList(emptyUserList);
        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals(blankMessage, output.toString().trim());
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
    void printUniqueUsernameLine_nullInUsersList_shouldPrintNameOfNotNullUser() {
        userDAO.setUserList(nullInUsersList);
        userDAO.printUniqueUsernameLine();
        Assertions.assertEquals("Maria", output.toString().trim());
    }

}