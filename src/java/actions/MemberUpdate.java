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
public class MemberUpdate extends ActionSupport implements SessionAware{
    
    public MemberUpdate() {
    }
    
    @Override
    public String execute() throws Exception {
        Member oldmem= (Member) session.get("member");
        
//        String msg = "Old middle: " + oldmem.getMiddlenm() +
//                " new: " + member.getMiddlenm();
        oldmem.setFirstnm(member.getFirstnm());
        oldmem.setLastnm(member.getLastnm());
        oldmem.setMiddlenm(member.getMiddlenm());
        oldmem.setPassword(member.getPassword());
        oldmem.setPassAttempt(member.getPassword());
        
        String msg = MemberDB.updtMember(oldmem);
        if(msg.startsWith("Error")){
            member = MemberDB.getMemberByID(oldmem.getMemid());
            session.put("member", member);
        }
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("msg",msg);
        return SUCCESS;
    }
    
    private Member member;
    public Member getMember(){
        return member;
    }
    public void setMember(Member member){
        this.member = member;
    }
    
    private Map session;
    @Override
    public void setSession(Map session){
        this.session = session;
    }
}
