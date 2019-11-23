/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import business.Member;
import business.MemberDB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Jasmine.Herd
 */
public class ClubLogon extends ActionSupport implements SessionAware {
    
    public ClubLogon() {
    }
    
    @Override
    public String execute() throws Exception {
        String msg = "" ; //"Credentials were: " + this.userid+ " "+ this.pattempt;  
        Map request = (Map) ActionContext.getContext().get("request");
        
        member = MemberDB.getMemberByID(this.userid);
        if(member != null){
            member.setPassAttempt(Long.parseLong(this.pattempt));
            if(member.isAuthenticated()){
                request.put("msg", "Member " + this.userid + " is authenticated");
                session.put("member", member);
                return SUCCESS;
            }else{
                request.put("msg", "Member " + this.userid + " found but not authenticated.");
                return INPUT;
            }
        }else{
            request.put("msg", "Member " + this.userid + " not found.");
            return INPUT;
        }
  
    }
    @Override
    public void validate(){
        try{
            long pat = Long.parseLong(this.pattempt);
            if(pat <= 0){
                throw new NumberFormatException("bad password: not > 0");
            }
        }catch(NumberFormatException e){
            addFieldError("pattempt","Password issue: "+ e.getMessage());
                    
        }
    }
    
    private Member member;
    private String userid;
    private String pattempt;
    public void setUserid(String uid){
        this.userid = uid;
    }
    public String getUserid(){
        return this.userid;
    }
    public void setPattempt(String pat){
        this.pattempt = pat;
    }
    public String getPattempt(){
        return this.pattempt;
    }
    
    private Map session;
    @Override
    public void setSession(Map session){
        this.session =session;
    }
    
}
