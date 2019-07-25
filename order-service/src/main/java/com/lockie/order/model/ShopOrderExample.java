package com.lockie.order.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopOrderExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNull() {
            addCriterion("order_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNotNull() {
            addCriterion("order_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDateEqualTo(Date value) {
            addCriterion("order_date =", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotEqualTo(Date value) {
            addCriterion("order_date <>", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThan(Date value) {
            addCriterion("order_date >", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThanOrEqualTo(Date value) {
            addCriterion("order_date >=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThan(Date value) {
            addCriterion("order_date <", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThanOrEqualTo(Date value) {
            addCriterion("order_date <=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateIn(List<Date> values) {
            addCriterion("order_date in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotIn(List<Date> values) {
            addCriterion("order_date not in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateBetween(Date value1, Date value2) {
            addCriterion("order_date between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotBetween(Date value1, Date value2) {
            addCriterion("order_date not between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderTotalIsNull() {
            addCriterion("order_total is null");
            return (Criteria) this;
        }

        public Criteria andOrderTotalIsNotNull() {
            addCriterion("order_total is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTotalEqualTo(BigDecimal value) {
            addCriterion("order_total =", value, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalNotEqualTo(BigDecimal value) {
            addCriterion("order_total <>", value, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalGreaterThan(BigDecimal value) {
            addCriterion("order_total >", value, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_total >=", value, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalLessThan(BigDecimal value) {
            addCriterion("order_total <", value, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_total <=", value, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalIn(List<BigDecimal> values) {
            addCriterion("order_total in", values, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalNotIn(List<BigDecimal> values) {
            addCriterion("order_total not in", values, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_total between", value1, value2, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_total not between", value1, value2, "orderTotal");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeIsNull() {
            addCriterion("order_currency_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeIsNotNull() {
            addCriterion("order_currency_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeEqualTo(String value) {
            addCriterion("order_currency_code =", value, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeNotEqualTo(String value) {
            addCriterion("order_currency_code <>", value, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeGreaterThan(String value) {
            addCriterion("order_currency_code >", value, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_currency_code >=", value, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeLessThan(String value) {
            addCriterion("order_currency_code <", value, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeLessThanOrEqualTo(String value) {
            addCriterion("order_currency_code <=", value, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeLike(String value) {
            addCriterion("order_currency_code like", value, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeNotLike(String value) {
            addCriterion("order_currency_code not like", value, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeIn(List<String> values) {
            addCriterion("order_currency_code in", values, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeNotIn(List<String> values) {
            addCriterion("order_currency_code not in", values, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeBetween(String value1, String value2) {
            addCriterion("order_currency_code between", value1, value2, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andOrderCurrencyCodeNotBetween(String value1, String value2) {
            addCriterion("order_currency_code not between", value1, value2, "orderCurrencyCode");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedIsNull() {
            addCriterion("total_qty_ordered is null");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedIsNotNull() {
            addCriterion("total_qty_ordered is not null");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedEqualTo(Integer value) {
            addCriterion("total_qty_ordered =", value, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedNotEqualTo(Integer value) {
            addCriterion("total_qty_ordered <>", value, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedGreaterThan(Integer value) {
            addCriterion("total_qty_ordered >", value, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_qty_ordered >=", value, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedLessThan(Integer value) {
            addCriterion("total_qty_ordered <", value, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedLessThanOrEqualTo(Integer value) {
            addCriterion("total_qty_ordered <=", value, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedIn(List<Integer> values) {
            addCriterion("total_qty_ordered in", values, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedNotIn(List<Integer> values) {
            addCriterion("total_qty_ordered not in", values, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedBetween(Integer value1, Integer value2) {
            addCriterion("total_qty_ordered between", value1, value2, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalQtyOrderedNotBetween(Integer value1, Integer value2) {
            addCriterion("total_qty_ordered not between", value1, value2, "totalQtyOrdered");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountIsNull() {
            addCriterion("total_item_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountIsNotNull() {
            addCriterion("total_item_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountEqualTo(Integer value) {
            addCriterion("total_item_count =", value, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountNotEqualTo(Integer value) {
            addCriterion("total_item_count <>", value, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountGreaterThan(Integer value) {
            addCriterion("total_item_count >", value, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_item_count >=", value, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountLessThan(Integer value) {
            addCriterion("total_item_count <", value, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountLessThanOrEqualTo(Integer value) {
            addCriterion("total_item_count <=", value, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountIn(List<Integer> values) {
            addCriterion("total_item_count in", values, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountNotIn(List<Integer> values) {
            addCriterion("total_item_count not in", values, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountBetween(Integer value1, Integer value2) {
            addCriterion("total_item_count between", value1, value2, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andTotalItemCountNotBetween(Integer value1, Integer value2) {
            addCriterion("total_item_count not between", value1, value2, "totalItemCount");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(String value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(String value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(String value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(String value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLike(String value) {
            addCriterion("order_type like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotLike(String value) {
            addCriterion("order_type not like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<String> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<String> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(String value1, String value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(String value1, String value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNull() {
            addCriterion("order_source is null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNotNull() {
            addCriterion("order_source is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceEqualTo(String value) {
            addCriterion("order_source =", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotEqualTo(String value) {
            addCriterion("order_source <>", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThan(String value) {
            addCriterion("order_source >", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThanOrEqualTo(String value) {
            addCriterion("order_source >=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThan(String value) {
            addCriterion("order_source <", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThanOrEqualTo(String value) {
            addCriterion("order_source <=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLike(String value) {
            addCriterion("order_source like", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotLike(String value) {
            addCriterion("order_source not like", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIn(List<String> values) {
            addCriterion("order_source in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotIn(List<String> values) {
            addCriterion("order_source not in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceBetween(String value1, String value2) {
            addCriterion("order_source between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotBetween(String value1, String value2) {
            addCriterion("order_source not between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagIsNull() {
            addCriterion("order_email_flag is null");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagIsNotNull() {
            addCriterion("order_email_flag is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagEqualTo(Integer value) {
            addCriterion("order_email_flag =", value, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagNotEqualTo(Integer value) {
            addCriterion("order_email_flag <>", value, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagGreaterThan(Integer value) {
            addCriterion("order_email_flag >", value, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_email_flag >=", value, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagLessThan(Integer value) {
            addCriterion("order_email_flag <", value, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagLessThanOrEqualTo(Integer value) {
            addCriterion("order_email_flag <=", value, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagIn(List<Integer> values) {
            addCriterion("order_email_flag in", values, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagNotIn(List<Integer> values) {
            addCriterion("order_email_flag not in", values, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagBetween(Integer value1, Integer value2) {
            addCriterion("order_email_flag between", value1, value2, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderEmailFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("order_email_flag not between", value1, value2, "orderEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagIsNull() {
            addCriterion("order_unpay_email_flag is null");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagIsNotNull() {
            addCriterion("order_unpay_email_flag is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagEqualTo(Integer value) {
            addCriterion("order_unpay_email_flag =", value, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagNotEqualTo(Integer value) {
            addCriterion("order_unpay_email_flag <>", value, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagGreaterThan(Integer value) {
            addCriterion("order_unpay_email_flag >", value, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_unpay_email_flag >=", value, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagLessThan(Integer value) {
            addCriterion("order_unpay_email_flag <", value, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagLessThanOrEqualTo(Integer value) {
            addCriterion("order_unpay_email_flag <=", value, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagIn(List<Integer> values) {
            addCriterion("order_unpay_email_flag in", values, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagNotIn(List<Integer> values) {
            addCriterion("order_unpay_email_flag not in", values, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagBetween(Integer value1, Integer value2) {
            addCriterion("order_unpay_email_flag between", value1, value2, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andOrderUnpayEmailFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("order_unpay_email_flag not between", value1, value2, "orderUnpayEmailFlag");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Integer value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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