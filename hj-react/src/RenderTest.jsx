const React = require('react');
const { PureComponent } = React;

class RenderTest extends PureComponent {
  state = {
    counter: 0,
    string: 'hello',
    number: 1,
    boolean: true,
    object: {},
    array: [],
  }

  onClick = () => {
    this.setState({
      array: [...this.state.array, 1]
    });
  }

  render() {
    console.log('렌더링', this.state)
    return (
      <div>
        <button onClick={this.onClick}>클릭</button>
      </div>
    )
  }
}

module.exports = RenderTest;