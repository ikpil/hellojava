import React, { useCallback} from 'react';
import { CLICK_CELL, CHANGE_TURN } from './TicTacToe'

const Td = ({ dispatch, rowIndex, cellIndex }) => {
    const onClickTd = useCallback(() => {
        console.log(rowIndex, cellIndex)
        dispatch({ type: CLICK_CELL, row: rowIndex, cell: cellIndex })
        dispatch({ type: CHANGE_TURN })
    }, []);

    return (
        <td onClick={onClickTd}></td>
    );
}

export default Td;
