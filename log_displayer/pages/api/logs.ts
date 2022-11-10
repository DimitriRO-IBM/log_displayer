import { Log } from '../../models/log.model';
import { NextApiRequest, NextApiResponse } from 'next';
import prisma from '../../lib/prisma';

type LogApiResponse = {
    log?: Partial<Log>;
    logs?: Partial<Log>[];
    error?: any;
}

export default async function handler(req: NextApiRequest, res: NextApiResponse): Promise<void> {
    switch (req.method) {
        case 'GET':
            try {
                const logs = await prisma.log.findMany();
                res.status(200).json({
                    logs: logs.sort((a, b) => Number(a.timestamp) - Number(b.timestamp)),
                });
            } catch (e) {
                res.status(500).json({ error: e.message });
            }
            break;
        case 'POST':
            const { message, timestamp } = req.body;
            try {
                const log = await prisma.log.create({
                    data: {
                        message,
                        timestamp,
                    }
                });
                res.status(200).json({ log });
            } catch (e) {
                res.status(500).json({ error: e.message });
            }
            break;
    }
}
