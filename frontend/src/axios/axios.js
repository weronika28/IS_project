import {Axios} from "axios";

export const STORAGE_KEY = "access_token"

export const axios = new Axios({
  baseURL: "http://localhost:5000",
  headers: {
    "Content-Type": "application/json"
  }
});

axios.interceptors.request.use(request => {
    if(localStorage.getItem(STORAGE_KEY))
      request.headers['Authorization'] = `Bearer ${localStorage.getItem(STORAGE_KEY)}`

  return request;
});

// interceptor to convert data to JSON before sending
axios.interceptors.request.use(config => {
  if (config.data && config.headers['Content-Type'] === 'application/json') {
    config.data = JSON.stringify(config.data);
  }
  return config;
});

// interceptor to convert data to JSON after receiving
axios.interceptors.response.use(response => {
  if (response.headers['content-type'] === 'application/json') {
    response.data = JSON.parse(response.data);
  }
  return response;
});