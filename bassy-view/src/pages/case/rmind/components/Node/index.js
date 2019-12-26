import React, {useContext, useEffect, useRef, useState} from 'react';
import {Icon} from 'antd';
import {css, cx} from 'emotion';
import {context} from '../../context';
import useMindmap from "../../customHooks/useMindmap";
import {getNodeInfo} from '../../context/reducer/nodeStatus/actionCreator';
import * as refer from '../../statics/refer';
import {
    CASE,
    CASE_LABEL,
    CASE_PRECONDITION,
    CASE_PRI,
    CASE_STEP,
    CASE_STEP_DESCRIBE,
    EXCEPT_DB,
    EXCEPT_OTHER,
    EXCEPT_RESPONSE,
    EXCEPT_UI,
    PRODUCT,
    PRODUCT_MODULE,
    ROOT
} from '../../statics/refer';
import {handlePropagation} from '../../methods/assistFunctions';
import Toolbar from './subComponents/Toolbar';
import InputDiv from './subComponents/InputDiv';
import {getLabelList} from "../../../../../apis";
import {CASE_LABEL_SYSTEM} from "../../statics/refer";
import {CASE_LABEL_BASE} from "../../statics/refer";
import BaseLabelSelect from "./subComponents/BaseLabelSelect";
import SystemLabelSelect from "./subComponents/SystemLabelSelect";

const Node = ({layer, node, parent, node_refs, on_left}) => {
    const self = useRef();
    const {state: nodeStatus, dispatch: nDispatch} = useContext(context).nodeStatus;
    const mindmapHook = useMindmap();

    const handleSelectNode = () => {
        mindmapHook.selectNode(node.id, true);
    };

    const handleEditNode = async () => {
        if (node.type === ROOT || node.type === PRODUCT || node.type === PRODUCT_MODULE || node.type === CASE_PRI
            || node.type === CASE_STEP || node.type === CASE_LABEL) {
            return;
        }
        mindmapHook.editNode(node.id);
    };

    const handleToggleChildren = () => {
        mindmapHook.toggleChildren(node.id, !node.showChildren);
        mindmapHook.clearNodeStatus(); // 避免出现当前选择的节点被隐藏后仍然可以操作的情况
    };

    const caseVerityType = (verifyType) => {
        if (verifyType !== undefined && verifyType === PRODUCT_MODULE) {
            return <Icon style={{margin: "auto"}} type="apartment"/>;
        }
        if (verifyType !== undefined && verifyType === CASE) {
            return <Icon style={{margin: "auto"}} type="eye"/>;
        }
        if (verifyType !== undefined && verifyType === CASE_PRECONDITION) {
            return <Icon style={{margin: "auto"}} type="robot"/>;
        }
        if (verifyType !== undefined && verifyType === CASE_PRI) {
            return <Icon style={{margin: "auto"}} type="bell"/>;
        }
        if (verifyType !== undefined && verifyType === CASE_STEP) {
            return <Icon style={{margin: "auto"}} type="user"/>;
        }
        if (verifyType !== undefined && verifyType === CASE_LABEL) {
            return <Icon style={{margin: "auto"}} type="tags"/>;
        }
        if (verifyType !== undefined && verifyType === EXCEPT_DB) {
            return <Icon style={{margin: "auto"}} type="database"/>;
        }
        if (verifyType !== undefined && verifyType === EXCEPT_UI) {
            return <Icon style={{margin: "auto"}} type="desktop"/>;
        }
        if (verifyType !== undefined && verifyType === EXCEPT_RESPONSE) {
            return <Icon style={{margin: "auto"}} type="api"/>;
        }
        if (verifyType !== undefined && verifyType === EXCEPT_OTHER) {
            return <Icon style={{margin: "auto"}} type="more"/>;
        }
        if (verifyType !== undefined && verifyType === CASE_STEP_DESCRIBE) {
            return <Icon style={{margin: "auto"}} type="code"/>;
        }
        if (verifyType !== undefined && verifyType === CASE_LABEL_SYSTEM) {
            return <Icon style={{margin: "auto"}} type="setting"/>;
        }
        if (verifyType !== undefined && verifyType === CASE_LABEL_BASE) {
            return <Icon style={{margin: "auto"}} type="build"/>;
        }
    };


    const casePass = (node) => {
        const {pass, requireId} = node;
        //基础用例不展示通过状态
        if (requireId === null){
            return ;
        }
        //未执行
        if (pass !== undefined && pass === 0) {
            return null;
        }
        //通过
        if (pass !== undefined && pass === 1) {
            return <Icon style={{margin: "auto"}}  type="check-circle" theme="twoTone" twoToneColor="#52c41a"/>;
        }
        //阻塞
        if (pass !== undefined && pass === 2) {
            return <Icon style={{margin: "auto"}}  type="close-circle" theme="twoTone" twoToneColor="#ff4c26"/>;
        }
        //存疑
        if (pass !== undefined && pass === 3) {
            return <Icon style={{margin: "auto"}}  type="question-circle" theme="twoTone" twoToneColor="#eda938"/>;
        }
    };

    const nodeStyle = (nodeType) => {
        if (nodeType === ROOT) {
            return 0;
        }
        if (nodeType === PRODUCT) {
            return 1;
        }
        if (nodeType === PRODUCT_MODULE) {
            return 2;
        }
        return 3;
    };

    useEffect(() => {
        node_refs.add(self);
        return () => {
            node_refs.delete(self);
        }
    }, []);

    useEffect(() => {
        if (nodeStatus.cur_select === node.id) {
            //下面代码用来选中节点居中
            // self.current.scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});
            nDispatch(getNodeInfo(node, parent, on_left));
        }
    }, [nodeStatus.cur_select, node]);

    // 为避免事件冒泡导致干扰，点击事件放在了 dropArea div，最外层 Node div 用于阻止冒泡
    return (<div
        className={cx(common_style, specific_style[nodeStyle(node.type)], {[seleted_style]: nodeStatus.cur_select === node.id})}
        draggable={layer > 0 && nodeStatus.cur_edit !== node.id}
        data-tag={on_left ? refer.LEFT_NODE : refer.RIGHT_NODE}
        data-parent={parent.id}
        data-show-children={node.showChildren}
        id={node.id}
        ref={self}
        onClick={handlePropagation}>
        {caseVerityType(node.type)}
        {nodeStatus.cur_edit === node.id && node.type !== CASE_LABEL && node.type !== CASE_LABEL_SYSTEM &&
        node.type !== CASE_LABEL_BASE && <InputDiv node_id={node.id}>{node.text}</InputDiv>}
        <div className={drop_area} data-tag={refer.DROP_AREA} onClick={handleSelectNode}
             onDoubleClick={handleEditNode}/>
        &nbsp;&nbsp;<div style={{whiteSpace:"pre-wrap"}}>{node.text}</div>&nbsp;&nbsp;
        {casePass(node)}
        {nodeStatus.cur_edit === node.id && node.type === CASE_LABEL_SYSTEM &&
        <SystemLabelSelect node_id={node.id} labels={node.labels} value={node.text}/>}
        {nodeStatus.cur_edit === node.id && node.type === CASE_LABEL_BASE &&
        <BaseLabelSelect node_id={node.id} labels={node.labels} value={node.text}/>}
        {(layer > 0 && node.children.length > 0) &&
        <button className={cx(toggle_button, (on_left ? button_left : button_right))}
                onClick={handleToggleChildren}>{node.showChildren ? '-' : '+'}</button>}
        {(nodeStatus.cur_select === node.id && nodeStatus.select_by_click) &&
        <Toolbar layer={layer} node={node} parent={parent}/>}
    </div>);
};

