import React from "react";
import {Card, DatePicker, Form, Input,} from "antd";
import moment from "moment";
import connect from "react-redux/es/connect/connect";
import {addProjectNameComponentData, addProjectTime} from "../actions";

const {RangePicker} = DatePicker;

const FormItem = Form.Item;
const formLayout = {
    labelCol: {
        span: 5
    },
    wrapperCol: {
        span: 6
    }
};

@connect(state => ({
    mode: state.project.mode,
    detail: state.project.projectDetail
}))
@Form.create()
export default class ProjectName extends React.Component {

    onProjectTimeChange = time => {
        this.props.dispatch(addProjectTime(time));
    };

    componentWillMount = () => {
        this.props.dispatch(addProjectNameComponentData(
            {form:this.props.form}
        ));
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {detail, mode} = this.props;
        return (
            <Card title="项目名称">
                <FormItem {...formLayout} label="项目名称">
                    {getFieldDecorator("programName", {
                        rules: [
                            {
                                required: true,
                                message: "请输入项目名称！"
                            }
                        ],
                        initialValue: detail.programName
                    })(<Input placeholder="请输入项目名称" disabled={mode === "view"}
                    />)}
                </FormItem>

                <FormItem {...formLayout} label="项目时间">
                    {getFieldDecorator("programTm", {
                        rules: [
                            {
                                required: true,
                                message: "请设置项目时间！"

                            }
                        ],
                        initialValue: [moment(detail.startTime), moment(detail.endTime)]
                    })(<RangePicker
                        style={{
                            display: "inline-block",
                            marginLeft: "6"
                        }}
                        disabled={mode === "view"  || mode === "edit"}
                        onChange={this.onProjectTimeChange}
                    />)}
                </FormItem>

            </Card>
        );
    }
}
