import React from 'react';
import 'fixed-data-table/dist/fixed-data-table.css';
import {Table, Column, Cell} from 'fixed-data-table';

// Render your table
var TableProgramInfo=React.createClass({

createMetadata: function(data) {
    var metadata = [];
    var program = data.Program;
    var outputs = data.Outputs;
    var totalMealsSnacks = 0;
    var totalConsellingSessions = 0;
    var totalMentorsTutors = 0;
    var totalWorkshops = 0;
    var totalVolunteers = 0;

    for(var i = 0; i < program.length; i++) {
        var row = [];
        var currentProgramName = null;

        currentProgramName = program[i].name;

        if(currentProgramName != null) {
            var currentProgramID = program[i].id;
            var currentMealsSnacks = 0;
            var currentConsellingSessions = 0;
            var currentMentorsTutors = 0;
            var currentWorkshops = 0;
            var currentVolunteers = 0;

            for(var j = 0; j < outputs.length; j++) {
                var currentOutput = outputs[j];
                var currentAndarID = currentOutput.andar_id;
                if(currentAndarID == currentProgramID) {
                    var currentType = currentOutput.type;
                    var currentValue = currentOutput.value;
                    switch(currentType) {
                        case "Meals/Snacks":
                            currentMealsSnacks = currentValue;
                            totalMealsSnacks += currentMealsSnacks;
                            break;
                        case "Counselling Sessions":
                            currentConsellingSessions = currentValue;
                            totalConsellingSessions += currentConsellingSessions;
                            break;
                        case "Mentors/Tutors":
                            currentMentorsTutors = currentValue;
                            totalMentorsTutors += currentMentorsTutors;
                            break;
                        case "Workshops":
                            currentWorkshops = currentValue;
                            totalWorkshops += currentWorkshops;
                            break;
                        case "Volunteers":
                            currentVolunteers = currentValue;
                            totalVolunteers += currentVolunteers;
                            break;
                        default:
                            //do nothing
                    }
                }
            }

            //row should have added 6 things
            row.push(currentProgramName);
            row.push(currentMealsSnacks);
            row.push(currentConsellingSessions);
            row.push(currentMentorsTutors);
            row.push(currentWorkshops);
            row.push(currentVolunteers);
        }

        metadata.push(row);
    }

    var totalRow = [];
    totalRow.push("Grand Total");
    totalRow.push(totalMealsSnacks);
    totalRow.push(totalConsellingSessions);
    totalRow.push(totalMentorsTutors);
    totalRow.push(totalWorkshops);
    totalRow.push(totalVolunteers);

    metadata.push(totalRow);

    return metadata;
},

render() {
    var dataFromDash = this.props.data;
    var metadata = this.createMetadata(dataFromDash)
    var heightTotal = metadata.length * 50 + 52;

    return (
      <Table
        rowHeight={50}
        rowsCount={metadata.length}
        width={1400}
        height={heightTotal}
        headerHeight={50}>
        <Column
          header={<Cell>Program Name</Cell>}
          cell={({rowIndex, ...props}) => (
            <Cell {...props}>
              {metadata[rowIndex][0]}
            </Cell>
          )}
          width={400}
        />
         <Column
          header={<Cell># Meals/Snacks</Cell>}
          cell={({rowIndex, ...props}) => (
            <Cell {...props}>
              {metadata[rowIndex][1]}
            </Cell>
          )}
          width={200}
        />
       <Column
          header={<Cell># Counselling Sessions</Cell>}
          cell={({rowIndex, ...props}) => (
            <Cell {...props}>
              {metadata[rowIndex][2]}
            </Cell>
          )}
          width={200}
        />
        <Column
          header={<Cell># Mentors/Tutors</Cell>}
          cell={({rowIndex, ...props}) => (
            <Cell {...props}>
              {metadata[rowIndex][3]}
            </Cell>
          )}
          width={200}
        />
        <Column
          header={<Cell># Workshops</Cell>}
          cell={({rowIndex, ...props}) => (
            <Cell {...props}>
              {metadata[rowIndex][4]}
            </Cell>
          )}
          width={200}
        />
        <Column
          header={<Cell># Volunteers</Cell>}
          cell={({rowIndex, ...props}) => (
            <Cell {...props}>
              {metadata[rowIndex][5]}
            </Cell>
          )}
          width={200}
        />
      </Table>
    );
  }
});
module.exports = TableProgramInfo;