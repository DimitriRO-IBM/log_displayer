import { Constants } from '../lib/constants';
import { NextProgressbarSpinner } from 'nextjs-progressbar-spinner/dist/cjs';
import useSWR from 'swr';
import { LogTable } from '../components/log-table/log-table';
import fetcher = Constants.fetcher;

function useLogs() {
    const { data, error } = useSWR('api/logs', fetcher);

    return {
        logs: data,
        isLoading: !data && !error,
        isError: error,
    }
}

export default function HomeApp() {
    const { logs, isLoading, isError } = useLogs();

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
            <LogTable logs={ [...logs?.logs ] }></LogTable>
        </div>
    );
}
