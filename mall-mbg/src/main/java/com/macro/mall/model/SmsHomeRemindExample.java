package com.macro.mall.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsHomeRemindExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<SmsHomeRemindExample.Criteria> oredCriteria;

    public SmsHomeRemindExample() {
        oredCriteria = new ArrayList<SmsHomeRemindExample.Criteria>();
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

    public List<SmsHomeRemindExample.Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(SmsHomeRemindExample.Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public SmsHomeRemindExample.Criteria or() {
        SmsHomeRemindExample.Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public SmsHomeRemindExample.Criteria createCriteria() {
        SmsHomeRemindExample.Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected SmsHomeRemindExample.Criteria createCriteriaInternal() {
        SmsHomeRemindExample.Criteria criteria = new SmsHomeRemindExample.Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<SmsHomeRemindExample.Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<SmsHomeRemindExample.Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<SmsHomeRemindExample.Criterion> getAllCriteria() {
            return criteria;
        }

        public List<SmsHomeRemindExample.Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new SmsHomeRemindExample.Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new SmsHomeRemindExample.Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new SmsHomeRemindExample.Criterion(condition, value1, value2));
        }

        public SmsHomeRemindExample.Criteria andIdIsNull() {
            addCriterion("id is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameIsNull() {
            addCriterion("name is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountIsNull() {
            addCriterion("click_count is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountIsNotNull() {
            addCriterion("click_count is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountEqualTo(Integer value) {
            addCriterion("click_count =", value, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountNotEqualTo(Integer value) {
            addCriterion("click_count <>", value, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountGreaterThan(Integer value) {
            addCriterion("click_count >", value, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_count >=", value, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountLessThan(Integer value) {
            addCriterion("click_count <", value, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountLessThanOrEqualTo(Integer value) {
            addCriterion("click_count <=", value, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountIn(List<Integer> values) {
            addCriterion("click_count in", values, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountNotIn(List<Integer> values) {
            addCriterion("click_count not in", values, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountBetween(Integer value1, Integer value2) {
            addCriterion("click_count between", value1, value2, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andClickCountNotBetween(Integer value1, Integer value2) {
            addCriterion("click_count not between", value1, value2, "clickCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountIsNull() {
            addCriterion("order_count is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountIsNotNull() {
            addCriterion("order_count is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountEqualTo(Integer value) {
            addCriterion("order_count =", value, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountNotEqualTo(Integer value) {
            addCriterion("order_count <>", value, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountGreaterThan(Integer value) {
            addCriterion("order_count >", value, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_count >=", value, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountLessThan(Integer value) {
            addCriterion("order_count <", value, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("order_count <=", value, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountIn(List<Integer> values) {
            addCriterion("order_count in", values, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountNotIn(List<Integer> values) {
            addCriterion("order_count not in", values, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("order_count between", value1, value2, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("order_count not between", value1, value2, "orderCount");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }

        public SmsHomeRemindExample.Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (SmsHomeRemindExample.Criteria) this;
        }
    }

    public static class Criteria extends SmsHomeRemindExample.GeneratedCriteria {

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
