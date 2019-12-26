import React, {useReducer} from "react";
import reducer, {init} from "../../reducer"

const context = React.createContext({});
const WrappedProvider = props => {
	const [state, dispatch] = useReducer(reducer, init);
	return (<context.Provider value={{state, dispatch}}>
		{props.children}
	</context.Provider>)
};


export {context};
export default WrappedProvider;
