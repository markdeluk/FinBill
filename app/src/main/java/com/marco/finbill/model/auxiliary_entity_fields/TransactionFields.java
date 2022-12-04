package com.marco.finbill.model.auxiliary_entity_fields;

public class TransactionFields {
    private Integer id;
    private Integer currencyId;
    private Object from;
    private Object to;

    public TransactionFields() {
        this.id = null;
        this.from = null;
        this.to = null;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public void setTo(Object to) {
        this.to = to;
    }

    public void set(Integer id, Integer currencyId, Object from, Object to) {
        this.id = id;
        this.currencyId = currencyId;
        this.from = from;
        this.to = to;
    }

    public boolean isValid() {
        return id != null && currencyId != null && from != null && to != null;
    }

    public void clear() {
        this.id = null;
        this.currencyId = null;
        this.from = null;
        this.to = null;
    }
}
