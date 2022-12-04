package com.marco.finbill.model.auxiliary_entity_fields;

public class CategoryFields {

    private Integer balanceCurrencyId;
    private Boolean proceed;

    public CategoryFields() {
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
