import { NextProgressbarSpinner } from 'nextjs-progressbar-spinner/dist/cjs';
import { useLogs } from '../pages/effects/logs';
import { LogTable } from '../components/log-table/log-table';

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
