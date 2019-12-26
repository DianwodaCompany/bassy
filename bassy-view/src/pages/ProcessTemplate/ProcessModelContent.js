import React from 'react'
import {Card} from 'antd'

import TaskNodes from "./TaskNodes.js";


export default class ProcessModelContent extends React.Component {
    state = {
        processNodeCodes: [
            {
                processNodeCode: "",
                processNodeValue: "",
                task: [
                    {
                        taskCode: "",
                        taskName: "",
                        need: false
                    }
                ]
            }
        ]
    };

    render() {
        const {getFieldDecorator, detail, view, style, processNodes} = this.props;
        return (
            <Card title="流程">
                <TaskNodes
                    view={view}
                    detail={detail}
                    ref={n => (this.nodes = n)}
                    processNodes={processNodes}
                    style={style}
                    getFieldDecorator={getFieldDecorator}
                    getProcessNodes={this.props.getProcessNodes}
                />
            </Card>
        )
    }
}