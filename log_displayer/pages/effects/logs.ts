import { Constants } from '../../lib/constants';
import useSWR from 'swr';
import fetcher = Constants.fetcher;

export function useLogs() {
    const { data, error } = useSWR('api/logs', fetcher);

    return {
        logs: data,
        isLoading: !data && !error,
        isError: error,
    }
}
