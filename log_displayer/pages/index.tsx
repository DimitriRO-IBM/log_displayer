import { useEffect, useState } from 'react';
import { Constants } from '../lib/constants';
import { NextProgressbarSpinner } from 'nextjs-progressbar-spinner/dist/cjs';
import useSWR, { SWRConfiguration, useSWRConfig } from 'swr';
import { LogTable } from '../components/log-table/log-table';
import fetcher = Constants.fetcher;

export default function HomeApp() {
    const [logs, setLogs] = useState();

    const useLogs = () => {
        const { data, error } = useSWR('api/logs', {
            fetcher,
            refreshInterval: 1000,
            refreshWhenHidden: true,
        } as SWRConfiguration);

        return {
            _logs: data?.logs,
            isLoading: !data && !error,
            isError: error,
        }
    }

    const { _logs, isLoading, isError } = useLogs();

    useEffect(() => {
        console.log('In use effect ==>', _logs);
        setLogs(_logs)
    } ,[_logs, logs])

    if (isLoading) {
        return <NextProgressbarSpinner></NextProgressbarSpinner>
    }

    if (isError) {
        return {
            notFound: true,
        }
    }

    return (
        <div className="main">
            <LogTable logs={ [..._logs ] }></LogTable>
        </div>
    );
}
