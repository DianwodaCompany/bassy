import React from 'react';
import {Card, Input, Radio, Table, Badge} from 'antd';
import moment from "moment/moment";
import {getProCaseIndex} from '../../../../apis';

const RadioGroup = Radio.Group;
const RadioButton = Radio.Button;
const {Search} = Input;

export default class ProgramCaseIndex extends React.Component {

    state = {
        proStatus: 'processing',
        keyword: "",
        proData: [{
            id: ''
        }],
        pageNum: 1,
        pageSize: 10,
        total: 0,
    };

    proCaseSearchData = () => {
        const data = localStorage.getItem("proCaseSearchData");
        return data === null ? "" : data;
    };

    getProCaseIndex = async (pageNum = 1, keyword, status) => {
        let param = Object.assign({pageNum, pageSize: 10, keyword, status});
        let resp = await getProCaseIndex(param);
        const {currentPage, pageSize, list, totalCount} = resp.data;
        this.setState({
            proData: list,
            pageSize,
            total: totalCount,
            pageNum: currentPage,
        });
    };

    changePage = page => {
        this.setState({pageNum: page});
        this.getProCaseIndex(page, this.proCaseSearchData(), this.state.proStatus);
    };

    componentWillMount() {
        this.getProCaseIndex(this.state.pageNum, this.proCaseSearchData(), this.state.proStatus);
    };

    onRadioChange = (e) => {
        this.setState({proStatus: e.target.value});
        this.getProCaseIndex(this.state.pageNum, this.proCaseSearchData(), e.target.value);
    };

    onSearch = value => {
        localStorage.setItem("proCaseSearchData", value);
        this.getProCaseIndex(this.state.pageNum, value, this.state.proStatus);
    };

    manageCases = (record) => {
        this.props.history.push({
            pathname: '/case/programCase/' + record.requireId
        })
    };

    render() {
        const {proStatus, proData, keyword, pageNum, pageSize, total} = this.state;
        const expandedRowRender = pro => {
            const columns = [
                {title: '需求id', dataIndex: 'requireId',},
                {
                    title: '需求名称',
                    key: 'requireName',
                    render: record => (
                        <Badge count={record.unpushCase}><span style={{marginRight: '10px'}}>{record.requireName}</span></Badge>)
                },
                {title: '用例数', dataIndex: 'totalCase',},
                {
                    title: '执行率',
                    key: 'runPercent',
                    render: record => (record.runPercent === null ? '-' : (record.runPercent * 100).toFixed(1) + '%')
                },
                {
                    title: '通过率',
                    key: 'passPercent',
                    render: record => (record.passPercent === null ? '-' : (record.passPercent * 100).toFixed(1) + '%')
                },
                {
                    title: '操作',
                    dataIndex: 'operation',
                    key: 'operation',
                    render: (text, record) => (
                        record.requireId === '' ? '' :
                            <span className="table-operation">
                            <a onClick={() => this.manageCases(record)}>管理用例</a>
                        </span>
                    ),
                },
            ];
            if (JSON.stringify(pro.requireInfoVOList) === '[]') {
                pro.requireInfoVOList = [{requireId: '', requireName: "该项目没有关联的需求", runPercent: '', passPercent: ''}]
            }
            return <Table columns={columns} dataSource={pro.requireInfoVOList} pagination={false}
                          rowKey={(record, index) => 'require' + index}/>;
        };

        const proColumns = [
            {title: 'id', dataIndex: 'id', key: 'id'},
            {title: '项目名称', dataIndex: 'programName', key: 'programName'},
            {
                title: "创建时间",
                dataIndex: "createTm",
                render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
            },
            {
                title: "开始时间",
                dataIndex: "startTime",
                render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
            },
            {
                title: "结束时间",
                dataIndex: "endTime",
                render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
            },
        ];

        return (
            <Card title="项目用例管理" extra={
                <div>
                    <RadioGroup onChange={this.onRadioChange} defaultValue={proStatus}>
                        <RadioButton value='processing'>进行中</RadioButton>
                        <RadioButton value='unpush'>待合基线</RadioButton>
                        <RadioButton value='end'>已完成</RadioButton>
                    </RadioGroup>
                    <Search
                        placeholder="项目名称 搜搜"
                        defaultValue={keyword}
                        onSearch={this.onSearch}
                        style={{width: 300, marginLeft: 10}}
                    />
                </div>
            }>
                <Table
                    rowKey={(record, index) => 'pro' + index}
                    columns={proColumns}
                    expandedRowRender={record => expandedRowRender(record)}
                    dataSource={proData}
                    defaultExpandedRowKeys={['pro0']}
                    pagination={{
                        current: pageNum,
                        total,
                        pageSize,
                        onChange: this.changePage
                    }}
                />
            </Card>
        )
    }

}
