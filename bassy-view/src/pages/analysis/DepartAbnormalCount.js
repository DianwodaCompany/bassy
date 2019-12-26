import React from 'react'
import {connect} from 'react-redux'
import {Table, Card, Modal, Form, Button, Radio, Input, Icon} from 'antd';
import moment from 'moment';
import {Ellipsis} from 'ant-design-pro';
import Highlighter from 'react-highlight-words';
import {getDepartAbnormalCount, getDepartAbnormalDetail} from "../../apis";

const RadioButton = Radio.Button;
const RadioGroup = Radio.Group;

@connect(state => ({
  staffInfo: state.common.staffInfo,
  authResourceCodeList: state.common.authResourceCodeList,
}))
@Form.create()
export default class DepartAbnormalCount extends React.Component {

  state = {
    startTm: moment().add(-7, 'days').format("YYYY-MM-DD"),
    endTm: moment().add(1,'days').format("YYYY-MM-DD"),
    countType: '7days',
    departAbnormalCount: [{}],
    departAbnormalDetail: [{}],
    detailVisible: false,
  };

  getDepartAbnormalCount = async () => {
    const formData = new FormData();
    formData.append("startTm", this.state.startTm);
    formData.append("endTm", this.state.endTm);
    const departAbnormalCount = await getDepartAbnormalCount(formData);
    this.setState({departAbnormalCount: departAbnormalCount.data})
  };

  componentWillMount() {
    this.getDepartAbnormalCount();
  }

  showDetail = async(departId) => {
    this.setState({detailVisible: true});
    const formData = new FormData();
    formData.append("startTm", this.state.startTm);
    formData.append("endTm", this.state.endTm);
    formData.append("abnormalDepart", departId);
    const departAbnormalDetail = await getDepartAbnormalDetail(formData);
    this.setState({departAbnormalDetail: departAbnormalDetail.data})
  };

  onCancel = () => {
    this.setState({detailVisible: false, departAbnormalDetail: [{}]});
  };

  handleChangeCountType = e => {
    switch (e.target.value) {
      case '7days':
        this.state.startTm = moment().add(-7, 'days').format("YYYY-MM-DD");
        this.state.endTm = moment().add(1, 'days').format("YYYY-MM-DD");
        break;
      case 'month':
        this.state.startTm = moment().add(-30, 'days').format("YYYY-MM-DD");
        this.state.endTm = moment().add(1, 'days').format("YYYY-MM-DD");
        break;
      case 'all':
        this.state.startTm = moment("2019-01-06").format("YYYY-MM-DD");
        this.state.endTm = moment().add(1, 'days').format("YYYY-MM-DD");
        break;
    }
    this.getDepartAbnormalCount(this.state.startTm, this.state.endTm);
  };

  getColumnSearchProps = (placehold,filter) => ({
    filterDropdown: ({
                       setSelectedKeys, selectedKeys, confirm, clearFilters,
                     }) => (
      <div className="custom-filter-dropdown">
        <Input
          ref={node => { this.searchInput = node; }}
          placeholder={`Search ${placehold}`}
          value={selectedKeys[0]}
          onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
          onPressEnter={() => this.handleSearch(selectedKeys, confirm)}
          style={{ width: 188, marginBottom: 8, display: 'block' }}
        />
        <Button
          type="primary"
          onClick={() => this.handleSearch(selectedKeys, confirm)}
          icon="search"
          size="small"
          style={{ width: 90, marginRight: 8 }}
        >
          Search
        </Button>
        <Button
          onClick={() => this.handleReset(clearFilters)}
          size="small"
          style={{ width: 90 }}
        >
          Reset
        </Button>
      </div>
    ),
    filterIcon: filtered => <Icon type="search" style={{ color: filtered ? '#1890ff' : undefined }} />,
    onFilter: (value,record) => filter(value,record),
    onFilterDropdownVisibleChange: (visible) => {
      if (visible) {
        setTimeout(() => this.searchInput.select());
      }
    },
    render: (text) => (
      <Highlighter
        highlightStyle={{ backgroundColor: '#ffc069', padding: 0 }}
        searchWords={[this.state.searchText]}
        autoEscape
        textToHighlight={text.toString()}
      />
    ),
  });

  handleSearch = (selectedKeys, confirm) => {
    confirm();
    this.setState({ searchText: selectedKeys[0] });
  };

  handleReset = (clearFilters) => {
    clearFilters();
    this.setState({ searchText: '' });
  };

