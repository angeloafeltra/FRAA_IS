module.exports = {
    publicPath: "./",
    chainWebpack: (config) => {
        config.plugin("html").tap((args) => {
            args[0].title = "Fraa"
            return args;
        });
    },
    devServer: {
        port: 8080,
        proxy: "http://192.168.125.73:3000"
    }
}