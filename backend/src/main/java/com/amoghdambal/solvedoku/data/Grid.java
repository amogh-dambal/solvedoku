package com.amoghdambal.solvedoku.data;

import org.springframework.data.annotation.Id;

import java.security.InvalidParameterException;

public class Grid {
    @Id
    private String id;

    private String title;

    private boolean completed;

//    private Integer[][] grid;

//    public Integer[][] getGrid() {
//        return this.grid;
//    }
//
//    public Grid() {
//        this.grid = new Integer[9][9];
//    }

    public String getID() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public Grid(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

//    public Grid(Integer[][] grid) {
//        final int M = grid.length;
//        final int N = grid[0].length;
//
//        // input grid is not square
//        if (M != N) {
//            throw new InvalidParameterException("Input grid is not square!");
//        }
//        // input grid is not 9 x 9
//        else if (M * N != 81) {
//            throw new InvalidParameterException("Input grid size is not correct!");
//        }
//        else {
//            this.grid = grid;
//        }
//    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (Integer[] row : this.grid) {
//            for (Integer i : row) {
//                sb.append(i).append("\t");
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
}
