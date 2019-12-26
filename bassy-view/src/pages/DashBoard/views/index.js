import React from 'react'
import {connect} from 'react-redux'
import {Row, Col, Card, Spin, Table, Radio, Button} from 'antd'
import {
  getTaskAbnormalCollect,
  getTaskAbnormalCountDTO,
  getProjectNumCount,
  getWorkHourTrend,
  } from "../../../apis";
import moment from "moment";
import numeral from 'numeral';
import styles from './index.less';
import {Ellipsis,Charts} from 'ant-design-pro';
import {Axis, Chart, Coord, Geom, Label, Legend, Tooltip} from "bizcharts";
import * as G2 from "bizcharts";
import DataSet from '@antv/data-set';
import ExportJsonExcel from 'js-export-excel';
import {haveAdminAuth} from "../../../utils";

const topColResponsiveProps = {
    xs: 24,
    sm: 12,
    md: 12,
    lg: 12,
    xl: 6,
    style: { marginBottom: 24 },
};

const topColResponsiveProps2 = {
    xs: 24,
    sm: 12,
    md: 12,
    lg: 12,
    xl: 12,
};

const RadioButton = Radio.Button;
const RadioGroup = Radio.Group;

@connect(state => ({
  authResourceCodeList: state.common.authResourceCodeList,
}))
export default class DashBoard extends React.Component {
    state = {
        loading: false,
        taskAbnormalList: [],
        abnormalCountType: 'all',
        projectNumCountDTO:{
            totalCount:0,
            processingCount:0,
            initCount:0,
            endCount:0
        },
      allTaskAbnormalCountDTO: {},
      last7DaysTaskAbnormalCountDTO: {},
    };

    columns = [/*{
        title: '编号',
        dataIndex: 'id',
        key: 'id',
    },*/ {
        title: '项目',
        dataIndex: 'projectName',
        render: (text, record, index) => {
            return "(" + record.projectId + ")" + record.projectName;
        }
    },  {
        title: '需求',
        dataIndex: 'storyTitle',
        render: (text, record, index) => {
            return "(" + record.storyId + ")" + record.storyTitle;
        }
    },{
        title: '任务',
        dataIndex: 'taskName',
        render: (text, record, index) => {
            return "(" + record.taskId + ")" + record.taskName;
        }
    }, {
        title: '任务状态',
        dataIndex: 'taskStatus',
        filters: [{
            text: '待排期',
            value: "待排期",
        }, {
            text: '进行中',
            value: "进行中",
        }, {
            text: '已暂停',
            value: "已暂停",
        }, {
            text: '已关闭',
            value: "已关闭",
        }, {
            text: '已结束',
            value: "已结束",
        }],
        onFilter: (value, record) => {
            return record.taskStatus === value;
        },
    },{
        title: '一级原因',
        dataIndex: 'reasonTeamName',
    }, {
        title: '二级原因',
        dataIndex: 'reasonTypeName',
    }, {
        title: '严重等级',
        dataIndex: 'reasonLevel',
        sorter: (a, b) => {
            let aLevel = parseInt(a.reasonLevel.substr(1));
            let bLevel = parseInt(b.reasonLevel.substr(1));
            return aLevel - bLevel;
        }
    }, {
        title: '异常说明',
        dataIndex: 'reasonDetail',
        width: 200,
        render: (text, record, index) => {
            return <Ellipsis length={60} tooltip>{text}</Ellipsis>;
        }
    }, {
        title: '异常类型',
        dataIndex: 'abnormalType',
        render: (text, record, index) => {
            if(record.abnormalType === 1){
                return "任务进度异常";
            }
        }
    },  {
        title: '最新状态',
        dataIndex: 'currentNormalState',
        filters: [{
            text: '异常',
            value: "0",
        }, {
            text: '正常',
            value: "1",
        }],
        filterMultiple: false,
        onFilter: (value, record) => {
            return record.currentNormalState.toString() === value;
        },
        render: (text, record, index) => {
            return record.currentNormalState === 0 ? <span className={styles.abnormal}>异常</span> : "正常";
        }
    }, {
        title: '测试人员',
        dataIndex: 'testerName',
    }, {
        title: '责任人',
        dataIndex: 'abnormalOwnerName',
    }, {
        title: '更新时间',
        dataIndex: 'modifyTime',
        render: (text, record, index) => {
            return moment(text).format("YYYY-MM-DD HH:mm:ss");
        }
    }];

