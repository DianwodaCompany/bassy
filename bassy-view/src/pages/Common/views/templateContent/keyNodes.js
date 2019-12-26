import React from "react";
import {connect} from "react-redux";
import moment from "moment";
import {Checkbox, Col, DatePicker, Form, Icon, Input, message, Row, Select} from "antd";
import styles from "../../../ConfigManagement/views/index.less";

const {RangePicker} = DatePicker;
const Option = Select.Option;
const FormItem = Form.Item;
const formLayout = {
    labelCol: {
        span: 5
    },
    wrapperCol: {
        span: 15
    }
};

@connect(state => ({
    allStages: state.common.allStages,
    currentView: state.common.currentView,
    projectTime: state.project.projectTime,
    mode: state.common.currentView === "ConfigManagement" ? state.project.mode : state.configManagement.mode,
    detail: state.common.currentView === "ConfigManagement" ? state.project.projectDetail : state.configManagement.projectTemplateDetail
}))
export default class KeyNodes extends React.Component {
    state = {
        newAdd: -1,
        nodes: []
    };
    handleStageChange = (i, val) => {
        console.log(val, i);
        const {nodes} = this.state;
        const {coreNodes} = this.props.detail;
        const currentNode = nodes.length > 0 ? nodes : coreNodes;
        currentNode.map((v, index) => {
            if (index === i) {
                v.projectNode = val;
            }
        });
        this.props.getCoreNodes(currentNode);
        this.setState({nodes: currentNode});
    };
    addNode = n => {
        const {nodes} = this.state;
        const {coreNodes} = this.props.detail;
        const currentNode = nodes.length > 0 ? nodes : coreNodes;
        const newNode = {
            startTime: '',
            endTime: '',
            demandNeed: false,
            projectNode: ""
        };
        currentNode.splice(n + 1, 0, newNode);
        this.props.getCoreNodes(currentNode);
        this.setState({nodes: currentNode, newAdd: n + 1});
    };
    deleteNode = n => {
        const {nodes} = this.state;
        const {coreNodes} = this.props.detail;
        const currentNode = nodes.length > 0 ? nodes : coreNodes;
        if (nodes.length === 1) {
            message.error("至少有一个项目节点！");
            return;
        }
        currentNode.splice(n, 1);
        this.setState({
            nodes: currentNode,
            newAdd: -1
        });
    };
    dateChange = (n, idx, ...d) => {
        console.log(n, idx, d);
        const {nodes} = this.state;
        const {coreNodes} = this.props.detail;
        const currentNode = nodes.length > 0 ? nodes : coreNodes;
        currentNode.map((v,index) => {
            if (v.projectNode === n.projectNode && idx === index) {
                v.startTime = d[1][0];
                v.endTime = d[1][1];
            }
        });
        this.props.getCoreNodes(currentNode);
        this.setState({nodes: currentNode});
    };
    needsChange = (i, e) => {
        const {nodes} = this.state;
        const {coreNodes} = this.props.detail;
        const currentNode = nodes.length > 0 ? nodes : coreNodes;
        currentNode.map(v => {
            if (v.projectNode === i.projectNode) {
                v.demandNeed = !v.demandNeed;
            }
        });
        this.props.getCoreNodes(currentNode);
        this.setState({nodes: currentNode});
    };
    nodeChange = (e, n, i) => {
        const {nodes} = this.state;
        const {coreNodes} = this.props.detail;
        const currentNode = nodes.length > 0 ? nodes : coreNodes;
        currentNode.map((v, index) => {
            if (index === i) {
                v.projectNode = e.target.value;
            }
        });
        this.props.getCoreNodes(currentNode);
        this.setState({nodes: currentNode});
    };

    render() {
        const {
            allStages,
            mode,
            currentView,
            projectTime,
            detail
        } = this.props;
        const {nodes, newAdd} = this.state;
        const coreNodesDetail = detail ? detail.coreNodes : [{"projectNode":"","startTime":"","endTime":""}];
        const coreNodes = nodes.length > 0 ? nodes : coreNodesDetail;
        let projectTm = projectTime === undefined ? ['', ''] : projectTime;
        return (
            <div>
                <Row>
                    <Col span={5} style={{textAlign: "right"}}>
                        {detail.parentCode === "inner" ? "细分阶段：" : "关键项目节点："}
                    </Col>
                </Row>
                {coreNodes.map((n, i) => (
                    <FormItem
                        style={
                            i === newAdd ? {border: "1px solid red"} : {}
                        }
                        labelCol={{
                            span: 5
                        }}
                        wrapperCol={{
                            span: 19
                        }}
                        key={i}
                        label={"阶段" + (i + 1)}
                        required={detail.parentCode !== "inner"}
                    >
                        <Row>
                            {detail.parentCode === "inner" && (
                                <Col span={8}>
                                    <FormItem {...formLayout}>
                                        <Input
                                            disabled={mode === "view" || mode === "edit"}
                                            value={n.projectNode}
                                            onChange={e => this.nodeChange(e, n, i)}
                                        />
                                    </FormItem>
                                </Col>
                            )}
                            {detail.parentCode !== "inner" && (
                                <Col span={8}>
                                    <Select
                                        showSearch
                                        disabled={mode === "view" || mode === "edit"}
                                        value={n.projectNode}
                                        style={{
                                            width: 200
                                        }}
                                        placeholder="选择阶段"
                                        optionFilterProp="children"
                                        onChange={this.handleStageChange.bind(this, i)}
                                    >
                                        {allStages.map((m, j) => (
                                            <Option value={m.code} title={m.name} key={m.code}>
                                                {m.name}
                                            </Option>
                                        ))}
                                    </Select>
                                </Col>
                            )}
                            <Col span={16}>
                                {currentView==="ConfigManagement" && <RangePicker
                                    style={{
                                        display: "inline-block",
                                        marginLeft: "6px"
                                    }}
                                    onChange={this.dateChange.bind(this, n, i)}
                                    value={n.startTime != null && n.startTime !== '' ? [moment(n.startTime), moment(n.endTime)] : [moment(projectTm[0]._d), moment(projectTm[1]._d)]}
                                    disabled={mode === "view"}
                                />}
                                {detail.parentCode !== "inner" && (
                                    <Checkbox
                                        style={{
                                            marginLeft: "6px"
                                        }}
                                        onChange={this.needsChange.bind(this, n)}
                                        checked={Boolean(n.demandNeed)}
                                        disabled={mode === "view" || mode === "edit"}
                                    >
                                        和需求ID有关
                                    </Checkbox>
                                )}
                                {mode !== "view" && mode !== "edit" && (
                                    <Icon
                                        type="plus"
                                        className={styles.icon}
                                        onClick={() => this.addNode(i)}
                                    />
                                )}
                                {mode !== "view" && mode !== "edit" && (
                                    <Icon
                                        type="minus"
                                        className={styles.icon}
                                        onClick={() => this.deleteNode(i)}
                                    />
                                )}
                            </Col>
                        </Row>
                    </FormItem>
                ))}
            </div>
        );
    }
}
