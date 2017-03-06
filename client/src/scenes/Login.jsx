// /**
//  * Created by nathan on 21/01/17.
//  */
import React, { PropTypes } from 'react';
import LoginForm from '../components/LoginForm.jsx';
import cookie from 'react-cookie';

var Login = React.createClass({
  getDefaultProps: function () {
    return {
      username: '',
      password: ''
    };
  },
  getInitialState: function () {
    return {
        username: this.props.username,
        password: this.props.password
    };
  },
  saveCookie : function(username) {
    cookie.save('userID', username, {path: '/'});
  },
  handleNewName: function (u, p) {
    this.setState({
      username: u,
      password: p
    });
    var loginUsername = u;
    var loginPass = p;
    //ajax call
        $.ajax({
            url:"http://localhost:8080/BackendServer/DatabaseServlet",
           type: "POST",
           data: JSON.stringify({
             "action" : "Login User",
             "user": loginUsername,
             "password": loginPass
           }),
            dataType:"json",
            success:function(data){
               console.log(data)
               document.getElementById('out').innerHTML = JSON.stringify(data);
               if(data.status === "success"){
                 console.log("success");
                 //set SessionToken to hold JWT
                 this.saveCookie(loginUsername);
               }
            }.bind(this),
            error:function(error){
               document.getElementById('out').innerHTML = error;
                console.log(error);
            }
        });
  },

  render: function () {
    var username = this.state.username;
    var password = this.state.password;
    return (
      <div>
        <LoginForm onNewName={this.handleNewName}/>
        <h1> Hello {username}</h1>
        <h2> This is your password: {password}</h2>
        <div id="out"></div>
      </div>
    );
  }
});


module.exports = Login;
