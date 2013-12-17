<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Add a new task</title>
</head>
<body>
<form method="post" action="/webapi/task" enctype="text/plain">
<input type="hidden" name="_method" value="put" />
<input type="hidden" name="id" id="id" value="0" /><br />
<label>Assign To:</label><br />
<input type="text" name="assigned" id="assigned" maxlength="40" /><br />
<label>Description:</label><br />
<input type="text" name="description" id="description" maxlength="40" /><br />
<button type="submit" name="Submit">Submit</button>
</form>
</body>
</html>