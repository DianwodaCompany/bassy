import React, { createElement } from 'react';
import { withRouter } from 'react-router-dom';
import pathToRegexp from 'path-to-regexp';
import { Breadcrumb, Icon} from 'antd';
import {routes} from '../../router'

const getBreadcrumb = (route, url) => {
    let breadcrumb = {}
    route.map(r => {
        if(pathToRegexp(r.path).test(url)) {
            breadcrumb = r;
        }
    })
    return breadcrumb;
};

function urlToList(url) {
    const pathSnippets = url.split('/').filter(i => i);
    return pathSnippets.map((_, index) => `/${pathSnippets.slice(0, index + 1).join('/')}`);
}

function conversionFromLocation(routerLocation, route) {
    const linkElement = 'a';
    // Convert the url to an array
    const pathSnippets = urlToList(routerLocation.pathname);
    // Loop data mosaic routing
    const extraBreadcrumbItems = pathSnippets.map((url, index) => {
        const currentBreadcrumb = getBreadcrumb(route, url);
        if (currentBreadcrumb.inherited) {
            return null;
        }
        const isLinkable = index !== pathSnippets.length - 1 && currentBreadcrumb.main;
        const name = currentBreadcrumb.name;
        return name && !currentBreadcrumb.hideInBreadcrumb ? (
            <Breadcrumb.Item key={url}>
                {createElement(
                    isLinkable ? linkElement : 'span',
                    { [linkElement === 'a' ? 'href' : 'to']: "#"+url },
                    name
                )}
            </Breadcrumb.Item>
        ) : null;
    });
    // Add home breadcrumbs to your head
    extraBreadcrumbItems.unshift(
        <Breadcrumb.Item key="home">
            {createElement(
                linkElement,
                {
                    [linkElement === 'a' ? 'href' : 'to']: '/#',
                },
                <Icon type="home"  style={{ fontSize: '8px'}} theme="twoTone" twoToneColor="#F57400"/>
            )}
        </Breadcrumb.Item>
    );
    return (
        <Breadcrumb style={{ margin: '8px 0' }} separator=">">
            {extraBreadcrumbItems}
        </Breadcrumb>
    );
};

export const BreadcrumbView = withRouter((props) => {
    const { location } = props;
    // 根据 location 生成 面包屑
    // Generate breadcrumbs based on location
    if (location && location.pathname) {
        return conversionFromLocation(location, routes);
    }
    return null;
});

