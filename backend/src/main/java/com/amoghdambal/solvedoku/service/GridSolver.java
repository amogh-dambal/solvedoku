package com.amoghdambal.solvedoku.service;

import com.amoghdambal.solvedoku.data.Grid;

import java.util.HashSet;
import java.util.Set;

public class GridSolver {
    private static Integer[][] buildArray(Grid g) {
        String[] gridString = g.getGridRepresentation().split(",");
        assert gridString.length == 81;

        Integer[][] squares = new Integer[9][9];

        for (int i = 0; i < gridString.length; i++) {
            assert gridString[i].length() == 1;
            char c = gridString[i].charAt(0);

            assert Character.isDigit(c);
            int digit = Character.digit(c, 10);

            int x = i / 9;
            int y = i % 9;
            squares[x][y] = digit;
        }
        return squares;
    }

    private static Grid buildGrid(Integer[][] squares) {
        Grid solved = new Grid();
        StringBuilder sb = new StringBuilder();
        for (Integer[] row : squares) {
            for (Integer digit : row) {
                sb.append(digit).append(",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        solved.setGridRepresentation(sb.toString());
        return solved;
    }

    private static boolean isLegalSudoku(Integer[][] squares) {
        // check that each row, column, and subgrid is legal
        return isLegalRows(squares) && isLegalColumns(squares) && isLegalSubgrids(squares);
    }

    private static boolean isLegalRows(Integer[][] squares) {
        Set<Integer> duplicates;
        for (Integer[] row : squares) {
            int sum = 0;
            duplicates = new HashSet<>();
            for (Integer digit : row) {
                if (digit == 0) {
                    return false;
                }
                else if (duplicates.contains(digit)) {
                    return false;
                }
                else {
                    sum += digit;
                    duplicates.add(digit);
                }
            }
            if (sum != 45 || duplicates.size() != 9) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLegalColumns(Integer[][] squares) {
        Set<Integer> duplicates;
        for (int j = 0; j < 9; j++) {
            int sum = 0;
            duplicates = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                Integer digit = squares[i][j];
                if (digit == 0) {
                    return false;
                }
                else if (duplicates.contains(digit)) {
                    return false;
                }
                else {
                    sum += digit;
                    duplicates.add(digit);
                }
            }

            if (sum != 45 || duplicates.size() == 9) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLegalSubgrids(Integer[][] squares) {
        boolean valid = true;
        for (int i = 0; i < 9 && valid; i += 3) {
            for (int j = 0; j < 9 && valid; j += 3) {
                valid = isLegalSubgrid(squares, i, j);
            }
        }
        return valid;
    }

    private static boolean isLegalSubgrid(Integer[][] squares, int i, int j) {
        int sum = 0;
        Set<Integer> duplicates = new HashSet<>();
        for (int x = i; x < i + 3; x++) {
            for (int y = j; y < j + 3; j++) {
                Integer digit = squares[i][j];
                if (digit == 0) {
                    return false;
                }
                else if (duplicates.contains(digit)) {
                    return false;
                }
                else {
                    sum += digit;
                    duplicates.add(digit);
                }
            }
        }
        return sum == 45 && duplicates.size() == 9;
    }

    /**
     * method that checkes whether
     * placing DIGIT at SQUARES[I][J]
     * will violate the rules of sudoku
     * @param digit the digit to be placed
     * @param i the x position
     * @param j the y position
     * @param squares the board itself
     * @return true if you can place digit, false otherwise
     */
    private static boolean canPlace(int digit, int i, int j, Integer[][] squares) {
        // validity of row
        for (int x = 0; x < 9; x++) {
            if (squares[x][j] == digit) {
                return false;
            }
        }
        // validity of column
        for (int y = 0; y < 9; y++) {
            if (squares[i][y] == digit) {
                return false;
            }
        }
        // validity of 3 x 3 square
        int squareStartRow = i / 3 * 3;
        int squareStartCol = j / 3 * 3;
        for (int p = squareStartRow; p < squareStartRow + 3; p++) {
            for (int q = squareStartCol; q < squareStartCol + 3; q++) {
                boolean onSameSquare = p == i && q == j;
                if (!onSameSquare && squares[p][q] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class GridSolveObject {
        public Integer[][] squares;

        public GridSolveObject(Integer[][] squares) {
            this.squares = new Integer[squares.length][squares.length];
            for (int i = 0; i < squares.length; i++) {
                System.arraycopy(squares[i], 0, this.squares[i], 0, squares[i].length);
            }
        }
    }

    /**
     * simple recursive backtracking algorithm to solve
     * a Sudoku puzzle
     * @param gso a wrapper Object so that we can modify the parameter
     * @return boolean: true if sudoku was solved, false otherwise
     */
    private static boolean solve(GridSolveObject gso) {
        for (int i = 0; i < gso.squares.length; i++) {
            for (int j = 0; j < gso.squares[i].length; j++) {
                int currentDigit = gso.squares[i][j];

                if (currentDigit == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (canPlace(k, i, j, gso.squares)){
                            gso.squares[i][j] = k;

                            // recursive backtracking step
                            if (solve(gso)) {
                                return true;
                            }
                            else {
                                gso.squares[i][j] = 0;
                            }
                        }
                    }
                    // we went through all the digits, couldn't solve the sudoku.
                    // have made a mistake at a previous step, have to undo.
                    return false;
                }
            }
        }
        // we'll only reach here if everything is good, I think?
        return true;
    }

    /**
     *
     * @param g comma separated string. 81 digits
     * @return solved Grid object. null if sudoku is invalid
     */
    public static Grid solveGrid(Grid g) {
       Integer[][] squares = buildArray(g);

       Grid solved = null;
       if (isLegalSudoku(squares)) {
           GridSolveObject gso = new GridSolveObject(squares);

           if (solve(gso)) {
               solved = buildGrid(gso.squares);
           }
       }
       return solved;
    }


}
