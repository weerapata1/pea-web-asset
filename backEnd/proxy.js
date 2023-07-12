const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/idm', // API endpoint path to be proxied
    createProxyMiddleware({
      target: 'https://idm.pea.co.th/webservices/IdmServices.asmx', // URL of the API server
      changeOrigin: true,
      secure: false, // Set to true if your API server uses HTTPS
    })
  );
};