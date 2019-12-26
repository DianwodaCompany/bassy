import React, { Component, Fragment } from 'react';
import {
    Popover,
    Card,
    Select,
    Form,
    Icon,
    Steps,
    Badge
} from 'antd';
import {connect} from "react-redux";
import classNames from 'classnames';

import {getStaffList} from "../../../apis/index";
import moment from "moment/moment";
import {getPlanRequireBoard, getTestPlan} from "../../../apis";
import {message} from "antd/lib/index";
import styles from './index.less';

const Option = Select.Option;
const FormItem = Form.Item;
const { Step } = Steps;

@connect(state => ({
    staffInfo: state.common.staffInfo,
    testPlan: state.testPlan,
    allDict: state.allDict,
    abnormalReasonTeam: state.common.abnormalReasonTeam,
    abnormalReasonType: state.common.abnormalReasonType,
    allTasksWithProcess: state.common.allTasksWithProcess
}))
@Form.create()
export default class RequireBoard extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            plans:[]
        };
    }

    async componentWillMount() {
        const id = this.props.detail.id;
        var resp = await getPlanRequireBoard(id);
        if(resp.data) {
            this.setState({plans: resp.data});
        }
    }

    render() {
        const desc = (details) => (
            details.map(d => {
                var testerName = d.split(",")[0];
                var status = d.split(",")[1];
                var time = d.split(",")[2];
                return (
                    <div className={classNames(styles.textSecondary, styles.stepDescription)}>
                        <Fragment>
                            {testerName !== "null" ? testerName + "  " : ""}
                            {/*<Icon type="edit" theme="filled" style={{ marginLeft: 6, marginLeft: 4 }} />*/}
                            { status === 'init' ? "预计开始于" : status === 'processing' ? "最后更新于" : "结束于" }
                            </Fragment>
                        <div>{time}</div>
                    </div>
                )
            })
        );

        const popoverContent = (
            <div style={{ width: 160 }}>
                吴加号
                <span className={styles.textSecondary} style={{ float: 'right' }}>
                    <Badge status="default" text={<span style={{ color: 'rgba(0, 0, 0, 0.45)' }}>未响应</span>} />
                </span>
                <div className={styles.textSecondary} style={{ marginTop: 4 }}>
                    耗时：2小时25分钟
                </div>
            </div>
        );

        const customDot = (dot, { status }) =>
            status === 'process' ? (
                <Popover placement="topLeft" arrowPointAtCenter content={popoverContent}>
                    {dot}
                </Popover>
            ) : (
                dot
            );

        const {plans} = this.state;
        return (
            plans.map( item => {
                var current = item.nodes.findIndex(n => {return n.status === "end" || n.status === "processing" });
                console.log("current", current);
                return (
                    <Card title={"[" + item.requireId + "]" + item.requireRelate} style={{ marginBottom: 24 }} bordered={false}>
                        <Steps direction={"horizontal"} progressDot={customDot} current={item.current}>
                            {item.nodes.map( t => {
                                return (
                                    <Step title={t.processName} description={desc(t.details)} />
                                )
                            })}
                        </Steps>
                    </Card>
                )

            })
        )
    }

}