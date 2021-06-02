/**
 * sudoku.js
 * file that contains game logic
 */

/**
 * function that checks whether the given Sudoku is valid i.e. legal or not
 * @param {2D array} squares : array that holds number information of Sudokus
 * @returns boolean: true if valid, false otherwise
 */
export function isValidSudoku(squares) {
    return validateRows(squares) && validateColumns(squares) && validateGrids(squares); 
}

/**
 * function that takes in a partially filled sudoku and solves it 
 * using recursive backtracking algorithm
 * @param {2D array} squares : array that holds number information of sudokus
 * @returns new 2D array of numbers representing the solution to this sudoku
 */
export function solveSudoku(gridObject) {
    const newSquares = gridObject.squares;
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            // null means no value there
            if (newSquares[i][j] !== 0) {
                for (let k = 1; k <= 9; k++) {
                    if (validate(newSquares, i, j, k)) {
                        newSquares[i][j] = k;

                        if (solveSudoku(gridObject)) {
                            return true;
                        }
                        else {
                            newSquares[i][j] = 0;
                        }
                    }
                }
                // we've checked all possible values, nothing gives us a solution
                return false;
            }
        }
    }
    // basest case - it's already solved (i think)
    return true;
}

/* TODO: replace null checks since we're not actually using null as base value */
function validate(squares, i, j, k) {
    const potentialSquares = squares.slice();
    potentialSquares[i][j] = k;
    return isValidSudoku(potentialSquares);
}

function validateRows(squares) {
    let valid = true;
    for (let i = 0; i < 9 && valid; i++) {
        const numbers = new Set();
        for (let j = 0; j < 9 && valid; j++) {
            if (squares[i][j]) {
                valid = !numbers.has(squares[i][j]);

                if (valid) {
                    numbers.add(squares[i][j]);
                }
                else {
                    return false;
                }
            }
        }
    }
    return valid;
}

function validateColumns(squares) {
    let valid = true;
    for (let j = 0; j < 9 && valid; j++) {
        const numbers = new Set();
        for (let i = 0; i < 9; i++) {
            if (squares[i][j]) {
                valid = !numbers.has(squares[i][j]);

                if (valid) {
                    numbers.add(squares[i][j]);
                }
                else {
                    return false;
                }
            }
        }
    }
    return valid;
}

function validateGrids(squares) {
    let valid = true;
    for (let i = 0; i < 9 && valid; i += 3) {
        for (let j = 0; j < 9 && valid; j += 3) {             
            valid = validateSubgrid(squares, i, j);
        }
    }
    return valid;
}

function validateSubgrid(squares, x, y) {
    let valid = true;
    const numbers = new Set();
    for (let i = 0; i < 3 && valid; i++) {
        for (let j = 0; j < 3 && valid; j++) {
            const k = squares[i + x][j + y];
            if (k) {
                valid = !numbers.has(k);

                if (valid) {
                    numbers.add(k);
                }
                else {
                    return false;
                }
            }
        }
    }
    return valid;
}

