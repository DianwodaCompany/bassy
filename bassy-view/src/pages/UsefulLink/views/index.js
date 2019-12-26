import React from 'react'
import {connect} from "react-redux";
import {Avatar, Carousel, Col, Divider, Row} from 'antd';
import style from './index.less';


@connect(state => ({
	usefulLinks: state.link.usefulLinks
}))
export default class UsefulLink extends React.Component {
	state = {};

	componentWillMount() {
	}

	onChange = (a, b, c) => {
		console.info(a, b, c);
	};

	renderSingleCarousel = (usefulLinks) => {
		return (
			usefulLinks.map((links) => (
				<div key={links.id}>
					<p style={{fontSize: 36, textAlign: "center"}}>{links.env}</p>
					{this.renderSingleDivider(links.links)}
				</div>
			))
		);
	};

	renderSingleDivider = (links) => {
		return (
			links.map((link) => (
				<div key={link.id}>
					<Divider orientation="left">{link.type}</Divider>
					<Row type="flex">
						{this.renderSingleCol(link.info)}
					</Row>
				</div>
			))
		);
	};

	renderSingleCol = (info) => {
		return (
			info.map((i) => (
				<Col span={4} key={i.id}>
					<a href={i.url} target="view_window">
						<div style={{textAlign: "center"}}>
							<Avatar size="large" style={{backgroundColor: '#001529'}}>{i.icon}</Avatar>
							<p>{i.title}</p>
						</div>
					</a>
				</Col>
			))
		);
	};

	render() {
		const {usefulLinks} = this.props;
		return (
			<Carousel afterChange={this.onChange} className="ant-carousel-class">
				{this.renderSingleCarousel(usefulLinks)}
			</Carousel>
		)
	}
}
