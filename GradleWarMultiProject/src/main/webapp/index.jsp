<%@ page import="com.example.Message" %>
<%@ page import="com.example2.LocalMessage" %>
<!DOCTYPE html>
<% String title = "It worked!"; %>
<html>
	<head>
		<title><%= title%></title>
	</head>
	<body>
		<h1><%= title%></h1>
		<p>This sample jsp file is located in src/main/webapp/index.jsp.</p>
		<p>Test a relative link to a file located in <a href="jsp/testrelative.jsp">src/main/webapp/index.jsp</a></p>
		<p>Test a message coming from the jar_dependency project.  Sure, calling a service class directly
		from a JSP is not the coolest design, but we all know this is just a sample app, right?</p>
		<p>The Message class in the dependency jar (src/main/webapp/index.jsp) says this:</p>
		<p><strong><%= new Message().getMessage() %>.</strong></p>
		<p>The message class in our local war (src/main/java/com/example2/LocalMessage.java) says:</p>
		<p><strong><%= new LocalMessage().getMessage() %>.</strong></p>
		<h2>Things to Try</h2>
		<p>You should already be running this using:  <span style="font-style:italic;"> gradle -t -q jettyRunWar</span> 
			using Gradle 3.0 or later.</p>
		<p>Assuming all that's true, while the server is running (yeah, that's right, don't
			restart it) go ahead and edit the JSP or any of the files mentioned on this page.  A second or two after you
		save the file you should see your changes reflected on the page.</p>
</body>
</html>