<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/ea/fmt" %>

<html>
<head>
  <title>JSTL: Formatting/I18N Support -- Italian Locale Example</title>
</head>
<body bgcolor="#FFFFFF">
<h3>Italian Locale</h3>

<fmt:locale value="it"/>
<fmt:bundle basename="org.apache.taglibs.standard.examples.i18n.Resources" var="itBundle" scope="page"/>
<fmt:message key="greetingMorning" bundle="$itBundle"/>

</body>
</html>
