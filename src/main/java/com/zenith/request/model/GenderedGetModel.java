package com.zenith.request.model;

public class GenderedGetModel {

    private String token;
    private String gender;
    private int amountToPay;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the amountToPay
     */
    public int getAmountToPay() {
        return amountToPay;
    }

    /**
     * @param amountToPay the amountToPay to set
     */
    public void setAmountToPay(int amountToPay) {
        this.amountToPay = amountToPay;
    }

}
