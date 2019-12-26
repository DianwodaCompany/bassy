import React, {Fragment, PureComponent} from 'react';
import Highlighter from 'react-highlight-words';
import MultipleDatePicker from "../../../components/MultipleDatePicker";
import EditableTestPlan from "./editablePlanRow"

import moment from 'moment';
import {
    Button,
    Col,
    DatePicker,
    Divider,
    Form,
    Input,
    InputNumber,
    message,
    Modal,
    Popconfirm,
    Row,
    Select,
    Spin,
    Table,
    Icon,
    Checkbox,
} from 'antd';
import {connect} from "react-redux";

import {creatTestPlan, deleteTestPlan, editTestPlan, getStaffList, getTestPlan, getZentaoStories, batchEditTestPlan, getZentaoProjects} from "../../../apis/index";
import {colSort} from "../../../utils";
import {Radio} from "antd/lib/index";

const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

const reasonLevel = ["P1", "P2", "P3"];

@connect(state => ({
    staffInfo: state.common.staffInfo,
    testPlan: state.testPlan,
    allDict: state.allDict,
    abnormalReasonTeam: state.common.abnormalReasonTeam,
    abnormalReasonType: state.common.abnormalReasonType,
    allTasksWithProcess: state.common.allTasksWithProcess
}))
@Form.create()
export default class TestPlan extends PureComponent {

    state = {
        addVisible: false,
        isRequireNeed: false,
        loading: false,
        process: "",
        taskList: [{}],
        editingPlan: [],
        staffList: [{code: '', name: ''}],
        singleAbnormalNeed: true,
        reasonTeam: '',
        reasonTypes: [],
        reasonDetailRequired: false,
        fetching: false,
        fetchData: [],
        fetchZtProjects: [],
        searchText:"",
        includeDate:null,
        excludeDate:null,
        selectedRows: [],
        selectedRowKeys:[],
        batchAssign: false,
        batchAbnormalView: false,
        batchAbnormalNeed: false,
        hasAbnormalOwner: true,
        linkToZtProject: false,
        operateMode:'',
        editableModal: false,
    };

    /**
     * 删除计划
     */
    deletePlan = async (item) => {
        const res = await deleteTestPlan(item.id);
        if (res.data) {
            message.success('删除成功');
            this.resetModal();
            const res = await getTestPlan(item.programId);
            this.props.getNewPlan(res.data);
        } else {
            message.error(res.msg);
        }
    };

  /**
   * 复制计划
   */
  copyPlan = item => {
    const projectType = this.props.detail.programType;
    if(projectType !== "inner") {
      const processTask = this.props.allTasksWithProcess.find((obj) => {
        return item.programProcess === obj.processCode
      });
      this.setState({
        taskList: processTask.tasks,
      });
    }
    this.setState({
      addVisible: true,
      operateMode: 'copy',
      editingPlan: item,
    });
  };

  /**
   * 编辑计划
   * @param item
   */
  editPlan = item => {
      const projectType = this.props.detail.programType;
      if(projectType !== "inner") {
        const processTask = this.props.allTasksWithProcess.find((obj) => {
          return item.programProcess === obj.processCode
        });
        this.setState({
          taskList: processTask.tasks,
        });
      }
        this.setState({
            addVisible: true,
            singleAbnormalNeed: true,
            operateMode: 'edit',
            editingPlan: item,
        });
        console.log("当前行的item值" + item);
    };

    /**
     * 编辑计划时是否勾选填写异常
     */
    onSingleAbnormalNeedChange = () => {
      this.setState({ singleAbnormalNeed: !this.state.singleAbnormalNeed})
    };

