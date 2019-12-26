import React from 'react'
import {connect} from "react-redux";
import {
    Avatar,
    Button,
    Card,
    Col,
    Form,
    Icon,
    Input,
    message,
    Popconfirm,
    Row,
    Select,
    Spin,
    Table,
    Tag,
    Tooltip
} from 'antd';
import {
    addCase,
    getCaseDetail,
    getLabelList,
    getModuleList,
    getProductList,
    updateCase,
    genSysLabel
} from "../../../apis/index";
import {addAllProduct, updateProductModuleList, updateTestCaseDetail} from "../actions";
import Provider from "./programCase/provider";
import BaseCaseConflictSolve from "./baseCase/baseCaseConflictSolve";
import {css} from "emotion";

const EditableContext = React.createContext();
const FormItem = Form.Item;

const formItemLayout = {
    labelCol: {
        xs: {span: 24},
        sm: {span: 4},
    },
    wrapperCol: {
        xs: {span: 24},
        sm: {span: 20},
    },
};
const formItemLayout2 = {
    labelCol: {
        xs: {span: 24},
        sm: {span: 2},
    },
    wrapperCol: {
        xs: {span: 24},
        sm: {span: 22},
    },
};

const EditableRow = ({form, index, ...props}) => (
    <EditableContext.Provider value={form}>
        <tr {...props} />
    </EditableContext.Provider>
);

const EditableFormRow = Form.create()(EditableRow);


class EditableCell extends React.Component {
    getInput = () => {
        return <Input.TextArea rows={4}/>;
    };

    render() {
        const {
            editing,
            dataIndex,
            title,
            record,
            ...restProps
        } = this.props;
        return (
            <EditableContext.Consumer>
                {(form) => {
                    const {getFieldDecorator} = form;
                    return (
                        <td {...restProps}>
                            {editing ? (
                                <FormItem style={{margin: 0}}>
                                    {getFieldDecorator(dataIndex, {
                                        initialValue: record[dataIndex]
                                    })(this.getInput())}
                                </FormItem>
                            ) : restProps.children}
                        </td>
                    );
                }}
            </EditableContext.Consumer>
        );
    }
}


@connect(state => ({
    staffInfo: state.common.staffInfo,
    testCase: state.testCase
}))
@Form.create()
export default class caseDetail extends React.Component {
    state = {
        id: null,
        inputVisible: false,
        baseLabel: [],
        product: '',
        module: '',
        title: '',
        type: '',
        precondition: '',
        pri: '',
        step: [{
            desc: "",
            expectDb: "",
            expectOther: "",
            expectResponse: "",
            expectUi: "",
            stepId: 1
        }],
        label: [],
        lastEditedBy: '',
        lastEditedDate: '',
        version: null,
        conflictCaseInfo: {
            caseId: null,
            caseTitle: null,
            baseCase: {
                step: [],
                label: []
            }, programCase: {
                step: [],
                label: []
            }
        },
        editingId: 1
    };

    componentDidMount() {
        this.initCaseCommon();
        this.initCaseDetail();
    }

    initCaseCommon = async () => {
        const res2 = await getProductList({keyWords: ""});
        this.props.dispatch(addAllProduct(res2.data));
    };

    initCaseDetail = async () => {
        const {caseId, add, edit, copy, view} = this.props.location.state;

        if (add) {
            this.setState({
                type: "1",
                pri: 3
            });
        }
        if (copy || edit || view) {
            const res = await getCaseDetail({id: caseId});
            res.data.step.map((step, stepId) => {
                Object.assign(step, {stepId: stepId + 1});
            });
            const res3 = await getModuleList({productId: res.data.product, allLayer: true});
            this.props.dispatch(updateTestCaseDetail({product: res.data.product}));
            this.props.dispatch(updateProductModuleList(res3.data));
            this.setState({
                id: res.data.id,
                product: res.data.product,
                module: res.data.module,
                title: res.data.title,
                type: res.data.type,
                precondition: res.data.precondition,
                pri: res.data.pri,
                step: res.data.step,
                label: res.data.label,
                lastEditedBy: res.data.lastEditedBy,
                lastEditedDate: res.data.lastEditedDate,
                version: res.data.version
            });
        }
    };


