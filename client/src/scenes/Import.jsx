import { RadioGroup, RadioButton } from 'react-radio-buttons';
var React = require('react');
var Baby = require('babyparse');

var buttonStyle={
  margin : "20px"
}

var Import = React.createClass({

    importProgram : function(e){
      var file = document.getElementById('CSVUpload').files[0];
      console.log(file);
       var reader = new FileReader();
       reader.onload = function () {
           var result = reader.result;
           var parsed = Baby.parse(result);
           // Currently the result is in this scope, so if we want to pass this data to
           // the backend server, the call will have to be in here
           //document.getElementById('json').innerHTML = JSON.stringify(parsed);
           $.ajax({
                url:"http://localhost:8080/BackendServer/DatabaseServlet",
                type: "POST",
                data: JSON.stringify({
                  "action" : "Import Programs",
                   "data": parsed.data
                }),
                dataType:"json",
                success:function(data){
                  if(data.status === 'failed'){
                    alert("Error Message: Something happened during the request to send data from server, please contact your Administrator");
                    document.getElementById('errorOut').innerHTML = "Upload Failed, may be bad connection to Database, or the data already exists";
                  }else{
                    document.getElementById('errorOut').innerHTML = "Upload Success!";
                  }
                   console.log(data)
                 }.bind(this),
                 error:function(error){
                   console.log(error);
               }
             });

         };
       // start reading the file. When it is done, calls the onload event defined above.
       reader.readAsBinaryString(file);
      },

      importOutput : function(e){
        var file = document.getElementById('CSVUpload').files[0];
        console.log(file);
         var reader = new FileReader();
         reader.onload = function () {
             var result = reader.result;
             var parsed = Baby.parse(result);
             // Currently the result is in this scope, so if we want to pass this data to
             // the backend server, the call will have to be in here
             $.ajax({
                  url:"http://localhost:8080/BackendServer/DatabaseServlet",
                  type: "POST",
                  data: JSON.stringify({
                    "action" : "Import Output",
                     "data": parsed.data
                  }),
                  dataType:"json",
                  success:function(data){
                    if(data.status === 'failed'){
                      alert("Error Message: Something happened during the request to send data from server, please contact your Administrator");
                      document.getElementById('errorOut').innerHTML = "Upload Failed, may be bad connection to Database, or the data already exists";
                    }else{
                      document.getElementById('errorOut').innerHTML = "Upload Success!";
                    }
                     console.log(data)
                   }.bind(this),
                   error:function(error){
                     console.log(error);
                 }
               });

           };
         // start reading the file. When it is done, calls the onload event defined above.
         reader.readAsBinaryString(file);
        },

    render: function() {
        return (
            <div>
               <form ref="uploadForm" className="uploader" encType="multipart/form-data" >
                   <input className="button success button" style={buttonStyle}  ref="file" id="CSVUpload" type="file" name="file" className="upload-file"/>
                   <input className="button success button" style={buttonStyle} type="button" ref="button" value="Upload Program File" onClick={this.importProgram} />
                   <input className="button success button" style={buttonStyle} type="button" ref="button" value="Upload Output File" onClick={this.importOutput} />
                   <br/><br/>
               </form>
               <h2>Status of upload:</h2>
               <div id="errorOut"></div>
               <br/><br/>

                 <br/><br/>
            </div>
        );
    }
});

module.exports = Import;
