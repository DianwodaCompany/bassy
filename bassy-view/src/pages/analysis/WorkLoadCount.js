import React from 'react'
import {connect} from 'react-redux'
import {Table, Card, Row, Form, DatePicker,} from 'antd';
import moment from 'moment';
import {getStaffWorkLoad, getDepartWorkLoad} from "../../apis";

const { MonthPicker } = DatePicker;


@connect(state => ({
    staffInfo: state.common.staffInfo,
    authResourceCodeList: state.common.authResourceCodeList,
}))
@Form.create()
export default class WorkLoadCount extends React.Component {

    state = {
        staffWorkLoad: [{
            staffName:'',
            departName:'',
            workLoad:'',
            placing:'',
            preWorkLoad:'',
            prePlacing:'',
            hisWorkLoad:'',
        }],
        departWorkLoad: [{
            departName:'',
            workLoad:'',
            placing:'',
            preWorkLoad:'',
            prePlacing:'',
            hisWorkLoad:'',
        }],
        key: 'staff',
        date: moment().month(moment().month() - 1).startOf('month').format('YYYY-MM-DD HH:mm:ss'),
    };
    staffColumns = [
        {
            title: '姓名',
            dataIndex: 'staffName',
        },
        {
            title: '所属组',
            dataIndex: 'departName',
        },
        {
            title: '本月平均工时',
            dataIndex: 'workLoad',
        },
        {
            title: '排名',
            dataIndex: 'placing',
        },
        {
            title: '上月平均工时',
            dataIndex: 'preWorkLoad',
        },
        {
            title: '排名',
            dataIndex: 'prePlacing',
        },
        {
            title: '历史平均工时',
            dataIndex: 'hisWorkLoad',
        },
    ];

    departColumns = [
        {
            title: '组名',
            dataIndex: 'departName',
        },
        {
            title: '本月人均工时',
            dataIndex: 'workLoad',
        },
        {
            title: '排名',
            dataIndex: 'placing',
        },
        {
            title: '上月人均工时',
            dataIndex: 'preWorkLoad',
        },
        {
            title: '排名',
            dataIndex: 'prePlacing',
        },
        {
            title: '历史人均工时',
            dataIndex: 'hisWorkLoad',
        },
    ];

    tabList = [{
        key: 'staff',
        tab: '员工工时',
    }, {
        key: 'depart',
        tab: '组工时',
    }];

    componentWillMount = async() => {
        this.getWorkLoad(this.state.key, this.state.date);
    };

    disabledDate(current) {
        //当前月以及20190106之前的月份不可选择
        return current > moment().month(moment().month() - 1) || current < moment(new Date('2018/12/20'));
    }

    getWorkLoad = async(key, date) => {
        let resp;
        const formData = new FormData();
        formData.append("date", date);
        if( key == 'staff') {
            resp = await getStaffWorkLoad(formData);
            this.setState({staffWorkLoad: resp.data})
        } else {
            resp = await getDepartWorkLoad(formData);
            this.setState({departWorkLoad: resp.data})
        }
    };
    onChange = async(date, dateString) => {
        let dateParam = moment(new Date(dateString)).startOf('month').format('YYYY-MM-DD HH:mm:ss');
        this.setState({date: dateParam});
        this.getWorkLoad(this.state.key, dateParam);
    };

    onTabChange = (key, type) => {
        console.log(key, type);
        this.setState({ [type]: key });
        this.getWorkLoad(key, this.state.date);
    };

    render() {
        const {key, staffWorkLoad, departWorkLoad} = this.state;
        return (
            <Card tabList={this.tabList}
                  activeTabKey={key}
                  onTabChange={(key) => { this.onTabChange(key, 'key'); }}
            >
                <Row type="flex" justify="center" align="top" style={{marginBottom:8}}>
                <MonthPicker defaultValue={moment().month(moment().month() - 1)} disabledDate={this.disabledDate} onChange={this.onChange} placeholder="Select month" />
                </Row>
                {key == 'staff' && <Table dataSource={staffWorkLoad} columns={this.staffColumns} bordered={true}/>}
                {key == 'depart' && <Table dataSource={departWorkLoad} columns={this.departColumns} bordered={true}/>}
            </Card>
        )
    }
}