    columns = [
        {
            title: "编号",
            dataIndex: "stepId",
            key: "stepId",
            width: "80px",
            align: "center",
        }, {
            title: "步骤",
            dataIndex: "desc",
            key: "desc",
            editable: true
        }, {
            title: "预期1（DB)",
            dataIndex: "expectDb",
            key: "expectDb",
            editable: true
        }, {
            title: "预期2（UI/交互)",
            dataIndex: "expectUi",
            key: "expectUi",
            editable: true
        }, {
            title: "预期3（接口)",
            dataIndex: "expectResponse",
            key: "expectResponse",
            editable: true
        }, {
            title: "预期4（其他)",
            dataIndex: "expectOther",
            key: "expectOther",
            width: "150px",
            editable: true
        }, {
            title: "操作",
            key: "operation",
            width: "110px",
            render: item => {
                return (
                    <div>{
                        !this.isEditing(item) && <div>
                            <a onClick={() => this.addStep(item)}>
                                增加
                            </a>&emsp;
                            <a onClick={() => this.copyStep(item)}>
                                复制
                            </a><br/>
                            <a onClick={() => this.deleteStep(item)}>
                                删除
                            </a>&emsp;
                            <a onClick={() => this.editStep(item)}>
                                编辑
                            </a>
                        </div>
                    }{
                        this.isEditing(item) &&
                        <span>
						  <EditableContext.Consumer>
							{form => (
                                <a
                                    href="javascript:;"
                                    onClick={() => this.saveStep(form, item.stepId)}
                                    style={{marginRight: 8}}
                                >
                                    保存
                                </a>
                            )}
						  </EditableContext.Consumer>
						  <Popconfirm
                              title="Sure to cancel?"
                              onConfirm={() => this.cancel(item.stepId)}
                          >
							<a>取消</a>
						  </Popconfirm>
						</span>
                    }
                    </div>
                )
            }
        }
    ];


    baseLabel = () => {
        const baseLabel = [];
        this.state.label.map((lab) => {
            if (lab.type === 'base') {
                baseLabel.push(lab.labelName)
            }
        });
        return baseLabel;
    };

    handleClose = removedTag => {
        const newLabel = this.state.label.filter(lab => lab.labelName !== removedTag);
        this.setState({label: newLabel});
    };

    showInput = () => {
        this.setState({inputVisible: true}, () => this.input.focus());
    };

    handleInputChange = e => {
        this.setState({inputValue: e.target.value});
    };

    handleInputConfirm = () => {
        const {inputValue} = this.state;
        let {label} = this.state;
        let index1 = label.findIndex(l => {
            return l.labelName === inputValue
        });
        if (inputValue && index1 === -1) {
            label = [...label, {type: "system", labelName: inputValue}];
        }
        this.setState({
            label,
            inputVisible: false,
            inputValue: '',
        });
    };

    saveInputRef = input => (this.input = input);

    changeProduct = async (product_id) => {
        const res = await getModuleList({productId: product_id, allLayer: true});
        this.props.dispatch(updateProductModuleList(res.data));
        this.props.dispatch(updateTestCaseDetail({product: product_id}));
        this.props.form.setFieldsValue({module: null})
    };

    fetchLabel = async value => {
        this.setState({data: [], fetching: true});
        const res = await getLabelList({type: "base", keyWords: value});
        this.setState({baseLabel: res.data.list, fetching: false});
    };

    handleChange = value => {
        let {label} = this.state;
        if (value && label.indexOf(value) === -1) {
            label.map(lab => {
                if (lab.labelName === value) {
                    return;
                }
            });
            this.state.baseLabel.map(lab => {
                if (lab.name === value) {
                    label = [...label, {type: "base", labelId: lab.id}];
                }
            })
        }
        this.setState({
            baseLabel: [],
            label,
            fetching: false
        });
    };

    addStep = (currentStep) => {
        const {step} = this.state;
        let newStep = Object.assign([], step);
        newStep.map(s => {
            if (s.stepId > currentStep.stepId) {
                s.stepId += 1;
            }
        });
        newStep.push({
            parent: 0,
            caseId: this.state.id,
            version: this.state.version,
            type: "",
            desc: "",
            expectDb: "",
            expectUi: "",
            expectResponse: "",
            expectOther: "",
            stepId: currentStep.stepId + 1
        });
        newStep.sort(this.stepSort);
        this.setState({step: newStep, editingId: currentStep.stepId + 1});
    };

