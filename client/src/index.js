import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {Board} from './board.js';


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

/*
====
test
====
*/
class TestComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            grids: []
        }
    }

    async componentDidMount() {
        const response = await fetch("/api");
        const body = await response.json();
        this.setState({grids: body});
    }

    render() {
        const flightsView = this.state.grids;
        return (
            <div>
                <h2>
                    Flights 
                </h2>
                {
                    flightsView.map(
                        flight =>
                        <div key={flight.id}>
                            {flight.gridRepresentation}
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