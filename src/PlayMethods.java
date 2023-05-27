import java.util.List;

public class PlayMethods {
    public static void p1_buy(String[][] board, String[] command, int p1_money, int p2_money, int banker_money, int p1_position, List<String> p1_properties) {
        p1_money -= Integer.parseInt(board[p1_position][2]);
        banker_money += Integer.parseInt(board[p1_position][2]);
        p1_properties.add(board[p1_position][1]);
        Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 bought " + board[p1_position][1] + "\n");
    }

    public static void p1_pay_rent(String[][] board, String[] command, int p1_money, int p2_money, int p1_position, List<String> p2_properties) {
        if (board[p1_position][3].equals("Land")) {
            if (Integer.parseInt(board[p1_position][2]) <= 2000) {
                int rent = Integer.parseInt(board[p1_position][2]) * 2 / 5;
                if (p1_money >= rent) {
                    p1_money -= rent;
                    p2_money += rent;
                }
            } else if (Integer.parseInt(board[p1_position][2]) <= 3000) {
                int rent = Integer.parseInt(board[p1_position][2]) * 3 / 10;
                if (p1_money >= rent) {
                    p1_money -= rent;
                    p2_money += rent;
                }
            } else {
                int rent = Integer.parseInt(board[p1_position][2]) * 35 / 100;
                if (p1_money >= rent) {
                    p1_money -= rent;
                    p2_money += rent;
                }
            }
        } else if (board[p1_position][3].equals("Company")) {
            int rent = Integer.parseInt(command[1]) * 4;
            if (p1_money >= rent) {
                p1_money -= rent;
                p2_money += rent;
            }
        } else if (board[p1_position][3].equals("Railroad")) {
            int railroad_count = 0;
            for (String[] square : board)
                if (square[3].equals("Railroad")) {
                    if (p2_properties.contains(square[1])) railroad_count++;
                }
            int rent = railroad_count * 25;
            if (p1_money >= rent) {
                p1_money -= rent;
                p2_money += rent;
            }
        }
        Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 paid rent for " + board[p1_position][1] + "\n");
    }

    public static void p1_communityChest(String[][] board, String[] communityChest, String[] command, int community_count, int p1_money, int p2_money, int banker_money, int p1_position, List<String> p1_properties, List<String> p2_properties) {
        if (community_count == 0) { //Advance to Go (Collect $200)
            p1_money += 200;
            banker_money -= 200;
            p1_position = 1;
        }
        if (community_count == 1) { //Bank error in your favor - collect $75
            p1_money += 75;
            banker_money -= 75;
        }
        if (community_count == 2) { //Doctor's fees - Pay $50
            p1_money -= 50;
            banker_money += 50;
        }
        if (community_count == 3) {  //It is your birthday Collect $10 from each player
            p1_money += 10;
            p2_money -= 10;
        }
        if (community_count == 4) { //Grand Opera Night - collect $50 from every player for opening night seats
            p1_money += 50;
            p2_money -= 50;
        }
        if (community_count == 5) { //Income Tax refund - collect $20
            p1_money += 20;
            banker_money -= 20;
        }
        if (community_count == 6) { //Life Insurance Matures - collect $100"
            p1_money += 100;
            banker_money -= 100;
        }
        if (community_count == 7) { //Pay Hospital Fees of $100
            p1_money -= 100;
            banker_money += 100;
        }
        if (community_count == 8) { //Pay School Fees of $50
            p1_money -= 50;
            banker_money += 50;
        }
        if (community_count == 9) { //You inherit $100
            p1_money += 100;
            banker_money -= 100;
        }
        if (community_count == 10) { //From sale of stock you get $50
            p1_money += 50;
            banker_money -= 50;
        }
        Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 1 draw '" + communityChest[community_count] + "'\n");
    }

