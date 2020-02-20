import React, { useCallback} from 'react';
import { CLICK_CELL, CHANGE_TURN } from './TicTacToe'

const Td = ({ dispatch, rowIndex, cellIndex, cellData }) => {
    const onClickTd = useCallback(() => {
        console.log(rowIndex, cellIndex, cellData);

        dispatch({ type: CLICK_CELL, row: rowIndex, cell: cellIndex });
        dispatch({ type: CHANGE_TURN });
    }, []);

    return (
        <td onClick={onClickTd}>{cellData}</td>
    );
}

export default Td;
