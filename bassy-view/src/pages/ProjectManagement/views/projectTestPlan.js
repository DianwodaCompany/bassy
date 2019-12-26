import React from "react";
import {connect} from "react-redux";
import {Card, Form, Spin, Tabs} from "antd";
import ProjectName from "./projectName"
import TestPlan from "./testPlanRow";
import RequireBoard from "./requireBoard";
import {getProgram, getTestPlan, getProgramModuleById} from "../../../apis/index";
import {TemplateContent} from "../../Common";

const { TabPane } = Tabs;

@connect(state => ({
    allStages: state.common.allStages,
    programTypes: state.programTypes,
    common: state.common,
    projectTemplates: state.projectTemplates,
    processModules: state.processModules,
    testPlan: state.testPlan,
    mode: state.project.mode,
    detail: state.project.projectDetail
}))
export default class ProjectTestPlan extends React.Component {

    state = {
        visible: false,
        loading: false,
        programName: "",
        startTime: "",
        endTime: "",
        detail: {
            id: -1,
            persons: {
                PD: "",
                PM: "",
                DO: "",
                TO: ""
            },
            coreNodes: [
                {
                    projectNode: "",
                    startTime: "",
                    endTime: "",
                    demandNeed: ""
                }
            ]
        },
        testPlanList: [],
        isScheduled: false,
        editingKey: '',
    };

    getNewPlan = newPlan => {
        this.setState({testPlanList: newPlan});
    };

    async componentWillMount() {
        this.setState({loading: true});
        const id = this.props.detail.id;
        const testPlan = await getTestPlan(id);
        const program = await getProgram(id);
        const programTemplate = await getProgramModuleById(program.data.programModule);
        const planList = testPlan.data;
        if (program.data.status !== 'init') {
            this.state.isScheduled = true;
        }
        let projectNodeListTmp = [];
        for(var node of planList.programTasks) {
            if(node.programProcess) {
                projectNodeListTmp.push(node.programProcess);
            }
        }
        for(let node of program.data.coreNodes) {
            if(node.projectNode) {
                projectNodeListTmp.push(node.projectNode)
            }
        }
        let projectNodeList = Array.from(new Set(projectNodeListTmp));
        this.setState({
            detail: program.data,
            programTemplateDetail: programTemplate.data,
            testPlanList: planList,
            projectNodeList: projectNodeList,
            loading: false,
        });
    }

    render() {
        const {testPlanList,projectNodeList, isScheduled} = this.state;
        const {detail} = this.props;
        return (
            <Spin spinning={this.state.loading}>
                <Form>
                    <Card title="测试计划">
                        <Tabs type="card">
                            <TabPane tab="列表视图" key="1">
                                <TestPlan
                                    projectNodeList={projectNodeList}
                                    testPlanList={testPlanList}
                                    isScheduled={isScheduled}
                                    getNewPlan={newPlan => this.getNewPlan(newPlan)}
                                    detail={detail}
                                />
                            </TabPane>
                            <TabPane tab="需求视图" key="2">
                                <RequireBoard detail={detail}/>
                            </TabPane>
                        </Tabs>
                    </Card>
                    <ProjectName />
                    <TemplateContent />
                </Form>
            </Spin>
        );
    }
}
