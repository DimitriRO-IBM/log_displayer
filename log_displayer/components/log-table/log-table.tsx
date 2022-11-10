import { LogLine } from './log-line/log-line';
import { Log } from '../../models/log.model';
import { useEffect, useState } from 'react';
import styles from './LogTable.module.scss';

type LogTableProps = {
    logs: Log[] | any[];
}

export const LogTable = ({logs}: LogTableProps) => {
    const [_logs, setLogs] = useState(logs ?? []);

    let displayedLogs: JSX.Element | JSX.Element[] = <div className={styles["log-table__content--empty"]}>No logs in table</div>;

    if (_logs.length) {
        displayedLogs = _logs.map((log, index) => {
            return <LogLine key={index} index={index} log={log}></LogLine>
        });
    }

    return (
        <div className={styles["log-table__container"]}>
            <div className={styles["log-table__header"]}>
                <span className={styles["log-table__header--index"]}>Index</span>
                <span className={styles["log-table__header--date"]}>Date</span>
                <span className={styles["log-table__header--message"]}>Message</span>
            </div>
            <div className={styles["log-table__content"]}>
                { displayedLogs }
            </div>
        </div>
    );
};
