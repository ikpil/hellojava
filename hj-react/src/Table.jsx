import React from 'react';
import Tr from './Tr';

const Table = ({ dispatch, onClick, tableData }) => {
    return (
        <table onClick={onClick}>
            {Array(tableData.length).fill().map((tr, i) => <Tr dispatch={dispatch} rowIndex={i} rowData={tableData[i]}/>)}
        </table>
    )
}

export default Table;