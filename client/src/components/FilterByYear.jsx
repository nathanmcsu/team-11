var React = require('react');
var array = [];

var FilterByYear = React.createClass({
  getInitialState:function(){
    return {
      selectValue: []};
  },
  handleChange: function(e){
    this.setState({selectValue: array});
    var newSelection = JSON.stringify(e.target.value);
    var isPresent = false;
    var i;
    for(i = 0; i < array.length; i++){
      if(newSelection === array[i]){
        isPresent = true;
      }
    }
    if(!isPresent){
      array.push(JSON.stringify(e.target.value));
    }
  },

  render:
  function(){
    var message = 'FilterByYear: ' + this.state.selectValue;
    return(
      <div className="medium-3 columns">
        <label>Select year</label>
        <select multiple={{true}} size="3" value={[]} onChange={this.handleChange}>
          <option value="2017/18">2017/18</option>
          <option value="2016/17">2016/17</option>
          <option value="2015/16">2015/16</option>
        </select>
        <label>{message}</label>
      </div>
    )
  },
})

module.exports = FilterByYear;
