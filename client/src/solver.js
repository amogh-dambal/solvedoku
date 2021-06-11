import React from 'react';

export function Solver(props) {
    return (
        <button
            className="submission-button"
            onClick={props.onClick}
        >
            Submit
        </button>
    )
}