import React from "react";
import {Icon, Timeline} from "antd";
import moment from 'moment';
import {getBBSLog} from "../../apis/index"


export default class ActiveTree extends React.Component {

    state = {
        loading: false,
        staffName: [],
        bbsLogList: [],
        staffList: [{code: '', name: ''}],
    };


    getBBSLog = async () => {
        let resp = await getBBSLog();
        this.setState({bbsLogList: resp.data});
    };

    viewArticleInfo = (info) => {
        if(info.type != 50){
            this.props.history.push({
                pathname: '/bbs/articleList/articleInfo/' + info.bbsId,
                state: {
                    id: info.bbsId,
                }
            });
        }else {
            this.props.history.push({
                pathname: '/bbs/articleList/weeklyInfo/' + info.bbsId,
                state: {
                    id: info.bbsId,
                }
            });
        }
    };

    activeInfo = (info) => {
        if (info.operation === 1) {
            return <span style={{cursor: "pointer"}}
                         onClick={() => this.viewArticleInfo(info)}>{moment(info.insTm).format('YYYY-MM-DD HH:mm') + " " + info.staffName + "【发表】了：" + info.title}</span>;
        }
        if (info.operation === 2) {
            return <span style={{cursor: "pointer"}}
                         onClick={() => this.viewArticleInfo(info)}>{moment(info.insTm).format('YYYY-MM-DD HH:mm') + " " + info.staffName + "【编辑】了：" + info.title}</span>;
        }
        if (info.operation === 3) {
            return <span style={{cursor: "pointer"}}
                         onClick={() => this.viewArticleInfo(info)}>{moment(info.insTm).format('YYYY-MM-DD HH:mm') + " " + info.staffName + "【点赞】了：" + info.title}</span>;
        }
    };

    icon = (info) => {
        if (info.operation === 1) {
            return <Icon type="file-text" style={{fontSize: '16px'}}/>
        }
        if (info.operation === 2) {
            return <Icon type="edit" style={{fontSize: '16px'}}/>
        }
        if (info.operation === 3) {
            return <Icon type="like" style={{fontSize: '16px'}}/>
        }
    }

    componentWillMount() {
        this.setState({loading: true});
        this.getBBSLog();
    }

    render() {
        const {
            bbsLogList,
        } = this.state;
        return (
            <Timeline mode="alternate">
                {
                    bbsLogList.map((info) => (
                        <Timeline.Item
                            key={info.id}
                            position={info.operation === 1 ? "right" : "left"}
                            dot={this.icon(info)}
                        >
                            {
                                this.activeInfo(info)
                            }
                        </Timeline.Item>
                    ))
                }
            </Timeline>
        )
    }
}