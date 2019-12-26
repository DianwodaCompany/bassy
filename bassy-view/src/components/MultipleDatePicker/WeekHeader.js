import React from 'react';
import styled from 'styled-components';

const StyledDiv = styled.div`
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: space-between;
  font-weight: 500;
  line-height: 1.25;
  opacity: 0.8;
  text-align: center;
`;

const StyledSpan = styled.span`
  padding: 0.5rem;
`;

const WeekHeader = () => (
  <StyledDiv>
    <StyledSpan>S</StyledSpan>
    <StyledSpan>M</StyledSpan>
    <StyledSpan>T</StyledSpan>
    <StyledSpan>W</StyledSpan>
    <StyledSpan>T</StyledSpan>
    <StyledSpan>F</StyledSpan>
    <StyledSpan>S</StyledSpan>
  </StyledDiv>
);

export default WeekHeader;
