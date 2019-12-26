import React from "react";
import {ProjectTemplate, Normal, TaskAbnormalMaintain} from "./ConfigManagement";
import ProcessMode from "./ProcessTemplate";
import Process from "./ProcessTemplate/process";
import ProjectManagement from "./ProjectManagement/views/index";
import Project from "./ProjectManagement/views/project";
import ProjectDocument from "./ProjectManagement/views/projectDocument";
import ProjectTestPlan from "./ProjectManagement/views/projectTestPlan";
import ProjectReport from "./ProjectManagement/views/projectReport";
import TaskManagement from "./TaskManagement/index";
import ProgramTaskDetail from "./TaskManagement/ProgramTaskDetail";
import TestSuite from "./AutoTest/views/index"
import {MyCalendarTask} from "./MyTask"
import UpdateTaskRateDetail from "./Common/views/task/updateTaskRateDetail"
import TaskUpdateHistory from "./Common/views/task/taskUpdateHistory"
import ResourceHeapMap from "./Resource/ResourceHeapMap"
import {UsefulLink} from "./UsefulLink"
import {DashBoard} from "./DashBoard"
import WorkDailyReport from "./Resource/workDailyReport"
import Asset from "./AssetManagement/asset"
import DepartAbnormalCount from "./analysis/DepartAbnormalCount"
import OrderDispatch from "./testTool/views/orderDispatch"
import WorkLoadCount from "./analysis/WorkLoadCount"
import BBS from "./bbs/index"
import MyArticle from "./bbs/my_article"
import ArticleEditor from "./bbs/article_editor"
import ArticleEditorStudy from "./bbs/article_editor_study"
import ArticleEditorNote from "./bbs/article_editor_note"
import ArticleInfo from "./bbs/articleInfo"
import WeeklyInfo from "./bbs/weeklyInfo"
import BaseCase from "./case/views/baseCase"
import CaseDetail from "./case/views/caseDetail"
import CaseMindView from "./case/views/caseMindView"
import ProductModuleManage from "./case/views/productModuleManage/index"
import ProgramCaseIndex from "./case/views/programCase/programCaseIndex"
// import ProgramCaseDetail from "./case/views/programCaseDetail"
import ProgramCaseDetail from "./case/views/programCase/index"

import LabelManage from "./case/views/labelManage"

