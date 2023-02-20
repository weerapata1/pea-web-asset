module.exports = {
  devServer: {
    port : 8000,
    proxy: {
      '/api': {
          target: 'http://172.21.1.51:8080',
          ws: true,
          changeOrigin: true
      }
  }
  },
  transpileDependencies: [
    'vuetify'
  ],


}
