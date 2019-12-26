import React from "react";
import {Button, Card, Form, Input, Radio, Select} from "antd";
import KeyNodes from "./templateContent/keyNodes";
import Requires from "./templateContent/requires";
import Persons from "./templateContent/persons"
import {connect} from "react-redux";
import {getAllDictByGroup} from "../../../apis/index";
import {addTemplateContentComponentData} from '../../ConfigManagement/actions';
import {addProjectContentComponentData} from "../../ProjectManagement/actions";

const TextArea = Input.TextArea;
const FormItem = Form.Item;
const Option = Select.Option;
const RadioGroup = Radio.Group;
const formLayout = {
    labelCol: {
        span: 5
    },
    wrapperCol: {
        span: 10
    }
};

@connect(state => ({
    allStages: state.common.allStages,
    currentView: state.common.currentView,
    mode: state.common.currentView === "ConfigManagement" ? state.project.mode : state.configManagement.mode,
    detail: state.common.currentView === "ConfigManagement" ? state.project.projectDetail : state.configManagement.projectTemplateDetail
}))
@Form.create()
export default class TemplateContent extends React.Component {
    state = {
        requires: [],
        coreNodes: [],
        persons: {}
    };
    getRequires = requires => {
        this.setState({requires: requires});
    };
    getCoreNodes = coreNodes => {
        this.setState({coreNodes});
    };
    getPersons = persons => {
        this.setState({persons})
    };
    collectData = () => {
        const {requires, coreNodes} = this.state;
        const {detail} = this.props;
        const {persons} = this.state;
        if (persons.PD !== undefined) {
            persons.PD = persons.PD.split(" ")[0]
        } else {
            persons.PD = ""
        }
        if (persons.PM !== undefined) {
            persons.PM = persons.PM.split(" ")[0]
        } else {
            persons.PM = ""
        }
        if (persons.DO !== undefined) {
            persons.DO = persons.DO.split(" ")[0]
        } else {
            persons.DO = ""
        }
        if (persons.TO !== undefined) {
            persons.TO = persons.TO.split(" ")[0]
        } else {
            persons.TO = ""
        }
        return {
            requires: this.state.requires,
            coreNodes: coreNodes.length > 0 ? coreNodes : detail.coreNodes,
            persons: this.state.persons
        };
    };

    onChangeLoop = (val) => {
        this.setState({isLoop: val});
    };

    componentWillMount = async () => {
        if(this.props.currentView === "ConfigManagement"){
            this.props.dispatch(addProjectContentComponentData(
                {
                    form:this.props.form,
                    collectData:this.collectData
                }
            ));
        }
        else {
            this.props.dispatch(addTemplateContentComponentData(
                {
                    form:this.props.form,
                    collectData:this.collectData
                }
            ));
        }

        const dictList = await getAllDictByGroup("INNER_PROJECT_TYPE");
        this.setState({innerProjectTypeList: dictList.data})
    };

