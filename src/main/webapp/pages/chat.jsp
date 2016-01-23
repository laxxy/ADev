<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Home | J-PLATFORM</title>
  <link href="<c:url value="/assets/core/css/bootstrap.min.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/font-awesome.min.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/prettyPhoto.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/price-range.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/animate.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/main.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/responsive.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/chat.css"/>" rel="stylesheet">
</head><!--/head-->
<body onload="getMessages(${messages.size()}, '${user.login}')">
<tiles:insertDefinition name="header"/>
<section id="cart_items">
  <div class="container">
        <div class="chat">
          <div class="chat-header clearfix">
            <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_01_green.jpg" alt="avatar">

            <div class="chat-about">
              <div class="chat-with">Income ${messages.size()}</div>
              <div class="chat-num-messages">${lot.lotName} </div>
            </div>
          </div>

          <div class="chat-history">
            <ul id="ms-data">
              <c:forEach items="${messages}" var="message">
                <c:choose>
                  <c:when test="${user.login eq message.author}">
                    <li class="clearfix">
                      <div class="message-data align-right">
                        <span class="message-data-time">${message.date}</span> &nbsp; &nbsp;
                        <span class="message-data-name">${message.author}</span> <i class="fa fa-circle me"></i>

                      </div>
                      <div class="message other-message float-right">
                        ${message.message}
                      </div>
                    </li>
                  </c:when>
                  <c:otherwise>
                    <li>
                      <div class="message-data">
                        <span class="message-data-name"><i class="fa fa-circle online"></i> ${message.author}</span>
                        <span class="message-data-time">${message.date}</span>
                      </div>
                      <div class="message my-message">
                          ${message.message}
                      </div>
                    </li>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </ul>

          </div>

          <div class="chat-message clearfix">
            <textarea name="message-to-send" id="message-to-send" placeholder="Type your message" rows="3"></textarea>
            <button onclick="postMessage(document.getElementById('message-to-send').value)">Send</button>

          </div>

        </div>
    </div>
  </section>
<script id="message-template" type="text/x-handlebars-template">
  <li class="clearfix">
    <div class="message-data align-right">
      <span class="message-data-time" >{{time}}, Today</span> &nbsp; &nbsp;
      <span class="message-data-name" >Olia</span> <i class="fa fa-circle me"></i>
    </div>
    <div class="message other-message float-right">
      {{messageOutput}}
    </div>
  </li>
</script>
<script id="message-response-template" type="text/x-handlebars-template">
  <li>
    <div class="message-data">
      <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span>
      <span class="message-data-time">{{time}}, Today</span>
    </div>
    <div class="message my-message">
      {{response}}
    </div>
  </li>
</script>
<script src="<c:url value="/assets/core/js/jquery.js"/>"></script>
<script src="<c:url value="/assets/core/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/core/js/jquery.scrollUp.min.js"/>"></script>
<script src="<c:url value="/assets/core/js/price-range.js"/>"></script>
<script src="<c:url value="/assets/core/js/jquery.prettyPhoto.js"/>"></script>
<script src="<c:url value="/assets/core/js/main.js"/>"></script>
<script src="<c:url value="/assets/core/js/chat.js"/>"></script>
</body>
</html>