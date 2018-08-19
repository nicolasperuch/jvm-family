package dev.peruch.rabbitmq.model;

public class OrderModel {

    private String item;
    private Double price;
    private String customer;
    private String creditCard;
    private String purchaseDate;
    private String status;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "item='" + item + '\'' +
                ", price=" + price +
                ", customer='" + customer + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
