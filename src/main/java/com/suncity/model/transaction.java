package com.suncity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class transaction {
@Id
@Column
@GeneratedValue(strategy=GenerationType.AUTO) 
private int transactionId;
@Column
private String amount;
@Column
private String date;
@Column
private String receiverAccountNo;
@Column
private String senderAccountNo;
@Column
private String status;
public transaction(){
}
public transaction(int transactionId, String amount, String date,
String receiverAccountNo, String senderAccountNo, String status) {
super();
this.transactionId = transactionId;
this.amount = amount;
this.date = date;
this.receiverAccountNo = receiverAccountNo;
this.senderAccountNo = senderAccountNo;
this.status = status;
}
public int getTransactionId() {
return transactionId;
}
public void setTransactionId(int transactionId) {
this.transactionId = transactionId;
}
public String getAmount() {
return amount;
}
public void setAmount(String amount) {
this.amount = amount;
}
public String getDate() {
return date;
}
public void setDate(String date) {
this.date = date;
}
public String getReceiverAccountNo() {
return receiverAccountNo;
}
public void setReceiverAccountNo(String receiverAccountNo) {
this.receiverAccountNo = receiverAccountNo;
}
public String getSenderAccountNo() {
return senderAccountNo;
}
public void setSenderAccountNo(String senderAccountNo) {
this.senderAccountNo = senderAccountNo;
}
public String getStatus() {
return status;
}
public void setStatus(String status) {
this.status = status;
}

}