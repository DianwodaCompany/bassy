import React, { Component } from 'react';
import styled from 'styled-components';
import moment from "moment";

const Root = styled.div`
  width: 165px;
  height: 330px;
  float: left;
  font-weight: 700;
  display: inline-block;
  background-color: rgb(0, 188, 212);
  border-top-left-radius: 2px;
  border-top-right-radius: 0px;
  border-bottom-left-radius: 2px;
  color: rgb(255, 255, 255);
  padding: 20px;
  box-sizing: border-box;
  overflow-y: auto;
  @media (max-width: 400px) {
    display: none;
  }
`;

const DateTime = styled.div`
  position: relative;
  overflow: hidden;
  height: 16px;
  margin: 0px 0px 10px;
  font-size: 16px;
  font-weight: 500;
  line-height: 16px;
  transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms;
`;

class DateDisplay extends Component {
  state = {
    selectedYear: false,
  };

  componentWillMount() {
    if (!this.props.monthDaySelected) {
      this.setState({ selectedYear: true });
    }
  }

  getFormatedDate = date => {
    const dateTime = moment(date).format("YYYY-MM-DD");
    return `${dateTime}`;
  };

  render() {
    const { selectedDates } = this.props;

    return (
      <Root>
        {selectedDates.map(date => <DateTime key={`${date.toString()}`}>{this.getFormatedDate(date)}</DateTime>)}
      </Root>
    );
  }
}

export default DateDisplay;
