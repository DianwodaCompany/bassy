import {Select, Spin} from 'antd';
import {getZentaoStories} from "../../apis";
import React from "react";

const Option = Select.Option;

export default class FetchZtRequire extends React.Component {
  state = {
    fetchData: [],
    fetching: false,
  };

  fetchDemands = async (keyword) => {
    this.setState({fetchData: [], fetching: true});
    let resp = await getZentaoStories(keyword);
    const data = resp.data.map(d => (
      {
        text: `[${d.ztStoryId}] ${d.ztStoryName} (项目 ${d.ztProjectName == null ? "未关联项目" : d.ztProjectName} )`,
        value: `${d.ztStoryId},${d.ztStoryName},${d.ztProjectId},${d.ztProjectName}`,
      }));
    if (data.length === 0) {
      this.setState({
        fetchData: [{text: "禅道无此需求", value: 0}], fetching: false
      });
    } else {
      this.setState({fetchData: data, fetching: false});
    }
  };

  render() {
    const {disabled, handleSelect} = this.props;
    const {fetchData, fetching} = this.state;
    return (
      <Select
        showSearch={true}
        placeholder="输入关键字搜索禅道需求"
        notFoundContent={fetching ? <Spin size="small"/> : null}
        filterOption={false}
        showArrow={false}
        onSearch={this.fetchDemands}
        onSelect={value => handleSelect(value)}
        style={{width: '100%'}}
        disabled={disabled}
      >
        {fetchData.map(fetch =>
          <Option key={fetch.value}>{fetch.text}</Option>
        )}
      </Select>
      )

  }
}