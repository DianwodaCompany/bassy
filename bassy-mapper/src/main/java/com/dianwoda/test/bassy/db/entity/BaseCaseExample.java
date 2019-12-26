package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseCaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseCaseExample() {
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

        public Criteria andProductIsNull() {
            addCriterion("product is null");
            return (Criteria) this;
        }

        public Criteria andProductIsNotNull() {
            addCriterion("product is not null");
            return (Criteria) this;
        }

        public Criteria andProductEqualTo(Integer value) {
            addCriterion("product =", value, "product");
            return (Criteria) this;
        }

        public Criteria andProductNotEqualTo(Integer value) {
            addCriterion("product <>", value, "product");
            return (Criteria) this;
        }

        public Criteria andProductGreaterThan(Integer value) {
            addCriterion("product >", value, "product");
            return (Criteria) this;
        }

        public Criteria andProductGreaterThanOrEqualTo(Integer value) {
            addCriterion("product >=", value, "product");
            return (Criteria) this;
        }

        public Criteria andProductLessThan(Integer value) {
            addCriterion("product <", value, "product");
            return (Criteria) this;
        }

        public Criteria andProductLessThanOrEqualTo(Integer value) {
            addCriterion("product <=", value, "product");
            return (Criteria) this;
        }

        public Criteria andProductIn(List<Integer> values) {
            addCriterion("product in", values, "product");
            return (Criteria) this;
        }

        public Criteria andProductNotIn(List<Integer> values) {
            addCriterion("product not in", values, "product");
            return (Criteria) this;
        }

        public Criteria andProductBetween(Integer value1, Integer value2) {
            addCriterion("product between", value1, value2, "product");
            return (Criteria) this;
        }

        public Criteria andProductNotBetween(Integer value1, Integer value2) {
            addCriterion("product not between", value1, value2, "product");
            return (Criteria) this;
        }

        public Criteria andModuleIsNull() {
            addCriterion("`module` is null");
            return (Criteria) this;
        }

        public Criteria andModuleIsNotNull() {
            addCriterion("`module` is not null");
            return (Criteria) this;
        }

        public Criteria andModuleEqualTo(Integer value) {
            addCriterion("`module` =", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotEqualTo(Integer value) {
            addCriterion("`module` <>", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThan(Integer value) {
            addCriterion("`module` >", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("`module` >=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThan(Integer value) {
            addCriterion("`module` <", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThanOrEqualTo(Integer value) {
            addCriterion("`module` <=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleIn(List<Integer> values) {
            addCriterion("`module` in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotIn(List<Integer> values) {
            addCriterion("`module` not in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleBetween(Integer value1, Integer value2) {
            addCriterion("`module` between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotBetween(Integer value1, Integer value2) {
            addCriterion("`module` not between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andRequireIsNull() {
            addCriterion("`require` is null");
            return (Criteria) this;
        }

        public Criteria andRequireIsNotNull() {
            addCriterion("`require` is not null");
            return (Criteria) this;
        }

        public Criteria andRequireEqualTo(Integer value) {
            addCriterion("`require` =", value, "require");
            return (Criteria) this;
        }

        public Criteria andRequireNotEqualTo(Integer value) {
            addCriterion("`require` <>", value, "require");
            return (Criteria) this;
        }

        public Criteria andRequireGreaterThan(Integer value) {
            addCriterion("`require` >", value, "require");
            return (Criteria) this;
        }

        public Criteria andRequireGreaterThanOrEqualTo(Integer value) {
            addCriterion("`require` >=", value, "require");
            return (Criteria) this;
        }

        public Criteria andRequireLessThan(Integer value) {
            addCriterion("`require` <", value, "require");
            return (Criteria) this;
        }

        public Criteria andRequireLessThanOrEqualTo(Integer value) {
            addCriterion("`require` <=", value, "require");
            return (Criteria) this;
        }

        public Criteria andRequireIn(List<Integer> values) {
            addCriterion("`require` in", values, "require");
            return (Criteria) this;
        }

        public Criteria andRequireNotIn(List<Integer> values) {
            addCriterion("`require` not in", values, "require");
            return (Criteria) this;
        }

        public Criteria andRequireBetween(Integer value1, Integer value2) {
            addCriterion("`require` between", value1, value2, "require");
            return (Criteria) this;
        }

        public Criteria andRequireNotBetween(Integer value1, Integer value2) {
            addCriterion("`require` not between", value1, value2, "require");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andPriIsNull() {
            addCriterion("pri is null");
            return (Criteria) this;
        }

        public Criteria andPriIsNotNull() {
            addCriterion("pri is not null");
            return (Criteria) this;
        }

        public Criteria andPriEqualTo(Byte value) {
            addCriterion("pri =", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotEqualTo(Byte value) {
            addCriterion("pri <>", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriGreaterThan(Byte value) {
            addCriterion("pri >", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriGreaterThanOrEqualTo(Byte value) {
            addCriterion("pri >=", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLessThan(Byte value) {
            addCriterion("pri <", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLessThanOrEqualTo(Byte value) {
            addCriterion("pri <=", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriIn(List<Byte> values) {
            addCriterion("pri in", values, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotIn(List<Byte> values) {
            addCriterion("pri not in", values, "pri");
            return (Criteria) this;
        }

        public Criteria andPriBetween(Byte value1, Byte value2) {
            addCriterion("pri between", value1, value2, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotBetween(Byte value1, Byte value2) {
            addCriterion("pri not between", value1, value2, "pri");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`type` not between", value1, value2, "type");
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

        public Criteria andLastEditedByIsNull() {
            addCriterion("last_edited_by is null");
            return (Criteria) this;
        }

        public Criteria andLastEditedByIsNotNull() {
            addCriterion("last_edited_by is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditedByEqualTo(String value) {
            addCriterion("last_edited_by =", value, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByNotEqualTo(String value) {
            addCriterion("last_edited_by <>", value, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByGreaterThan(String value) {
            addCriterion("last_edited_by >", value, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByGreaterThanOrEqualTo(String value) {
            addCriterion("last_edited_by >=", value, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByLessThan(String value) {
            addCriterion("last_edited_by <", value, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByLessThanOrEqualTo(String value) {
            addCriterion("last_edited_by <=", value, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByLike(String value) {
            addCriterion("last_edited_by like", value, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByNotLike(String value) {
            addCriterion("last_edited_by not like", value, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByIn(List<String> values) {
            addCriterion("last_edited_by in", values, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByNotIn(List<String> values) {
            addCriterion("last_edited_by not in", values, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByBetween(String value1, String value2) {
            addCriterion("last_edited_by between", value1, value2, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedByNotBetween(String value1, String value2) {
            addCriterion("last_edited_by not between", value1, value2, "lastEditedBy");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateIsNull() {
            addCriterion("last_edited_date is null");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateIsNotNull() {
            addCriterion("last_edited_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateEqualTo(Date value) {
            addCriterion("last_edited_date =", value, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateNotEqualTo(Date value) {
            addCriterion("last_edited_date <>", value, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateGreaterThan(Date value) {
            addCriterion("last_edited_date >", value, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_edited_date >=", value, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateLessThan(Date value) {
            addCriterion("last_edited_date <", value, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateLessThanOrEqualTo(Date value) {
            addCriterion("last_edited_date <=", value, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateIn(List<Date> values) {
            addCriterion("last_edited_date in", values, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateNotIn(List<Date> values) {
            addCriterion("last_edited_date not in", values, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateBetween(Date value1, Date value2) {
            addCriterion("last_edited_date between", value1, value2, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andLastEditedDateNotBetween(Date value1, Date value2) {
            addCriterion("last_edited_date not between", value1, value2, "lastEditedDate");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Byte value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Byte value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Byte value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Byte value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Byte value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Byte value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Byte> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Byte> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Byte value1, Byte value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Byte value1, Byte value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andParentCaseIsNull() {
            addCriterion("parent_case is null");
            return (Criteria) this;
        }

        public Criteria andParentCaseIsNotNull() {
            addCriterion("parent_case is not null");
            return (Criteria) this;
        }

        public Criteria andParentCaseEqualTo(Integer value) {
            addCriterion("parent_case =", value, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseNotEqualTo(Integer value) {
            addCriterion("parent_case <>", value, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseGreaterThan(Integer value) {
            addCriterion("parent_case >", value, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_case >=", value, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseLessThan(Integer value) {
            addCriterion("parent_case <", value, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseLessThanOrEqualTo(Integer value) {
            addCriterion("parent_case <=", value, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseIn(List<Integer> values) {
            addCriterion("parent_case in", values, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseNotIn(List<Integer> values) {
            addCriterion("parent_case not in", values, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseBetween(Integer value1, Integer value2) {
            addCriterion("parent_case between", value1, value2, "parentCase");
            return (Criteria) this;
        }

        public Criteria andParentCaseNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_case not between", value1, value2, "parentCase");
            return (Criteria) this;
        }

        public Criteria andFamilyIsNull() {
            addCriterion("family is null");
            return (Criteria) this;
        }

        public Criteria andFamilyIsNotNull() {
            addCriterion("family is not null");
            return (Criteria) this;
        }

        public Criteria andFamilyEqualTo(Byte value) {
            addCriterion("family =", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyNotEqualTo(Byte value) {
            addCriterion("family <>", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyGreaterThan(Byte value) {
            addCriterion("family >", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyGreaterThanOrEqualTo(Byte value) {
            addCriterion("family >=", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyLessThan(Byte value) {
            addCriterion("family <", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyLessThanOrEqualTo(Byte value) {
            addCriterion("family <=", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyIn(List<Byte> values) {
            addCriterion("family in", values, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyNotIn(List<Byte> values) {
            addCriterion("family not in", values, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyBetween(Byte value1, Byte value2) {
            addCriterion("family between", value1, value2, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyNotBetween(Byte value1, Byte value2) {
            addCriterion("family not between", value1, value2, "family");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusIsNull() {
            addCriterion("execute_status is null");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusIsNotNull() {
            addCriterion("execute_status is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusEqualTo(Byte value) {
            addCriterion("execute_status =", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotEqualTo(Byte value) {
            addCriterion("execute_status <>", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusGreaterThan(Byte value) {
            addCriterion("execute_status >", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("execute_status >=", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusLessThan(Byte value) {
            addCriterion("execute_status <", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusLessThanOrEqualTo(Byte value) {
            addCriterion("execute_status <=", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusIn(List<Byte> values) {
            addCriterion("execute_status in", values, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotIn(List<Byte> values) {
            addCriterion("execute_status not in", values, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusBetween(Byte value1, Byte value2) {
            addCriterion("execute_status between", value1, value2, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("execute_status not between", value1, value2, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andEditedIsNull() {
            addCriterion("edited is null");
            return (Criteria) this;
        }

        public Criteria andEditedIsNotNull() {
            addCriterion("edited is not null");
            return (Criteria) this;
        }

        public Criteria andEditedEqualTo(Byte value) {
            addCriterion("edited =", value, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedNotEqualTo(Byte value) {
            addCriterion("edited <>", value, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedGreaterThan(Byte value) {
            addCriterion("edited >", value, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedGreaterThanOrEqualTo(Byte value) {
            addCriterion("edited >=", value, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedLessThan(Byte value) {
            addCriterion("edited <", value, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedLessThanOrEqualTo(Byte value) {
            addCriterion("edited <=", value, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedIn(List<Byte> values) {
            addCriterion("edited in", values, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedNotIn(List<Byte> values) {
            addCriterion("edited not in", values, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedBetween(Byte value1, Byte value2) {
            addCriterion("edited between", value1, value2, "edited");
            return (Criteria) this;
        }

        public Criteria andEditedNotBetween(Byte value1, Byte value2) {
            addCriterion("edited not between", value1, value2, "edited");
            return (Criteria) this;
        }

        public Criteria andPushedIsNull() {
            addCriterion("pushed is null");
            return (Criteria) this;
        }

        public Criteria andPushedIsNotNull() {
            addCriterion("pushed is not null");
            return (Criteria) this;
        }

        public Criteria andPushedEqualTo(Byte value) {
            addCriterion("pushed =", value, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedNotEqualTo(Byte value) {
            addCriterion("pushed <>", value, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedGreaterThan(Byte value) {
            addCriterion("pushed >", value, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedGreaterThanOrEqualTo(Byte value) {
            addCriterion("pushed >=", value, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedLessThan(Byte value) {
            addCriterion("pushed <", value, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedLessThanOrEqualTo(Byte value) {
            addCriterion("pushed <=", value, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedIn(List<Byte> values) {
            addCriterion("pushed in", values, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedNotIn(List<Byte> values) {
            addCriterion("pushed not in", values, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedBetween(Byte value1, Byte value2) {
            addCriterion("pushed between", value1, value2, "pushed");
            return (Criteria) this;
        }

        public Criteria andPushedNotBetween(Byte value1, Byte value2) {
            addCriterion("pushed not between", value1, value2, "pushed");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Byte value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Byte value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Byte value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Byte value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Byte> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Byte> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Byte value1, Byte value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
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