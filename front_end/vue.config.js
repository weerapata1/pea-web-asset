const path = require('path');
const webpack = require('webpack');

function resolveSrc(_path) {
  return path.join(__dirname, _path);
}

module.exports = {

 chainWebpack: config => {
        config
            .plugin('html')
            .tap(args => {
                args[0].favicon = "public/img/favicon.ico";
                return args;
            }),
        config.resolve.alias.set(
              'vue$',
              // If using the runtime only build
              path.resolve(__dirname, 'node_modules/vue/dist/vue.runtime.esm.js')
              // Or if using full build of Vue (runtime + compiler)
              // path.resolve(__dirname, 'node_modules/vue/dist/vue.esm.js')
        )
    },
  lintOnSave: false,
  configureWebpack: {
    // Set up all the aliases we use in our app.
    resolve: {
      alias: {
        src: resolveSrc('src'),
        'chart.js': 'chart.js/dist/Chart.js'
      }
    },
    plugins: [
      new webpack.optimize.LimitChunkCountPlugin({
        maxChunks: 6
      })
    ]
  },
  pwa: {
    name: 'Assets Management System',
    themeColor: '#344675',
    msTileColor: '#344675',
    appleMobileWebAppCapable: 'yes',
    appleMobileWebAppStatusBarStyle: '#344675'
  },
  css: {
    // Enable CSS source maps.
    sourceMap: process.env.NODE_ENV !== 'production'
  }
};
