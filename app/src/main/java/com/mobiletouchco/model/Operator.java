package com.mobiletouchco.model;

import java.util.Comparator;

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
    private String mobile_network_code="";

    public String getMobile_network_code() {
        return mobile_network_code;
    }

    public void setMobile_network_code(String mobile_network_code) {
        this.mobile_network_code = mobile_network_code;
    }

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
    public static Comparator<Operator> StuNameComparator = new Comparator<Operator>() {

        public int compare(Operator s1, Operator s2) {
            String StudentName1 = s1.getOperator_name().toUpperCase();
            String StudentName2 = s2.getOperator_name().toUpperCase();

            //ascending order
            return StudentName1.compareTo(StudentName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};
}
