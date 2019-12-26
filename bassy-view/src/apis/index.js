import {
    http, httpUpload, httpParams
} from './utils'

//import axios from 'axios'

export async function menu() {
    return await http('/api/foo/menu')
}

export async function temp() {
    return await http('/api/foo/temp')
}

export async function logout() {
    return await http('/auth/logout')
}

/**
 * 获取所有项目流程节点
 * @returns {Promise.<*>}
 */
export async function getAllProcess() {
    return await http('/bassy/dict/allProcess')
}

/**
 * 获取所有项目流程节点
 * @returns {Promise.<*>}
 */
export async function getAllTestTask() {
    return await http('/bassy/dict/allTestTask')
}

/**
 * 获取所有字典信息
 * @returns {Promise.<*>}
 */
export async function getAllDict() {
    return await http('/bassy/dict/all')
}

/**
 * 根据类别获取字典
 * @param groupName
 * @returns {Promise<void>}
 */
export async function getAllDictByGroup(groupName) {
    return await http('/bassy/dict/' + groupName)
}

/**
 * 获取项目类型
 * @returns {Promise<void>}
 */
export async function getProgramType() {
    return await http('/bassy/programModule/getProgramType')
}

/**
 * 获取员工信息
 * @param param
 * @returns {Promise<void>}
 */
export async function getStaffInfo(param) {
    return await http(`/bassy/staff/staffInfo/${param.id}`)
}

/**
 * 获取根据工号角色编码
 * @param code
 * @returns {Promise<void>}
 */
export async function getAuthenticatedRescodes(code) {
    return await http(`/bassy/common/getAuthenticatedRescodes/${code}`)
}

/**
 * 根据关键字(如姓名、工号等)获取员工列表
 * @param keyWord
 * @returns {Promise<void>}
 */
export async function getStaffList(keyWord) {
    return await http(`/bassy/staff/staffList/${keyWord}`)
}

/**
 * 获取可用的项目模板
 * @param params
 * @returns {Promise<void>}
 */
export async function getAllEnableModules(params) {
    return await http('/bassy/programModule/getAllEnableModules', 'post', params)
}

/**
 * 根据模板id获取模板信息
 * @param id
 * @returns {Promise<void>}
 */
export async function getProgramModuleById(id) {
    return await http('/bassy/programModule/getProgramModuleById/' + id)
}

/**
 * 根据模板类型获取基础模板
 * @param typeCode
 * @returns {Promise<void>}
 */
export async function getProgramDefaultModuleByType(typeCode) {
    return await http('/bassy/programModule/getDefaultModule/' + typeCode)
}

/**
 * 获取流程模板
 * @param state
 * @returns {Promise<void>}
 */
export async function getProcessByState(state) {
    return await http(`/bassy/processModule/modules/${state}`, 'post')
}

/**
 * 获取可用的流程模板
 * @param params
 * @returns {Promise<void>}
 */
export async function getAllEnableProcess(params) {
    return await http('/bassy/processModule/allModules', 'post', params)
}

/**
 * 删除流程模板
 * @param id
 * @returns {Promise<void>}
 */
export async function deleteProcessModule(id) {
    return await http(`/bassy/processModule/delete/${id}`, 'post')
}

/**
 * 新增流程模板
 * @param params
 * @returns {Promise<void>}
 */
export async function addProcessModule(params) {
    return await http(`/bassy/processModule/add`, 'post', params)
}

/**
 * 更新流程模板
 * @param params
 * @returns {Promise<void>}
 */
export async function updateProcessModule(params) {
    return await http(`/bassy/processModule/update`, 'post', params)
}

/**
 * 删除模块
 * @param {Number} id
 */
export async function deleteModule(id) {
    return await http(`/bassy/programModule/deleteModule/${id}`, 'post')
}

/**
 * 获取默认的模型
 * @param {String} style 模板类型
 */
