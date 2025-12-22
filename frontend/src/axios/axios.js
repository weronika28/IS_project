import a from 'axios';

export const STORAGE_KEY = "access_token"

a.defaults.baseURL = "http://api.local";
a.defaults.headers.common['Content-Type'] = 'application/json';

a.interceptors.request.use(request => {
    if (localStorage.getItem(STORAGE_KEY))
        request.headers['Authorization'] = `Bearer ${localStorage.getItem(STORAGE_KEY)}`

    console.log("storage key: ", localStorage.getItem(STORAGE_KEY))
    console.log({request})

    return request;
});

// interceptor to convert data to JSON before sending
a.interceptors.request.use(config => {
    if (config.data && config.headers['Content-Type'] === 'application/json') {
        config.data = JSON.stringify(config.data);
    }
    return config;
});

// interceptor to convert data to JSON after receiving
a.interceptors.response.use(response => {
    if (response.headers['content-type'] === 'application/json') {
        response.data = JSON.parse(response.data);
    }
    return response;
});

export const axios = a;
