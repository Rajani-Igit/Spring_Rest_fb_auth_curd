
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html ng-app="mymodule">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      
        <script src="resources/assets/js/framework/jquery-1.12.0.min.js"></script>
        <script src="resources/assets/js/framework/angular.js"></script>
        <script src="resources/assets/js/addUser.js"></script>
       <script src="resources/assets/js/framework/alasql.min.js"></script>
 <script src="resources/assets/js/framework/excl.js"></script>
 <script src="resources/assets/js/framework/xlsx.core.min.js"></script>
 <script src="resources/assets/js/framework/Blob.js"></script>
      <!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.11.10/xlsx.js"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/alasql/0.4.3/alasql.min.js"></script> -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Add Usesr</title>
        <script>
        
        
        function logutuser(){
        FB.getLoginStatus(function(response) {
            if (response.status === 'connected') {
                FB.logout(function(response) {
                    // this part just clears the $_SESSION var
                    // replace with your own code
                     location.href = "logout.do";
                  alert("You have logged out");
                });
            }
        });
    }



  window.fbAsyncInit = function() {
    FB.init({
      appId      : '119643865511460',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.8' // use graph api version 2.8
    });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
 
        </script>
    </head>
<body ng-controller="myctrl">
<%
final String message = (String) request.getAttribute ("userid");
%>



<div class="container-fluid">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Curd Operation</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li ng-click="showallusers()"><a href="#">All Users</a></li>
      <li ng-click="search()"><a href="#">Search User</a></li>
       <li ng-click="adduserdata()"><a href="#">Add User</a></li>
    </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp<%= message %></a></li>
      <li onclick="logutuser();"><a href="#"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      
    </ul>
    
  </div>
</nav>
<!--   <input data-my-Directive type="file" name="file" accept="image/gif, image/jpeg, image/png">
  <img id="preview" />
  <div>hiii<div> -->
  <!-- <div style="
    margin-top: 54px;
    margin-left: 305px;">
  <button class="btn btn-primary" ng-click="adduserdata()">Add User</button>
  <button class="btn btn-danger" ng-click="showallusers()">Show All Users</button>
  <button class="btn btn-warning" ng-click="search()">Search User</button>
  </div> -->
 <div ng-show="showsuccessmessage" style="
    position: absolute;
    left: 546px;
    top: 74px;
    font-size: large;
    font-family: sans-serif;
    font-style: italic;
    color: red;
">{{successmessage}}</div> 

  <div total-user-table ng-if="totalusesrdirective"></div>
  
  <div ng-show="searchshow" class="container" style=" position: absolute; top: 191px;left: 366px;">
               <form>
                    <div class="form-group">
                        <label for="usr">Search:</label>
                        <input type="text"  placeholder="Search By Email id" class="form-control" style="width: 50%;" ng-model="searchattribute">
                    </div>
                     <button class="btn btn-success" ng-click="searchuser(searchattribute)">Submit</button>
                    </form>
                    </div>
  <div  form-directive  ng-show="show">
                
                
   <div ng-show="loadingshow">
  <img src="resources/assets/images/ajax-loader.gif" id="loadingImage"  style="display: block;margin: 0px auto;position: absolute;top: 189px;left: 596px;">
</div>       
  
 
</div>
</body>
</html>
