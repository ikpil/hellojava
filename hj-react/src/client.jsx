import React from 'react';
import ReactDom from 'react-dom';
import { hot } from 'react-hot-loader/root';
import TicTacToe from './TicTacToe';

const HotLoader = hot(TicTacToe);

ReactDom.render(<HotLoader />, document.querySelector('#root'));