import React from 'react'
import {Switch,Button,Affix} from "antd";
import {connect} from "react-redux";
import BigCalendar from 'react-big-calendar'
import TaskEventPopover from './taskEventPopover'
import moment from "moment";
import './react-big-calendar.css';
import styles from "./index.less";
import {getMyCalendarTasks} from "../../../apis/index";
import * as ReactDOM from "react-dom";
import CustomTask from "../../Common/views/task/customTask";
import {ButtonSet} from "../../case/rmind/components/Popup/common/styledComponents";

@connect(state => ({
    staffInfo: state.common.staffInfo
}))
export default class MyCalendarTask extends React.Component {

    state = {
        checked: true,
        events: [],
        culture: 'zh',
        addVisible: false,
        operateMode: '',
        customTaskInfo: {
            taskName: "",
            expectHour: 1,
        },
        id: null,
    };

    getMyCalendarTasks = async () => {
        let ret = await getMyCalendarTasks(this.props.staffInfo.code);
        let eventList = [];
        for (let event of ret.data) {
            if(this.state.checked && event.status === "end"){
                continue;
            }
            let newEvent = {};
            newEvent.start = moment(event.start).toDate();
            newEvent.end = moment(event.end).toDate();
            newEvent.id = event.id;
            newEvent.title = event.title;
            newEvent.status = event.status;
            newEvent.programTask = event.programTask;
            newEvent.programName = event.programName;
            newEvent.refreshCalendarTask = this.getMyCalendarTasks;
            newEvent.history = this.props.history;
            newEvent.editCustomTask = this.editCustomTask;
            eventList.push(newEvent);
        }
        this.setState({
            events: eventList
        })
    };

    addCustomTask = (customTaskInfo) => {
        this.setState({
            addVisible: true,
            operateMode: "add",
            customTaskInfo: customTaskInfo
        });
    };

    editCustomTask = (customTaskInfo) => {
        this.setState({addVisible: true, operateMode: 'edit', customTaskInfo: customTaskInfo})
    };

    cancelAdd = () => {
        this.setState({
            addVisible: false,
            customTaskInfo: {
                taskName: "",
                expectHour: 1,
            }
        });
    };

    onChange = (checked) => {
        this.state.checked = checked;
        this.getMyCalendarTasks();
    };

    toAddNote = () => {
        this.props.history.push({
            pathname: '/bbs/myArticle/addNote',
            state: {
            id: this.state.id}
        })

    }

    componentWillMount() {
        this.getMyCalendarTasks();
    }

    componentDidMount() {
        setTimeout(() => {
            let domNodes = ReactDOM.findDOMNode(this).getElementsByClassName('rbc-today');
            if (domNodes.length > 0) {
                var domRect = domNodes[0].getBoundingClientRect();
                var height = window.innerHeight
                    || document.documentElement.clientHeight
                    || document.body.clientHeight;
                if (domRect.bottom > height) {
                    window.scrollTo({
                        top: domRect.top - domRect.height,
                        behavior: "smooth"
                    })
                }
            }
        }, 1000)
    }

    render() {
        const {events, culture, addVisible, customTaskInfo, operateMode} = this.state;
        let formats = {
            weekdayFormat: 'ddd',
            monthHeaderFormat: 'MM YYYY',
        };
        let views = {
            MONTH: 'month',
            WEEK: 'week',
            DAY: 'day',
        };
        let allViews = Object.keys(views).map(k => views[k]);
        const localizer = BigCalendar.momentLocalizer(moment);
        return (
            <div>
                <div className={styles.switch}>
                    <span className={styles.span}>隐藏完成任务</span>
                    <Switch defaultChecked onChange={this.onChange} />
                </div>
                <div className={styles.calendarTask}>
                    <BigCalendar
                        selectable={true}
                        onSelectSlot={(slotInfo) => {
                            console.log("onSelectSlot:" + JSON.stringify(slotInfo));
                            if (slotInfo.action === "doubleClick") {
                                let customTaskInfo = {
                                    taskName: "",
                                    expectHour: 1,
                                    startTm: slotInfo.start,
                                    endTm: slotInfo.end
                                };
                                this.addCustomTask(customTaskInfo)
                            }
                        }}
                        formats={formats}
                        events={events}
                        views={allViews}
                        step={60}
                        showMultiDayTimes
                        defaultDate={new Date()}
                        localizer={localizer}
                        culture={culture}
                        tooltipAccessor={null}
                        eventPropGetter={event => {
                            if (event.status === "close" || event.status === "end") {
                                return {
                                    style: {
                                        color: "rgba(0, 0, 0, 1)",
                                        backgroundColor: "rgba(122, 122, 122, 0.2)"
                                    }
                                };
                            } else if (event.status === "init" && moment().isAfter(event.start)) {
                                return {
                                    style: {
                                        color: "rgba(0, 0, 0, 1)",
                                        backgroundColor: "#e4a593"
                                    }
                                };
                            } else if ("init,pause,processing".includes(event.status) && moment().isAfter(event.end)) {
                                return {
                                    style: {
                                        color: "rgba(0, 0, 0, 1)",
                                        backgroundColor: "#e4a593"
                                    }
                                };
                            } else if (event.status === "processing") {
                                return {
                                    style: {
                                        color: "rgba(0, 0, 0, 1)",
                                        backgroundColor: "#a4b6dc"
                                    }
                                };
                            }
                            else if (event.status === "init") {
                                return {
                                    style: {
                                        color: "rgba(0, 0, 0, 1)",
                                        backgroundColor: "#AEDCB1"
                                    }
                                };
                            }
                        }}
                        components={{
                            eventWrapper: TaskEventPopover
                        }}
                    />
                </div>
                <Affix style={{ position: 'absolute', top:475, left:1723 }}>
                    <Button type="primary" onClick={() => this.toAddNote()}>快去记一笔吧！</Button>
                </Affix>
                <div>
                    {
                        addVisible &&
                        <CustomTask
                            operateMode={operateMode}
                            customTaskInfo={customTaskInfo}
                            toMe={true}
                            callback={() => this.getMyCalendarTasks()}
                            onCancel={this.cancelAdd}
                        />
                    }
                </div>
            </div>
        )
    }
}