    public static void p1_chance(String[][] board, String[] chance, String[] communityChest, String[] command, int community_count, int chance_count, int p1_money, int p2_money, int banker_money, int p1_position, List<String> p1_properties, List<String> p2_properties) {
        if (chance_count == 0) { // Advance to Go (Collect $200)
            p1_money += 200;
            banker_money -= 200;
            p1_position = 1;
            Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 1 draw '" + chance[chance_count] + "'\n");
        }
        if (chance_count == 1) { // Advance to Leicester Square
            p1_position = 27;
            if (!p2_properties.contains(board[p1_position][1]) && !p1_properties.contains(board[p1_position][1])) {
                if (p1_money >= Integer.parseInt(board[p1_position][2])) {
                    p1_chance_buy(board, chance, command, chance_count, p1_money, p2_money, banker_money, p1_position, p1_properties);
                }
            } else if (p2_properties.contains(board[p1_position][1])){
                p1_chance_pay_rent(board, command, chance, chance_count, p1_money, p2_money, p1_position, p2_properties);
            } else if (p1_properties.contains(board[p1_position][1])) {
                Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 1 draw '" + chance[chance_count] + "' - Player 1 has" + board[p1_position][1] + "\n");
            }
        }

        if (chance_count == 2) { // Go back 3 spaces
            p1_position -= 3;
            if (board[p1_position][2] != null) {
                if (p1_money >= Integer.parseInt(board[p1_position][2]) && !(p2_properties.contains(board[p1_position][1])) && !(p1_properties.contains(board[p1_position][1]))) {
                    p1_chance_buy(board, chance, command, chance_count, p1_money, p2_money, banker_money, p1_position, p1_properties);

                } else if (p2_properties.contains(board[p1_position][1])) {
                    p1_chance_pay_rent(board, command, chance, chance_count, p1_money, p2_money, p1_position, p2_properties);
                } else if (p1_properties.contains(board[p1_position][1])) {
                    Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 1 draw '" + chance[chance_count] + "' - Player 1 has" + board[p1_position][1] + "\n");
                }
            } else if (board[p1_position][1].equals("Community Chest")) {
                p1_communityChest(board, communityChest, command, community_count, p1_money, p2_money, banker_money, p1_position, p1_properties, p2_properties);
                community_count += 1;
            } else if (board[p1_position][1].equals("Tax")) {
                p1_money -= 100;
                banker_money += 100;
            }
        }
        if (chance_count == 3) { // Pay poor tax of $15
            p1_money -= 15;
            banker_money += 15;
            Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 draw '" + chance[chance_count] + "'\n");
        }
        if (chance_count == 4) { // Your building loan matures - collect $150
            p1_money += 150;
            banker_money -= 150;
            Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 draw '" + chance[chance_count] + "'\n");

        }
        if (chance_count == 5) { //You have won a crossword competition - collect $100
            p1_money += 100;
            banker_money -= 100;
            Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 1 draw '" + chance[chance_count] + "'\n");
        }
    }

    public static void p1_chance_buy(String[][] board, String[] chance, String[] command, int chance_count, int p1_money, int p2_money, int banker_money, int p1_position, List<String> p1_properties) {
        p1_money -= Integer.parseInt(board[p1_position][2]);
        banker_money += Integer.parseInt(board[p1_position][2]);
        p1_properties.add(board[p1_position][1]);
        Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 1 draw " + chance[chance_count] + " - Player 1 bought " + board[p1_position][1] + "\n");
    }

