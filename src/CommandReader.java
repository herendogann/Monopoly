import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CommandReader {
    public static String[][] command_array(String fileName) {
        int row = row_counter(fileName);
        String[][] command_table = new String[row][2];
        int j = 0;
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            while (scanner.hasNextLine()) {
                String[] command_array = scanner.nextLine().split(";");
                if (command_array.length != 1) {
                    command_table[j][0] = command_array[0];
                    command_table[j][1] = command_array[1];
                } else {
                    command_table[j][0] = command_array[0];
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return command_table;
    }
    public static int row_counter(String fileName) {
        int row = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) {
                row++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }
}
