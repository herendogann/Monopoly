public class Square {
    public static String[][] board_2D_array() {
        String[][] board = new String[41][4];
        String[][] lands = JsonReader.land_2D_array();
        String[][] railroads = JsonReader.railroad_2D_array();
        String[][] companies = JsonReader.company_2D_array();

        board[1][0] = "1"; board[1][1] = "GO";
        board[3][0] = "3"; board[3][1] = "Community Chest";
        board[5][0] = "5"; board[5][1] = "Tax";
        board[8][0] = "8"; board[8][1] = "Chance";
        board[11][0] = "11"; board[11][1] = "Jail";
        board[18][0] = "18"; board[18][1] = "Community Chest";
        board[21][0] = "21"; board[21][1] = "Free Parking";
        board[23][0] = "23"; board[23][1] = "Chance";
        board[31][0] = "31"; board[31][1] = "Go to Jail";
        board[34][0] = "34"; board[34][1] = "Community Chest";
        board[37][0] = "37"; board[37][1] = "Chance";
        board[39][0] = "39"; board[39][1] = "Tax";

        for (String[] land : lands){
            int id = Integer.parseInt(land[0]);
            board[id][0] = land[0];
            board[id][1] = land[1];
            board[id][2] = land[2];
            board[id][3] = "Land";
        }
        for (String[] railroad : railroads){
            int id = Integer.parseInt(railroad[0]);
            board[id][0] = railroad[0];
            board[id][1] = railroad[1];
            board[id][2] = railroad[2];
            board[id][3] = "Railroad";
        }
        for (String[] company : companies){
            int id = Integer.parseInt(company[0]);
            board[id][0] = company[0];
            board[id][1] = company[1];
            board[id][2] = company[2];
            board[id][3] = "Company";
        }
        return board;
    }
}