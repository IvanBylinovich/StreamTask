package by.solbegsoft.streamtask.util;

import by.solbegsoft.streamtask.dao.InMemoryUserDAO;
import by.solbegsoft.streamtask.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

class UsersPrinterUtilTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private InMemoryUserDAO userDAO = Mockito.mock(InMemoryUserDAO.class);
    private UsersPrinterUtil usersPrinterUtil;

    private List<User> testUsersList = Arrays.asList(
            new User("Ivan", 14, Arrays.asList("idontliketorepeattwice@mail.ru", "idontliketorepeattwice@mail.ru", "msin@fds.com")),
            new User("John", 18, Arrays.asList("lemon@john.com", "j@weak.io", "j@weak.io", "j@weak.io")),
            new User("Maria", 23, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com")),
            new User("Kate", 32, Arrays.asList("hh@tut.ru", "passwordisthesameaslogin@gmail.com")),
            new User("Phil", 17, Arrays.asList("reallycomplexmail222@hh.ru")),
            new User("Kate", 19, Arrays.asList("reallycomplexmail333@hh.ru")),
            new User("John", 22, Arrays.asList("reallycomplexmail444@hh.ru", "idontliketorepeattwice@mail.ru"))
    );

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

    public UsersPrinterUtilTest() {
        Mockito.when(userDAO.getUsers()).thenReturn(testUsersList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);
    }

    String allName = "Ivan\r\nJohn\r\nMaria\r\nKate\r\nPhil\r\nKate\r\nJohn\r\n";
    String errorMessage = "error message\r\n";
    String adultUsers = "John\r\nMaria\r\nKate\r\nKate\r\nJohn\r\n";
    String uniqueNames = "Ivan\r\nJohn\r\nMaria\r\nKate\r\nPhil\r\n";
    String uniqueEmail = "idontliketorepeattwice@mail.ru\r\nmsin@fds.com\r\nlemon@john.com\r\nj@weak.io\r\n" +
            "db@maria.ru\r\nxray@gmail.com\r\nhh@tut.ru\r\npasswordisthesameaslogin@gmail.com\r\n" +
            "reallycomplexmail222@hh.ru\r\nreallycomplexmail333@hh.ru\r\nreallycomplexmail444@hh.ru\r\n";
    String uniqueUsernameLine = "Ivan, John, Maria, Kate, Phil\r\n";

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    void printAllName_correctWork_true() {
        usersPrinterUtil.printAllName();
        Assertions.assertEquals(allName, output.toString());
    }

    @Test
    void printAllName_blankedUsersInList_shouldPrintNonNullNames() {
        Mockito.when(userDAO.getUsers()).thenReturn(blankUserInList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);
        usersPrinterUtil.printAllName();
        Assertions.assertEquals("Maria\r\n", output.toString());
    }

    @Test
    void printAllName_usersListIsNull_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(null);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printAllName();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printAllName_nullInUsersList_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(nullInUsersList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printAllName();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printAllAdultUserName_correctWork_true() {
        usersPrinterUtil.printAllAdultUsername();
        Assertions.assertEquals(adultUsers, output.toString());
    }

    @Test
    void printAllAdultUserName_blankedUsersInList_shouldPrintNonNullNames() {
        Mockito.when(userDAO.getUsers()).thenReturn(blankUserInList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printAllAdultUsername();
        Assertions.assertEquals("Maria\r\n", output.toString());
    }

    @Test
    void printAllAdultUserName_usersListIsNull_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(null);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printAllAdultUsername();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printAllAdultUserName_nullInUsersList_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(nullInUsersList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printAllAdultUsername();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printUniqueNames_correctWork_true() {
        usersPrinterUtil.printUniqueNames();
        Assertions.assertEquals(uniqueNames, output.toString());
    }

    @Test
    void printUniqueNames_blankedUsersInList_shouldPrintNonNullNames() {
        Mockito.when(userDAO.getUsers()).thenReturn(blankUserInList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueNames();
        Assertions.assertEquals("Maria\r\n", output.toString());
    }

    @Test
    void printUniqueNames_usersListIsNull_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(null);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueNames();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printUniqueNames_nullInUsersList_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(nullInUsersList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueNames();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printUniqueEmails_correctWork_true() {
        usersPrinterUtil.printUniqueEmails();
        Assertions.assertEquals(uniqueEmail, output.toString());
    }

    @Test
    void printUniqueEmails_blankedUsersInList_shouldPrintNonNullNames() {
        Mockito.when(userDAO.getUsers()).thenReturn(blankUserInList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueEmails();
        Assertions.assertEquals("db@maria.ru\r\nxray@gmail.com\r\n", output.toString());
    }

    @Test
    void printUniqueEmails_usersListIsNull_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(null);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueEmails();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printUniqueEmails_nullInUsersList_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(nullInUsersList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueEmails();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printAgeSum_correctWork_true() {
        usersPrinterUtil.printAgeSum();
        Assertions.assertEquals("145\r\n", output.toString());
    }

    @Test
    void printAgeSum_blankedUsersInList_shouldPrintNonNullNames() {
        Mockito.when(userDAO.getUsers()).thenReturn(blankUserInList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printAgeSum();
        Assertions.assertEquals("23\r\n", output.toString());
    }

    @Test
    void printAgeSum_usersListIsNull_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(null);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printAgeSum();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printAgeSum_nullInUsersList_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(nullInUsersList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printAgeSum();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printUniqueUsernameLine_correctWork_true() {
        usersPrinterUtil.printUniqueUsernameLine();
        Assertions.assertEquals(uniqueUsernameLine, output.toString());
    }

    @Test
    void printUniqueUsernameLine_blankedUsersInList_shouldPrintNonNullNames() {
        Mockito.when(userDAO.getUsers()).thenReturn(blankUserInList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueUsernameLine();
        Assertions.assertEquals("Maria\r\n", output.toString());
    }

    @Test
    void printUniqueUsernameLine_usersListIsNull_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(null);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueUsernameLine();
        Assertions.assertEquals(errorMessage, output.toString());
    }

    @Test
    void printUniqueUsernameLine_nullInUsersList_shouldPrintErrorMessage() {
        Mockito.when(userDAO.getUsers()).thenReturn(nullInUsersList);
        this.usersPrinterUtil = new UsersPrinterUtil(userDAO);

        usersPrinterUtil.printUniqueUsernameLine();
        Assertions.assertEquals(errorMessage, output.toString());
    }
}