  /**
   * 新增计划
   */
  addPlan = () => {
        this.resetModal();
        this.setState({
            addVisible: true,
            operateMode: 'add',
            editingPlan: {},
            includeDate:null,
            excludeDate:null
        })
    };
    //modal中新增测试计划动作
    doAdd = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll(
            async (err, values) => {
                if (!err) {
                    console.log("value from add modal+++" + values);
                    const {excludeDate, includeDate, operateMode, hasAbnormalOwner} = this.state;
                    let user = this.props.staffInfo.code;
                    const params = Object.assign({}, this.state.editingPlan);
                    params.startTm = new Date(moment(values.startTm).startOf('day').format('YYYY-MM-DD HH:mm:ss'));
                    params.endTm = new Date(moment(values.endTm).endOf('day').format('YYYY-MM-DD HH:mm:ss'));
                    params.tester = /\((\d+)\)/.exec(values.tester)[1];
                    if (values.withTester !== undefined)
                        params.withTester = /\((\d+)\)/.exec(values.withTester)[1];
                    params.expectHour = values.expectHour;
                    params.programId = this.props.testPlanList.programId;
                    if(includeDate){
                        let includeDatesArray = [];
                        for(let date of includeDate){
                            includeDatesArray.push(moment(date).format("YYYY-MM-DD"));
                        }
                        params.includeDate = includeDatesArray.join();
                    }
                   if(excludeDate){
                       let excludeDatesArray = [];
                       for(let date of excludeDate){
                           excludeDatesArray.push(moment(date).format("YYYY-MM-DD"));
                       }
                       params.excludeDate = excludeDatesArray.join();
                   }
                    const projectBigType = this.props.detail.programType;
                    if (projectBigType === "inner") {
                        params.taskCode = this.props.detail.innerProjectType;
                        params.taskName = values.taskName;
                    } else {
                        params.taskCode = values.taskCode;
                    }

                    if (operateMode === 'edit') {
                        params.isNormal = 1;
                        params.modifier = user;
                        if (values.reasonTeam !== undefined) {
                            params.isNormal = 0;
                            params.reasonTeam = values.reasonTeam;
                            params.reasonType = values.reasonType;
                            params.reasonLevel = values.reasonLevel;
                            params.reasonDetail = values.reasonDetail;
                            params.abnormalType = 0;
                            if(hasAbnormalOwner) {
                              params.abnormalOwner = /\((\d+)\)/.exec(values.abnormalOwner)[1];
                            }
                        }
                        const editResp = await editTestPlan(params);
                        if (editResp.data) {
                            message.success("编辑计划成功!");
                            // this.props.dispatch({
                            //   type: ASYNC_GET_TEST_PLAN
                            // })
                            const res = await getTestPlan(params.programId);
                            this.props.getNewPlan(res.data);
                        } else {
                            message.error("编辑计划失败，请联系开发人员!");
                            return
                        }
                    } else if (operateMode === 'copy') {
                      delete params.id;
                      delete params.params;
                      delete params.ztTaskId;
                      delete params.testerName;
                      delete params.withTesterName;
                      params.programProcess = values.programProcess;
                      if (values.requires !== undefined && values.requires.length != 0 && values.requires.split(",").length >= 2) {
                        params.requireId = values.requires.split(",")[0];
                        params.requireRelate = values.requires.split(",")[1];
                        params.ztProjectId = values.requires.split(",")[2];
                      }
                      params.creator = user;
                      const createResp = await creatTestPlan(params);
                      if (createResp.data) {
                        message.success("新增计划成功!");
                        const res = await getTestPlan(params.programId);
                        this.props.getNewPlan(res.data);
                      } else {
                        message.error("新增计划失败，请联系开发人员!");
                        return
                      }
                    } else {
                        params.programProcess = values.programProcess;
                        if (values.requires !== undefined) {
                            params.requireId = values.requires.split(",")[0];
                            params.requireRelate = values.requires.split(",")[1];
                            params.ztProjectId = values.requires.split(",")[2];
                        }
                        params.creator = user;
                        const createResp = await creatTestPlan(params);
                        if (createResp.data) {
                            message.success("新增计划成功!");
                            const res = await getTestPlan(params.programId);
                            this.props.getNewPlan(res.data);
                        } else {
                            message.error("新增计划失败，请联系开发人员!");
                            return
                        }
                    }
                    this.resetModal();
                }
            }
        )
    };
    //modal中取消动作
    doCancel = () => {
        this.resetModal();
        this.setState({
            editingPlan: [],
        })
    };
    processChange = value => {
        const projectType = this.props.detail.programType;
        if (projectType === "inner") return;
        const processTask = this.props.allTasksWithProcess.find((obj) => {
            return value === obj.processCode
        });
        this.props.form.setFieldsValue({'taskCode': ""});
        this.setState({
            taskList: processTask.tasks,
        });
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
    selectReasonTeam = team => {
        this.setState({
            reasonTeam: team
        })
    };
    reasonLevelChange = level => {
        if (level !== 'P3')
            this.setState({reasonDetailRequired: true});
        else
            this.setState({reasonDetailRequired: false})
    };
    fetchDemands = async (keyword) => {
        this.setState({fetchData: [], fetching: true});
        let resp = await getZentaoStories(keyword);
        const data = resp.data.map(d => (
            {
                text: `[${d.ztStoryId}] ${d.ztStoryName} (项目 ${d.ztProjectName == null ? "未关联项目" : d.ztProjectName} )`,
                value: `${d.ztStoryId},${d.ztStoryName},${d.ztProjectId},${d.ztProjectName}`,
            }));
        if (data.length === 0) {
            this.setState({
                fetchData: [{text: "禅道无此需求", value: 0}], fetching: false
            });
        } else {
            this.setState({fetchData: data, fetching: false});
        }
    };

    resetModal() {
        this.props.form.resetFields();
        this.setState({
            addVisible: false,
            isRequireNeed: false,
            hasAbnormalOwner: true,
        })
    }

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

  onSelectChange = (selectedRowKeys, selectedRows) => {
    console.log('selectedRows changed: ', selectedRows);
    this.setState({ selectedRowKeys,selectedRows });
  };

  onBatchAssign = () => {
    const {selectedRows} = this.state;
    for(let i in selectedRows) {
      if(selectedRows[i].tester !== null) {
        this.setState({batchAbnormalView: true, batchAbnormalNeed: true});
        break;
      }
    }
    this.setState({ batchAssign:true })
  };

  onCancelBatchAssign = () => {
    this.setState({ batchAssign:false, batchAbnormalView:false, batchAbnormalNeed: false })
  };

  onBatchAbnormalViewChange = () => {
      this.setState({ batchAbnormalView: !this.state.batchAbnormalView })
  };

  doBatchAssign = e => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll(
      async (err, values) => {
        if (!err) {
          const { selectedRows, hasAbnormalOwner, batchAbnormalView } = this.state;
          let batch = [];
          if(selectedRows.length ===0) {
            message.info("请至少选中一条记录");
            return;
          }
          for(let i in selectedRows) {
            let r = selectedRows[i];
            if (r.tester !== null) {
              r.isNormal = 0;
              r.reasonTeam = values.reasonTeam;
              r.reasonType = values.reasonType;
              r.reasonLevel = values.reasonLevel;
              r.reasonDetail = values.reasonDetail;
              r.abnormalType = 0;
              if(hasAbnormalOwner && batchAbnormalView){
                r.abnormalOwner = /\((\d+)\)/.exec(values.abnormalOwner)[1];
              }
            } else {
              r.isNormal = 1;
            }
            r.tester = /\((\d+)\)/.exec(values.tester)[1];
            if (values.withTester !== undefined){
              r.withTester = /\((\d+)\)/.exec(values.withTester)[1];
            }
            r.modifier = this.props.staffInfo.code;
            batch.push(r);
          }
          let resp = await batchEditTestPlan(batch);
          if(resp.data) {
            message.success("成功" + resp.data.successCount + "条，失败" + resp.data.failCount + "条");
            const res = await getTestPlan(selectedRows[0].programId);
            this.props.getNewPlan(res.data);
          }
          this.setState({ batchAssign: false, selectedRows: [], selectedRowKeys: [], batchAbnormalView: false, batchAbnormalNeed: false});
        }
      });
  };

  onLinkToZtProject = () => {
    this.setState({ linkToZtProject:true })
  };

  onCancelLinkToZtProject = () => {
    this.setState({ linkToZtProject:false, fetchData: [] })
  };

  fetchZtProjects = async(keyword) => {
    if (keyword != null && keyword !== "") {
      this.setState({fetchZtProjects: [], fetching: true});
      let resp = await getZentaoProjects(keyword);
      const data = resp.data.map(d => (
        {
          text: `[${d.id}] ${d.name}`,
          value: `${d.id},${d.name}`,
        }));
      if (data.length === 0) {
        this.setState({
          fetchZtProjects: [{text: "禅道无此项目", value: 0}], fetching: false
        });
      } else {
        this.setState({fetchZtProjects: data, fetching: false});
      }
    }
  };

  doLinkToZtProject = e => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll(
      async (err, values) => {
        if(!err) {
          const { selectedRows } = this.state;
          let batch = [];
          if(selectedRows.length ===0) {
            message.info("请至少选中一条记录");
            return;
          }
          for(let i in selectedRows) {
            let r = selectedRows[i];
            r.ztProjectId = values.ztProject.split(",")[0];
            batch.push(r);
          }
          let resp = await batchEditTestPlan(batch);
          if(resp.data) {
            message.success("成功" + resp.data.successCount + "条，失败" + resp.data.failCount + "条");
            const res = await getTestPlan(selectedRows[0].programId);
            this.props.getNewPlan(res.data);
          }
          this.setState({ linkToZtProject: false, selectedRows: [], selectedRowKeys: [], fetchData: []});
        }
      }
    )
  };

  onBatchEdit = () => {
      this.setState({editableModal: true})
  };


  onCancelBatchEdit = () => {
      this.setState({editableModal: false})
  };

    render() {

        const {
            allDict,
            abnormalReasonTeam,
            abnormalReasonType,
            allTasksWithProcess,
            testPlanList,
            projectNodeList,
            isScheduled,
            detail,
        } = this.props;

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
        const formItemWithoutLabelLayout = {
            wrapperCol: {
              xs: {
                span: 24,
                offset: 0,
              },
              sm: {
                span: 12,
                offset: 7,
              },
            },
        };

        const projectBigType = detail.programType;
        const taskTitle = projectBigType === "inner" ? "任务名称" : "阶段任务";
        const taskKey = projectBigType === "inner" ? "taskName" : "taskCode";
        const columns = [
            {
                title: '项目阶段',
                dataIndex: 'programProcess',
                key: 'programProcess',
                width: '11%',
                ...this.getColumnSearchProps('项目阶段', (value, record) => {
                  if (projectBigType !== "inner") {
                    let val = "";
                    for(var v of allTasksWithProcess){
                      if (v.processCode === record.programProcess) {
                        val = v.processValue;
                        break;
                      }
                    }
                    return val.includes(value);
                  }
                  else {
                    return record.programProcess.includes(value);
                  }
                }),
                sorter: (a, b) => {
                    return colSort(a.programProcess, b.programProcess, "string");
                },
                render: (text, record, index) => {
                    if (projectBigType !== "inner") {
                        let val = "";
                        for(var v of allTasksWithProcess){
                            if (v.processCode === text) {
                                val = v.processValue;
                                break;
                            }
                        }
                        return val;
                    }
                    else {
                        return text;
                    }
                },
            },
            {
                title: '关联需求',
                key: 'requireId',
                width: '11%',
                ...this.getColumnSearchProps('需求',(value, record) => {
                    if(record.requireId == null) {
                        return "无".includes(value);
                    } else {
                      return (record.requireId + record.requireRelate).includes(value);
                    }
                }),
                sorter: (a, b) => {
                    return colSort(a.requireId, b.requireId, "string");
                },
                render: (text, record, index) => record.requireId == null ? "无" : "[" + record.requireId + "]" + record.requireRelate,
            },
            {
                title: taskTitle,
                dataIndex: taskKey,
                key: taskKey,
                width: '11%',
                ...this.getColumnSearchProps('任务', (value, record) => {
                    if (projectBigType !== "inner") {
                      let val = "";
                      for(var v of allDict){
                        if (v.dictCode === record.taskCode) {
                          val = v.dictValue;
                          break;
                        }
                      }
                      return val.includes(value);
                    } else {
                      return record.taskName.includes(value);
                    }
                }),
                sorter: (a, b) => {
                    if (projectBigType === "inner") {
                        return colSort(a.taskName, b.taskName, "string");
                    } else {
                        return colSort(a.taskCode, b.taskCode, "string");
                    }
                },
                render: (text, record, index) => {
                    if (projectBigType !== "inner") {
                        let val = "";
                        for(var v of allDict){
                            if (v.dictCode === text) {
                                val = v.dictValue;
                                break;
                            }
                        }
                        return val;
                    }
                    else {
                        return text;
                    }
                },
            },
            {
                title: '开始时间',
                dataIndex: 'startTm',
                key: 'startTm',
                width: '11%',
                sorter: (a, b) => {
                    return colSort(a.startTm, b.startTm, "date");
                },
                render: (text, record, index) => moment(text).format("YYYY-MM-DD")
            },
            {
                title: '结束时间',
                dataIndex: 'endTm',
                key: 'endTm',
                width: '11%',
                sorter: (a, b) => {
                    return colSort(a.endTm, b.endTm, "date");
                },
                render: (text, record, index) => moment(text).format("YYYY-MM-DD")
            },
            {
                title: '测试负责人',
                key: 'testerName',
                width: '11%',
                ...this.getColumnSearchProps('负责人',(value, record) => {
                  if (record.tester != null && record.tester !== "") {
                    return ("(" + record.tester + ")" + record.testerName).includes(value);
                  }
              }),
                sorter: (a, b) => {
                    return colSort(a.testerName, b.testerName, "string");
                },
                render: (text, record, index) => {
                    if (record.tester != null && record.tester !== "") {
                        return "(" + record.tester + ")" + record.testerName
                    }
                }
            },
            {
                title: '参与测试人员',
                key: 'withTesterName',
                width: '11%',
                sorter: (a, b) => {
                    return colSort(a.withTesterName, b.withTesterName, "string");
                },
                render: (text, record, index) => {
                    if (record.withTester != null && record.withTester !== "") {
                        return "(" + record.withTester + ")" + record.withTesterName
                    }
                }
            },
            {
                title: '工时(H)',
                dataIndex: 'expectHour',
                key: 'expectHour',
                width: '6%',
                sorter: (a, b) => {
                  return colSort(a.expectHour, b.expectHour, "number");
                }
            },
            {
                title: '操作',
                key: 'action',
                render: (text, record, index) => {
                    return (
                        <span>
                          <a onClick={() => this.editPlan(record)}>编辑</a>
                          <Divider type="vertical"/>
                          <a onClick={() => this.copyPlan(record)}>复制</a>
                          {
                            record.status === 'init' &&
                              <span>
                                <Divider type="vertical"/>
                                <Popconfirm title="是否要删除此行？" onConfirm={() => this.deletePlan(record)} okText="确认" cancelText="取消">
                                  <a>删除</a>
                                </Popconfirm>
                              </span>
                          }
                        </span>
                    );
                },
            },
        ];

        const {
            loading, addVisible, taskList, editingPlan, singleAbnormalNeed, batchAssign, batchAbnormalView, batchAbnormalNeed, linkToZtProject, fetchZtProjects,
            staffList, reasonDetailRequired, fetching, fetchData, selectedRows, selectedRowKeys, operateMode, hasAbnormalOwner,
          } = this.state;

        const rowSelection = {
          selectedRowKeys,
          selectedRows,
          onChange: this.onSelectChange,
        };

        let includeSelectDates = [];
        let excludeSelectDates = [];
        if(editingPlan && editingPlan.includeDate && editingPlan.includeDate !== ""){
            let includeDatesArray = editingPlan.includeDate.split(",");
            for(let dateString of includeDatesArray){
                includeSelectDates.push(moment(dateString,'YYYY-MM-DD').toDate());
            }
        }
        if(editingPlan && editingPlan.excludeDate && editingPlan.excludeDate !== ""){
            let excludeDatesArray = editingPlan.excludeDate.split(",");
            for(let dateString of excludeDatesArray){
                excludeSelectDates.push(moment(dateString,'YYYY-MM-DD').toDate());
            }
        }

        const viewDisable = isScheduled && operateMode === 'edit' && editingPlan.tester != null && editingPlan.tester != "";
        const singleAbnormalCheckView = editingPlan.expectHour != null && viewDisable;
        const reasonView = singleAbnormalCheckView  && singleAbnormalNeed;
        const isProgramProcessRequired = projectBigType !== "inner";
        const hasSelected = selectedRows.length > 0;

        return <Fragment>
          <div>
            <Row style={{marginBottom:2}}>
                <Button
                  type="primary"
                  onClick={() => this.addPlan()}
                >
                  新增计划
                </Button>
                { hasSelected &&
                <Button type={"primary"}
                        onClick={this.onBatchEdit}
                        style={{ marginLeft: 8 }}
                >
                    批量排期
                </Button>
                }
              { hasSelected &&
                <Button type={"primary"}
                        onClick={this.onBatchAssign}
                        style={{ marginLeft: 8 }}
                >
                  批量指派
                </Button>
              }
              { hasSelected &&
              <Button type={"primary"}
                      onClick={this.onLinkToZtProject}
                      style={{ marginLeft: 8 }}
              >
                关联禅道项目
              </Button>
              }
            </Row>
            <Row style={{marginBottom:2}}>
              <span style={{ marginLeft: 8 }}>
                {hasSelected ? `Selected ${selectedRows.length} items` : ''}
              </span>
            </Row>
          </div>
          <div>
            <Table
              loading={loading}
              rowSelection={rowSelection}
              rowKey={record =>record.id}
              columns={columns}
              dataSource={testPlanList.programTasks}
              pagination={false}
            />
          </div>
            {
                this.state.editableModal &&
                <Modal title={'批量排期'}
                       visible={this.state.editableModal}
                       onCancel={this.onCancelBatchEdit}
                       width={'80%'}
                       footer={null}
                       style={{ top: 20 }}>
                    <EditableTestPlan editing={selectedRows}
                                      projectType={projectBigType}
                                      onCancel={this.onCancelBatchEdit}
                                      getNewPlan={newPlan => this.getNewPlan(newPlan)}
                    />
                </Modal>
            }
          {
            batchAssign && <Modal title={'批量指派'}
                                  visible={batchAssign}
                                  width={550}
                                  onCancel={this.onCancelBatchAssign}
                                  okText={'确认'}
                                  cancelText={'取消'}
                                  onOk={this.doBatchAssign}
                                  style={{ top: 20 }}>
              <Form>
                <FormItem label="测试负责人" {...formItemLayout}>
                  {getFieldDecorator('tester', {
                    rules: [
                      {
                        required: true, message: '测试负责人必填!',
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
                <FormItem label="参与人员" {...formItemLayout}>
                  {getFieldDecorator('withTester', {
                  })(
                    <Select
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
                {
                  batchAbnormalNeed &&
                    <div style={{marginLeft: 60, marginBottom: 20}}>
                        <Checkbox checked={this.state.batchAbnormalView} onChange={this.onBatchAbnormalViewChange}></Checkbox>
                        <span style={{marginLeft: 10}}>选中记录包含已指派的任务，我要填写变更原因</span>
                    </div>
                }
                {
                  batchAbnormalView && <FormItem label="变更原因" {...formItemLayout}>
                    {getFieldDecorator('reasonTeam', {
                      rules: [
                        {
                          required: true, message: '变更原因必选!',
                        }
                      ],
                    })(
                      < Select
                        id="reasonTeam"
                        placeholder="一级分类"
                        showSearch
                        onChange={value => this.selectReasonTeam(value)}
                        optionFilterProp="children"
                      >
                        {abnormalReasonTeam.map((t) => (
                          <Option value={t.reasonTeamCode} key={t.reasonTeamCode}>
                            {t.reasonTeamName}
                          </Option>
                        ))}
                      </Select>
                    )}
                  </FormItem>
                }
                {
                  batchAbnormalView && <FormItem {...formItemWithoutLabelLayout}>
                      {getFieldDecorator('reasonType', {
                        rules: [
                          {
                            required: true, message: '变更二级原因必选!',
                          }
                        ],
                      })(
                        <Select
                          id="reasonType"
                          placeholder="二级分类"
                          showSearch
                          optionFilterProp="children"
                        >
                          {abnormalReasonType.map((s) => (
                            s.reasonTeamCode === this.state.reasonTeam &&
                            <Option value={s.reasonTypeCode} key={s.reasonTypeCode}>
                              {s.reasonTypeName}
                            </Option>
                          ))}
                        </Select>
                      )}
                </FormItem>
                }
                {
                  batchAbnormalView && <FormItem {...formItemLayout} label="责任人">
                    {getFieldDecorator('hasAbnormalOwner', {
                      rules: [{required: hasAbnormalOwner, message: '责任人不能为空!'}],
                      initialValue: 1
                    })(
                      <RadioGroup onChange={() => {
                        this.setState({hasAbnormalOwner: !hasAbnormalOwner})
                      }}>
                        <Radio value={1}>关联责任人</Radio>
                        <Radio value={0}>无责任人</Radio>
                      </RadioGroup>
                    )}
                  </FormItem>
                }
                {batchAbnormalView && hasAbnormalOwner &&
                <FormItem wrapperCol={{span: 7, offset: 4}}>
                  {getFieldDecorator('abnormalOwner', {
                    rules: [{required: hasAbnormalOwner, message: '责任人不能为空!'}],
                  })(
                    <Select
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
                {batchAbnormalView && <FormItem label="影响级别" {...formItemLayout}>
                  {getFieldDecorator('reasonLevel', {
                    initialValue: 'P3'
                  })(
                    <Select
                      id="reasonLevel"
                      showSearch
                      onChange={level => this.reasonLevelChange(level)}
                      optionFilterProp="children"
                    >
                      {reasonLevel.map((m) =>
                        <Option value={m} key={new Date()}>{m}</Option>
                      )}
                    </Select>
                  )}
                </FormItem>
                }
                {batchAbnormalView && <FormItem label="详细说明" {...formItemLayout}>
                  {getFieldDecorator('reasonDetail', {
                    rules: [{required: reasonDetailRequired, message: '影响级别大于P3该项必填'}],
                  })(
                    <Input.TextArea rows={4}/>
                  )}
                </FormItem>
                }
              </Form>
            </Modal>
          }
          {
            linkToZtProject &&
            <Modal title={'关联禅道项目'}
                   visible={linkToZtProject}
                   width={550}
                   onCancel={this.onCancelLinkToZtProject}
                   okText={'确认'}
                   cancelText={'取消'}
                   onOk={this.doLinkToZtProject}>
              <Form>
                <FormItem label="禅道项目" {...formItemLayout}>
                  {getFieldDecorator('ztProject', {
                    rules: [
                      {
                        required: true, message: '必选!',
                      }
                    ],
                  })(<Select
                      showSearch
                      placeholder="项目名称"
                      onSearch={this.fetchZtProjects}
                    >
                      {fetchZtProjects.map(fetch => (
                        <Option key={fetch.value}>{fetch.text}</Option>
                      ))}
                    </Select>
                  )}
                </FormItem>
              </Form>
            </Modal>
          }
        {addVisible && <Modal
          title={operateMode === 'edit' ? '编辑计划' : '新增计划'}
          visible={addVisible}
          footer={null}
          width={550}
          onCancel={() => this.doCancel()}
          style={{ top: 20 }}
        >
          <Form layout="horizontal" onSubmit={this.doAdd}>
            <FormItem label="项目阶段" {...formItemLayout}>
              {getFieldDecorator('programProcess', {
                rules: [
                  {
                    required: isProgramProcessRequired, message: '项目阶段必填!',
                  }
                ],
                initialValue: editingPlan.programProcess,
              })(
                <Select
                  id="tasks"
                  showSearch
                  style={{width: 200}}
                  placeholder="选择阶段"
                  optionFilterProp="children"
                  onChange={value => this.processChange(value)}
                  disabled={operateMode === 'edit'}
                >
                  {projectBigType === "inner" &&
                  projectNodeList.map((node) => {
                    return <Option value={node} key={node}>{node}</Option>
                  })
                  }
                  {projectBigType !== "inner" && allTasksWithProcess.map(item => {
                    return <Option value={item.processCode} title={item.processValue}
                                   key={item.processCode}>{item.processValue}</Option>
                  })}
                </Select>
              )}
            </FormItem>
            <FormItem label={"关联需求"}  {...formItemLayout}>
              {getFieldDecorator('requires', {
                initialValue: editingPlan.requireId == null && operateMode === 'edit' ? "无关联需求" : editingPlan.requireRelate,
              })(
                <Select
                  showSearch={true}
                  placeholder="输入关键字搜索禅道需求"
                  notFoundContent={fetching ? <Spin size="small"/> : null}
                  filterOption={false}
                  showArrow={false}
                  onSearch={this.fetchDemands}
                  style={{width: '100%'}}
                  disabled={operateMode === 'edit'}
                >
                  {fetchData.map(fetch =>
                    <Option key={fetch.value}>{fetch.text}</Option>
                  )}
                </Select>
              )}
            </FormItem>
            {projectBigType === "inner" &&
            <FormItem label="任务名称" {...formItemLayout}>
              {getFieldDecorator('taskName', {
                rules: [
                  {
                    required: true, message: '任务名称必填!',
                  }
                ],
                initialValue: editingPlan.taskName,
              })(
                <Input/>
              )}
            </FormItem>
            }
            {projectBigType !== "inner" && <FormItem label="阶段任务" {...formItemLayout}>
              {getFieldDecorator('taskCode', {
                rules: [
                  {
                    required: true, message: '阶段任务必填!',
                  }
                ],
                initialValue: editingPlan.taskCode
              })(
                <Select
                  id="tasks"
                  showSearch
                  style={{width: 200}}
                  placeholder="选择任务"
                  optionFilterProp="children"
                  disabled={viewDisable}
                >
                  {taskList.map(item => {
                    return <Option value={item.task} title={item.taskValue}
                                   key={item.task}>{item.taskValue}</Option>
                  })}
                </Select>
              )}
            </FormItem>
            }
            <FormItem label="开始时间" {...formItemLayout}>
              {getFieldDecorator('startTm', {
                rules: [
                  {
                    required: true, message: '开始时间必填!',
                  }
                ],
                initialValue: operateMode !== 'add' ? moment(editingPlan.startTm) : moment(new Date())
              })(
                <DatePicker
                  placeholder="开始时间"/>
              )}
            </FormItem>
            <FormItem label="结束时间" {...formItemLayout}>
              {getFieldDecorator('endTm', {
                rules: [
                  {
                    required: true, message: '结束时间必填!',
                  }
                ],
                initialValue: operateMode !== 'add' ? moment(editingPlan.endTm) : moment(new Date())
              })(
                <DatePicker
                  placeholder="结束时间"/>
              )}
            </FormItem>
              <FormItem label="排除节假日" {...formItemLayout}>
                  <MultipleDatePicker selectedDates={excludeSelectDates} onSubmit={dates => this.state.excludeDate = dates}/>
              </FormItem>
              <FormItem label="包括特殊周末" {...formItemLayout}>
                  <MultipleDatePicker selectedDates={includeSelectDates} onSubmit={dates => this.state.includeDate = dates}/>
              </FormItem>
            <FormItem label="测试负责人" {...formItemLayout}>
              {getFieldDecorator('tester', {
                rules: [
                  {
                    required: true, message: '测试负责人必填!',
                  }
                ],
                initialValue: operateMode !== 'add' && editingPlan.tester ? "(" + editingPlan.tester + ")" + editingPlan.testerName : undefined
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
            <FormItem label="参与人员" {...formItemLayout}>
              {getFieldDecorator('withTester', {
                initialValue: operateMode !== 'add' && editingPlan.withTester ? "(" + editingPlan.withTester + ")" + editingPlan.withTesterName : undefined
              })(
                <Select
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
            <FormItem label="工时" {...formItemLayout}>
              {getFieldDecorator('expectHour', {
                rules: [
                  {
                    required: true, message: '工时必填!',
                  }
                ],
                initialValue: editingPlan.expectHour
              })(<InputNumber step={0.5}/>)}
            </FormItem>
              {
                  singleAbnormalCheckView &&
                  <div style={{marginLeft: 80, marginBottom: 20}}>
                      <Checkbox checked={this.state.singleAbnormalNeed} onChange={this.onSingleAbnormalNeedChange}></Checkbox>
                      <span style={{marginLeft: 10}}>排期变更，我要填写变更原因</span>
                  </div>
              }
            {
              reasonView && <FormItem label="变更原因" {...formItemLayout}>
                {getFieldDecorator('reasonTeam', {
                  rules: [
                    {
                      required: true, message: '变更原因必选!',
                    }
                  ],
                })(
                  < Select
                    id="reasonTeam"
                    placeholder="一级分类"
                    showSearch
                    onChange={value => this.selectReasonTeam(value)}
                    optionFilterProp="children"
                  >
                    {abnormalReasonTeam.map((t) => (
                      <Option value={t.reasonTeamCode} key={t.reasonTeamCode}>
                        {t.reasonTeamName}
                      </Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            }
            {
              reasonView && <FormItem {...formItemWithoutLabelLayout}>
                {getFieldDecorator('reasonType', {
                  rules: [
                    {
                      required: true, message: '变更二级原因必选!',
                    }
                  ],
                })(
                  <Select
                    id="reasonType"
                    placeholder="二级分类"
                    showSearch
                    optionFilterProp="children"
                  >
                    {abnormalReasonType.map((s) => (
                      s.reasonTeamCode === this.state.reasonTeam &&
                      <Option value={s.reasonTypeCode} key={s.reasonTypeCode}>
                        {s.reasonTypeName}
                      </Option>
                    ))}
                  </Select>
                )}
              </FormItem>
            }
            {reasonView &&
            <FormItem {...formItemLayout} label="责任人">
              {getFieldDecorator('hasAbnormalOwner', {
                rules: [{required: hasAbnormalOwner, message: '责任人不能为空!'}],
                initialValue: 1
              })(
                <RadioGroup onChange={() => {
                  this.setState({hasAbnormalOwner: !hasAbnormalOwner})
                }}>
                  <Radio value={1}>关联责任人</Radio>
                  <Radio value={0}>无责任人</Radio>
                </RadioGroup>
              )}
            </FormItem>
            }
            {reasonView && hasAbnormalOwner &&
              <FormItem {...formItemWithoutLabelLayout}>
              {getFieldDecorator('abnormalOwner', {
                rules: [{required: hasAbnormalOwner, message: '责任人不能为空!'}],
              })(
                <Select
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
            {reasonView && <FormItem label="影响级别" {...formItemLayout}>
              {getFieldDecorator('reasonLevel', {
                initialValue: 'P3'
              })(
                <Select
                  id="reasonLevel"
                  showSearch
                  onChange={level => this.reasonLevelChange(level)}
                  optionFilterProp="children"
                >
                  {reasonLevel.map((m) =>
                    <Option value={m} key={new Date()}>{m}</Option>
                  )}
                </Select>
              )}
            </FormItem>
            }
            {reasonView && <FormItem label="详细说明" {...formItemLayout}>
              {getFieldDecorator('reasonDetail', {
                rules: [{required: reasonDetailRequired, message: '影响级别大于P3该项必填'}],
              })(
                <Input.TextArea rows={4}/>
              )}
            </FormItem>
            }
            <Row type="flex" justify="end" style={{marginTop: '36px'}}>
              <Col span={4}>
                <Button
                  style={{marginRight: '8px'}}
                  onClick={() => this.doCancel()}
                >取消</Button>
              </Col>
              <Col span={4}>
                <Button
                  style={{marginRight: '12px'}}
                  type="primary"
                  htmlType="submit"
                >确认</Button>
              </Col>
            </Row>
          </Form>
        </Modal>
        }
        <Button
          style={{width: '100%', marginTop: 16, marginBottom: 8}}
          type="dashed"
          onClick={() => this.addPlan()}
          icon="plus"
        >
          新增计划
        </Button>
      </Fragment>;
    }
}
