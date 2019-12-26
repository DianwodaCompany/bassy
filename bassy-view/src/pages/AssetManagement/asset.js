import React from 'react'
import {connect} from 'react-redux'
import {Table, Divider, Card, Modal, Form, Select, Input, DatePicker, Dropdown, Icon, Menu, Button, InputNumber, Row} from 'antd';
import {getAsset, borrowAsset, returnAsset, updateAssetStatus, getAssetLogs, addAsset, editAsset} from "../../apis/index"
import {haveAdminAuth,haveLeaderAuth} from "../../utils";
import moment from 'moment';
import Highlighter from 'react-highlight-words';
import {getStaffList} from "../../apis";
import {message} from "antd/lib/index";

const FormItem = Form.Item;
const Option = Select.Option;
const MenuItem = Menu.Item;

@connect(state => ({
  staffInfo: state.common.staffInfo,
  authResourceCodeList: state.common.authResourceCodeList,
}))
@Form.create()
export default class Asset extends React.Component {

  state = {
    assets: [],
    editingItem: {},
    borrowView: false,
    loading: false,
    staffList: [{code: '', name: ''}],
    operate: 'borrow',
    assetLog: [],
    logView: false,
    searchText:"",
    addView: false,
    editView: false,
    extraInfoView: false,
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
        textToHighlight={text == null ? "" : text.toString()}
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
          title: 'id',
          key: 'id',
          dataIndex: 'id',
          width: '5%',
      },
    {
      title: '资产类型',
      key: 'type',
      dataIndex: 'typeMean',
      width: '6%',
      ...this.getColumnSearchProps('资产类型',(value, record) => {
        if (record.typeMean != null && record.typeMean !== "") {
          return record.typeMean.includes(value);
        }
      }),
    },
    {
      title: '品牌',
      key: 'brand',
      dataIndex: 'brand',
      width: '5%',
      ...this.getColumnSearchProps('品牌',(value, record) => {
        if (record.brand != null && record.brand !== "") {
          return record.brand.toLowerCase().includes(value);
        }
      }),
    },
    {
      title: '型号/名称',
      key: 'model',
      dataIndex: 'model',
      width: '10%',
      ...this.getColumnSearchProps('型号',(value, record) => {
        if (record.model != null && record.model !== "") {
          return record.model.toLowerCase().includes(value);
        }
      }),
    },
      {
          title: 'IMEI',
          key: 'imei',
          dataIndex: 'imei',
          width: '10%',
          ...this.getColumnSearchProps('IMEI',(value, record) => {
              if (record.imei != null && record.imei !== "") {
                  return record.imei.toLowerCase().includes(value);
              }
          }),
      },
      {
          title: '资产编号',
          key: 'assetNumber',
          dataIndex: 'assetNumber',
          width: '10%',
          ...this.getColumnSearchProps('资产编号',(value, record) => {
              if (record.assetNumber != null && record.assetNumber !== "") {
                  return record.assetNumber.toLowerCase().includes(value);
              }
          }),
      },
    {
      title: '系统',
      key: 'version',
      dataIndex: 'version',
      width: '5%',
      ...this.getColumnSearchProps('系统',(value, record) => {
        if (record.version != null && record.version !== "") {
          return record.version.includes(value);
        }
      }),
    },
    {
      title: '屏幕尺寸',
      key: 'size',
      dataIndex: 'size',
      width: '5%',
    },
    {
      title: '总数量',
      key: 'amount',
      dataIndex: 'amount',
      width: '5%',
    },
    {
      title: '已领用',
      key: 'inUse',
      dataIndex: 'inUse',
      width: '5%',
    },
    {
      title: '状态',
      key: 'status',
      dataIndex: 'statusMean',
      width: '6%',
      ...this.getColumnSearchProps('状态',(value, record) => {
        if (record.statusMean != null && record.statusMean !== "") {
          return record.statusMean.includes(value);
        }
      }),
    },
    {
      title: '领用人',
      key: 'borrower',
      dataIndex: 'borrowerName',
      width: '6%',
      ...this.getColumnSearchProps('领用人',(value, record) => {
        if (record.borrowerName != null && record.borrowerName !== "") {
          return record.borrowerName.includes(value);
        }
      }),
    },
    {
      title: '领用时间',
      key: 'borrowTm',
      width: '6%',
      render: record => record.borrowTm == null ? '' : moment(record.borrowTm).format('YYYY-MM-DD HH:mm:ss')
    },
    {
      title: '操作',
      key: 'action',
      width: '11%',
      render: record => {
        return (
          <span>
            {
              (haveAdminAuth(this.props.authResourceCodeList) || haveLeaderAuth(this.props.authResourceCodeList)) && record.amount > record.inUse &&
              <span>
                <a onClick={() => this.onBorrow(record)}>领用</a>
                <Divider type="vertical"/>
              </span>
            }
            {
              (haveAdminAuth(this.props.authResourceCodeList) || haveLeaderAuth(this.props.authResourceCodeList)) && record.amount <= record.inUse &&
              <span>
                <a onClick={() => this.onReturn(record)}>归还</a>
                <Divider type="vertical"/>
              </span>
            }
            <Dropdown overlay={
              <Menu onClick={({ key }) => this.onClickMenu(key, record)}>
                {record.status !== 40 && haveAdminAuth(this.props.authResourceCodeList) && <MenuItem key={'edit'}>编辑</MenuItem>}
                {record.status === 30 ? haveAdminAuth(this.props.authResourceCodeList) && <MenuItem key={'unuse'}>恢复使用</MenuItem> : haveAdminAuth(this.props.authResourceCodeList) && <MenuItem key={'repair'}>报修</MenuItem>}
                {record.status !== 40 && haveAdminAuth(this.props.authResourceCodeList) && <MenuItem key={'abandon'}>作废</MenuItem>}
                <MenuItem key={'log'}>操作记录</MenuItem>
              </Menu>
            }>
              <a className="ant-dropdown-link">更多 <Icon type="down" /></a>
            </Dropdown>
          </span>
        )
      }
    },
  ];

  logColumn = [
    {
      title: '操作',
      key: 'status',
      dataIndex: 'statusMean',
    },
    {
      title: '领用人',
      key: 'borrower',
      dataIndex: 'borrowerName',
    },
    {
      title: '领用/归还时间',
      key: 'borrowTm',
      render: record => {
        if(record.borrowTm == null  && record.returnTm == null)
          return '';
        else if(record.borrowTm != null)
          return moment(record.borrowTm).format('YYYY-MM-DD HH:mm:ss');
        else
          return moment(record.returnTm).format('YYYY-MM-DD HH:mm:ss');
      }
    },
    {
      title: '备注',
      key: 'remark',
      dataIndex: 'remark',
    },
    {
      title: '操作人',
      key: 'modifierName',
      dataIndex: 'modifierName',
    },
    {
      title: '操作时间',
      key: 'modifyTm',
      render: record => {return moment(record.modifyTm).format('YYYY-MM-DD HH:mm:ss');}
    }
  ];

  componentWillMount = async () => {
    let resp = await getAsset();
    this.setState({assets: resp.data});
  };

  changPersonKeyWord = async (input) => {
    if (input === undefined || input === "") {
      return;
    }
    const staffList = await getStaffList(input);
    if (staffList.data) {
      this.setState({staffList: staffList.data})
    }
  };

  onTypeSelect = key => {
      if(key == 10) {
          this.setState({extraInfoView: true});
      } else {
          this.setState({extraInfoView: false});
      }
  };

  onBorrow = record => {
    this.setState({borrowView: true, operate: 'borrow', editingItem: record});
  };

  onReturn = record => {
    this.setState({borrowView: true, operate: 'return', editingItem: record});
  };

  onAdd = record => {
    this.setState({addView: true, editingItem: record});
  };

  onEdit = () => {
    this.setState({editView: true});
  };

  onCancel = () => {
    this.setState({logView: false, assetLog: [], borrowView: false, addView: false, editView: false, extraInfoView: false, operate: 'borrow', editingItem: {}});
    this.props.form.resetFields();
  };

  onClickMenu = (key, record) => {
    this.state.editingItem = record;
    switch (key) {
      case 'edit':
        this.onEdit();
        break;
      case 'repair':
        this.doChangeStatus(30);
        break;
      case 'abandon':
        this.doChangeStatus(40);
        break;
      case 'unuse' :
        this.doChangeStatus(10);
        break;
      case 'log':
        this.viewAssetLog();
        break
    }
  };

  doChangeStatus = async(status) => {
    const {editingItem} = this.state;
    const param = {};
    let asset = Object.assign({}, editingItem);
    asset.status = status;
    param.modifier = this.props.staffInfo.code;
    param.asset = asset;
    let resp = await updateAssetStatus(param);

    if (resp.data) {
      message.success("资产操作成功!");
      const resp1 = await getAsset();
      this.setState({assets: resp1.data, editingItem: {}});
    } else {
      message.error("资产操作失败，请联系开发人员!");
      return
    }
  };

  viewAssetLog = async() => {
    this.setState({logView: true});
    const {editingItem} = this.state;
    let resp = await getAssetLogs(editingItem.id);
    if(resp.data) {
      this.setState({assetLog: resp.data, editingItem: {}});
    } else {
      message.error("获取操作记录失败，请联系开发人员!");
      return
    }
  };

  onCancelLog = () => {
    this.setState({logView: false, editingItem: {}, assetLog: []});
  };

  doEditAsset = e => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll(
      async (err, values) => {
        if (!err) {
          this.setState({loading: true});
          const {editingItem} = this.state;
          const param = {};
          let asset = Object.assign({}, editingItem);
          asset.version = values.version;
          asset.amount = values.amount;
          param.modifier = this.props.staffInfo.code;
          param.asset = asset;
          let resp = await editAsset(param);
          if (resp.data) {
            message.success("资产编辑成功!");
            const resp1 = await getAsset();
            this.props.form.resetFields();
            this.setState({assets: resp1.data, editView: false, editingItem: {}});
          } else {
            message.error("资产操作失败，请联系开发人员!");
            return
          }
          this.setState({loading: false});
        }
      })
  };

  doAddAsset = e => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll(
      async (err, values) => {
        if (!err) {
          this.setState({loading: true});
          const param = {};
          let asset = Object.assign({}, values);
          param.modifier = this.props.staffInfo.code;
          param.asset = asset;
          let resp = await addAsset(param);
          if (resp.data) {
            message.success("新增资产成功!");
            const resp1 = await getAsset();
            this.props.form.resetFields();
            this.setState({assets: resp1.data, addView: false});
          } else {
            message.error("资产操作失败，请联系开发人员!");
            return
          }
          this.setState({loading: false});
        }
      })
  };

  doBorrowReturn = e => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll(
      async (err, values) => {
        if (!err) {
          this.setState({loading: true});
          const {operate, editingItem} = this.state;
          const param = {};
          let asset = Object.assign({}, editingItem);
          if (values.remark != null) {
            param.remark = values.remark;
          }
          param.modifier = this.props.staffInfo.code;
          let resp = {};
          if(operate === 'borrow') {
            asset.borrower = /\((\d+)\)/.exec(values.borrower)[1];
            asset.borrowTm = new Date(moment(values.time).format('YYYY-MM-DD HH:mm:ss'));
            param.asset = asset;
            resp = await borrowAsset(param);
          } else {
            asset.returnTm = new Date(moment(values.time).format('YYYY-MM-DD HH:mm:ss'));
            param.asset = asset;
            resp = await returnAsset(param);
          }
          if (resp.data) {
            message.success("资产操作成功!");
            const resp1 = await getAsset();
            this.props.form.resetFields();
            this.setState({assets: resp1.data, borrowView: false, operate: 'borrow', editingItem: {}});
          } else {
            message.error("资产操作失败，请联系开发人员!");
            return
          }
          this.setState({loading: false});
        }
      })
  };

  render() {

    const {borrowView, addView, editView, extraInfoView, staffList, operate, loading, logView, assetLog, assets} = this.state;
    const {authResourceCodeList} = this.props;
    const {getFieldDecorator} = this.props.form;
    const formItemLayout = {
      labelCol: {
        xs: {span: 24},
        sm: {span: 7},
      },
      wrapperCol: {
        xs: {span: 24},
        sm: {span: 12},
      },
    };

    return (
      <div>
        <Card bordered={false}>
          {
            haveAdminAuth(authResourceCodeList) &&
            <Row style={{marginBottom:4}}>
              <Button type="primary" onClick={this.onAdd}>新增资产</Button>
            </Row>
          }
          <Table dataSource={assets} columns={this.column} bordered/>
          <Modal onOk={this.doBorrowReturn} onCancel={this.onCancel} okText={operate === 'borrow' ? "领用" : "归还"} cancelText={"取消"} confirmLoading={loading} visible={borrowView} title={operate === 'borrow' ? "资产领用" : "资产归还"}>
            <Form>
              {operate === 'borrow' &&
              <FormItem label="领用人" {...formItemLayout}>
                {getFieldDecorator('borrower', {
                  rules: [
                    {
                      required: borrowView, message: '领用人必填!',
                    }
                  ],
                })(<Select
                    showSearch
                    placeholder="工号或姓名"
                    onSearch={this.changPersonKeyWord}
                  >
                    {staffList.map((s) => (
                      <Option value={`(${s.code})${s.name}`} key={s.code}>
                        {"(" + s.code + ")" + s.name}
                      </Option>
                    ))}
                  </Select>
                )}
              </FormItem>
              }
              <FormItem label={operate === 'borrow' ? "领用时间" : "归还时间"} {...formItemLayout}>
                {getFieldDecorator('time', {
                  rules: [
                    {
                      required: borrowView, message: '时间必填!',
                    }
                  ],
                  initialValue: moment(new Date())
                })(
                  <DatePicker showTime placeholder="领用/归还时间"/>
                )}
              </FormItem>
              <FormItem label={"备注"} {...formItemLayout}>
                {getFieldDecorator('remark')(
                  <Input.TextArea rows={4}/>
                )}
              </FormItem>
            </Form>
          </Modal>
          <Modal onCancel={this.onCancelLog} visible={logView} title={"资产操作记录"} width={'60%'} footer={null}>
            <Table dataSource={assetLog} columns={this.logColumn} bordered/>
          </Modal>
          <Modal title={"新增资产"} visible={addView} onCancel={this.onCancel} onOk={this.doAddAsset}>
            <Form>
              <FormItem label="资产类型" {...formItemLayout}>
                {getFieldDecorator('type', {
                  rules: [
                    {
                      required: addView, message: '资产类型必填!',
                    }
                  ],
                })(
                  <Select showSearch placeholder="资产类型" onSelect={this.onTypeSelect}>
                    <Option key={10}>手机</Option>
                    <Option key={20}>电脑</Option>
                    <Option key={30}>配件</Option>
                    <Option key={40}>图书</Option>
                  </Select>
                )}
              </FormItem>
              <FormItem label={"品牌"} {...formItemLayout}>
                {getFieldDecorator('brand')(
                  <Input placeholder="品牌"/>
                )}
              </FormItem>
              <FormItem label={"型号/名称"} {...formItemLayout}>
                {getFieldDecorator('model', {
                  rules: [
                    {
                      required: addView, message: '资产型号/名称必填!',
                    }
                  ],
                })(
                  <Input placeholder="型号/名称"/>
                )}
              </FormItem>
                { extraInfoView && <FormItem label={"IMEI"} {...formItemLayout}>
                    {getFieldDecorator('imei', {
                        rules: [
                            {
                                required: extraInfoView, message: 'IMEI必填!',
                            }
                        ],
                    })(
                        <Input placeholder="IMEI"/>
                    )}
                </FormItem>}
                { extraInfoView && <FormItem label={"资产编号"} {...formItemLayout}>
                    {getFieldDecorator('assetNumber', {
                        rules: [
                            {
                                required: extraInfoView, message: '资产编号必填!',
                            }
                        ],
                    })(
                        <Input placeholder="资产编号"/>
                    )}
                </FormItem>}
              <FormItem label={"系统"} {...formItemLayout}>
                {getFieldDecorator('version')(
                  <Input placeholder="系统"/>
                )}
              </FormItem>
              <FormItem label={"尺寸"} {...formItemLayout}>
                {getFieldDecorator('size')(
                  <Input placeholder="尺寸"/>
                )}
              </FormItem>
              <FormItem label={"数量"} {...formItemLayout}>
                {getFieldDecorator('amount', {
                  rules: [
                    {
                      required: addView, message: '资产数量必填!',
                    }
                  ],
                  initialValue: 1,
                })(
                  <InputNumber step={1}/>
                )}
              </FormItem>
            </Form>
          </Modal>
          <Modal title={"编辑资产"} visible={editView} onCancel={this.onCancel} onOk={this.doEditAsset}>
            <Form>
              <FormItem label={"系统"} {...formItemLayout}>
                {getFieldDecorator('version', {
                  initialValue: this.state.editingItem.version,
                })(
                  <Input placeholder="系统"/>
                )}
              </FormItem>
              <FormItem label={"数量"} {...formItemLayout}>
                {getFieldDecorator('amount', {
                  rules: [
                    {
                      required: editView, message: '资产数量必填!',
                    }
                  ],
                  initialValue: this.state.editingItem.amount == undefined ? 1 : this.state.editingItem.amount,
                })(
                  <InputNumber step={1}/>
                )}
              </FormItem>
            </Form>
          </Modal>
        </Card>
      </div>
    )
  }
}