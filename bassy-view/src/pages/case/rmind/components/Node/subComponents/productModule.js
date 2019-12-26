import React, {useState} from 'react';
import {css, cx} from 'emotion';
import useMindmap from '../../../customHooks/useMindmap';
import {ButtonSet, Highlight} from '../../Popup/common/styledComponents';
import {Tooltip} from "antd";

const ProductModule = ({node, moduleList, cancelAddProductModule}) => {
	const mindmapHook = useMindmap();

	const [moduleInfo, setModuleInfo] = useState({});

	const handleAddProductModule = () => {
		mindmapHook.addProductModule(node.id, node.productId, node.requireId, moduleInfo.id, moduleInfo.name);
	};

	const selectModule = (module) => {
		setModuleInfo(module);
	};

	return (<div className={wrapper}>
		<div className={content_wrapper}>
			<i className={'zwicon-close-circle ' + close_button} onClick={cancelAddProductModule}/>
			<Highlight>请选择子模块：</Highlight>
			<ul className={list_wrapper}>
				{moduleList.map((module) => <div
					className={cx(module_wrapper, {[current_style]: module.id === moduleInfo.id})}
					onClick={() => selectModule(module)}><Tooltip placement="topLeft" title={module.name}>{module.name}</Tooltip></div>)}
			</ul>
			<ButtonSet>
				<button onClick={handleAddProductModule}>完成</button>
			</ButtonSet>
		</div>
		<div className={overlay}/>
	</div>);
};

export default ProductModule;


// CSS
const wrapper = css`
position: fixed;
top:0;
bottom:0;
left:0;
right:0;
z-index: 500;
`;

const content_wrapper = css`
display: flex;
flex-direction: column;
position: absolute;
top:30%;
left:0;
right:0;
width: 400px;
margin: auto;
padding: 40px 40px 20px;
font-size: 1rem;
background-color: #ffffff;
border-radius: 20px;
z-index: 1;
overFlow:scroll;
max-height:500px;
`;
// CSS
const list_wrapper = css`
display: flex;
justify-content: space-between;
flex-wrap: wrap;
padding: 0;
list-style: none;
`;
const overlay = css`
position: absolute;
top:0;
bottom:0;
left:0;
right:0;
background: #000000;
opacity: 0.2;
`;

const close_button = css`
position: absolute;
top: 10px;
right: 10px;
font-size: 25px;
font-weight: 700;

&:active {
transform: scale(0.95)
}
`;

const module_wrapper = css`
display: flex;
box-sizing: border-box;
width: 47%;
height: 25px;
margin: 8px 0;
border-radius: 18px;
cursor: pointer;
overflow: auto;

div {
flex: 1;
z-index: -1;
}
`;

const current_style = css`
box-shadow: 0 0 0 4px #ffffff, 0 0 0 6px #000000; /* 两层阴影实现框选效果，也可用 inset 阴影 + border-color 做 */
`;
