public class Main {
    public static void main(String[] args) {
        String[][] board = Square.board_2D_array();
        String[][] commands = CommandReader.command_array(args[0]);
        String[] chance = JsonReader.chance_array();
        String[] communityChest = JsonReader.communityChest_array();

        Gameplay.gameplay(commands, board, chance, communityChest);

    }
}