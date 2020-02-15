const path = require('path');

module.exports = {
    name: 'word-relay-setting',
    mode: 'development', // production
    devtool: 'eval',
    resolve: {
        extensions: ['.js', '.jsx']
    },

    entry: {
        app: [
           './src/client', 
        ]
    },

    module: {
        rules: [{
            test: /\.jsx?$/,
            loader: 'babel-loader',
            options: {
                presets: [
                    '@babel/preset-env',
                    '@babel/preset-react',
                ],
                plugins: [
                    '@babel/plugin-proposal-class-properties',
                    'react-hot-loader/babel'
                ]
            }
        }]
    },

    output: {
        path: path.join(__dirname, 'out'),
        filename: 'app.js',
        publicPath: 'out'
    },

    devServer: {
        contentBase: path.join(__dirname, 'public'),
        compress: true,
        port: 9000
    },
}