import {connect} from "react-redux";
import {Button, Card, Col, Form, Icon, Input, message, Modal, Row, Table, Tooltip, Tree} from "antd";
import React from "react";
import md5 from "md5";
import {getParenModuleSub, getProductModuleTree, productModuleOperate} from "../../../../apis/index";
import StaffCode from "../../../Common/views/staffCode";

const {TreeNode} = Tree;

const formItemLayout = {
    labelCol: {
        xs: {span: 24},
        sm: {span: 8},
    },
    wrapperCol: {
        xs: {span: 24},
        sm: {span: 16},
    },
};


@connect(state => ({
    staffInfo: state.common.staffInfo,
    testCase: state.testCase
}))
@Form.create()
export default class ProductModuleManage extends React.Component {

    state = {
        editProduct: false,
        editProductDefender: null,
        editProductName: null,
        editProductId: null,
        editModule: false,
        editModuleDefender: null,
        editModuleName: null,
        editModuleId: null,
        moduleData: [],
        productModuleTreeDate: [],
        spanInfo: {
            type: 0,
            treeSpan: 8,
            moduleSpan: 16
        }
    };


    columns = [
        {
            dataIndex: 'parentModuleName',
            render: (value, row, index) => {
                const obj = {
                    children: value,
                    props: {},
                };
                if (index === 0) {
                    obj.props.rowSpan = 999;
                }
                // These two are merged into above cell
                if (index > 0) {
                    obj.props.rowSpan = 0;
                }
                return obj;
            },
        },
        {
            title: "模块信息",
            render: item => {
                const newModule = item.key !== undefined;
                return <Row gutter={12}>
                    <Col span={8}>
                        <Input value={item.moduleName} onChange={(e) => this.addModuleName(item, e)}
                               placeholder={"模块名称"}
                               disabled={!newModule}/>
                    </Col>
                    <Col span={8}>
                        {
                            newModule ? <StaffCode onSelect={e => this.addModuleDefender(item, e)}
                                                   value={item.moduleDefender}/> :
                                // newModule ? <StaffCode/> :
                                <Input value={item.moduleDefender} disabled/>
                        }
                    </Col>
                    <Col span={1}>
                        <Tooltip title={"添加"}><Icon type="plus" onClick={this.addModule}/></Tooltip>
                    </Col>
                    {
                        newModule &&
                        <Col span={7}>
                            <Tooltip title={"删除"}><Icon type="minus" onClick={() => this.deleteModule(item)}/></Tooltip>
                        </Col>
                    }
                </Row>
            }
        }
    ];

    addNewProduct = () => {
        this.setState({
            editProduct: true,
            editProductDefender: null,
            editProductName: null,
            editProductId: null,
            currentNode: null
        });
    };

    cancelAddNewProduct = () => {
        this.props.form.resetFields();
        this.setState({editProduct: false, currentNode: null});
    };