    getRemarkComponent = () => {
        const {getFieldDecorator} = this.props.form;
        const {detail, mode} = this.props;
        const remark = detail ? detail.remark : "";
        return (
            <FormItem {...formLayout} label="备注">
                {getFieldDecorator("remark", {
                    initialValue: remark
                })(<TextArea rows={4} disabled={mode === "view" || mode === "edit"}/>)}
            </FormItem>
        )
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {detail, mode, currentView} = this.props;
        const bigType = detail.programType ? detail.programType  : detail.parentCode;
        const {innerProjectTypeList} = this.state;
        if (mode !== "add" && !detail) return (null);
        const detailLoop = detail ? detail.isLoop : "N";
        const isLoop = this.state.isLoop ? this.state.isLoop : detailLoop;
        const workId = detail ? detail.workId : "";
        let innerProjectInitVal = "";
        if (innerProjectTypeList) {
            for(var innerProject of innerProjectTypeList){
                if (innerProject.dictCode === detail.innerProjectType) {
                    innerProjectInitVal = innerProject.dictCode;
                    break;
                }
            }
        }
        if (bigType === "inner") {
            return (
                <Card title="项目详情">
                    {currentView==="ProjectTemplate" && <FormItem {...formLayout} label="是否循环任务">
                        {getFieldDecorator("isLoop", {
                            rules: [
                                {
                                    required: true
                                }
                            ],
                            initialValue: isLoop
                        })(
                            <Select
                                style={{width: 200}}
                                placeholder="选择是否循环任务"
                                onChange={this.onChangeLoop}
                                optionFilterProp="children"
                                disabled={mode === "view"  || mode === "edit"}
                            >
                                <Option value="N" key="1">否</Option>
                                <Option value="Y" key="2">是</Option>
                            </Select>
                        )}
                    </FormItem>}
                    {currentView==="ConfigManagement" && <Persons
                        getPersons={this.getPersons}
                    />}
                    {isLoop === "N" && <KeyNodes
                        getCoreNodes={this.getCoreNodes}
                    />}
                    <FormItem {...formLayout} label="内部项目类型">
                        {getFieldDecorator("innerProjectType", {
                            rules: [
                                {
                                    required: true
                                }
                            ],
                            initialValue: innerProjectInitVal
                        })(
                            <Select
                                style={{width: 200}}
                                placeholder="选择内部项目类型"
                                optionFilterProp="children"
                                disabled={mode === "view" || mode === "edit"}
                            >
                                {this.state.innerProjectTypeList && this.state.innerProjectTypeList.map((dict) =>
                                    <Option value={dict.dictCode} key={dict.id}>{dict.dictValue}</Option>
                                )}
                            </Select>
                        )}
                    </FormItem>
                    {isLoop === "Y" && <FormItem {...formLayout} label="每日任务数量">
                        {getFieldDecorator("dailyTaskNum", {
                            rules: [
                                {
                                    required: false,
                                    message: "请输入每日任务数量！"
                                }
                            ],
                            initialValue: detail.dailyTaskNum
                        })(<Input placeholder="请输入每日任务数量" disabled={mode === "view" || mode === "edit"}/>)}
                    </FormItem>}
                    {isLoop === "Y" && <FormItem {...formLayout} label="每个任务工作量">
                        {getFieldDecorator("eachTaskWorkHour", {
                            rules: [
                                {
                                    required: false,
                                    message: "请输入每个任务工作量！"
                                }
                            ],
                            initialValue: detail.eachTaskWorkHour
                        })(<Input placeholder="请输入每个任务工作量" disabled={mode === "view" || mode === "edit"}/>)}
                    </FormItem>}
                    {this.getRemarkComponent()}
                </Card>
            );
        }
        else {
            return (
                <Card title="流程">
                    {currentView==="ConfigManagement" && <Persons
                        getPersons={this.getPersons}
                    />}
                    <KeyNodes
                        getCoreNodes={this.getCoreNodes}
                    />
                    {bigType === "normal" && currentView==="ConfigManagement" && <FormItem {...formLayout} label="关联工单ID">
                        {getFieldDecorator("workId")(
                            <div>
                                <Input
                                    style={{width: 200, marginRight: 6}}
                                    placeholder="关联工单ID"
                                    defaultValue={workId}
                                    disabled={mode === "view" || mode === "edit"}
                                />
                                <Button
                                    icon="sync"
                                    type="primary"
                                    size="small"
                                    onClick={this.verifyWorksheet}
                                    disabled={mode === "view" || mode === "edit"}
                                >
                                    校验
                                </Button>
                            </div>
                        )}
                    </FormItem>
                    }
                    {currentView==="ConfigManagement" && <Requires
                        getRequires={this.getRequires}
						history={this.props.history}
                    />}
                    {bigType === "urgent" && (
                        <div>
                            <FormItem {...formLayout} label="紧急原因">
                                {getFieldDecorator("urgentReason")(
                                    <TextArea rows={4} disabled={mode === "view" || mode === "edit"}/>
                                )}
                            </FormItem>
                            <FormItem {...formLayout} label="是否已审批通过">
                                {getFieldDecorator("isApproved", {
                                    initialValue: true
                                })(
                                    <RadioGroup disabled={mode === "view" || mode === "edit"}>
                                        <Radio value={true}> 是 </Radio>{" "}
                                        <Radio value={false}> 否 </Radio>
                                    </RadioGroup>
                                )}
                            </FormItem>
                        </div>
                    )}
                    {this.getRemarkComponent()}
                </Card>
            );
        }
    }
}
