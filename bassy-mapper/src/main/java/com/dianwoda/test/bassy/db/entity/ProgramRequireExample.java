package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramRequireExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramRequireExample() {
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

        public Criteria andRequireIdIsNull() {
            addCriterion("require_id is null");
            return (Criteria) this;
        }

        public Criteria andRequireIdIsNotNull() {
            addCriterion("require_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequireIdEqualTo(Integer value) {
            addCriterion("require_id =", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdNotEqualTo(Integer value) {
            addCriterion("require_id <>", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdGreaterThan(Integer value) {
            addCriterion("require_id >", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("require_id >=", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdLessThan(Integer value) {
            addCriterion("require_id <", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdLessThanOrEqualTo(Integer value) {
            addCriterion("require_id <=", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdIn(List<Integer> values) {
            addCriterion("require_id in", values, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdNotIn(List<Integer> values) {
            addCriterion("require_id not in", values, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdBetween(Integer value1, Integer value2) {
            addCriterion("require_id between", value1, value2, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdNotBetween(Integer value1, Integer value2) {
            addCriterion("require_id not between", value1, value2, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireNameIsNull() {
            addCriterion("require_name is null");
            return (Criteria) this;
        }

        public Criteria andRequireNameIsNotNull() {
            addCriterion("require_name is not null");
            return (Criteria) this;
        }

        public Criteria andRequireNameEqualTo(String value) {
            addCriterion("require_name =", value, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameNotEqualTo(String value) {
            addCriterion("require_name <>", value, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameGreaterThan(String value) {
            addCriterion("require_name >", value, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameGreaterThanOrEqualTo(String value) {
            addCriterion("require_name >=", value, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameLessThan(String value) {
            addCriterion("require_name <", value, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameLessThanOrEqualTo(String value) {
            addCriterion("require_name <=", value, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameLike(String value) {
            addCriterion("require_name like", value, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameNotLike(String value) {
            addCriterion("require_name not like", value, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameIn(List<String> values) {
            addCriterion("require_name in", values, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameNotIn(List<String> values) {
            addCriterion("require_name not in", values, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameBetween(String value1, String value2) {
            addCriterion("require_name between", value1, value2, "requireName");
            return (Criteria) this;
        }

        public Criteria andRequireNameNotBetween(String value1, String value2) {
            addCriterion("require_name not between", value1, value2, "requireName");
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

        public Criteria andZtProjectIdIsNull() {
            addCriterion("zt_project_id is null");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdIsNotNull() {
            addCriterion("zt_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdEqualTo(Integer value) {
            addCriterion("zt_project_id =", value, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdNotEqualTo(Integer value) {
            addCriterion("zt_project_id <>", value, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdGreaterThan(Integer value) {
            addCriterion("zt_project_id >", value, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("zt_project_id >=", value, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdLessThan(Integer value) {
            addCriterion("zt_project_id <", value, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("zt_project_id <=", value, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdIn(List<Integer> values) {
            addCriterion("zt_project_id in", values, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdNotIn(List<Integer> values) {
            addCriterion("zt_project_id not in", values, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("zt_project_id between", value1, value2, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("zt_project_id not between", value1, value2, "ztProjectId");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameIsNull() {
            addCriterion("zt_project_name is null");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameIsNotNull() {
            addCriterion("zt_project_name is not null");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameEqualTo(String value) {
            addCriterion("zt_project_name =", value, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameNotEqualTo(String value) {
            addCriterion("zt_project_name <>", value, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameGreaterThan(String value) {
            addCriterion("zt_project_name >", value, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("zt_project_name >=", value, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameLessThan(String value) {
            addCriterion("zt_project_name <", value, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameLessThanOrEqualTo(String value) {
            addCriterion("zt_project_name <=", value, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameLike(String value) {
            addCriterion("zt_project_name like", value, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameNotLike(String value) {
            addCriterion("zt_project_name not like", value, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameIn(List<String> values) {
            addCriterion("zt_project_name in", values, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameNotIn(List<String> values) {
            addCriterion("zt_project_name not in", values, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameBetween(String value1, String value2) {
            addCriterion("zt_project_name between", value1, value2, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andZtProjectNameNotBetween(String value1, String value2) {
            addCriterion("zt_project_name not between", value1, value2, "ztProjectName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTmIsNull() {
            addCriterion("create_tm is null");
            return (Criteria) this;
        }

        public Criteria andCreateTmIsNotNull() {
            addCriterion("create_tm is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTmEqualTo(Date value) {
            addCriterion("create_tm =", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotEqualTo(Date value) {
            addCriterion("create_tm <>", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmGreaterThan(Date value) {
            addCriterion("create_tm >", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmGreaterThanOrEqualTo(Date value) {
            addCriterion("create_tm >=", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmLessThan(Date value) {
            addCriterion("create_tm <", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmLessThanOrEqualTo(Date value) {
            addCriterion("create_tm <=", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmIn(List<Date> values) {
            addCriterion("create_tm in", values, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotIn(List<Date> values) {
            addCriterion("create_tm not in", values, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmBetween(Date value1, Date value2) {
            addCriterion("create_tm between", value1, value2, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotBetween(Date value1, Date value2) {
            addCriterion("create_tm not between", value1, value2, "createTm");
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