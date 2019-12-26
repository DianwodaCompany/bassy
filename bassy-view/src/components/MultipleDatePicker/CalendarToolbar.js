import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { dateTimeFormat } from './dateUtils';
import styled from 'styled-components';

const Icon = styled.button`
  border: none;
  font-family: Roboto, sans-serif;
  text-decoration: none;
  outline: none;
  position: relative;
  z-index: 1;
  height: 36px;
  line-height: 1.25;
  overflow: hidden;
  background-color: rgba(0, 0, 0, 0);
  text-align: center;

  font-weight: bold;
  padding: 5px 8px;
  border-radius: 4px;
  cursor: pointer;
  font-style: normal;
  font-size: 0.7em;

  :hover {
    color: rgb(0, 188, 212);
  }
`;

const Root = styled.div`
  display: flex;
  justify-content: space-between;
  background-color: inherit;
  height: 48px;
`;

const TitleDiv = styled.div`
  font-size: 14px;
  font-weight: 500;
  text-align: center;
  width: 100%;
`;

const TitleText = styled.div`
  height: inherit;
  padding-top: 12px;
`;

class CalendarToolbar extends Component {
  static propTypes = {
    displayDate: PropTypes.object.isRequired,
    nextMonth: PropTypes.bool,
    onMonthChange: PropTypes.func,
    prevMonth: PropTypes.bool,
  };

  static defaultProps = {
    nextMonth: true,
    prevMonth: true,
  };

  state = {
    transitionDirection: 'up',
  };

  componentWillReceiveProps(nextProps) {
    if (nextProps.displayDate !== this.props.displayDate) {
      const direction = nextProps.displayDate > this.props.displayDate ? 'left' : 'right';
      this.setState({
        transitionDirection: direction,
      });
    }
  }

  handleTouchTapPrevMonth = e => {
    e.preventDefault();
    if (this.props.onMonthChange) {
      this.props.onMonthChange(-1);
    }
  };

  handleTouchTapNextMonth = e => {
    e.preventDefault();
    if (this.props.onMonthChange) {
      this.props.onMonthChange(1);
    }
  };

  render() {
    const { displayDate } = this.props;

    const dateTimeFormatted = new dateTimeFormat('en-US', {
      month: 'long',
      year: 'numeric',
    }).format(displayDate);

    return (
      <Root>
        <Icon disabled={!this.props.prevMonth} onClick={this.handleTouchTapPrevMonth}>
          {String.fromCharCode(9664)}
        </Icon>

        <TitleDiv>
          <TitleText key={dateTimeFormatted}>{dateTimeFormatted}</TitleText>
        </TitleDiv>
        <Icon disabled={!this.props.nextMonth} onClick={this.handleTouchTapNextMonth}>
          {String.fromCharCode(9654)}
        </Icon>
      </Root>
    );
  }
}

export default CalendarToolbar;