export const routes = [
	{
        path: "/dashboard",
        main: props => <DashBoard {...props} />,
        name: "仪表盘"
    },
    {
        path: "/projectTemplate",
        main: props => <ProjectTemplate {...props} />,
        name: "项目模板"
    },
    {
        path: "/projectTemplate/detail/:id",
        main: props => <Normal {...props}/>,
        name: "项目模板详情"
    },
    {
        path: "/projectTemplate/edit/:id",
        main: props => <Normal {...props}/>,
        name: "编辑项目模板"
    },
    {
        path: "/projectTemplate/add",
        main: props => <Normal {...props}/>,
        name: "新增项目模板"
    },
    {
        path: "/processMode",
        main: props => <ProcessMode {...props} />,
        name: "流程模板"
    },
    {
        path: "/processMode/add",
        main: props => <Process {...props} add/>,
        name: "新增流程模板"
    },
    {
        path: "/processMode/edit/:id",
        main: props => <Process {...props} edit/>,
        name: "编辑流程模板"
    },
    {
        path: "/processMode/detail/:id",
        main: props => <Process {...props} view/>,
        name: "流程模板详情"
    },
    {
        path: "/taskAbnormalMaintain",
        main: props => <TaskAbnormalMaintain {...props} />,
        name: "任务异常维护"
    },
    {
        path: "/project",
        main: props => <ProjectManagement {...props} />,
        name: "项目管理"
    },
    {
        path: "/project/add",
        main: props => <Project {...props} add/>,
        name: "新增项目"
    },
    {
        path: "/project/detail/:id",
        main: props => <Project {...props} view/>,
        name: "项目详情"
    },
    {
        path: "/project/testPlan/add",
        main: props => <ProjectTestPlan {...props} add/>,
        name: "新增项目计划"
    },
    {
        path: "/project/testPlan/edit",
        main: props => <ProjectTestPlan {...props} edit/>,
        name: "编辑项目计划"
    },
    {
        path: "/task",
        main: props => <TaskManagement {...props} />,
        name: "任务管理"
    },
    {
        path: "/task/updateTask",
        main: props => <UpdateTaskRateDetail {...props} />,
        name: "更新任务"
    },
    {
        path: "/task/updateTaskHistory",
        main: props => <TaskUpdateHistory {...props} />,
        name: "任务更新历史"
    },
    {
        path: "/programTaskDetail",
        main: props => <ProgramTaskDetail {...props} />,
        name: "任务详情"
    },
    {
        path: "/testSuite",
        main: props => <TestSuite {...props} />,
        name: "执行自动化"
    },
    {
        path: "/toReport",
        main: props => <Report {...props} />,
        name: "自动化报告"
    },
    {
        path: "/myTask",
        main: props => <MyCalendarTask {...props} />,
        name: "我的任务"
    },
    {
        path: "/myTask/updateTask",
        main: props => <UpdateTaskRateDetail {...props} />,
        name: "更新任务"
    },
    {
        path: "/myTask/updateTaskHistory",
        main: props => <TaskUpdateHistory {...props} />,
        name: "任务更新历史"
    },
    {
        path: "/project/document",
        main: props => <ProjectDocument {...props} />,
        name: "项目文档"
    },
    {
        path: "/project/document/report",
        main: props => <ProjectReport {...props} />,
        name: "项目日报"
    },
    {
        path: "/resource/heapmap",
        main: props => <ResourceHeapMap {...props} />,
        name: "资源热力图"
    },
    {
        path: "/resource/workDailyReport",
        main: props => <WorkDailyReport {...props} />,
        name: "日报"
    },
    {
        path: "/asset/list",
        main: props => <Asset {...props} />,
        name: "资产管理"
    },
    {
        path: "/usefulLink",
        main: props => <UsefulLink {...props} />,
        name: "常用链接"
    },
    {
        path: "/analysis/departAbnormalCount",
        main: props => <DepartAbnormalCount {...props} />,
        name: "质量过程统计"
    },
    {
        path: "/analysis/workLoadCount",
        main: props => <WorkLoadCount {...props} />,
        name: "工作量统计"
    },
    {
        path: "/tools/orderDispatch",
        main: props => <OrderDispatch {...props} />,
        name: "派单异常"
    },
    {
        path: "/bbs/articleList",
        main: props => <BBS {...props} />,
        name: "期刊"
    },
    {
        path: "/bbs/myArticle",
        main: props => <MyArticle {...props} />,
        name: "我的贡献"
    },
    {
        path: "/bbs/myArticle/add",
        main: props => <ArticleEditor {...props} add/>,
        name: "新增文章"
    },
    {
        path: "/bbs/myArticle/addSt",
        main: props => <ArticleEditorStudy {...props} add/>,
        name: "新增学习打卡"
    },
    {
        path: "/bbs/myArticle/addNote",
        main: props => <ArticleEditorNote {...props} add/>,
        name: "新增记一笔"
    },
    {
        path: "/bbs/myArticle/edit",
        main: props => <ArticleEditor {...props} edit/>,
        name: "编辑文章"
    },
    {
        path: "/bbs/myArticle/editSt",
        main: props => <ArticleEditorStudy {...props} edit/>,
        name: "编辑学习打卡"
    },
    {
        path: "/bbs/myArticle/editNote",
        main: props => <ArticleEditorNote {...props} edit/>,
        name: "编辑记一笔"
    },
    {
        path: "/bbs/articleList/weeklyInfo/:id",
        main: props => <WeeklyInfo {...props}/>,
        name: "查看测试周刊"
    },
    {
        path: "/case/baseCase/list",
        main: props => <BaseCase {...props}/>,
        name: "基础用例"
    },{
        path: "/case/baseCase/list/caseDetail",
        main: props => <CaseDetail {...props}/>,
        name: "用例详情"
    },{
        path: "/case/programCase/:requireId/caseDetail",
        main: props => <CaseDetail {...props}/>,
        name: "用例详情"
    },{
        path: "/case/baseCase/list/caseMindView",
        main: props => <CaseMindView {...props}/>,
        name: "用例CMind详情"
    },{
        path: "/case/programCase/:requireId/caseMindView",
        main: props => <CaseMindView {...props}/>,
        name: "用例CMind详情"
    },
    {
        path: "/bbs/articleList/articleInfo/:id",
        main: props => <ArticleInfo {...props}/>,
        name: "查看文章"
    },
    {
        path: "/case/productModule/manage",
        main: props => <ProductModuleManage {...props}/>,
        name: "产品及模块维护"
    },
    {
        path: "/case/programCase",
        main: props => <ProgramCaseIndex {...props}/>,
        name: "项目用例"
    },
    {
        path: "/case/programCase/:requireId",
        main: props => <ProgramCaseDetail {...props}/>,
        name: "需求用例"
    },
    {
        path: "/case/label/manage",
        main: props => <LabelManage {...props}/>,
        name: "标签管理"
    },
];
