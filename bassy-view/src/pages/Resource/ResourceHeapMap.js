import React from "react";
import {Button, Card, Col, DatePicker, Form, Row, Tabs} from 'antd';
import HeatMap from "./HeapMap";
import {getDepartStaff, getHeapWorkHourData} from "../../apis/index";
import moment from "moment";
import {connect} from "react-redux";

const TabPane = Tabs.TabPane;
const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';
const {RangePicker} = DatePicker;

@connect(state => ({
    staffInfo: state.common.staffInfo
}))
export default class ResourceHeapMap extends React.Component {

    state = {
        startTm: moment().format(dateFormat),
        endTm: moment().add(7, 'days').format(dateFormat),
        departStaff: [],
        days: [],
        heapData: []
    };

    handleDepartChange = activeKey => {
        this.state.departId = Number(activeKey);
        this.refreshData();
    };

    disabledDate = date => {
        const todayZero = moment().format(dateFormat);
        //return date.toDate().getTime() < moment(todayZero).toDate().getTime();
        return false;
    };

    onDateSelectChange = val => {
        this.setState({
            startTm: val[0].format(dateFormat),
            endTm: val[1].format(dateFormat)
        });
    };

    handleSearch = e => {
        this.refreshData();
    };

    handleReset = e => {
        this.state.startTm = moment().format(dateFormat);
        this.state.endTm = moment().add(7, 'days').format(dateFormat);
        this.refreshData();
    };

    componentWillMount = () => {
        const {staffInfo} = this.props;
        if(staffInfo.departId === 461){
            this.state.departId = 3;
        }else if(staffInfo.departId === 462){
            this.state.departId = 1;
        }
        else if(staffInfo.departId === 661){
            this.state.departId = 0;
        }
        else if(staffInfo.departId === 463){
            this.state.departId = 2;
        }else {
            this.state.departId = 1;
        }
        this.refreshData();
    };

    refreshData = async () => {
        const {departId, startTm, endTm} = this.state;
        const getDepartStaffRes = await getDepartStaff(departId);
        let departStaff = [];
        for (let index = 0; index < getDepartStaffRes.data.length; ++index) {
            let staff = getDepartStaffRes.data[index];
            if (!staff.staffName) {
                departStaff.push(staff.staffCode);
            }
            else {
                departStaff.push(staff.staffName);
            }
        }

        let start = startTm;
        let days = [];
        while (moment(start).isBefore(moment(endTm))) {
            days.push(start);
            start = moment(start).add(1, 'days').format("YYYY-MM-DD");
        }
        days.push(start);

        const formData = new FormData();
        formData.append("departId", departId);
        formData.append("startTm", startTm);
        formData.append("endTm", moment(endTm).add(1, 'days').format("YYYY-MM-DD"));
        const getHeapWorkHourDataRes = await getHeapWorkHourData(formData);
        this.setState(
            {
                departStaff: departStaff,
                days: days,
                heapData: getHeapWorkHourDataRes.data
            }
        );
    };

    render() {
        return (
            <div>
                <Card bordered={false}>
                    <Form
                        layout="inline"
                        style={{marginBottom: 12}}
                    >
                        <Row type="flex" justify="center" align="top">
                            <Col>
                                <FormItem label="时间区间">
                                    <RangePicker
                                        disabledDate={this.disabledDate}
                                        defaultValue={[moment(this.state.startTm, dateFormat), moment(this.state.endTm, dateFormat)]}
                                        value={[moment(this.state.startTm, dateFormat), moment(this.state.endTm, dateFormat)]}
                                        format={dateFormat}
                                        onChange={this.onDateSelectChange}/>
                                </FormItem>
                            </Col>
                            <Col>
                                <FormItem>
                                    <Button type="primary" icon="search" htmlType="submit"
                                            onClick={this.handleSearch}>
                                        搜索
                                    </Button>
                                </FormItem>
                                <FormItem>
                                    <Button type="primary" onClick={this.handleReset}>
                                        重置
                                    </Button>
                                </FormItem>
                            </Col>
                        </Row>
                    </Form>
                    <Row/>
                    <Row>
                        <Tabs defaultActiveKey={this.state.departId.toString()} size={"large"} onChange={this.handleDepartChange}>
                            <TabPane tab="商家组" key="0">{this.state.departId === 0 &&
                            <HeatMap departStaff={this.state.departStaff}
                                     days={this.state.days}
                                     heapData={this.state.heapData}/>}
                            </TabPane>
                            <TabPane tab="骑手组" key="1">{this.state.departId === 1 &&
                            <HeatMap departStaff={this.state.departStaff}
                                     days={this.state.days}
                                     heapData={this.state.heapData}/>
                            }</TabPane>
                            <TabPane tab="支撑组" key="2">{this.state.departId === 2 &&
                            <HeatMap departStaff={this.state.departStaff}
                                     days={this.state.days}
                                     heapData={this.state.heapData}/>
                            }</TabPane>
                            <TabPane tab="测开组" key="3">{this.state.departId === 3 &&
                            <HeatMap departStaff={this.state.departStaff}
                                     days={this.state.days}
                                     heapData={this.state.heapData}/>
                            }</TabPane>
                        </Tabs>
                    </Row>
                </Card>
            </div>

        );
    }
}