    componentWillMount() {
        this.getTaskAbnormalCollect();
        this.getProjectNumCount();
        this.getTaskAbnormalCountDTO();
        this.getLast7DaysTaskAbnormalCountDTO();
        // this.getWorkHourTrend();
    }

    getWorkHourTrend = async () => {
        const monthArray = [];
        let start = "2019-01-01";
        while (moment(start).isBefore(moment())) {
            monthArray.push(start);
            start = moment(start).add(1, 'months').format("YYYY-MM-DD");
        }
        const workHourTrendDTOList = await getWorkHourTrend(monthArray);
        this.setState(
            {
                workHourTrendDTOList: workHourTrendDTOList.data
            }
        );
    };

    getTaskAbnormalCollect = async () => {
        const formData = new FormData();
        formData.append("startTm", moment().add(-7, 'days').format("YYYY-MM-DD"));
        formData.append("endTm", moment().add(1,'days').format("YYYY-MM-DD"));
        const taskAbnormalCollect = await getTaskAbnormalCollect(formData);
        let filteredTaskAbnormalList = [];
        let taskAbnormalList = taskAbnormalCollect.data.taskAbnormalDTOList;
        for(let taskAbnormal of taskAbnormalList){
            if(taskAbnormal.abnormalType === 1 && taskAbnormal.reasonTypeName !== "填写有误"){
                filteredTaskAbnormalList.push(taskAbnormal)
            }
        }
        this.setState(
            {
                taskAbnormalList: filteredTaskAbnormalList
            }
        );
    };

    getTaskAbnormalCountDTO = async () => {
        const formData = new FormData();
        formData.append("startTm", moment("2019-01-06").format("YYYY-MM-DD"));
        formData.append("endTm", moment().add(1,'days').format("YYYY-MM-DD"));
        const allTaskAbnormalCountDTO = await getTaskAbnormalCountDTO(formData);
        this.state.allTaskAbnormalCountDTO = allTaskAbnormalCountDTO.data;

    };

    getLast7DaysTaskAbnormalCountDTO = async () => {
        const formData = new FormData();
        formData.append("startTm", moment().add(-7, 'days').format("YYYY-MM-DD"));
        formData.append("endTm", moment().add(1,'days').format("YYYY-MM-DD"));
        const last7DaysTaskAbnormalCountDTO = await getTaskAbnormalCountDTO(formData);
        this.state.last7DaysTaskAbnormalCountDTO = last7DaysTaskAbnormalCountDTO.data;
    };

    handleChangeAbnormalCountType = e => {
        this.setState({abnormalCountType: e.target.value});
    };

    getProjectNumCount = async () => {
        const projectNumCountDTO = await getProjectNumCount();
        this.setState(
            {
                projectNumCountDTO: projectNumCountDTO.data
            }
        );
    };

    downloadExcel = () => {
        const { taskAbnormalList } = this.state;
        var option={};
        let dataTable = [];
        if (taskAbnormalList) {
            for (let i in taskAbnormalList) {
                let obj = {
                    '项目名称': taskAbnormalList[i].projectName,
                    '需求': '(' + taskAbnormalList[i].storyId + ')' + taskAbnormalList[i].storyTitle,
                    '任务': '(' + taskAbnormalList[i].taskId + ')' + taskAbnormalList[i].taskName,
                    '任务状态': taskAbnormalList[i].taskStatus,
                    '一级原因': taskAbnormalList[i].reasonTeamName,
                    '二级原因': taskAbnormalList[i].reasonTypeName,
                    '严重等级': taskAbnormalList[i].reasonLevel,
                    '异常说明': taskAbnormalList[i].reasonDetail,
                    '最新状态': taskAbnormalList[i].currentNormalState === 1 ? '正常' : '异常',
                    '测试人员': taskAbnormalList[i].testerName,
                    '责任人': taskAbnormalList[i].abnormalOwnerName,
                    '更新时间': moment(taskAbnormalList[i].modifyTime).format("YYYY-MM-DD HH:mm:ss"),
                };
                dataTable.push(obj);
            }
        }
        var startTm = moment().add(-7, 'days').format("YYYYMMDD");
        var endTm = moment().format("YYYYMMDD");
        option.fileName = '本周异常' + '(' +startTm + '--' + endTm + ')';
        option.datas=[{
                sheetData: dataTable,
                sheetName: startTm + '-' + endTm,
                sheetHeader:['项目名称','需求','任务','任务状态','一级原因','二级原因','严重等级','异常说明','最新状态','测试人员','责任人','更新时间'],
            }];

        var toExcel = new ExportJsonExcel(option); //new
        toExcel.saveExcel();
    };

