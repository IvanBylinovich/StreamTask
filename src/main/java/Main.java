import by.solbegsoft.streamtask.service.UserService;

public class Main {
    public static void main(String[] args) {
        /*
                new User("Ivan", 14, Arrays.asList("idontliketorepeattwice@mail.ru", "idontliketorepeattwice@mail.ru", "msin@fds.com")),
                new User("John", 18, Arrays.asList("lemon@john.com", "j@weak.io", "j@weak.io", "j@weak.io")),
                new User("Maria", 23, Arrays.asList("db@maria.ru", "xray@gmail.com", "xray@gmail.com", "xray@gmail.com")),
                new User("Kate", 32, Arrays.asList("hh@tut.ru", "passwordisthesameaslogin@gmail.com")),
                new User("Phil", 12, Arrays.asList("reallycomplexmail222@hh.ru")),
                new User("Kate", 19, Arrays.asList("reallycomplexmail333@hh.ru")),
                new User("John", 22, Arrays.asList("reallycomplexmail444@hh.ru", "idontliketorepeattwice@mail.ru"))
         */

        UserService userService = new UserService();

        System.out.println("***** All names ***** ");
        userService.printAllName();
        System.out.println("***** All Adult User *****");
        userService.printAllAdultUserName();
        System.out.println("***** unique names *****");
        userService.printUniqueNames();
        System.out.println("***** Unique Username Line *****");
        userService.printUniqueUsernameLine();
        System.out.println("***** unique emails *****");
        userService.printUniqueEmails();
        System.out.println("***** Age sum *****");
        userService.printAgeSum();

    }
}
