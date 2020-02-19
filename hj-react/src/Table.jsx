import React from 'react';
import Tr from './Tr';

const Table = ({ onClick }) => {
    return (
        <table onClick={onClick}>
            <Tr>{''}</Tr>
        </table>
    )
}

export default Table;