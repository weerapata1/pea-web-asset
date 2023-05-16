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
      "^/api": {
        target: "http://localhost:8080",
        ws: true,
        changeOrigin: true,
        secure: false
      },
      "/idm": {
        target: "https://idm.pea.co.th/webservices/IdmServices.asmx",
        ws: true,
        changeOrigin: true,
        
        // ws: true,
        // changeOrigin: true,
        // secure: false,
        // pathRewrite: {'^/idm' : '/'}
      }
    },
    //proxy:"https://idm.pea.co.th/webservices/IdmServices.asmx",
  },
  transpileDependencies: ["vuetify"],
};
