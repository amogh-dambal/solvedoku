package com.amoghdambal.solvedoku.service;

import com.amoghdambal.solvedoku.data.Grid;

import java.util.HashSet;
import java.util.Set;

public class GridSolver {
    private Integer[][] squares;

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

    private boolean isLegalSudoku() {
        // check that each row, column, and subgrid is legal
        return isLegalRows() && isLegalColumns() && isLegalSubgrids();
    }

    private boolean isLegalRows() {
        Set<Integer> duplicates;
        for (Integer[] row : this.squares) {
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

    private boolean isLegalColumns() {
        Set<Integer> duplicates;
        for (int j = 0; j < 9; j++) {
            int sum = 0;
            duplicates = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                Integer digit = this.squares[i][j];
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

    private boolean isLegalSubgrids() {
        boolean valid = true;
        for (int i = 0; i < 9 && valid; i += 3) {
            for (int j = 0; j < 9 && valid; j += 3) {
                valid = isLegalSubgrid(i, j);
            }
        }
        return valid;
    }

    private boolean isLegalSubgrid(int i, int j) {
        int sum = 0;
        Set<Integer> duplicates = new HashSet<>();
        for (int x = i; x < i + 3; x++) {
            for (int y = j; y < j + 3; j++) {
                Integer digit = this.squares[i][j];
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
     * @return true if you can place digit, false otherwise
     */
    private boolean canPlace(int digit, int i, int j) {
        // validity of row
        for (int x = 0; x < 9; x++) {
            if (this.squares[x][j] == digit) {
                return false;
            }
        }
        // validity of column
        for (int y = 0; y < 9; y++) {
            if (this.squares[i][y] == digit) {
                return false;
            }
        }
        // validity of 3 x 3 square
        int squareStartRow = i / 3 * 3;
        int squareStartCol = j / 3 * 3;
        for (int p = squareStartRow; p < squareStartRow + 3; p++) {
            for (int q = squareStartCol; q < squareStartCol + 3; q++) {
                boolean onSameSquare = p == i && q == j;
                if (!onSameSquare && this.squares[p][q] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * simple recursive backtracking algorithm to solve
     * a Sudoku puzzle
     * @return boolean: true if sudoku was solved, false otherwise
     */
    private boolean solve() {
        for (int i = 0; i < this.squares.length; i++) {
            for (int j = 0; j < this.squares[i].length; j++) {
                int currentDigit = this.squares[i][j];

                if (currentDigit == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (canPlace(k, i, j)){
                            this.squares[i][j] = k;

                            // recursive backtracking step
                            if (solve()) {
                                return true;
                            }
                            else {
                                this.squares[i][j] = 0;
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
     * default constructor - should be no way to construct
     * a GridSolver object without a grid
     * @param g the Grid object given to us by the frontend
     */
    public GridSolver(Grid g) {
        this.squares = buildArray(g);
    }

    /**
     * obtain a solution for the Sudoku puzzle
     * @return solved Grid object. null if sudoku is invalid
     */
    public Grid getSolution() {
       Grid solved = null;
       if (isLegalSudoku()) {
           if (solve()) {
               solved = buildGrid(this.squares);
           }
       }
       return solved;
    }


}