export async function getDefaultModule(style) {
    return await http(`/bassy/programModule/getDefaultModule/${style}`);
}

/**
 * 新增module
 * @param {Object} params
 */
export async function insertModule(params) {
    return await http('/bassy/programModule/insertModule', 'post', params)

}

/**
 * 编辑module
 * @param {Object} params
 */
export async function editModule(params) {
    return await http('/bassy/programModule/updateModule', 'post', params)
}

/**
 * 获取根据类型获取所有模板
 */
export async function getProjectTemplatesByType(style) {
    return await http(`/bassy/programModule/getProjectTemplatesByType/${style}`)
}

/**
 * 根据项目模板ID获取流程模版列表
 */
export async function getProcessTemplatesByProjectTemplateId(id) {
    return await http(`/bassy/processModule/modules/programModuleId/${id}`)
}

/**
 * 获取根据id获取所有模板
 */
export async function getProcessModuleById(id) {
    return await http(`/bassy/processModule/module/${id}`)
}

/**
 * 通过阶段node获取所有可以关联任务
 */
export async function getProcessTaskByProcessNode(processNode) {
    return await http(`/bassy/processModule/processTask/${processNode}`, `post`)
}

/**
 * 获取所有关联任务
 */
export async function getAllProcessTasks() {
    return await http(`/bassy/processModule/processTask`, `get`)
}

/**
 * 添加项目
 */
export async function addProgram(params) {
    return await http(`/bassy/program/add`, `post`, params)
}
/**
 * 添加项目需求
 */
export async function addProgramRequire(params) {
    return await http(`/bassy/program/require/add`, `post`, params)
}
/**
 * 删除项目需求
 */
export async function deleteProgramRequire(params) {
    return await http(`/bassy/program/require/delete`, `post`, params)
}

/**
 * 删除项目需求
 */
export async function transferProgramRequire(params) {
    return await http(`/bassy/program/require/transfer`, `post`, params)
}

/**
 * 获取项目所有需求
 * @param programId
 * @returns {Promise<*>}
 */
export async function getProgramRequire(programId) {
    return await http(`/bassy/program/require/${programId}`, `post`)
}

/**
 * 获取根据id获取所有模板
 */
export async function getProgram(id) {
    return await http(`/bassy/program/${id}`)
}

/**
 * 根据关键字获取项目名称
 * @param keyword
 * @returns {Promise<*>}
 */
export async function getProgramName(keyword) {
    return await http(`/bassy/program/nameList/${keyword}`,`post`)
}

/**
 * 获取所有可用项目
 */
export async function getAllProjects(params) {
    return await http('/bassy/program/list/all', 'post', params)
}

/**
 * 分页获取项目信息
 */
export async function programList(params) {
    return await http('/bassy/program/list', 'post', params)
}

/**
 * 编辑项目
 */
export async function editProgram(params) {
  return await http(`/bassy/program/edit`, `post`, params)
}

/**
 * 创建测试计划
 * @param params
 * @returns {Promise.<*>}
 */
export async function creatTestPlan(params) {
    return await http('/bassy/program/testplan/create', 'post', params)
}

/**
 * 编辑测试计划
 * @param params
 * @returns {Promise.<*>}
 */
export async function editTestPlan(params) {
    return await http(`/bassy/program/testplan/update`, 'post', params)
}

/**
 * 批量编辑测试计划
 * @param params
 * @returns {Promise.<*>}
 */
export async function batchEditTestPlan(params) {
  return await http(`/bassy/program/testplan/update/batch`, 'post', params)
}

/**
 * 删除测试计划
 * @param id
 * @returns {Promise.<*>}
 */
export async function deleteTestPlan(id) {
    return await http(`/bassy/program/testplan/delete/${id}`, 'post')
}

/**
 * 获取测试计划
 * @param programId
 * @returns {Promise.<*>}
 */