export default Node;

// CSS
const style_selected_border = `
box-shadow: 0 0 0 3px #ffffff, 0 0 0 6px var(${refer.THEME_EX}); /* 双层阴影实现选中框 */
`;

const common_style = css`
display:flex;
white-space: pre-wrap;
position: relative;
min-width: 10px;
max-width: 1300px;
margin: 5px 40px;
padding: 15px;
background-color: #ffffff;
border: 1px solid var(${refer.THEME_MAIN});
border-radius: 15px;
cursor: pointer;

p {
min-height: 18px; /* 当 p 中没有内容时撑起元素 */
margin: 0;
line-height: 1.5em;
overflow-wrap: break-word;
}

&:hover {
${style_selected_border}
}

&.ondrag {
background-color: var(${refer.THEME_EX});
p {
color: #ffffff;
}
}
`;

const specific_style = [ // div&用于提高 CSS 权重
    css`
div& {
padding: 15px 20px;
font-size: 120%;
font-weight: 700;
background-color: var(${refer.THEME_DARK});
border:2px solid var(${refer.THEME_EX});
}
    `,
    css`
div& {
background-color: var(${refer.THEME_LIGHT});
}
    `,
    css`
div& {
padding: 10px 15px;
}
    `,
    css`
div& {
padding: 0 15px;
border: none;
p {
font-size: 90%;
}
}
    `
];

const seleted_style = css`
z-index: 1; /* 提高 Node 高度，防止被遮挡 */
${style_selected_border}
`;

// 兼有防止文字被选中的功能
const drop_area = css`
position: absolute;
top:0;
bottom:0;
left:0;
right:0;
`;

const toggle_button = css`
position: absolute;
top:0;
bottom: 0;
width: 20px;
height: 20px;
margin: auto 0;
padding: 0;
text-align: center;
background-color: #ffffff;
border: 1px solid #cccccc;
border-radius: 50%;
outline: none;
`;

const button_left = css`
left: -15px;
`;

const button_right = css`
right: -15px;
`;
