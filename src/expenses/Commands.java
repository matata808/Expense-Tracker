package expenses;

public class Commands {

    public enum Flag {
        DESCRIPTION("--description"),
        AMOUNT("--amount"),
        ID("--id"),
        MONTH("--month");

        private final String value;

        Flag(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        }

    //TODO: find a way to implement the commands

    /*
    these are the commands that the user can give in:

    expense-tracker add --description <description> --amount <amount>

    expense-tracker update --id <id> [--description <description>] [--amount <amount>]

    expense-tracker delete --id <id>

    expense-tracker list

    expense-tracker summary

    expense-tracker summary --month <month>
     */
}