    public static void p1_chance_pay_rent(String[][] board, String[] command, String[] chance, int chance_count, int p1_money, int p2_money, int p1_position, List<String> p2_properties) {
        if (board[p1_position][3].equals("Land")) {
            if (Integer.parseInt(board[p1_position][2]) <= 2000) {
                int rent = Integer.parseInt(board[p1_position][2]) * 2 / 5;
                if (p1_money >= rent) {
                    p1_money -= rent;
                    p2_money += rent;
                }
            } else if (Integer.parseInt(board[p1_position][2]) <= 3000) {
                int rent = Integer.parseInt(board[p1_position][2]) * 3 / 10;
                if (p1_money >= rent) {
                    p1_money -= rent;
                    p2_money += rent;
                }
            } else {
                int rent = Integer.parseInt(board[p1_position][2]) * 35 / 100;
                if (p1_money >= rent) {
                    p1_money -= rent;
                    p2_money += rent;
                }
            }
        } else if (board[p1_position][3].equals("Company")) {
            int rent = Integer.parseInt(command[1]) * 4;
            if (p1_money >= rent) {
                p1_money -= rent;
                p2_money += rent;
            }
        } else if (board[p1_position][3].equals("Railroad")) {
            int railroad_count = 0;
            for (String[] square : board)
                if (square[3].equals("Railroad")) {
                    if (p2_properties.contains(square[1])) railroad_count++;
                }
            int rent = railroad_count * 25;
            if (p1_money >= rent) {
                p1_money -= rent;
                p2_money += rent;
            }
        }
        Writer.write("Player 1\t" + command[1] + "\t" + p1_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 1 draw " + chance[chance_count] + " - Player 1 paid rent for  " + board[p1_position][1] + "\n");
    }




    public static void p2_buy(String[][] board, String[] command, int p1_money, int p2_money, int banker_money, int p2_position, List<String> p2_properties) {
        p2_money -= Integer.parseInt(board[p2_position][2]);
        banker_money += Integer.parseInt(board[p2_position][2]);
        p2_properties.add(board[p2_position][1]);
        Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 bought " + board[p2_position][1] + "\n");
    }

    public static void p2_pay_rent(String[][] board, String[] command, int p1_money, int p2_money, int p2_position, List<String> p1_properties) {
        if (board[p2_position][3].equals("Land")) {
            if (Integer.parseInt(board[p2_position][2]) <= 2000) {
                int rent = Integer.parseInt(board[p2_position][2]) * 2 / 5;
                if (p2_money >= rent) {
                    p2_money -= rent;
                    p1_money += rent;
                }
            } else if (Integer.parseInt(board[p2_position][2]) <= 3000) {
                int rent = Integer.parseInt(board[p2_position][2]) * 3 / 10;
                if (p2_money >= rent) {
                    p2_money -= rent;
                    p1_money += rent;
                }
            } else {
                int rent = Integer.parseInt(board[p2_position][2]) * 35 / 100;
                if (p2_money >= rent) {
                    p2_money -= rent;
                    p1_money += rent;
                }
            }
        } else if (board[p2_position][3].equals("Company")) {
            int rent = Integer.parseInt(command[1]) * 4;
            if (p2_money >= rent) {
                p2_money -= rent;
                p1_money += rent;
            }
        } else if (board[p2_position][3].equals("Railroad")) {
            int railroad_count = 0;
            for (String[] square : board)
                if (square[3].equals("Railroad")) {
                    if (p1_properties.contains(square[1])) railroad_count++;
                }
            int rent = railroad_count * 25;
            if (p2_money >= rent) {
                p2_money -= rent;
                p1_money += rent;
            }
        }
        Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 paid rent for " + board[p2_position][1] + "\n");
    }

    public static void p2_communityChest(String[][] board, String[] communityChest, String[] command, int community_count, int p1_money, int p2_money, int banker_money, int p2_position, List<String> p1_properties, List<String> p2_properties) {
        if (community_count == 0) { //Advance to Go (Collect $200)
            p2_money += 200;
            banker_money -= 200;
            p2_position = 1;
        }
        if (community_count == 1) { //Bank error in your favor - collect $75
            p2_money += 75;
            banker_money -= 75;
        }
        if (community_count == 2) { //Doctor's fees - Pay $50
            p2_money -= 50;
            banker_money += 50;
        }
        if (community_count == 3) {  //It is your birthday Collect $10 from each player
            p2_money += 10;
            p1_money -= 10;
        }
        if (community_count == 4) { //Grand Opera Night - collect $50 from every player for opening night seats
            p2_money += 50;
            p1_money -= 50;
        }
        if (community_count == 5) { //Income Tax refund - collect $20
            p2_money += 20;
            banker_money -= 20;
        }
        if (community_count == 6) { //Life Insurance Matures - collect $100"
            p2_money += 100;
            banker_money -= 100;
        }
        if (community_count == 7) { //Pay Hospital Fees of $100
            p2_money -= 100;
            banker_money += 100;
        }
        if (community_count == 8) { //Pay School Fees of $50
            p2_money -= 50;
            banker_money += 50;
        }
        if (community_count == 9) { //You inherit $100
            p2_money += 100;
            banker_money -= 100;
        }
        if (community_count == 10) { //From sale of stock you get $50
            p2_money += 50;
            banker_money -= 50;
        }
        Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 2 draw '" + communityChest[community_count] + "'\n");
    }

    public static void p2_chance(String[][] board, String[] chance, String[] communityChest, String[] command, int community_count, int chance_count, int p1_money, int p2_money, int banker_money, int p2_position, List<String> p1_properties, List<String> p2_properties) {
        if (chance_count == 0) { // Advance to Go (Collect $200)
            p2_money += 200;
            banker_money -= 200;
            p2_position = 1;
            Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 2 draw '" + chance[chance_count] + "'\n");
        }
        if (chance_count == 1) { // Advance to Leicester Square
            p2_position = 27;
            if (!p1_properties.contains(board[p2_position][1]) && !p2_properties.contains(board[p2_position][1])) {
                if (p2_money >= Integer.parseInt(board[p2_position][2])) {
                    p2_chance_buy(board, chance, command, chance_count, p1_money, p2_money, banker_money, p2_position, p2_properties);
                }
            } else if (p1_properties.contains(board[p2_position][1])){
                p2_chance_pay_rent(board, command, chance, chance_count, p1_money, p2_money, p2_position, p1_properties);
            } else if (p2_properties.contains(board[p2_position][1])){
                Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 2 draw '" + chance[chance_count] + "' - Player 2 has" + board[p2_position][1] + "\n");
            }
        }

        if (chance_count == 2) { // Go back 3 spaces
            p2_position -= 3;
            if (board[p2_position][2] != null) {
                if (p2_money >= Integer.parseInt(board[p2_position][2]) && !(p1_properties.contains(board[p2_position][1])) && !(p2_properties.contains(board[p2_position][1]))) {
                    p2_chance_buy(board, chance, command, chance_count, p1_money, p2_money, banker_money, p2_position, p2_properties);

                } else if (p1_properties.contains(board[p2_position][1])) {
                    p2_chance_pay_rent(board, command, chance, chance_count, p1_money, p2_money, p2_position, p1_properties);
                } else if (p2_properties.contains(board[p2_position][1])) {
                    Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 2 draw '" + chance[chance_count] + "' - Player 2 has" + board[p2_position][1] + "\n");
                }
            } else if (board[p2_position][1].equals("Community Chest")) {
                p2_communityChest(board, communityChest, command, community_count, p1_money, p2_money, banker_money, p2_position, p1_properties, p2_properties);
                community_count += 1;
            } else if (board[p2_position][1].equals("Tax")) {
                p2_money -= 100;
                banker_money += 100;
            }
        }
        if (chance_count == 3) { // Pay poor tax of $15
            p2_money -= 15;
            banker_money += 15;
            Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 draw '" + chance[chance_count] + "'\n");
        }
        if (chance_count == 4) { // Your building loan matures - collect $150
            p2_money += 150;
            banker_money -= 150;
            Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 draw '" + chance[chance_count] + "'\n");

        }
        if (chance_count == 5) { //You have won a crossword competition - collect $100
            p2_money += 100;
            banker_money -= 100;
            Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\t" + "Player 2 draw '" + chance[chance_count] + "'\n");
        }
    }

    public static void p2_chance_buy(String[][] board, String[] chance, String[] command, int chance_count, int p1_money, int p2_money, int banker_money, int p2_position, List<String> p2_properties) {
        p2_money -= Integer.parseInt(board[p2_position][2]);
        banker_money += Integer.parseInt(board[p2_position][2]);
        p2_properties.add(board[p2_position][1]);
        Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 2 draw " + chance[chance_count] + " - Player 2 bought " + board[p2_position][1] + "\n");
    }

    public static void p2_chance_pay_rent(String[][] board, String[] command, String[] chance, int chance_count, int p1_money, int p2_money, int p2_position, List<String> p1_properties) {
        if (board[p2_position][3].equals("Land")) {
            if (Integer.parseInt(board[p2_position][2]) <= 2000) {
                int rent = Integer.parseInt(board[p2_position][2]) * 2 / 5;
                if (p2_money >= rent) {
                    p2_money -= rent;
                    p1_money += rent;
                }
            } else if (Integer.parseInt(board[p2_position][2]) <= 3000) {
                int rent = Integer.parseInt(board[p2_position][2]) * 3 / 10;
                if (p2_money >= rent) {
                    p2_money -= rent;
                    p1_money += rent;
                }
            } else {
                int rent = Integer.parseInt(board[p2_position][2]) * 35 / 100;
                if (p2_money >= rent) {
                    p2_money -= rent;
                    p1_money += rent;
                }
            }
        } else if (board[p2_position][3].equals("Company")) {
            int rent = Integer.parseInt(command[1]) * 4;
            if (p2_money >= rent) {
                p2_money -= rent;
                p1_money += rent;
            }
        } else if (board[p2_position][3].equals("Railroad")) {
            int railroad_count = 0;
            for (String[] square : board)
                if (square[3].equals("Railroad")) {
                    if (p1_properties.contains(square[1])) railroad_count++;
                }
            int rent = railroad_count * 25;
            if (p2_money >= rent) {
                p2_money -= rent;
                p1_money += rent;
            }
        }
        Writer.write("Player 2\t" + command[1] + "\t" + p2_position + "\t" + p1_money + "\t" + p2_money + "\tPlayer 2 draw " + chance[chance_count] + " - Player 2 paid rent for  " + board[p2_position][1] + "\n");
    }
}