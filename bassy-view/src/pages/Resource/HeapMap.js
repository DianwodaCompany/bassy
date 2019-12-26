import React from "react";
import {Axis, Chart, Geom, Label, Tooltip,} from 'bizcharts';

export default class HeatMap extends React.Component {

    state = {
        data: []
    };

    render() {
        const {heapData, departStaff, days} = this.props;
        const cols = {
            name: {
                type: 'cat',
                values: departStaff,
            },
            date: {
                type: 'cat',
                values: days,
            },
        };

        const yLabel = {
            offset: 25,
            textStyle: {
                textAlign: 'center', // 文本对齐方向，可取值为： start center end
                fill: '#404040', // 文本的颜色
                fontSize: '15', // 文本大小
                fontWeight: 'bold', // 文本粗细
                rotate: 30,
                textBaseline: 'top' // 文本基准线，可取 top middle bottom，默认为middle
            }
        };

        const xLabel = {
            textStyle: {
                textAlign: 'center', // 文本对齐方向，可取值为： start center end
                fill: '#404040', // 文本的颜色
                fontSize: '14', // 文本大小
                fontWeight: 'bold', // 文本粗细
                // rotate: 30,
                // textBaseline: 'top' // 文本基准线，可取 top middle bottom，默认为middle
            }
        };

        return (
            <div>
                <Chart
                    onTooltipChange={(ev)=> {
                        var items = ev.items;
                        var origin = items[0];
                        for(let hd of heapData){
                            if(hd.heapWorkRelateTaskDTO && hd.heapWorkRelateTaskDTO.name === origin.point._origin.name && hd.heapWorkRelateTaskDTO.date === origin.point._origin.date){
                                for(let heapWorkTaskDTO of hd.heapWorkRelateTaskDTO.heapWorkTaskDTOList){
                                    var name;
                                    if(heapWorkTaskDTO.storyId && heapWorkTaskDTO.taskStatus === "init"){
                                        name = "[需求]("+heapWorkTaskDTO.storyId+")"+heapWorkTaskDTO.storyTitle+" "+"("+heapWorkTaskDTO.taskId+")"+heapWorkTaskDTO.taskName+"("+heapWorkTaskDTO.taskStatusName+")";
                                    }else if(heapWorkTaskDTO.storyId && heapWorkTaskDTO.taskStatus !== "init"){
                                        name = "[需求]("+heapWorkTaskDTO.storyId+")"+heapWorkTaskDTO.storyTitle+" "+"("+heapWorkTaskDTO.taskId+")"+heapWorkTaskDTO.taskName+"("+heapWorkTaskDTO.taskStatusName+")("+heapWorkTaskDTO.percent+"%)";
                                    }else if(heapWorkTaskDTO.projectId && heapWorkTaskDTO.taskStatus === "init"){
                                        name = "[项目]("+heapWorkTaskDTO.projectId+")"+heapWorkTaskDTO.projectName+" "+"("+heapWorkTaskDTO.taskId+")"+heapWorkTaskDTO.taskName+"("+heapWorkTaskDTO.taskStatusName+")";
                                    }else if(heapWorkTaskDTO.projectId && heapWorkTaskDTO.taskStatus !== "init"){
                                        name = "[项目]("+heapWorkTaskDTO.projectId+")"+heapWorkTaskDTO.projectName+" "+"("+heapWorkTaskDTO.taskId+")"+heapWorkTaskDTO.taskName+"("+heapWorkTaskDTO.taskStatusName+")("+heapWorkTaskDTO.percent+"%)";
                                    }else if(heapWorkTaskDTO.taskStatus === "init"){
                                        name = "("+heapWorkTaskDTO.taskId+")"+heapWorkTaskDTO.taskName+"("+heapWorkTaskDTO.taskStatusName+")";
                                    }
                                    else {
                                        name = "("+heapWorkTaskDTO.taskId+")"+heapWorkTaskDTO.taskName+"("+heapWorkTaskDTO.taskStatusName+")("+heapWorkTaskDTO.percent+"%)";
                                    }
                                    items.push({
                                        name: name,
                                        title: origin.title,
                                        marker: true,
                                        value: heapWorkTaskDTO.hour
                                    });
                                }
                            }
                        }

                    }}
                    height={window.innerHeight}
                    width={600}
                    data={heapData}
                    scale={cols}
                    padding={[20, 80, 120, 110]}
                    forceFit
                >
                    <Axis
                        name="name"
                        grid={{
                            align: 'center',
                            lineStyle: {
                                lineWidth: 1,
                                lineDash: null,
                                stroke: '#f0f0f0',
                            },
                            showFirstLine: true,
                        }}
                        label={yLabel}
                    />
                    <Axis
                        name="date"
                        grid={{
                            align: 'center',
                            lineStyle: {
                                lineWidth: 1,
                                lineDash: null,
                                stroke: '#f0f0f0',
                            },
                        }}
                        label={xLabel}
                    />
                    <Tooltip/>
                    <Geom
                        type="polygon"
                        position="date*name"
                        color={['hour', (hour) => {
                            if (hour !== null) {
                                if (hour >= 0 && hour <= 4)
                                    return '#bbf6a4';
                                else if (hour > 4 && hour <= 8)
                                    return '#a4b6dc';
                                else if (hour > 8 && hour <= 12)
                                    return '#e4a593';
                                else if (hour > 12)
                                    return '#e47768';
                            }
                            else {
                                return '#FDF8EF';
                            }
                        }]}
                        style={{
                            stroke: '#fff',
                            lineWidth: 3,
                        }}
                    >
                        <Label
                            content="hour"
                            offset={-2}
                            textStyle={{
                                fill: '#000000'
                            }}
                        />
                    </Geom>
                </Chart>
            </div>
        );
    }
}