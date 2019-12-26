import React from "react";
import {Card, Form, Select} from "antd";
import {getProcessTemplatesByProjectTemplateId, getProjectTemplatesByType,getProgramModuleById} from "../../../apis/index";
import {connect} from "react-redux";
import {addProjectHeadComponentData} from "../../ProjectManagement/actions";
import {addProjectDetail} from "../actions";

const FormItem = Form.Item;
const Option = Select.Option;
const formLayout = {
    labelCol: {
        span: 5
    },
    wrapperCol: {
        span: 6
    }
};

@connect(state => ({
    projectBigTypes: state.common.projectBigTypes,
    mode: state.project.mode,
    detail: state.project.projectDetail
}))
@Form.create()
export default class ProjectHead extends React.Component {

    state = {
        detail: "",
        projectBigType: "",
        projectTemplates: [
            {
                id: "",
                moduleName: ""
            }
        ],
        processModules: [],
        processModule: {}
    };

    getProjectTemplatesByBigType = async (t) => {
        const ret = await getProjectTemplatesByType(t);
        this.setState({projectTemplates: ret.data, projectBigType: t});
        this.props.form.setFieldsValue({'programModule': ""});
        this.props.form.setFieldsValue({'processModule': ""});
    };

    getProcessTemplatesByProjectTemplateId = async (t) => {
        const ret = await getProcessTemplatesByProjectTemplateId(t);
        this.setState({processModules: ret.data});
        const projectTemplate = await getProgramModuleById(t);
        this.props.dispatch(addProjectDetail(projectTemplate.data));
        this.props.form.setFieldsValue({'processModule': ""});
    };

    componentWillMount = () => {
        this.props.dispatch(addProjectHeadComponentData(
            {form:this.props.form}
        ));
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {detail, mode, projectBigTypes, programModuleName, processModuleName} = this.props;
        if (detail && detail.programType) {
            this.state.projectBigType = detail.programType;
        }
        const {projectTemplates, processModules, projectBigType} = this.state;
        return (
            <Card title="项目类型">
                <FormItem {...formLayout} label="项目大类">
                    {getFieldDecorator("programType", {
                        rules: [
                            {
                                required: true,
                                message: "请选择项目大类！"
                            }
                        ],
                        initialValue: projectBigType
                    })(<Select
                            showSearch
                            onChange={this.getProjectTemplatesByBigType}
                            optionFilterProp="children"
                            disabled={mode === "view" || mode === "edit"}
                        >
                            {projectBigTypes && Object.keys(projectBigTypes).map((m, i) =>
                                <Option value={m} key={new Date()}>{projectBigTypes[m]}</Option>
                            )}
                        </Select>
                    )}
                </FormItem>
                <FormItem {...formLayout} label="项目模版">
                    {getFieldDecorator("programModule", {
                        rules: [
                            {
                                required: true,
                                message: "请选择项目模版！"
                            }
                        ],
                        initialValue: programModuleName
                    })(<Select
                            showSearch
                            onChange={this.getProcessTemplatesByProjectTemplateId}
                            optionFilterProp="children"
                            disabled={mode === "view" || mode === "edit"}
                        >
                            {projectTemplates && Object.keys(projectTemplates).map((p) =>
                                <Option value={projectTemplates[p].id}
                                        key={new Date()}>{projectTemplates[p].moduleName}</Option>
                            )}
                        </Select>
                    )}
                </FormItem>
                {projectBigType !== "inner" && <FormItem {...formLayout} label="流程模版">
                    {getFieldDecorator("processModule", {
                        rules: [
                            {
                                required: true,
                                message: "请选择流程模版！"
                            }
                        ],
                        initialValue: processModuleName
                    })(<Select
                            id="selectType"
                            showSearch
                            rowKey="id"
                            optionFilterProp="children"
                            disabled={mode === "view" || mode === "edit"}
                        >
                            {processModules && Object.keys(processModules).map((p) =>
                                <Option value={processModules[p].id}
                                        key={new Date()}>{processModules[p].moduleName}</Option>
                            )}
                        </Select>
                    )}
                </FormItem>
                }
            </Card>
        );
    }
}
