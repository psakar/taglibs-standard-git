<%@ taglib prefix="x" uri="http://java.sun.com/jstl/ea/xml" %>

<html>
<head>
  <title>JSTL: XML Support -- Parse / Set / Expr</title>
</head>
<body bgcolor="#FFFFFF">
<h3>Parse / Set / Expr</h3>

<x:parse var="a">
  <a>
   <b>
    <c>
     foo
    </c>
   </b>
   <d>
     <e>
       bar
     </e>
   </d>
  </a>
</x:parse>

<x:set var="d" select="$a//d"/>
<x:expr select="$d/e"/>

<hr />

</body>
</html>
