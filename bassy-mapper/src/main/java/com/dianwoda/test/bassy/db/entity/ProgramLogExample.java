package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramLogExample() {
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

        public Criteria andProgramIdIsNull() {
            addCriterion("program_id is null");
            return (Criteria) this;
        }

        public Criteria andProgramIdIsNotNull() {
            addCriterion("program_id is not null");
            return (Criteria) this;
        }

        public Criteria andProgramIdEqualTo(Integer value) {
            addCriterion("program_id =", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotEqualTo(Integer value) {
            addCriterion("program_id <>", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThan(Integer value) {
            addCriterion("program_id >", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("program_id >=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThan(Integer value) {
            addCriterion("program_id <", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThanOrEqualTo(Integer value) {
            addCriterion("program_id <=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdIn(List<Integer> values) {
            addCriterion("program_id in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotIn(List<Integer> values) {
            addCriterion("program_id not in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdBetween(Integer value1, Integer value2) {
            addCriterion("program_id between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotBetween(Integer value1, Integer value2) {
            addCriterion("program_id not between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPercentIsNull() {
            addCriterion("percent is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("percent is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(Integer value) {
            addCriterion("percent =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(Integer value) {
            addCriterion("percent <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(Integer value) {
            addCriterion("percent >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("percent >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(Integer value) {
            addCriterion("percent <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(Integer value) {
            addCriterion("percent <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<Integer> values) {
            addCriterion("percent in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<Integer> values) {
            addCriterion("percent not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(Integer value1, Integer value2) {
            addCriterion("percent between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("percent not between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andProgramExplainIsNull() {
            addCriterion("program_explain is null");
            return (Criteria) this;
        }

        public Criteria andProgramExplainIsNotNull() {
            addCriterion("program_explain is not null");
            return (Criteria) this;
        }

        public Criteria andProgramExplainEqualTo(String value) {
            addCriterion("program_explain =", value, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainNotEqualTo(String value) {
            addCriterion("program_explain <>", value, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainGreaterThan(String value) {
            addCriterion("program_explain >", value, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainGreaterThanOrEqualTo(String value) {
            addCriterion("program_explain >=", value, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainLessThan(String value) {
            addCriterion("program_explain <", value, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainLessThanOrEqualTo(String value) {
            addCriterion("program_explain <=", value, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainLike(String value) {
            addCriterion("program_explain like", value, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainNotLike(String value) {
            addCriterion("program_explain not like", value, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainIn(List<String> values) {
            addCriterion("program_explain in", values, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainNotIn(List<String> values) {
            addCriterion("program_explain not in", values, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainBetween(String value1, String value2) {
            addCriterion("program_explain between", value1, value2, "programExplain");
            return (Criteria) this;
        }

        public Criteria andProgramExplainNotBetween(String value1, String value2) {
            addCriterion("program_explain not between", value1, value2, "programExplain");
            return (Criteria) this;
        }

        public Criteria andLogTypeIsNull() {
            addCriterion("log_type is null");
            return (Criteria) this;
        }

        public Criteria andLogTypeIsNotNull() {
            addCriterion("log_type is not null");
            return (Criteria) this;
        }

        public Criteria andLogTypeEqualTo(Integer value) {
            addCriterion("log_type =", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotEqualTo(Integer value) {
            addCriterion("log_type <>", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeGreaterThan(Integer value) {
            addCriterion("log_type >", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("log_type >=", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeLessThan(Integer value) {
            addCriterion("log_type <", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeLessThanOrEqualTo(Integer value) {
            addCriterion("log_type <=", value, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeIn(List<Integer> values) {
            addCriterion("log_type in", values, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotIn(List<Integer> values) {
            addCriterion("log_type not in", values, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeBetween(Integer value1, Integer value2) {
            addCriterion("log_type between", value1, value2, "logType");
            return (Criteria) this;
        }

        public Criteria andLogTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("log_type not between", value1, value2, "logType");
            return (Criteria) this;
        }

        public Criteria andBugInfoIsNull() {
            addCriterion("bug_info is null");
            return (Criteria) this;
        }

        public Criteria andBugInfoIsNotNull() {
            addCriterion("bug_info is not null");
            return (Criteria) this;
        }

        public Criteria andBugInfoEqualTo(String value) {
            addCriterion("bug_info =", value, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoNotEqualTo(String value) {
            addCriterion("bug_info <>", value, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoGreaterThan(String value) {
            addCriterion("bug_info >", value, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoGreaterThanOrEqualTo(String value) {
            addCriterion("bug_info >=", value, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoLessThan(String value) {
            addCriterion("bug_info <", value, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoLessThanOrEqualTo(String value) {
            addCriterion("bug_info <=", value, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoLike(String value) {
            addCriterion("bug_info like", value, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoNotLike(String value) {
            addCriterion("bug_info not like", value, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoIn(List<String> values) {
            addCriterion("bug_info in", values, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoNotIn(List<String> values) {
            addCriterion("bug_info not in", values, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoBetween(String value1, String value2) {
            addCriterion("bug_info between", value1, value2, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andBugInfoNotBetween(String value1, String value2) {
            addCriterion("bug_info not between", value1, value2, "bugInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoIsNull() {
            addCriterion("task_info is null");
            return (Criteria) this;
        }

        public Criteria andTaskInfoIsNotNull() {
            addCriterion("task_info is not null");
            return (Criteria) this;
        }

        public Criteria andTaskInfoEqualTo(String value) {
            addCriterion("task_info =", value, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoNotEqualTo(String value) {
            addCriterion("task_info <>", value, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoGreaterThan(String value) {
            addCriterion("task_info >", value, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoGreaterThanOrEqualTo(String value) {
            addCriterion("task_info >=", value, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoLessThan(String value) {
            addCriterion("task_info <", value, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoLessThanOrEqualTo(String value) {
            addCriterion("task_info <=", value, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoLike(String value) {
            addCriterion("task_info like", value, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoNotLike(String value) {
            addCriterion("task_info not like", value, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoIn(List<String> values) {
            addCriterion("task_info in", values, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoNotIn(List<String> values) {
            addCriterion("task_info not in", values, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoBetween(String value1, String value2) {
            addCriterion("task_info between", value1, value2, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andTaskInfoNotBetween(String value1, String value2) {
            addCriterion("task_info not between", value1, value2, "taskInfo");
            return (Criteria) this;
        }

        public Criteria andIsNormalIsNull() {
            addCriterion("is_normal is null");
            return (Criteria) this;
        }

        public Criteria andIsNormalIsNotNull() {
            addCriterion("is_normal is not null");
            return (Criteria) this;
        }

        public Criteria andIsNormalEqualTo(Byte value) {
            addCriterion("is_normal =", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalNotEqualTo(Byte value) {
            addCriterion("is_normal <>", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalGreaterThan(Byte value) {
            addCriterion("is_normal >", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_normal >=", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalLessThan(Byte value) {
            addCriterion("is_normal <", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalLessThanOrEqualTo(Byte value) {
            addCriterion("is_normal <=", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalIn(List<Byte> values) {
            addCriterion("is_normal in", values, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalNotIn(List<Byte> values) {
            addCriterion("is_normal not in", values, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalBetween(Byte value1, Byte value2) {
            addCriterion("is_normal between", value1, value2, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalNotBetween(Byte value1, Byte value2) {
            addCriterion("is_normal not between", value1, value2, "isNormal");
            return (Criteria) this;
        }

        public Criteria andReasonTeamIsNull() {
            addCriterion("reason_team is null");
            return (Criteria) this;
        }

        public Criteria andReasonTeamIsNotNull() {
            addCriterion("reason_team is not null");
            return (Criteria) this;
        }

        public Criteria andReasonTeamEqualTo(String value) {
            addCriterion("reason_team =", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamNotEqualTo(String value) {
            addCriterion("reason_team <>", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamGreaterThan(String value) {
            addCriterion("reason_team >", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamGreaterThanOrEqualTo(String value) {
            addCriterion("reason_team >=", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamLessThan(String value) {
            addCriterion("reason_team <", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamLessThanOrEqualTo(String value) {
            addCriterion("reason_team <=", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamLike(String value) {
            addCriterion("reason_team like", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamNotLike(String value) {
            addCriterion("reason_team not like", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamIn(List<String> values) {
            addCriterion("reason_team in", values, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamNotIn(List<String> values) {
            addCriterion("reason_team not in", values, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamBetween(String value1, String value2) {
            addCriterion("reason_team between", value1, value2, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamNotBetween(String value1, String value2) {
            addCriterion("reason_team not between", value1, value2, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIsNull() {
            addCriterion("reason_type is null");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIsNotNull() {
            addCriterion("reason_type is not null");
            return (Criteria) this;
        }

        public Criteria andReasonTypeEqualTo(String value) {
            addCriterion("reason_type =", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotEqualTo(String value) {
            addCriterion("reason_type <>", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeGreaterThan(String value) {
            addCriterion("reason_type >", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeGreaterThanOrEqualTo(String value) {
            addCriterion("reason_type >=", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeLessThan(String value) {
            addCriterion("reason_type <", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeLessThanOrEqualTo(String value) {
            addCriterion("reason_type <=", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeLike(String value) {
            addCriterion("reason_type like", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotLike(String value) {
            addCriterion("reason_type not like", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIn(List<String> values) {
            addCriterion("reason_type in", values, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotIn(List<String> values) {
            addCriterion("reason_type not in", values, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeBetween(String value1, String value2) {
            addCriterion("reason_type between", value1, value2, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotBetween(String value1, String value2) {
            addCriterion("reason_type not between", value1, value2, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonLevelIsNull() {
            addCriterion("reason_level is null");
            return (Criteria) this;
        }

        public Criteria andReasonLevelIsNotNull() {
            addCriterion("reason_level is not null");
            return (Criteria) this;
        }

        public Criteria andReasonLevelEqualTo(String value) {
            addCriterion("reason_level =", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelNotEqualTo(String value) {
            addCriterion("reason_level <>", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelGreaterThan(String value) {
            addCriterion("reason_level >", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelGreaterThanOrEqualTo(String value) {
            addCriterion("reason_level >=", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelLessThan(String value) {
            addCriterion("reason_level <", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelLessThanOrEqualTo(String value) {
            addCriterion("reason_level <=", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelLike(String value) {
            addCriterion("reason_level like", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelNotLike(String value) {
            addCriterion("reason_level not like", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelIn(List<String> values) {
            addCriterion("reason_level in", values, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelNotIn(List<String> values) {
            addCriterion("reason_level not in", values, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelBetween(String value1, String value2) {
            addCriterion("reason_level between", value1, value2, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelNotBetween(String value1, String value2) {
            addCriterion("reason_level not between", value1, value2, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonDetailIsNull() {
            addCriterion("reason_detail is null");
            return (Criteria) this;
        }

        public Criteria andReasonDetailIsNotNull() {
            addCriterion("reason_detail is not null");
            return (Criteria) this;
        }

        public Criteria andReasonDetailEqualTo(String value) {
            addCriterion("reason_detail =", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailNotEqualTo(String value) {
            addCriterion("reason_detail <>", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailGreaterThan(String value) {
            addCriterion("reason_detail >", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailGreaterThanOrEqualTo(String value) {
            addCriterion("reason_detail >=", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailLessThan(String value) {
            addCriterion("reason_detail <", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailLessThanOrEqualTo(String value) {
            addCriterion("reason_detail <=", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailLike(String value) {
            addCriterion("reason_detail like", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailNotLike(String value) {
            addCriterion("reason_detail not like", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailIn(List<String> values) {
            addCriterion("reason_detail in", values, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailNotIn(List<String> values) {
            addCriterion("reason_detail not in", values, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailBetween(String value1, String value2) {
            addCriterion("reason_detail between", value1, value2, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailNotBetween(String value1, String value2) {
            addCriterion("reason_detail not between", value1, value2, "reasonDetail");
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

        public Criteria andModifyTmIsNull() {
            addCriterion("modify_tm is null");
            return (Criteria) this;
        }

        public Criteria andModifyTmIsNotNull() {
            addCriterion("modify_tm is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTmEqualTo(Date value) {
            addCriterion("modify_tm =", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotEqualTo(Date value) {
            addCriterion("modify_tm <>", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmGreaterThan(Date value) {
            addCriterion("modify_tm >", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_tm >=", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmLessThan(Date value) {
            addCriterion("modify_tm <", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmLessThanOrEqualTo(Date value) {
            addCriterion("modify_tm <=", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmIn(List<Date> values) {
            addCriterion("modify_tm in", values, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotIn(List<Date> values) {
            addCriterion("modify_tm not in", values, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmBetween(Date value1, Date value2) {
            addCriterion("modify_tm between", value1, value2, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotBetween(Date value1, Date value2) {
            addCriterion("modify_tm not between", value1, value2, "modifyTm");
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