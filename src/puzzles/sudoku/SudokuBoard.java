package puzzles.sudoku;

import puzzles.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SudokuBoard extends Board {
    private int[][] currentBoard;
    private int dimension;
    private Pattern p = Pattern.compile("R([\\d]+)C([\\d]+)");

    public SudokuBoard(int dimension) {
        this.dimension = dimension;
        this.currentBoard = new int[dimension][dimension];
    }

    public SudokuBoard(String condensed) {//TODO generalize to NxN functionality
        if (condensed.length() != 81) {
            throw new RuntimeException("invalid 9x9 condensed form");
        } else {
            this.dimension = 9;
            this.currentBoard = new int[9][9];
            int idx = 0;
            for (int row = 1; row <= 9; row++) {
                for (int column = 1; column <= 9; column++) {
                    currentBoard[row - 1][column - 1] = Integer.parseInt(String.valueOf(condensed.charAt(idx++)));
                }
            }
        }
    }

    public SudokuBoard(SudokuBoard sb) {
        this.dimension = sb.dimension;
        this.currentBoard = new int[dimension][dimension];
        for (int row = 1; row <= dimension; row++) {
            for (int column = 1; column <= dimension; column++) {
                this.currentBoard[row - 1][column - 1] = sb.currentBoard[row - 1][column - 1];
            }
        }
    }

    @Override
    public void updateLocation(String location, String value) {
        Matcher m = p.matcher(location);
        if (!m.find()) {
            throw new RuntimeException(String.format("%s is not a valid location in R#C# format", location));
        } else {
            currentBoard[Integer.parseInt(m.group(1)) - 1][Integer.parseInt(m.group(2)) - 1] = Integer.parseInt(value);
        }
    }

    public int getDimension() {
        return dimension;
    }

    public List<String> allFilled() {
        ArrayList<String> out = new ArrayList<>();
        for (int row = 1; row <= dimension; row++) {
            for (int column = 1; column <= dimension; column++) {
                int val = currentBoard[row - 1][column - 1];
                if (val != 0) {
                    out.add(String.format("R%dC%dV%d", row, column, val));
                }
            }
        }
        return out;
    }

    public String toCondensed() {
        String out="";
        for (int row = 1; row <= dimension; row++) {
            for (int column = 1; column <= dimension; column++) {
                out+=currentBoard[row-1][column-1];
            }
        }
        return out;
    }
}
