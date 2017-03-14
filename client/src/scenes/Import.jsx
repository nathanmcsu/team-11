var React = require('react');
var Baby = require('babyparse');
var url = require('url');

var buttonStyle={
  margin : "20px",
  align: "center"
}

var Import = React.createClass({

    importProgram : function(e){
      var file = document.getElementById('CSVUpload').files[0];
      if(file == null){
        alert("No file selected!");
        return null;
      }
      console.log(file);
       var reader = new FileReader();
       reader.onload = function () {
           var result = reader.result;
           var parsed = Baby.parse(result);
           // Currently the result is in this scope, so if we want to pass this data to
           // the backend server, the call will have to be in here
           document.getElementById('json').innerHTML = JSON.stringify(parsed);
           $.ajax({
                url:url,
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
        if(file == null){
          alert("No file selected!");
          return null;
        }
        console.log(file);
        var reader = new FileReader();
         reader.onload = function () {
             var result = reader.result;
             var parsed = Baby.parse(result);
             // Currently the result is in this scope, so if we want to pass this data to
             // the backend server, the call will have to be in here
             $.ajax({
                  url:url,
                  type: "POST",
                  data: JSON.stringify({
                    "action" : "Import Output",
                     "data": parsed.data
                  }),
                  dataType:"json",
                  success:function(data){
                    if(data.status === 'failed'){
                      alert("Error Message: Something happened during the request to send data from server, please contact your Administrator");
                      document.getElementById('errorOut').innerHTML = "Upload Failed, possibly bad connection to database, or the data already exists. Please contact your Administrator";
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
            <div className="row" style={{padding: "10%"}}>
              <h2 style={{margin:"20px", textAlign: "center"}}>Welcome to the Import Page!</h2>
              <p className="help-text" style={{margin:"20px", textAlign: "center"}}>You can import new Andar Data below. Please click the appropriate upload button depending on the data you are uploading</p>
               <form ref="uploadForm" className="uploader" encType="multipart/form-data" >
                   <input className="button success button" style={buttonStyle}  ref="file" id="CSVUpload" type="file" name="file" className="upload-file"/>
                   <input className="button success button" style={buttonStyle} type="button" ref="button" value="Upload Program File" onClick={this.importProgram} />
                   <input className="button success button" style={buttonStyle} type="button" ref="button" value="Upload Output File" onClick={this.importOutput} />
                   <br/><br/>
               </form>
               <h3 style={{margin: "20px"}}>Status of Upload</h3>
               <div id="errorOut" style={{margin:"20px"}}>Status of upload will be displayed here</div>
               <br/><br/>
              <div id="json">Json: </div>
                 <br/><br/>
            </div>
        );
    }
});

module.exports = Import;