    render() {
        const {loading, taskAbnormalList,allTaskAbnormalCountDTO,workHourTrendDTOList, projectNumCountDTO, last7DaysTaskAbnormalCountDTO, abnormalCountType} = this.state;
        let taskAbnormalCountArray = [], taskAbnormalCountDTO = {};
        if(abnormalCountType === 'all') {
          taskAbnormalCountDTO = allTaskAbnormalCountDTO;
        } else {
          taskAbnormalCountDTO = last7DaysTaskAbnormalCountDTO;
        }
        if(taskAbnormalCountDTO){
            Object.keys(taskAbnormalCountDTO).forEach(function(key){
                var type;
                if(key !== "taskAbnormalCountDetailDTOList"){
                    if(key === "productCount"){
                        type = "产品问题";
                    }else if(key === "developCount"){
                        type = "开发问题";
                    }else if(key === "testCount"){
                        type = "测试问题";
                    }
                    else if(key === "operationCount"){
                        type = "运维问题";
                    }
                    else if(key === "dbaCount"){
                        type = "DBA问题";
                    }
                    else if(key === "otherCount"){
                        type = "其他";
                    }
                    if(taskAbnormalCountDTO[key] !== 0){
                        taskAbnormalCountArray.push({
                            type: type,
                            value: taskAbnormalCountDTO[key]
                        })
                    }
                }
            });

            const sliceNumber = 0.01; // 自定义 other 的图形，增加两条线
            G2.Shape.registerShape("interval", "sliceShape", {
                draw(cfg, container) {
                    const points = cfg.points;
                    let path = [];
                    path.push(["M", points[0].x, points[0].y]);
                    path.push(["L", points[1].x, points[1].y - sliceNumber]);
                    path.push(["L", points[2].x, points[2].y - sliceNumber]);
                    path.push(["L", points[3].x, points[3].y]);
                    path.push("Z");
                    path = this.parsePath(path);
                    return container.addShape("path", {
                        attrs: {
                            fill: cfg.color,
                            path: path
                        }
                    });
                }
            });
        }
        let dv;
        const cols = {
            month: {
                range: [0, 1]
            }
        };
        if(workHourTrendDTOList){
            const ds = new DataSet();
            dv = ds.createView().source(workHourTrendDTOList);
            dv.transform({
                type: "fold",
                fields: ["shangjia", "qishou","zhicheng","cekai"],
                // 展开字段集
                key: "group",
                // key字段
                value: "hour" // value字段
            });
            console.log(dv);
        }

        return (
            <div>
                <Row gutter={24}>
                    <Col {...topColResponsiveProps}>
                        <Charts.ChartCard
                            bordered={false}
                            title="项目总数"
                            loading={loading}
                            total={numeral(projectNumCountDTO.totalCount).format('0,0')}
                            contentHeight={46}
                        >
                        </Charts.ChartCard>
                    </Col>
                    <Col {...topColResponsiveProps}>
                        <Charts.ChartCard
                            bordered={false}
                            title="运行中的项目数"
                            loading={loading}
                            total={numeral(projectNumCountDTO.processingCount).format('0,0')}
                            contentHeight={46}
                        >
                        </Charts.ChartCard>
                    </Col>
                    <Col {...topColResponsiveProps}>
                        <Charts.ChartCard
                            bordered={false}
                            title="未开始的项目数"
                            loading={loading}
                            total={numeral(projectNumCountDTO.initCount).format('0,0')}
                            contentHeight={46}
                        >
                        </Charts.ChartCard>
                    </Col>
                    <Col {...topColResponsiveProps}>
                        <Charts.ChartCard
                            bordered={false}
                            title="已完成的项目数"
                            loading={loading}
                            total={numeral(projectNumCountDTO.endCount).format('0,0')}
                            contentHeight={46}
                        >
                        </Charts.ChartCard>
                    </Col>
                </Row>
                <Row gutter={24}>
                    <Col {...topColResponsiveProps2}>
                        <Card title="各小组人均工作量趋势">
                            {workHourTrendDTOList && <Chart onTooltipChange={(ev) => {
                                    var items = ev.items;
                                    for(let item of items){
                                        if(item.name === "shangjia"){
                                            item.name = "商家";
                                        }else if(item.name === "qishou"){
                                            item.name = "骑手";
                                        }else if(item.name === "zhicheng"){
                                            item.name = "支撑";
                                        }else if(item.name === "cekai"){
                                            item.name = "测开";
                                        }
                                    }
                                }
                            } height={400} data={dv} scale={cols} forceFit>
                                <Legend
                                    itemFormatter={(val) => {
                                        if(val === "shangjia"){
                                            return "商家";
                                        }else if(val === "qishou"){
                                            return "骑手";
                                        }else if(val === "zhicheng"){
                                            return "支撑";
                                        }else if(val === "cekai"){
                                            return "测开";
                                        }
                                        return val;
                                    }}
                                />
                                <Axis name="month" />
                                <Axis
                                    name="hour"
                                    label={{
                                        formatter: val => `${val}小时`
                                    }}
                                />
                                <Tooltip
                                    crosshairs={{
                                        type: "y"
                                    }}
                                />
                                <Geom
                                    type="line"
                                    position="month*hour"
                                    size={2}
                                    color={"group"}
                                />
                                <Geom
                                    type="point"
                                    position="month*hour"
                                    size={4}
                                    shape={"circle"}
                                    color={"group"}
                                    style={{
                                        stroke: "#fff",
                                        lineWidth: 1
                                    }}
                                />
                            </Chart>}
                        </Card>
                    </Col>
                    <Col {...topColResponsiveProps2}>
                        <Card title="异常原因分布" extra={
                          <div>
                            <RadioGroup onChange={this.handleChangeAbnormalCountType} defaultValue='all'>
                              <RadioButton value='all'>全部</RadioButton>
                              <RadioButton value='7days'>本周</RadioButton>
                            </RadioGroup>
                          </div>
                        }>
                            <Chart
                                onTooltipChange={(ev)=> {
                                    var items = ev.items;
                                    var origin = items[0];
                                    for(let detail of taskAbnormalCountDTO.taskAbnormalCountDetailDTOList){
                                        if(detail.teamName.substr(0,2) === origin.name.substr(0,2)){
                                            items.push({
                                                name: detail.typeName,
                                                marker: true,
                                                value: detail.count+"("+(detail.count/origin.value*100).toFixed(2)+"%)"
                                            });
                                        }
                                    }
                                }}
                                height={400} data={taskAbnormalCountArray} padding="auto" forceFit >
                                <Coord type="theta" innerRadius={0.75} />
                                <Tooltip showTitle={false} />
                                <Geom type="intervalStack" position="value" color="type" shape="sliceShape" >
                                    <Label
                                        content="value"
                                        offset={-80}
                                        textStyle={{
                                            fill: '#000000',
                                            rotate: 0
                                        }}
                                        formatter={(val, item) => {
                                            return item.point.type + ': ' + val;
                                        }}
                                    />
                                </Geom>
                            </Chart>
                        </Card>
                    </Col>
                </Row>
                <Spin spinning={loading}>
                    <Card title="本周异常问题" extra={
                      haveAdminAuth(this.props.authResourceCodeList) && <Button onClick={this.downloadExcel}>导出</Button>
                    }>
                        <Table
                            rowKey="id"
                            columns={this.columns}
                            dataSource={taskAbnormalList}
                        />
                    </Card>
                </Spin>
            </div>
        )
    }
}
