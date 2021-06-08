import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {isValidSudoku, solveSudoku} from './sudoku.js';

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
            onClick={props.onClick}
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
        console.log("allsum: " + allSum(this.state.squares));
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

    render() {
        const boardRows = this.buildBoardRows(); 
        /* todo: fix the CSS here: the solver component is way too far down. */
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


class Game extends React.Component {
    render() {
        return (
            <div className="game">
                <div className="game-board">
                    <Board/>
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

function allSum(squares) {
    let tmp = 0;
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            tmp += squares[i][j]
        }
    }
    return tmp;
}

/*
====
test
====
*/
class TestComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            flights: []
        }
    }

    async componentDidMount() {
        const response = await fetch("/test");
        const body = await response.json();
        this.setState({flights: body});
    }

    render() {
        const flightsView = this.state.flights;
        return (
            <div>
                <h2>
                    Flights 
                </h2>
                {
                    flightsView.map(
                        flight =>
                        <div key={flight.id}>
                            {flight.origin} {flight.destination}
                            {flight.airline} {flight.flightNumber}
                        </div>
                    )
                }
            </div>
        );
    }
}

// =======
ReactDOM.render(
    <TestComponent />,
    // <Game />,
    document.getElementById("root")
);