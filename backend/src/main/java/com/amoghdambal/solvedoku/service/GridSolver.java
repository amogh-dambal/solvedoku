package com.amoghdambal.solvedoku.service;

import com.amoghdambal.solvedoku.data.Grid;

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
        return false;
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
        return isRowValid()
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