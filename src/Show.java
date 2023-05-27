import java.util.List;

public class Show extends Writer{
    public static void show(int p1_money, int p2_money, int banker_money, List<String> p1_properties, List<String> p2_properties) {

        String[] p1_properties_array = new String[p1_properties.size()];
        p1_properties.toArray(p1_properties_array);                         // CONVERTING LIST TO ARRAY FOR REACHING ITS INDEXES EASILY WHILE PRINTING

        write("----------------------------------------------------------------------------------------------------------\n" +
                "Player 1\t" + p1_money + "\thave: ");
        if (p1_properties_array.length == 1) {
            write(p1_properties_array[0]);
        } else {
            for (int i = 0; i < p1_properties_array.length - 1; i++) {
                write(p1_properties_array[i] + ", ");
            }
            write(p1_properties_array[p1_properties_array.length - 1]);
        }

        String[] p2_properties_array = new String[p2_properties.size()];
        p2_properties.toArray(p2_properties_array);                         // CONVERTING LIST TO ARRAY FOR REACHING ITS INDEXES EASILY WHILE PRINTING

        write("\n" + "Player 2\t" + p2_money + "\thave: ");
        if (p2_properties_array.length != 0) {
            if (p2_properties_array.length == 1) {
                write(p2_properties_array[0]);
            } else {
                for (int i = 0; i < p2_properties_array.length - 1; i++) {
                    write(p2_properties_array[i] + ", ");
                }
                write(p2_properties_array[p2_properties_array.length - 1]);
            }
        }

        if (p1_money > p2_money) {                                                  // DECLARING WHO IS THE WINNER
            write("\nBanker\t" + banker_money + "\nWinner\tPlayer 1");
        } else {
            write("\nBanker\t" + banker_money + "\nWinner\tPlayer 2");
        }
        write("\n----------------------------------------------------------------------------------------------------------\n");
    }
}