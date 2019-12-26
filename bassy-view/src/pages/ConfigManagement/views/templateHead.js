import React from "react";
import {Card, Form, Input} from "antd";
import {connect} from "react-redux";
import {addTemplateHeadComponentData} from '../actions.js';

const FormItem = Form.Item;
const formLayout = {
    labelCol: {
        span: 5
    },
    wrapperCol: {
        span: 5
    }
};
export const categories = {
    program: "立项项目",
    inner: "内部项目",
    urgent: "紧急项目",
    normal: "常规项目"
};

@connect(state => ({
    mode: state.common.currentView === "ConfigManagement" ? state.project.mode : state.configManagement.mode,
    detail: state.common.currentView === "ConfigManagement" ? state.project.projectDetail : state.configManagement.projectTemplateDetail
}))
@Form.create()
export default class TemplateHead extends React.Component {

    initModuleName = (detail, add) => {
        if (add) {
            return ""
        } else {
            return detail.moduleName
        }
    };

    componentWillMount = () => {
        this.props.dispatch(addTemplateHeadComponentData(
            {form:this.props.form}
        ));
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {detail, mode} = this.props;
        return (
            <Card title="模版类型">
                <FormItem {...formLayout} label="项目类型名称">
                    {getFieldDecorator("parentCode", {
                        rules: [
                            {
                                required: true
                            }
                        ],
                        initialValue: categories[detail.parentCode]
                    })(<Input disabled/>)}
                </FormItem>
                <FormItem {...formLayout} label="项目定义">
                    {getFieldDecorator("description", {
                        rules: [
                            {
                                required: false,
                                message: "请输入项目定义！"
                            }
                        ],
                        initialValue: detail.description
                    })(<Input placeholder="请输入项目定义" disabled={mode === "view" || mode === "edit"}/>)}
                </FormItem>
                <FormItem {...formLayout} label="模版名称">
                    {getFieldDecorator("moduleName", {
                        rules: [
                            {
                                required: true,
                                message: "请输入模版名称！"
                            }
                        ],
                        initialValue: this.initModuleName(detail, mode === "add")
                    })(<Input placeholder="请输入模版名称" disabled={mode === "view" || mode === "edit"}/>)}
                </FormItem>
            </Card>
        );
    }
}

