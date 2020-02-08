const express = require('express');
const router = express.Router();
const getServicePath = require('../services/get-service-path');
const fetch = require('node-fetch');
const proxy = require('express-http-proxy');
const app = express();
const BASE_URL = getServicePath('ORDERS')



app.use('/orders', proxy(BASE_URL, {
    filter(req, res) {
        return req.method === 'GET';
    }
}));


router.post('/', async (req, res, next) => {
    const userUuid = '123';
    const response = await fetch('http://'+BASE_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            userUuid,
            ...req.body
        }),
    });
    res.status(response.status);
    res.send()
});

module.exports = router;
