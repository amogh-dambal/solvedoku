import React from 'react'

export function Square(props) {
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