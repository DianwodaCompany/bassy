import React from "react";
import {Card, Form, Input, Select} from "antd";

const FormItem = Form.Item;
const Option = Select.Option;
const formLayout = {
    labelCol: {span: 5},
    wrapperCol: {span: 5}
};

export const categories = {
    program: "立项项目",
    inner: "内部项目",
    urgent: "紧急项目",
    normal: "常规项目"
};

export default class ModelHead extends React.Component {
    render() {
        const {getFieldDecorator, check, view, style, programModules, detail} = this.props;
        return (
            <Card title="模版类型">
                <FormItem {...formLayout} label="项目类型名称">
                    {getFieldDecorator("parentCode", {
                        rules: [
                            {
                                required: true
                            }
                        ],
                        initialValue: categories[style]
                    })(<Input disabled/>)}
                </FormItem>
                <FormItem {...formLayout} label="项目模版">
                    {getFieldDecorator("programModule", {
                        rules: [
                            {
                                required: true
                            }
                        ],
                        initialValue: `${detail.programModule}`
                    })(
                        <Select
                            id="selectType"
                            showSearch
                            optionFilterProp="children"
                            onChange={this.props.setProcessNodesByProgramMode}
                            disabled={view}
                        >
                            {programModules &&
                            Object.keys(programModules).map(p => (
                                <Option
                                    key={programModules[p].id}
                                >
                                    {programModules[p].moduleName}
                                </Option>
                            ))}
                        </Select>
                    )}
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
                    })(<Input placeholder="请输入项目定义" disabled={view}/>)}
                </FormItem>
                <FormItem {...formLayout} label="模版名称">
                    {getFieldDecorator("moduleName", {
                        rules: [
                            {
                                required: true,
                                message: "请输入模版名称！"
                            }
                        ],
                        initialValue: detail.moduleName
                    })(<Input placeholder="请输入模版名称" disabled={view}/>)}
                </FormItem>
            </Card>
        );
    }
}
