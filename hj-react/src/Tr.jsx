import React, { memo, useContext } from 'react';
import Td from './Td';

const Tr = ({ dispatch, rowIndex, rowData }) => {
    return (
        <tr>
            {Array(rowData.length).fill().map((td, i) => <Td dispatch={dispatch} rowIndex={rowIndex} cellIndex={i}/>)}
        </tr>
    );
}

export default Tr;