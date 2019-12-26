import React, {useRef} from 'react';
import {css} from 'emotion';
import Mindmap from '../Mindmap';

const Main = () => {
    const self = useRef();
    return (
        <div id="rMindRoot" ref={self} className={wrapper}>
            <Mindmap container_ref={self} />
        </div>
        );
};

export default Main;

// CSS
const wrapper = css`
// margin: 56px 0 0;
height:calc(100% - 56px);
overflow: scroll;
background:#F0F2F5;
`;
