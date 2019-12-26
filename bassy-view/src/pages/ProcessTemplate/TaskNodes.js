import React from "react";
import {connect} from "react-redux";
import CSSModules from "react-css-modules";
import {Checkbox, Col, Form, Row, Select,} from "antd";
import styles from "./index.less";

const FormItem = Form.Item;


@CSSModules(styles)
@connect(state => ({
    allStages: state.common.allStages
}))
export default class TaskNodes extends React.Component {
    state = {
        newAdd: -1,
        processNode: []
    };

    handleTaskNeedChange = (n, t) => {
        const {detail} = this.props
        const processNodes = detail.processNode !== undefined ? detail.processNode : detail.processNodes;
        console.info(processNodes)
        processNodes.map(p => {
            if (p.processNodeCode === n.processNodeCode) {
                p.task.map(t1 => {
                    if (t1.taskCode === t.taskCode) {
                        t1.taskNeed = !t1.taskNeed
                    }
                })
            }
        });
        this.setState({processNode: processNodes})
        this.props.getProcessNodes(processNodes)
    }

    render() {
        const {
            getFieldDecorator,
            allStages,
            view,
            detail,
            style,
        } = this.props;
        const {processNode, newAdd} = this.state;
        const processNodes = processNode.length > 0 ? processNode : (detail.processNodes !== undefined ? detail.processNodes : detail.processNode);
        return (
            <div>
                <Row>
                    <Col span={5} style={{textAlign: "right"}}>
                        关键项目节点：
                    </Col>
                </Row>
                {processNodes.map((n, i) => (
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
                        required
                    >
                        <Col span={4}>
                            <Select
                                showSearch
                                disabled
                                value={n.processNodeCode}
                                style={{
                                    width: 100
                                }}
                                placeholder="选择阶段"
                                optionFilterProp="children"
                            >
                                {allStages && allStages.map((m, j) => (
                                    <Option value={m.code} title={m.name} key={m.code}>
                                        {m.name}
                                    </Option>
                                ))}
                            </Select>
                        </Col>
                        <Col span={18}>
                            {n.task.map((t, i) => (
                                <Checkbox
                                    key={t.taskCode}
                                    style={{
                                        marginLeft: "6px"
                                    }}
                                    onChange={() => this.handleTaskNeedChange(n, t)}
                                    checked={t.taskNeed}
                                    disabled={view}
                                >
                                    {t.taskName}
                                </Checkbox>
                            ))
                            }
                        </Col>
                    </FormItem>
                ))}
            </div>
        );
    }
}