export async function getTestPlan(programId) {
    return await http(`/bassy/program/testplan/${programId}`)
}

/**
 * 分页获取测试任务列表
 * @param params
 * @returns {Promise.<*>}
 */
export async function getTasks(params) {
    return await http(`/bassy/task/list`, `post`, params)
}

/**
 * 获取指定项目的所有测试任务列表
 * @param params
 * @returns {Promise.<*>}
 */
export async function getProgramTasks(params) {
    return await http(`/bassy/task/list/program`, `post`, params)
}

/**
 * 流程模版存在校验
 * @param processModuleName
 * @returns {Promise.<*>}
 */
export async function processModuleIsExit(processModuleName) {
    return await http(`/bassy/processModule/processModuleIsExit/${processModuleName}`, `post`)
}

/**
 * 项目存在校验
 * @param programName
 * @returns {Promise.<*>}
 */
export async function programIsExit(programName) {
    return await http(`/bassy/program/programIsExit/${programName}`, `post`)
}

/**
 * 更新项目状态
 * @param params
 * @returns {Promise.<*>}
 */
export async function updateProgramStatus(params) {
    return await http(`/bassy/program/status/update`, `post`, params)
}

/**
 * 获取测试用例
 * @returns {Promise.<*>}
 */
export async function getTestCases(testProject) {
    return await http(`/bassy/autoTest/testCase/get/${testProject}`, `post`)
}

/**
 * 执行测试用例
 * @param params
 * @returns {Promise.<*>}
 */
export async function testCaseDo(params) {
    return await http(`/bassy/autoTest/testCase/do`, `post`, params);
}

/**
 * 设置自动化测试异常结果
 * @param id
 * @returns {Promise.<*>}
 */
export async function setAbnormalRestult(id) {
    return await http(`/bassy/autoTest/testResult/update/${id}`, `post`);
}

/**
 * 获取测试结果集合
 * @param params
 * @returns {Promise.<*>}
 */
export async function getAutoTestResults(params) {
    return await http(`/bassy/autoTest/testResult/get`, `post`, params);
}

/**
 * 获取测试报告
 * @param autoTestId
 * @returns {Promise.<*>}
 */
export async function getAutoTestReport(autoTestId) {
    return await http(`/bassy/autoTest/testReport/pull/${autoTestId}`);
}

/**
 * 项目模版存在校验
 * @param programModuleName
 * @returns {Promise.<*>}
 */
export async function programModuleIsExit(programModuleName) {
    return await http(`/bassy/programModule/programModuleIsExit/${programModuleName}`, `post`)
}

/**
 * 获取我的任务日历
 * @param code
 * @returns {Promise<void>}
 */
export async function getMyCalendarTasks(code) {
    return await http(`/bassy/task/list/myCalendarTask/${code}`, `post`);
}

/**
 * 获取更新任务记录
 * @param params
 * @returns {Promise.<*>}
 */
export async function getTaskHistory(params) {
    return await http(`/bassy/task/update/history`, `post`, params);
}

/**
 * 更新任务进度
 * @param params
 * @returns {Promise.<*>}
 */
export async function updateTaskRate(params) {
    return await http(`/bassy/task/update`, `post`, params);
}

/**
 * 开始任务
 * @param params
 * @returns {Promise.<*>}
 */
export async function startTask(params) {
    return await http(`/bassy/task/start`, `post`, params);
}

/**
 * 上传任务附件
 * @returns {Promise.<*>}
 */
export async function pushDocument(params) {
    return await httpUpload(`/bassy/task/document/push`, `post`, params);
}


/**
 * 获取任务文档
 * @param projectId
 * @returns {Promise.<*>}
 */
export async function getTaskDocumentList(projectId) {
    return await httpParams(`/bassy/program/document/list/${projectId}`);
}

/**
 * 删除任务文档
 * @param params
 * @returns {Promise.<*>}
 */
