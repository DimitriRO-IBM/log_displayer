import dayjs, { type Dayjs } from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
import 'dayjs/locale/fr';
import { NextApiRequest } from 'next';

dayjs.extend(customParseFormat);
dayjs.locale('fr');

export namespace Constants {
  export const READABLE_STRING_FORMAT: string = "";

  /**
   * Returns a string formatted by the {format} given as parameter
   * @param timestamp the unix timestamp in ms
   * @param format the date format
   * @returns a string formatted
   */
  export function getFormattedDate(timestamp: string, format: string): string {
    const _: Dayjs = dayjs(Number(timestamp));
    return _.format(format);
  }

  export const fetcher = (...args: any[]) => {
    // @ts-ignore
    return fetch(...args).then((res) => res.json());
  };
}