    stepSort = (a, b) => {
        return a.stepId - b.stepId;
    };

    editStep = (currentStep) => {
        this.edit(currentStep.stepId);
    };

    deleteStep = (currentStep) => {
        const {step} = this.state;
        let newStep = [];
        step.map(s => {
            if (s.stepId !== currentStep.stepId) {
                newStep.push(s);
            }
        });
        newStep.map(s => {
            if (s.stepId > currentStep.stepId) {
                s.stepId -= 1;
            }
        });
        if (newStep.length === 0) {
            newStep.push({
                parent: 0,
                caseId: this.state.id,
                version: this.state.version,
                type: "",
                desc: "",
                expectDb: "",
                expectUi: "",
                expectResponse: "",
                expectOther: "",
                stepId: 1
            })
        }
        this.setState({step: newStep});
    };

    copyStep = (currentStep) => {
        const {step} = this.state;
        let newStep = Object.assign([], step);
        let copyStep = {};
        newStep.map(s => {
            if (s.stepId === currentStep.stepId) {
                copyStep = Object.assign({}, s);
            }
        });
        copyStep.stepId = newStep.length + 1;
        delete copyStep.id;
        newStep.push(copyStep);
        newStep.sort(this.stepSort);
        this.setState({step: newStep, editingId: newStep.length});
    };

    saveStep = (form, stepId) => {
        form.validateFields(async (error, row) => {
            if (error) {
                return;
            }
            const newStep = [...this.state.step];
            const index = newStep.findIndex(item => stepId === item.stepId);
            if (index > -1) {
                const item = newStep[index];
                newStep.splice(index, 1, {
                    ...item,
                    ...row,
                });
                this.setState({step: newStep, editingId: ''});
            } else {
                newStep.push(row);
                this.setState({step: newStep, editingId: ''});
            }
        })
    };
    cancel = () => {
        this.setState({editingId: ''});
    };

    autoGenSysLabel = async () => {
        const {getFieldValue} = this.props.form;
        let {label, step} = this.state;
        let content = getFieldValue("title") + ',';
        content += getFieldValue("precondition") + ',';
        step.map(s => {
            content += s.desc + ',';
            content += s.expectDb + ',';
            content += s.expectResponse + ',';
            content += s.expectUi + ',';
            content += s.expectOther + ',';
        });
        let resp = await genSysLabel(content);
        if (resp.data) {
            resp.data.map(l => {
                if (label.indexOf(l) === -1) {
                    label = [...label, {type: "system", labelName: l}];
                }
            });
            this.setState({
                label,
            });
        }
    };

