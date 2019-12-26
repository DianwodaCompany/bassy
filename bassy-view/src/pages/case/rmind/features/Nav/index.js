import React, {useContext, useState} from 'react';
import {css} from 'emotion';
import {context} from '../../context';
import {context as caseContext} from '../../../views/programCase/provider';
import useMindmap from '../../customHooks/useMindmap';
import useHistory from '../../customHooks/useHistory';
import * as refer from '../../statics/refer';
import * as popupType from '../../components/Popup/common/popupType';
import {downloadFile, handlePropagation} from '../../methods/assistFunctions'; // 防止 Mindmap 中的选中状态由于冒泡被清除
import ToolButton from '../../components/ToolButton';
import MindmapTitle from '../../components/MindmapTitle';
import Popup from '../../components/Popup';
import {getXmindBaseCase, updateXmindBaseCase} from "../../../../../apis/index";
import {message, Progress} from "antd";
import {addConflictCaseList, viewConflictList} from "../../../actions";
import ConflictCaseList from "../../../views/programCase/conflictCaseList";
import ConflictCaseSolve from "../../../views/programCase/conflictCaseSolve";
import {haveCommonAuth, haveGuestAuth} from "../../../../../utils";

const Nav = ({authResourceCodeList}) => {
    const [popup, setPopup] = useState(popupType.NONE);
    const [save, setSave] = useState({saving: false, saveStatus: "active", savePercent: 90});
    const {mindmap: {state: mindmap}, history: {state: history}, global: {state: {title}}} = useContext(context);
    const {state, dispatch} = useContext(caseContext);
    const {expandAll, setMindmap} = useMindmap();
    const {undoHistory, redoHistory} = useHistory();
    const {saving, saveStatus, savePercent} = save;

    const handleClosePopup = () => {
        setPopup(popupType.NONE);
    };

    const handleNewFile = () => {
        setPopup(popupType.NEW);
    };

    const handleDownload = () => {
        const url = `data:text/plain,${encodeURIComponent(JSON.stringify(mindmap))}`;
        downloadFile(url, `${title}.json`);
    };

    const handleOpenFile = () => {
        setPopup(popupType.OPEN);
    };

    const handleExport = () => {
        setPopup(popupType.EXPORT);
    };

    const handleTheme = () => {
        setPopup(popupType.THEME);
    };

    const handleFullscreen = () => {
        let element = document.getElementById("caseRoot");
        const prime = element.requestFullscreen();
    };

    const handleUndo = () => {
        undoHistory();
    };

    const handleRedo = () => {
        redoHistory();
    };

    const handleExpand = () => {
        expandAll(refer.ROOT_NODE_ID);
    };

    const saveCase = async () => {
        setSave({saving: true, savePercent: 90, saveStatus: "active"});
        const param = JSON.parse(localStorage.getItem('mindmap'));
        const res = await updateXmindBaseCase(param);
        if (res.errCode === 0) {
            setSave({saving: true, savePercent: 90, saveStatus: "exception"});
            setTimeout(function delay() {
                setSave({saving: false});
            }, 1000);
            return false;
        }
        const {conflictCases, testCaseXmindNodeVO} = res.data;
        if (conflictCases.length !== 0) {
            dispatch(addConflictCaseList(conflictCases));
            dispatch(viewConflictList(true));
            setSave({saving: true, savePercent: 90, saveStatus: "exception"});
            setTimeout(function delay() {
                setSave({saving: false});
            }, 1000);
            return false;
        }
        localStorage.setItem('mindmap', JSON.stringify(testCaseXmindNodeVO));
        setMindmap(testCaseXmindNodeVO, true);
        setSave({saving: true, savePercent: 100});
        setTimeout(function delay() {
            setSave({saving: false});
        }, 1000);
        return true;
    };

    const refreshCase = async () => {
        const caseParam = JSON.parse(localStorage.getItem('case_param'));
        const res = await getXmindBaseCase(caseParam);
        if (res.errCode === 1) {
            localStorage.setItem('mindmap', JSON.stringify(res.data));
            setMindmap(res.data, true);
        }
    };


    const callBack = async () => {
        const result = await saveCase();
        if (result){
            setTimeout(function delay() {
                window.history.back();
            }, 1100);
        }
    };


    return (<div>
        <nav className={wrapper}>
            <section className={section} onClick={handlePropagation}>
                {/*<ToolButton icon={'add-item-alt'} onClick={handleNewFile}>新建</ToolButton>*/}
                {/*<ToolButton icon={'folder-open'} onClick={handleOpenFile}>打开</ToolButton>*/}
                {/*<ToolButton icon={"download"} onClick={handleDownload}>下载至本地</ToolButton>*/}
                {/*<ToolButton icon={'export'} onClick={handleExport} disabled={true}>导出</ToolButton>*/}
                <ToolButton icon={'bg-colors'} onClick={handleTheme}>主题</ToolButton>
                <ToolButton icon={'fullscreen'} onClick={handleFullscreen}>全屏</ToolButton>
            </section>
            <section className={section}>
                <MindmapTitle/>
            </section>
            <section className={section} onClick={handlePropagation}>
                <ToolButton icon={'undo'} disabled={history.undo.length === 0} onClick={handleUndo}>撤销</ToolButton>
                <ToolButton icon={'redo'} disabled={history.redo.length === 0} onClick={handleRedo}>重做</ToolButton>
                <ToolButton icon={'fullscreen'} onClick={handleExpand}>展开所有节点</ToolButton>
                {
                    authResourceCodeList && !haveGuestAuth(authResourceCodeList) &&
                    <ToolButton icon={'save'} onClick={saveCase}>保存用例</ToolButton>
                }
                {
                    authResourceCodeList && !haveGuestAuth(authResourceCodeList) &&
                    <ToolButton icon={'arrow-right'} onClick={callBack}>保存并返回</ToolButton>
                }
            </section>
            {popup !== popupType.NONE &&
            <Popup type={popup} handleClosePopup={handleClosePopup} handleDownload={handleDownload}/>}
        </nav>
        {saving &&
        <nav className={wrapper}>
            <Progress percent={savePercent} status={saveStatus}/>
        </nav>
        }
        <ConflictCaseList/>
        <ConflictCaseSolve initCaseList={() => refreshCase()}/>
    </div>);
};

export default Nav;

// CSS
const wrapper = css`
display: flex;
justify-content: space-between;
top:0;
left:0;
right:0;
height: 56px;
padding: 0 50px;
font-size: 25px;
background-color: #ffffff;
box-shadow: 0 0px 2px #aaaaaa;
z-index: 10;
`;

const section = css`
display: flex;
`;
