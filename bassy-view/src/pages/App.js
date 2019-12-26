import React from 'react'
import {Dropdown, Icon, Layout, Menu} from 'antd'
import {BrowserRouter, Link, Route, Switch} from 'react-router-dom'
import CSSModules from 'react-css-modules'
import {connect} from 'react-redux'

import '../utils/data'
import {routes} from './router'
import {BreadcrumbView} from './Common/views/breadcrumb'
import styles from './App.less'
import {ASYNC_GET_ALL_DICTIONARY, ASYNC_GET_ALL_TASK, ASYNC_GET_PROGRAM_TYPE} from '../actionTypes';
import {
    getAllProcessTasks,
    getAllProjects,
    getProgramType,
    getStaffInfo,
    getAuthenticatedRescodes,
    getTaskAbnormalReasonTeamList,
    getTaskAbnormalReasonTypeList,
    getBBSPosts,
} from "../apis";
import {
	addAllProcessTasks,
	addAllProjects,
	addProjectBigTypes,
	addStaffInfo,
	addAuthResourceCodeList,
	AddAbnormalReasonTeam,
	AddAbnormalReasonType
} from "./Common/actions";
import {haveAdminAuth, haveCommonAuth, haveGuestAuth, haveLeaderAuth} from "../utils";
import {MyCalendarTask} from "./MyTask";
import {DashBoard} from "./DashBoard";
import ProgramCaseIndex from "./case/views/programCase/programCaseIndex";
import {message} from "antd/lib/index";

const {Header, Content, Footer, Sider} = Layout;
const SubMenu = Menu.SubMenu;

@CSSModules(styles)
@connect(state => ({
	staffInfo: state.common.staffInfo,
	authResourceCodeList: state.common.authResourceCodeList,
	programTypes: state.programTypes,
	allDict: state.allDict
}))
export default class App extends React.Component {

	state = {
		collapsed: false,
		articleInfo: []
	};
	onCollapse = (collapsed) => {
		this.setState({collapsed});
	};
	logout = () => {
		location.href = '/auth/logout'
	};

	getProjectBigTypes = async () => {
		let result = await getProgramType();
		this.props.dispatch(addProjectBigTypes(result.data));
	};

	getAllProcessTasks = async () => {
		let result = await getAllProcessTasks();
		this.props.dispatch(addAllProcessTasks(result.data));
	};

	getAllProjects = async () => {
		let result = await getAllProjects();
		this.props.dispatch(addAllProjects(result.data));
	};

	getStaffInfo = async () => {
		let code = window.__data.staffCode;
		let result = await getStaffInfo(code);
		this.props.dispatch(addStaffInfo(result.data));
		let result1 = await getAuthenticatedRescodes(code.id);
		this.props.dispatch(addAuthResourceCodeList(result1.data));
	};

	getTaskAbnormalReasonTeamList = async () => {
		let result = await getTaskAbnormalReasonTeamList();
		this.props.dispatch(AddAbnormalReasonTeam(result.data));
	};

	getTaskAbnormalReasonTypeList = async () => {
		let result = await getTaskAbnormalReasonTypeList();
		this.props.dispatch(AddAbnormalReasonType(result.data));
	};

	getArticleList = async () => {
        const data = {pageNum: 1, pageSize: 5, sort: "newArticle"};
        let resp = await getBBSPosts(data);
        if (resp.data) {
            const {list} = resp.data;
            this.setState({
                articleInfo: list,
            });
        } else {
            message.error("查询出错!");
        }
    };

	componentWillMount() {
		this.getProjectBigTypes();
		this.getAllProcessTasks();
		this.getAllProjects();
		this.getStaffInfo();
		this.getTaskAbnormalReasonTeamList();
		this.getTaskAbnormalReasonTypeList();
        this.getArticleList();
		this.props.dispatch({
			type: ASYNC_GET_PROGRAM_TYPE
		});
		this.props.dispatch({
			type: ASYNC_GET_ALL_TASK,
			payload: {
				pageNum: null,
				pageSize: null,
			}
		});
		this.props.dispatch({
			type: ASYNC_GET_ALL_DICTIONARY
		});

		const projectSearchData = {
			name: "",
			status: "",
			owner: [],
		};
		localStorage.setItem("projectSearchData", JSON.stringify(projectSearchData))

	}

