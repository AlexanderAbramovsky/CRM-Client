package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import sahan.abr.lib.LibSRM;

@Data
@AllArgsConstructor
public class Client {
    private Integer id;

    private Integer idChild;
    private Integer idPassport;
    private Integer idContract;
    private Integer idActiveSubscription;

    private String surname;
    private String name;
    private String middleName;

    private String phoneNumber;
    private String contactPhoneNumber;

    public Client(Integer idChild, Integer idPassport, Integer idContract, Integer idActiveSubscription, String surname, String name, String middleName, String phoneNumber, String contactPhoneNumber) {
        this.idChild = idChild;
        this.idPassport = idPassport;
        this.idContract = idContract;
        this.idActiveSubscription = idActiveSubscription;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getInform(){
        return "Client{" +
                "id=" + id +
                ", idChild=" + idChild +
                ", idPassport=" + idPassport +
                ", idContract=" + idContract +
                ", idActiveSubscription=" + idActiveSubscription +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactPhoneNumber='" + contactPhoneNumber + '\'';
    }

    @Override
    public String toString() {
        return LibSRM.getFIO(this) + " " + phoneNumber;
    }

    //TODO подумать на счет mail, vk и т.п.
}
