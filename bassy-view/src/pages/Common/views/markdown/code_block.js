import './marked.css'
const React = require('react');
const PropTypes = require('prop-types');
const hljs = require('highlight.js');
import 'highlight.js/styles/github.css';


export default class CodeBlock extends React.PureComponent {
    constructor(props) {
        super(props);
        this.setRef = this.setRef.bind(this)
    }

    setRef(el) {
        this.codeEl = el
    }

    componentDidMount() {
        this.highlightCode()
    }

    componentDidUpdate() {
        this.highlightCode()
    }

    highlightCode() {
        hljs.highlightBlock(this.codeEl)
    }

    render() {
        return (
            <pre>
                <code ref={this.setRef} className={`language-${this.props.language}`}>
                    {this.props.value}
                </code>
            </pre>
        )
    }
}

CodeBlock.defaultProps = {
    language: ''
};

CodeBlock.propTypes = {
    value: PropTypes.string.isRequired,
    language: PropTypes.string
};