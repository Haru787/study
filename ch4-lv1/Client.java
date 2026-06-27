package com.example.billing;

import java.time.LocalDateTime;

/**
 * 請求書の宛先となる取引先エンティティ。
 */
public class Client {

    private String        clientId;
    private String        clientName;
    private String        address;
    private String        contactEmail;
    private String        phoneNumber;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 取引先コンストラクタ。
     */
    public Client(String clientId, String clientName, String address,
                  String contactEmail, String phoneNumber) {
        this.clientId     = clientId;
        this.clientName   = clientName;
        this.address      = address;
        this.contactEmail = contactEmail;
        this.phoneNumber  = phoneNumber;
        this.active       = true;
        this.createdAt    = LocalDateTime.now();
        this.updatedAt    = LocalDateTime.now();
    }

    public String  getClientId()     { return clientId; }
    public String  getClientName()   { return clientName; }
    public String  getAddress()      { return address; }
    public String  getContactEmail() { return contactEmail; }
    public String  getPhoneNumber()  { return phoneNumber; }
    public boolean isActive()        { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}