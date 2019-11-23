package business;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 
 */
@Entity
@Table(name="tblpurchases")
public class Purchase {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long pid;
    
    @Column(name="MemId")
    private String memid;
    
    @Column(name="PurchaseDt")
    @Temporal(TemporalType.DATE)
    private Date purchdt;
    
    @Column(name="transtype")
    private String transtype;
    
    @Column(name="TransCd")
    private String tcd;
    
    @Column(name="Amount")
    private double amt;
    
    @OneToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="TransCd",insertable=false,updatable=false)
    private PCodes pcodes;
    
    public Purchase() {
        this.memid = "";
        this.purchdt = null;
        this.transtype = "";
        this.pid = 0;
        this.amt = 0;
        this.tcd = "";
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }

    public Date getPurchdt() {
        return purchdt;
    }

    public void setPurchdt(Date purchdt) {
        this.purchdt = purchdt;
    }
    
    public String getPurchdtS() {
        return new SimpleDateFormat("MM-dd-yyyy").format(this.purchdt); 
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    public String getTcd() {
        return tcd;
    }

    public void setTcd(String tcd) {
        this.tcd = tcd;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }
    
    public String getTransdesc() {
        return pcodes.getTransdesc();
    }
        
    public void setPCodes(PCodes pcd){
        this.pcodes = pcd;
    }
}
