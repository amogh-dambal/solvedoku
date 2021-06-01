import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

/* function component */
function Square(props) {
    return (
        /* TODO: input validation such that 
        max length of input is 1 character
        that is a digit */
        <input
            type="number"
            className="square"
            min="1"
            max="9"
            onChange={props.onChange}
        />
    );
}

function Solver(props) {
    return (
        <button
            className="submission-button"
            /* onClick={TODO}*/
        >
            Submit
        </button>
    )
}

class Board extends React.Component {
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

        /* debugging code to ensure React components are correctly setup */
        let tmp = 0; 
        for (let i = 0; i < 9; i++) {
            for (let j = 0; j < 9; j++) {
                tmp += this.state.squares[i][j];
            }
        }
        console.log("event handled. tmp updated to " + tmp + ".");
        
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
            boardRows.push(<div className="board-row">{grid[i]}</div>)
        }
        return boardRows;
    }

    render() {
        const boardRows = this.buildBoardRows(); 
        return (
            <div>
                {boardRows}
            </div>
        );
        
    }
};


class Game extends React.Component {
    render() {
        return (
            <div class="game">
                <div class="game-board">
                    <Board/>
                </div>
                <div>
                    <Solver/>
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
    let m = Array(9).fill(null).map(
        () => {
            return Array(9).fill(null)
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

// =======
ReactDOM.render(
    <Game />,
    document.getElementById("root")
);