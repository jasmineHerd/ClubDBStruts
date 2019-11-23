<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchases</title>
    </head>
    <body>
        <h1>Member Purchases</h1>
        <h2> <s:property value="#session['member'].memid"/></h2>
        <h2><s:property value="#session['member'].firstnm"/> <s:property value="#session['member'].lastnm"/></h2>
        <s:set name="startDate" value="#request['pd']"/>
        <table border="1">
            <s:if test="%{#startDate != null}">
                <caption>Transactions From:
                    <s:date name="startDate" format="MM-dd-yyy"/>
                </caption>
            </s:if>
            <tr>
                <th>Purchase Dt</th>
                <th>Purchase Type</th>
                <th>Trans. Cd</th>
                <th>Trans. Desc</th>
                <th>Amount</th>
            </tr>
            <s:iterator value="#session['member'].purchases">
                <s:if test="%{#startDate == null || #startDate <= purchdt}">
                <tr>
                    <td align="left"><s:property value="purchdtS"/></td> 
                    <td align="left"><s:property value="transtype"/></td>
                    <td align="left"><s:property value="tcd"/></td>
                    <td align="left"><s:property value="transdesc"/></td>
                    <td align="right"><s:number name="amt" type="currency"/></td>
                </tr>
                </s:if>
            </s:iterator>      
        </table>
        <br>
        Total Amount Due:
            <fmt:formatNumber value="${member.totalDue}" type="currency"/>
        <p>${msg}</p>
        <br>
        <a href="MemberScreen.jsp">Back to Member Screen</a>
    </body>
</html>
