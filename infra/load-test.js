import http from 'k6/http';
import { check, sleep } from 'k6';
import { Counter, Trend } from 'k6/metrics';

export let errorCount = new Counter('errors');
export let startTime = new Trend('start_time');

export let options = {
    stages: [
        { duration: '1m', target: 1 }, // Ramp-up to 1 user over 1 minute to simulate cold start
    ],
    thresholds: {
        errors: ['count<10'], // Allow up to 10 errors
        http_req_duration: ['p(95)<500'], // 95% of requests should be below 500ms
        start_time: ['p(95)<20000'], // 95% of start times should be below 20s
    },
};

export default function () {
    let start = new Date().getTime();
    let res = http.get('http://localhost:8080/your-endpoint'); // Update with your actual endpoint

    check(res, {
        'is status 200': (r) => r.status === 200,
    }) || errorCount.add(1);

    let end = new Date().getTime();
    startTime.add(end - start);

    sleep(1); // Wait for 1 second between iterations
}
