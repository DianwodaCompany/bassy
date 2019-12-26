import React from "react";
import {Button, Card, Col, Form, Input, Progress, Row, Select, Tag, Avatar, List, Icon, Radio, message} from "antd";
import moment from 'moment';
import {connect} from 'react-redux'
import {getStaffList, getBBSPosts, countBBSPersCtrb,countStudyTimes} from "../../apis/index"
import index from './index.less';
import ActiveTree from './activeTree'
import marked from "marked";
import {Chart, Coord, Geom, Label, Tooltip, Legend} from "bizcharts";
import * as G2 from "bizcharts";

const RadioButton = Radio.Button;
const RadioGroup = Radio.Group;

const ListContent2 = ({data: {authorName, ctrbNum, up}}) => (
    <Row gutter={16}>
        <Col span={8}>
            <p style={{width: 150}}>{authorName}</p>
        </Col>
        <Col span={16}>
            <Progress
                percent={ctrbNum}
                size="small"
                showInfo={false}
                // strokeColor={up ? "#1890ff" : "#f5222d"}
            />
        </Col>
    </Row>
);

@connect(state => ({
    staffInfo: state.common.staffInfo,
}))
@Form.create()
export default class BBS extends React.Component {

    state = {
        loading: false,
        ctrbNum: [],
        posts: [],
        pageNum: 1,
        pageSize: 5,
        total: 0,
        staffCode: 0,
        staffList: [{code: '', name: ''}],
        studyList: [],
    };

    countPersCtrb = async () => {
        let resp = await countBBSPersCtrb();
        this.setState({ctrbNum: resp.data});
    };

