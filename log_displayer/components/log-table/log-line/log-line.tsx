import React, { useEffect, useState } from 'react';
import { Log } from '../../../models/log.model';
import styles from './LogLine.module.scss';

type LogLineProps = {
    index: number;
    log: Log | Partial<Log>;
};

export const LogLine = ({ index, log }: LogLineProps) => {
    const [_index, setIndex] = useState(index ?? 0);
    const [_log, setLog] = useState(log ?? new Log('', ''));

    const iLog: Log = new Log(_log.message, _log.timestamp);

    return (
        <div className={ styles['log-table__line'] }>
          <span className={ styles['log-table__line--index'] }>
            { _index ?? 0 }
          </span>
                <span className={ styles['log-table__line--date'] }>
            { iLog?.getReadableDate() }
          </span>
                <span className={ styles['log-table__line--message'] }>
            { iLog.message }
          </span>
        </div>
    );
};
