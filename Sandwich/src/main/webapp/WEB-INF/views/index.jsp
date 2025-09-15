<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Sandwich Condiments</title>
</head>
<body>
<h2>Select Condiments for Your Sandwich</h2>
<br action="save" method="post">
    <input type="checkbox" name="condiment" value="Lettuce"/> Lettuce
    <input type="checkbox" name="condiment" value="Tomato"/> Tomato <
    <input type="checkbox" name="condiment" value="Mustard"/> Mustard
    <input type="checkbox" name="condiment" value="Sprouts"/> Sprouts </br>
    <input type="submit" value="Save"/>
</form>
</body>
</html>
