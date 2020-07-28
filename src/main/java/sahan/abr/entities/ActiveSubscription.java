package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActiveSubscription {
    private Integer id;
    private Integer clientId;
    private String titleSubscription;
    private Integer classesSubscription;
    private String sTimeSubscription;
    private String eTimeSubscription;

    private String freezeSubscription;
    private String eTimeFreeze;
    private String sTimeFreeze;
    private String noteFreeze;

    public ActiveSubscription(Integer clientId, String titleSubscription, Integer classesSubscription, String sTimeSubscription, String eTimeSubscription, String freezeSubscription, String eTimeFreeze, String sTimeFreeze, String noteFreeze) {
        this.clientId = clientId;
        this.titleSubscription = titleSubscription;
        this.classesSubscription = classesSubscription;
        this.sTimeSubscription = sTimeSubscription;
        this.eTimeSubscription = eTimeSubscription;
        this.freezeSubscription = freezeSubscription;
        this.eTimeFreeze = eTimeFreeze;
        this.sTimeFreeze = sTimeFreeze;
        this.noteFreeze = noteFreeze;
    }
}
