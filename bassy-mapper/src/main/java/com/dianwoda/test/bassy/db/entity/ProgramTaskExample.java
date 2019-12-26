package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramTaskExample() {
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

        public Criteria andProgramProcessIsNull() {
            addCriterion("program_process is null");
            return (Criteria) this;
        }

        public Criteria andProgramProcessIsNotNull() {
            addCriterion("program_process is not null");
            return (Criteria) this;
        }

        public Criteria andProgramProcessEqualTo(String value) {
            addCriterion("program_process =", value, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessNotEqualTo(String value) {
            addCriterion("program_process <>", value, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessGreaterThan(String value) {
            addCriterion("program_process >", value, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessGreaterThanOrEqualTo(String value) {
            addCriterion("program_process >=", value, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessLessThan(String value) {
            addCriterion("program_process <", value, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessLessThanOrEqualTo(String value) {
            addCriterion("program_process <=", value, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessLike(String value) {
            addCriterion("program_process like", value, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessNotLike(String value) {
            addCriterion("program_process not like", value, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessIn(List<String> values) {
            addCriterion("program_process in", values, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessNotIn(List<String> values) {
            addCriterion("program_process not in", values, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessBetween(String value1, String value2) {
            addCriterion("program_process between", value1, value2, "programProcess");
            return (Criteria) this;
        }

        public Criteria andProgramProcessNotBetween(String value1, String value2) {
            addCriterion("program_process not between", value1, value2, "programProcess");
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

        public Criteria andRequireRelateIsNull() {
            addCriterion("require_relate is null");
            return (Criteria) this;
        }

        public Criteria andRequireRelateIsNotNull() {
            addCriterion("require_relate is not null");
            return (Criteria) this;
        }

        public Criteria andRequireRelateEqualTo(String value) {
            addCriterion("require_relate =", value, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateNotEqualTo(String value) {
            addCriterion("require_relate <>", value, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateGreaterThan(String value) {
            addCriterion("require_relate >", value, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateGreaterThanOrEqualTo(String value) {
            addCriterion("require_relate >=", value, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateLessThan(String value) {
            addCriterion("require_relate <", value, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateLessThanOrEqualTo(String value) {
            addCriterion("require_relate <=", value, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateLike(String value) {
            addCriterion("require_relate like", value, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateNotLike(String value) {
            addCriterion("require_relate not like", value, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateIn(List<String> values) {
            addCriterion("require_relate in", values, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateNotIn(List<String> values) {
            addCriterion("require_relate not in", values, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateBetween(String value1, String value2) {
            addCriterion("require_relate between", value1, value2, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andRequireRelateNotBetween(String value1, String value2) {
            addCriterion("require_relate not between", value1, value2, "requireRelate");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("task_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("task_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("task_name =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("task_name <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("task_name >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_name >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("task_name <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("task_name <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("task_name not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("task_name in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("task_name not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("task_name between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("task_name not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskCodeIsNull() {
            addCriterion("task_code is null");
            return (Criteria) this;
        }

        public Criteria andTaskCodeIsNotNull() {
            addCriterion("task_code is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCodeEqualTo(String value) {
            addCriterion("task_code =", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeNotEqualTo(String value) {
            addCriterion("task_code <>", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeGreaterThan(String value) {
            addCriterion("task_code >", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeGreaterThanOrEqualTo(String value) {
            addCriterion("task_code >=", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeLessThan(String value) {
            addCriterion("task_code <", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeLessThanOrEqualTo(String value) {
            addCriterion("task_code <=", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeLike(String value) {
            addCriterion("task_code like", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeNotLike(String value) {
            addCriterion("task_code not like", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeIn(List<String> values) {
            addCriterion("task_code in", values, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeNotIn(List<String> values) {
            addCriterion("task_code not in", values, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeBetween(String value1, String value2) {
            addCriterion("task_code between", value1, value2, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeNotBetween(String value1, String value2) {
            addCriterion("task_code not between", value1, value2, "taskCode");
            return (Criteria) this;
        }

        public Criteria andStartTmIsNull() {
            addCriterion("start_tm is null");
            return (Criteria) this;
        }

        public Criteria andStartTmIsNotNull() {
            addCriterion("start_tm is not null");
            return (Criteria) this;
        }

        public Criteria andStartTmEqualTo(Date value) {
            addCriterion("start_tm =", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotEqualTo(Date value) {
            addCriterion("start_tm <>", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmGreaterThan(Date value) {
            addCriterion("start_tm >", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmGreaterThanOrEqualTo(Date value) {
            addCriterion("start_tm >=", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmLessThan(Date value) {
            addCriterion("start_tm <", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmLessThanOrEqualTo(Date value) {
            addCriterion("start_tm <=", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmIn(List<Date> values) {
            addCriterion("start_tm in", values, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotIn(List<Date> values) {
            addCriterion("start_tm not in", values, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmBetween(Date value1, Date value2) {
            addCriterion("start_tm between", value1, value2, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotBetween(Date value1, Date value2) {
            addCriterion("start_tm not between", value1, value2, "startTm");
            return (Criteria) this;
        }

        public Criteria andEndTmIsNull() {
            addCriterion("end_tm is null");
            return (Criteria) this;
        }

        public Criteria andEndTmIsNotNull() {
            addCriterion("end_tm is not null");
            return (Criteria) this;
        }

        public Criteria andEndTmEqualTo(Date value) {
            addCriterion("end_tm =", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotEqualTo(Date value) {
            addCriterion("end_tm <>", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmGreaterThan(Date value) {
            addCriterion("end_tm >", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmGreaterThanOrEqualTo(Date value) {
            addCriterion("end_tm >=", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmLessThan(Date value) {
            addCriterion("end_tm <", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmLessThanOrEqualTo(Date value) {
            addCriterion("end_tm <=", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmIn(List<Date> values) {
            addCriterion("end_tm in", values, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotIn(List<Date> values) {
            addCriterion("end_tm not in", values, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmBetween(Date value1, Date value2) {
            addCriterion("end_tm between", value1, value2, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotBetween(Date value1, Date value2) {
            addCriterion("end_tm not between", value1, value2, "endTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmIsNull() {
            addCriterion("actual_start_tm is null");
            return (Criteria) this;
        }

        public Criteria andActualStartTmIsNotNull() {
            addCriterion("actual_start_tm is not null");
            return (Criteria) this;
        }

        public Criteria andActualStartTmEqualTo(Date value) {
            addCriterion("actual_start_tm =", value, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmNotEqualTo(Date value) {
            addCriterion("actual_start_tm <>", value, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmGreaterThan(Date value) {
            addCriterion("actual_start_tm >", value, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmGreaterThanOrEqualTo(Date value) {
            addCriterion("actual_start_tm >=", value, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmLessThan(Date value) {
            addCriterion("actual_start_tm <", value, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmLessThanOrEqualTo(Date value) {
            addCriterion("actual_start_tm <=", value, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmIn(List<Date> values) {
            addCriterion("actual_start_tm in", values, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmNotIn(List<Date> values) {
            addCriterion("actual_start_tm not in", values, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmBetween(Date value1, Date value2) {
            addCriterion("actual_start_tm between", value1, value2, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualStartTmNotBetween(Date value1, Date value2) {
            addCriterion("actual_start_tm not between", value1, value2, "actualStartTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmIsNull() {
            addCriterion("actual_end_tm is null");
            return (Criteria) this;
        }

        public Criteria andActualEndTmIsNotNull() {
            addCriterion("actual_end_tm is not null");
            return (Criteria) this;
        }

        public Criteria andActualEndTmEqualTo(Date value) {
            addCriterion("actual_end_tm =", value, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmNotEqualTo(Date value) {
            addCriterion("actual_end_tm <>", value, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmGreaterThan(Date value) {
            addCriterion("actual_end_tm >", value, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmGreaterThanOrEqualTo(Date value) {
            addCriterion("actual_end_tm >=", value, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmLessThan(Date value) {
            addCriterion("actual_end_tm <", value, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmLessThanOrEqualTo(Date value) {
            addCriterion("actual_end_tm <=", value, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmIn(List<Date> values) {
            addCriterion("actual_end_tm in", values, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmNotIn(List<Date> values) {
            addCriterion("actual_end_tm not in", values, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmBetween(Date value1, Date value2) {
            addCriterion("actual_end_tm between", value1, value2, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andActualEndTmNotBetween(Date value1, Date value2) {
            addCriterion("actual_end_tm not between", value1, value2, "actualEndTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmIsNull() {
            addCriterion("close_tm is null");
            return (Criteria) this;
        }

        public Criteria andCloseTmIsNotNull() {
            addCriterion("close_tm is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTmEqualTo(Date value) {
            addCriterion("close_tm =", value, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmNotEqualTo(Date value) {
            addCriterion("close_tm <>", value, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmGreaterThan(Date value) {
            addCriterion("close_tm >", value, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmGreaterThanOrEqualTo(Date value) {
            addCriterion("close_tm >=", value, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmLessThan(Date value) {
            addCriterion("close_tm <", value, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmLessThanOrEqualTo(Date value) {
            addCriterion("close_tm <=", value, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmIn(List<Date> values) {
            addCriterion("close_tm in", values, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmNotIn(List<Date> values) {
            addCriterion("close_tm not in", values, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmBetween(Date value1, Date value2) {
            addCriterion("close_tm between", value1, value2, "closeTm");
            return (Criteria) this;
        }

        public Criteria andCloseTmNotBetween(Date value1, Date value2) {
            addCriterion("close_tm not between", value1, value2, "closeTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmIsNull() {
            addCriterion("pause_tm is null");
            return (Criteria) this;
        }

        public Criteria andPauseTmIsNotNull() {
            addCriterion("pause_tm is not null");
            return (Criteria) this;
        }

        public Criteria andPauseTmEqualTo(Date value) {
            addCriterion("pause_tm =", value, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmNotEqualTo(Date value) {
            addCriterion("pause_tm <>", value, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmGreaterThan(Date value) {
            addCriterion("pause_tm >", value, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmGreaterThanOrEqualTo(Date value) {
            addCriterion("pause_tm >=", value, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmLessThan(Date value) {
            addCriterion("pause_tm <", value, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmLessThanOrEqualTo(Date value) {
            addCriterion("pause_tm <=", value, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmIn(List<Date> values) {
            addCriterion("pause_tm in", values, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmNotIn(List<Date> values) {
            addCriterion("pause_tm not in", values, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmBetween(Date value1, Date value2) {
            addCriterion("pause_tm between", value1, value2, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andPauseTmNotBetween(Date value1, Date value2) {
            addCriterion("pause_tm not between", value1, value2, "pauseTm");
            return (Criteria) this;
        }

        public Criteria andTesterIsNull() {
            addCriterion("tester is null");
            return (Criteria) this;
        }

        public Criteria andTesterIsNotNull() {
            addCriterion("tester is not null");
            return (Criteria) this;
        }

        public Criteria andTesterEqualTo(String value) {
            addCriterion("tester =", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterNotEqualTo(String value) {
            addCriterion("tester <>", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterGreaterThan(String value) {
            addCriterion("tester >", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterGreaterThanOrEqualTo(String value) {
            addCriterion("tester >=", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterLessThan(String value) {
            addCriterion("tester <", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterLessThanOrEqualTo(String value) {
            addCriterion("tester <=", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterLike(String value) {
            addCriterion("tester like", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterNotLike(String value) {
            addCriterion("tester not like", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterIn(List<String> values) {
            addCriterion("tester in", values, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterNotIn(List<String> values) {
            addCriterion("tester not in", values, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterBetween(String value1, String value2) {
            addCriterion("tester between", value1, value2, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterNotBetween(String value1, String value2) {
            addCriterion("tester not between", value1, value2, "tester");
            return (Criteria) this;
        }

        public Criteria andWithTesterIsNull() {
            addCriterion("with_tester is null");
            return (Criteria) this;
        }

        public Criteria andWithTesterIsNotNull() {
            addCriterion("with_tester is not null");
            return (Criteria) this;
        }

        public Criteria andWithTesterEqualTo(String value) {
            addCriterion("with_tester =", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterNotEqualTo(String value) {
            addCriterion("with_tester <>", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterGreaterThan(String value) {
            addCriterion("with_tester >", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterGreaterThanOrEqualTo(String value) {
            addCriterion("with_tester >=", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterLessThan(String value) {
            addCriterion("with_tester <", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterLessThanOrEqualTo(String value) {
            addCriterion("with_tester <=", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterLike(String value) {
            addCriterion("with_tester like", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterNotLike(String value) {
            addCriterion("with_tester not like", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterIn(List<String> values) {
            addCriterion("with_tester in", values, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterNotIn(List<String> values) {
            addCriterion("with_tester not in", values, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterBetween(String value1, String value2) {
            addCriterion("with_tester between", value1, value2, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterNotBetween(String value1, String value2) {
            addCriterion("with_tester not between", value1, value2, "withTester");
            return (Criteria) this;
        }

        public Criteria andExpectHourIsNull() {
            addCriterion("expect_hour is null");
            return (Criteria) this;
        }

        public Criteria andExpectHourIsNotNull() {
            addCriterion("expect_hour is not null");
            return (Criteria) this;
        }

        public Criteria andExpectHourEqualTo(Float value) {
            addCriterion("expect_hour =", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourNotEqualTo(Float value) {
            addCriterion("expect_hour <>", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourGreaterThan(Float value) {
            addCriterion("expect_hour >", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourGreaterThanOrEqualTo(Float value) {
            addCriterion("expect_hour >=", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourLessThan(Float value) {
            addCriterion("expect_hour <", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourLessThanOrEqualTo(Float value) {
            addCriterion("expect_hour <=", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourIn(List<Float> values) {
            addCriterion("expect_hour in", values, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourNotIn(List<Float> values) {
            addCriterion("expect_hour not in", values, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourBetween(Float value1, Float value2) {
            addCriterion("expect_hour between", value1, value2, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourNotBetween(Float value1, Float value2) {
            addCriterion("expect_hour not between", value1, value2, "expectHour");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
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

        public Criteria andZtTaskIdIsNull() {
            addCriterion("zt_task_id is null");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdIsNotNull() {
            addCriterion("zt_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdEqualTo(Integer value) {
            addCriterion("zt_task_id =", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdNotEqualTo(Integer value) {
            addCriterion("zt_task_id <>", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdGreaterThan(Integer value) {
            addCriterion("zt_task_id >", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("zt_task_id >=", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdLessThan(Integer value) {
            addCriterion("zt_task_id <", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("zt_task_id <=", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdIn(List<Integer> values) {
            addCriterion("zt_task_id in", values, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdNotIn(List<Integer> values) {
            addCriterion("zt_task_id not in", values, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("zt_task_id between", value1, value2, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("zt_task_id not between", value1, value2, "ztTaskId");
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

        public Criteria andPercentIsNull() {
            addCriterion("`percent` is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("`percent` is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(Integer value) {
            addCriterion("`percent` =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(Integer value) {
            addCriterion("`percent` <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(Integer value) {
            addCriterion("`percent` >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("`percent` >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(Integer value) {
            addCriterion("`percent` <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(Integer value) {
            addCriterion("`percent` <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<Integer> values) {
            addCriterion("`percent` in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<Integer> values) {
            addCriterion("`percent` not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(Integer value1, Integer value2) {
            addCriterion("`percent` between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("`percent` not between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andActualHourIsNull() {
            addCriterion("actual_hour is null");
            return (Criteria) this;
        }

        public Criteria andActualHourIsNotNull() {
            addCriterion("actual_hour is not null");
            return (Criteria) this;
        }

        public Criteria andActualHourEqualTo(Float value) {
            addCriterion("actual_hour =", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourNotEqualTo(Float value) {
            addCriterion("actual_hour <>", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourGreaterThan(Float value) {
            addCriterion("actual_hour >", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourGreaterThanOrEqualTo(Float value) {
            addCriterion("actual_hour >=", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourLessThan(Float value) {
            addCriterion("actual_hour <", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourLessThanOrEqualTo(Float value) {
            addCriterion("actual_hour <=", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourIn(List<Float> values) {
            addCriterion("actual_hour in", values, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourNotIn(List<Float> values) {
            addCriterion("actual_hour not in", values, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourBetween(Float value1, Float value2) {
            addCriterion("actual_hour between", value1, value2, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourNotBetween(Float value1, Float value2) {
            addCriterion("actual_hour not between", value1, value2, "actualHour");
            return (Criteria) this;
        }

        public Criteria andExcludeDateIsNull() {
            addCriterion("exclude_date is null");
            return (Criteria) this;
        }

        public Criteria andExcludeDateIsNotNull() {
            addCriterion("exclude_date is not null");
            return (Criteria) this;
        }

        public Criteria andExcludeDateEqualTo(String value) {
            addCriterion("exclude_date =", value, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateNotEqualTo(String value) {
            addCriterion("exclude_date <>", value, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateGreaterThan(String value) {
            addCriterion("exclude_date >", value, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateGreaterThanOrEqualTo(String value) {
            addCriterion("exclude_date >=", value, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateLessThan(String value) {
            addCriterion("exclude_date <", value, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateLessThanOrEqualTo(String value) {
            addCriterion("exclude_date <=", value, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateLike(String value) {
            addCriterion("exclude_date like", value, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateNotLike(String value) {
            addCriterion("exclude_date not like", value, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateIn(List<String> values) {
            addCriterion("exclude_date in", values, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateNotIn(List<String> values) {
            addCriterion("exclude_date not in", values, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateBetween(String value1, String value2) {
            addCriterion("exclude_date between", value1, value2, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andExcludeDateNotBetween(String value1, String value2) {
            addCriterion("exclude_date not between", value1, value2, "excludeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateIsNull() {
            addCriterion("include_date is null");
            return (Criteria) this;
        }

        public Criteria andIncludeDateIsNotNull() {
            addCriterion("include_date is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeDateEqualTo(String value) {
            addCriterion("include_date =", value, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateNotEqualTo(String value) {
            addCriterion("include_date <>", value, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateGreaterThan(String value) {
            addCriterion("include_date >", value, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateGreaterThanOrEqualTo(String value) {
            addCriterion("include_date >=", value, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateLessThan(String value) {
            addCriterion("include_date <", value, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateLessThanOrEqualTo(String value) {
            addCriterion("include_date <=", value, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateLike(String value) {
            addCriterion("include_date like", value, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateNotLike(String value) {
            addCriterion("include_date not like", value, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateIn(List<String> values) {
            addCriterion("include_date in", values, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateNotIn(List<String> values) {
            addCriterion("include_date not in", values, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateBetween(String value1, String value2) {
            addCriterion("include_date between", value1, value2, "includeDate");
            return (Criteria) this;
        }

        public Criteria andIncludeDateNotBetween(String value1, String value2) {
            addCriterion("include_date not between", value1, value2, "includeDate");
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