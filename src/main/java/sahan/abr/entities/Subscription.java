package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subscription {

    private Integer id;
    private String titleSubscription;
    private double priceSubscription;
    private int validity;
    private int numberClasses;

    public Subscription(String titleSubscription, double priceSubscription, int validity, int numberClasses) {
        this.titleSubscription = titleSubscription;
        this.priceSubscription = priceSubscription;
        this.validity = validity;
        this.numberClasses = numberClasses;
    }

    @Override
    public String toString() {
        return titleSubscription;
    }
}
