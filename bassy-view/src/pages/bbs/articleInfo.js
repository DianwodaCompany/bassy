import React from "react";
import {Card, Input, Divider, Icon, Button, Tooltip, List} from "antd";
import marked from "marked";
import {getBBSDetail, likeBBS} from "../../apis";
import {message} from "antd/lib/index";
import index from './index.less';
import moment from "moment";


const ArticleComment = (info) => (
    <div>
        <span>小红：瘦的太快不好。。。</span>
        <h3/>
        <Input.TextArea autosize={true} placeholder="别客气，来吧。。。"/>
    </div>
);



export default class ArticleInfo extends React.Component {

    state = {
        showMessage: false,
        article: {
            id: null,
            title: "",
            content: "",
            like: 0,
            likeStatus: false,
            relateBbs:[],
        },
    };

    getBBSInfo = async (id) => {
        const data = {staffCode: window.__data.staffCode};
        let resp = await getBBSDetail(id, data);
        if (resp.data) {
            this.setState({article: resp.data});
        } else {
            message.error("获取文章出错");
        }
    }


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

    componentWillMount = async () => {
        this.getBBSInfo(this.props.location.state.id);
    };

    componentWillReceiveProps = (prevProps) => {
        const {params} = this.props.match;
        const {id} = prevProps.match.params;
        if (params.id === id) {
            return false;
        }
        this.getBBSInfo(id);
    };

    doLike = async (item) => {
        const data = {id: item.id, likeStatus: item.likeStatus, staffCode: window.__data.staffCode};
        await likeBBS(data);
        const data2 = {staffCode: window.__data.staffCode};
        let resp = await getBBSDetail(this.props.location.state.id, data2);
        if (resp.data) {
            let article = resp.data;
            this.setState({article: article});
        } else {
            message.error("获取文章出错");
        }
    };

    createMarkup = (html) => {
        return {__html: html};
    };

    render() {
        const {showMessage, article} = this.state;

        const ListTitle = ({data: {id, title, type}}) => (
            <div>
                <a onClick={() => this.showDetail(id,type)}>{title}</a>
            </div>

        );

        const ArticleOperation = () => (
            <div>
                <Tooltip placement="right" title={article.likeStatus ? "取消点赞" : "点个赞吧~"} arrowPointAtCenter>
                    <Button type="link" onClick={() => this.doLike(article)}>
                        <IconText text={article.like} likeStatus={article.likeStatus}/>
                    </Button>
                </Tooltip>
            </div>
        );

        const RelateArticle = () => (
                <div>
                    {/*<Divider dashed orientation="left"></Divider>*/}
                    <br/>
                    <br/>
                        <h4>关联文章</h4>
                    <List
                        dataSource={article.relateBbs}
                        renderItem={item =>
                           <List.Item
                                key={item.id}
                            >
                               <ListTitle data={item}/>
                            </List.Item>
                        }
                    />

                </div>
        );

        const IconText = ({text, likeStatus}) => (
            <span>
                    <Icon type="like" theme={likeStatus ? "filled" : "outlined"} style={{marginRight: 8}}/>
                {text}
             </span>
        );

        return (
            <div>
                <Card>
                    <Card.Meta title={<div> {<strong>{article.title}</strong>}
                        <sup> <Icon type={article.type === 30 ? "money-collect" : ""} style={{
                            fontSize: '14px',
                            color: '#ff0000'
                        }}/></sup></div>
                    }
                               description={article.authorName + "   发布于" + moment(article.createTm).format('YYYY-MM-DD HH:mm') +
                               ((article.modifyTm ===article.createTm)? "": "   编辑于" + moment(article.modifyTm).format('YYYY-MM-DD HH:mm') )
                               }/>
                </Card>
                <Card>
                    <div dangerouslySetInnerHTML={this.createMarkup(marked(article.content))} />
                    <br />
                    <br />
                    <br />
                    <Divider dashed/>
                    <ArticleOperation/>
                    <div>{article.relateBbs.length === 0  ? "" : <RelateArticle/>}</div>
                </Card>
                <h2/>
                {showMessage && <Card>
                    <ArticleComment/>
                </Card>
                }
            </div>

        )
    }
}