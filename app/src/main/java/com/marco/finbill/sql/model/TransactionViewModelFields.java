package com.marco.finbill.sql.model;


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
        if (from != null && to != null) {
            return true;
        }
        return false;
    }

    public void clear() {
        this.from = null;
        this.to = null;
    }
}
