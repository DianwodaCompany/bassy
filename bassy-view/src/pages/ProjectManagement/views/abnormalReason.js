import React from "react";
import {Col, Form, Input, message, Modal, Row, Select} from "antd";
import {updateProgramStatus, getStaffList} from "../../../apis/index";
import connect from "react-redux/es/connect/connect";
import {Radio} from "antd/lib/index";

const FormItem = Form.Item;
const TextArea = Input.TextArea;
const Option = Select.Option;
const RadioGroup = Radio.Group;

const reasonLevel = ["P1", "P2", "P3"];

@Form.create()
@connect(state => ({
    staffInfo: state.common.staffInfo,
    abnormalReasonTeam: state.common.abnormalReasonTeam,
    abnormalReasonType: state.common.abnormalReasonType
}))
export default class AbnormalReason extends React.Component {

    state = {
        showVisible: false,
        reasonTeam: "",
        reasonType: "",
        reasonLevel: "",
        reasonTypes: [],
        reasonLevels: [],
        reasonDetail: "",
        hasAbnormalOwner: true,
        staffList: [{code: '', name: ''}],
    };

    selectReasonTeam = (reasonTeam) => {
        this.setState({
            reasonTeam: reasonTeam
        })
    };

    selectReasonType = (reasonType) => {
        this.setState({reasonType})
    };

    selectReasonLevel = (reasonLevel) => {
        this.setState({reasonLevel})
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

    setAbnormal = async () => {
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                const param = {
                    programId: this.props.programId,
                    status: this.props.status,
                    person: this.props.staffInfo.code,
                    isNormal: 0,
                    abnormalOwner: this.state.hasAbnormalOwner ? /\((\d+)\)/.exec(values.abnormalOwner)[1] :null,
                };
                const params = Object.assign({}, values, param);
                console.log("params: ", params);
                const res = await updateProgramStatus(params);
                if (res.errCode === 1) {
                    message.success("设置成功！")
                } else {
                    message.error("设置失败！")
                }
                this.props.resetProjectManagement()
            }
        });
    };

    setReasonDetail = (value) => {
        this.setState({reasonDetail: value})
    };

    openConfirmModel = () => {
      this.props.form.validateFieldsAndScroll( (err) => {
          if(!err) {
            this.setState({showVisible: true})
          }
        })
    };

    cancelConfirmModel = () => {
        this.setState({
            showVisible: false
        })
    };

    render() {
        const {abnormalReasonTeam, abnormalReasonType} = this.props;
        const {showVisible, staffList, hasAbnormalOwner} = this.state;
        const {getFieldDecorator} = this.props.form;
        return (
            <div>
                <Modal
                    title={'环节异常'}
                    width={600}
                    visible={true}
                    onCancel={this.props.cancelUpdateStatus}
                    onOk={() => this.openConfirmModel()}
                >
                  <FormItem label={"异常原因"}>
                    {getFieldDecorator('reasonTeam', {
                      rules: [{required: true, message: '该项必填'}],
                    })(
                      < Select
                        id="reasonTeam"
                        showSearch
                        rowKey="id"
                        onChange={this.selectReasonTeam}
                        optionFilterProp="children"
                        style={{width: "100%"}}
                      >
                        {abnormalReasonTeam.map((t) => (
                          <Option value={t.reasonTeamCode} key={t.reasonTeamCode}>
                            {t.reasonTeamName}
                          </Option>
                        ))}
                      </Select>
                    )}
                  </FormItem>
                  <FormItem>
                    {getFieldDecorator('reasonType', {
                      rules: [{required: true, message: '该项必填'}],
                    })(
                      <Select
                        id="reasonType"
                        showSearch
                        rowKey="id"
                        onChange={this.selectReasonType}
                        style={{width: "100%"}}
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
                  <FormItem label="责任人">
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
                  {
                    hasAbnormalOwner &&
                    <FormItem>
                      {getFieldDecorator('abnormalOwner', {
                        rules: [{required: hasAbnormalOwner, message: '责任人不能为空!'}],
                      })(
                        <Select
                          showSearch
                          placeholder="工号或姓名"
                          onSearch={this.changPersonKeyWord}
                          style={{width: "100%"}}
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
                  <FormItem label={"影响等级"}>
                    {getFieldDecorator('reasonLevel', {
                      rules: [{required: true, message: 'reasonTeam必填'}],
                    })(
                      <Select
                        id="reasonLevel"
                        showSearch
                        rowKey="id"
                        onChange={this.selectReasonLevel}
                        style={{width: "100%"}}
                        optionFilterProp="children"
                      >
                        {reasonLevel.map((m) =>
                          <Option value={m} key={new Date()}>{m}</Option>
                        )}
                      </Select>
                    )}
                  </FormItem>
                  <FormItem label={"详细说明"}>
                    {getFieldDecorator('reasonDetail', {
                      rules: [{required: true, message: '该项必填'}],
                    })(
                      <TextArea rows={4} onChange={this.setReasonDetail}/>
                    )}
                  </FormItem>

                </Modal>
                <Modal
                    title={'确认异常'}
                    width={600}
                    visible={showVisible}
                    onCancel={() => this.cancelConfirmModel()}
                    onOk={this.setAbnormal}
                >
                    <p>该项目所有未结束的任务也都将{this.props.status}，你确定吗？</p>
                </Modal>
            </div>

        )
    }
}