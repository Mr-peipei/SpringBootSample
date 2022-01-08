import logo from './logo.svg';
import './App.css';
import fetch from "node-fetch";
import {Component} from "react";

class App extends Component{
  constructor (props) {
    super(props)
    this.state= {
    }
  }
  componentWillMount() {
    const URL = 'http://localhost:8080/user/system@co.jp/'
  }

  render() {
     return <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  };
}

export default App;
