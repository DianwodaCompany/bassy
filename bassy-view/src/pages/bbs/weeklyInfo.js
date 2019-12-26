import React from "react";
import {Card, Collapse, Divider, Icon, Button, Tooltip} from "antd";
import marked from "marked";
import { likeBBS, getWeeklyDetail} from "../../apis";
import {message} from "antd/lib/index";
import moment from "moment";



export default class WeeklyInfo extends React.Component {

    state = {
        article: {
            id: null,
            title: "",
            like: 0,
            likeStatus: false,
            bbsList:[{
                noteType:"",
                bbsList:[],
            }],
        },
    };

    customPanelStyle = {
        background: '#f7f7f7',
        borderRadius: 4,
        marginBottom: 12,
        border: 0,
        overflow: 'hidden',
    };

    customPanelStyle0 = {
        background: '#f7f7f7',
        borderRadius: 4,
        marginBottom: 12,
        border: 0,
        overflow: 'hidden',
    };

    getBBSInfo = async (id) => {
        const data = {staffCode: window.__data.staffCode};
        let resp = await getWeeklyDetail(id, data);
        if (resp.data) {
            this.setState({article: resp.data});
        } else {
            message.error("获取周刊出错");
        }
    };

    componentWillMount = async () => {
        this.getBBSInfo(this.props.location.state.id);
    };

    doLike = async (item) => {
        const data = {id: item.id, likeStatus: item.likeStatus, staffCode: window.__data.staffCode};
        await likeBBS(data);
        const data2 = {staffCode: window.__data.staffCode};
        let resp = await getWeeklyDetail(this.props.location.state.id, data2);
        if (resp.data) {
            let article = resp.data;
            this.setState({article: article});
        } else {
            message.error("获取周刊出错");
        }
    };

    createMarkup = (html) => {
        return {__html: html};
    };

    render() {
        const {article} = this.state;

        const ArticleOperation = () => (
            <div>
                <Tooltip placement="right" title={article.likeStatus ? "取消点赞" : "点个赞吧~"} arrowPointAtCenter>
                    <Button type="link" onClick={() => this.doLike(article)}>
                        <IconText text={article.like} likeStatus={article.likeStatus}/>
                    </Button>
                </Tooltip>
            </div>
        );

        const panel =(list) => {
            const obj=[];
            for(let i=0;i<list.length;i++){
                    obj.push(<Collapse.Panel header={i===0 ? "特别推荐："+ list[i].title + "【hot~】" : "  "+list[i].title} key={list[i].id}
                                             style={i===0 ?this.customPanelStyle0:this.customPanelStyle}>
                        {showDetail(list[i])}</Collapse.Panel>);
            }
            return obj;
        };


        const showDetail = (item) => (
            <div>
                <span style={this.customPanelStyle}>{moment(item.createTm).format('YYYY-MM-DD HH:mm ') + "By" + item.authorName }
                </span>
                <Divider dashed />
                    <div dangerouslySetInnerHTML={this.createMarkup(marked(item.content))} />
                    <br />
                    <br />
            </div>
        );

        const BBSNote = () => {
        const obj=[];
            for(let i in article.bbsList){
                obj.push(
                        <Card type="inner"
                              title={article.bbsList[i].noteType}>
                        <Collapse
                            accordion
                            bordered={false}
                            defaultActiveKey={['1']}
                            expandIconPosition={"right"}
                            expandIcon={({ isActive }) => <Icon type="caret-right" rotate={isActive ? 90 : 0} />}
                        >{panel(article.bbsList[i].bbsList)}
                        </Collapse>
                    </Card>
                )}
                return obj;
        };

        const IconText = ({text, likeStatus}) => (
            <span>
                    <Icon type="like" theme={likeStatus ? "filled" : "outlined"} style={{marginRight: 8}}/>
                {text}
             </span>
        );

        return (
            <div>
                <Card>
                    <Card.Meta title={<div> {<strong>{article.title}</strong>}</div>
                    }
                               description={article.authorName + "   发布于" + moment(article.createTm).format('YYYY-MM-DD HH:mm') +
                               ((article.modifyTm ===article.createTm)? "": "   编辑于" + moment(article.modifyTm).format('YYYY-MM-DD HH:mm') )
                               }/>
                </Card>
                <BBSNote/>
                <br/>
                <br/>
                <br/>
                <Divider dashed/>
                <ArticleOperation/>

            </div>

        )
    }
}