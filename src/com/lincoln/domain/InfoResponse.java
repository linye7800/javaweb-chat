package com.lincoln.domain;

/**
 * description:
 *
 * @author linye
 * @date 2022年07月03日 10:32 PM
 */
public class InfoResponse {

    private String op;

    private String value;


    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
                "op='" + op + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

