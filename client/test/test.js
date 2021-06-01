const grid = Array(9).fill(null).map(() => Array(9).fill(null));

function printGrid(grid) {
    console.log(
        JSON.stringify(grid)
        .replace(
            /(\[\[)(.*)(\]\])/g,'[\n  [$2]\n]'
        )
        .replace(
            /],/g,'],\n  '
        )
    );
}

function manualPrintGrid(grid) {
    for (var i = 0; i < 9; i++) {
        for (var j = 0; j < 9; j++) {
            console.log(grid[i][j]);
        }
    }
}

printGrid(grid);
manualPrintGrid(grid);