import React, {useEffect, useRef} from 'react';
import {css} from 'emotion';
import useMindmap from '../../../customHooks/useMindmap';
import {handlePropagation} from '../../../methods/assistFunctions';

const InputDiv = ({node_id, children}) => {
	const self = useRef();
	const {changeText, selectNode} = useMindmap();

	const handleKeydown = event => {
		switch (event.key.toUpperCase()) {
			case 'ESCAPE':
				self.current.textContent = children;
			case 'ENTER':
				if (event.altKey){
					let content = self.current.textContent;
                    let sel = window.getSelection();
                    let range = sel.getRangeAt(0);
                    let rangeStartOffset = range.startOffset;
                    var firstContent = content.substring(0,rangeStartOffset);
                    var lastContent = content.substring(rangeStartOffset,content.length);
                    if (self.current.firstChild.length === rangeStartOffset){
						self.current.innerHTML = firstContent + "\r\n\r\n";
					}else {
						self.current.innerHTML = firstContent + "\r\n" + lastContent;
					}
                    sel.removeAllRanges();
                    range = document.createRange();
                    range.selectNodeContents(self.current.firstChild);
                    range.setStart(self.current.firstChild, rangeStartOffset + 1);
                    range.setEnd(self.current.firstChild, rangeStartOffset + 1);
                    sel.addRange(range);
                }else {
					self.current.blur();
				}
				break;
			default:
				break;
		}
	};

	const handleBlur = () => {
		changeText(node_id, self.current.outerText, window.__data.staffCode);
		selectNode(node_id);
	};

	useEffect(() => {
		self.current.focus();
		const selection = document.getSelection();
		selection.selectAllChildren(self.current);
	}, []);

	return (<div className={wrapper} ref={self} contentEditable="true" suppressContentEditableWarning="true"
				 // onClick={handlePropagation} onBlur={handleBlur}>{children}</div>);
				 onClick={handlePropagation} onKeyDown={handleKeydown} onBlur={handleBlur}>{children}</div>);
};

export default InputDiv;

// CSS
const wrapper = css`
white-space: pre-wrap;
position: absolute;
top: 0;
bottom: 0;
left: 0;
right: 0;
width: fit-content;
min-width: 1em;
height: fit-content;
margin: auto;
padding: 10px;
color: #333333;
background-color: #ffffff;
box-shadow: 0 0 20px #aaaaaa;
border-radius: 5px;
outline: none;
z-index: 3;
user-select: text;
`;
