package dev.peruch.rabbitxml.model;

public class PaymentModel {

    private String value;
    private String nzu;
    private String date;
    private String cpf;
    private String id;

    public String getValue() {
        return value;
    }

    public PaymentModel setValue(String value) {
        this.value = value;
        return this;
    }

    public String getNzu() {
        return nzu;
    }

    public PaymentModel setNzu(String nzu) {
        this.nzu = nzu;
        return this;
    }

    public String getDate() {
        return date;
    }

    public PaymentModel setDate(String date) {
        this.date = date;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public PaymentModel setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getId() {
        return id;
    }

    public PaymentModel setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "value='" + value + '\'' +
                ", nzu='" + nzu + '\'' +
                ", date='" + date + '\'' +
                ", cpf='" + cpf + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