export async function deleteProgramDocument(params) {
    return await httpParams(`/bassy/program/document/delete`, `post`, params);
}

/**
 * 生成项目日报
 * @param params
 * @returns {Promise.<*>}
 */
export async function addProgramReport(params) {
    return await http(`/bassy/program/report/add`, `post`, params);
}

/**
 * 获取项目日报列表
 * @param params
 * @returns {Promise.<*>}
 */
export async function getHistoryReports(params) {
    return await http(`/bassy/program/report/getHistoryList`, `post`, params);
}

/**
 * 获取一个组的所有成员
 * @param departId
 * @returns {Promise.<*>}
 */
export async function getDepartStaff(departId) {
    return await http(`/bassy/staff/departStaff/${departId}`);
}

/**
 * 获取小组日常日报
 * @param departId
 * @returns {Promise<void>}
 */
export async function getWorkDailyReport(params) {
  return await http(`/bassy/statistic/getWorkDailyReport`, `post`, params);
}

/**
 * 查询资源排期热力数据
 * @param params
 * @returns {Promise.<*>}
 */
export async function getHeapWorkHourData(params) {
    return await http(`/bassy/statistic/getHeapWorkHourList`, `post`, params);
}

export async function getWorkHourTrend(params) {
    return await http(`/bassy/statistic/getWorkHourTrend`, `post`, params);
}

/**
 * 查询任务异常数据
 * @param params
 * @returns {Promise.<*>}
 */
export async function getTaskAbnormalCollect(params) {
    return await http(`/bassy/statistic/getTaskAbnormalCollect`, `post`, params);
}

/**
 * 根据条件查询项目异常信息
 * @param params
 * @returns {Promise<void>}
 */
export async function getTaskAbnormalListByCondition(params) {
    return await http(`/bassy/statistic/getTaskAbnormalListByCondition`, `post`, params);
}

/**
 * 获取项目异常信息的统计数据
 * @param params
 * @returns {Promise<void>}
 */
export async function getTaskAbnormalCountDTO(params) {
    return await http(`/bassy/statistic/getTaskAbnormalCountDTO`, `post`, params);
}

/**
 * 获取各种状态的项目数量
 * @returns {Promise<void>}
 */
export async function getProjectNumCount() {
    return await http(`/bassy/statistic/getProjectNumCount`);
}

/**
 * 获取项目异常所属的团队
 * @returns {Promise<void>}
 */
export async function getTaskAbnormalReasonTeamList() {
    return await http(`/bassy/statistic/getTaskAbnormalReasonTeamList`);
}

/**
 * 获取项目异常的分类
 * @returns {Promise<void>}
 */
export async function getTaskAbnormalReasonTypeList() {
    return await http(`/bassy/statistic/getTaskAbnormalReasonTypeList`);
}

/**
 * 根据输入的关键字查询禅道需求
 * @param keyword
 * @returns {Promise.<*>}
 */
export async function getZentaoStories(keyword) {
    return await http(`/bassy/program/zentao/stories/${keyword}`);
}

/**
 * 根据输入的关键字查询禅道项目
 * @param keyword
 * @returns {Promise.<*>}
 */
export async function getZentaoProjects(keyword) {
  return await http(`/bassy/program/zentao/projects/${keyword}`);
}

/**
 * 获取项目需求进度
 * @param params
 * @returns {Promise<*>}
 */
export async function getProgramReportCount(params) {
    return await http(`/bassy/program/report/count`, `post`,params);
}

/**
 * 获取需求详情
 * @param requireId 需求id
 * @returns {Promise<*>}
 */
export async function getProgramRequireInfo(requireId) {
    return await http(`/bassy/program/requireInfo/${requireId}`, `post`);
}

/**
 * 手动更新自动化测试用例
 * @returns {Promise<*>}
 */
export async function updateTestCase() {
    return await http(`/bassy/autoTest/testCase/update`);
}

