function getBaseUrlForService(serviceName) {
    switch (serviceName) {
        case 'ORDERS':
            return 'localhost:8080'
    }
}

module.exports = getBaseUrlForService;