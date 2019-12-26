import React, { Component } from 'react';
import styled from 'styled-components';
import DateUtilities from './utils';
import { dateTimeFormat } from './dateUtils';

const StyledWeek = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 34px;
  margin-bottom: 2px;
`;

const DayButton = styled.button`
  border: 10px;
  box-sizing: border-box;
  display: inline-block;
  font-family: Roboto, sans-serif;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  cursor: pointer;
  text-decoration: none;
  margin: 0;
  padding: 0.5rem;
  outline: none;
  font-size: inherit;
  font-weight: 400;
  position: relative;
  z-index: 1;
  background: none;
  text-align: right;
`;

const Blank = styled.div`
  border: 10px;
  box-sizing: border-box;
  display: inline-block;
  font-family: Roboto, sans-serif;
  text-decoration: none;
  margin: 0;
  padding: 0.5rem;
  outline: none;
  font-size: inherit;
  font-weight: 400;
  position: relative;
  z-index: 1;
  background: none;
`;

const DayBackdrop = styled.div`
  background-color: rgb(0, 151, 167);
  height: 34px;
  border-radius: 50%;
  left: 0;
  opacity: ${({ selected }) => (selected ? '1' : '0')};
  position: absolute;
  top: 0;
  transform: scale(${({ selected }) => (selected ? '1' : '0')});
  transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms;
  width: 34px;
`;

const Day = styled.div`
  color: ${({ selected }) => (selected ? 'rgb(255, 255, 255)' : 'rgba(0, 0, 0, 0.87)')};
  font-weight: ${({ today }) => (today ? 'bold' : '400')};
  font-size: ${({ today }) => (today ? '1.1rem' : 'auto')};
  position: relative;
  color: ${({ disabled }) => (disabled ? 'lightgrey' : 'auto')};
`;

class Week extends Component {
  onSelect = day => {
    if (!this.isDisabled(day)) this.props.onSelect(day);
  };

  isDisabled = day => {
    let minDate = this.props.minDate,
      maxDate = this.props.maxDate;

    return (minDate && DateUtilities.isBefore(day, minDate)) || (maxDate && DateUtilities.isAfter(day, maxDate));
  };

  isSelected = day => this.props.selectedDates && DateUtilities.dateIn(this.props.selectedDates, day);

  render() {
    const dateInNumberic = new dateTimeFormat('en-US', {
      day: 'numeric',
      month: 'numeric',
      year: 'numeric',
    });

    const dateToday = dateInNumberic.format(new Date());

    const dayInNumeric = new dateTimeFormat('en-US', {
      day: 'numeric',
    });
    return (
      <StyledWeek>
        {this.props.week.map((day, i) => {
          if (day) {
            const isToday = day && dateToday === dateInNumberic.format(day);
            const isDisabled = this.isDisabled(day);
            const isSelected = this.isSelected(day);

            return (
              <DayButton
                key={`day-${day}`}
                onClick={(e) => {e.preventDefault(); this.onSelect(day)}}
                disabled={isDisabled}
                selected={isSelected}
              >
                <DayBackdrop selected={isSelected} />
                <Day selected={isSelected} disabled={isDisabled} today={isToday}>
                  {dayInNumeric.format(day)}
                </Day>
              </DayButton>
            );
          }
          return <Blank key={`blank-${i}`} />;
        })}
      </StyledWeek>
    );
  }
}

export default Week;