/**
 * 分页获取失败用例列表
 * @param failMethod
 * @returns {Promise<*>}
 */
export async function getfailMethodList(failMethod) {
    return await http(`/bassy/autoTest/failMethod/getList`, `post`,failMethod);
}

/**
 * 更新失败用例信息
 * @param failMethod
 * @returns {Promise<*>}
 */
export async function updateFailMethod(failMethod) {
    return await http(`/bassy/autoTest/failMethod/updateReason`, `post`, failMethod)
}

/**
 * 作废任务日志
 * @param id
 * @returns {Promise<void>}
 */
export async function invalidTaskLog(id) {
    return await http(`/bassy/task/invalid/taskLog/${id}`, 'post')
}

/**
 * 更新任务日志
 * @param params
 * @returns {Promise<void>}
 */
export async function updateTaskLog(params) {
    return await http(`/bassy/task/taskLog/update`, 'post',params)
}

/**
 * 获取资产信息
 * @returns {Promise<void>}
 */
export async function getAsset() {
    return await http(`/bassy/asset/list`)
}

/**
 * 资产借用
 * @param params
 * @returns {Promise<void>}
 */
export async function borrowAsset(params) {
    return await http(`/bassy/asset/borrow`, 'post',params)
}

/**
 * 资产归还
 * @param params
 * @returns {Promise<void>}
 */
export async function returnAsset(params) {
  return await http(`/bassy/asset/return`, 'post',params)
}

/**
 * 变更资产状态
 * @param params
 * @returns {Promise<void>}
 */
export async function updateAssetStatus(params) {
  return await http(`/bassy/asset/updateStatus`, 'post',params)
}

/**
 * 获取资产操作日志
 * @param id
 * @returns {Promise<void>}
 */
export async function getAssetLogs(id) {
  return await http(`/bassy/asset/assetLogs/${id}`)
}

/**
 * 添加新资产
 * @param params
 * @returns {Promise<void>}
 */
export async function addAsset(params) {
  return await http(`/bassy/asset/add`, 'post',params)
}

/**
 * 编辑资产信息
 * @param params
 * @returns {Promise<void>}
 */
export async function editAsset(params) {
  return await http(`/bassy/asset/edit`, 'post',params)
}

/**
 * 获取测试套件
 * @param params
 * @returns {Promise<*>}
 */
export async function getTestSuites(params) {
    return await http(`/bassy/autoTest/suite/get`, 'post',params)
}

/**
 * 创建测试套件
 * @param params
 * @returns {Promise<*>}
 */
export async function createTestSuite(params,staff) {
    return await http(`/bassy/autoTest/suite/create/${staff}`, 'post',params)
}

/**
 * 删除测试套件
 * @param suiteId 测试套件ID
 * @returns {Promise<*>}
 */
export async function deleteTestSuite(suiteId) {
    return await http(`/bassy/autoTest/suite/delete/${suiteId}`, 'post')
}

/**
 * 获取自动化测试统计结果
 * @returns {Promise<*>}
 */
export async function getAutoTestStatistics() {
    return await http(`/bassy/autoTest/statistics/get`, 'post')
}

/**
 * 获取部门异常统计
 * @param param
 * @returns {Promise<void>}
 */
export async function getDepartAbnormalCount(param) {
  return await http(`/bassy/statistic/getDepartAbnormalCount`, 'post', param)
}

/**
 * 获取部门异常明细
 * @param param
 * @returns {Promise<void>}
 */
export async function getDepartAbnormalDetail(param) {
  return await http(`/bassy/statistic/getDepartAbnormalDetail`, 'post', param)
}

/**
 * 调度派单工具，订单找人
 * @param param
 * @returns {Promise<*>}
 */
export async function dispatchAppsVerify(param) {
  return await http(`/bassy/tool/dispatch/apps/verify`, 'post', param)
}
/**
 * 调度派单工具，订单找人
 * @param param
 * @returns {Promise<*>}
 */
