package model;

import java.util.Comparator;
import java.util.Random;

public class Toy implements Comparator<Toy>, Comparable<Toy> {
    public Toy(String vendorID, String name, Double lotteryWeight, Double price, Integer quantity) throws Exception {
        this.price = price;
        this.vendorID = vendorID;
        checkWeight(lotteryWeight);
        this.lotteryWeight = lotteryWeight;
        this.name = name;
        if (quantity > 0)
            this.quantity = quantity;
        else
            this.quantity = 0;
        this.refreshLotteryRank();
    }

    public void setLotteryWeight(Double lotteryWeight) throws Exception {
        checkWeight(lotteryWeight);
        this.lotteryWeight = lotteryWeight;
    }

    public Double getLotteryRank() {
        return lotteryRank;
    }

    public Double getPrice() {
        return price;
    }

    public Double getLotteryWeight() {
        return lotteryWeight;
    }

    public String getName() {
        return name;
    }

    public String getVendorID() {
        return vendorID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity < 0)
            this.quantity = 0;
        else
            this.quantity = quantity;
    }

    public Toy takeOne() throws Exception {
        this.setQuantity(this.quantity - 1);
        return new Toy(this.vendorID, this.name, this.lotteryWeight, this.price, 1);
    }

    private Integer quantity;
    private Double price;
    private Double lotteryWeight;
    private Double lotteryRank;
    private String name;
    private String vendorID;

    public void refreshLotteryRank() {
        this.lotteryRank = Math.random() + this.lotteryWeight;
    }

    private void checkWeight(Double weigth) throws Exception {
        if (!(weigth >= 0.0 && weigth <= 1.0)) {
            throw new Exception("Недопустимое значение для веса!");
        }
    }

    @Override
    public int compare(Toy o1, Toy o2) {
        return o1.getLotteryRank() < o2.getLotteryRank() ? -1 : o1.getLotteryRank() > o2.getLotteryRank() ? 1 : 0;
    }

    @Override
    public int compareTo(Toy o) {
        return this.getLotteryRank() < o.getLotteryRank() ? -1 : this.getLotteryRank() > o.getLotteryRank() ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Артикул='" + vendorID + '\'' +
                ", Имя='" + name + '\'' +
                ", Количество=" + quantity +
                ", Цена=" + price +
                ", Вес=" + lotteryWeight;
    }
}