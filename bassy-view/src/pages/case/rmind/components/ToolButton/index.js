import React from 'react';
import {Avatar, Icon} from "antd"
import {css, cx} from 'emotion';
import * as refer from '../../statics/refer';
import {handlePropagation} from '../../methods/assistFunctions'; // 用于禁用按钮点击。如果使用 button 的 disable 属性实现，会由于点击事件冒泡导致 Toolbar 被隐藏

const ToolButton = ({icon, onClick, children, disabled, avatar}) => {
    return (
        <button onClick={disabled ? handlePropagation : onClick} className={cx(wrapper, {[disabled_style]: disabled})}>
            {
                icon !== undefined && <Icon type={icon}/>
            }
            {
                avatar !== undefined && avatar === 1 &&
                <Avatar style={{backgroundColor:'#f5222d'}} size="small">{avatar}</Avatar>
            }
            {
                avatar !== undefined && avatar === 2 &&
                <Avatar style={{backgroundColor:'#faad14'}} size="small">{avatar}</Avatar>
            }
            {
                avatar !== undefined && avatar === 3 &&
                <Avatar style={{backgroundColor:'#52c41a'}} size="small">{avatar}</Avatar>
            }

            <span>{children}</span>
        </button>);
};

export default ToolButton;

// CSS
const wrapper = css`
margin: 0 0.12em;
padding: 0 0.24em;
/* margin 用于增加间隔，padding 用于增大可点击区域 */
background-color: transparent;
border: none;
outline: none;
cursor: pointer;

i {
display: block;
margin-bottom: 0.12em;
font-size: 100%;
}
span {
display: block;
font-size: 25%;
}

&:active {
transform: scale(0.95);
}

&:hover {
color: var(${refer.THEME_ASSIST});
}
`;

const disabled_style = css`
&, &:hover {
color: #cccccc;
}

cursor: not-allowed;
`;
