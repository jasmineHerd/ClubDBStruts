/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 *
 * @author Jasmine.Herd
 */
public class Struts2Dispatcher extends StrutsPrepareAndExecuteFilter{
    public void init(FilterConfig filterConfig) throws ServletException{
        super.init(filterConfig);
        try{
            SessionFactory sessionFactory = 
                    HibernateUtil.getSessionFactory();
            if(sessionFactory == null){
                throw new HibernateException("Session Factory is null");
            }else{
                System.out.println("Session Factory is ok.");
            }
        }catch(HibernateException e){
            throw new ServletException(e.getMessage());
        }
    }
}
