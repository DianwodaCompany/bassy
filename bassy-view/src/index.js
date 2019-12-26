import React from 'react'
import ReactDOM from 'react-dom'
import App from './pages/App.js'
import { Provider } from 'react-redux'
import { createStore, applyMiddleware, compose} from 'redux'
import createSagaMiddleware from 'redux-saga'
import thunk from 'redux-thunk';
import logger from 'redux-logger'
import reducer from './reducers'
import mySaga from './sagas'
import 'antd/dist/antd.less'
import 'ant-design-pro/dist/ant-design-pro.css'

const win = window;

const sagaMiddleware = createSagaMiddleware();

const middlewares = [];
middlewares.push(thunk,sagaMiddleware,logger);

const storeEnhancers = compose(
    applyMiddleware(...middlewares),
    (win && win.__REDUX_DEVTOOLS_EXTENSION__) ? win.__REDUX_DEVTOOLS_EXTENSION__() : (f) => f,
);

const store = createStore(
    reducer, {}, storeEnhancers
);

sagaMiddleware.run(mySaga);


ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('root')
);

if (module.hot) {
    module.hot.accept()
}