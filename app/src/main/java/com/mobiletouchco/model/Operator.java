package com.mobiletouchco.model;

/**
 * Created by Prosanto on 9/2/16.
 */
public class Operator {
    private String operator_id="";
    private String operator_name="";
    private String charge_code="";
    private String check_balance="";
    private String customer_service="";
    private String operator_logo="";

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getCharge_code() {
        return charge_code;
    }

    public void setCharge_code(String charge_code) {
        this.charge_code = charge_code;
    }

    public String getCheck_balance() {
        return check_balance;
    }

    public void setCheck_balance(String check_balance) {
        this.check_balance = check_balance;
    }

    public String getCustomer_service() {
        return customer_service;
    }

    public void setCustomer_service(String customer_service) {
        this.customer_service = customer_service;
    }

    public String getOperator_logo() {
        return operator_logo;
    }

    public void setOperator_logo(String operator_logo) {
        this.operator_logo = operator_logo;
    }
}
