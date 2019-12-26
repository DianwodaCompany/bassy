package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramExample() {
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
        //增加方法，支持数据或查询
        public Criteria andOrQueryAll(String creator,String persons){
            if(creator !=null && persons !=null){
                addCriterion("(creator = \""+creator+"\" or persons like  \""+"%"+persons+"%"+"\")");
            }
            return (Criteria)this;
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

        public Criteria andProgramNameIsNull() {
            addCriterion("program_name is null");
            return (Criteria) this;
        }

        public Criteria andProgramNameIsNotNull() {
            addCriterion("program_name is not null");
            return (Criteria) this;
        }

        public Criteria andProgramNameEqualTo(String value) {
            addCriterion("program_name =", value, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameNotEqualTo(String value) {
            addCriterion("program_name <>", value, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameGreaterThan(String value) {
            addCriterion("program_name >", value, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameGreaterThanOrEqualTo(String value) {
            addCriterion("program_name >=", value, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameLessThan(String value) {
            addCriterion("program_name <", value, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameLessThanOrEqualTo(String value) {
            addCriterion("program_name <=", value, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameLike(String value) {
            addCriterion("program_name like", value, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameNotLike(String value) {
            addCriterion("program_name not like", value, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameIn(List<String> values) {
            addCriterion("program_name in", values, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameNotIn(List<String> values) {
            addCriterion("program_name not in", values, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameBetween(String value1, String value2) {
            addCriterion("program_name between", value1, value2, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramNameNotBetween(String value1, String value2) {
            addCriterion("program_name not between", value1, value2, "programName");
            return (Criteria) this;
        }

        public Criteria andProgramTypeIsNull() {
            addCriterion("program_type is null");
            return (Criteria) this;
        }

        public Criteria andProgramTypeIsNotNull() {
            addCriterion("program_type is not null");
            return (Criteria) this;
        }

        public Criteria andProgramTypeEqualTo(String value) {
            addCriterion("program_type =", value, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeNotEqualTo(String value) {
            addCriterion("program_type <>", value, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeGreaterThan(String value) {
            addCriterion("program_type >", value, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeGreaterThanOrEqualTo(String value) {
            addCriterion("program_type >=", value, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeLessThan(String value) {
            addCriterion("program_type <", value, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeLessThanOrEqualTo(String value) {
            addCriterion("program_type <=", value, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeLike(String value) {
            addCriterion("program_type like", value, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeNotLike(String value) {
            addCriterion("program_type not like", value, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeIn(List<String> values) {
            addCriterion("program_type in", values, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeNotIn(List<String> values) {
            addCriterion("program_type not in", values, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeBetween(String value1, String value2) {
            addCriterion("program_type between", value1, value2, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramTypeNotBetween(String value1, String value2) {
            addCriterion("program_type not between", value1, value2, "programType");
            return (Criteria) this;
        }

        public Criteria andProgramModuleIsNull() {
            addCriterion("program_module is null");
            return (Criteria) this;
        }

        public Criteria andProgramModuleIsNotNull() {
            addCriterion("program_module is not null");
            return (Criteria) this;
        }

        public Criteria andProgramModuleEqualTo(Integer value) {
            addCriterion("program_module =", value, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleNotEqualTo(Integer value) {
            addCriterion("program_module <>", value, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleGreaterThan(Integer value) {
            addCriterion("program_module >", value, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("program_module >=", value, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleLessThan(Integer value) {
            addCriterion("program_module <", value, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleLessThanOrEqualTo(Integer value) {
            addCriterion("program_module <=", value, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleIn(List<Integer> values) {
            addCriterion("program_module in", values, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleNotIn(List<Integer> values) {
            addCriterion("program_module not in", values, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleBetween(Integer value1, Integer value2) {
            addCriterion("program_module between", value1, value2, "programModule");
            return (Criteria) this;
        }

        public Criteria andProgramModuleNotBetween(Integer value1, Integer value2) {
            addCriterion("program_module not between", value1, value2, "programModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleIsNull() {
            addCriterion("process_module is null");
            return (Criteria) this;
        }

        public Criteria andProcessModuleIsNotNull() {
            addCriterion("process_module is not null");
            return (Criteria) this;
        }

        public Criteria andProcessModuleEqualTo(Integer value) {
            addCriterion("process_module =", value, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleNotEqualTo(Integer value) {
            addCriterion("process_module <>", value, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleGreaterThan(Integer value) {
            addCriterion("process_module >", value, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_module >=", value, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleLessThan(Integer value) {
            addCriterion("process_module <", value, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleLessThanOrEqualTo(Integer value) {
            addCriterion("process_module <=", value, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleIn(List<Integer> values) {
            addCriterion("process_module in", values, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleNotIn(List<Integer> values) {
            addCriterion("process_module not in", values, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleBetween(Integer value1, Integer value2) {
            addCriterion("process_module between", value1, value2, "processModule");
            return (Criteria) this;
        }

        public Criteria andProcessModuleNotBetween(Integer value1, Integer value2) {
            addCriterion("process_module not between", value1, value2, "processModule");
            return (Criteria) this;
        }

        public Criteria andPersonsIsNull() {
            addCriterion("persons is null");
            return (Criteria) this;
        }

        public Criteria andPersonsIsNotNull() {
            addCriterion("persons is not null");
            return (Criteria) this;
        }

        public Criteria andPersonsEqualTo(String value) {
            addCriterion("persons =", value, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsNotEqualTo(String value) {
            addCriterion("persons <>", value, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsGreaterThan(String value) {
            addCriterion("persons >", value, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsGreaterThanOrEqualTo(String value) {
            addCriterion("persons >=", value, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsLessThan(String value) {
            addCriterion("persons <", value, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsLessThanOrEqualTo(String value) {
            addCriterion("persons <=", value, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsLike(String value) {
            addCriterion("persons like", value, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsNotLike(String value) {
            addCriterion("persons not like", value, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsIn(List<String> values) {
            addCriterion("persons in", values, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsNotIn(List<String> values) {
            addCriterion("persons not in", values, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsBetween(String value1, String value2) {
            addCriterion("persons between", value1, value2, "persons");
            return (Criteria) this;
        }

        public Criteria andPersonsNotBetween(String value1, String value2) {
            addCriterion("persons not between", value1, value2, "persons");
            return (Criteria) this;
        }

        public Criteria andCoreNodesIsNull() {
            addCriterion("core_nodes is null");
            return (Criteria) this;
        }

        public Criteria andCoreNodesIsNotNull() {
            addCriterion("core_nodes is not null");
            return (Criteria) this;
        }

        public Criteria andCoreNodesEqualTo(String value) {
            addCriterion("core_nodes =", value, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesNotEqualTo(String value) {
            addCriterion("core_nodes <>", value, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesGreaterThan(String value) {
            addCriterion("core_nodes >", value, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesGreaterThanOrEqualTo(String value) {
            addCriterion("core_nodes >=", value, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesLessThan(String value) {
            addCriterion("core_nodes <", value, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesLessThanOrEqualTo(String value) {
            addCriterion("core_nodes <=", value, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesLike(String value) {
            addCriterion("core_nodes like", value, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesNotLike(String value) {
            addCriterion("core_nodes not like", value, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesIn(List<String> values) {
            addCriterion("core_nodes in", values, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesNotIn(List<String> values) {
            addCriterion("core_nodes not in", values, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesBetween(String value1, String value2) {
            addCriterion("core_nodes between", value1, value2, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andCoreNodesNotBetween(String value1, String value2) {
            addCriterion("core_nodes not between", value1, value2, "coreNodes");
            return (Criteria) this;
        }

        public Criteria andRequiresIsNull() {
            addCriterion("requires is null");
            return (Criteria) this;
        }

        public Criteria andRequiresIsNotNull() {
            addCriterion("requires is not null");
            return (Criteria) this;
        }

        public Criteria andRequiresEqualTo(String value) {
            addCriterion("requires =", value, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresNotEqualTo(String value) {
            addCriterion("requires <>", value, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresGreaterThan(String value) {
            addCriterion("requires >", value, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresGreaterThanOrEqualTo(String value) {
            addCriterion("requires >=", value, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresLessThan(String value) {
            addCriterion("requires <", value, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresLessThanOrEqualTo(String value) {
            addCriterion("requires <=", value, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresLike(String value) {
            addCriterion("requires like", value, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresNotLike(String value) {
            addCriterion("requires not like", value, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresIn(List<String> values) {
            addCriterion("requires in", values, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresNotIn(List<String> values) {
            addCriterion("requires not in", values, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresBetween(String value1, String value2) {
            addCriterion("requires between", value1, value2, "requires");
            return (Criteria) this;
        }

        public Criteria andRequiresNotBetween(String value1, String value2) {
            addCriterion("requires not between", value1, value2, "requires");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andWorkIdIsNull() {
            addCriterion("work_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkIdIsNotNull() {
            addCriterion("work_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkIdEqualTo(String value) {
            addCriterion("work_id =", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotEqualTo(String value) {
            addCriterion("work_id <>", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdGreaterThan(String value) {
            addCriterion("work_id >", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdGreaterThanOrEqualTo(String value) {
            addCriterion("work_id >=", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLessThan(String value) {
            addCriterion("work_id <", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLessThanOrEqualTo(String value) {
            addCriterion("work_id <=", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLike(String value) {
            addCriterion("work_id like", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotLike(String value) {
            addCriterion("work_id not like", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdIn(List<String> values) {
            addCriterion("work_id in", values, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotIn(List<String> values) {
            addCriterion("work_id not in", values, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdBetween(String value1, String value2) {
            addCriterion("work_id between", value1, value2, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotBetween(String value1, String value2) {
            addCriterion("work_id not between", value1, value2, "workId");
            return (Criteria) this;
        }

        public Criteria andPressingReasonIsNull() {
            addCriterion("pressing_reason is null");
            return (Criteria) this;
        }

        public Criteria andPressingReasonIsNotNull() {
            addCriterion("pressing_reason is not null");
            return (Criteria) this;
        }

        public Criteria andPressingReasonEqualTo(String value) {
            addCriterion("pressing_reason =", value, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonNotEqualTo(String value) {
            addCriterion("pressing_reason <>", value, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonGreaterThan(String value) {
            addCriterion("pressing_reason >", value, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonGreaterThanOrEqualTo(String value) {
            addCriterion("pressing_reason >=", value, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonLessThan(String value) {
            addCriterion("pressing_reason <", value, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonLessThanOrEqualTo(String value) {
            addCriterion("pressing_reason <=", value, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonLike(String value) {
            addCriterion("pressing_reason like", value, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonNotLike(String value) {
            addCriterion("pressing_reason not like", value, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonIn(List<String> values) {
            addCriterion("pressing_reason in", values, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonNotIn(List<String> values) {
            addCriterion("pressing_reason not in", values, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonBetween(String value1, String value2) {
            addCriterion("pressing_reason between", value1, value2, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andPressingReasonNotBetween(String value1, String value2) {
            addCriterion("pressing_reason not between", value1, value2, "pressingReason");
            return (Criteria) this;
        }

        public Criteria andIsCheckIsNull() {
            addCriterion("is_check is null");
            return (Criteria) this;
        }

        public Criteria andIsCheckIsNotNull() {
            addCriterion("is_check is not null");
            return (Criteria) this;
        }

        public Criteria andIsCheckEqualTo(String value) {
            addCriterion("is_check =", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckNotEqualTo(String value) {
            addCriterion("is_check <>", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckGreaterThan(String value) {
            addCriterion("is_check >", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckGreaterThanOrEqualTo(String value) {
            addCriterion("is_check >=", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckLessThan(String value) {
            addCriterion("is_check <", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckLessThanOrEqualTo(String value) {
            addCriterion("is_check <=", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckLike(String value) {
            addCriterion("is_check like", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckNotLike(String value) {
            addCriterion("is_check not like", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckIn(List<String> values) {
            addCriterion("is_check in", values, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckNotIn(List<String> values) {
            addCriterion("is_check not in", values, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckBetween(String value1, String value2) {
            addCriterion("is_check between", value1, value2, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckNotBetween(String value1, String value2) {
            addCriterion("is_check not between", value1, value2, "isCheck");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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

        public Criteria andInnerProjectTypeIsNull() {
            addCriterion("inner_project_type is null");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeIsNotNull() {
            addCriterion("inner_project_type is not null");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeEqualTo(String value) {
            addCriterion("inner_project_type =", value, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeNotEqualTo(String value) {
            addCriterion("inner_project_type <>", value, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeGreaterThan(String value) {
            addCriterion("inner_project_type >", value, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("inner_project_type >=", value, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeLessThan(String value) {
            addCriterion("inner_project_type <", value, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeLessThanOrEqualTo(String value) {
            addCriterion("inner_project_type <=", value, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeLike(String value) {
            addCriterion("inner_project_type like", value, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeNotLike(String value) {
            addCriterion("inner_project_type not like", value, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeIn(List<String> values) {
            addCriterion("inner_project_type in", values, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeNotIn(List<String> values) {
            addCriterion("inner_project_type not in", values, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeBetween(String value1, String value2) {
            addCriterion("inner_project_type between", value1, value2, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andInnerProjectTypeNotBetween(String value1, String value2) {
            addCriterion("inner_project_type not between", value1, value2, "innerProjectType");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourIsNull() {
            addCriterion("each_task_work_hour is null");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourIsNotNull() {
            addCriterion("each_task_work_hour is not null");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourEqualTo(Float value) {
            addCriterion("each_task_work_hour =", value, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourNotEqualTo(Float value) {
            addCriterion("each_task_work_hour <>", value, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourGreaterThan(Float value) {
            addCriterion("each_task_work_hour >", value, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourGreaterThanOrEqualTo(Float value) {
            addCriterion("each_task_work_hour >=", value, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourLessThan(Float value) {
            addCriterion("each_task_work_hour <", value, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourLessThanOrEqualTo(Float value) {
            addCriterion("each_task_work_hour <=", value, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourIn(List<Float> values) {
            addCriterion("each_task_work_hour in", values, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourNotIn(List<Float> values) {
            addCriterion("each_task_work_hour not in", values, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourBetween(Float value1, Float value2) {
            addCriterion("each_task_work_hour between", value1, value2, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andEachTaskWorkHourNotBetween(Float value1, Float value2) {
            addCriterion("each_task_work_hour not between", value1, value2, "eachTaskWorkHour");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumIsNull() {
            addCriterion("daily_task_num is null");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumIsNotNull() {
            addCriterion("daily_task_num is not null");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumEqualTo(Integer value) {
            addCriterion("daily_task_num =", value, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumNotEqualTo(Integer value) {
            addCriterion("daily_task_num <>", value, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumGreaterThan(Integer value) {
            addCriterion("daily_task_num >", value, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("daily_task_num >=", value, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumLessThan(Integer value) {
            addCriterion("daily_task_num <", value, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumLessThanOrEqualTo(Integer value) {
            addCriterion("daily_task_num <=", value, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumIn(List<Integer> values) {
            addCriterion("daily_task_num in", values, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumNotIn(List<Integer> values) {
            addCriterion("daily_task_num not in", values, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumBetween(Integer value1, Integer value2) {
            addCriterion("daily_task_num between", value1, value2, "dailyTaskNum");
            return (Criteria) this;
        }

        public Criteria andDailyTaskNumNotBetween(Integer value1, Integer value2) {
            addCriterion("daily_task_num not between", value1, value2, "dailyTaskNum");
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