export async function dispatchFindRider(param) {
  return await http(`/bassy/tool/dispatch/find/rider`, 'post', param)
}

/**
 * 调度派单工具，骑手过滤
 * @param param
 * @returns {Promise<*>}
 */
export async function dispatchRiderFilter(param) {
  return await http(`/bassy/tool/dispatch/rider/filter`, 'post', param)
}

/**
 * 获取任务的异常历史
 * @param param
 * @returns {Promise<void>}
 */
export async function getAbnormalLog(param) {
  return await http(`/bassy/task/list/abnormal`, 'post', param)
}

/**
 * 获取员工工作量统计
 * @param param
 * @returns {Promise<void>}
 */
export async function getStaffWorkLoad(param) {
    return await http(`/bassy/statistic/getStaffWorkLoadCount`, 'post', param)
}

/**
 * 获取部门工作量统计
 * @param param
 * @returns {Promise<void>}
 */
export async function getDepartWorkLoad(param) {
    return await http(`/bassy/statistic/getDepartWorkLoadCount`, 'post', param)
}

/**
 * 获取期刊数据
 * @param param
 * @returns {Promise<void>}
 */
export async function getBBSPosts(param) {
    return await http(`/bassy/bbs/list`, 'post', param)
}

/**
 * 新增/修改期刊
 * @param param
 * @returns {Promise<void>}
 */
export async function editBBS(param) {
    return await http(`/bassy/bbs/edit`, 'post', param)
}

/**
 * 获取期刊详情
 * @param param
 * @returns {Promise<void>}
 */
export async function getBBSDetail(id,param) {
    return await http(`/bassy/bbs/detail/${id}`, 'post', param);
}

/**
 * 获取周刊详情
 * @param param
 * @returns {Promise<void>}
 */
export async function getWeeklyDetail(id,param) {
    return await http(`/bassy/bbs/weekly/detail/${id}`, 'post', param);
}

/**
 * 获取期刊个人贡献数
 * @param param
 * @returns {Promise<void>}
 */
export async function countBBSPersCtrb() {
    return await http(`/bassy/bbs/countPersCtrb`);
}

/**
 * 获取期刊操作记录
 * @param param
 * @returns {Promise<void>}
 */
export async function getBBSLog() {
    return await http(`/bassy/bbs/getLog`);
}

/**
 * 期刊点赞
 * @param param
 * @returns {Promise<void>}
 */
export async function likeBBS(param) {
    return await http(`/bassy/bbs/likeBBS`, 'post', param);
}

/**
 * 学习打卡统计
 * @param param
 * @returns {Promise<void>}
 */
export async function countStudyTimes(param) {
    return await http(`/bassy/bbs/countStudyTms`, 'post', param);
}

/**
 * 分页获取用例
 * @param param
 * @returns {Promise<*>}
 */
export async function getPagedBaseCase(param) {
    return await http(`/bassy/case/pagelist`, 'post', param)
}

/**
 * 获取xmind格式基础用例
 * @param param
 * @returns {Promise<*>}
 */
export async function getXmindBaseCase(param) {
    return await http(`/bassy/case/xmind/list`, 'post', param)
}
/**
 * 通过xmind更新测试用例
 * @param param
 * @returns {Promise<*>}
 */
export async function updateXmindBaseCase(param) {
    return await http(`/bassy/case/xmind/update`, 'post', param)
}

/**
 * 获取用例详情
 * @param param
 * @returns {Promise<*>}
 */
export async function getCaseDetail(param) {
    return await http(`/bassy/case/detail`, 'post', param)
}

/**
 * 更新测试用例
 * @param param
 * @returns {Promise<*>}
 */
export async function updateCase(param) {
    return await http(`/bassy/case/update`, 'post', param)
}

/**
 * 新增用例
 * @param param
 * @returns {Promise<*>}
 */