    getPosts = async (pageNum = 1, type, authorCode, keyword, sort) => {
        if (sort === undefined || sort === "") {
            sort = "hotArticle";
        }
        const data = {pageNum, pageSize: 6, type, authorCode, keyword, sort, staffCode: window.__data.staffCode};
        let resp = await getBBSPosts(data);
        if (resp.data) {
            const {currentPage, pageSize, list, totalCount,like,likeStatus} = resp.data;
            this.setState({
                posts: list,
                pageSize,
                total: totalCount,
                pageNum: currentPage,
                loading: false,
                like: like,
                likeStatus: likeStatus,
            });
        } else {
            message.error("查询出错!");
        }
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

    handleReset = () => {
        this.props.form.resetFields()
    };

    showDetail = (id,type) => {
        if(type !== 50){
            this.props.history.push({
                pathname: '/bbs/articleList/articleInfo/' + id,
                state: {
                    id: id,
                }
            });
        }else {
            this.props.history.push({
                pathname: '/bbs/articleList/weeklyInfo/' + id,
                state: {
                    id: id,
                }
            });
        }

    };

    countStudyTimes = async () => {
        const data = {week: moment().format('w')-1};
        let resp = await countStudyTimes(data);
        this.setState({studyList: resp.data});
    };

    handleChangeWeekButton = async (e) => {
        let week = parseInt(moment().format('w'));
        const data = {week: e.target.value ==="7days"? week : week-1};
        let resp = await countStudyTimes(data);
        this.setState({studyList: resp.data});
    }

    handleSearch = e => {
        e.preventDefault();
        const {type, author, keyword, sort} = this.props.form.getFieldsValue();
        let authorCode = null;
        if (author != null)
            authorCode = /\((\d+)\)/.exec(author)[1];
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                console.log("Received values of form: ", values);
                this.getPosts(1, type, authorCode, keyword, sort);
                localStorage.setItem("projectSearchData", JSON.stringify(values))
            }
        });
    };

    changePage = page => {
        this.setState({pageNum: page});
        const {type, author, keyword, sort} = this.props.form.getFieldsValue();
        let authorCode = null;
        if (author != null)
            authorCode = /\((\d+)\)/.exec(author)[1];
        this.getPosts(page, type, authorCode, keyword, sort);
    };

    componentWillMount() {
        this.setState({loading: true});
        this.countStudyTimes();
        this.getPosts();
        this.countPersCtrb();
    }

    createMarkup = (html) => {
        return {__html: html};
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {
            pageNum,
            pageSize,
            total,
            loading,
            posts,
            staffList,
            studyList,
        } = this.state;

         let tagColors = ['gold','cyan','blue','volcano','geekblue','green','purple'];

        console.log("studyList>>>>>>>>>>>>>",studyList)
        let studyArray = [];
        if (studyList) {
            for (let study of studyList) {
                    studyArray.push({
                        count: "打卡" + study.studyTimes + "次",
                        number: study.studyPeopleNum,
                        name: study.authorName,
                    })
            }
            console.log("studyArray>>>>>>>>>>>>>", studyArray)
        }

        const ListContent = ({data:{id, type, content, authorName, modifyTm}}) => (
            <div className={index.listContent}>
                <div className={index.description}>
                    <span style={{cursor: "pointer"}} onClick={() => this.showDetail(id,type)}>
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

        const IconText = ({text, likeStatus}) => (
            <div >
        <Icon type="like" theme={likeStatus ? "filled" : "outlined"} style={{marginRight: 8,color: '#1890ff'}}/>
                {text}
      </div>);

        return (
            <div>
                <Col span={18} style={{paddingRight: 20}}>
                    <Card bordered={false}>
                        <Form
                            layout="inline"
                            onSubmit={this.handleSearch}
                            style={{marginBottom: 12}}
                        >
                            <Row type="flex" justify="start" align="bottom">
                                <Col span={4}>
                                    <Form.Item label="类型">
                                        {getFieldDecorator("type", {
                                            initialValue: ""
                                        })(<Select
                                            style={{width: 100}}
                                        >
                                            <Option value="">全部</Option>
                                            <Option value="10">文章</Option>
                                            <Option value="20">短想法</Option>
                                            <Option value="30">学习打卡</Option>
                                            <Option value="40">记一笔</Option>
                                            <Option value="50">周刊</Option>
                                        </Select>)}
                                    </Form.Item>
                                </Col>
                                <Col span={5}>
                                    <Form.Item label="作者">
                                        {getFieldDecorator("author")(
                                            <Select
                                                showSearch
                                                placeholder="工号或姓名"
                                                onSearch={this.changPersonKeyWord}
                                                style={{width: 140}}
                                            >
                                                {staffList.map((s) => (
                                                    <Option value={`(${s.code})${s.name}`} key={s.code}>
                                                        {"(" + s.code + ")" + s.name}
                                                    </Option>
                                                ))}
                                            </Select>
                                        )}
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
                                        {getFieldDecorator("sort", {
                                            initialValue: "hotArticle"
                                        })(
                                            <Radio.Group>
                                                <Radio.Button value="hotArticle">热帖</Radio.Button>
                                                <Radio.Button value="newArticle">新帖</Radio.Button>
                                            </Radio.Group>
                                        )}
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

                    <Card
                        style={{marginTop: 24, minHeight: 400}}
                        bordered={false}
                        bodyStyle={{padding: '8px 32px 32px 32px'}}
                    >
                        <List
                            size="large"
                            loading={loading}
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
                                            <a  style={{fontFamily:"scans-serif"}} className={index.listItemMetaTitle} onClick={() => this.showDetail(item.id,item.type)}>
                                                <strong>{item.title}</strong>
                                                <sup> <Icon type={item.type ===30 ? "money-collect" : ""} style={{ fontSize: '14px',color:'#ff0000'}}/></sup>

                                            </a>
                                        }
                                        description={
                                            <span>
                                            {item.tag.map(function (tag) {
                                                return (
                                                    <Tag color={tagColors[Math.floor(Math.random()*tagColors.length)]} key={tag}>{tag}</Tag>
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
                    </Card>
                </Col>

                <Col span={6}>
                    <Card title="动态树" style={{minHeight: 800}}>
                        <ActiveTree {...this.props}/>
                    </Card>
                    <Card title="学习打卡" style={{marginTop: 20}} extra={
                        <div>
                            <RadioGroup   onChange={this.handleChangeWeekButton} defaultValue='7daysAgo' >
                                <RadioButton value='7daysAgo'>上周</RadioButton>
                                <RadioButton value='7days'>本周</RadioButton>
                            </RadioGroup>
                        </div>
                    }>

                        <Chart
                            onTooltipChange={(ev)=> {
                                var items = ev.items; // tooltip显示的项
                                var origin = items[0]; // 将一条数据改成多条数据
                                items.splice(0); // 清空
                                for(let detail of studyArray){
                                    if(detail.count === origin.name){
                                        items.push({
                                            name: detail.count,
                                            marker: true,
                                            value: detail.name,
                                        });
                                    }
                                }
                            }}
                            height={400} data={studyArray} padding="auto" forceFit >
                            <Coord type="theta" innerRadius={0.75} />
                            <Tooltip showTitle={false} />
                            <Legend />
                            <Geom type="intervalStack" position="number" color={['count', ['#1890ff', '#2fc25b','#facc14','#f04864']]} shape="sliceShape" >
                                <Label
                                    content="number"
                                    offset={-75}
                                    textStyle={{
                                        fill: '#000000',
                                        rotate: 0
                                    }}
                                    formatter={(val, item) => {
                                        return item.point.count + ': ' + val + '人';
                                    }}
                                />
                            </Geom>
                        </Chart>
                    </Card>

                    <Card title="贡献列表" style={{marginTop: 20}}>
                        <List
                            size="small"
                            split={false}
                            dataSource={this.state.ctrbNum}
                            footer={<span>
										<b>规则：</b>
										发表文章加5分，文章获一个赞加1分，文章获一个评论加2分；评论文章获1分，点赞获1分。
									</span>}
                            renderItem={item => (
                                <List.Item>
                                    <ListContent2 data={item}/>
                                </List.Item>
                            )
                            }/>
                    </Card>
                </Col>

            </div>
        )
    }
}