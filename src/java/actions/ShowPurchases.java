package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Jasmine.Herd
 */
public class ShowPurchases extends ActionSupport implements SessionAware {

    public ShowPurchases() {
    }

    @Override
    public String execute() throws Exception {
        Map request = (Map) ActionContext.getContext().get("request");

        String dt = this.month + "-" + this.day + "-" + this.year;
        Date pd;
        try {
            pd = new SimpleDateFormat("MM-dd-yyyy").parse(dt);
        } catch (Exception e) {
            pd = null;
        }
        request.put("pd", pd);
        return SUCCESS;
    }

    @Override
    public void validate() {
        if ((!this.month.isEmpty()) || (!this.day.isEmpty()) || (!this.year.isEmpty())) {
            try {
                int mo = Integer.parseInt(this.month);
                int da = Integer.parseInt(this.day);
                int yr = Integer.parseInt(this.year);
                LocalDate today = LocalDate.now();
                int Localyear = today.getYear();
                Date pd;
                if ((mo < 1 || mo > 12) || (da < 1 || da > 31)|| (yr > Localyear)) {
                    throw new Exception("out of bounds.");
                }
             

            } catch (Exception e) {
                addFieldError("month", "Month error: " + e.getMessage());
            }
        }
        
    }
    private String month;
    private String day;
    private String year;

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private Map session;

    @Override
    public void setSession(Map session) {
        this.session = session;
    }

}
