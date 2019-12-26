import React from "react";
import {Col, Form, Row, Select} from "antd";
import {getStaffInfo, getStaffList} from "../../../../apis/index";
import connect from "react-redux/es/connect/connect";

const Option = Select.Option;

const FormItem = Form.Item;
const formLayout = {
    labelCol: {
        span: 4
    },
    wrapperCol: {
        span: 20
    }
};

@connect(state => ({
    mode: state.project.mode,
    detail: state.project.projectDetail
}))
export default class Persons extends React.Component {

    state = {
        //初始值定义成undefined，目的是当没有值时，显示placeholder的提示值
        persons: {
            PD: undefined,
            PM: undefined,
            DO: undefined,
            TO: undefined
        },
        PDName: '',
        staffList: [{
            id: "",
            code: "",
            name: "",
            mobile: "",
            email: "",
            duty: "",
            departId: "",
            departName: "",
            disable: ""
        }]
    };

    changPersonKeyWord = async (input) => {
        if (input === undefined || input === "") {
            return;
        }
        const staffList = await getStaffList(input);
        if (staffList.data) {
            this.setState({staffList: staffList.data})
        }
    };

    handleChange = (name, value) => {
        const newPersons = this.state.persons;
        switch (name) {
            case "PD":
                newPersons.PD = value;
                break;
            case "PM":
                newPersons.PM = value;
                break;
            case "DO":
                newPersons.DO = value;
                break;
            case "TO":
                newPersons.TO = value;
                break
        }
        this.props.getPersons(newPersons);
        this.setState({persons: newPersons});
        console.log(this.state.persons.PD)
    };

    componentWillMount = async () => {
        const {detail} = this.props;
        if(detail && detail.persons){
            const newPersons = this.state.persons;
            if(detail.persons.PD && detail.persons.PD !== ""){
                const staffInfo = await getStaffInfo({id: detail.persons.PD});
                newPersons.PD = "("+staffInfo.data.staffInfo.code+") "+staffInfo.data.name;
            }
            if(detail.persons.PM && detail.persons.PM !== ""){
                const staffInfo = await getStaffInfo({id: detail.persons.PM});
                newPersons.PM = "("+staffInfo.data.staffInfo.code+") "+staffInfo.data.name;
            }
            if(detail.persons.DO && detail.persons.DO !== ""){
                const staffInfo = await getStaffInfo({id: detail.persons.DO});
                newPersons.DO = "("+staffInfo.data.staffInfo.code+") "+staffInfo.data.name;
            }
            if(detail.persons.TO && detail.persons.TO !== ""){
                const staffInfo = await getStaffInfo({id: detail.persons.TO});
                newPersons.TO = "("+staffInfo.data.staffInfo.code+") "+staffInfo.data.name;
            }
            this.setState({persons: newPersons});
        }
    };

    render() {
        const {detail,mode} = this.props;
        const bigType = detail.programType ? detail.programType  : detail.parentCode;
        const {staffList} = this.state;
        return (
            <div>
                <Row>
                    <Col span={5} style={{textAlign: "right"}}>
                        项目核心成员：
                    </Col>
                </Row>
                {bigType !== "inner" && (
                    <Row>
                        <Col span={4}/>
                        <Col span={7}>
                            <FormItem  {...formLayout} label="PD">
                                <Select
                                    showSearch
                                    placeholder="工号或姓名"
                                    value={this.state.persons.PD}
                                    onSearch={this.changPersonKeyWord}
                                    onChange={(value) => this.handleChange('PD', value)}
                                    disabled={mode === "view" || mode === "edit"}
                                >
                                    {staffList.map((s) => (
                                        <Option value={`${s.code} ${s.name}`} key={s.code}>
                                            {"(" + s.code + ")" + s.name}
                                        </Option>
                                    ))}
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span={7}>
                            <FormItem  {...formLayout} label="PM">
                                <Select
                                    showSearch
                                    placeholder="工号或姓名"
                                    value={this.state.persons.PM}
                                    onSearch={this.changPersonKeyWord}
                                    onChange={(value) => this.handleChange('PM', value)}
                                    disabled={mode === "view" || mode === "edit"}
                                >
                                    {staffList.map((s) => (
                                        <Option value={`${s.code} ${s.name}`} key={s.code}>
                                            {"(" + s.code + ")" + s.name}
                                        </Option>
                                    ))}
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span={6}/>
                    </Row>)}


                <Row>
                    <Col span={4}/>
                    {bigType !== "inner" && (
                        <Col span={7}>
                            <FormItem {...formLayout} label="DO">
                                <Select
                                    showSearch
                                    placeholder="工号或姓名"
                                    value={this.state.persons.DO}
                                    onSearch={this.changPersonKeyWord}
                                    onChange={(value) => this.handleChange('DO', value)}
                                    disabled={mode === "view" || mode === "edit"}
                                >
                                    {staffList.map((s) => (
                                        <Option value={`${s.code} ${s.name}`} key={s.code}>
                                            {"(" + s.code + ")" + s.name}
                                        </Option>
                                    ))}
                                </Select>
                            </FormItem>
                        </Col>
                    )}
                    <Col span={7}>
                        <FormItem {...formLayout} label="TO">
                            <Select
                                showSearch
                                placeholder="工号或姓名"
                                value={this.state.persons.TO}
                                onSearch={this.changPersonKeyWord}
                                onChange={(value) => this.handleChange('TO', value)}
                                disabled={mode === "view" || mode === "edit"}
                            >
                                {staffList.map((s) => (
                                    <Option value={`${s.code} ${s.name}`} key={s.code}>
                                        {"(" + s.code + ")" + s.name}
                                    </Option>
                                ))}
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span={6}/>
                </Row>
            </div>
        )
    }
}