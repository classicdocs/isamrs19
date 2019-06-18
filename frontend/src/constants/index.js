const ENVIRONMENTS = {
  'localhost': 'dev',
  'isamrs19.herokuapp.com': 'stage'
};

const BACKEND_HOSTNAMES = {
  'dev': 'http://localhost:8088',
  'stage': 'https://isamrs19.herokuapp.com'
};

const API_ROUTE = '/api';

export {
  ENVIRONMENTS,
  BACKEND_HOSTNAMES,
  API_ROUTE
};