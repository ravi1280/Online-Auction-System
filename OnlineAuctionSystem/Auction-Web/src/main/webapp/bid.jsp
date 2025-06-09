<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 6/8/2025
  Time: 4:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lk.jiat.ee.session.AuctionBean, lk.jiat.ee.entity.AuctionItem" %>
<jsp:useBean id="bean" class="lk.jiat.ee.session.AuctionBean" scope="application" />
<%
    Long id = Long.parseLong(request.getParameter("id"));
    String bid = (request.getParameter("cbid"));
    AuctionItem item = bean.findById(id);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Place Bid</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #f0f4f8;
            font-family: 'Poppins', sans-serif;
            color: #333;
        }

        .container {
            max-width: 500px;
            margin: 60px auto;
            background-color: #fff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        }

        h2 {
            margin-top: 0;
            font-size: 24px;
            color: #4CAF50;
            text-align: center;
        }

        label {
            display: block;
            margin: 15px 0 5px;
            font-weight: 600;
        }

        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
        }

        .submit-btn {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px;
            margin-top: 20px;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #45a049;
        }

        .info {
            text-align: center;
            margin-bottom: 20px;
            font-size: 14px;
            color: #666;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Bid for <%= item.getName() %></h2>
    <div class="info">Current highest bid: $<%= bid %></div>
    <form method="post" action="bbid">
        <input type="hidden" name="id" value="<%= item.getId() %>"/>
        <label for="amount">Bid Amount:</label>
        <input type="number" id="amount" name="amount" min="<%= item.getCurrentBid() + 1 %>" step="1" required/>
        <button class="submit-btn" type="submit">Submit Bid</button>
    </form>
</div>

</body>
</html>
