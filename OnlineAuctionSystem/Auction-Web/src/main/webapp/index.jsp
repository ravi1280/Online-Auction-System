<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/5/2025
  Time: 11:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lk.jiat.ee.session.AuctionBean, lk.jiat.ee.entity.AuctionItem, java.util.*" %>
<jsp:useBean id="bean" class="lk.jiat.ee.session.AuctionBean" scope="application" />
<%
  String username = (String) session.getAttribute("username");
  if (username == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Auction Home</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'Poppins', sans-serif;
      background: #f4f6f8;
      color: #333;
    }

    header {
      background-color: #4CAF50;
      color: white;
      padding: 20px 40px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
    }

    h1 {
      margin: 0;
      font-size: 28px;
    }

    .welcome {
      font-size: 16px;
    }

    .container {
      max-width: 1000px;
      margin: 40px auto;
      padding: 0 20px;
    }

    .auction-card {
      background: #fff;
      padding: 20px;
      margin-bottom: 20px;
      border-radius: 10px;
      box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
      transition: transform 0.2s;
    }

    .auction-card:hover {
      transform: translateY(-4px);
    }

    .auction-card h3 {
      margin-top: 0;
    }

    .auction-card p {
      margin: 5px 0;
    }

    .bid-button {
      padding: 10px 20px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-weight: bold;
      transition: background-color 0.3s;
    }

    .bid-button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>

<header>
  <h1>Live Auctions</h1>
  <div class="welcome">Welcome, <%= username %>!</div>
</header>

<div class="container">
  <%
    List<AuctionItem> items = (List<AuctionItem>) request.getAttribute("items");
    if (items != null) {
      for (AuctionItem item : items) {
  %>
  <div class="auction-card">
    <h3><%= item.getName() %></h3>
    <p><strong>Current Bid:</strong> $<%= item.getCurrentBid() %></p>
    <p><strong>Auction Ends At:</strong> <%= item.getEndTime() %></p>
    <form action="bid.jsp" method="get">
      <input type="hidden" name="id" value="<%= item.getId() %>"/>
      <input type="hidden" name="cbid" value="<%= item.getCurrentBid() %>"/>
      <button class="bid-button" type="submit">Bid Now</button>
    </form>
  </div>
  <%
    }
  } else {
  %>
  <p>No auction items found.</p>
  <%
    }
  %>
</div>

</body>
</html>


