package business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author 
 */
@Entity
@Table(name="tblMembers")
public class Member {
    @Id
    @Column(name="MemID")
    private String memid;
    
    @Column(name="LastName")
    private String lastnm;
    
    @Column(name="FirstName")
    private String firstnm;
    
    @Column(name="MiddleName")
    private String middlenm;
    
    @Column(name="Status")
    private String status;
    
    @Column(name="MemDt")
    @Temporal(TemporalType.DATE)
    private Date memdt;
    
    @Column(name="Password")
    private long password;
    
    @Column(name="YTD_Total")
    private double ytdtot;
    
    @Column(name="YTD_Total_Dt")
    @Temporal(TemporalType.DATE)
    private Date ytdtotdt;
    
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="Memid")
    @OrderBy("PurchaseDt")
    private List<Purchase>purchases;
    
    
    @Transient
    private long passattempt;
    
    public Member() {
        this.memid="";
        this.lastnm="";
        this.firstnm="";
        this.middlenm="";
        this.status="";
        this.memdt=null;
        this.password=-1;
    }

    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }

    public String getLastnm() {
        return lastnm;
    }

    public void setLastnm(String lastnm) {
        this.lastnm = lastnm;
    }

    public String getFirstnm() {
        return firstnm;
    }

    public void setFirstnm(String firstnm) {
        this.firstnm = firstnm;
    }

    public String getMiddlenm() {
        return middlenm;
    }

    public void setMiddlenm(String middlenm) {
        this.middlenm = middlenm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getMemdt() {
        return memdt;
    }

    public void setMemdt(Date memdt) {
        this.memdt = memdt;
    }
    
    public String getMemDtS(){
        SimpleDateFormat sdf = 
                new SimpleDateFormat("MM-dd-yyyy");
        return sdf.format(this.memdt);
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }
    
    public void setPassAttempt(long patt){
        this.passattempt = patt;
    }
    
    public boolean isAuthenticated(){
        if (this.password > 0){
            if (this.password == this.passattempt) {
                return true;
            }
        }
        return false;
    }
    
    public List<Purchase> getPurchases(){
        return this.purchases;
    }
    
    public void setPurchases(List<Purchase> pur){
        this.purchases = pur;
    }
    
    public void addPurchase(Purchase p){
        this.purchases.add(p);
    }
    public double getTotalDue(){
        double tot=0;
        for (Purchase p : this.purchases){
            if(p.getTranstype().equalsIgnoreCase("D")){
                tot += p.getAmt();
            }else{
                tot -= p.getAmt();
            }
        }
        return tot;
    }
}