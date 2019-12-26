import React from "react";
import {Button, Card, Col, Form, Input, Row, Avatar, Tag, Tabs, List, Icon, Select, Divider} from "antd";
import {getBBSPosts} from "../../apis";
import {connect} from "react-redux";
import index from './index.less';
import moment from "moment/moment";
import marked from "marked";
import highlight from "highlight.js";
import SimpleMDE from "simplemde";

const TabPane = Tabs.TabPane;

@connect(
    state => ({
        staffInfo: state.common.staffInfo,
    })
)
@Form.create()
export default class MyArticle extends React.Component {

    constructor(props) {
        super(props);
        this.handleTabChange = this.handleTabChange.bind(this)
    }

    state = {
        smde: null,
        // id: null,
        posts: [],
        type: 10,
        id: null,
        content: null,
        pageNum: 1,
        pageSize: 5,
        total: 0,
        paneKey:"articles",
        noteType:"",
    };

    publish = () => {
        this.props.history.push({
            pathname: '/bbs/myArticle/add',
            state: {
                id: this.state.id,
                type:this.state.paneKey === "articles" ? 10 : 20
            }
        });
    };

    publishSt = () => {
        this.props.history.push({
            pathname: '/bbs/myArticle/addSt',
            state: {
                id: this.state.id,
            }
        });
    };

    publishNote = () => {
        this.props.history.push({
            pathname: '/bbs/myArticle/addNote',
            state: {
                id: this.state.id,
            }
        });
    };

    edit = (id) => {
        this.props.history.push({
            pathname: '/bbs/myArticle/edit',
            state: {
                id: id
            }
        });
    };

    editSt = (id) => {
        this.props.history.push({
            pathname: '/bbs/myArticle/editSt',
            state: {
                id: id
            }
        });
    };

    editNote = (id) => {
        this.props.history.push({
            pathname: '/bbs/myArticle/editNote',
            state: {
                id: id
            }
        });
    };

    getBBSPosts = async (pageNum = 1, type, authorCode, keyword, sort, noteType) => {
        if (sort === undefined || sort === ""){
            sort = "hotArticle";
        }
        if (type === undefined  || type === ""){
            type = "10";
        }
        const data = {pageNum, pageSize: 5, type, authorCode, keyword, sort,staffCode:window.__data.staffCode,noteType};
        let resp = await getBBSPosts(data);
        const {currentPage, pageSize, list, totalCount} = resp.data;

        this.setState({
            posts: list,
            pageSize,
            total: totalCount,
            pageNum: currentPage,
        });
    };

    handleReset = () => {
        this.props.form.resetFields()
    };

