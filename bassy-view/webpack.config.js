const path = require('path');
const mode = "development";
const webpack = require('webpack');
module.exports = {
	mode,
	entry: [
		// 'webpack-hot-middleware/client?noInfo=false&reload=true&quiet=false',
		"./src/index.js"
	],
	output: {
		path: path.resolve(__dirname, "dist"),
		filename: "bundle.js",
		publicPath: 'http://localhost:8801/dist/'
	},
	devtool: "source-map",

	// resolve: {
	// 	// Add '.ts' and '.tsx' as resolvable extensions.
	// 	extensions: [".jsx", ".js", ".json"]
	// },

	module: {
		rules: [{
			test: /\.(js|jsx)/,
			exclude: /node_modules/,
			use: {
				loader: 'babel-loader'
			}
		}, {
			test: /\.(png|eot|ttf|woff|svg)/,
			use: {
				loader: 'file-loader'
			}
		},
			{
				test: /\.less$/,
				include: /node_modules/,
				loader: 'style-loader!css-loader!less-loader?javascriptEnabled=true'
			},
			{
				test: /\.css$/,
				loaders: ["style-loader", "css-loader"]
			},
			{
				test: /\.less$/,
				exclude: path.resolve(__dirname, './node_modules'),
				use: [{
					loader: "style-loader" // creates style nodes from JS strings
				}, {
					loader: "css-loader", // translates CSS into CommonJS
					options: {
						modules: true,
						importLoaders: 1,
						localIdentName: "[name]__[local]___[hash:base64:5]",
					}
				}, {
					loader: "less-loader", // compiles Less to CSS
					options:
						{
							modifyVars: {
								'primary-color': '#F57400',
								'border-radius-base': '2px',
							},
							javascriptEnabled: true
						}
				}]
			}
		]
	},

	devServer: {
		proxy: {
			'/bassy': 'http://localhost:13235/'
		}
	},
	node: {
		fs: "empty"
	},
	plugins: [
		new webpack.HotModuleReplacementPlugin(),
		new webpack.NoEmitOnErrorsPlugin()
	]
	// When importing a module whose path matches one of the following, just
	// assume a corresponding global variable exists and use that instead.
	// This is important because it allows us to avoid bundling all of our
	// dependencies, which allows browsers to cache those libraries between builds.

}
