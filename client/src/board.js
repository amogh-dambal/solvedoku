import React from 'react';
import {Square} from './square.js';
import {Solver} from './solver.js';
import {isValidSudoku, solveSudoku} from './sudoku.js';

export class Board extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            squares: initializeSudoku(9),
            solved: false
        }
    }

    handleInput(i, event) {
        const [x, y] = expand(i);

        const newSquares = this.state.squares.slice();
        newSquares[x][y] = parseInt(event.target.value);
        this.setState({
            squares: newSquares,
            solved: false
        });
    }

    buildBoardRows() {
        
        const grid = [];
        for (let i = 0; i < 9; i++) {
            const row = [];
            for (let j = 0; j < 9; j++) {
                const idx = collapse(i, j);
                row.push(<Square key={idx} onChange={(event) => {this.handleInput(idx, event)}}/>);
            }
            grid.push(row);
        }

        /*
        we split up construction into 2 separate
        items so we can wrap the board rows in style
        */
        const boardRows = [];
        for (let i = 0; i < 9; i++) {
            boardRows.push(<div key={i} className="board-row">{grid[i]}</div>)
        }
        return boardRows;
    }

    onButtonClick() {
        // console.log("allsum: " + allSum(this.state.squares));

        /**
         * algorithm: 
         * 1. build a string representation of the current this.state.squares
         * 2. build a JSON object of the thing
         * 3. send it to the backend with a POST request 
         * 4. backend will send us back a solved string
         * 5. rebuild a grid representation from that string and update state
         */
        const gridJSON = {
            "gridRepresentation": null
        };


        if (isValidSudoku(this.state.squares)) {
            const squaresCopy = this.state.squares.slice();
            const gridObject = {
                squares: squaresCopy
            };
            const solved = solveSudoku(gridObject);
            if (!solved) {
                alert("Error! Unable to solve the given sudoku.");
            }
            this.setState({
                squares: gridObject.squares,
                solved: true
            });
        }
        // log an error in some way. not sure how 
        else {
            console.log("You have entered an invalid Sudoku. Please try again.");
        }

    }

    async componentDidMount() {
        this.setState({
            /* TODO */
        });
    }

    render() {
        const boardRows = this.buildBoardRows(); 
        return (
            <div>
                <div>
                    {boardRows}
                </div>
                <div>
                    <Solver onClick={() => {this.onButtonClick()}}/>
                </div>             
            </div>
   
        );
        
    }
};

/**
 * function that initializes a square matrix M of 
 * size n x n for sudoku. initial value of any
 * @pre n > 0 (is usually 9 for a classical Sudoku)
 * @param {int} n : number of rows and columns
 * @returns {Array} m : 2D matrix
 * @post m[i][j] === null where 0 <= i, j < n
 */
 function initializeSudoku(n) {
    let m = Array(9).fill(0).map(
        () => {
            return Array(9).fill(0)
        }
    );
    return m;
}

/* 
converts single-digit index i 
to coordinates (x, y) and returns the pair
assumes an M x N grid.  
*/ 
function expand(pos) {
    // number of rows
    const M = 9;
    // number of columns
    const N = 9;
    return [
        Math.floor(pos / M),
        Math.floor(pos % N)
    ];
}

function collapse(row, col) {
    return (9 * row) + col;
}