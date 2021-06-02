import org.junit.jupiter.api.Test;

public class MyFirstTest {

    @Test
    public void firstTest() {
        System.out.println("Bye, corona!");

        String firstName = "Dmitro";
        String lastName = "Coronovich";
        String email = "best@corona.lv";
        int age = 34;

        System.out.println("First name: " + firstName + " " + "Last name: " + lastName);

        int commentCount = 36;
        int newComments = 22;

        String stringCommentCount = "16";
        String stringNewComments = "22";

        System.out.println(commentCount + newComments);
        System.out.println(stringCommentCount + stringNewComments);

        printSum(stringCommentCount, stringNewComments);
    }

    private void printSum(String a, String b) {
        int first = Integer.parseInt(a);
        int second = Integer.parseInt(b);

        System.out.println(first + second);
    }
}

// ------ FOR CYCLE --------
//        for (int i = 0; i < titles.size(); i++) {
//            if (!titles.get(i).getText().isEmpty()) { //!true = false, if not empty;
//                System.out.println(i + ":" + titles.get(i).getText());
//            }
//
//
//        }
//        ------ FOR EACH. title - required list, WebElement we - where we put elements; ---------
//        for (WebElement we : titles) {
//            //           if (!we.getText().isEmpty()) {
//            //               System.out.println(we.getText());
//            //          }else {
//            //              System.out.println("-----------");
//            //          }
//
//            ----------- TERNARY условие ? true : false (else) -------------------
//            System.out.println(we.getText().isEmpty() ? "---------" : we.getText());
//            }