export async function addCase(param) {
    return await http(`/bassy/case/add`, 'post', param)
}

/**
 * 复制用例
 * @param param
 * @returns {Promise<*>}
 */
export async function copyCase(param) {
    return await http(`/bassy/case/copy`, 'post', param)
}

/**
 * 删除用例
 * @param param
 * @returns {Promise<*>}
 */
export async function deleteCase(param) {
    return await http(`/bassy/case/delete`, 'post', param)
}

/**
 * 获取标签集合
 * @param param
 * @returns {Promise<*>}
 */
export async function getLabelList(param) {
    return await http(`/bassy/case/label/list`, 'post', param)
}

/**
 * 编辑新增标签
 * @param param
 * @returns {Promise<*>}
 */
export async function updateLabelInfo(param) {
    return await http(`/bassy/case/label/update`, 'post', param)
}

/**
 * 删除标签
 * @param param
 * @returns {Promise<*>}
 */
export async function deleteLabel(param) {
    return await http(`/bassy/case/label/delete`, 'post', param)
}

/**
 * 获取产品所有模块
 * @param param
 * @returns {Promise<*>}
 */
export async function getModuleList(param) {
    return await http(`/bassy/case/module/list`, 'post', param)
}

/**
 * 获取产品列表
 * @param param
 * @returns {Promise<*>}
 */
export async function getProductList(param) {
    return await http(`/bassy/case/product/list`, 'post', param)
}

/**
 * 获取产品模块列表
 * @param param
 * @returns {Promise<*>}
 */
export async function getProductModuleTree(param) {
    return await http(`/bassy/case/product/module/tree`, 'post', param)
}

/**
 * 更新产品及模块
 * @param param
 * @returns {Promise<*>}
 */
export async function productModuleOperate(param) {
    return await http(`/bassy/case/product/module/operate`, 'post', param)
}

/**
 * 获取父节点所有子模块
 * @param param
 * @returns {Promise<*>}
 */
export async function getParenModuleSub(param) {
    return await http(`/bassy/case/product/module/sub`, 'post', param)
}

/**
 * 导入用例
 * @param param
 * @returns {Promise<*>}
 */
export async function importTestCase(param) {
    return await http(`/bassy/case/import`, 'post', param)
}

export async function deleteByXmindTestCase(param) {
    return await http(`/bassy/case/xmind/delete`, 'post', param)
}

/**
 * 获取测试计划的需求视图
 * @param param
 * @returns {Promise<void>}
 */
export async function getPlanRequireBoard(programId) {
    return await http(`/bassy/program/testplan/requireBoard/${programId}`)
}

/**
 * 获取项目用例首页数据
 * @param param
 * @returns {Promise<void>}
 */
export async function getProCaseIndex(param) {
    return await http(`/bassy/case/programCase`, 'post', param)
}

/**
 * 推送用例
 * @param param
 * @returns {Promise<*>}
 */
export async function pushProgramCaseToBaseCase(param) {
    return await http(`/bassy/case/program/require/push`, 'post', param)
}

/**
 * 拉取用例
 * @param param
 * @returns {Promise<*>}
 */
export async function pullBaseCaseToProgramCase(param) {
    return await http(`/bassy/case/program/require/pull`, 'post', param)
}

/**
 * 解决版本冲突用例
 * @param param
 * @returns {Promise<*>}
 */
export async function solveConflictCase(param) {
    return await http(`/bassy/case/program/conflict/solve`, 'post', param)
}

/**
 * 从基线用例导入到项目用例
 * @param param
 * @returns {Promise<void>}
 */
export async function importFromBase(param) {
    return await http(`/bassy/case/program/importfrombase`, 'post', param)
}

/**
 * 自动生成系统标签
 * @param param
 * @returns {Promise<void>}
 */
export async function genSysLabel(param) {
    return await http(`/bassy/case/program/genSysLabel`, 'post', param)
}

