import React, {useContext, useEffect, useMemo, useRef} from 'react';
import {css} from 'emotion';
import {context} from '../../context';
import {setCasePassRate, setTitle} from '../../context/reducer/global/actionCreator';
import * as refer from '../../statics/refer';

const MindmapTitle = () => {
	const self = useRef();
	const {mindmap: {state: root_node}, global: {state: {title, casePassRate}, dispatch}} = useContext(context);

	const mindmap_json = useMemo(() => JSON.stringify(root_node), [root_node]);
	let passRate = 0;
	let caseNum = 0;
	let passNum = 0;
	useEffect(() => {
		countCaseNum(root_node);
		passRate = parseInt((passNum / caseNum) * 100);
		dispatch(setCasePassRate(passRate));
	}, [mindmap_json]);

	const countCaseNum = (node) => {
		node.children.map(node => {
			if (node.type === "case") {
				caseNum += 1;
				if (node.pass === 1) {
					passNum += 1;
				}
			}
			if (node.children.length > 0) {
				countCaseNum(node, caseNum, passNum);
			}
		})
	};

	const handleKeydown = event => {
		switch (event.key.toUpperCase()) {
			case 'ESCAPE':
				self.current.textContent = title;
			case 'ENTER':
				self.current.blur();
				break;
			default:
				break;
		}
	};

	const handleBlur = () => {
		let new_title = self.current.textContent.trim();
		if (new_title === '') {
			new_title = title;
		}
		if (new_title.length > 30) {
			new_title = new_title.slice(0, 30);
		}
		self.current.textContent = new_title; // contentEditable 组件内容不会被自动更新
		dispatch(setTitle(new_title));
	};

	return (<div>
			<span ref={self} className={wrapper}>{localStorage.getItem('title', title)}</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			{
				refer.DEFAULT_TITLE !== localStorage.getItem('title', title) &&
				<span className={wrapper}>通过率{casePassRate}%</span>
			}

		</div>

	);
};

export default MindmapTitle;

// CSS
const wrapper = css`
align-self: center;
padding: 0 10px; /* 两侧 padding 用于增大组件点击区域，避免将光标定位至首尾处时意外 blur */
color: var(${refer.THEME_DARK});
font-size: 20px;
font-weight: 700;
border-bottom: 2px solid transparent;
outline: none;
transition: 0.2s;

&:read-write {
cursor: edit;
}

&:focus {
border-bottom: 2px solid var(${refer.THEME_ASSIST});
}
`;
