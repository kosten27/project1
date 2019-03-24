<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Modify product</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>

    <body class="w3-light-grey">
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Webapp</h1>
        </div>

        <div class="w3-container w3-padding">
            <div class="w3-card-4">
                <div class="w3-container w3-center w3-light-blue">
                    <h2>Modify product</h2>
                </div>

                <form method="post" action="/products" class="w3-selection w3-light-grey w3-padding">
                    <input type="hidden" name="method" value="put">
                    <label>Product ID:
                        <input type="text" name="productId" class="w3-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>
                    <label>Name:
                        <input type="text" name="name" class="w3-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>
                    <label>Price:
                        <input type="number" name="price" class="w3-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>

                    <button type="submit" class="w3-btn w3-light-blue w3-round-large w3-margin-bottom">Submit</button>
                </form>
            </div>
        </div>

        <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
            <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
        </div>
    </body>
</html>