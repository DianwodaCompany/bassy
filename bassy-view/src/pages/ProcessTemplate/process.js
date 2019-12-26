import React from 'react'
import {connect} from 'react-redux'
import CSSModules from 'react-css-modules'
import {Button, Card, Form, message, Spin} from 'antd'
import styles from './index.less'
import ProcessModelHead from './ProcessModelHead'
import ProcessModelContent from './ProcessModelContent'
import {
    addProcessModule,
    getProcessModuleById,
    getProcessTaskByProcessNode,
    getProgramModuleById,
    getProjectTemplatesByType,
    processModuleIsExit,
    updateProcessModule
} from "../../apis";

const FormItem = Form.Item;

const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 16,
            offset: 10,
        },
    },
};

@CSSModules(styles)
@connect(state => ({
    staffInfo: state.common.staffInfo,
    allStages: state.common.allStages
}))
@Form.create()
export default class Process extends React.Component {
    state = {
        visible: false,
        loading: false,
        type: "",
        detail: {
            id: -1,
            parentCode: "",
            programModule: "",
            projectTemplates: [],
            processNodes: [
                {
                    processNodeCode: "",
                    processNodeValue: "",
                    task: [
                        {
                            taskCode: "",
                            taskName: ""

                        }
                    ]
                }
            ]
        }
    };


    viewProcessModeList = () => {
        this.props.history.push({
            pathname: '/processMode'
        })
    };

    submit = e => {
        e.preventDefault();
        console.log(this.props);
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            console.log(err, values);
            if (!err) {
                console.log('Received values of form: ', values);
                values.processNode = JSON.stringify(this.state.detail.processNodes);
                if (this.props.add) {
                    const moduleName = values.moduleName;
                    const exit = await processModuleIsExit(moduleName);
                    if (exit.data) {
                        message.error("模版名称已存在！");
                        return
                    }
                }
                delete values.parentCode;
                console.log(values);
                this.setState({loading: true});
                let user = this.props.staffInfo.code;
                if (this.props.add) {
                    values.creator = user;
                    const insert = await addProcessModule(values);
                    if (insert.data) {
                        message.success("新增成功");
                        this.viewProcessModeList()
                    } else {
                        message.error(insert.msg);
                    }
                } else if (this.props.edit) {
                    values.modifyer = user;
                    values.id = this.state.detail.id;
                    const update = await updateProcessModule(values);
                    if (update.data) {
                        message.success("编辑成功");
                        this.viewProcessModeList()
                    } else {
                        message.error(insert.msg);
                    }
                }
                if (this.state.edit) {

                }
                this.setState({loading: false});
            }
        });
    };
    setProcessNodesByProgramMode = async (id) => {
        const ret = await getProgramModuleById(id);
        const coreNodes = ret.data.coreNodes;
        let processNodes = [];
        for (let i in coreNodes) {
            const processNode = coreNodes[i].projectNode;
            const task = await getProcessTaskByProcessNode(processNode);
            let pN = {
                processNodeCode: "",
                processNodeValue: "",
                task: []
            };
            pN.processNodeCode = processNode;
            for (let i in task.data) {
                let t = {taskCode: "", taskName: "", taskNeed: false};
                t.taskCode = task.data[i].task;
                t.taskName = task.data[i].taskValue;
                t.taskNeed = false;
                pN.task.push(t)
            }
            processNodes.push(pN)
        }
        this.state.detail.processNodes = processNodes;
        this.setState({detail: this.state.detail})
    };
    getProcessNodes = processNodes => {
        this.state.detail.processNodes = processNodes;
        const det = this.state.detail;
        det.processNodes = processNodes;
        this.setState({detail: det})
    };

    async componentWillMount() {
        const style = this.props.location.state.style;
        const ret = await getProjectTemplatesByType(style);
        this.setState({projectTemplates: ret.data});
        if (this.props.add) {
            // handle create
        } else {
            // handle view and edit
            const id = this.props.location.state.id;
            const ret = await getProcessModuleById(id);
            this.setState({
                detail: ret.data
            });
            console.log(ret.data);
        }
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        const {view} = this.props;
        const style = this.props.location.state.style;
        const {detail, projectTemplates} = this.state;
        return (
            <Spin spinning={this.state.loading}>
                <Form onSubmit={this.submit}>
                    <Card>
                        <ProcessModelHead
                            getFieldDecorator={getFieldDecorator}
                            detail={detail}
                            style={style}
                            programModules={projectTemplates}
                            setProcessNodesByProgramMode={this.setProcessNodesByProgramMode}
                            view={view}
                        />
                        <ProcessModelContent
                            detail={detail}
                            ref={c => (this.content = c)}
                            getFieldDecorator={getFieldDecorator}
                            processNodes={detail.processNodes}
                            getProcessNodes={this.getProcessNodes}
                            view={view}
                        />
                        {!view && (
                            <FormItem
                                {...tailFormItemLayout}>
                                <Button
                                    type="primary"
                                    htmlType="submit"
                                >确认</Button>
                            </FormItem>
                        )
                        }
                    </Card>
                </Form>
            </Spin>
        )
    }
}
