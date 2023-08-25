module.exports = {
  devServer: {
    // headers: {
    //   "Access-Control-Allow-Origin": "*",
    //   "Access-Control-Allow-Methods":"DELETE, POST, GET, OPTIONS",
    //   "Access-Control-Allow-Headers":"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With",
    // },
    port: 8000,
    proxy:
     {
      "/api": {
        target: "http://172.21.1.51:8080",
        ws: true,
        changeOrigin: true,
        secure: false
      },
      "/idm": {
        target: "https://idm.pea.co.th/webservices",
        ws: true,
        changeOrigin: true,
        // pathRewrite: {'^/idm' : '/'}
      }
    },
    //proxy:"https://idm.pea.co.th/webservices/IdmServices.asmx",
  },
  transpileDependencies: ["vuetify"],
};