    submit = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                if (this.state.editingId !== undefined && this.state.editingId !== "") {
                    message.warn("有测试步骤没有保存！");
                    return;
                }
                const {add, edit, copy, programCase, requireId} = this.props.location.state;
                const param = Object.assign({}, values, {
                        step: this.state.step,
                        label: this.state.label,
                        version: this.state.version,
                        id: this.state.id,
                        lastEditedBy: this.props.staffInfo.code,
                        lastEditedDate: new Date()
                    },
                    {
                        family: programCase ? 1 : 0
                    },
                    {
                        requireId: requireId === undefined ? null : requireId
                    });
                if (edit) {
                    const res = await updateCase(param);
                    if (res.errCode === 1 && res.data === null) {
                        this.backList()
                    }
                    if (res.errCode === 1 && res.data !== null) {
                        this.setState({conflictCaseInfo: Object.assign({}, res.data, {"time": new Date()})});
                        return;
                    }
                }
                if (copy || add) {
                    if (copy) {
                        param.id = null;
                    }
                    const res = await addCase(param);
                    if (res.data) {
                        this.backList()
                    }
                }
            }
        });
    };

    addNextCase = () => {
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                const {programCase, requireId} = this.props.location.state;
                const param = Object.assign({}, values, {
                        step: this.state.step,
                        label: this.state.label,
                        version: this.state.version,
                        id: this.state.id,
                        lastEditedBy: this.props.staffInfo.code,
                        lastEditedDate: new Date()
                    },
                    {
                        family: programCase ? 1 : 0
                    },
                    {
                        requireId: requireId === undefined ? null : requireId
                    });
                const res = await addCase(param);
                if (res.data) {
                    message.info("测试用例保存成功！");
                    this.props.form.setFieldsValue({title: null, precondition: null, baseLabel: []});
                    this.setState({
                        title: null,
                        precondition: null,
                        step: [{
                            desc: "",
                            expectDb: "",
                            expectOther: "",
                            expectResponse: "",
                            expectUi: "",
                            stepId: 1
                        }],
                        label: [],
                    });
                }
            }
        });
    };

    backList = () => {
        this.props.history.goBack();
    };

    edit = (stepId) => {
        this.setState({editingId: stepId});
    };

    isEditing = record => record.stepId === this.state.editingId;

    handleSearch = async value => {
        if (value) {
            const {product} = this.props.testCase.caseDetail;
            const res = await getModuleList({productId: product, keyWords: value, allLayer: true});
            this.props.dispatch(updateProductModuleList(res.data));
        } else {
            this.setState({data: []});
        }
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {allProduct, caseType, casePri, productModuleList} = this.props.testCase;
        const {product, module, title, type, pri, step, label, precondition, baseLabel, fetching} = this.state;
        const {inputVisible, inputValue, conflictCaseInfo} = this.state;
        const {add, edit} = this.props.location.state;


        const components = {
            body: {
                row: EditableFormRow,
                cell: EditableCell,
            },
        };

        const columns = this.columns.map((col) => {
            if (!col.editable) {
                return col;
            }
            return {
                ...col,
                onCell: record => ({
                    record,
                    dataIndex: col.dataIndex,
                    title: col.title,
                    editing: this.isEditing(record)
                }),
            };
        });

        const Mytag = ({label}) => {
            return label.map((lab, index) => {
                if (lab.type === "system") {
                    const isLongLab = lab.labelName.length > 5;
                    const tagElem = (
                        <Tag key={index} closable={true}
                             onClose={() => this.handleClose(lab.labelName)}>
                            {isLongLab ? `${lab.labelName.slice(0, 5)}...` : lab.labelName}
                        </Tag>
                    );
                    return isLongLab ? (
                        <Tooltip title={lab.labelName} key={index}>
                            {tagElem}
                        </Tooltip>
                    ) : (
                        tagElem
                    );
                    // return (
                    //     <Tag closable={true}
                    //          onClose={() => this.handleClose(lab.labelName)}>
                    //         {lab.labelName}
                    //     </Tag>
                    // )

                }
            })
        };

        return (
            <Provider>
                <Card title="这里是标题">
                    <div style={{width: 900}}>
                        <Form layout="horizontal" onSubmit={this.submit}>
                            <Row gutter={16}>
                                <Col span={12}>
                                    <FormItem label={"所属产品"} {...formItemLayout}>
                                        {getFieldDecorator("product", {
                                            initialValue: product
                                        })(
                                            <Select
                                                onChange={this.changeProduct}
                                            >
                                                {
                                                    allProduct.map((p) => (
                                                        <Select.Option key={p.id} value={p.id}>{p.name}</Select.Option>
                                                    ))
                                                }
                                            </Select>
                                        )}
                                    </FormItem>
                                </Col>
                                <Col span={12}>
                                    <FormItem label={"所属模块"} {...formItemLayout}>
                                        {getFieldDecorator("module", {
                                            initialValue: module
                                        })(
                                            <Select
                                                showSearch
                                                filterOption={false}
                                                onSearch={this.handleSearch}
                                                notFoundContent={null}
                                            >
                                                {
                                                    productModuleList.map((m) => (
                                                        <Select.Option key={m.id} value={m.id}><Tooltip
                                                            placement="topLeft"
                                                            title={m.name}>{m.name}</Tooltip></Select.Option>
                                                    ))
                                                }
                                            </Select>
                                        )}
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row gutter={16}>
                                <Col span={12}>
                                    <FormItem label={"用例类型"} {...formItemLayout}>
                                        {getFieldDecorator("type", {
                                            initialValue: type
                                        })(
                                            <Select>
                                                {
                                                    caseType.map((t) => (
                                                        <Select.Option key={t.code}
                                                                       value={t.code}>{t.name}</Select.Option>
                                                    ))
                                                }
                                            </Select>
                                        )}
                                    </FormItem>
                                </Col>
                                <Col span={12}>
                                    <FormItem label={"优先级"} {...formItemLayout}>
                                        {getFieldDecorator("pri", {
                                            initialValue: pri
                                        })(
                                            <Select>
                                                {
                                                    casePri.map((p) => {
                                                            let backgroundColor = '';
                                                            if (p.code === 1) {
                                                                backgroundColor = '#f5222d';
                                                            } else if (p.code === 2) {
                                                                backgroundColor = '#faad14';
                                                            } else {
                                                                backgroundColor = '#52c41a';
                                                            }
                                                            return <Select.Option key={p.code} value={p.code}>
                                                                <Avatar style={{backgroundColor}}
                                                                        size="small">{p.code}
                                                                </Avatar>
                                                            </Select.Option>
                                                        }
                                                    )
                                                }
                                            </Select>
                                        )}
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row gutter={16}>
                                <Col span={24}>
                                    <FormItem label={"用例标题"} {...formItemLayout2}>
                                        {getFieldDecorator("title", {
                                            rules: [
                                                {
                                                    required: true,
                                                    message: "用例标题不能为空！"
                                                }
                                            ],
                                            initialValue: title
                                        })(<Input/>)}
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row gutter={16}>
                                <Col span={24}>
                                    <FormItem label={"前置条件"} {...formItemLayout2}>
                                        {getFieldDecorator("precondition", {
                                            initialValue: precondition
                                        })(<Input.TextArea rows={2}/>)}
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row gutter={16}>
                                <Col span={24}>
                                    <FormItem label={"步骤"} {...formItemLayout2}>
                                        <Table
                                            rowKey="id"
                                            columns={columns}
                                            components={components}
                                            bordered={true}
                                            dataSource={step}
                                            rowClassName="editable-row"
                                            className={wrapper}
                                        />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row gutter={16}>
                                <Col span={24}>
                                    <FormItem label={"系统标签"} {...formItemLayout2}>
                                        <div>
                                            <Mytag label={label}/>
                                            {inputVisible && (
                                                <Input
                                                    ref={this.saveInputRef}
                                                    type="text"
                                                    size="small"
                                                    style={{width: 78}}
                                                    value={inputValue}
                                                    onChange={this.handleInputChange}
                                                    onBlur={this.handleInputConfirm}
                                                    onPressEnter={this.handleInputConfirm}
                                                />
                                            )}
                                            {!inputVisible && (
                                                <Tag onClick={this.showInput}
                                                     style={{background: '#fff', borderStyle: 'dashed'}}>
                                                    <Icon type="plus"/> New Tag
                                                </Tag>
                                            )}
                                            <Button onClick={this.autoGenSysLabel} shape={"circle"} icon={"rocket"}
                                                    size={"small"}/>
                                        </div>
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row gutter={16}>
                                <Col span={24}>
                                    <FormItem label={"基础标签"} {...formItemLayout2}>
                                        {getFieldDecorator("baseLabel", {
                                            rules: [
                                                {
                                                    required: true,
                                                    message: "请添加基础标签！"
                                                }
                                            ],
                                            initialValue: this.baseLabel()
                                        })(<Select
                                            mode="multiple"
                                            style={{width: '100%'}}
                                            placeholder="请添加基础标签"
                                            notFoundContent={fetching ? <Spin size="small"/> : null}
                                            filterOption={false}
                                            onSearch={this.fetchLabel}
                                            onSelect={this.handleChange}
                                            onDeselect={this.handleClose}
                                        >
                                            {
                                                baseLabel.map(b => (
                                                        <Select.Option key={b.id} value={b.name}>{b.name}</Select.Option>
                                                    )
                                                )
                                            }
                                        </Select>)}
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row type="flex" justify="center">
                                <Col span={10}>
                                </Col>
                                <Col span={2}>
                                    <Button type={"primary"} htmlType="submit">完成</Button>
                                </Col>
                                <Col span={2}>
                                    <Button onClick={this.backList}>取消</Button>
                                </Col>
                                <Col span={6}>
                                </Col>
                                <Col span={4}>
                                    {add && <Button type={"primary"} onClick={this.addNextCase}>继续下一条</Button>}
                                </Col>
                            </Row>
                        </Form>
                    </div>
                    <BaseCaseConflictSolve conflictCaseInfo={conflictCaseInfo}/>
                </Card>
            </Provider>
        )
    }
}

const wrapper = css`
white-space: pre-wrap;
`;