package com.marco.finbill.sql.model.entity_fields;

public class AccountViewModelFields {

    private Integer balanceCurrencyId;
    private Integer platfondCurrencyId;
    private Boolean proceed;

    public AccountViewModelFields() {
        this.balanceCurrencyId = null;
        this.platfondCurrencyId = null;
        this.proceed = false;
    }

    public Integer getBalanceCurrencyId() {
        return balanceCurrencyId;
    }

    public Integer getPlatfondCurrencyId() {
        return platfondCurrencyId;
    }

    public Boolean getProceed() {
        return proceed;
    }

    public void setBalanceCurrencyId(Integer balanceCurrencyId) {
        this.balanceCurrencyId = balanceCurrencyId;
    }

    public void setPlatfondCurrencyId(Integer platfondCurrencyId) {
        this.platfondCurrencyId = platfondCurrencyId;
    }

    public void setProceed(Boolean proceed) {
        this.proceed = proceed;
    }

    public boolean isValid() {
        return balanceCurrencyId != null && platfondCurrencyId != null && proceed;
    }

    public void clear() {
        this.balanceCurrencyId = null;
        this.platfondCurrencyId = null;
        this.proceed = false;
    }
}