    reallyEditProduct = () => {
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            console.info(values);
            let params = {};
            if (this.state.currentNode == null) {
                params = Object.assign({}, values, {target: 0, operate: 0});
            } else {
                params = Object.assign({}, values, {target: 0, operate: 2},
                    {productId: this.state.currentNode.productId});
            }
            const res = await productModuleOperate(params);
            if (res.errCode === 1) {
                this.props.form.resetFields();
                this.initProductTree();
                this.setState({editProduct: false, currentNode: null});
            }
        });
    };

    editProduct = (node) => {
        this.setState({
            editProduct: true,
            editProductDefender: node.defender,
            editProductName: node.name,
            editProductId: node.productId,
            currentNode: node
        });
    };

    clickEditNode = (node) => {
        if (node.moduleId !== null) {
            this.clickEditModule(node);
            return;
        }
        this.editProduct(node);
    };

    clickEditModule = (node) => {
        this.setState({
            editModule: true,
            editModuleDefender: node.defender,
            editModuleName: node.name,
            editModuleId: node.moduleId,
            currentNode: node
        });
    };
    reallyEditModule = () => {
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            const params = Object.assign({}, values, {target: 1, operate: 2},
                {moduleId: this.state.currentNode.moduleId});
            const res = await productModuleOperate(params);
            if (res.errCode === 1) {
                this.props.form.resetFields();
                this.initProductTree();
                this.setState({editModule: false, currentNode: null});
            }
        });
    };

    cancelEditModule = () => {
        this.props.form.resetFields();
        this.setState({editModule: false, currentNode: null});
    };

    clickDeleteButton = async (node) => {
        const params = Object.assign({}, {operate: 1},
            {target: node.moduleId == null ? 0 : 1},
            {moduleId: node.moduleId, productId: node.productId});
        const res = await productModuleOperate(params);
        if (res.errCode === 1) {
            message.success("删除节点成功！");
            this.initProductTree();
        }
    };

    addModule = () => {
        const fistModule = this.state.moduleData[0];
        const newModule = this.state.moduleData;
        newModule.push({
            key: md5('' + Date.now() + Math.random()),
            parentModuleId: fistModule.parentModuleId,
            parentModuleName: fistModule.parentModuleName,
            parentModuleDefender: fistModule.parentModuleDefender,
            moduleId: "",
            moduleName: "",
            moduleDefender: ""
        });
        this.setState({
            moduleData: newModule
        });
        console.info("")
    };

    addModuleName = (item, e) => {
        const newModuleData = Object.assign([], this, this.state.moduleData);
        newModuleData.map((m) => {
            if (m.key === item.key) {
                m.moduleName = e.target.value;
            }
        });
        this.setState({moduleData: newModuleData});
    };

    addModuleDefender = (item, e) => {
        const newModuleData = Object.assign([], this, this.state.moduleData);
        newModuleData.map((m) => {
            if (m.key === item.key) {
                m.moduleDefender = e;
            }
        });
        this.setState({moduleData: newModuleData});
    };

    reallyAddModule = () => {
        const {currentNode} = this.state;
        this.state.moduleData.map(async (m) => {
            if (m.key === undefined) {
                return;
            }
            let path = "";

            if (currentNode.path === null) {
                path = "";
            } else if (currentNode.path === "") {
                path = currentNode.moduleId;
            } else {
                path = currentNode.path + "," + currentNode.moduleId
            }

            const params = Object.assign({}, {
                productId: currentNode.productId,
                moduleName: m.moduleName,
                path,
                defender: m.moduleDefender
            }, {
                target: 1,
                operate: 0
            });
            const res = await productModuleOperate(params);
            if (res.errCode === 1) {
                this.initProductTree();
                this.clickTreeNode(this.state.currentNode)
            }
        });
    };

    returnBaseCase = () => {

    };


    deleteModule = (item) => {
        const newModule = this.state.moduleData.filter(m => {
            return m.key !== item.key;
        });
        this.setState({
            moduleData: newModule
        });
        console.info(item)
    };

    clickTreeNode = async (node) => {
        let params = {productId: node.productId, moduleId: node.moduleId};
        const res = await getParenModuleSub(params);
        res.data.push(
            {
                key: md5('' + Date.now() + Math.random()),
                parentModuleId: node.moduleId,
                parentModuleName: node.name,
                parentModuleDefender: node.defender,
                moduleId: "",
                moduleName: "",
                moduleDefender: ""
            }
        );
        this.setState({moduleData: res.data, currentNode: node});
    };

    componentDidMount() {
        this.initProductTree();
    }

    initProductTree = async () => {
        const res = await getProductModuleTree({});
        this.setState({productModuleTreeDate: res.data});
    };

    changeSpan = () => {
        const {type} = this.state.spanInfo;
        if (type) {
            this.setState({spanInfo: {type: 0, treeSpan: 8, moduleSpan: 16}});
        } else {
            this.setState({spanInfo: {type: 1, treeSpan: 16, moduleSpan: 8}});
        }
    };

    render() {

        const renderTreeNode = (data) => data.map(node => {

            node.title = (
                <div>
					<span onClick={() => {
                        this.clickTreeNode(node)
                    }}>{node.name + "【" + node.defender + "】"}</span>&nbsp;&nbsp;
                    <Tooltip title={"编辑"}><Icon type="edit"
                                                onClick={() => this.clickEditNode(node)}/></Tooltip>&nbsp;&nbsp;
                    <Tooltip title={"删除"}><Icon type="delete" onClick={() => this.clickDeleteButton(node)}/></Tooltip>
                </div>
            );
            if (node.children) {
                return <TreeNode title={node.title} key={node.id}>{renderTreeNode(node.children)}</TreeNode>
            }
            return <TreeNode title={node.title} key={node.id}/>
        });
        const {getFieldDecorator} = this.props.form;
        const {
            editProduct, editProductName, editProductDefender,
            editModule, editModuleName, editModuleDefender, moduleData, productModuleTreeDate
        } = this.state;
        const {type, treeSpan, moduleSpan} = this.state.spanInfo;
        return <div>
            <Row gutter={12}>
                <Col span={treeSpan}>
                    <Card title={"产品及模块"} style={{overflowX:"scroll"}} extra={
                        type ? <a onClick={() => this.changeSpan()}>返回</a> : <a onClick={() => this.changeSpan()}>加宽</a>
                    }>
                        <Tree showLine defaultExpandedKeys={['0-0-0']} onSelect={this.onSelect}>
                            {
                                renderTreeNode(productModuleTreeDate)
                            }
                        </Tree>
                        <a onClick={this.addNewProduct}>添加产品</a>
                    </Card>
                </Col>
                <Col span={moduleSpan}>
                    <Card title={"关系维护"}>
                        <Table columns={this.columns}
                               dataSource={moduleData} showHeader={false} pagination={false}/>
                        <Row gutter={12} type="flex" justify="center">
                            <Col span={2}><Button type="primary" onClick={this.reallyAddModule}>保存</Button></Col>
                            <Col span={2}><Button type="primary" onClick={this.returnBaseCase}>返回</Button></Col>
                        </Row>
                    </Card>
                </Col>
            </Row>
            <Modal visible={editModule} title={"模块管理"} okText={"提交"} cancelText={"取消"}
                   onCancel={this.cancelEditModule} onOk={this.reallyEditModule}>
                <Form layout={"horizontal"} labelCol={{span: 5}} wrapperCol={{span: 15}}>
                    <Form.Item label={"模块名称"}>
                        {getFieldDecorator('moduleName', {
                            rules: [
                                {
                                    required: true, message: '模块名称不能为空!',
                                }
                            ],
                            initialValue: editModuleName
                        })(<Input/>)}
                    </Form.Item>
                    <Form.Item label={"负责人"}>
                        {getFieldDecorator('defender', {
                            rules: [
                                {
                                    required: true, message: '维护人不能为空!',
                                }
                            ],
                            initialValue: editModuleDefender
                        })(<StaffCode/>)}
                    </Form.Item>
                </Form>
            </Modal>
            <Modal visible={editProduct} title={editProductName === null ? "产品新增" : "产品编辑"} okText={"提交"}
                   cancelText={"取消"}
                   onCancel={this.cancelAddNewProduct} onOk={this.reallyEditProduct}>
                <Form layout={"horizontal"} labelCol={{span: 5}} wrapperCol={{span: 15}}>
                    <Form.Item label={"产品名称"}>
                        {getFieldDecorator('productName', {
                            rules: [
                                {
                                    required: true, message: '产品名称不能为空!',
                                }
                            ],
                            initialValue: editProductName
                        })(<Input/>)}
                    </Form.Item>
                    <Form.Item label={"负责人"}>
                        {getFieldDecorator('defender', {
                            rules: [
                                {
                                    required: true, message: '维护人不能为空!',
                                }
                            ],
                            initialValue: editProductDefender
                        })(<StaffCode/>)}
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    }
}
