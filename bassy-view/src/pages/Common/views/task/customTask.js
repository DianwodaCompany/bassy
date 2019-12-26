import React from "react";
import {Button, DatePicker, Form, Input, InputNumber, Modal, Select,message} from "antd";
import {connect} from "react-redux";
import moment from "moment/moment";
import {creatTestPlan, editTestPlan, getStaffList} from "../../../../apis/index";

const FormItem = Form.Item;
const Option = Select.Option;

@Form.create()
@connect(
  state => ({
    staffInfo: state.common.staffInfo,
    allDict: state.allDict,
  })
)
export default class CustomTask extends React.Component {

  state = {
    staffList: [{code: '', name: ''}],
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

  submit = e => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll(
      async (err, values) => {
        if (!err) {
          this.setState({loading: true});
          const { customTaskInfo, toMe, staffInfo, operateMode} = this.props;
          let user = staffInfo.code;
          const params = Object.assign({}, customTaskInfo);
          params.startTm = new Date(moment(values.startTm).startOf('day').format('YYYY-MM-DD HH:mm:ss'));
          params.endTm = new Date(moment(values.endTm).endOf('day').format('YYYY-MM-DD HH:mm:ss'));
          if(toMe){
            params.tester = user;
          } else {
            params.tester = /\((\d+)\)/.exec(values.tester)[1];
          }
          params.expectHour = values.expectHour;
          params.programId = 0;
          params.taskName = values.taskName;
          params.taskCode = values.taskCode;
          params.isNormal = 1;
          if(operateMode === 'edit') {
            params.modifier = user;
            delete params.testerName;
            const editResp = await editTestPlan(params);
            if (editResp.data) {
              message.success("编辑自定义任务成功!");
              this.props.callback();
            } else {
              message.error("编辑自定义任务失败，请联系开发人员!");
              return
            }
          } else {
            params.creator = user;
            const createResp = await creatTestPlan(params);
            if (createResp.data) {
              message.success("新增自定义任务成功!");
              this.props.callback();
            } else {
              message.error("新增自定义任务失败，请联系开发人员!");
              return
            }
          }
          this.setState({loading: false});
          this.props.onCancel();
          this.props.callback();
        }
      }
    )
  }
  render () {
    const { getFieldDecorator } = this.props.form;
    const { customTaskInfo, toMe, allDict, operateMode} = this.props;
    const { staffList, loading } = this.state;
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

    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };
    return (

      <Modal
        title={operateMode === 'edit' ? '编辑自定义任务' : '新增自定义任务'}
        visible={true}
        footer={null}
        width={550}
        onCancel={this.props.onCancel}
      >
        <Form layout="horizontal" onSubmit={this.submit}>
          <FormItem label={"任务类型"} {...formItemLayout}>
            {
              getFieldDecorator("taskCode",
                {
                  rules:[{
                    required: true, message: "任务类型必选"
                  }],
                  initialValue: customTaskInfo.taskCode,
                })
              ( <Select>
                  {allDict.map(d => {
                    if (d.dictGroup === "INNER_PROJECT_TYPE") {
                      return <Option key={d.dictCode}>{d.dictValue}</Option>
                    }
                  })}
                </Select>
              )
            }
          </FormItem>
          <FormItem label="任务名称" {...formItemLayout}>
            {getFieldDecorator('taskName', {
              rules: [
                {
                  required: true, message: '任务必填!',
                }],
              initialValue: customTaskInfo.taskName,
            })
            (
              <Input/>
            )
            }
          </FormItem>
          <FormItem label="开始时间" {...formItemLayout}>
            {getFieldDecorator('startTm', {
              rules: [
                {
                  required: true, message: '开始时间必填!',
                }],
              initialValue: operateMode === 'edit' ? moment(customTaskInfo.startTm) : (customTaskInfo.startTm ? moment(customTaskInfo.startTm) : moment(new Date()))
            })
            (
              <DatePicker placeholder="开始时间"/>
            )
            }
          </FormItem>
          <FormItem label="结束时间" {...formItemLayout}>
            {getFieldDecorator('endTm', {
              rules: [
                {
                  required: true, message: '结束时间必填!',
                }],
              initialValue: operateMode === 'edit' ? moment(customTaskInfo.endTm) : (customTaskInfo.endTm ? moment(customTaskInfo.endTm) : moment(new Date()))
            })
            (
              <DatePicker placeholder="结束时间"/>
            )
            }
          </FormItem>
          {!toMe && <FormItem label="指派给" {...formItemLayout}>
            {getFieldDecorator('tester', {
              rules: [
                {
                  required: true, message: '任务归属人必填!',
                }],
              initialValue: operateMode === 'edit' && customTaskInfo.tester ? "(" + customTaskInfo.tester + ")" + customTaskInfo.testerName : undefined
            })
            (<Select
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
            )
            }
          </FormItem>}
          <FormItem label="工时" {...formItemLayout}>
            {getFieldDecorator('expectHour', {
              rules: [
                {
                  required: true, message: '工时必填!',
                }],
              initialValue: customTaskInfo.expectHour
            })
            (<InputNumber step={0.5}/>)
            }
          </FormItem>
          {operateMode === 'edit' && <FormItem label="变更说明" {...formItemLayout}>
            {getFieldDecorator('reasonDetail')(
              <Input.TextArea rows={4}/>
            )}
          </FormItem>
          }
          <FormItem {...tailFormItemLayout}>
            <Button type="primary" htmlType="submit" loading={loading}>
              确认
            </Button>
          </FormItem>
        </Form>
      </Modal>

    )
  }
}