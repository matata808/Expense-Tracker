public class Main {
    public static void main(String[] args) {

        switch (args[0]) {
            case "add":
                System.out.println("Enter amount to add");
                break;
            case "remove":
                System.out.println("Enter amount to remove");
                break;
            case "list":
                System.out.println("Enter amount to list");
                break;
            case "update":
                System.out.println("Enter amount to update");
                break;
            default:
                System.out.println("Invalid input");
        }

    }
}
