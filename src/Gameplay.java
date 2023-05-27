import java.util.ArrayList;
import java.util.List;

public class Gameplay extends PlayMethods {public int p1_position = 1;
    public int p2_position = 1;
    public static void gameplay(String[][] commands, String[][] board, String[] chance, String[] communityChest) {

        int p1_money = 15000;
        int p2_money = 15000;
        int banker_money = 100000;
        int chance_count = 0;
        int community_count = 0;
        int jail_count = 4;
        List<String> p1_properties = new ArrayList<>();
        List<String> p2_properties = new ArrayList<>();

        for (String[] command : commands) {
            if (command[0].equals("Player 1")) {
                if (jail_count == 4) {
                    int p1_position = Integer.parseInt(command[1]);
                    if (p1_position > 40) {
                        p1_position = p1_position % 40;
                        p1_money += 200;
                    }
                    if (board[p1_position][2] != null) {
                        if (p1_money >= Integer.parseInt(board[p1_position][2]) && !(p2_properties.contains(board[p1_position][1])) && !(p1_properties.contains(board[p1_position][1]))) {
                            p1_buy(board, command, p1_money, p2_money, banker_money, p1_position, p1_properties);
                        } else if (p2_properties.contains(board[p1_position][1])) {
                            p1_pay_rent(board, command, p1_money, p2_money, p1_position, p2_properties);
                        } else if (p1_properties.contains(board[p1_position][1])) {
                            Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 has " + board[p1_position][1] + "\n");
                        }
                    } else if (board[p1_position][1].equals("Chance")) {
                        p1_chance(board, chance, communityChest, command, community_count, chance_count, p1_money, p2_money, banker_money, p1_position, p1_properties, p2_properties);
                        chance_count += 1;
                    } else if (board[p1_position][1].equals("Community Chest")) {
                        p1_communityChest(board, communityChest, command, community_count, p1_money, p2_money, banker_money, p1_position, p1_properties, p2_properties);
                        community_count += 1;
                    } else if (board[p1_position][1].equals("GO")) {
                        p1_money += 200;
                        banker_money -= 200;
                    } else if (board[p1_position][1].equals("Tax")) {
                        p1_money -= 100;
                        banker_money += 100;
                    } else if (board[p1_position][1].equals("Jail")) {
                        jail_count = 1;
                        Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 went to jail\n");
                    } else if (board[p1_position][1].equals("Go to Jail")) {
                        p1_position = 11;
                        jail_count = 1;
                        Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 went to jail\n");
                    }
                } else if (jail_count < 4) {
                    Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 in jail (count = " + jail_count + "\n");
                    jail_count += 1;
                }
            }
            if (command[0].equals("Player 2")) {
                if (jail_count == 4) {
                    int p2_position = Integer.parseInt(command[1]);
                    if (p2_position > 40) {
                        p2_position = p2_position % 40;
                        p2_money += 200;
                    }
                    if (board[p2_position][2] != null) {
                        if (p2_money >= Integer.parseInt(board[p2_position][2]) && !(p1_properties.contains(board[p2_position][1])) && !(p2_properties.contains(board[p2_position][1]))) {
                            p2_buy(board, command, p1_money, p2_money, banker_money, p2_position, p2_properties);
                        } else if (p1_properties.contains(board[p2_position][1])) {
                            p2_pay_rent(board, command, p1_money, p2_money, p2_position, p1_properties);
                        } else if (p2_properties.contains(board[p2_position][1])) {
                            Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 has " + board[p2_position][1] + "\n");
                        }
                    } else if (board[p2_position][1].equals("Chance")) {
                        p2_chance(board, chance, communityChest, command, community_count, chance_count, p1_money, p2_money, banker_money, p2_position, p1_properties, p2_properties);
                        chance_count += 1;
                    } else if (board[p2_position][1].equals("Community Chest")) {
                        p2_communityChest(board, communityChest, command, community_count, p1_money, p2_money, banker_money, p2_position, p1_properties, p2_properties);
                        community_count += 1;
                    } else if (board[p2_position][1].equals("GO")) {
                        p2_money += 200;
                        banker_money -= 200;
                    } else if (board[p2_position][1].equals("Tax")) {
                        p2_money -= 100;
                        banker_money += 100;
                    } else if (board[p2_position][1].equals("Jail")) {
                        jail_count = 1;
                        Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 went to jail\n");
                    } else if (board[p2_position][1].equals("Go to Jail")) {
                        p2_position = 11;
                        jail_count = 1;
                        Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 went to jail\n");
                    }
                } else if (jail_count < 4) {
                    Writer.write("Player 2\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 in jail (count = " + jail_count + "\n");
                    jail_count += 1;
                }
            }
            if (command[0].equals("show()")) {

                String[] p1_properties_array = new String[p1_properties.size()];
                p1_properties.toArray(p1_properties_array);
                Writer.write("--------------------------------------------------------------------------------------\n" + "Player 1\t" + p1_money + "\thave: ");
                if (p1_properties_array.length == 1) {
                    Writer.write(p1_properties_array[0]);
                } else {
                    for (int i = 0; i < p1_properties_array.length - 1; i++) {
                        Writer.write(p1_properties_array[i] + ",");
                    }
                    Writer.write(p1_properties_array[p1_properties_array.length - 1]);
                }

                String[] p2_properties_array = new String[p2_properties.size()];
                p2_properties.toArray(p2_properties_array);
                Writer.write("\n" + "Player 2\t" + p2_money + "\thave: ");
                if (p2_properties_array.length == 1) {
                    Writer.write(p2_properties_array[0]);
                } else {
                    for (int i = 0; i < p2_properties_array.length - 1; i++) {
                        Writer.write(p2_properties_array[i] + ",");
                    }
                    Writer.write(p2_properties_array[p2_properties_array.length - 1]);
                }

                if (p1_money > p2_money) {
                    Writer.write("\nBanker\t" + banker_money + "\nWinner\tPlayer 1");
                } else {
                    Writer.write("\nBanker\t" + banker_money + "\nWinner\tPlayer 2");
                }
                Writer.write("\n--------------------------------------------------------------------------------------\n");
            }
        }
    }
}