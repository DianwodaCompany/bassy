import style from "../../case.less";
import {Avatar, Badge, Button, Card, message, Switch, Table, Icon, Popconfirm, Checkbox} from "antd";
import ImportFromBaseCase from "./importFromBaseCase";
import ConflictCaseList from "./conflictCaseList";
import React, {useContext, useEffect, useState} from "react";
import {
    deleteCase,
    getPagedBaseCase,
    getProgramRequireInfo,
    getXmindBaseCase,
    pullBaseCaseToProgramCase,
    pushProgramCaseToBaseCase
} from "../../../../apis";
import {addConflictCaseList, updateProgramCaseInfo, viewConflictList} from "../../actions";
import ConflictCaseSolve from "./conflictCaseSolve";
import {context} from "./provider";

const ButtonGroup = Button.Group;

const ProgramCaseDetail = ({props}) => {

    const {state, dispatch} = useContext(context);
    const {caseList, requireId, requireName, importModalView, pageNum, pageSize, total, caseListView, editByMe, notPass} = state.programCaseInfo;
    const {authResourceCodeList} = props;

    useEffect(() => {
        async function init() {
            await initCaseList();
            if(localStorage.getItem("case_param") !== null) {
                dispatch(updateProgramCaseInfo({
                    editByMe: JSON.parse(localStorage.getItem("case_param")).editByMe,
                    notPass: JSON.parse(localStorage.getItem("case_param")).notPass
                }));
            }
        }
        init();
    }, [editByMe, notPass]);

    const initCaseList = async () => {
        const requireId = props.match.params.requireId;
        const res = await getProgramRequireInfo(requireId);
        let lastEditedBy = editByMe ? props.staffInfo.code : null;
        const resCase = await getPagedBaseCase({family: 1, requireId, pageNum: 1, pageSize: 10, lastEditedBy, notPass});
        dispatch(updateProgramCaseInfo({
            requireId,
            requireName: res.data.requireName,
            caseList: resCase.data.list,
            pageNum: resCase.data.currentPage,
            pageSize: resCase.data.pageSize,
            total: resCase.data.totalCount
        }));
    };
    const addNewProgramCase = () => {
        props.history.push({
            pathname: '/case/programCase/' + requireId + '/caseDetail',
            state: {add: true, programCase: true, requireId}
        })
    };

    const editProgramCase = (item) => {
        props.history.push({
            pathname: '/case/programCase/' + requireId + '/caseDetail',
            state: {edit: true, programCase: true, caseId: item.id}
        })
    };

    const deleteProgramCase = async (item) => {
        await deleteCase({id: item.id});
        await initCaseList();
    };

    //获取用例列表
    const requireCaseList = async (pageNum = 1) => {
        let lastEditedBy = editByMe ? props.staffInfo.code : null;
        const res = await getPagedBaseCase(Object.assign({}, {family: 1, requireId, lastEditedBy, notPass}, {
            pageNum,
            pageSize: 10
        }));
        dispatch(updateProgramCaseInfo({
            caseList: res.data.list,
            pageNum: res.data.currentPage,
            pageSize: res.data.pageSize,
            total: res.data.totalCount
        }));
    };

    const changePageNum = async (pageNum) => {
        await requireCaseList(pageNum);
    };

    const viewProgramCaseMindMap = async () => {
        let lastEditedBy = editByMe ? props.staffInfo.code : null;
        const param = {family: 1, requireId, editByMe, lastEditedBy, notPass};
        localStorage.setItem("case_param", JSON.stringify(param));
        localStorage.setItem("title", requireId + requireName);
        const res = await getXmindBaseCase(param);
        if (res.data !== null) {
            localStorage.setItem('mindmap', JSON.stringify(res.data));
            props.history.push({
                pathname: '/case/programCase/' + requireId + '/caseMindView'
            })
        } else {
            dispatch(updateProgramCaseInfo({
                caseListView: true
            }));
        }
    };

    const pullBaseCase = async (item) => {
        const res = await pullBaseCaseToProgramCase({programCaseId: item.id});
        if (res.data.length === 0) {
            await initCaseList();
            message.success("拉取最新测试用例成功！");
        } else {
            dispatch(viewConflictList(true));
            dispatch(addConflictCaseList(res.data));
        }
    };
    const pushBaseCase = async (item) => {
        const res = await pushProgramCaseToBaseCase({programCaseId: item.id});
        if (res.data.length === 0) {
            await initCaseList();
            message.success("合并测试用例成功！");
        } else {
            dispatch(addConflictCaseList(res.data));
            dispatch(viewConflictList(true));
        }
    };
    const batchPullBaseCase = async () => {
        let lastEditedBy = editByMe ? props.staffInfo.code : null;
        const res = await pullBaseCaseToProgramCase({requireId, lastEditedBy, notPass});
        if (res.data.length === 0) {
            await initCaseList();
            message.success("拉取最新测试用例成功！");
        } else {
            dispatch(addConflictCaseList(res.data));
            dispatch(viewConflictList(true));
        }

    };
    const batchPushBaseCase = async () => {
        let lastEditedBy = editByMe ? props.staffInfo.code : null;
        const res = await pushProgramCaseToBaseCase({requireId, lastEditedBy, notPass});
        if (res.data.length === 0) {
            await initCaseList();
            message.success("合并测试用例成功！");
        } else {
            dispatch(addConflictCaseList(res.data));
            dispatch(viewConflictList(true));
        }
    };

    const importFromBase = () => {
        dispatch(updateProgramCaseInfo({importModalView: true}));
    };

    const cancelImport = () => {
        dispatch(updateProgramCaseInfo({importModalView: false}));
    };

    const editByChanged = async () => {
        if(localStorage.getItem("case_param") !== null) {
            let lastEditedBy = !editByMe ? props.staffInfo.code : null;
            const param = {family: 1, requireId, editByMe: !editByMe, lastEditedBy, notPass};
            localStorage.setItem("case_param",JSON.stringify(param));
        }
        dispatch(updateProgramCaseInfo({editByMe: !editByMe}));
    };

    const notPassChanged = async () => {
        if(localStorage.getItem("case_param") !== null) {
            let lastEditedBy = editByMe ? props.staffInfo.code : null;
            const param = {family: 1, requireId, editByMe, lastEditedBy, notPass: !notPass};
            localStorage.setItem("case_param",JSON.stringify(param));
        }
        dispatch(updateProgramCaseInfo({notPass: !notPass}));
    };

    const columns = [
        {
            title: '用例名称',
            key: 'title',
            dataIndex: 'title',
            width: '300px',
        },
        {
            title: '归属产品',
            key: 'productName',
            dataIndex: 'productName'
        },
        {
            title: "优先级(P)",
            dataIndex: "pri",
            key: "pri",
            render: pri => {
                let backgroundColor = '';
                if (pri === 1) {
                    backgroundColor = '#f5222d';

                } else if (pri === 2) {
                    backgroundColor = '#faad14';

                } else {
                    backgroundColor = '#52c41a';
                }
                return <Avatar style={{backgroundColor}} size="small">{pri}</Avatar>
            }
        },
        {
            title: "最后更新人",
            dataIndex: "lastEditedBy",
            key: "lastEditedBy"
        },
        {
            title: '版本',
            key: 'version',
            dataIndex: 'version'
        },
        {
            title: '来源',
            key: 'origin',
            render: item => item.parentCase == null ? '新建' : item.baseVersion > item.version ?
                <Badge count={<Icon type="cloud-download" style={{color: '#faad14', fontSize: '8px'}}/>}><span
                    style={{marginRight: '6px'}}>{'基线'}</span></Badge> : '基线'
        },
        {
            title: '合并基线',
            key: 'pushed',
            render: item => item.pushed === 1 ? '已合并' :
                <Badge count={<Icon type="cloud-upload" style={{color: 'red', fontSize: '8px'}}/>}><span
                    style={{marginRight: '6px'}}>{'未合并'}</span></Badge>
        },
        {
            title: '操作',
            key: 'action',
            render: item => (
                authResourceCodeList &&
                <span className={style["table-operation"]}>
                        <a onClick={() => editProgramCase(item)}>编辑</a>
						<Popconfirm title="删除不可恢复，确认删除?" onConfirm={() => deleteProgramCase(item)} okText="确认"
                                    cancelText="取消">
							<a>删除</a>
						</Popconfirm>
                        <a onClick={() => pushBaseCase(item)}>合并基线</a>
                        <a onClick={() => pullBaseCase(item)}>拉取基线</a>
                </span>
            ),
        }
    ];
    return <Card title={requireId + requireName} extra={
        <div className={style["table-operation"]}>
            <Checkbox checked={editByMe} onChange={editByChanged}>由我创建</Checkbox>
            <Checkbox checked={notPass} onChange={notPassChanged}>未通过</Checkbox>
            <Switch checkedChildren="list" unCheckedChildren="CMind" defaultChecked checked={caseListView}
                    onChange={viewProgramCaseMindMap} style={{marginLeft: 15}}/>
            {
                authResourceCodeList &&
                <ButtonGroup>
                    <Button onClick={importFromBase}>导入</Button>
                    <Button onClick={() => addNewProgramCase()}>新增</Button>
                    <Button onClick={() => batchPullBaseCase()}>拉取基线</Button>
                    <Button onClick={() => batchPushBaseCase()}>合并基线</Button>
                </ButtonGroup>
            }

        </div>
    }>
        <Table
            rowKey={"id"}
            columns={columns}
            dataSource={caseList}
            pagination={{
                onChange: changePageNum,
                total,
                current: pageNum,
                pageSize
            }}/>
        {
            importModalView &&
            <ImportFromBaseCase
                require={requireId}
                view={importModalView}
                onCancel={cancelImport}
                refreshProgramCase={require => requireCaseList()}
            />

        }
        <ConflictCaseList/>
        <ConflictCaseSolve initCaseList={() => initCaseList()}/>
    </Card>
};

export default ProgramCaseDetail;
