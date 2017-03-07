var React = require('react');

var FilterByFocusArea = React.createClass({
  getInitialState:function(){
    return {selectValue: '(nothing selected)'};
  },
  handleChange: function(e){
    this.setState({selectValue: e.target.value});
  },
  render:function(){
    var message = 'FilterByFocusArea: ' + this.state.selectValue;
    return(
      <div className="medium-3 columns">
        <label>Select focus area</label>
        <select multiple="multiple" size="3" value={this.state.selectValue} onChange={this.handleChange}>
          <option value="All that Kids can Be">All that Kids can Be</option>
          <option value="Building Stronger Communities">Building Stronger Communities</option>
          <option value="Other">Other</option>
        </select>
        <label>{message}</label>
      </div>
    )
  }
})

module.exports = FilterByFocusArea;