  column = [
    {
      title: '二级部门',
      key: 'departParentName',
      ...this.getColumnSearchProps('二级部门',(value, record) => {
        return record.departParentName.includes(value);
      }),
      render: (text, record, index) => {
        return record.departParentName;
      }
    },
    {
      title: '三级部门',
      key: 'departName',
      ...this.getColumnSearchProps('三级部门',(value, record) => {
        return record.departName.includes(value);
      }),
      render: (text, record, index) => {
        return record.departName;
      }
    },
    {
      title: '总扣分',
      dataIndex: 'totalScore',
      key: 'totalScore',
    },
    {
      title: '人均扣分',
      dataIndex: 'scorePerStaff',
      key: 'scorePerStaff',
    },
    {
      title: '失分原因top',
      dataIndex: 'topReasonMean',
      key: 'topReasonMean',
    },
    {
      title: '失分责任人top',
      dataIndex: 'topStaffName',
      key: 'topStaffName',
    },
    {
      title: '操作',
      key: 'action',
      render: item => {
        return (
          <Button type={"primary"} size={"small"} onClick={() => this.showDetail(item.departId)}>
            查看明细
          </Button>
        )
      }
    },
  ];

  detailColumn = [
    {
      title: '项目',
      dataIndex: 'projectName',
      render: (text, record, index) => {
        return "(" + record.projectId + ")" + record.projectName;
      }
    },
    {
      title: '需求',
      dataIndex: 'storyTitle',
      render: (text, record, index) => {
        return "(" + record.storyId + ")" + record.storyTitle;
      }
    },
    {
      title: '任务',
      dataIndex: 'taskName',
      render: (text, record, index) => {
        return "(" + record.taskId + ")" + record.taskName;
      }
    },
    {
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
    },
    {
      title: '一级原因',
      dataIndex: 'reasonTeamName',
    },
    {
      title: '二级原因',
      dataIndex: 'reasonTypeName',
    },
    {
      title: '严重等级',
      dataIndex: 'reasonLevel',
      sorter: (a, b) => {
        let aLevel = parseInt(a.reasonLevel.substr(1));
        let bLevel = parseInt(b.reasonLevel.substr(1));
        return aLevel - bLevel;
      }
    },
    {
      title: '异常说明',
      dataIndex: 'reasonDetail',
      width: 200,
      render: (text, record, index) => {
        return <Ellipsis length={60} tooltip>{text}</Ellipsis>;
      }
    },
    {
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
        return record.currentNormalState === 0 ? <span style={{color:"red"}}>异常</span> : "正常";
      }
    },
    {
      title: '测试人员',
      dataIndex: 'testerName',
    },
    {
      title: '责任人',
      dataIndex: 'abnormalOwnerName',
    },
    {
      title: '更新时间',
      dataIndex: 'modifyTime',
      render: (text, record, index) => {
        return moment(text).format("YYYY-MM-DD HH:mm:ss");
      }
    }
  ];

  render() {
    const {departAbnormalCount, departAbnormalDetail, detailVisible} = this.state;
    return (
      <Card title={"部门异常统计"} extra={
        <div>
          <RadioGroup onChange={e => this.handleChangeCountType(e)} defaultValue='7days'>
            <RadioButton value='7days'>本周</RadioButton>
            <RadioButton value='month'>本月</RadioButton>
            <RadioButton value='all'>全部</RadioButton>
          </RadioGroup>
        </div>
      }>
        <Table dataSource={departAbnormalCount} columns={this.column} bordered />
        <div>
          <p>
            扣分规则<br />
            P1：扣60分（影响多个项目的进度或单个项目的最终上线时间影响大于2个工作日）<br />
            P2：扣30分（项目上线时间影响2个工作日以内）<br />
            P3：扣8分（项目组自行消化，对上线进度无影响）<br />
            <br />
            总扣分： 按三级部门统计，该部门下组员的扣分合计<br />
            人均扣分：总扣分/三级部门人数<br />
            失分TOP：按过程异常原因归类，统计每个异常原因失分之和，取出该组失分最大的原因<br />
            失分责任人TOP：按人统计失分之和，取出改组失分最大的责任人<br />
          </p>
        </div>
        <Modal onCancel={this.onCancel} visible={detailVisible} title={"异常明细"} width={"80%"} footer={false}>
          <Table dataSource={departAbnormalDetail} columns={this.detailColumn} bordered={false} />
        </Modal>
      </Card>
      )
  }
}