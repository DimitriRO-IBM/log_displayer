import { Constants } from "../lib/constants";


export class Log {
  readonly timestamp: string;
  message: string;

  constructor(message: string, timestamp: string) {
    this.message = message;
    this.timestamp = timestamp;
  }

  getReadableDate(): string {
    return Constants.getFormattedDate(this.timestamp, "DD-MM-YYYY HH:mm:ss");
  }
}
