package com.marco.finbill.sql.model.entity_fields;


public class TransactionViewModelFields {
    private Object from;
    private Object to;

    public TransactionViewModelFields() {
        this.from = null;
        this.to = null;
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public void setTo(Object to) {
        this.to = to;
    }

    public void set(Object from, Object to) {
        this.from = from;
        this.to = to;
    }

    public boolean isValid() {
        return from != null && to != null;
    }

    public void clear() {
        this.from = null;
        this.to = null;
    }
}