  render() {
    const {authResourceCodeList, staffInfo} = this.props;
    return (
      <BrowserRouter basename="#">
        <Layout style={{minHeight: '100vh'}}>
          <Sider
            breakpoint="lg"
            collapsedWidth="0"
            collapsible
            collapsed={this.state.collapsed}
            onCollapse={this.onCollapse}
            style={{overflow: 'auto', height: '100vh', position: 'fixed', left: 0}}
            className={styles.sider}
          >
            <div className={styles.logo} key="logo">
              <Link to="/">
                {/*<img src={logo} alt="logo" />*/}
                <h1>D Quality</h1>
              </Link>
            </div>
            <Menu theme="dark" mode="inline">
                {
                    authResourceCodeList &&
                    <Menu.Item key="sub0">
                        <Icon type="dashboard"/>
                        <span>仪表盘</span>
                        <Link to="/dashboard"/>
                    </Menu.Item>
                }
                {
                    authResourceCodeList &&
                    < Menu.Item key="sub1">
                        <Icon type="user"/>
                        <span>我的任务</span>
                        <Link to="/myTask"/>
                    </Menu.Item>
                }

              {
                  authResourceCodeList && !haveGuestAuth(authResourceCodeList) &&
                <Menu.Item key="sub2">
                  <Icon type="schedule"/>
                  <span>项目管理</span>
                  <Link to="/project"/>
                </Menu.Item>
              }
              {
                  authResourceCodeList && !haveGuestAuth(authResourceCodeList) &&
                <Menu.Item key="sub3">
                  <Icon type="pushpin"/>
                  <span>任务管理</span>
                  <Link to="/task"/>
                </Menu.Item>
              }
                <SubMenu key="sub9" title={<span><Icon type="flag" /><span>测试用例</span></span>}>
                    <Menu.Item key="sub9_0">
                        <Link to='/case/baseCase/list'>基础用例</Link>
                    </Menu.Item>
                    <Menu.Item key="sub9_1">
                        <Link to='/case/programCase'>项目用例</Link>
                    </Menu.Item>
                    {
                        authResourceCodeList &&
                        <Menu.Item key="sub9_2">
                            <Link to='/case/productModule/manage'>产品模块</Link>
                        </Menu.Item>
                    }

                    {
                        authResourceCodeList &&
                        <Menu.Item key="sub9_3">
                            <Link to='/case/label/manage'>标签库</Link>
                        </Menu.Item>
                    }

                </SubMenu>
                {
                    authResourceCodeList &&
                    <SubMenu key="sub20" title={<span><Icon type="read"/><span>智库</span></span>}>
                        <Menu.Item key="sub20_0">
                            <Link to='/bbs/articleList'>期刊</Link>
                        </Menu.Item>
                        <Menu.Item key="sub20_1">
                            <Link to='/bbs/myArticle'>我的贡献</Link>
                        </Menu.Item>
                    </SubMenu>

                }

              <SubMenu key="sub4" title={<span><Icon type="coffee"/><span>自动化测试</span></span>}>
                {
                    authResourceCodeList && !haveGuestAuth(authResourceCodeList) &&
                    <Menu.Item key="4_2">
                        <Link to="/testSuite">测试套件</Link>
                    </Menu.Item>
                }
              </SubMenu>
                {
                    authResourceCodeList &&
                    <SubMenu key="sub5" title={<span><Icon type="line-chart"/><span>资源情况</span></span>}>
                        {
                            !haveCommonAuth(authResourceCodeList) &&
                            <Menu.Item key="5_1">
                                <Link to='/resource/heapmap'>热力图</Link>
                            </Menu.Item>
                        }
                        <Menu.Item key="5_2">
                            <Link to='/resource/workDailyReport'>日报</Link>
                        </Menu.Item>
                    </SubMenu>
                }
              {
                  authResourceCodeList && !haveCommonAuth(authResourceCodeList) &&
                <SubMenu key="sub10" title={<span><Icon type="pie-chart"/><span>统计分析</span></span>}>
                  <Menu.Item key="10_1">
                    <Link to='/analysis/departAbnormalCount'>质量过程统计</Link>
                  </Menu.Item>
                    <Menu.Item key="10_2">
                        <Link to='/analysis/workLoadCount'>工作量统计</Link>
                    </Menu.Item>
                </SubMenu>
              }
              {
                (haveLeaderAuth(authResourceCodeList) || haveAdminAuth(authResourceCodeList)) && authResourceCodeList &&
                <SubMenu key="sub6" title={<span><Icon type="setting"/><span>配置管理</span></span>}>
                  <Menu.Item key="6_1">
                    <Link to='/projectTemplate'>项目模版维护</Link>
                  </Menu.Item>
                  <Menu.Item key="6_2">
                    <Link to='/processMode'>项目流程模版维护</Link>
                  </Menu.Item>
                  {
                    haveAdminAuth(authResourceCodeList) &&
                    <Menu.Item key="6_3">
                      <Link to='/taskAbnormalMaintain'>任务异常维护</Link>
                    </Menu.Item>
                  }
                </SubMenu>
              }
              <Menu.Item key="sub7">
                <Icon type="pushpin"/>
                <span>资产管理</span>
                <Link to="/asset/list"/>
              </Menu.Item>
              <SubMenu key="sub8" title={<span><Icon type="tool"/><span>测试工具</span></span>}>
                <Menu.Item key="sub8_0">
                  <span>自助下单工具</span>
                  <a href="http://website-test-qa3.dwbops.com" target="_blank"
                     rel="noopener noreferrer"/>
                </Menu.Item>
                <Menu.Item key="sub8_1">
                  <span>调度自助工具</span>
                  <a href="http://brace-tool-qa1.dwbops.com/" target="_blank"
                     rel="noopener noreferrer"/>
                </Menu.Item>
                <Menu.Item key="sub8_2">
                  <span>派单异常排查</span>
                  <Link to="/tools/orderDispatch"/>
                </Menu.Item>
                <Menu.Item key="sub8_5">
                  <span>骑手测试工具</span>
					<a href="http://check-order-react-qa1.dwbops.com/" target="_blank"
					   rel="noopener noreferrer"/>
                </Menu.Item>
                <Menu.Item key="sub8_3">
                  <span>mock测试工具</span>
                  <a href="http://116.62.172.151:12810/login" target="_blank"
                     rel="noopener noreferrer"/>
                </Menu.Item>
                <Menu.Item key="sub8_4">
                  <span>综合测试工具</span>
                  <a href="http://selftest-qa3.dwbops.com/selftest/index2/#" target="_blank"
                     rel="noopener noreferrer"/>
                </Menu.Item>
              </SubMenu>
                  <Menu.Item key="sub21">
                <Icon type="link"/>
                <span>常用链接</span>
                <Link to="/usefulLink"/>
              </Menu.Item>
            </Menu>
          </Sider>
          <Layout style={{marginLeft: 200}}>
            <Header className={styles.header}>
				{/*文章轮播*/}
				<div className={styles.wrap}>
					<div className={styles.content}>
						{
							this.state.articleInfo.map((info) => (
								<p><Link to={{pathname:( info.type != 50  ? "/bbs/articleList/articleInfo/" :"/bbs/articleList/weeklyInfo/") + info.id, state:{id: info.id}}}>{info.authorName + "：" + info.title}</Link></p>
							))
						}
					</div>
				</div>
              <Dropdown
                trigger={['click', 'hover']}
                style={{display: 'inline-block'}}
                overlay={
                  <Menu onClick={this.logout}>
                    <Menu.Item key="logout">登出</Menu.Item>
                  </Menu>}
              >
                <div>
                  {staffInfo.name}
                  <Icon type="user"/>
                </div>
              </Dropdown>
            </Header>
            <Content style={{margin: '0px 16px 0px 16px'}}>
              <BreadcrumbView/>
              <Switch>
                {<Route
                  key={0}
                  breadcrumbName="Home"
                  path="/"
                  exact
                  component={authResourceCodeList ? haveCommonAuth(authResourceCodeList) ? props =>
                    <MyCalendarTask {...props} /> : props => <DashBoard {...props} /> : props => <ProgramCaseIndex {...props}/>}
                />}
                {routes.map((route, index) => (
                  <Route
                    key={index + 1}
                    breadcrumbName={route.name}
                    path={route.path}
                    exact
                    component={route.main}
                  />
                ))}
              </Switch>
            </Content>
            <Footer style={{textAlign: 'center'}}>
              D Quality ©2018 Created by DWD
            </Footer>
          </Layout>
        </Layout>
      </BrowserRouter>
    )
  }
}
