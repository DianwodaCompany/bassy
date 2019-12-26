import React from 'react'
import {Table,Card, Tabs, Row} from "antd";
import moment from "moment/moment";
import {getWorkDailyReport} from "../../apis";

const TabPane = Tabs.TabPane;
const dateFormat = 'YYYY-MM-DD';
moment.locale('zh-cn');

export default class WorkDailyReport extends React.Component {

  state = {
    departId: 1,
    departWork: [{
      code: '',
      name: '',
      date: '',
      todayAct: '',
      todayExp: '',
      tomorrow: '',
    }],
    days: [],
    heapData: [],
    date: moment().add(-1,"days").format(dateFormat),
  };

  columns = [
    {
      title: "姓名",
      key: "name",
      width: 80,
      dataIndex: "name",
    },
    {
      title: "今日预期",
      key: "todayExp",
      width: 350,
      render: item => {
        let todayExp = item.todayExp;
        let content = [];
        for(let i in todayExp) {
          let text = '';
          text += todayExp[i].projectName == null ? '' : '##' + todayExp[i].projectName + '## ';
          text += todayExp[i].requireId == null ? '' : todayExp[i].requireRelate + '[' + todayExp[i].requireId + ']' + '--' ;
          text += todayExp[i].taskName == null ? '' : todayExp[i].taskName;
          let p = <p>{text}</p>;
          content.push(p);
        }
        return content;
      }
    },
    {
      title: "今日实际",
      key: "todayAct",
      width: 350,
      render: item => {
        let todayAct = item.todayAct;
        let content = [];
        for(let i in todayAct) {
          let text = '';
          text += todayAct[i].requireId == null ? '' : todayAct[i].requireRelate + '--' ;
          text += todayAct[i].taskName == null ? '' : todayAct[i].taskName;
          text += todayAct[i].todayHour == null ? '' : '(' + todayAct[i].todayHour + 'h -> '+ todayAct[i].percent + '%)';
          let p =  <p>{text}</p>;
          content.push(p);
        }
        return content;
      }
    },
    {
      title: "异常/备注",
      key: "abnormal",
      width: 150,
      render: item => {
        let abnormal = item.todayAct;
        let content =[];
        for(let i in abnormal) {
          let text = '';
          text += abnormal[i].isNormal === 0 && abnormal[i].requireId != null ? abnormal[i].requireRelate + "--" : '';
          text += abnormal[i].isNormal === 0 && abnormal[i].reasonDetail != null ? abnormal[i].taskName + '->' + abnormal[i].reasonDetail : '';
          text += abnormal[i].isNormal === 0 && abnormal[i].taskExplain != null ? abnormal[i].taskName + '->'  + abnormal[i].taskExplain : '';
          let p =  <p>{text}</p>;
          content.push(p);
        }
        return content;
      }
    },
    {
      title: "明日计划",
      key: "tomorrow",
      width: 350,
      render: item => {
        let tomorrow = item.tomorrow;
        let content = [];
        for(let i in tomorrow) {
          let text = '';
          text += tomorrow[i].projectName == null ? '' : '##' + tomorrow[i].projectName + '## ';
          text += tomorrow[i].requireId == null ? '' : tomorrow[i].requireRelate + '[' + tomorrow[i].requireId + ']' + '--' ;
          text += tomorrow[i].taskName == null ? '' : tomorrow[i].taskName;
          let p =  <p>{text}</p>;
          content.push(p);
        }
        return content;
      }
    }
  ]

  handleDepartChange = activeKey => {
    this.state.departId = Number(activeKey);
    this.refreshData();
  };

  handleDateChange = activeKey => {
    this.state.date = activeKey
    this.refreshData();
  };

  refreshData = async () => {
    const {departId, date} = this.state;
    const formData = new FormData();
    formData.append("departId",departId.toString());
    formData.append("date",date);
    const getDepartDaily = await getWorkDailyReport(formData);
    this.setState({departWork: getDepartDaily.data});
  };

  componentWillMount() {
    const {days} = this.state;
    const startTm = moment().add(-1,"days").format(dateFormat);
    const endTm = moment().add(-7, 'days').format(dateFormat);
    for(let i = startTm; moment(i).isSameOrAfter(endTm); i = moment(i).add(-1,"days").format(dateFormat)) {
      days.push(i);
    }
    this.refreshData();
  }

  render() {

    const {departWork, date, days} = this.state;

    return (
      <div>
        <Card bordered={false}>
          <Row>
            <Tabs defaultActiveKey="1" size={"large"} onChange={this.handleDepartChange}>
              <TabPane tab="商家组" key="0">
                <Row/>
                {this.state.departId === 0 &&
                  <Tabs defaultActiveKey={date} tabPosition={"left"} onChange={this.handleDateChange}>
                    {days.map( d => {
                      return (
                        <TabPane tab={moment(d).format('MM-DD (dddd)') + ' >'} key={d}>
                          <Table bordered dataSource={departWork} columns={this.columns} />
                        </TabPane>
                      )})
                    }
                  </Tabs>
                }
              </TabPane>
              <TabPane tab="骑手组" key="1">
                <Row/>
                {this.state.departId === 1 &&
                  <Tabs defaultActiveKey={date} tabPosition={"left"} onChange={this.handleDateChange}>
                    {days.map( d => {
                      return (
                        <TabPane tab={moment(d).format('MM-DD (dddd)') + ' >'} key={d}>
                          <Table bordered dataSource={departWork} columns={this.columns} />
                        </TabPane>
                      )})
                    }
                  </Tabs>
                }
                </TabPane>
              <TabPane tab="支撑组" key="2">
                <Row/>
                {this.state.departId === 2 &&
                  <Tabs defaultActiveKey={date} tabPosition={"left"} onChange={this.handleDateChange}>
                    {days.map( d => {
                      return (
                        <TabPane tab={moment(d).format('MM-DD (dddd)') + ' >'} key={d}>
                          <Table bordered dataSource={departWork} columns={this.columns} />
                        </TabPane>
                      )})
                    }
                  </Tabs>
                }
              </TabPane>
              <TabPane tab="测开组" key="3">
                <Row/>
                {this.state.departId === 3 &&
                  <Tabs defaultActiveKey={date} tabPosition={"left"} onChange={this.handleDateChange}>
                    {days.map( d => {
                      return (
                        <TabPane tab={moment(d).format('MM-DD (dddd)') + ' >'} key={d}>
                          <Table bordered dataSource={departWork} columns={this.columns} />
                        </TabPane>
                      )})
                    }
                  </Tabs>
                }
              </TabPane>
            </Tabs>
          </Row>
        </Card>
      </div>
    )
  }

}