import React from "react";
import {Button, Card, Col, Form, message, Row, Select, Spin} from 'antd';
import {connect} from "react-redux";
import TemplateHead, {categories} from "./templateHead";
import {TemplateContent} from "../../Common";
import {editModule, getDefaultModule, insertModule, programModuleIsExit} from "../../../apis/index";
import {addProjectTemplateDetail} from "../actions";

const FormItem = Form.Item;

@connect(state => ({
    staffInfo: state.common.staffInfo,
    allStages: state.common.allStages,
    mode: state.common.currentView === "ConfigManagement" ? state.project.mode : state.configManagement.mode,
    detail: state.common.currentView === "ConfigManagement" ? state.project.projectDetail : state.configManagement.projectTemplateDetail,
    headData:state.configManagement.headData,
    contentData:state.configManagement.contentData
}))
export default class Normal extends React.Component {
    state = {
        visible: false,
        loading: false
    };

    viewProjectTemplateList = () => {
        this.props.history.push({
            pathname: '/projectTemplate'
        })
    };

    submit = e => {
        e.preventDefault();
        let obj = {};
        this.props.headData.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log("Received values of head form: ", values);
                obj = Object.assign(obj, values);
            }
        });
        this.props.contentData.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                console.log("Received values of content form: ", values);
                const res = this.props.contentData.collectData();
                obj = Object.assign(obj, values, res);
                if (this.props.mode === "add") {
                    const moduleName = obj.moduleName;
                    const exit = await programModuleIsExit(moduleName);
                    if (exit.data) {
                        message.error("项目名称已经存在！");
                        return
                    }
                }
                for (let i in categories) {
                    if (obj.parentCode === categories[i]) {
                        obj.parentCode = i;
                    }
                }
                const cns = obj.coreNodes;
                for (let i in cns) {
                    delete cns[i].key
                }
                obj.coreNodes = JSON.stringify(cns);
                obj.requires = JSON.stringify(obj.requires);
                obj.persons = JSON.stringify(obj.persons);
                this.setState({loading: true});
                let user = this.props.staffInfo.code;
                if (this.props.mode === "add") {
                    obj.creator = user;
                    obj.createTm = new Date();
                    const insert = await insertModule(obj);
                    if (insert.data) {
                        message.success("新增成功");
                        this.viewProjectTemplateList()
                    } else {
                        message.error(insert.msg);
                    }
                }
                if (this.props.mode === "edit") {
                    obj.id = this.props.detail.id;
                    obj.modifier = user;
                    obj.modifyTm = new Date();
                    console.info(obj);
                    const edit = await editModule(obj);
                    if (edit.data) {
                        message.success("编辑成功");
                        this.viewProjectTemplateList()
                    } else {
                        message.error(edit.msg);
                    }
                }
                this.setState({loading: false});
            }
        });
    };

    back = () => {
        this.props.history.goBack();
    };

    async componentWillMount() {
        if(this.props.mode === "add"){
            let result = await getDefaultModule(this.props.detail.parentCode);
            this.props.dispatch(addProjectTemplateDetail(result.data));
        }
    }

    render() {
        const {mode} = this.props;
        return (
            <Spin spinning={this.state.loading}>
                <Form onSubmit={this.submit}>
                    <Card>
                        <TemplateHead
                        />
                        <TemplateContent
                        />
                        {mode !== "view" && (
                            <Row>
                                <Col span={3} offset={8}>
                                    <FormItem>
                                        <Button onClick={this.back}>
                                            取消
                                        </Button>
                                    </FormItem>
                                </Col>
                                <Col span={6}>
                                    <FormItem>
                                        <Button type="primary" htmlType="submit">
                                            确认
                                        </Button>
                                    </FormItem>
                                </Col>
                            </Row>
                        )}
                        {
                            mode === "view" && mode === "edit" &&
                            <Row>
                                <Col span={3} offset={8}>
                                    <FormItem>
                                        <Button onClick={this.back}>
                                            返回
                                        </Button>
                                    </FormItem>
                                </Col>
                            </Row>
                        }
                    </Card>
                </Form>
            </Spin>
        );
    }
}