    handleSearch = e => {
        e.preventDefault();
        const {keyword, noteType} = this.props.form.getFieldsValue();
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                console.log("Received values of form: ", values);
                this.getBBSPosts(1, this.state.type, window.__data.staffCode, keyword, "", noteType);
                localStorage.setItem("projectSearchData", JSON.stringify(values))
            }
        });
    };

    changePage = page => {
        this.setState({pageNum: page});
        this.getBBSPosts(page, this.state.type, window.__data.staffCode,null,"",this.state.noteType);
    };

    componentWillMount() {
        this.getBBSPosts(1, this.state.type, window.__data.staffCode, null, "",this.state.noteType);
    }

    createMarkup = (html) => {
        return {__html: html};
    };

    operationsSt =() => {

        if(this.state.paneKey === 'study' ){
            return  (<div>
                <Button type="primary" onClick={this.publishSt}>打卡</Button>
            </div>)
        }else if(this.state.paneKey === 'note' ){
            return  (<div>
                <Button type="primary" onClick={this.publishNote}>记一笔</Button>
            </div>)
        }else{
            return  (<div>
                <Button type="primary" onClick={this.publish}>发布</Button>
            </div>)
        }

    }

    handleTabChange(key) {
    if(key === "articles") {
        this.state.type = 10;
    } else if(key === "microIdea"){
        this.state.type = 20;
    } else if(key === "study"){
        this.state.type = 30;
    }else {
        this.state.type = 40;
    }
    this.getBBSPosts(1, this.state.type, this.props.staffInfo.code, null, "","");
    this.setState({
        paneKey:key,
    });
}

    render() {
        const {getFieldDecorator} = this.props.form;
        const {posts,pageNum,pageSize,total} = this.state;
        const ListContent = ({ data: {id, content, modifyTm, authorName} }) => (
            <div className={index.listContent}>
                <div className={index.description}>
                    <span style={{cursor: "pointer"}} onClick={() => this.edit(id)}>
                        {
                            <div dangerouslySetInnerHTML={this.createMarkup(marked(content))} />
                        }
                    </span>
                </div>
                <div className={index.extra}>
                    <Avatar icon="user" size="small"/>
                    {authorName} 发布于
                    <em>{moment(modifyTm).format('YYYY-MM-DD HH:mm')}</em>
                </div>
            </div>
        );

        const IconText = ({ text, likeStatus}) => (
            <span style={{cursor: "default"}}>
        <Icon type="like" theme={likeStatus ? "filled" : "outlined"} style={{marginRight: 8,color: '#1890ff'}}/>
                {text}
      </span>);

        return(
            <Card bordered={false}>
                <div>
                    <Tabs defaultActiveKey="articles" tabBarExtraContent={this.operationsSt()}  onChange={this.handleTabChange} >
                        <TabPane tab="文章" key="articles">
                            <List
                                size="large"
                                rowKey="id"
                                itemLayout="vertical"
                                pagination={{
                                    current: pageNum,
                                    total,
                                    pageSize,
                                    onChange: this.changePage
                                }}
                                dataSource={posts}
                                renderItem={item => (
                                    <List.Item
                                        key={item.id}
                                        extra={<div className={index.listItemExtra}/>}
                                    >
                                        <List.Item.Meta
                                            title={
                                                <a className={index.listItemMetaTitle} onClick={() => this.edit(item.id)}>
                                                    {item.title}
                                                </a>
                                            }
                                            description={
                                                <span>
                                                    {item.tag.map(function (tag) {
                                                        return (
                                                            <Tag key={tag}>{tag}</Tag>
                                                        )
                                                    })}
                                                </span>
                                            }
                                        />
                                        <ListContent data={item}/>
                                        <div style={{marginTop:15}}>
                                            <IconText  text={item.like} likeStatus={item.likeStatus} />
                                        </div>
                                    </List.Item>
                                )}
                            />
                        </TabPane>
                        <TabPane tab="想法" key="microIdea">
                            <List
                                size="large"
                                rowKey="id"
                                itemLayout="vertical"
                                pagination={{
                                    current: pageNum,
                                    total,
                                    pageSize,
                                    onChange: this.changePage
                                }}
                                dataSource={posts}
                                renderItem={item => (
                                    <List.Item
                                        key={item.id}
                                        extra={<div className={index.listItemExtra}/>}
                                    >
                                        <List.Item.Meta
                                            title={
                                                <a className={index.listItemMetaTitle} onClick={() => this.edit(item.id)}>
                                                    {item.title}
                                                </a>
                                            }
                                            description={
                                                <span>
                                                    {item.tag.map(function (tag) {
                                                        return (
                                                            <Tag key={tag}>{tag}</Tag>
                                                        )
                                                    })}
                                                </span>
                                            }
                                        />
                                        <ListContent data={item}/>
                                        <div style={{marginTop:15}}>
                                            <IconText  text={item.like} likeStatus={item.likeStatus} />
                                        </div>
                                    </List.Item>
                                )}
                            />
                        </TabPane>
                        <TabPane tab="学习打卡" key="study">
                            <List
                                size="large"
                                rowKey="id"
                                itemLayout="vertical"
                                pagination={{
                                    current: pageNum,
                                    total,
                                    pageSize,
                                    onChange: this.changePage
                                }}
                                dataSource={posts}
                                renderItem={item => (
                                    <List.Item
                                        key={item.id}
                                        extra={<div className={index.listItemExtra}/>}
                                    >
                                        <List.Item.Meta
                                            title={
                                                <a className={index.listItemMetaTitle} onClick={() => this.editSt(item.id)}>
                                                    {item.title}
                                                </a>
                                            }
                                            description={
                                                <span>
                                                    {item.tag.map(function (tag) {
                                                        return (
                                                            <Tag key={tag}>{tag}</Tag>
                                                        )
                                                    })}
                                                </span>
                                            }
                                        />
                                        <ListContent data={item}/>
                                        <div style={{marginTop:15}}>
                                            <IconText  text={item.like} likeStatus={item.likeStatus} />
                                        </div>
                                    </List.Item>
                                )}
                            />
                        </TabPane>
                        <TabPane tab="记一笔" key="note">
                            <Card bordered={false}>
                                <Form
                                    layout="inline"
                                    onSubmit={this.handleSearch}
                                    style={{marginBottom: 12}}
                                >
                                    <Row type="flex" justify="start" align="bottom">
                                        <Col span={4}>
                                            <Form.Item label="所属分类">
                                                {getFieldDecorator("noteType", {
                                                    initialValue: ""
                                                })(<Select
                                                    style={{width: 100}}
                                                >
                                                    <Option value="">全部</Option>
                                                    <Option value="0">业务经验</Option>
                                                    <Option value="1">测试经验</Option>
                                                    <Option value="2">工具技巧</Option>
                                                    <Option value="3">其他</Option>
                                                </Select>)}
                                            </Form.Item>
                                        </Col>

                                        <Col span={6}>
                                            <Form.Item>
                                                {getFieldDecorator("keyword")(<Input placeholder="搜索关键字..."
                                                                                     style={{width: 220}}/>)}
                                            </Form.Item>
                                        </Col>

                                        <Col span={4}>
                                            <Form.Item>
                                                <Button type="primary" htmlType="submit">
                                                    搜索
                                                </Button>
                                            </Form.Item>
                                            <Form.Item>
                                                <Button type="primary" onClick={this.handleReset}>
                                                    重置
                                                </Button>
                                            </Form.Item>
                                        </Col>
                                    </Row>
                                </Form>
                            </Card>
                            <Divider dashed/>
                            <List
                                size="large"
                                rowKey="id"
                                itemLayout="vertical"
                                pagination={{
                                    current: pageNum,
                                    total,
                                    pageSize,
                                    onChange: this.changePage
                                }}
                                dataSource={posts}
                                renderItem={item => (
                                    <List.Item
                                        key={item.id}
                                        extra={<div className={index.listItemExtra}/>}
                                    >
                                        <List.Item.Meta
                                            title={
                                                <a className={index.listItemMetaTitle} onClick={() => this.editNote(item.id)}>
                                                    {item.title}
                                                </a>
                                            }
                                            description={
                                                <span>
                                                    {item.tag.map(function (tag) {
                                                        return (
                                                            <Tag key={tag}>{tag}</Tag>
                                                        )
                                                    })}
                                                </span>
                                            }
                                        />
                                        <ListContent data={item}/>
                                        <div style={{marginTop:15}}>
                                            <IconText  text={item.like} likeStatus={item.likeStatus} />
                                        </div>
                                    </List.Item>
                                )}
                            />
                        </TabPane>
                    </Tabs>
                </div>
            </Card>
        )
    }
}