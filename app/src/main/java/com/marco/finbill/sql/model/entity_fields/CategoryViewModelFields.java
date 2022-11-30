package com.marco.finbill.sql.model.entity_fields;

public class CategoryViewModelFields {

    private Integer balanceCurrencyId;
    private Boolean proceed;

    public CategoryViewModelFields() {
        this.balanceCurrencyId = null;
        this.proceed = false;
    }

    public Integer getBalanceCurrencyId() {
        return balanceCurrencyId;
    }

    public Boolean getProceed() {
        return proceed;
    }

    public void setBalanceCurrencyId(Integer balanceCurrencyId) {
        this.balanceCurrencyId = balanceCurrencyId;
    }

    public void setProceed(Boolean proceed) {
        this.proceed = proceed;
    }

    public boolean isValid() {
        return balanceCurrencyId != null && proceed;
    }

    public void clear() {
        this.balanceCurrencyId = null;
        this.proceed = false;
    }

}
