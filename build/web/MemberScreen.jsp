<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Welcome</title>
        <style>
            table.member-details{
                border-collapse: collapse;
            }
            table.member-details td, table.member-details th{
                padding: 6px;
                border: 1px solid #999;
            }
        </style>
    </head>
   
    <body>
        <h1>Club member Data</h1>
        <s:form action="MemberUpdate" method="post">
            <table class="member-details">
            <tr>
                <td>Member ID:</td>
                <td><input type="text" name="member.memid"
                           value="<s:property value="#session['member'].memid"/>"
                           readonly="true"></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="member.lastnm" 
                           value="<s:property value="#session['member'].lastnm"/>" >
                </td>
            </tr>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="member.firstnm" 
                           value="<s:property value="#session['member'].firstnm"/>" >
                </td>
            </tr>
            <tr>
                <s:textfield name = "member.middlenm" 
                label = "Middle Nm:"
                value="%{#session['member'].middlenm}"/>
            </tr>
            <tr>
                <td>Status:</td>
                <td><input type="text" name="member.status" 
                           value="<s:property value="#session['member'].status"/>" >
                </td>
            <tr>
            <tr>
                <td>Member Date:</td>
                <td><input type="text" name="member.memdt" 
                           value="<s:property value="#session['member'].memdt"/>" >
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="member.password" 
                           value="<s:property value="#session['member'].password"/>" >
                </td>
            </tr>
            <tr>
                <td></td>
                <td><s:submit value="Update Member data"/></td>
            </tr>
            </table>
        </s:form>
        <br>
        ${msg}
         <hr>
         <br>View Transaction History From:<br>

            <s:form action="ShowPurchases" method="post" >
                 <s:textfield name="month" label="Month" />   <s:textfield name="day" label="Day" />  <s:textfield name="year" label="Year"/>
    <s:submit value="View Transactions"/>
                            <br>

            </s:form> <br>
         <br><br>
         
         <a href="/ClubDBStruts">Back to the Login Screen</a>
       </body>

</html>