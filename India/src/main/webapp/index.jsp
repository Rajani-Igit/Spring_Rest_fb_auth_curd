<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<meta charset="UTF-8">
</head>
<body>
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
<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
     
     var username; 
  FB.api('/me', function(response) {
      console.log( response);
     username=response.name;
      location.href = "login.do?userid="+username;
/*      $.ajax({
  type: "POST",
  url: "login.do",
  async: false,
  data: {'userid':username},
  success: function(data) {
  $("#maindiv").append(data);
  console.log(data);
  },
  error: function(data) {
               
            }
  //dataType: dataType
}); */
    });
    } else {
      alert("Login with valid userid and pwd");
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
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

    // Now that we've initialized the JavaScript SDK, we call 
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.



  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log( response);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!';
    });

  }
</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Curd Operation</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li ><a href="#">All Users</a></li>
      <li ><a href="#">Search User</a></li>
       <li ><a href="#">Add User</a></li>
    </ul>
     
      <ul class="nav navbar-nav navbar-right">
      
      <li ><a href="#"><span class="glyphicon glyphicon-user"></span><fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
      </fb:login-button></a></li>
    </ul>
    
  </div>
</nav>

</body>
</html>
   