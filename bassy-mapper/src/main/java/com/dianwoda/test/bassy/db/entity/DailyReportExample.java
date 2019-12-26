package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DailyReportExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("report_date is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("report_date is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(Date value) {
            addCriterion("report_date =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(Date value) {
            addCriterion("report_date <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(Date value) {
            addCriterion("report_date >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(Date value) {
            addCriterion("report_date >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(Date value) {
            addCriterion("report_date <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(Date value) {
            addCriterion("report_date <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<Date> values) {
            addCriterion("report_date in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<Date> values) {
            addCriterion("report_date not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(Date value1, Date value2) {
            addCriterion("report_date between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(Date value1, Date value2) {
            addCriterion("report_date not between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andProjectStageIsNull() {
            addCriterion("project_stage is null");
            return (Criteria) this;
        }

        public Criteria andProjectStageIsNotNull() {
            addCriterion("project_stage is not null");
            return (Criteria) this;
        }

        public Criteria andProjectStageEqualTo(String value) {
            addCriterion("project_stage =", value, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageNotEqualTo(String value) {
            addCriterion("project_stage <>", value, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageGreaterThan(String value) {
            addCriterion("project_stage >", value, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageGreaterThanOrEqualTo(String value) {
            addCriterion("project_stage >=", value, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageLessThan(String value) {
            addCriterion("project_stage <", value, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageLessThanOrEqualTo(String value) {
            addCriterion("project_stage <=", value, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageLike(String value) {
            addCriterion("project_stage like", value, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageNotLike(String value) {
            addCriterion("project_stage not like", value, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageIn(List<String> values) {
            addCriterion("project_stage in", values, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageNotIn(List<String> values) {
            addCriterion("project_stage not in", values, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageBetween(String value1, String value2) {
            addCriterion("project_stage between", value1, value2, "projectStage");
            return (Criteria) this;
        }

        public Criteria andProjectStageNotBetween(String value1, String value2) {
            addCriterion("project_stage not between", value1, value2, "projectStage");
            return (Criteria) this;
        }

        public Criteria andTaskProgressIsNull() {
            addCriterion("task_progress is null");
            return (Criteria) this;
        }

        public Criteria andTaskProgressIsNotNull() {
            addCriterion("task_progress is not null");
            return (Criteria) this;
        }

        public Criteria andTaskProgressEqualTo(String value) {
            addCriterion("task_progress =", value, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressNotEqualTo(String value) {
            addCriterion("task_progress <>", value, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressGreaterThan(String value) {
            addCriterion("task_progress >", value, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressGreaterThanOrEqualTo(String value) {
            addCriterion("task_progress >=", value, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressLessThan(String value) {
            addCriterion("task_progress <", value, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressLessThanOrEqualTo(String value) {
            addCriterion("task_progress <=", value, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressLike(String value) {
            addCriterion("task_progress like", value, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressNotLike(String value) {
            addCriterion("task_progress not like", value, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressIn(List<String> values) {
            addCriterion("task_progress in", values, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressNotIn(List<String> values) {
            addCriterion("task_progress not in", values, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressBetween(String value1, String value2) {
            addCriterion("task_progress between", value1, value2, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andTaskProgressNotBetween(String value1, String value2) {
            addCriterion("task_progress not between", value1, value2, "taskProgress");
            return (Criteria) this;
        }

        public Criteria andBugSummaryIsNull() {
            addCriterion("bug_summary is null");
            return (Criteria) this;
        }

        public Criteria andBugSummaryIsNotNull() {
            addCriterion("bug_summary is not null");
            return (Criteria) this;
        }

        public Criteria andBugSummaryEqualTo(String value) {
            addCriterion("bug_summary =", value, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryNotEqualTo(String value) {
            addCriterion("bug_summary <>", value, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryGreaterThan(String value) {
            addCriterion("bug_summary >", value, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("bug_summary >=", value, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryLessThan(String value) {
            addCriterion("bug_summary <", value, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryLessThanOrEqualTo(String value) {
            addCriterion("bug_summary <=", value, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryLike(String value) {
            addCriterion("bug_summary like", value, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryNotLike(String value) {
            addCriterion("bug_summary not like", value, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryIn(List<String> values) {
            addCriterion("bug_summary in", values, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryNotIn(List<String> values) {
            addCriterion("bug_summary not in", values, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryBetween(String value1, String value2) {
            addCriterion("bug_summary between", value1, value2, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andBugSummaryNotBetween(String value1, String value2) {
            addCriterion("bug_summary not between", value1, value2, "bugSummary");
            return (Criteria) this;
        }

        public Criteria andProjectRiskIsNull() {
            addCriterion("project_risk is null");
            return (Criteria) this;
        }

        public Criteria andProjectRiskIsNotNull() {
            addCriterion("project_risk is not null");
            return (Criteria) this;
        }

        public Criteria andProjectRiskEqualTo(String value) {
            addCriterion("project_risk =", value, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskNotEqualTo(String value) {
            addCriterion("project_risk <>", value, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskGreaterThan(String value) {
            addCriterion("project_risk >", value, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskGreaterThanOrEqualTo(String value) {
            addCriterion("project_risk >=", value, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskLessThan(String value) {
            addCriterion("project_risk <", value, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskLessThanOrEqualTo(String value) {
            addCriterion("project_risk <=", value, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskLike(String value) {
            addCriterion("project_risk like", value, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskNotLike(String value) {
            addCriterion("project_risk not like", value, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskIn(List<String> values) {
            addCriterion("project_risk in", values, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskNotIn(List<String> values) {
            addCriterion("project_risk not in", values, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskBetween(String value1, String value2) {
            addCriterion("project_risk between", value1, value2, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andProjectRiskNotBetween(String value1, String value2) {
            addCriterion("project_risk not between", value1, value2, "projectRisk");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}